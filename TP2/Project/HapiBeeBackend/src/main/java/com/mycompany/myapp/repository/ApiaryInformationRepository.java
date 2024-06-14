package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ApiaryInformation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ApiaryInformation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiaryInformationRepository extends JpaRepository<ApiaryInformation, Long>, JpaSpecificationExecutor<ApiaryInformation> {}
