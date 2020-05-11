package com.example.demo.enumerate;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlowEnum {

    WTJ(0, "未提交"),
    SPZ(1, "审批中"),
    SPTG(2, "审批通过"),
    SPBH(3, "审批驳回");

    private Integer code;
    private String name;

    public static String getNameByCode(Integer i) {
        FlowEnum[] values = FlowEnum.values();
        for (FlowEnum v : values) {
            if (v.getCode().equals(i)){
                return v.getName();
            }
        }
        return null;
    }
}
