package top.menghuijia.bucks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.menghuijia.bucks.model.Coffee;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {}
