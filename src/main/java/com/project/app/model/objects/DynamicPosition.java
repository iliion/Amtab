package com.project.app.model.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DynamicPosition extends Position {

    @JsonProperty("DataOraAcquisizioneIt")
    private String dataOraAcquisizioneIt;

    @JsonProperty("Direzione")
    private String direzione;

    @JsonProperty("VelocitaKmh")
    private int velocitaKmh;

    public DynamicPosition() {
        super();
    }

    public String getDataOraAcquisizioneIt() {
        return dataOraAcquisizioneIt;
    }

    public void setDataOraAcquisizioneIt(String dataOraAcquisizioneIt) {
        this.dataOraAcquisizioneIt = dataOraAcquisizioneIt;
    }

    public String getDirezione() {
        return direzione;
    }

    public void setDirezione(String direzione) {
        this.direzione = direzione;
    }

    public int getVelocitaKmh() {
        return velocitaKmh;
    }

    public void setVelocitaKmh(int velocitaKmh) {
        this.velocitaKmh = velocitaKmh;
    }

}
