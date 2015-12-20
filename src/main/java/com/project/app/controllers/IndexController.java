package com.project.app.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.project.app.services.business.BusService;
import com.project.app.services.business.LineService;
import com.project.app.services.business.PositionService;
import com.project.app.services.business.StopService;
import com.project.app.services.business.TimetableService;
import com.project.app.services.entities.LineJPA;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.entities.TimeTableJPA;

@Controller
class IndexController {

	private static final Logger logger = LoggerFactory
			.getLogger(IndexController.class);

	@Autowired
	StopService stopService;

	@Autowired
	LineService lineService;

	@Autowired
	PositionService positionService;

	@Autowired
	TimetableService timeTableService;

	@Autowired
	BusService busService;

	final private String baseUrl = "http://bari.opendata.planetek.it/OrariBus/v2.1/OpenDataService.svc/REST";

	@RequestMapping(value = "/bootstrap", method = RequestMethod.GET)
	public ModelAndView bootstrap() {

		List<LineJPA> list = lineService.findAll();
		List<StopJPA> stops = stopService.findAll();
		List<TimeTableJPA> tts = timeTableService.findByIdLine("02");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("bootstrap");
		model.addObject("list", list);
		model.addObject("stops", stops);
		model.addObject("tts", tts);

		return model;
	}
}
