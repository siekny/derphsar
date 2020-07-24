package com.example.derphsar_api.service;

import com.example.derphsar_api.repository.dto.ShopDto;

import java.util.List;

public interface ShopService {

    ShopDto insert(ShopDto shop);
    List<ShopDto> select();
}
