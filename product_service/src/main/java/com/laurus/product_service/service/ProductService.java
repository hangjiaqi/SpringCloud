package com.laurus.product_service.service;


import com.laurus.product_service.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> listProduct();

    Product findById(int id);


}
