package com.codenation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@NoArgsConstructor
public class Message {
    private Integer numero_casas;
    private String token;
    private String cifrado;
    private String decifrado;
    private String resumo_criptografico;

    @Override
    public String toString() {
        return "Message{" +
                "numeroCasas=" + numero_casas +
                ", token='" + token + '\'' +
                ", cifrado='" + cifrado + '\'' +
                ", decifrado='" + decifrado + '\'' +
                ", resumoCriptografico='" + resumo_criptografico + '\'' +
                '}';
    }
}
