package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Crest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Crest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CrestRepository extends JpaRepository<Crest, Long>, JpaSpecificationExecutor<Crest> {

    @Query("SELECT c FROM Crest c " +
        "JOIN c.hive h " +
        "JOIN h.apiary a " +
        "JOIN a.beekeeper b " +
        "JOIN b.user u " +
        "WHERE u.id = :userId" )
    Page<Crest> findAllByUserId(Pageable page, @Param("userId") Long userId);
}
