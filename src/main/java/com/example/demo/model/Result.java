package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

/**
 * <p>结果返回</p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @Author phil
 * @Date Created in 2017年12月04日 20:39
 * @Version 1.0
 * @Since 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result implements Serializable {

    private static final long serialVersionUID = 3424573832145773945L;

    public final static int SUCCESS_CODE = 0;// 成功状态

    public final static int FAIL_CODE = 1;// 失败状态

    private final static String DEFAULT_ERROR_MESSAGE = "error";

    private final static String DEFAULT_MESSAGE = "success";

    private int code;

    private String message;

    private Object data;

    private Long currentTimestamp = Instant.now().toEpochMilli();

    public static Result ok(){
        return ok(DEFAULT_MESSAGE, null);
    }

    public static Result ok(Object data){
        return ok(DEFAULT_MESSAGE, data);
    }

    public static Result ok(String message, Object data){
        if (data instanceof Result){
            return (Result) data;
        }
        return Result.builder()
                .code(SUCCESS_CODE)
                .message(message)
                .data(data)
                .currentTimestamp(Instant.now().toEpochMilli())
                .build();
    }

    public static Result fail(){
        return fail(DEFAULT_ERROR_MESSAGE);
    }

    public static Result fail(Exception e){
        return fail(e.getMessage());
    }

    public static Result fail(String message){
        return Result.builder()
                .code(FAIL_CODE)
                .message(message)
                .data(null)
                .currentTimestamp(Instant.now().toEpochMilli())
                .build();
    }

    public static Result fail(ResultCode resultCode){
        return Result.builder()
                .code(resultCode.code)
                .message(resultCode.description)
                .data(null)
                .currentTimestamp(Instant.now().toEpochMilli())
                .build();
    }

    public static Result fail(int code, String message){
        return Result.builder()
                .code(code)
                .message(message)
                .data(null)
                .currentTimestamp(Instant.now().toEpochMilli())
                .build();
    }

    public Result setData(Object t) {
        this.data = t;
        return this;
    }

    @AllArgsConstructor
    public enum ResultCode {
        SUCCESS(0, "操作成功"),
        FAIL(1, "操作失败"),

        NEEDLOGIN(-1, "需要登录"),
        NOAUTHORITY(-2, "您没有权限");


        private int code;
        private String description;
    }
}
