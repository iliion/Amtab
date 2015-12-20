package com.project.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.project.app.model.CloseStop;
import com.project.app.model.DailyBusService;
import com.project.app.model.EveryStop;
import com.project.app.model.Status;
import com.project.app.model.StopPosition;
import com.project.app.model.TimetableStop;
import com.project.app.model.objects.Line;
import com.project.app.services.converters.ConverterUtils;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.repositories.StopRepository;

// @RestController is without @ResponseBody
/*
 * The @ResponseBody annotation is similar to @RequestBody. This annotation can be put on a method
 * and indicates that the return type should be written straight to the HTTP response body (and not
 * placed in a Model, or interpreted as a view name).
 */
@Controller
@RequestMapping("/amtab")
public class AmtabController implements IController {

    final static Logger logger = LoggerFactory.getLogger(AmtabController.class);

    @Autowired
    StopRepository stopRepo;

    final private String baseUrl = "http://bari.opendata.planetek.it/OrariBus/v2.1/OpenDataService.svc/REST";

    /**
     * REST ENDPOINTS
     *
     *
     *
     **/

    // MezziLinea/{idLinea}
    @Override
    @RequestMapping(value = "/rest/line/{idLine}", method = RequestMethod.GET)
    public @ResponseBody Status[] status(@PathVariable("idLine") String idLine) {
        String lineUrl = baseUrl + "/MezziLinea/" + idLine;
        RestTemplate restTemplate = new RestTemplate();
        Status[] line = restTemplate.getForObject(lineUrl, Status[].class);

        return line;
    }

    // NumCorseGiorno
    @Override
    @RequestMapping(value = "/rest/totalRoutes", method = RequestMethod.GET)
    public @ResponseBody int totalRoutes() {
        String totalRoutesUrl = baseUrl + "/NumCorseGiorno";
        RestTemplate restTemplate = new RestTemplate();
        int totalRoutes = restTemplate.getForObject(totalRoutesUrl, int.class);

        return totalRoutes;
    }

    // OrariPalina/{idFermata}
    @Override
    @RequestMapping(value = "/rest/timetable/{idStop}", method = RequestMethod.GET)
    public @ResponseBody TimetableStop timetableStops(@PathVariable("idStop") String idStop) {
        String timetableStopsUrl = baseUrl + "/OrariPalina/" + idStop;
        RestTemplate restTemplate = new RestTemplate();
        TimetableStop stop = restTemplate.getForObject(timetableStopsUrl, TimetableStop.class);

        return stop;
    }

    // OrariPalina/{idFermata}/{*teoricoRealTime}
    @Override
    @RequestMapping(value = "/rest/timetable/{idStop}/{scope}", method = RequestMethod.GET)
    public @ResponseBody TimetableStop timetableStops(@PathVariable("idStop") String idStop,
            @PathVariable("scope") String scope) {
        String timetableStopsUrl = baseUrl + "/OrariPalina/" + idStop + "/" + scope;
        RestTemplate restTemplate = new RestTemplate();
        TimetableStop stop = restTemplate.getForObject(timetableStopsUrl, TimetableStop.class);

        return stop;
    }

    // rete/Fermate
    @Override
    @RequestMapping(value = "/rest/network/stops", method = RequestMethod.GET)
    public @ResponseBody StopPosition[] stops() {
        String stopUrl = baseUrl + "/rete/Fermate";
        RestTemplate restTemplate = new RestTemplate();
        StopPosition[] stops = restTemplate.getForObject(stopUrl, StopPosition[].class);

        return stops;
    }

    // rete/Fermate/{*idFermate}
    @Override
    @RequestMapping(value = "/rest/network/stops/{idStop}", method = RequestMethod.GET)
    public @ResponseBody StopPosition[] stops(@PathVariable("idStop") String idStop) {
        String stopUrl = baseUrl + "/rete/Fermate/" + idStop;
        RestTemplate restTemplate = new RestTemplate();
        StopPosition[] stops = restTemplate.getForObject(stopUrl, StopPosition[].class);

        return stops;
    }

    // rete/FermateLinea/{idLinea}
    @Override
    @RequestMapping(value = "/rest/network/busStops/{idLine}", method = RequestMethod.GET)
    public @ResponseBody EveryStop[] busStops(@PathVariable("idLine") String idLine) {
        String busStopsUrl = baseUrl + "/rete/FermateLinea/" + idLine;
        RestTemplate restTemplate = new RestTemplate();
        EveryStop[] stops = restTemplate.getForObject(busStopsUrl, EveryStop[].class);

        return stops;
    }

