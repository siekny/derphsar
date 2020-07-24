package com.example.derphsar_api.rest.product.restcontroller;

import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.rest.BaseApiResponse;
import com.example.derphsar_api.rest.product.request.ProductRequestModel;
import com.example.derphsar_api.service.implement.ProductServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private ProductServiceImp productService;

    @Autowired
    public void setProductService(ProductServiceImp productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<BaseApiResponse<List<ProductRequestModel>>> getProducts() {

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ProductRequestModel>> response =
                new BaseApiResponse<>();

        List<ProductRequestModel> articleDtoList = productService.getProducts();
//        List<ProductRequestModel> products = new ArrayList<>();
//
//        for (ProductDto articleDto : articleDtoList) {
//            products.add(mapper.map(articleDto, ProductRequestModel.class));
//        }

        response.setMessage("You have found all products successfully");
        response.setData(articleDtoList);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        System.out.println(articleDtoList);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/products")
    public ResponseEntity<BaseApiResponse<ProductRequestModel>> insert(
            @RequestBody ProductRequestModel productRequestModel) {


        BaseApiResponse<ProductRequestModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        ProductDto productDto = mapper.map(productRequestModel, ProductDto.class);


        ProductDto result = productService.insert(productDto);
        ProductRequestModel result2 = mapper.map(result, ProductRequestModel.class);
        response.setMessage("You have added product successfully");
        response.setData(result2);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);

    }





}
