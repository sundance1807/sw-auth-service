package kz.sw_auth_service.mapper;

import kz.sw_auth_service.model.dto.UserDTO;
import kz.sw_auth_service.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(UserEntity userEntity);
}
