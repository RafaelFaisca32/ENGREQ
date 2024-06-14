package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.AnnualInventoryDeclarationDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.io.IOException;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.AnnualInventoryDeclaration}.
 */
public interface AnnualInventoryDeclarationService {
    /**
     * Save a annualInventoryDeclaration.
     *
     * @param annualInventoryDeclarationDTO the entity to save.
     * @return the persisted entity.
     */
    AnnualInventoryDeclarationDTO save(AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO);

    /**
     * Updates a annualInventoryDeclaration.
     *
     * @param annualInventoryDeclarationDTO the entity to update.
     * @return the persisted entity.
     */
    AnnualInventoryDeclarationDTO update(AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO);

    /**
     * Partially updates a annualInventoryDeclaration.
     *
     * @param annualInventoryDeclarationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AnnualInventoryDeclarationDTO> partialUpdate(AnnualInventoryDeclarationDTO annualInventoryDeclarationDTO);

    /**
     * Get all the annualInventoryDeclarations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnnualInventoryDeclarationDTO> findAll(Pageable pageable);

    /**
     * Get the "id" annualInventoryDeclaration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnnualInventoryDeclarationDTO> findOne(Long id);

    /**
     * Delete the "id" annualInventoryDeclaration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    void sendAuthorizationRequestToPortal(long id) throws IOException, InterruptedException;

    void approveOrRejectAnnualInventoryDeclarationApproval(String result);
}
