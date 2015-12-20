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

import com.project.app.model.dto.LineDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.LineJPA;
import com.project.app.services.repositories.LineRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class LineService implements GenericService<LineJPA> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LineService.class);

    private final LineRepository lineRepository;

    private Validator validator;

    @Autowired
    LineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    @Override
    @Transactional(readOnly = true)
    public List<LineJPA> findAll() {
        List<LineJPA> lineEntries = lineRepository.findAll();
        return lineEntries;
    }

    @Override
    @Transactional(readOnly = true)
    public LineJPA findById(Long id) {
        LineJPA lineEntry = findLineEntryById(id);
        return lineEntry;
    }

    @Override
    @Transactional
    public LineJPA update(LineJPA updatedLineEntry) {
        LineJPA updated = findLineEntryById(updatedLineEntry.getId());
        updated.update(updatedLineEntry.getIdLine(), updatedLineEntry.getLineDescription());
        lineRepository.flush();
        return updated;
    }

    private LineJPA findLineEntryById(Long id) {
        LineJPA lineResult = lineRepository.findOne(id);
        return lineResult;
    }
}
