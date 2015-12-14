package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.model.objects.Bus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DailyBusService extends Bus {

    // ServizioGiornaliero/{idLinea}/{*idFermata}
    // {
    // "Direzione": "A",
    // "IdCorsa": 121804,
    // "IdFermata": "01135C00",
    // "Orario": "/Date(1448360400000+0100)/",
    // "Progressivo": 42
    // }

    @JsonProperty("Direzione")
    private String direzione;

    @JsonProperty("IdFermata")
    private String idFermata;

    @JsonProperty("Orario")
    private String orario;

    @JsonProperty("Progressivo")
    private int progressivo;

    public DailyBusService() {
        super();
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

    public String getOrario() {
        return orario;
    }

    public void setOrario(String orario) {
        this.orario = orario;
    }

    public int getProgressivo() {
        return progressivo;
    }

    public void setProgressivo(int progressivo) {
        this.progressivo = progressivo;
    }

}
