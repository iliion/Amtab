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

import com.project.app.model.dto.StopDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.repositories.StopRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class StopService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StopService.class);

    private final StopRepository stopRepository;

    private Validator validator;

    @Autowired
    StopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


//    @Override
    @Transactional(readOnly = true)
    public List<StopJPA> findAll() {
        List<StopJPA> stopEntries = stopRepository.findAll();
        return stopEntries;
    }

//    @Override
    @Transactional(readOnly = true)
    public StopJPA findById(Long id) {
        StopJPA stopEntry = findStopEntryById(id);
        return stopEntry;
    }

//    @Override
    @Transactional
    public StopJPA update(StopJPA updatedStopEntry) {
        StopJPA updated = findStopEntryById(updatedStopEntry.getId());
        updated.update(updatedStopEntry.getStopDescription(), updatedStopEntry.getIdStop());
        stopRepository.flush();
        return updated;
    }

    private StopJPA findStopEntryById(Long id) {
        StopJPA stopResult = stopRepository.findOne(id);
        return stopResult;
    }
}
