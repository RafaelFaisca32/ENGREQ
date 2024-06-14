package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.UnfoldingDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Unfolding}.
 */
public interface UnfoldingService {
    /**
     * Save a unfolding.
     *
     * @param unfoldingDTO the entity to save.
     * @return the persisted entity.
     */
    UnfoldingDTO save(UnfoldingDTO unfoldingDTO);

    /**
     * Updates a unfolding.
     *
     * @param unfoldingDTO the entity to update.
     * @return the persisted entity.
     */
    UnfoldingDTO update(UnfoldingDTO unfoldingDTO);

    /**
     * Partially updates a unfolding.
     *
     * @param unfoldingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UnfoldingDTO> partialUpdate(UnfoldingDTO unfoldingDTO);

    /**
     * Get all the unfoldings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UnfoldingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" unfolding.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UnfoldingDTO> findOne(Long id);

    /**
     * Delete the "id" unfolding.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
