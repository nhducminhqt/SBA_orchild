package com.orchid.orchidbe.services.IService;

import com.orchid.orchidbe.dto.OrderDTO;
import com.orchid.orchidbe.pojos.Order;
import java.util.List;

public interface OrderService {

    List<OrderDTO.OrderRes> getAll();
    Order getById(String id);
    void add(Order order);
    void update(Order order);
    void delete(String id);

}
