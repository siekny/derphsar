package com.kshrd.derphsar_api.service.implement;

import com.kshrd.derphsar_api.repository.CategoryRepository;
import com.kshrd.derphsar_api.repository.dto.CategoryDto;
import com.kshrd.derphsar_api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@ComponentScan("com.example.derphsar_api.repository")
public class CategoryServiceImp  implements CategoryService {

    public CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> select() {
        return categoryRepository.select();
    }
}
