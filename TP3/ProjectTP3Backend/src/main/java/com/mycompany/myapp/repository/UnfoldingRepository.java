package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Unfolding;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Unfolding entity.
 *
 * When extending this class, extend UnfoldingRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface UnfoldingRepository
    extends UnfoldingRepositoryWithBagRelationships, JpaRepository<Unfolding, Long>, JpaSpecificationExecutor<Unfolding> {
    default Optional<Unfolding> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Unfolding> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Unfolding> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
