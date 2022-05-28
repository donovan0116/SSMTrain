package com.donovan.service.impl;

import com.donovan.dao.CommodityDao;
import com.donovan.dto.CommodityDTO;
import com.donovan.model.Commodity;
import com.donovan.service.CommodityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityDao commodityDao;

    @Override
    public List<Commodity> findAll() {
        return commodityDao.findAll();
    }

    @Override
    public boolean save(CommodityDTO commodityDTO) {
        commodityDTO.setState("出售中");
        commodityDao.save(convert(commodityDTO));
        return true;
    }

    @Override
    public Commodity findById(Integer id) {
        return commodityDao.findById(id);
    }

    @Override
    public boolean update(CommodityDTO commodityDTO) {

        Commodity commodity = convert(commodityDTO);
        commodityDao.update(commodity);
        return true;
    }

    @Override
    public boolean updateState(CommodityDTO commodityDTO) {
        commodityDao.updateState(convert(commodityDTO));
        return true;
    }

    private Commodity convert(CommodityDTO commodityDTO) {

        Commodity commodity = new Commodity();

        if (commodityDTO.getId() != null) {
            commodity.setId(Integer.valueOf(commodityDTO.getId()));
        }
        commodity.setName(commodityDTO.getName());
        commodity.setType(commodityDTO.getType());
        commodity.setComment(commodityDTO.getComment());
        if (commodityDTO.getState().equals("出售中")) {
            commodity.setState("1");
        } else if (commodityDTO.getState().equals("已删除")) {
            commodity.setState("0");
        }

        return commodity;
    }
}
