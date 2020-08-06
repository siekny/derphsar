package com.example.derphsar_api.repository;


import com.example.derphsar_api.page.Pagination;
import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.repository.dto.PromotionDto;
import com.example.derphsar_api.repository.dto.ShopDto;
import com.example.derphsar_api.repository.provider.PromotionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository {


    //get all promotion
    @Select("SELECT * FROM dp_promotions WHERE status = 'true' LIMIT #{pagination.limit}  OFFSET #{pagination.offset}")
    @Results({
            @Result(column = "promo_id" ,property = "promoId"),
            @Result(column = "is_apply" ,property = "isApply"),
            @Result(column = "start_date" ,property = "startDate"),
            @Result(column = "end_date" ,property = "endDate"),
            @Result(column = "pro_id" ,property = "proId")
//           @Result(column = "pro_id", property = "product", many = @Many(select = "getProduct"))
    })
    List<PromotionDto> getPromotions(@Param("pagination") Pagination pagination);



    //select one product
    @Select("SELECT * FROM dp_products WHERE id=#{pro_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "pro_id" ,property = "proId"),
            @Result(column = "pro_name" ,property = "name"),
            @Result(column = "pro_price" ,property = "price")
    })
    ProductDto getProduct(int pro_id);


    //delete promotions
    @Delete("UPDATE dp_promotions SET status = 'false' where promo_id = #{promoId}")
    void deletePromotion(String id);


    //update a promotions
    @Update("UPDATE dp_promotions SET title = #{promotion.title}, is_apply = #{promotion.isApply}, start_date= #{promotion.startDate} ,end_date = #{promotion.endDate},start_rank = #{promotion.startRank}, end_rank= #{promotion.endRank},cover = #{promotion.cover}, status = #{promotion.status}  WHERE promo_id = #{id}")
    boolean updatePromotion(String id, PromotionDto promotion);


    //create a promotion
    @Insert("INSERT INTO dp_promotions(promo_id, title, is_apply, rank, start_date, end_date, status, pro_id) " +
            "VALUES (#{promoId}, #{title}, #{isApply}, #{rank}, #{startDate}, #{endDate}, #{status},#{pro_id}")
    boolean createPromotion(PromotionDto promotionDto);

    //Search promotion by shop id
    @Select("SELECT * FROM dp_promotions WHERE shop_id=#{shopId}")
    @Results({
            @Result(column = "shop_id" ,property = "shop",many = @Many(select = "selectOneShop")),
    })
    List<PromotionDto> findPromotionByShopId(@Param("shopId") int shopId);

    //select on shop
    @Select("SELECT * FROM dp_shops WHERE id = #{shop_id}")
    ShopDto selectOneShop(int shop_id);

    //find product by id
    @Select("SELECT * FROM dp_promotions WHERE promo_id = #{promoId}")
    PromotionDto findById(String promoId);

    //count all shops
    @Select("SELECT COUNT(id) FROM dp_promotions WHERE status = 'true'")
    int countId();
}
