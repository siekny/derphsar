package com.kshrd.derphsar_api.rest.restcontroller;

import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.rest.BaseApiResponse;
import com.kshrd.derphsar_api.rest.category.request.CategoryRequestModel;
import com.kshrd.derphsar_api.service.implement.CategoryServiceImp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CategoryRestController {

    CategoryServiceImp categoryServiceImp;

    @Autowired
    public void setCategoryService(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }


    @GetMapping("/categories")
    public ResponseEntity<BaseApiResponse<List<CategoryRequestModel>>> getCategories(){

        ModelMapper mapper = new ModelMapper();
        BaseApiResponse<List<CategoryRequestModel>> response =
                new BaseApiResponse<>();

        List<CategoryDto> categoryDtoList = categoryServiceImp.select();
        List<CategoryRequestModel> articles = new ArrayList<>();

        for (CategoryDto categoryDto : categoryDtoList) {
            articles.add(mapper.map(categoryDto, CategoryRequestModel.class));
        }

        response.setMessage("You have found all categories successfully");
        response.setData(articles);
        response.setStatus(HttpStatus.FOUND);
        response.setTime(new Timestamp(System.currentTimeMillis()));

        return ResponseEntity.ok(response);

    }
}