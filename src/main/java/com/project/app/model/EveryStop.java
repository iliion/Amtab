package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EveryStop {

    // rete/FermateLineaTeoriche/{idLinea}
    // "Direzione": "Andata",
    // "IdFermata": "08229C00",
    // "ProgressivoTeorico": 1

    @JsonProperty("Direzione")
    private String direzione;
    @JsonProperty("IdFermata")
    private String idFermata;
    @JsonProperty("ProgressivoTeorico")
    private int progressivoTeorico;

    public EveryStop() {
    }

    public String getDirezione() {
        return direzione;
    }

    public void setDirezione(String direzione) {
        this.direzione = direzione;
    }

    public String getIdFermata() {
        return idFermata;
    }

    public void setIdFermata(String idFermata) {
        this.idFermata = idFermata;
    }

    public int getProgressivoTeorico() {
        return progressivoTeorico;
    }

    public void setProgressivoTeorico(int progressivoTeorico) {
        this.progressivoTeorico = progressivoTeorico;
    }

}
