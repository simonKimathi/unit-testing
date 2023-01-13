package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.ProductLine;

public interface ProductLineService {
    List<ProductLine> getAllProductLines(Integer start, Integer limit);
}
