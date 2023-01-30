package com.example.demo.repository;

import com.example.demo.model.SuccessOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuccessOrdersRepository extends JpaRepository<SuccessOrders, Long> {

}
