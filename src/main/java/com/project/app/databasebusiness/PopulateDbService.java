package com.project.app.databasebusiness;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.project.app.model.DailyBusService;
import com.project.app.model.StopPosition;
import com.project.app.model.objects.Line;
import com.project.app.services.converters.ConverterUtils;

@org.springframework.stereotype.Service
public class PopulateDbService {

    final static Logger logger = LoggerFactory.getLogger(PopulateDbService.class);

    final private String baseUrl = "http://bari.opendata.planetek.it/OrariBus/v2.1/OpenDataService.svc/REST";

    public PopulateDbService() {
    }

    public StopPosition[] getPositionOfBusStops() {
        String stopUrl = baseUrl + "/rete/Fermate/";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(stopUrl, StopPosition[].class);
    }

    public Line[] getLines() {
        String lineUrl = baseUrl + "/rete/Linee";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(lineUrl, Line[].class);
    }

    public DailyBusService[] getDailyService(String idLine) {
        String dailyServiceUrl = baseUrl + "/ServizioGiornaliero/"
                + ConverterUtils.convertLine(idLine);
        RestTemplate restTemplate = new RestTemplate();
        DailyBusService[] dailyServiceObjs = restTemplate.getForObject(dailyServiceUrl,
                DailyBusService[].class);

        return dailyServiceObjs;
    }

}
