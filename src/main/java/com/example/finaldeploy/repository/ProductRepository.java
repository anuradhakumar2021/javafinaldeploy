package com.example.finaldeploy.repository;

import com.example.finaldeploy.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {
}
