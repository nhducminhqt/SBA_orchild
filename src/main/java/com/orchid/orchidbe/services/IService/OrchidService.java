package com.orchid.orchidbe.services.IService;

import com.orchid.orchidbe.pojos.Orchid;
import java.util.List;

public interface OrchidService {

    List<Orchid> getAll();
    Orchid getById(String id);
    void add(Orchid orchid);
    void update(Orchid orchid);
    void delete(Orchid orchid);

}
