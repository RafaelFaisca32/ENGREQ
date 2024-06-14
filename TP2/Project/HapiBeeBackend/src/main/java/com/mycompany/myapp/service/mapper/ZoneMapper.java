package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Zone;
import com.mycompany.myapp.service.dto.ZoneDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Zone} and its DTO {@link ZoneDTO}.
 */
@Mapper(componentModel = "spring")
public interface ZoneMapper extends EntityMapper<ZoneDTO, Zone> {}
