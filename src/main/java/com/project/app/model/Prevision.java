package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.model.objects.DynamicPosition;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prevision {

    // "DirezioneLinea": "R",
    // "IdCorsa": 134317,
    // "IdLinea": "03",
    // "OrarioArrivo": "/Date(1448293723000+0100)/",
    // "TipoPrevisione": "M",
    // "UltimeCoordinateMezzo":

    @JsonProperty("DirezioneLinea")
    private String direzioneLinea;

    @JsonProperty("IdCorsa")
    private int idCorsa;

    @JsonProperty("IdLinea")
    private String idLinea;

    @JsonProperty("OrarioArrivo")
    private String orarioArrivo;

    @JsonProperty("TipoPrevisione")
    private String tipoPrevisione;

    @JsonProperty("UltimeCoordinateMezzo")
    private DynamicPosition position;

    public Prevision() {
    }

    /****** Real Time Setters&Getters ********/

    public String getTipoPrevisione() {
        return tipoPrevisione;
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

    public String getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(String idLinea) {
        this.idLinea = idLinea;
    }

    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public void setTipoPrevisione(String tipoPrevisione) {
        this.tipoPrevisione = tipoPrevisione;
    }

    public DynamicPosition getPosition() {
        return position;
    }

    public void setPosition(DynamicPosition position) {
        this.position = position;
    }

}
