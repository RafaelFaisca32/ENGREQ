package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TranshumanceRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class TranshumanceRequestRepositoryWithBagRelationshipsImpl implements TranshumanceRequestRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TranshumanceRequest> fetchBagRelationships(Optional<TranshumanceRequest> transhumanceRequest) {
        return transhumanceRequest.map(this::fetchHives);
    }

    @Override
    public Page<TranshumanceRequest> fetchBagRelationships(Page<TranshumanceRequest> transhumanceRequests) {
        return new PageImpl<>(
            fetchBagRelationships(transhumanceRequests.getContent()),
            transhumanceRequests.getPageable(),
            transhumanceRequests.getTotalElements()
        );
    }

    @Override
    public List<TranshumanceRequest> fetchBagRelationships(List<TranshumanceRequest> transhumanceRequests) {
        return Optional.of(transhumanceRequests).map(this::fetchHives).orElse(Collections.emptyList());
    }

    TranshumanceRequest fetchHives(TranshumanceRequest result) {
        return entityManager
            .createQuery(
                "select transhumanceRequest from TranshumanceRequest transhumanceRequest left join fetch transhumanceRequest.hives where transhumanceRequest is :transhumanceRequest",
                TranshumanceRequest.class
            )
            .setParameter("transhumanceRequest", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<TranshumanceRequest> fetchHives(List<TranshumanceRequest> transhumanceRequests) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, transhumanceRequests.size()).forEach(index -> order.put(transhumanceRequests.get(index).getId(), index));
        List<TranshumanceRequest> result = entityManager
            .createQuery(
                "select distinct transhumanceRequest from TranshumanceRequest transhumanceRequest left join fetch transhumanceRequest.hives where transhumanceRequest in :transhumanceRequests",
                TranshumanceRequest.class
            )
            .setParameter("transhumanceRequests", transhumanceRequests)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
