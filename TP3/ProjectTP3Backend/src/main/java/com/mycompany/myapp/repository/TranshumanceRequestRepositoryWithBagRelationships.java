package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TranshumanceRequest;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface TranshumanceRequestRepositoryWithBagRelationships {
    Optional<TranshumanceRequest> fetchBagRelationships(Optional<TranshumanceRequest> transhumanceRequest);

    List<TranshumanceRequest> fetchBagRelationships(List<TranshumanceRequest> transhumanceRequests);

    Page<TranshumanceRequest> fetchBagRelationships(Page<TranshumanceRequest> transhumanceRequests);
}
