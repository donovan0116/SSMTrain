package com.donovan.dao;

import com.donovan.model.Commodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityDao {

    /*
     *查找所有未被删除的商品，被删除的商品状态为0，其余为1
     */

    List<Commodity> findAll();

    void save(Commodity commodity);

    Commodity findById(Integer id);

    void update(@Param("commodity") Commodity commodity);

    void updateState(@Param("commodity") Commodity commodity);

}
