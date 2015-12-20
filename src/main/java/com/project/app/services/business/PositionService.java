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

import com.project.app.model.dto.PositionDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.PositionJPA;
import com.project.app.services.repositories.PositionRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class PositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionService.class);

    private final PositionRepository positionRepository;

    private Validator validator;

    @Autowired
    PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


//    @Override
    @Transactional(readOnly = true)
    public List<PositionJPA> findAll() {
        List<PositionJPA> positionEntries = positionRepository.findAll();
        return positionEntries;
    }

//    @Override
    @Transactional(readOnly = true)
    public PositionJPA findById(Long id) {
        PositionJPA positionEntry = findPositionEntryById(id);
        return positionEntry;
    }

//    @Override
    @Transactional
    public PositionJPA update(PositionJPA updatedPositionEntry) {
        PositionJPA updated = findPositionEntryById(updatedPositionEntry.getId());
        updated.update(updatedPositionEntry.getLat(), updatedPositionEntry.getLon());
        positionRepository.flush();
        return updated;
    }

    private PositionJPA findPositionEntryById(Long id) {
        PositionJPA positionResult = positionRepository.findOne(id);
        return positionResult;
    }
}
