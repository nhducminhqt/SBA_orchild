package com.orchid.orchidbe.services.ImplService;

import com.orchid.orchidbe.dto.OrderDTO;
import com.orchid.orchidbe.pojos.Order;
import com.orchid.orchidbe.repositories.OrderRepository;
import com.orchid.orchidbe.services.IService.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDTO.OrderRes> getAll() {
        return orderRepository.findAll()
                .stream()
                .map(OrderDTO.OrderRes::fromEntity)
                .toList();
    }

    @Override
    public Order getById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + id));
    }

    @Override
    public void add(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order) {
        if (!orderRepository.existsById(order.getId())) {
            throw new IllegalArgumentException("Order not found with ID: " + order.getId());
        }
        orderRepository.save(order);
    }

    @Override
    public void delete(String id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order not found with ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO.OrderRes> getOrdersByAccountId(String accountId) {
        return orderRepository.findAll()
                .stream()
                .filter(order -> order.getAccount() != null && accountId.equals(order.getAccount().getId()))
                .map(OrderDTO.OrderRes::fromEntity)
                .toList();
    }
}