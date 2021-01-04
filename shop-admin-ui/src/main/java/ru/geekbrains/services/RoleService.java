package ru.geekbrains.services;

import ru.geekbrains.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Page<Role> findAll(Specification<Role> spec, int page, int size) {
        return roleRepository.findAll(spec, PageRequest.of(page, size));
    }

    public void saveOrUpdate(Role role) {
        String incomingRoleName= role.getName().toUpperCase();
        role.setName(!incomingRoleName.startsWith("ROLE_") ? "ROLE_" + incomingRoleName : incomingRoleName);
        roleRepository.save(role);
    }

    public void remove(Long id) {
        roleRepository.deleteById(id);
    }

    public Role findById(Long id) {
        return roleRepository.getOne(id);
    }
}
