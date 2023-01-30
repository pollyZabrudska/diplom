package com.example.demo.controller;


import com.example.demo.model.Goods;
import com.example.demo.model.GoodsRole;
import com.example.demo.model.UserOrder;
import com.example.demo.repository.GoodsRepository;
import com.example.demo.repository.UserOrderRepository;
import com.example.demo.service.DeclinedOrderService;
import com.example.demo.service.GoodsService;
import com.example.demo.service.SuccessOrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {
    private final UserOrderRepository userOrderRepository;
    private final SuccessOrderService successOrderService;
    private final DeclinedOrderService declinedOrderService;
    private final GoodsService goodsService;
    private final GoodsRepository goodsRepository;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/current-orders")
    public String adminPageCurrentOrders(Model model) {
        List<UserOrder> currentOrders = userOrderRepository.findAll();
        model.addAttribute("orders", currentOrders);
        return "current-orders";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/successOrder")
    public String successOrder(@RequestParam Long id) {
        successOrderService.addSuccessOrderToDB(id);
        userOrderRepository.deleteById(id);
        return "redirect:/current-orders";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/declinedOrder")
    public String declinedOrder(@RequestParam Long id) {
        declinedOrderService.addDeclinedOrderToDB(id);
        userOrderRepository.deleteById(id);
        return "redirect:/current-orders";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin-add")
    public String userGood(Model model) {
        return "admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin-add")
    public String addGoods(@RequestParam String goodsName,
                           @RequestParam Integer goodsPrise,
                           @RequestParam String aboutGoods,
                           @RequestParam("photoGoods") MultipartFile multipartFile,
                           @RequestParam String goodsRole) throws IOException {
        byte[] photoGoods = multipartFile.getBytes();
        String decodedPhoto = Base64.getEncoder().encodeToString(photoGoods);
        goodsService.addGoodsToDB(goodsName, goodsPrise, aboutGoods
                , decodedPhoto, GoodsRole.valueOf(goodsRole));
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin-goods")
    public String adminGoods(Model model) {
        Iterable<Goods> iterable = goodsRepository.findAll();
        model.addAttribute("all", iterable);
        return "buyings";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin-goods")
    public String adminRemoveGoods(@RequestParam Long goodsId) {
        goodsRepository.deleteById(goodsId);
        return "redirect:/admin-goods";
    }
}