package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.Product;

public interface ProductService {
    List<Product> getAllProducts(Integer start, Integer limit);
}
