package com.project.app.controllers;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.app.services.entities.BusJPA;
import com.project.app.services.entities.LineJPA;
import com.project.app.services.entities.PositionJPA;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.repositories.BusRepository;
import com.project.app.services.repositories.LineRepository;
import com.project.app.services.repositories.PositionRepository;
import com.project.app.services.repositories.StopRepository;
import com.project.app.services.repositories.TimeTableRepository;

@Controller
class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

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

    @RequestMapping(value = "/bootstrap", method = RequestMethod.GET)
    public ModelAndView index() {

        List<LineJPA> list = lineRepo.findAll();
        Collection<BusJPA> buses = list.get(0).getBusJPA();
        for (BusJPA bus : buses) {
            bus.toString();
        }
        List<StopJPA> stops = stopRepo.findAll();
        for (StopJPA stop : stops) {
            logger.info(stop.toString());
            PositionJPA stopPosition = stop.getStopPosition();
        }

        ModelAndView model = new ModelAndView();
        model.setViewName("bootstrap");
        model.addObject("list", list);
        model.addObject("stops", stops);

        return model;
    }
}
