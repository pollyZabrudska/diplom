package com.example.demo.repository;

import com.example.demo.model.DeclinedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclinedOrdersRepository extends JpaRepository<DeclinedOrder, Long> {
}
