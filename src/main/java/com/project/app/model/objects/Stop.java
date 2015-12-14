package com.project.app.model.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stop {

    @JsonProperty("DescrizioneFermata")
    private String descrizioneFermata;

    @JsonProperty("IdFermata")
    private String idFermata;

    public Stop() {
    }

    public Stop(String descrizioneFermata, String idFermata) {
        this.descrizioneFermata = descrizioneFermata;
        this.idFermata = idFermata;
    }

    public String getIdFermata() {
        return idFermata;
    }

    public void setIdFermata(String idFermata) {
        this.idFermata = idFermata;
    }

    public String getDescrizioneFermata() {
        return descrizioneFermata;
    }

    public void setDescrizioneFermata(String descrizioneFermata) {
        this.descrizioneFermata = descrizioneFermata;
    }

}
