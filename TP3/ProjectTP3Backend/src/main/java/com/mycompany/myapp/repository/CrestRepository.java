package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Crest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Crest entity.
 *
 * When extending this class, extend CrestRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface CrestRepository extends CrestRepositoryWithBagRelationships, JpaRepository<Crest, Long>, JpaSpecificationExecutor<Crest> {
    default Optional<Crest> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Crest> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Crest> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT c FROM Crest c " +
        "JOIN c.hive h " +
        "JOIN h.apiary a " +
        "JOIN a.beekeeper b " +
        "JOIN b.user u " +
        "WHERE u.id = :userId" )
    Page<Crest> findAllByUserId(Pageable page, @Param("userId") Long userId);
}
