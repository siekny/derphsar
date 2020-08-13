package com.kshrd.derphsar_api.service;


import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.WishListDto;

import java.util.List;

public interface ProductService {

    //getProducts
    List<ProductDto> getProducts(Pagination pagination);


    //create product
    ProductDto insert(ProductDto productDto);


    //delete a product
    void deleteProduct(String id);

    //update a product
    ProductDto updateProduct(String id, ProductDto productDto);

    //find by id
    ProductDto findById(String proId);

    //count id
    int countId();

    //get new products
    List<ProductDto> getNewProducts();

    //get popular
    List<ProductDto> getPopularProducts();

    //get products by user id
    List<ProductDto> getAllProductsByUserId(int userId, Pagination pagination);
}
