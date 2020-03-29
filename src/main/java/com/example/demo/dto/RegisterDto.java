package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable {
    private String emaill;
    private String pwd;
    private String authCode;
}
