package com.codenation.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class ProgrammerSingleton {
    private static ProgrammerSingleton singleInstance = null;

    public String requestUrlGenerateData;
    public String requestUrlSubmitSolution;
    public String token;

    private ProgrammerSingleton(){
        token = "d3857b93bbf98ecb6691062a446fd44236ded79a";
        requestUrlGenerateData = "https://api.codenation.dev/v1/challenge/dev-ps/generate-data?token=" + token;
        requestUrlSubmitSolution = "https://api.codenation.dev/v1/challenge/dev-ps/submit-solution?token=" + token;
    }

    public static ProgrammerSingleton getInstance(){
        if (singleInstance == null){
            singleInstance = new ProgrammerSingleton();
        }
        return singleInstance;
    }
}
