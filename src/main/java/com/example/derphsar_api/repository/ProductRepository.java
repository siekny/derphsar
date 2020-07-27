package com.example.derphsar_api.repository;

import com.example.derphsar_api.mybatis.JSONTypeHandlerPg;
import com.example.derphsar_api.repository.dto.ProductDetail;
import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.repository.dto.ShopDto;
import com.example.derphsar_api.repository.provider.ProductProvider;
import com.example.derphsar_api.rest.product.request.ProductRequestModel;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    @SelectProvider(value = ProductProvider.class, method = "getProducts")
    @Results({
            @Result(column = "pro_id" ,property = "proId"),
            @Result(column = "pro_name" ,property = "proName"),
            @Result(column = "pro_price" ,property = "proPrice"),
            @Result(column = "pro_description" ,property = "proDescription"),
            @Result(column = "pro_status" ,property = "proStatus"),
            @Result(column = "pro_is_sold" ,property = "proIsSold"),
            @Result(column = "pro_view_count" ,property = "proViewCount"),
            @Result(column = "pro_details" ,property = "proDetails", jdbcType = JdbcType.OTHER, typeHandler = JSONTypeHandlerPg.class),
            @Result(column = "pro_img" ,property = "proImages", jdbcType = JdbcType.OTHER, typeHandler = JSONTypeHandlerPg.class),

//            @Result(column = "color" ,property = "procolor"),
//            @Result(column = "size" ,property = "proSize"),
        //   @Result(column = "pro_id",property = "proDetails",many = @Many(select = "getProductDetail")),

            @Result(column = "shop_id", property = "shop", many = @Many(select = "getShops"))
    })
    List<ProductDto> getProducts();



    //select all shop
    @Select("SELECT * FROM dp_shops WHERE id=#{shop_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "shop_id" ,property = "shop_id"),
            @Result(column = "shop_name" ,property = "shop_name"),
            @Result(column = "shop_adress" ,property = "shop_adress")
    })
    ShopDto getShops(int shop_id);



    @Select("SELECT pro_details -> 'color' AS color, pro_details -> 'size' AS size FROM dp_products WHERE pro_id =#{pro_id}")
    @Results({
            @Result(column = "color" ,property = "color"),
            @Result(column = "size" ,property = "size")
    })
    ProductDetail getProductDetail(String pro_id);








    //create product
    @Insert("INSERT INTO dp_products ( pro_name, pro_price, pro_description, pro_status, pro_is_sold, pro_view_count, pro_img ,pro_details, shop_id)" +
            "VALUES (  #{proName, jdbcType=VARCHAR}, #{proPrice}, #{proDescription}, #{proStatus}, #{proIsSold}, #{proViewCount},#{proImages, jdbcType=OTHER, typeHandler=com.example.derphsar_api.mybatis.JSONTypeHandlerPg},#{proDetails, jdbcType=OTHER, typeHandler=com.example.derphsar_api.mybatis.JSONTypeHandlerPg}, #{shop.id})")
    boolean insert(ProductDto productDto);



    //delete product
    @Delete("delete FROM dp_products where pro_id =#{id}")
    void deleteProduct(String id);

}
