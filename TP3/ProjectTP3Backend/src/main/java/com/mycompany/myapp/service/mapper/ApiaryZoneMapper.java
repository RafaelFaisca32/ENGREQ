package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.ApiaryZone;
import com.mycompany.myapp.service.dto.ApiaryZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ApiaryZone} and its DTO {@link ApiaryZoneDTO}.
 */
@Mapper(componentModel = "spring")
public interface ApiaryZoneMapper extends EntityMapper<ApiaryZoneDTO, ApiaryZone> {}
