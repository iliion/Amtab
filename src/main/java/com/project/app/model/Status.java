package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.model.objects.DynamicPosition;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Status {

    @JsonProperty("IdLinea")
    private String idLinea;
    @JsonProperty("DirezioneLinea")
    private String direzioneLinea;
    @JsonProperty("IdCorsa")
    private int idCorsa;
    @JsonProperty("IdProssimaFermata")
    private String idProssimaFermata;
    @JsonProperty("UltimeCoordinateMezzo")
    private DynamicPosition ultimeCoordinateMezzo;

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }

    public String getDirezioneLinea() {
        return direzioneLinea;
    }

    public void setDirezioneLinea(String direzioneLinea) {
        this.direzioneLinea = direzioneLinea;
    }

    public int getIdCorsa() {
        return idCorsa;
    }

    public void setIdCorsa(int idCorsa) {
        this.idCorsa = idCorsa;
    }

    public String getIdProssimaFermata() {
        return idProssimaFermata;
    }

    public void setIdProssimaFermata(String idProssimaFermata) {
        this.idProssimaFermata = idProssimaFermata;
    }

    public DynamicPosition getUltimeCoordinateMezzo() {
        return ultimeCoordinateMezzo;
    }

    public void setUltimeCoordinateMezzo(DynamicPosition ultimeCoordinateMezzo) {
        this.ultimeCoordinateMezzo = ultimeCoordinateMezzo;
    }

}
