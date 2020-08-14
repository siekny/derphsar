package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.message.MessageProperties;
import com.kshrd.derphsar_api.rest.product.request.ProductRequestModel;
import com.kshrd.derphsar_api.rest.product.response.ProductsOfAUserResponse;
import com.kshrd.derphsar_api.rest.product.response.ProductResponseModel;
import com.kshrd.derphsar_api.service.implement.ProductServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
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
    private ProductServiceImp productServiceImp;
    private MessageProperties message;

    @Autowired
    public void setMessage(MessageProperties message) {
        this.message = message;
    }

    @Autowired
    public void setProductServiceImp(ProductServiceImp productServiceImp) {
        this.productServiceImp = productServiceImp;
    }





    /**
     * Post a product
     *
     * @param productRequestModel - Product request model
     * @return - Return response message
     */
    @PostMapping("/products")
    @ApiOperation(value = "post product to shop", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<ProductResponseModel>> createProduct(
            @RequestBody ProductRequestModel productRequestModel) {

        BaseApiResponse<ProductResponseModel> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();

        ProductDto productDto = mapper.map(productRequestModel, ProductDto.class);

        UUID uuid = UUID.randomUUID();
        productDto.setProId("DP"+uuid.toString().substring(0,10));
        productDto.setStatus(true);

        ProductDto result = productServiceImp.insert(productDto);
        ProductResponseModel responseModel = mapper.map(result, ProductResponseModel.class);
        response.setMessage(message.inserted("Product"));
        response.setData(responseModel);
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    /**
     * Get products
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("/products")
    @ApiOperation(value = "show all products", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getProducts(
            //@RequestParam(value="shopId",required = false,defaultValue = "0") int shopId,
            @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
            @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
            @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
            @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();


        pagination.setTotalCount(productServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();
        List<ProductDto> productDtos = productServiceImp.getProducts(pagination);
        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        for(ProductDto productDto : productDtos){
            try {
                Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                productDto.setDetails(details);
                productDto.setImages(images);

                ModelMapper modelMapper = new ModelMapper();
                ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                productResponseModels.add(productResponseModel);
            }catch (JsonProcessingException e){
                e.printStackTrace();
            }
        }
        response.setData(productResponseModels);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        response.setMessage(message.selected("Products"));

        return ResponseEntity.ok(response);
    }




    /**
     * Get new products for 12 records to show homepage
     *
     * @return - list of new products response
     */
    @GetMapping("/new-products")
    @ApiOperation(value = "show all new products 12 records", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getNewProducts() {

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            List<ProductDto> productDtos = productServiceImp.getNewProducts();
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Get related products for 12 records to show homepage
     *
     * @return - list of related products response
     */
    @GetMapping("/related-products")
    @ApiOperation(value = "show all related products 12 records", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getRelatedProducts(@RequestParam(value = "categoryId" , required = false , defaultValue = "1") int categoryId) {

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();
        ObjectMapper mapper = new ObjectMapper();
        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            List<ProductDto> productDtos = productServiceImp.getRelatedProducts(categoryId);
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }





    /**
     * Get new products for 12 records to show homepage
     *
     * @return - list of popular products response
     */
    @GetMapping("/popular-products")
    @ApiOperation(value = "show all popular products 12 records", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> getPopularProducts() {;

        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();

        ObjectMapper mapper = new ObjectMapper();

        List<ProductResponseModel> productResponseModels = new ArrayList<>();

        try{
            List<ProductDto> productDtos = productServiceImp.getPopularProducts();
            for(ProductDto productDto : productDtos){
                try {
                    Object details = mapper.readValue(productDto.getDetails().toString(), Object.class);
                    Object images = mapper.readValue(productDto.getImages().toString(), Object.class);
                    productDto.setDetails(details);
                    productDto.setImages(images);

                    ModelMapper modelMapper = new ModelMapper();
                    ProductResponseModel productResponseModel = modelMapper .map(productDto, ProductResponseModel.class);
                    productResponseModels.add(productResponseModel);
                }catch (JsonProcessingException e){
                    e.printStackTrace();
                }
            }

            if(!productDtos.isEmpty()){
                response.setMessage(message.selected("Products"));
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
            }else {
                response.setMessage(message.hasNoRecords("Products"));
                response.setStatus(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);
    }


    /**
     * Delete a product
     *
     * @param id - id of a product
     * @return - Return response message
     */
    @DeleteMapping("/products/{id}")
    @ApiOperation(value = "delete a product", response = Void.class)
    public ResponseEntity<BaseApiResponse<Void>> deleteProduct(@PathVariable("id") String id){
        BaseApiResponse<Void> response = new BaseApiResponse<>();

        productServiceImp.deleteProduct(id);
        response.setMessage(message.deleted("Product"));
        response.setStatus(HttpStatus.OK);
        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    /**
     * Put a product
     *
     * @param id - Id of a product
     * @param productRequestModel - Product request model
     * @return - Return response message
     */
    @PutMapping("/products/{id}")
    @ApiOperation(value = "update a product", response = ProductRequestModel.class)
    public ResponseEntity<BaseApiResponse<ProductRequestModel>> updateProduct(
            @PathVariable("id") String id,
            @RequestBody ProductRequestModel productRequestModel){
        ModelMapper modelMapper=new ModelMapper();
        ProductDto dto=modelMapper.map(productRequestModel,ProductDto.class);
        ProductRequestModel responeModel=modelMapper.map(productServiceImp.updateProduct(id,dto),ProductRequestModel.class);

        BaseApiResponse<ProductRequestModel> respone=new BaseApiResponse <>();
        respone.setMessage(message.updated("Update"));
        respone.setStatus(HttpStatus.OK);
        respone.setData(responeModel);
        respone.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(respone);
    }





    /**
     * Get a product
     *
     * @param id - Id of a product
     * @return - Return response message
     */
    @GetMapping("/products/{id}")
    @ApiOperation(value = "find a product by id", response = ProductResponseModel.class)
    public ResponseEntity<BaseApiResponse<List<ProductResponseModel>>> findById(@PathVariable("id") String id){
        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<ProductResponseModel>> response = new BaseApiResponse<>();
        List<ProductResponseModel> products = new ArrayList<>();

        try {
            ProductDto productDto = productServiceImp.findById(id);
            products.add(mapper.map(productDto, ProductResponseModel.class));

            response.setMessage(message.selectedOne("Product"));
            response.setData(products);
            response.setStatus(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }



    /**
     * Get Products
     *
     * @param page  - Page of pagination
     * @param limit - Limit data of a pagination
     * @param totalPages - Total pages of data limited in a page
     * @param pagesToShow - Pages to show
     * @return - Return response message
     */
    @GetMapping("all-products/{userId}")
    @ApiOperation(value = "show all products by user id", response = Void.class)
    public ResponseEntity<BaseApiResponse<List<ProductsOfAUserResponse>>> getAllProductsByUserId(@PathVariable("userId") int userId,
                                                                                                 @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
                                                                                                 @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
                                                                                                 @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
                                                                                                 @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {

        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
        pagination.setPage(page);
        pagination.setLimit(limit);
        pagination.nextPage();
        pagination.previousPage();
        pagination.setTotalCount(productServiceImp.countId());
        pagination.setTotalPages(pagination.getTotalPages());

        BaseApiResponse<List<ProductsOfAUserResponse>> response = new BaseApiResponse<>();
        ModelMapper mapper = new ModelMapper();
        List<ProductsOfAUserResponse> productResponseModels = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<ProductDto> products = productServiceImp.getAllProductsByUserId(userId,pagination);
            for (ProductDto productDto : products) {

                Object images = objectMapper.readValue(productDto.getImages().toString(), Object.class);
                productDto.setImages(images);

                ProductsOfAUserResponse productResponseModel = mapper.map(productDto, ProductsOfAUserResponse.class);
                productResponseModels.add(productResponseModel);
            }

            if (!products.isEmpty()) {
                response.setData(productResponseModels);
                response.setStatus(HttpStatus.FOUND);
                response.setMessage(message.selected("Products"));
            }else {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMessage(message.hasNoRecords("Products"));
            }

            response.setPagination(pagination);
            System.out.println("Product = " + response);
        }catch (Exception e){
            e.printStackTrace();
        }

        response.setTime(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.ok(response);
    }


    //get all products
//    @GetMapping("/products")
//    @ApiOperation(value = "show all products", response = ProductDto.class)
//    public List<ProductDto> getProducts(
//                                        //@RequestParam(value="shopId",required = false,defaultValue = "0") int shopId,
//                                        @RequestParam(value = "page" , required = false , defaultValue = "1") int page,
//                                        @RequestParam(value = "limit" , required = false , defaultValue = "3") int limit,
//                                        @RequestParam(value = "totalPages" , required = false , defaultValue = "3") int totalPages,
//                                        @RequestParam(value = "pagesToShow" , required = false , defaultValue = "3") int pagesToShow) {
//
//        Pagination pagination = new Pagination(page, limit,totalPages,pagesToShow);
//        pagination.setPage(page);
//        pagination.setLimit(limit);
//        pagination.nextPage();
//        pagination.previousPage();
//
//
//        pagination.setTotalCount(productService.countId());
//        pagination.setTotalPages(pagination.getTotalPages());
//
//
////        ModelMapper mapper = new ModelMapper();
////        BaseApiResponse<List<ShopRequestModel>> response =
////                new BaseApiResponse<>();
////
////        List<ShopDto> shopDtoList = shopServiceImp.getShops(pagination);
////        List<ShopRequestModel> shops = new ArrayList<>();
////        for (ShopDto shopDto : shopDtoList) {
////            shops.add(mapper.map(shopDto, ShopRequestModel.class));
////        }
////
////        response.setPagination(pagination);
////        response.setMessage("you have selected all shops successfully!");
////        response.setData(shops);
////        response.setStatus(HttpStatus.OK);
////        response.setTime(new Timestamp(System.currentTimeMillis()));
////        return ResponseEntity.ok(response);
//
//        /////
//        List<ProductDto> data;
//        data = productService.getProducts(pagination);
//
//        //ModelMapper mapper = new ModelMapper();
//        BaseApiResponse<List<ProductRequestModel>> response =
//                new BaseApiResponse<>();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        for (ProductDto jd : data) {
//            try {
//                Object test = mapper.readValue(jd.getDetails().toString(), Object.class);
//                Object test1 = mapper.readValue(jd.getImages().toString(), Object.class);
//                jd.setDetails(test);
//                jd.setImages(test1);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        return data;
//    }
}
