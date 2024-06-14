package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Disease;
import com.mycompany.myapp.service.dto.DiseaseDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Disease} and its DTO {@link DiseaseDTO}.
 */
@Mapper(componentModel = "spring")
public interface DiseaseMapper extends EntityMapper<DiseaseDTO, Disease> {}
