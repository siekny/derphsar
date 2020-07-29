package com.example.derphsar_api.repository;


import com.example.derphsar_api.repository.dto.ProductDto;
import com.example.derphsar_api.repository.dto.PromotionDto;
import com.example.derphsar_api.repository.provider.PromotionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository {


    //get all promotion
    @SelectProvider(value = PromotionProvider.class, method = "getPromotions")
    @Results({
            @Result(column = "promo_id" ,property = "promoId"),
            @Result(column = "is_apply" ,property = "isApply"),
            @Result(column = "start_date" ,property = "startDate"),
            @Result(column = "end_date" ,property = "endDate"),
            @Result(column = "start_rank" ,property = "startRank"),
            @Result(column = "end_rank" ,property = "endRank"),
            @Result(column = "pro_id", property = "product", many = @Many(select = "getProduct"))
    })
    List<PromotionDto> getPromotions();



    //select all product
    @Select("SELECT * FROM dp_products WHERE id=#{pro_id}")
    @Results({
            @Result(column = "id" ,property = "id"),
            @Result(column = "pro_id" ,property = "proId"),
            @Result(column = "pro_name" ,property = "proName"),
            @Result(column = "pro_price" ,property = "proPrice")
    })
    ProductDto getProduct(int pro_id);


    //delete promotions
    @Delete("delete FROM dp_promotion where promo_id =#{id}")
    void deletePromotion(String id);


    //update a promotions
    @Update("UPDATE dp_promotion set title = #{promotion.title}, is_apply = #{promotion.isApply}, start_date= #{promotion.startDate} ,end_date = #{promotion.endDate},start_rank = #{promotion.startRank}, end_rank= #{promotion.endRank},cover = #{promotion.cover}, status = #{promotion.status}  WHERE promo_id = #{id}")
    boolean updatePromotion(String id, PromotionDto promotion);


}
