package com.laurus.order_service.controller;

import com.laurus.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Action;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    ProductOrderService productOrderService;

    @RequestMapping("save")
    public Object save(@RequestParam("user_id") int userId, @RequestParam("product_id") int productId) throws IOException {
        return productOrderService.save2(userId,productId);
    }
}