    // rete/FermateLineaTeoriche/{idLinea}
    @Override
    @RequestMapping(value = "/rest/network/theoreticBusStops/{idLine}", method = RequestMethod.GET)
    public @ResponseBody EveryStop[] theoreticBusStops(@PathVariable("idLine") String idLine) {
        String theoreticBusStopsUrl = baseUrl + "/rete/FermateLineaTeoriche/" + idLine;
        RestTemplate restTemplate = new RestTemplate();
        EveryStop[] stops = restTemplate.getForObject(theoreticBusStopsUrl, EveryStop[].class);

        return stops;
    }

    // rete/FermateLineaTeoriche/{idLinea}/{*orario}
    @Override
    @RequestMapping(value = "/rest/network/theoreticBusStops/{idLine}/{timetable}", method = RequestMethod.GET)
    public @ResponseBody EveryStop[] theoreticBusStops(@PathVariable("idLine") String idLine,
            @PathVariable("timetable") String timetable) {
        String theoreticBusStopsUrl = baseUrl + "/rete/FermateLineaTeoriche/" + idLine + "/"
                + timetable;
        RestTemplate restTemplate = new RestTemplate();
        EveryStop[] stops = restTemplate.getForObject(theoreticBusStopsUrl, EveryStop[].class);

        return stops;
    }

    // rete/FermateVicine/{lat}/{lon}
    @Override
    @RequestMapping(value = "/rest/network/closeBusStops/{lat}/{lon}", method = RequestMethod.GET)
    public @ResponseBody CloseStop[] closeBusStops(@PathVariable("lat") String lat,
            @PathVariable("lon") String lon) {
        String closeBusStopsUrl = baseUrl + "/rete/FermateVicine/" + lat + "/" + lon;
        RestTemplate restTemplate = new RestTemplate();
        CloseStop[] pois = restTemplate.getForObject(closeBusStopsUrl, CloseStop[].class);

        return pois;
    }

    // rete/FermateVicine/{lat}/{lon}/{*distanzaMassima}
    @Override
    @RequestMapping(value = "/rest/network/closeBusStops/{lat}/{lon}/{maxDistance}", method = RequestMethod.GET)
    public @ResponseBody CloseStop[] closeBusStops(@PathVariable("lat") String lat,
            @PathVariable("lon") String lon, @PathVariable("maxDistance") int maxDistance) {
        String closeBusStopsUrl = baseUrl + "/rete/FermateVicine/" + lat + "/" + lon + "/"
                + maxDistance;
        RestTemplate restTemplate = new RestTemplate();
        CloseStop[] pois = restTemplate.getForObject(closeBusStopsUrl, CloseStop[].class);

        return pois;
    }

    // rete/Linee
    @Override
    @RequestMapping(value = "/rest/network/routes", method = RequestMethod.GET)
    public @ResponseBody Line[] routes() {
        String routesUrl = baseUrl + "/rete/Linee";
        RestTemplate restTemplate = new RestTemplate();
        Line[] lines = restTemplate.getForObject(routesUrl, Line[].class);

        return lines;
    }

    // ServizioGiornaliero/{idLinea}
    @Override
    @RequestMapping(value = "/rest/dailyService/{idLine}", method = RequestMethod.GET)
    public @ResponseBody DailyBusService[] dailyService(@PathVariable("idLine") String idLine) {
        String dailyServiceUrl = baseUrl + "/ServizioGiornaliero/"
                + ConverterUtils.convertLine(idLine);
        RestTemplate restTemplate = new RestTemplate();
        DailyBusService[] dailyServiceObjs = restTemplate.getForObject(dailyServiceUrl,
                DailyBusService[].class);

        return dailyServiceObjs;
    }

    // ServizioGiornaliero/{idLinea}/{*idFermata}
    @Override
    @RequestMapping(value = "/rest/dailyService/{idLine}/{idStop}", method = RequestMethod.GET)
    public @ResponseBody DailyBusService[] dailyService(@PathVariable("idLine") String idLine,
            @PathVariable("idStop") String idStop) {
        String dailyServiceUrl = baseUrl + "/ServizioGiornaliero/"
                + ConverterUtils.convertLine(idLine) + "/" + idStop;
        RestTemplate restTemplate = new RestTemplate();
        DailyBusService[] dailyServiceObjs = restTemplate.getForObject(dailyServiceUrl,
                DailyBusService[].class);

        return dailyServiceObjs;
    }

}
