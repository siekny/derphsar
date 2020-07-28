package com.example.derphsar_api.service.implement;

import com.example.derphsar_api.repository.ProductRepository;
import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.rest.product.request.ProductRequestModel;
import com.example.derphsar_api.service.ProductService;
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

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.getProducts();
    }

    @Override
    public ProductDto insert(ProductDto productDto) {
        System.out.println("Product"+productDto);
        boolean isInserted = productRepository.insert(productDto);
        if(isInserted)
            return productDto;
        else
            return null;
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        boolean isUpdated = productRepository.updateProduct(id,productDto);
        if(isUpdated){
            return productDto;
        }
        return null;
    }

}
