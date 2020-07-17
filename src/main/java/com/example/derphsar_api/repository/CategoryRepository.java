package com.example.derphsar_api.repository;

import com.example.derphsar_api.repository.dto.CategoryDto;
import com.example.derphsar_api.repository.provider.CategoryProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @SelectProvider(type = CategoryProvider.class, method = "select")
    public List<CategoryDto> select();
}
