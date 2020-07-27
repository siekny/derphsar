package com.example.derphsar_api.rest.product.restcontroller;

import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.rest.BaseApiResponse;
import com.example.derphsar_api.rest.product.request.ProductRequestModel;
import com.example.derphsar_api.rest.product.response.ProductResponseModel;
import com.example.derphsar_api.service.implement.ProductServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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


//    @GetMapping("/products")
//    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> select() {
//
//        ModelMapper mapper = new ModelMapper();
//        BaseApiResponse<List<ProductResponseModel>> response =
//                new BaseApiResponse<>();
//
//        List<ProductDto> articleDtoList = productService.getProducts();
//        List<ProductResponseModel> articles = new ArrayList<>();
//
//        for (ProductDto articleDto : articleDtoList) {
////
////            if (articleDto.getProDetails() != null) {
////                String test = articleDto.getProDetails().toString();
////                System.out.println("Test = " + test);
////                JsonObject jsonObject = new JsonParser().parse(test).getAsJsonObject();
////                articleDto.setProDetails(jsonObject);
////            }
//
//            articles.add(mapper.map(articleDto, ProductResponseModel.class));
//        }
//
//        response.setMessage("You have found all articles successfully");
//        response.setData(articles);
//        response.setStatus(HttpStatus.OK);
//        response.setTime(new Timestamp(System.currentTimeMillis()));
//
//        return ResponseEntity.ok(response);
//    }





    //post product
    @PostMapping("/products")
    public ResponseEntity<BaseApiResponse<ProductRequestModel>> createProduct(
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



    //get all products
    @GetMapping("/products")
    public List<ProductDto> getProducts() {

        List<ProductDto> data;

        data = productService.getProducts();

        ObjectMapper mapper = new ObjectMapper();

        for (ProductDto jd : data) {
            try {
                Object test = mapper.readValue(jd.getProDetails().toString(), Object.class);
                Object test1 = mapper.readValue(jd.getProImages().toString(), Object.class);
                jd.setProDetails(test);
                jd.setProImages(test1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return data;

    }

}
