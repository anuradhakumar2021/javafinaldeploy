package com.example.finaldeploy.service;

import com.example.finaldeploy.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    void delete(Integer id);

    List<Product> all();

    Product findById( Integer id );

}
