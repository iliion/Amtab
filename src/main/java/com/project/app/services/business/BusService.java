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

import com.project.app.model.dto.BusDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.BusJPA;
import com.project.app.services.repositories.BusRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class BusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusService.class);

    private final BusRepository busRepository;

    private Validator validator;

    @Autowired
    BusService(BusRepository busRepository) {
        this.busRepository = busRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


//    @Override
    @Transactional(readOnly = true)
    public List<BusJPA> findAll() {
        List<BusJPA> busEntries = busRepository.findAll();
        return busEntries;
    }

//    @Override
    @Transactional(readOnly = true)
    public BusJPA findById(Long id) {
        BusJPA busEntry = findBusEntryById(id);
        return busEntry;
    }

//    @Override
    @Transactional
    public BusJPA update(BusJPA updatedBusEntry) {
        BusJPA updated = findBusEntryById(updatedBusEntry.getId());
        updated.update(updatedBusEntry.getIdBus());
        busRepository.flush();
        return updated;
    }

    private BusJPA findBusEntryById(Long id) {
        BusJPA busResult = busRepository.findOne(id);
        return busResult;
    }
}
