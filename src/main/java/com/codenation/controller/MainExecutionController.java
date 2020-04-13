package com.codenation.controller;

import com.codenation.exception.CipherException;
import com.codenation.model.Message;
import com.codenation.model.ProgrammerSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.io.File;


@Slf4j
public class MainExecutionController {

    public void executeChallenge(RestTemplate restTemplate, ProgrammerSingleton singleton) {

        Message message = restTemplate.getForObject(singleton.requestUrlGenerateData, Message.class);

        if (message == null || message.getCifrado() == null) {
            String errorMessage = "Erro ao fazer requisição.";
            log.error(errorMessage);
            throw new CipherException(errorMessage);
        }

        log.info(message.toString());

        FileController fileController = new FileController();
        fileController.save(message);

        CaesarCipher caesarCipher = new CaesarCipher();
        message.setDecifrado(caesarCipher.decipher(message.getCifrado(), message.getNumero_casas()));

        message.setResumo_criptografico(caesarCipher.cryptographResume(message.getDecifrado()));
        fileController.save(message);

        FileUploadService fileUploadService = new FileUploadService();
        fileUploadService.setRestTemplate(restTemplate);
        File savedFile = fileController.getSavedFile();
        fileUploadService.postFile(message, savedFile, singleton);
    }
}
