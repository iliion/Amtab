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

import com.project.app.model.dto.PositionDTO;
import com.project.app.services.converters.EntitiesConverter;
import com.project.app.services.entities.PositionJPA;
import com.project.app.services.repositories.PositionRepository;

/**
 * @author Ilias Ioannou
 */
@Service
public class RepoPositionService implements GenericRepoService<PositionDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepoPositionService.class);

    private final PositionRepository positionRepository;

    private Validator validator;

    @Autowired
    RepoPositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    @Override
    @Transactional
    public PositionDTO create(PositionDTO newEntry) {
        LOGGER.info("Creating a new Position entry by using information: {}", newEntry);

        PositionJPA created = new PositionJPA();

        created = positionRepository.save(created);
        LOGGER.info("Created a new Position entry: {}", created);

        return EntitiesConverter.mapPositionEntityIntoDTO(created);
    }

    @Override
    @Transactional
    public PositionDTO delete(Long id) {
        LOGGER.info("Deleting a Position entry with id: {}", id);

        PositionJPA deleted = findPositionEntryById(id);
        LOGGER.debug("Found Position entry: {}", deleted);

        positionRepository.delete(deleted);
        LOGGER.info("Deleted Position entry: {}", deleted);

        return EntitiesConverter.mapPositionEntityIntoDTO(deleted);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PositionDTO> findAll() {
        LOGGER.info("Finding all Position entries.");

        List<PositionJPA> positionEntries = positionRepository.findAll();

        LOGGER.info("Found {} Position entries", positionEntries.size());

        return EntitiesConverter.mapPositionEntitiesIntoDTOs(positionEntries);
    }

    @Override
    @Transactional(readOnly = true)
    public PositionDTO findById(Long id) {
        LOGGER.info("Finding Position entry by using id: {}", id);

        PositionJPA positionEntry = findPositionEntryById(id);
        LOGGER.info("Found Position entry: {}", positionEntry);

        return EntitiesConverter.mapPositionEntityIntoDTO(positionEntry);
    }

    @Override
    @Transactional
    public PositionDTO update(PositionDTO updatedPositionEntry) {
        LOGGER.info("Updating the information of a Position entry by using information: {}",
                updatedPositionEntry);

        PositionJPA updated = findPositionEntryById(updatedPositionEntry.getIdPosition());
        updated.update(updatedPositionEntry.getLatitudine(), updatedPositionEntry.getLongitudine());

        // We need to flush the changes or otherwise the returned object
        // doesn't contain the updated audit information.
        positionRepository.flush();

        LOGGER.info("Updated the information of the Position entry: {}", updated);

        return EntitiesConverter.mapPositionEntityIntoDTO(updated);
    }

    private PositionJPA findPositionEntryById(Long id) {
        // Optional<PositionJPA> positionResult = positionRepository.findOne(id);
        // return positionResult.orElseThrow(() -> new TodoNotFoundException(id));
        PositionJPA positionResult = positionRepository.findOne(id);
        return positionResult;
    }
}
