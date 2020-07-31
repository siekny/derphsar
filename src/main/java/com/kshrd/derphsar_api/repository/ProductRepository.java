package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg;
import com.kshrd.derphsar_api.repository.dto.ProductDto;
import com.kshrd.derphsar_api.repository.dto.ShopDto;
import com.kshrd.derphsar_api.repository.provider.ProductProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    @SelectProvider(value = ProductProvider.class, method = "getProducts")
    @Results({
            @Result(column = "pro_id" ,property = "proId"),
            @Result(column = "is_sold" ,property = "isSold"),
            @Result(column = "view_count" ,property = "viewCount"),
            @Result(column = "details" ,property = "details", jdbcType = JdbcType.OTHER, typeHandler = JSONTypeHandlerPg.class),
            @Result(column = "images" ,property = "images", jdbcType = JdbcType.OTHER, typeHandler = JSONTypeHandlerPg.class),
            @Result(column = "shop_id", property = "shop", many = @Many(select = "getShops"))
    })
    List<ProductDto> getProducts();

    //select all shop
    @Select("SELECT * FROM dp_shops WHERE id=#{shop_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "shop_id" ,property = "shopId"),
            @Result(column = "name" ,property = "name"),
            @Result(column = "address" ,property = "address")
    })
    ShopDto getShops(int shop_id);



    //create product
    @Insert("INSERT INTO dp_products (pro_id, name, price, description, status, is_sold, view_count, images , details, shop_id)" +
            "VALUES ( #{proId}, #{name, jdbcType=VARCHAR}, #{price}, #{description}, #{status}, #{isSold}, #{viewCount},#{images, jdbcType=OTHER, typeHandler=com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg},#{details, jdbcType=OTHER, typeHandler=com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg}, #{shop.id})")
    boolean insert(ProductDto productDto);



    //delete product
    @Delete("delete FROM dp_products WHERE pro_id =#{id}")
    void deleteProduct(String id);


    //update a product
    @Update("UPDATE dp_products set name = #{product.name}, price = #{product.price}, description= #{product.description} ,status = #{product.status}, is_sold = #{product.isSold}, view_count= #{product.viewCount} WHERE pro_id = #{id}")
    boolean updateProduct(String id, ProductDto product);


//    //find product by shop id
//    @Select("SELECT * FROM dp_products WHERE shop_id = 1")
//    @Results({
//            @Result(column = "shop_id" ,property = "shop",many = @Many(select = "selectOneShop")),
//    })
//    List<ProductDto> findProductByShopId(@Param("shopId") String shopId);
//
//    //select on Shop
//    @Select("SELECT * FROM dp_shops WHERE id=#{shopId}")
//    @Results({
//            @Result(column = "id" ,property = "id"),
//            @Result(column = "shop_id" ,property = "shopId"),
//            @Result(column = "name" ,property = "name"),
//            @Result(column = "address" ,property = "address")
//    })
//    ShopDto selectOneShop(String shopId);



    //Search product by shop
    @Select("SELECT * FROM dp_products WHERE shop_id=#{shopId}")
    @Results({
            @Result(column = "shop_id" ,property = "shop",many = @Many(select = "selectOneShop")),
    })
    List<ProductDto> findProductByShopId(@Param("shopId") int shopId);

    //select on shop
    @Select("SELECT * FROM dp_shops WHERE id = #{shop_id}")
    ShopDto selectOneShop(int shop_id);

    //find product by id
    @Select("SELECT * FROM dp_products WHERE pro_id = #{proId}")
    ProductDto findById(String proId);


}
