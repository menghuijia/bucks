package top.menghuijia.bucks;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import top.menghuijia.bucks.model.Coffee;
import top.menghuijia.bucks.model.CoffeeOrder;
import top.menghuijia.bucks.model.OrderState;
import top.menghuijia.bucks.repository.CoffeeRepository;
import top.menghuijia.bucks.service.CoffeeOrderService;
import top.menghuijia.bucks.service.CoffeeService;

import java.util.Optional;

@Slf4j
@EnableJpaAuditing
@SpringBootApplication
public class BucksApplication implements ApplicationRunner {

    @Autowired
    private CoffeeRepository coffeeRepository;


    @Autowired
    private CoffeeService coffeeService;

    @Autowired
    private CoffeeOrderService orderService;

    public static void main(String[] args) {
        SpringApplication.run(BucksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("All Coffee: {}", coffeeRepository.findAll());

        Optional<Coffee> latte = coffeeService.findOneCoffee("Latte");
        if (latte.isPresent()) {
            CoffeeOrder order = orderService.createOrder("Li Lei", latte.get());
            log.info("Update INIT to PAID: {}", orderService.updateState(order, OrderState.PAID));
            log.info("Update PAID to INIT: {}", orderService.updateState(order, OrderState.INIT));
        }
    }
}
