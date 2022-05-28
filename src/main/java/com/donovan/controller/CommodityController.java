package com.donovan.controller;

import com.donovan.dto.CommodityDTO;
import com.donovan.model.Commodity;
import com.donovan.service.CommodityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/commodity")
public class CommodityController {

    @Resource
    private CommodityService commodityService;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Commodity> commodityList = commodityService.findAll();
        //向下一个页面传递对象键值对，其中“commodities”是键。
        model.addAttribute("commodityList", convertToList(commodityList));
        return "commodityManage";

    }

    @GetMapping("/in")
    public String commodityIn(Model model) {
        return "commodityIn";
    }

    @GetMapping("/out")
    public String commodityOut(@RequestParam("id") String id, Model model) {
        Commodity commodity = commodityService.findById(Integer.valueOf(id));
        model.addAttribute("commodity", convertToDTO(commodity));
        return "commodityOut";
    }

    @GetMapping("/updateInfo")
    public String updateInfo(@RequestParam("id") String id, Model model) {
        Commodity commodity =  commodityService.findById(Integer.valueOf(id));
        model.addAttribute("commodity", convertToDTO(commodity));
        return "updateInfo";
    }

    //保存商品信息
    @PostMapping(value = "/save")
    public String save(@RequestBody CommodityDTO commodityDTO){
        commodityService.save(commodityDTO);
        return "commodityManage";
    }

    //更新用户信息
    @PostMapping(value = "/update")
    public String update(@RequestBody CommodityDTO commodityDTO) {
        commodityService.update(commodityDTO);
        return "commodityManage";
    }

    //更新用户状态
    @PostMapping(value = "/updateState")
    public String updateState(@RequestBody CommodityDTO commodityDTO) {
        commodityService.updateState(commodityDTO);
        return "commodityManage";
    }

    private List<CommodityDTO> convertToList(List<Commodity> commodityList) {
        if (commodityList.isEmpty()) {
            return null;
        }

        List<CommodityDTO> commodityDTOList = new ArrayList<>();
        for (Commodity commodity : commodityList) {
            commodityDTOList.add(convertToDTO(commodity));
        }
        return commodityDTOList;
    }

    private CommodityDTO convertToDTO(Commodity commodity) {
        CommodityDTO commodityDTO = new CommodityDTO();

        commodityDTO.setId(String.valueOf(commodity.getId()));
        commodityDTO.setName(commodity.getName());
        commodityDTO.setType(commodity.getType());
        commodityDTO.setComment(commodity.getComment());

        if (commodity.getState().equals("1")) {
            commodityDTO.setState("出售中");
        } else if (commodity.getState().equals("0")) {
            commodityDTO.setState("已删除");
        }

        return commodityDTO;

    }

}
