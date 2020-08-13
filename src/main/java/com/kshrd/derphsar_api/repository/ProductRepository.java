package com.kshrd.derphsar_api.repository;

import com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg;
import com.kshrd.derphsar_api.page.Pagination;
import com.kshrd.derphsar_api.repository.dto.*;
import com.kshrd.derphsar_api.repository.provider.ProductProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {

    //get all products
    // @SelectProvider(value = ProductProvider.class, method = "getProducts")
    @Select("SELECT * FROM dp_products WHERE status = 'true' LIMIT #{pagination.limit}  OFFSET #{pagination.offset}")
    @Results(id = "mapProduct", value = {
            @Result(column = "pro_id" ,property = "proId"),
            @Result(column = "is_sold" ,property = "soldStatus"),
            @Result(column = "view_count" ,property = "viewCount"),
            @Result(column = "post_date", property = "postDate"),
            @Result(column = "details" ,property = "details", jdbcType = JdbcType.OTHER, typeHandler = JSONTypeHandlerPg.class),
            @Result(column = "images" ,property = "images", jdbcType = JdbcType.OTHER, typeHandler = JSONTypeHandlerPg.class),
            @Result(column = "shop_id", property = "shop", many = @Many(select = "getShop"))
    })
    List<ProductDto> getProducts(@Param("pagination") Pagination pagination);


    //select all shops
    @Select("SELECT * FROM dp_shops WHERE id=#{shop_id} AND status = 'true'")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "shop_id" ,property = "shopId"),
            @Result(column = "name" ,property = "name"),
            @Result(column = "address" ,property = "address"),
            @Result(column = "u_id", property = "user", many = @Many(select = "getUser")),
            @Result(column = "cat_id", property = "category", many = @Many(select = "getCategory")),
            @Result(column = "promo_id", property = "promotion", many = @Many(select = "getPromotion"))
    })
    ShopDto getShop(int shop_id);


    @Select("SELECT * FROM dp_users WHERE id = #{user_id}  AND status = 'true'")
    @Results({
            @Result(column = "user_id", property = "userId")
    })
    UserDto getUser(int user_id);


    @Select("SELECT * FROM dp_category WHERE id= #{cate_id}")
    @Results({
            @Result(column = "cat_id", property = "catId")
    })
    CategoryDto getCategory(int cate_id);


    @Select("SELECT * FROM dp_promotion WHERE id= #{promo_id}  AND status = 'true'")
    @Results({
            @Result(column = "promo_id", property = "promoId"),
            @Result(column = "is_apply", property = "isApply"),
            @Result(column = "end_date", property = "endDate"),
            @Result(column = "start_date", property = "startDate"),
            @Result(column = "start_rank", property = "startRank"),
            @Result(column = "end_rank", property = "endRank"),
    })
    PromotionDto getPromotion(int promo_id);



    //create product
    @Insert("INSERT INTO dp_products (pro_id, name, price, description, status, is_sold, view_count, images , details, shop_id)" +
            "VALUES ( #{proId}, #{name, jdbcType=VARCHAR}, #{price}, #{description}, #{status}, #{soldStatus}, #{viewCount},#{images, jdbcType=OTHER, typeHandler=com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg},#{details, jdbcType=OTHER, typeHandler=com.kshrd.derphsar_api.mybatis.JSONTypeHandlerPg}, #{shop.id})")
    boolean insert(ProductDto productDto);



    //delete product
    @Delete("delete FROM dp_products WHERE pro_id =#{id}")
    void deleteProduct(String id);


    //update a product
    @Update("UPDATE dp_products set name = #{product.name}, price = #{product.price}, description= #{product.description} ,status = #{product.status}, is_sold = #{product.soldStatus}, view_count= #{product.viewCount} WHERE pro_id = #{id}")
    boolean updateProduct(String id, ProductDto product);


    //Search product by shop
    @Select("SELECT * FROM dp_products WHERE shop_id=#{shopId} AND status = 'true' LIMIT #{pagination.limit}  OFFSET #{pagination.offset}")
    @Results({
            @Result(column = "shop_id" ,property = "shop",many = @Many(select = "selectOneShop")),
    })
    List<ProductDto> findProductByShopId(@Param("shopId") int shopId, @Param("pagination") Pagination pagination);



    //select on shop
    @Select("SELECT * FROM dp_shops WHERE id = #{shop_id}")
    ShopDto selectOneShop(int shop_id);



    //find product by id
    @Select("SELECT * FROM dp_products WHERE pro_id = #{proId}")
    ProductDto findById(String proId);


    @SelectProvider(type = ProductProvider.class, method = "countId")
    int countId();




    @Select("SELECT * FROM dp_products AS pro\n" +
            "WHERE status = 'true'\n" +
            "ORDER BY pro.post_date DESC\n" +
            "LIMIT 12 ")
        @ResultMap("mapProduct")
    List<ProductDto> getNewProducts();



    @Select("SELECT * FROM dp_products AS pro\n" +
            "WHERE status = 'true'\n" +
            "ORDER BY pro.view_count DESC\n" +
            "LIMIT 12")
        @ResultMap("mapProduct")
    List<ProductDto> getPopularProducts();

}
