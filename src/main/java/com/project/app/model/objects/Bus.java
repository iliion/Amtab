package com.project.app.model.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {

    @JsonProperty("IdCorsa")
    private int idCorsa;

    public Bus() {
    }

    public Bus(int idCorsa) {
        this.idCorsa = idCorsa;
    }

    public int getIdCorsa() {
        return idCorsa;
    }

    public void setIdCorsa(int idCorsa) {
        this.idCorsa = idCorsa;
    }

}
