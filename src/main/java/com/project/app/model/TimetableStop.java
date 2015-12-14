package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimetableStop {

    // OrariPalina/{idFermata}
    // IdFermata": "08129003",
    // "PrevisioniLinee":[
    // {
    // "DirezioneLinea": "R",
    // "IdCorsa": 134270,
    // "IdLinea": "03",
    // "OrarioArrivo": "/Date(1448360809000+0100)/",
    // "TipoPrevisione": "T",
    // "UltimeCoordinateMezzo": null
    // },.....
    // ]

    @JsonProperty("IdFermata")
    private String idStop;

    @JsonProperty("PrevisioniLinee")
    private Prevision[] prevision;

    public TimetableStop() {
    }

    public String getIdStop() {
        return idStop;
    }

    public void setIdStop(String idStop) {
        this.idStop = idStop;
    }

    public Prevision[] getPrevision() {
        return prevision;
    }

    public void setPrevision(Prevision[] prevision) {
        this.prevision = prevision;
    }

}
