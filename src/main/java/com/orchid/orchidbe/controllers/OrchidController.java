package com.orchid.orchidbe.controllers;

import com.orchid.orchidbe.pojos.Orchid;
import com.orchid.orchidbe.services.IService.OrchidService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/orchids")
@RequiredArgsConstructor
@Tag(name = "Orchid Api", description = "Operation related to Orchid")
public class OrchidController {

    private final OrchidService orchidService;

    @GetMapping("")
    public ResponseEntity<List<Orchid>> getOrchids() {
        return ResponseEntity.ok(orchidService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Orchid> getOrchidById(@PathVariable String id) {
        Orchid orchid = orchidService.getById(id);
        return ResponseEntity.ok(orchid);
    }

    @PostMapping("")
    public ResponseEntity<Void> addOrchid(@RequestBody Orchid orchid) {
        orchidService.add(orchid);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrchid(@PathVariable String id, @RequestBody Orchid orchid) {
        orchid.setId(id); // Ensure the ID is set for the update
        orchidService.update(orchid);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrchid(@PathVariable String id) {
        Orchid orchid = orchidService.getById(id); // Fetch the orchid to delete
        orchidService.delete(orchid);
        return ResponseEntity.noContent().build();
    }
}
