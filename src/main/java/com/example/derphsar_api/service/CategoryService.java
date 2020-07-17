package com.example.derphsar_api.service;

import com.example.derphsar_api.repository.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> select();
}
