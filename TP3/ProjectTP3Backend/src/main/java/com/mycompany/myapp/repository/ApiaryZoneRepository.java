package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.ApiaryZone;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ApiaryZone entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiaryZoneRepository extends JpaRepository<ApiaryZone, Long>, JpaSpecificationExecutor<ApiaryZone> {}
