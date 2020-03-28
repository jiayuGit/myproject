package com.example.demo.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmaillDto implements Serializable {
    private String emaill;
    private String name;
}
