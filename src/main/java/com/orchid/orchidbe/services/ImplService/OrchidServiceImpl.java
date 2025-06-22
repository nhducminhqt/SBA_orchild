package com.orchid.orchidbe.services.ImplService;

import com.orchid.orchidbe.pojos.Orchid;
import com.orchid.orchidbe.repositories.OrchidRepository;
import java.util.List;

import com.orchid.orchidbe.services.IService.OrchidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchidServiceImpl implements OrchidService {

    private final OrchidRepository orchidRepository;

    @Override
    public List<Orchid> getAll() {
        return orchidRepository.findAll();
    }

    @Override
    public Orchid getById(String id) {
        return orchidRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Orchid not found with ID: " + id));
    }

    @Override
    public void add(Orchid orchid) {
        orchidRepository.save(orchid);
    }

    @Override
    public void update(Orchid orchid) {
        if (!orchidRepository.existsById(orchid.getId())) {
            throw new IllegalArgumentException("Orchid not found with ID: " + orchid.getId());
        }
        orchidRepository.save(orchid);
    }

    @Override
    public void delete(Orchid orchid) {
        if (!orchidRepository.existsById(orchid.getId())) {
            throw new IllegalArgumentException("Orchid not found with ID: " + orchid.getId());
        }
        orchidRepository.delete(orchid);
    }
}