package com.codenation.controller;

import com.codenation.model.Message;
import com.codenation.model.ProgrammerSingleton;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;

@Slf4j
public class FileUploadService {
    @Setter
    private RestTemplate restTemplate;

    public void postFile(Message message, File file, ProgrammerSingleton singleton) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(singleton.requestUrlSubmitSolution);
        builder.queryParam("numero_casas", message.getNumero_casas());
        builder.queryParam("token", singleton.token);
        builder.queryParam("cifrado", message.getCifrado());
        builder.queryParam("decifrado", message.getDecifrado());
        builder.queryParam("resumo_criptografico", message.getResumo_criptografico());

        LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("answer", new FileSystemResource(file));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(params, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                String.class);

        log.info("Resultado da requisição: " + responseEntity.toString());
    }
}
