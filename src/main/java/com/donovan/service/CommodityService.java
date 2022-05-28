package com.donovan.service;

import com.donovan.dto.CommodityDTO;
import com.donovan.model.Commodity;

import java.util.List;

public interface CommodityService {

    List<Commodity> findAll();

    boolean save(CommodityDTO commodityDTO);
    Commodity findById(Integer id);
    boolean update(CommodityDTO commodityDTO);
    boolean updateState(CommodityDTO commodityDTO);

}
