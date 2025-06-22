package com.orchid.orchidbe.seeders;

import com.orchid.orchidbe.pojos.Role;
import com.orchid.orchidbe.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        seedRoleIfNotExists("ROLE_ADMIN");
        seedRoleIfNotExists("ROLE_USER");
        seedRoleIfNotExists("ROLE_STAFF");
    }

    private void seedRoleIfNotExists(String roleName) {
        boolean exists = roleRepository.existsByName(roleName);
        if (!exists) {
            Role role = new Role(roleName);
            roleRepository.save(role);
            System.out.println("✅ Đã tạo role: " + roleName);
        } else {
            System.out.println("ℹ️ Role đã tồn tại: " + roleName);
        }
    }
}
