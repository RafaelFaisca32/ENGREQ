package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Hive;
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
public class HiveRepositoryWithBagRelationshipsImpl implements HiveRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Hive> fetchBagRelationships(Optional<Hive> hive) {
        return hive.map(this::fetchFrames);
    }

    @Override
    public Page<Hive> fetchBagRelationships(Page<Hive> hives) {
        return new PageImpl<>(fetchBagRelationships(hives.getContent()), hives.getPageable(), hives.getTotalElements());
    }

    @Override
    public List<Hive> fetchBagRelationships(List<Hive> hives) {
        return Optional.of(hives).map(this::fetchFrames).orElse(Collections.emptyList());
    }

    Hive fetchFrames(Hive result) {
        return entityManager
            .createQuery("select hive from Hive hive left join fetch hive.frames where hive is :hive", Hive.class)
            .setParameter("hive", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Hive> fetchFrames(List<Hive> hives) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, hives.size()).forEach(index -> order.put(hives.get(index).getId(), index));
        List<Hive> result = entityManager
            .createQuery("select distinct hive from Hive hive left join fetch hive.frames where hive in :hives", Hive.class)
            .setParameter("hives", hives)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
