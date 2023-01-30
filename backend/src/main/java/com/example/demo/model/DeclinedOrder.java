package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class DeclinedOrder {
    @Id
    @GeneratedValue
    private Long id;
    private String userLogin;
    private String goodsName;

    public DeclinedOrder(String userLogin, String goodsName) {
        this.userLogin = userLogin;
        this.goodsName = goodsName;
    }
}
