package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Crest;
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
public class CrestRepositoryWithBagRelationshipsImpl implements CrestRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Crest> fetchBagRelationships(Optional<Crest> crest) {
        return crest.map(this::fetchFrames);
    }

    @Override
    public Page<Crest> fetchBagRelationships(Page<Crest> crests) {
        return new PageImpl<>(fetchBagRelationships(crests.getContent()), crests.getPageable(), crests.getTotalElements());
    }

    @Override
    public List<Crest> fetchBagRelationships(List<Crest> crests) {
        return Optional.of(crests).map(this::fetchFrames).orElse(Collections.emptyList());
    }

    Crest fetchFrames(Crest result) {
        return entityManager
            .createQuery("select crest from Crest crest left join fetch crest.frames where crest is :crest", Crest.class)
            .setParameter("crest", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Crest> fetchFrames(List<Crest> crests) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, crests.size()).forEach(index -> order.put(crests.get(index).getId(), index));
        List<Crest> result = entityManager
            .createQuery("select distinct crest from Crest crest left join fetch crest.frames where crest in :crests", Crest.class)
            .setParameter("crests", crests)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
