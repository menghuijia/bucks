package top.menghuijia.bucks.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.menghuijia.bucks.model.Coffee;
import top.menghuijia.bucks.model.CoffeeOrder;
import top.menghuijia.bucks.model.OrderState;
import top.menghuijia.bucks.repository.CoffeeOrderRepository;

import java.util.Arrays;

@Slf4j
@Service
public class CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(Arrays.asList(coffees))
                .state(OrderState.INIT)
                .build();

        CoffeeOrder saved = coffeeOrderRepository.save(order);
        log.info("New Order: {}", saved);
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            log.warn("Wrong State order: {}, {}", state, order.getState());
            return false;
        }
        order.setState(state);
        coffeeOrderRepository.save(order);
        log.info("Updated Order: {}", order);
        return true;
    }
}
