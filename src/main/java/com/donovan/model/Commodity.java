package com.donovan.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Commodity implements Serializable {

    private Integer id;
    private String name;
    private String type;
    private String comment;
    private String state;

}
