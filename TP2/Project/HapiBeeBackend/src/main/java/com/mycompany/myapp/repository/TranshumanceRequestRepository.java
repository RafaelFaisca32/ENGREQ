package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TranshumanceRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TranshumanceRequest entity.
 *
 * When extending this class, extend TranshumanceRequestRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface TranshumanceRequestRepository
    extends
        TranshumanceRequestRepositoryWithBagRelationships,
        JpaRepository<TranshumanceRequest, Long>,
        JpaSpecificationExecutor<TranshumanceRequest> {
    default Optional<TranshumanceRequest> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<TranshumanceRequest> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<TranshumanceRequest> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
