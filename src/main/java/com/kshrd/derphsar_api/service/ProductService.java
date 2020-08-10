package com.kshrd.derphsar_api.service;


import com.kshrd.derphsar_api.repository.dto.ProductDto;

import java.util.List;

public interface ProductService {

    //getProducts
    List<ProductDto> getProducts(int shopId);


    //create product
    ProductDto insert(ProductDto productDto);


    //delete a product
    void deleteProduct(String id);

    //update a product
    ProductDto updateProduct(String id, ProductDto productDto);

    //find by id
    ProductDto findById(String proId);
}
