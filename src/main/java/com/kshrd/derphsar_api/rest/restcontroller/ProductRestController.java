package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.product.request.ProductRequestModel;
import com.kshrd.derphsar_api.service.implement.ProductServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
    private ProductServiceImp productService;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setProductService(ProductServiceImp productService) {
        this.productService = productService;
    }

    //post a product
    @PostMapping("/products")
    public ResponseEntity<BaseApiResponse<ProductRequestModel>> createProduct(
            @RequestBody ProductRequestModel productRequestModel) {

        BaseApiResponse<ProductRequestModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        ProductDto productDto = mapper.map(productRequestModel, ProductDto.class);

        UUID uuid = UUID.randomUUID();
        productDto.setProId("DP"+uuid.toString().substring(0,10));

        ProductDto result = productService.insert(productDto);
        ProductRequestModel result2 = mapper.map(result, ProductRequestModel.class);
        response.setMessage(message.inserted("Product"));
        response.setData(result2);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    //get all products
    @GetMapping("/products")
    public List<ProductDto> getProducts(@RequestParam(value="shopId",required = false,defaultValue = "0") int shopId) {

        List<ProductDto> data;
        data = productService.getProducts(shopId);

        ObjectMapper mapper = new ObjectMapper();

        for (ProductDto jd : data) {
            try {
                Object test = mapper.readValue(jd.getDetails().toString(), Object.class);
                Object test1 = mapper.readValue(jd.getImages().toString(), Object.class);
                jd.setDetails(test);
                jd.setImages(test1);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return data;
    }


    //Delete a product
    @DeleteMapping("/products/{id}")
    public ResponseEntity<BaseApiResponse<Void>> deleteProduct(@PathVariable("id") String id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        productService.deleteProduct(id);
        response.setMessage(message.deleted("Product"));
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



    //Update a product
    @PutMapping("/products/{id}")
    public ResponseEntity<BaseApiResponse<ProductRequestModel>> updateProduct(
            @PathVariable("id") String id,
            @RequestBody ProductRequestModel productRequestModel){
        ModelMapper modelMapper=new ModelMapper();
        ProductDto dto=modelMapper.map(productRequestModel,ProductDto.class);
        ProductRequestModel responeModel=modelMapper.map(productService.updateProduct(id,dto),ProductRequestModel.class);

        BaseApiResponse<ProductRequestModel> respone=new BaseApiResponse <>();
        respone.setMessage(message.updated("Update"));
        respone.setStatus(HttpStatus.OK);
        respone.setData(responeModel);
        respone.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(respone);
    }


    //find by id
    @GetMapping("/products/{id}")
    public ResponseEntity<BaseApiResponse<List<ProductRequestModel>>> findById(@PathVariable("id") String id){
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ProductRequestModel>> response =
                new BaseApiResponse<>();

        ProductDto productDto = productService.findById(id);
        List<ProductRequestModel> productRequestModels = new ArrayList<>();

        productRequestModels.add(mapper.map(productDto, ProductRequestModel.class));
        response.setMessage(message.selectedOne("Product"));
        response.setData(productRequestModels);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }
}
