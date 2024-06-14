package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TranshumanceRequestDTO;

import java.io.IOException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TranshumanceRequest}.
 */
public interface TranshumanceRequestService {
    /**
     * Save a transhumanceRequest.
     *
     * @param transhumanceRequestDTO the entity to save.
     * @return the persisted entity.
     */
    TranshumanceRequestDTO save(TranshumanceRequestDTO transhumanceRequestDTO);

    /**
     * Updates a transhumanceRequest.
     *
     * @param transhumanceRequestDTO the entity to update.
     * @return the persisted entity.
     */
    TranshumanceRequestDTO update(TranshumanceRequestDTO transhumanceRequestDTO);

    /**
     * Partially updates a transhumanceRequest.
     *
     * @param transhumanceRequestDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TranshumanceRequestDTO> partialUpdate(TranshumanceRequestDTO transhumanceRequestDTO);

    /**
     * Get all the transhumanceRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TranshumanceRequestDTO> findAll(Pageable pageable);

    /**
     * Get all the transhumanceRequests with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TranshumanceRequestDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" transhumanceRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TranshumanceRequestDTO> findOne(Long id);

    /**
     * Delete the "id" transhumanceRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void sendAuthorizationRequestToPortal(long id, TranshumanceRequestDTO transhumanceRequest) throws IOException, InterruptedException;

    void approveOrRejectTranshumance(String result);
}
