package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Hive;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Hive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HiveRepository extends JpaRepository<Hive, Long>, JpaSpecificationExecutor<Hive> {}
