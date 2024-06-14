package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Apiary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Apiary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApiaryRepository extends JpaRepository<Apiary, Long>, JpaSpecificationExecutor<Apiary> {}
