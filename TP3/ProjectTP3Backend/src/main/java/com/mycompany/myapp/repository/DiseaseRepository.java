package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Disease;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Disease entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long>, JpaSpecificationExecutor<Disease> {}
