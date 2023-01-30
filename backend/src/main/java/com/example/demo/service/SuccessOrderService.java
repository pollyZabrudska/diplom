package com.example.demo.service;

import com.example.demo.model.SuccessOrders;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.SuccessOrdersRepository;
import com.example.demo.repository.UserOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SuccessOrderService {
    private final SuccessOrdersRepository successOrdersRepository;
    private final UserOrderRepository userOrderRepository;
    @Transactional
    public List<SuccessOrders> findAll(){
        return successOrdersRepository.findAll();
    }
    @Transactional
    public void addSuccessOrderToDB(Long id) {
        UserOrder userOrder = userOrderRepository.findById(id).get();
        String name = userOrder.getGoods().getGoodsName();
        Long summaryPrice = userOrder.getOrderSummaryPrice();
        SuccessOrders successOrders = new SuccessOrders(name, summaryPrice);
        successOrdersRepository.save(successOrders);
    }
}