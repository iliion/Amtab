package com.project.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.project.app.model.StopPosition;
import com.project.app.model.objects.Line;
import com.project.app.services.PopulateDbService;
import com.project.app.services.entities.LineJPA;
import com.project.app.services.entities.PositionJPA;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.repositories.BusRepository;
import com.project.app.services.repositories.LineRepository;
import com.project.app.services.repositories.PositionRepository;
import com.project.app.services.repositories.StopRepository;
import com.project.app.services.repositories.TimeTableRepository;

// @Component
public class ApplicationListenerBean implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListenerBean.class);

    @Autowired
    PopulateDbService service;

    @Autowired
    StopRepository stopRepo;

    @Autowired
    LineRepository lineRepo;

    @Autowired
    PositionRepository positionRepo;

    @Autowired
    TimeTableRepository timeTableRepo;

    @Autowired
    BusRepository busRepo;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();

        populateDatabase(applicationContext);
    }

    private void populateDatabase(ApplicationContext applicationContext) {

        // StopRepository stopRepo = applicationContext.getBean(StopRepository.class);
        StopPosition[] stopPositionArray = service.getPositionOfBusStops();
        LOGGER.info("Number of Bus Stops: " + stopPositionArray.length);
        for (int i = 0; i < stopPositionArray.length; i++) {
            StopJPA stopJPA = new StopJPA();
            PositionJPA positionJPA = new PositionJPA();

            LOGGER.info("IdFermata: " + stopPositionArray[i].getIdFermata());
            LOGGER.info("DescrizioneFermata: " + stopPositionArray[i].getDescrizioneFermata());
            LOGGER.info("LAT: " + stopPositionArray[i].getPosizioneFermata().getLat());
            LOGGER.info("LON: " + stopPositionArray[i].getPosizioneFermata().getLon());

            stopJPA.setIdStop(stopPositionArray[i].getIdFermata());
            stopJPA.setStopDescription(stopPositionArray[i].getDescrizioneFermata());
            positionJPA.setLat(stopPositionArray[i].getPosizioneFermata().getLat());
            positionJPA.setLon(stopPositionArray[i].getPosizioneFermata().getLon());
            stopJPA.setStopPosition(positionJPA);

            stopRepo.save(stopJPA);
        }
        stopRepo.flush();

        // LineRepository lineRepo = applicationContext.getBean(LineRepository.class);
        Line[] lines = service.getLines();
        LOGGER.info("Number of Lines: " + lines.length);
        for (int i = 0; i < lines.length; i++) {
            LineJPA lineJPA = new LineJPA();

            LOGGER.info("IdLinea: " + lines[i].getIdLinea());
            LOGGER.info("DescrizioneLinea: " + lines[i].getDescrizioneLinea());

            lineJPA.setIdLine(lines[i].getIdLinea());
            lineJPA.setLineDescription(lines[i].getDescrizioneLinea());

            lineRepo.save(lineJPA);
        }
        lineRepo.flush();

    }

    // @EventListener
    // public void handleContextRefresh(ContextRefreshedEvent event) {
    // ApplicationContext applicationContext = event.getApplicationContext();
    // }
}
