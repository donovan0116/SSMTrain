package com.donovan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommodityDTO implements Serializable {

    //序号
    private String id;
    //商品名称
    private String name;
    //商品类型
    private String type;
    //商品描述
    private String comment;
    //商品状态，0代表被删除了的，1代表未被删除
    private String state;

}
