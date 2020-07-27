package com.example.derphsar_api.service;


import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.rest.product.request.ProductRequestModel;

import java.util.List;

public interface ProductService {

    //getProducts
    List<ProductDto> getProducts();


    //create product
    ProductDto insert(ProductDto productDto);


    //delete a product
    void deleteProduct(String id);

    //update a product
    ProductDto updateProduct(String id, ProductDto productDto);
}
