package com.example.finaldeploy.service;

import com.example.finaldeploy.entity.Product;
import com.example.finaldeploy.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceMySQL implements ProductService{
    private final ProductRepository productRepository;

    public ProductServiceMySQL(@Autowired ProductRepository productRepository )
    {
        this.productRepository  = productRepository ;
    }

    @Override
    public Product save(Product product )
    {
        //TODO implement this method
        return productRepository.save(product);

    }

    @Override
    public void delete( Integer id )
    {
        //TODO implement this method
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> all()
    {
        //TODO implement this method
        List<Product> result = new ArrayList<>();
        productRepository.findAll().forEach( result::add );
        return result;
    }

    @Override
    public Product findById( Integer id)
    {
        //TODO implement this method
        Optional<Product> optional = productRepository.findById(id);
        Product newProduct = null;
        if(optional.isPresent()){
            newProduct = optional.get();
        }else{
            throw new RuntimeException("Product not for id ::" + id);
        }
        return newProduct ;
    }
}
