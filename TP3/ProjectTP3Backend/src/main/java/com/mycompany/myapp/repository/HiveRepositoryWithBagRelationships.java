package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Hive;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface HiveRepositoryWithBagRelationships {
    Optional<Hive> fetchBagRelationships(Optional<Hive> hive);

    List<Hive> fetchBagRelationships(List<Hive> hives);

    Page<Hive> fetchBagRelationships(Page<Hive> hives);
}
