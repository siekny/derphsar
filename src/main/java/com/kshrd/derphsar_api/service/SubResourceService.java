package com.kshrd.derphsar_api.service;

import com.kshrd.derphsar_api.repository.dto.ShopDto;

import java.util.List;

public interface SubResourceService {

    List<ShopDto> getAllShopsByUserId (int userId);
}
