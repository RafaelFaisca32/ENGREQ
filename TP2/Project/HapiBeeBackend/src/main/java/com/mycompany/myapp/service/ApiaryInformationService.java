package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.ApiaryInformationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.ApiaryInformation}.
 */
public interface ApiaryInformationService {
    /**
     * Save a apiaryInformation.
     *
     * @param apiaryInformationDTO the entity to save.
     * @return the persisted entity.
     */
    ApiaryInformationDTO save(ApiaryInformationDTO apiaryInformationDTO);

    /**
     * Updates a apiaryInformation.
     *
     * @param apiaryInformationDTO the entity to update.
     * @return the persisted entity.
     */
    ApiaryInformationDTO update(ApiaryInformationDTO apiaryInformationDTO);

    /**
     * Partially updates a apiaryInformation.
     *
     * @param apiaryInformationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ApiaryInformationDTO> partialUpdate(ApiaryInformationDTO apiaryInformationDTO);

    /**
     * Get all the apiaryInformations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ApiaryInformationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" apiaryInformation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ApiaryInformationDTO> findOne(Long id);

    /**
     * Delete the "id" apiaryInformation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
