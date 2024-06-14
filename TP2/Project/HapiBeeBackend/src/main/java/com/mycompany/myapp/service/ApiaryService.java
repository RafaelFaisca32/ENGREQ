package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ApiaryDTO;

import java.io.IOException;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.Apiary}.
 */
public interface ApiaryService {
    /**
     * Save a apiary.
     *
     * @param apiaryDTO the entity to save.
     * @return the persisted entity.
     */
    ApiaryDTO save(ApiaryDTO apiaryDTO);

    /**
     * Updates a apiary.
     *
     * @param apiaryDTO the entity to update.
     * @return the persisted entity.
     */
    ApiaryDTO update(ApiaryDTO apiaryDTO);

    /**
     * Partially updates a apiary.
     *
     * @param apiaryDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiaryDTO> partialUpdate(ApiaryDTO apiaryDTO);

    /**
     * Get all the apiaries.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiaryDTO> findAll(Pageable pageable);

    /**
     * Get the "id" apiary.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiaryDTO> findOne(Long id);

    /**
     * Delete the "id" apiary.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void sendAuthorizationRequestToPortal(long id) throws IOException, InterruptedException;

    void approveOrRejectApiary(String result);
}
