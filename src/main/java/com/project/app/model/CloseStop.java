package com.project.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.app.model.objects.Position;
import com.project.app.model.objects.Stop;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CloseStop extends Stop {

    // rete/FermateVicine/{lat}/{lon}/{*distanzaMassima}
    // {
    // "DescrizioneFermata": "PIAZZA MORO ,CAPOLINEA",
    // "DistanzaMetri": 37,
    // "IdFermata": "09369C01",
    // "ListaLinee":[{
    // "Direzione": "A", "IdLinea": "07"
    // },
    // {"Direzione": "A",…],
    // }
    // "PosizioneFermata":{
    // "Latitudine": "41.1184425057675",
    // "Longitudine": "16.869695498173"
    // }

    @JsonProperty("DistanzaMetri")
    private int distanzaMetri;

    @JsonProperty("ListaLinee")
    private Lines[] listaLinee;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Lines {

        @JsonProperty("Direzione")
        private String direzione;

        @JsonProperty("IdLinea")
        private String idLinea;

        public String getDirezione() {
            return direzione;
        }

        public void setDirezione(String direzione) {
            this.direzione = direzione;
        }

        public String getIdLinea() {
            return idLinea;
        }

        public void setIdLinea(String idLinea) {
            this.idLinea = idLinea;
        }
    }

    public CloseStop() {
        super();
    }

    @JsonProperty("PosizioneFermata")
    private Position posizioneFermata;

    public Position getPosizioneFermata() {
        return posizioneFermata;
    }

    public void setPosizioneFermata(Position posizioneFermata) {
        this.posizioneFermata = posizioneFermata;
    }

}
