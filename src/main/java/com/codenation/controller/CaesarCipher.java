package com.codenation.controller;

import com.codenation.exception.CipherException;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class CaesarCipher {

    public String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toLowerCase().toCharArray()) {
            if (Character.isLetter(character)) {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decipher(String message, int offset) {
        log.info("Decifrando mensagem...");
        String deciphered = cipher(message, 26 - (offset % 26));
        log.info("Mensagem decifrada: ".concat(deciphered));
        return deciphered;
    }

    public String cryptographResume(String decipheredMessage) {
        log.info("Criando resumo criptográfico...");
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(decipheredMessage.getBytes(StandardCharsets.UTF_8), 0, decipheredMessage.length());
            String sha1Message = DatatypeConverter.printHexBinary(msdDigest.digest()).toLowerCase();
            log.info("Resumo criptográfico: ".concat(sha1Message));
            return sha1Message;
        } catch (NoSuchAlgorithmException e) {
            String errorMessage = "Erro ao criar resumo criptográfico. ";
            log.error(errorMessage);
            throw new CipherException(e.getMessage(), e);
        }
    }
}
