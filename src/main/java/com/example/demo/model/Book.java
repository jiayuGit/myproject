package com.example.demo.model;

import com.example.demo.dto.BasicPageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    private String id;
    private String name;
}
