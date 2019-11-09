package com.laurus.order_service.service;

import com.laurus.order_service.entity.ProductOrder;

import java.io.IOException;

public interface ProductOrderService {

    ProductOrder save(int userId,int productId);

    ProductOrder save2(int userId,int productId) throws IOException;
}
