package com.project.app.controllers;

import com.project.app.model.CloseStop;
import com.project.app.model.DailyBusService;
import com.project.app.model.EveryStop;
import com.project.app.model.Status;
import com.project.app.model.StopPosition;
import com.project.app.model.TimetableStop;
import com.project.app.model.objects.Line;

public interface IController {

    /*
     * MezziLinea(IdLinea) : Restituisce la lista delle ultime posizioni dei mezzi operanti su di
     * una linea (una posizione per mezzo).
     *
     * @Param IdLinea Identifcativo della linea da interrogare. (ES.: '01').
     */
    public abstract Status[] status(String idLine);

    /*
     * NumCorseGiorno() : Funzione che restituisce il numero di corse previste per il giorno
     * corrente. La funzione non ha parametri e puΓ² essere utile per individuare i casi in cui per
     * il giorno corrente ci sono state variazioni nel servizio giornaliero. Quando avviene una
     * variazione del numero di corse, infatti, il servizio Γ¨ cambiato e quindi si deve procedere
     * all'aggiornamento.
     */
    public abstract int totalRoutes();

    /*
     * OrariPalina(IdFermata, TeoricoRealTime) : Restituisce la lista delle linee passanti per la
     * fermata ed il relativo orario del prossimo passaggio. Per poter chiamare la funzione Γ¨
     * necessario conoscere gli identificativi delle fermate da interrogare.
     * 
     * @Param IdFermata Identificativo della fermata (palina) da interrogare. (ES.: '08129003')
     * 
     * @Param TeoricoRealTime Questo parametro (opzionale) puΓ² assumere i seguenti valori:
     * 'teorico' - per indicare che devono essere restituite le sole previsioni teoriche delle
     * linee, ovvero le previsioni prestabilite 'realtime' - per indicare che devono essere
     * restituite le sole previsioni realtime, ovvero calcolate in base alla posizione del bus
     * 'null' - quando non specificato la funzione restituisce sia le previsioni teoriche che
     * monitorate
     */
    public abstract TimetableStop timetableStops(String idStop);

    public abstract TimetableStop timetableStops(String idStop, String scope);

    /*
     * Fermate(*IdFermate) : Restituisce la lista di tutte le fermate della rete TPL o un
     * sottoinsieme delle stesse, filtrate in base al parametro opzionale IdFermate.
     *
     * @Param IdFermate (opzionale) Identificativo delle fermate di cui si vuole conoscere i
     * dettagli. Il parametro puΓ² essere utile quando si vuole un set ridotto dei dati, usando gli
     * identificativi come filtro. Il separatore delle fermate nel caso di identificazione multipla
     * Γ¨ la virgola (,). ES. di selezione singola: '09369C01' ES. di selezione multipla:
     * '08129003,09369C01'
     */
    public abstract StopPosition[] stops();

    public abstract StopPosition[] stops(String idStop);

    /*
     * FermateLinea(IdLinea) : Restituisce la lista delle fermate afferenti ad una linea.
     *
     * @Param
     */
    public abstract EveryStop[] busStops(String idLine);

    /*
     * FermateLineaTeoriche(IdLinea, *Orario) : Restituisce la lista delle fermate teoriche
     * afferenti ad una linea. La differenza sostanziale dalla funzione Fermate Linea Γ¨ la presenza
     * del progressivo fermata teorico per ciascuna fermata restituita.Tale progressivo Γ¨ ottenuto
     * in base alla corsa teorica attuale (o futura nel caso sia indicato l'orario mediante
     * l'apposito parametro). Il progressivo fermata ha la funzione di stabilire l'ordine di
     * passaggio del bus dalle fermate, oltre a definire con esattezza quali fermate verranno
     * effettivamente percorse del bus.
     * 
     * @Param IdLinea Identificativo della linea da interrogare.
     * 
     * @Param Orario (opzionale) Orario utile per identificare la corsa teorica e quindi le fermate
     * teoriche con progressivo nell'ora specifica. Il formato del parametro Γ¨ hhmm dove hh sono le
     * ore e mm sono i minuti (ES.: '1530' dove 15 sono le ore e 30 sono i minuti, ovvero le 15:30).
     */
    public abstract EveryStop[] theoreticBusStops(String idLine);

    public abstract EveryStop[] theoreticBusStops(String idLine, String timetable);

    /*
     * FermateVicine(Lat, Lon, *DistanzaMassima) : Restituisce la lista delle fermate teoriche
     * vicine ad un punto le cui coordinate geografiche sono rappresentate dai parametri lat
     * (latitudine) e lon (longitudine), nel sistema di riferimento WGS84 Geografico, ad una
     * distanza massima (euclidea) di 500 metri, salvo diversamente specificato nel parametro
     * opzionale DistanzaMassima (con range di valori fra 5 e 5000 metri).
     * 
     * @Param Lat Latitudine del punto da cui far partire la ricerca delle fermate vicine.
     * 
     * @Param Lon Longitudine del punto da cui far partire la ricerca delle fermate vicine.
     * 
     * @Param DistanzaMassima (opzionale) Distanza massima euclidea in cui effettuare la ricerca
     * delle fermate vicine, espressa in metri. Il range di valori ammessi Γ¨ [5,5000]. In caso di
     * valori inferiori o superiori al range indicato, il sistema automaticamente imposterΓ 
     * rispettivamente il valore minimo o massimo del range consentito.
     */
    public abstract CloseStop[] closeBusStops(String lan, String lon);

    public abstract CloseStop[] closeBusStops(String lan, String lon, int maxDistance);

    /*
     * Linee() : Restituisce la lista di tutte le linee della rete TPL. La funzione non ha
     * parametri.
     */
    public abstract Line[] routes();

    /*
     * ServizioGiornaliero(IdLinea, *IdFermata) : Restituisce la lista degli orari di passaggio
     * teorici del servizio giornaliero per la linea e la fermata (se indicata) selezionata.
     *
     * Per poter chiamare la funzione Γ¨ necessario conoscere gli identificativi delle linee da
     * interrogare (ottenibili dalla funzione linee).
     *
     * @Param IdLinea
     */
    public abstract DailyBusService[] dailyService(String idLine);

    public abstract DailyBusService[] dailyService(String idLine, String idStop);

}