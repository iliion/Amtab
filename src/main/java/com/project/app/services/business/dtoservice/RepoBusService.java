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

import com.project.app.model.dto.BusDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.BusJPA;
import com.project.app.services.repositories.BusRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class RepoBusService implements GenericRepoService<BusDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoBusService.class);

    private final BusRepository busRepository;

    private Validator validator;

    @Autowired
    RepoBusService(BusRepository busRepository) {
        this.busRepository = busRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    @Transactional
    public BusDTO create(BusDTO newEntry) {
        LOGGER.info("Creating a new Bus entry by using information: {}", newEntry);

        BusJPA created = new BusJPA();

        created = busRepository.save(created);
        LOGGER.info("Created a new Stop entry: {}", created);

        return EntitiesConverter.mapBusEntityIntoDTO(created);
    }

    @Override
    @Transactional
    public BusDTO delete(Long id) {
        LOGGER.info("Deleting a Stop entry with id: {}", id);

        BusJPA deleted = findStopEntryById(id);
        LOGGER.debug("Found Stop entry: {}", deleted);

        busRepository.delete(deleted);
        LOGGER.info("Deleted Stop entry: {}", deleted);

        return EntitiesConverter.mapBusEntityIntoDTO(deleted);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BusDTO> findAll() {
        LOGGER.info("Finding all Stop entries.");

        List<BusJPA> busEntries = busRepository.findAll();

        LOGGER.info("Found {} Stop entries", busEntries.size());

        return EntitiesConverter.mapBusEntitiesIntoDTOs(busEntries);
    }

    @Override
    @Transactional(readOnly = true)
    public BusDTO findById(Long id) {
        LOGGER.info("Finding Stop entry by using id: {}", id);

        BusJPA todoEntry = findStopEntryById(id);
        LOGGER.info("Found Stop entry: {}", todoEntry);

        return EntitiesConverter.mapBusEntityIntoDTO(todoEntry);
    }

    @Override
    @Transactional
    public BusDTO update(BusDTO updatedStopEntry) {
        LOGGER.info("Updating the information of a Stop entry by using information: {}",
                updatedStopEntry);

        BusJPA updated = findStopEntryById(updatedStopEntry.getId());
        updated.update(updatedStopEntry.getIdBus());

        // We need to flush the changes or otherwise the returned object
        // doesn't contain the updated audit information.
        busRepository.flush();

        LOGGER.info("Updated the information of the Stop entry: {}", updated);

        return EntitiesConverter.mapBusEntityIntoDTO(updated);
    }

    private BusJPA findStopEntryById(Long id) {
        // Optional<BusJPA> busResult = busRepository.findOne(id);
        // return busResult.orElseThrow(() -> new TodoNotFoundException(id));
        BusJPA busResult = busRepository.findOne(id);
        return busResult;
    }
}
