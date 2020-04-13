package com.codenation.controller;

import com.codenation.exception.FileControllerException;
import com.codenation.model.Message;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@NoArgsConstructor
@Slf4j
public class FileController {

    private String fileUrl = "answer.json";

    public void save(Message message) throws FileControllerException {
        log.info("Salvando mensage... ");
        try (FileWriter file = new FileWriter(fileUrl)) {
            file.write("");
            file.flush();

            file.write(String.valueOf(new Gson().toJsonTree(message)));
            file.flush();
        } catch (IOException e) {
            String erroMessage = "Erro ao acessar json. ";
            log.error(erroMessage);
            throw new FileControllerException(erroMessage, e);
        }
        log.info("Mensagem salva. ");
    }

    public File getSavedFile(){
        return new File(fileUrl);
    }
}
