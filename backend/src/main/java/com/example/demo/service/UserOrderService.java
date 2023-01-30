package com.example.demo.service;

import com.example.demo.model.CustomUser;
import com.example.demo.model.Goods;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.UserOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;

@Service
@AllArgsConstructor
public class UserOrderService {

    private final GoodsRepository goodsRepository;

    private final UserOrderRepository userOrderRepository;
    private final UserService userService;

    @Transactional
    public void addSuccessOrder(Long goodsId, String orderDetails, String addressOrder,
                                Long orderQuantity, Authentication authentication) {
        Goods goods = goodsRepository.findById(goodsId).get();
        Long orderSummaryPrice = Long.valueOf(goodsRepository.findById(goodsId).get().getGoodsPrise()) * orderQuantity;
        CustomUser customUser = userService.findByLogin(authentication.getName());
        userOrderRepository.save(new UserOrder(orderDetails, addressOrder,
                orderQuantity, customUser, goods, orderSummaryPrice));
    }

}