package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.ProductRepository;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // get all products
    @Override
    public List<ProductDto> getProducts(int shopId) {
        if(shopId == 0)
            return productRepository.getProducts();
        else
            return productRepository.findProductByShopId(shopId);
    }

    //insert a product
    @Override
    public ProductDto insert(ProductDto productDto) {
        System.out.println("Product"+productDto);
        boolean isInserted = productRepository.insert(productDto);
        if(isInserted)
            return productDto;
        else
            return null;
    }

    //delete a product
    @Override
    public void deleteProduct(String id) {
        productRepository.deleteProduct(id);
    }

    //update a product
    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        boolean isUpdated = productRepository.updateProduct(id,productDto);
        if(isUpdated){
            return productDto;
        }
        return null;
    }

    //find a product by id
    @Override
    public ProductDto findById(String proId) {
        return  productRepository.findById(proId);
    }

}