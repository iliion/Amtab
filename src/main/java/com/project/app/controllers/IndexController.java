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

import com.project.app.model.Status;
import com.project.app.services.business.LineService;
import com.project.app.services.business.PositionService;
import com.project.app.services.business.StopService;
import com.project.app.services.business.TimetableService;
import com.project.app.services.entities.LineJPA;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.repositories.BusRepository;

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
	BusRepository busRepo;

	final private String baseUrl = "http://bari.opendata.planetek.it/OrariBus/v2.1/OpenDataService.svc/REST";

	@RequestMapping(value = "/line/{idLine}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView currentStatus(
			@PathVariable("idLine") String idLine) {
		String lineUrl = baseUrl + "/MezziLinea/" + idLine;
		RestTemplate restTemplate = new RestTemplate();
		Status[] status = restTemplate.getForObject(lineUrl, Status[].class);

		ModelAndView model = new ModelAndView();
		model.setViewName("bootstrap");
		model.addObject("array", status);

		return model;
	}

	@RequestMapping(value = "/bootstrap", method = RequestMethod.GET)
	public ModelAndView bootstrap() {

		List<LineJPA> list = lineService.findAll();
		List<StopJPA> stops = stopService.findAll();

		ModelAndView model = new ModelAndView();
		model.setViewName("bootstrap");
		model.addObject("list", list);
		model.addObject("stops", stops);

		return model;
	}
}
