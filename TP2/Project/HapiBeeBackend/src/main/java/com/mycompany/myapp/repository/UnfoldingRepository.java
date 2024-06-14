package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Unfolding;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Unfolding entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnfoldingRepository extends JpaRepository<Unfolding, Long>, JpaSpecificationExecutor<Unfolding> {}
