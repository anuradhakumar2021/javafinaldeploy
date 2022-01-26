package com.example.finaldeploy.controller;

import com.example.finaldeploy.controller.dto.ProductDto;

import com.example.finaldeploy.entity.Product;
import com.example.finaldeploy.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Product")
public class ProductController {
    final ProductService productService;
//    final ProductRepository productRepository;


    public ProductController(@Autowired ProductService productService )
    {
        this.productService=productService;
    }
    @GetMapping
    public Iterable<Product> getProduct(){
        return productService.all();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable(name="id") Integer id){
        return productService.findById(id);
    }
    @CrossOrigin
    @PostMapping
    public Product save( @RequestBody ProductDto productDto )
    {
        return  productService.save( new Product(productDto));
    }
    @PutMapping( "/{id}" )
    public Product update( @RequestBody ProductDto productDto, @PathVariable Integer id )
    {
        Product product= productService.findById( id );
        product.setName(  productDto.getName() );
        product.setDescription(  productDto.getDescription() );
        product.setImageUrl(  productDto.getImageUrl() );
        return productService.save(  product );

//        Product productToUpdate = productToUpdateOptional.get();
//        if(incomingProductChanges.getName() != null){
//            productToUpdate.setName(incomingProductChanges.getName());
//        }
//        if(incomingProductChanges.getDescription() != null){
//            productToUpdate.setDescription(incomingProductChanges.getDescription());
//        }
//        if(incomingProductChanges.getImageUrl() != null){
//            productToUpdate.setImageUrl(incomingProductChanges.getImageUrl());
//        }
//        if(incomingProductChanges.getPrice() != null){
//            productToUpdate.setPrice(incomingProductChanges.getPrice());
//        }
//        return productService.save(productToUpdate);
    }
    @DeleteMapping( "/{id}" )
    public void delete( @PathVariable Integer id )
    {
        productService.delete( id );
    }
//        Product productToDelete= productToDeleteOptional.get();
//        productService.delete(productToDelete.getId());
//        return productToDelete;
}


