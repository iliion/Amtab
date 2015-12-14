package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.model.objects.Bus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusStop extends Bus {

    @JsonProperty("OrarioArrivo")
    private String orarioArrivo;

    @JsonProperty("TipoPrevisione")
    private String tipoPrevisione;

    public BusStop() {
        super();
    }

    public String getOrarioArrivo() {
        return orarioArrivo;
    }

    public void setOrarioArrivo(String orarioArrivo) {
        this.orarioArrivo = orarioArrivo;
    }

    public String getTipoPrevisione() {
        return tipoPrevisione;
    }

    public void setTipoPrevisione(String tipoPrevisione) {
        this.tipoPrevisione = tipoPrevisione;
    }

}
