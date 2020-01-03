package top.menghuijia.bucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.menghuijia.bucks.model.CoffeeOrder;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {}