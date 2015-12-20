package com.project.app.services.business;

import java.util.List;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.app.services.entities.TimeTableJPA;
import com.project.app.services.repositories.TimeTableRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class TimetableService {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TimetableService.class);

	private final TimeTableRepository timeTableRepository;

	private Validator validator;

	@Autowired
	TimetableService(TimeTableRepository timeTableRepository) {
		this.timeTableRepository = timeTableRepository;

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	@Transactional(readOnly = true)
	public List<TimeTableJPA> findByIdStop(String idStop) {
		List<TimeTableJPA> timetableStopEntry = timeTableRepository.findByIdBus(idStop);
		return timetableStopEntry;
	}
	
	@Transactional(readOnly = true)
	public List<TimeTableJPA> findByIdBus(String idBus) {
		List<TimeTableJPA> timetableBusEntry = timeTableRepository.findByIdBus(idBus);
		return timetableBusEntry;
	}
	
	@Transactional(readOnly = true)
	public List<TimeTableJPA> findByIdLine(String idLine) {
		List<TimeTableJPA> timetableLineEntry = timeTableRepository.findByIdLine(idLine);
		return timetableLineEntry;
	}
	
//	@Override
	@Transactional(readOnly = true)
	public List<TimeTableJPA> findAll() {
		List<TimeTableJPA> timeTableEntries = timeTableRepository.findAll();
		return timeTableEntries;
	}

//	@Override
	@Transactional(readOnly = true)
	public TimeTableJPA findById(Long id) {
		TimeTableJPA timeTableEntry = findTimetableEntryById(id);
		return timeTableEntry;
	}

//	@Override
	@Transactional
	public TimeTableJPA update(TimeTableJPA updatedTimetableEntry) {
		TimeTableJPA updated = findTimetableEntryById(updatedTimetableEntry
				.getId());
		updated.update(updatedTimetableEntry.getDirection(),
				updatedTimetableEntry.getOrario(),
				updatedTimetableEntry.getProgression());
		timeTableRepository.flush();
		return updated;
	}

	private TimeTableJPA findTimetableEntryById(Long id) {
		TimeTableJPA timetableResult = timeTableRepository.findOne(id);
		return timetableResult;
	}

}
