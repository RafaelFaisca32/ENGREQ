package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Unfolding;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface UnfoldingRepositoryWithBagRelationships {
    Optional<Unfolding> fetchBagRelationships(Optional<Unfolding> unfolding);

    List<Unfolding> fetchBagRelationships(List<Unfolding> unfoldings);

    Page<Unfolding> fetchBagRelationships(Page<Unfolding> unfoldings);
}
