package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Beekeeper;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.service.dto.BeekeeperDTO;
import com.mycompany.myapp.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Beekeeper} and its DTO {@link BeekeeperDTO}.
 */
@Mapper(componentModel = "spring")
public interface BeekeeperMapper extends EntityMapper<BeekeeperDTO, Beekeeper> {
    @Mapping(target = "user", source = "user", qualifiedByName = "userId")
    BeekeeperDTO toDto(Beekeeper s);

    @Named("userId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    UserDTO toDtoUserId(User user);
}
