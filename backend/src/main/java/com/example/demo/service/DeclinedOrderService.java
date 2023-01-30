package com.example.demo.service;

import com.example.demo.model.DeclinedOrder;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.DeclinedOrdersRepository;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.UserOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeclinedOrderService {
    private final DeclinedOrdersRepository declinedOrdersRepository;
    private final UserOrderRepository userOrderRepository;

    public void addDeclinedOrderToDB(Long id) {
        UserOrder userOrder = userOrderRepository.findById(id).get();
        String nameGoods = userOrder.getGoods().getGoodsName();
        String loginUser = userOrder.getCustomUser().getLogin();
        DeclinedOrder declinedOrder = new DeclinedOrder(loginUser, nameGoods);
        declinedOrdersRepository.save(declinedOrder);
    }
}