package com.example.derphsar_api.rest.category.restcontroller;

import com.example.derphsar_api.service.CategoryService;
import com.example.derphsar_api.service.implement.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryRestController {
    CategoryServiceImp categoryServiceImp;

    @Autowired
    public void setCategoryService(CategoryServiceImp categoryServiceImp) {
        this.categoryServiceImp = categoryServiceImp;
    }

    @GetMapping("/category")
    public ResponseEntity  findAll(){
        return ResponseEntity.ok(categoryServiceImp.select());
    }
}
