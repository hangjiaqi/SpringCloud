package com.laurus.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.laurus.order_service.entity.ProductOrder;
import com.laurus.order_service.service.ProductClient;
import com.laurus.order_service.service.ProductOrderService;
import com.laurus.order_service.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service("productOrderService")
public class ProductOrderServiceImpl implements ProductOrderService {
/*

    @Autowired
    RestTemplate restTemplate;
*/

    @Autowired
    ProductClient productClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Override
    public ProductOrder save(int userId, int productId) {
        //获取商品详情 TODO
//        Map map = restTemplate.getForObject("http://product-service/api/v1/product/find?id=" + productId, Map.class);
        ServiceInstance serviceInstance = loadBalancerClient.choose("product-service");
        String url =String.format("http://%s:%s/api/v1/product/find?id=" + productId,serviceInstance.getHost(),serviceInstance.getPort());
        RestTemplate restTemplate=new RestTemplate();
        Map map = restTemplate.getForObject(url, Map.class);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(LocalDateTime.now());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(map.get("name").toString());
        productOrder.setPrice((int) map.get("price"));
        return productOrder;
    }

    @Override
    public ProductOrder save2(int userId, int productId) throws IOException {
        String response = productClient.findById(productId);
        JsonNode jsonNode= JsonUtils.str2JsonNode(response);

        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(LocalDateTime.now());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.valueOf(jsonNode.get("price").toString()));
        return productOrder;
    }


}
