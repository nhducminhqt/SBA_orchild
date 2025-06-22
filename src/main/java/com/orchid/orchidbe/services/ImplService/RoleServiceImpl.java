package com.orchid.orchidbe.services.ImplService;

import com.orchid.orchidbe.dto.RoleDTO;
import com.orchid.orchidbe.pojos.Role;
import com.orchid.orchidbe.repositories.RoleRepository;
import java.util.List;

import com.orchid.orchidbe.services.IService.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getById(int id) {
        return roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Role not found"));
    }

    @Override
    public void add(RoleDTO.RoleReq role) {
        if(roleRepository.existsByName(role.name())) {
            throw new IllegalArgumentException("Role with name " + role.name() + " already exists");
        }

        roleRepository.save(new Role(role.name()));
    }

    @Override
    public void update(int id, RoleDTO.RoleReq role) {
        var existingRole = getById(id);
        existingRole.setName(role.name());
        roleRepository.save(existingRole);
    }

    @Override
    public void delete(int id) {
        var existingRole = getById(id);
        roleRepository.delete(existingRole);
    }
}
