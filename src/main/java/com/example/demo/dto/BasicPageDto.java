package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicPageDto implements Serializable {
    private static final long serialVersionUID = 4769903663877886301L;
    private int startPage=1;
    private int pageSize=10;
}
