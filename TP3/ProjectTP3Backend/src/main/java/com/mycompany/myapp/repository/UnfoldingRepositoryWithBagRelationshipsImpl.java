package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Unfolding;
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
public class UnfoldingRepositoryWithBagRelationshipsImpl implements UnfoldingRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Unfolding> fetchBagRelationships(Optional<Unfolding> unfolding) {
        return unfolding
            .map(this::fetchRemovedFramesOldHives)
            .map(this::fetchInsertedFramesOldHives)
            .map(this::fetchInsertedFramesNewHives);
    }

    @Override
    public Page<Unfolding> fetchBagRelationships(Page<Unfolding> unfoldings) {
        return new PageImpl<>(fetchBagRelationships(unfoldings.getContent()), unfoldings.getPageable(), unfoldings.getTotalElements());
    }

    @Override
    public List<Unfolding> fetchBagRelationships(List<Unfolding> unfoldings) {
        return Optional
            .of(unfoldings)
            .map(this::fetchRemovedFramesOldHives)
            .map(this::fetchInsertedFramesOldHives)
            .map(this::fetchInsertedFramesNewHives)
            .orElse(Collections.emptyList());
    }

    Unfolding fetchRemovedFramesOldHives(Unfolding result) {
        return entityManager
            .createQuery(
                "select unfolding from Unfolding unfolding left join fetch unfolding.removedFramesOldHives where unfolding is :unfolding",
                Unfolding.class
            )
            .setParameter("unfolding", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Unfolding> fetchRemovedFramesOldHives(List<Unfolding> unfoldings) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, unfoldings.size()).forEach(index -> order.put(unfoldings.get(index).getId(), index));
        List<Unfolding> result = entityManager
            .createQuery(
                "select distinct unfolding from Unfolding unfolding left join fetch unfolding.removedFramesOldHives where unfolding in :unfoldings",
                Unfolding.class
            )
            .setParameter("unfoldings", unfoldings)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Unfolding fetchInsertedFramesOldHives(Unfolding result) {
        return entityManager
            .createQuery(
                "select unfolding from Unfolding unfolding left join fetch unfolding.insertedFramesOldHives where unfolding is :unfolding",
                Unfolding.class
            )
            .setParameter("unfolding", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Unfolding> fetchInsertedFramesOldHives(List<Unfolding> unfoldings) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, unfoldings.size()).forEach(index -> order.put(unfoldings.get(index).getId(), index));
        List<Unfolding> result = entityManager
            .createQuery(
                "select distinct unfolding from Unfolding unfolding left join fetch unfolding.insertedFramesOldHives where unfolding in :unfoldings",
                Unfolding.class
            )
            .setParameter("unfoldings", unfoldings)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Unfolding fetchInsertedFramesNewHives(Unfolding result) {
        return entityManager
            .createQuery(
                "select unfolding from Unfolding unfolding left join fetch unfolding.insertedFramesNewHives where unfolding is :unfolding",
                Unfolding.class
            )
            .setParameter("unfolding", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Unfolding> fetchInsertedFramesNewHives(List<Unfolding> unfoldings) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, unfoldings.size()).forEach(index -> order.put(unfoldings.get(index).getId(), index));
        List<Unfolding> result = entityManager
            .createQuery(
                "select distinct unfolding from Unfolding unfolding left join fetch unfolding.insertedFramesNewHives where unfolding in :unfoldings",
                Unfolding.class
            )
            .setParameter("unfoldings", unfoldings)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
