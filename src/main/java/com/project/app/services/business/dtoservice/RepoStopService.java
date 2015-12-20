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

import com.project.app.model.dto.StopDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.StopJPA;
import com.project.app.services.repositories.StopRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class RepoStopService implements GenericRepoService<StopDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoStopService.class);

    private final StopRepository stopRepository;

    private Validator validator;

    @Autowired
    RepoStopService(StopRepository stopRepository) {
        this.stopRepository = stopRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    @Transactional
    public StopDTO create(StopDTO newEntry) {
        LOGGER.info("Creating a new Stop entry by using information: {}", newEntry);

        StopJPA created = new StopJPA();

        created = stopRepository.save(created);
        LOGGER.info("Created a new Stop entry: {}", created);

        return EntitiesConverter.mapStopEntityIntoDTO(created);
    }

    @Override
    @Transactional
    public StopDTO delete(Long id) {
        LOGGER.info("Deleting a Stop entry with id: {}", id);

        StopJPA deleted = findStopEntryById(id);
        LOGGER.debug("Found Stop entry: {}", deleted);

        stopRepository.delete(deleted);
        LOGGER.info("Deleted Stop entry: {}", deleted);

        return EntitiesConverter.mapStopEntityIntoDTO(deleted);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StopDTO> findAll() {
        LOGGER.info("Finding all Stop entries.");

        List<StopJPA> stopEntries = stopRepository.findAll();

        LOGGER.info("Found {} Stop entries", stopEntries.size());

        return EntitiesConverter.mapStopEntitiesIntoDTOs(stopEntries);
    }

    @Override
    @Transactional(readOnly = true)
    public StopDTO findById(Long id) {
        LOGGER.info("Finding Stop entry by using id: {}", id);

        StopJPA stopEntry = findStopEntryById(id);
        LOGGER.info("Found Stop entry: {}", stopEntry);

        return EntitiesConverter.mapStopEntityIntoDTO(stopEntry);
    }

    @Override
    @Transactional
    public StopDTO update(StopDTO updatedStopEntry) {
        LOGGER.info("Updating the information of a Stop entry by using information: {}",
                updatedStopEntry);

        StopJPA updated = findStopEntryById(updatedStopEntry.getId());
        updated.update(updatedStopEntry.getStopDescription(), updatedStopEntry.getIdStop());

        // We need to flush the changes or otherwise the returned object
        // doesn't contain the updated audit information.
        stopRepository.flush();

        LOGGER.info("Updated the information of the Stop entry: {}", updated);

        return EntitiesConverter.mapStopEntityIntoDTO(updated);
    }

    private StopJPA findStopEntryById(Long id) {
        // Optional<StopJPA> stopResult = stopRepository.findOne(id);
        // return stopResult.orElseThrow(() -> new TodoNotFoundException(id));
        StopJPA stopResult = stopRepository.findOne(id);
        return stopResult;
    }
}
