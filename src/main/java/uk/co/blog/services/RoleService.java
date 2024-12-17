package uk.co.blog.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.blog.models.Role;
import uk.co.blog.repositories.RoleRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findById(Long id){
        return roleRepository.findRoleById(id)
                .orElseThrow(()->new NoSuchElementException("Cannot find role with the Id "+ id));
    }

    public Role findByName(String name){
        return roleRepository.findRoleByName(name)
                .orElseThrow(()->new NoSuchElementException("Cannot find role with the name "+ name));
    }
}
