package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Crest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface CrestRepositoryWithBagRelationships {
    Optional<Crest> fetchBagRelationships(Optional<Crest> crest);

    List<Crest> fetchBagRelationships(List<Crest> crests);

    Page<Crest> fetchBagRelationships(Page<Crest> crests);
}
