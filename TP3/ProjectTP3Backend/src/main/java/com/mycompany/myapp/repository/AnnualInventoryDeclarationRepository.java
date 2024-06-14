package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.AnnualInventoryDeclaration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AnnualInventoryDeclaration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnualInventoryDeclarationRepository
    extends JpaRepository<AnnualInventoryDeclaration, Long>, JpaSpecificationExecutor<AnnualInventoryDeclaration> {}
