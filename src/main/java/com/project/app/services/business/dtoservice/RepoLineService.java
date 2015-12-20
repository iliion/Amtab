package com.project.app.services.business.dtoservice;

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
public class RepoLineService implements GenericRepoService<LineDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoLineService.class);

    private final LineRepository lineRepository;

    private Validator validator;

    @Autowired
    RepoLineService(LineRepository lineRepository) {
        this.lineRepository = lineRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    @Transactional
    public LineDTO create(LineDTO newEntry) {
        LOGGER.info("Creating a new Stop entry by using information: {}", newEntry);

        LineJPA created = new LineJPA();

        created = lineRepository.save(created);
        LOGGER.info("Created a new Stop entry: {}", created);

        return EntitiesConverter.mapLineEntityIntoDTO(created);
    }

    @Override
    @Transactional
    public LineDTO delete(Long id) {
        LOGGER.info("Deleting a Stop entry with id: {}", id);

        LineJPA deleted = findStopEntryById(id);
        LOGGER.debug("Found Stop entry: {}", deleted);

        lineRepository.delete(deleted);
        LOGGER.info("Deleted Stop entry: {}", deleted);

        return EntitiesConverter.mapLineEntityIntoDTO(deleted);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LineDTO> findAll() {
        LOGGER.info("Finding all Stop entries.");

        List<LineJPA> lineEntries = lineRepository.findAll();

        LOGGER.info("Found {} Line entries", lineEntries.size());

        return EntitiesConverter.mapLineEntitiesIntoDTOs(lineEntries);
    }

    @Override
    @Transactional(readOnly = true)
    public LineDTO findById(Long id) {
        LOGGER.info("Finding Stop entry by using id: {}", id);

        LineJPA lineEntry = findStopEntryById(id);
        LOGGER.info("Found Stop entry: {}", lineEntry);

        return EntitiesConverter.mapLineEntityIntoDTO(lineEntry);
    }

    @Override
    @Transactional
    public LineDTO update(LineDTO updatedLineEntry) {
        LOGGER.info("Updating the information of a Stop entry by using information: {}",
                updatedLineEntry);

        LineJPA updated = findStopEntryById(updatedLineEntry.getId());
        updated.update(updatedLineEntry.getIdLine(), updatedLineEntry.getLineDescription());

        // We need to flush the changes or otherwise the returned object
        // doesn't contain the updated audit information.
        lineRepository.flush();

        LOGGER.info("Updated the information of the Stop entry: {}", updated);

        return EntitiesConverter.mapLineEntityIntoDTO(updated);
    }

    private LineJPA findStopEntryById(Long id) {
        // Optional<LineJPA> stopResult = lineRepository.findOne(id);
        // return stopResult.orElseThrow(() -> new TodoNotFoundException(id));
        LineJPA lineResult = lineRepository.findOne(id);
        return lineResult;
    }
}
