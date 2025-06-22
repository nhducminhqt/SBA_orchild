package com.orchid.orchidbe.controllers;

import com.orchid.orchidbe.dto.RoleDTO;
import com.orchid.orchidbe.pojos.Role;
import com.orchid.orchidbe.services.IService.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/roles")
@RequiredArgsConstructor
@Tag(name = "Role Api", description = "Operation related to Role")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable String id) {
        Role role = roleService.getById(id);
        if (role == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(role);
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody RoleDTO.RoleReq req) {
        roleService.add(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id, @RequestBody RoleDTO.RoleReq req) {
        roleService.update(id, req);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}