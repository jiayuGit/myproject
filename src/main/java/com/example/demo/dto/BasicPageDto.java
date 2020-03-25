package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BasicPageDto implements Serializable {
    private Integer startPage;
    private Integer pageSize;
}
