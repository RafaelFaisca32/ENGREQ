package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.BeekeeperDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Beekeeper}.
 */
public interface BeekeeperService {
    /**
     * Save a beekeeper.
     *
     * @param beekeeperDTO the entity to save.
     * @return the persisted entity.
     */
    BeekeeperDTO save(BeekeeperDTO beekeeperDTO);

    /**
     * Updates a beekeeper.
     *
     * @param beekeeperDTO the entity to update.
     * @return the persisted entity.
     */
    BeekeeperDTO update(BeekeeperDTO beekeeperDTO);

    /**
     * Partially updates a beekeeper.
     *
     * @param beekeeperDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<BeekeeperDTO> partialUpdate(BeekeeperDTO beekeeperDTO);

    /**
     * Get all the beekeepers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<BeekeeperDTO> findAll(Pageable pageable);

    /**
     * Get the "id" beekeeper.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BeekeeperDTO> findOne(Long id);

    /**
     * Delete the "id" beekeeper.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
