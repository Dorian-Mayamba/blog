package uk.co.blog.mappers;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import uk.co.blog.dtos.roles.RoleDTO;
import uk.co.blog.models.Role;

@Mapper()
public interface RoleMapper {
    RoleDTO roleToDTO(Role role);
}
