package com.example.demo.enumerate;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FlowNodeEnum {

    WKS(0,"未开始"),
    DSP(1,"待审批"),
    SPTG(2,"审批通过"),
    SPBH(3,"审批驳回");

    private Integer code;
    private String name;
    public static String getNameByCode(Integer i) {
        FlowNodeEnum[] values = FlowNodeEnum.values();
        for (FlowNodeEnum v : values) {
            if (v.getCode().equals(i)){
                return v.getName();
            }
        }
        return null;
    }
}
