package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class SuccessOrders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String goodsName;

    private Long orderSummaryPrice;

    public SuccessOrders(String goodsName, Long orderSummaryPrice) {
        this.goodsName = goodsName;
        this.orderSummaryPrice = orderSummaryPrice;
    }
}
