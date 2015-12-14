package com.project.app.model.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Line {

    @JsonProperty("IdLinea")
    private String idLinea;

    @JsonProperty("DescrizioneLinea")
    private String descrizioneLinea;

    public Line() {
    }

    public Line(String idLinea, String descrizioneLinea) {
        this.idLinea = idLinea;
        this.descrizioneLinea = descrizioneLinea;
    }

    public String getDescrizioneLinea() {
        return descrizioneLinea;
    }

    public void setDescrizioneLinea(String descrizioneLinea) {
        this.descrizioneLinea = descrizioneLinea;
    }

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }

}
