package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Frame;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Frame entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FrameRepository extends JpaRepository<Frame, Long>, JpaSpecificationExecutor<Frame> {}
