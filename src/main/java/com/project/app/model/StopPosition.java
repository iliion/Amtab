package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.model.objects.Position;
import com.project.app.model.objects.Stop;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPosition extends Stop {

    // rete/Fermate/{*idFermate}
    // "DescrizioneFermata": "Viale S. Dioguardi fron str. del Caffè",
    // "IdFermata": "03227101",
    // "PosizioneFermata":{
    // "Latitudine": "41.0963",
    // "Longitudine": "16.8629"
    // }

    @JsonProperty("PosizioneFermata")
    private Position posizioneFermata;

    public StopPosition() {
        super();
    }

    public Position getPosizioneFermata() {
        return posizioneFermata;
    }

    public void setPosizioneFermata(Position posizioneFermata) {
        this.posizioneFermata = posizioneFermata;
    }

}
