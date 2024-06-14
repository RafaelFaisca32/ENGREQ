package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Beekeeper;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Beekeeper entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BeekeeperRepository extends JpaRepository<Beekeeper, Long>, JpaSpecificationExecutor<Beekeeper> {}
