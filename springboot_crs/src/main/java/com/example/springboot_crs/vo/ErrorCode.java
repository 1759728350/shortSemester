package com.example.springboot_crs.vo;
/**
 * @description:  枚举错误种类
 * @param:
 * @author Hedley
 * @date: 2022-06-25 0:14
 */
public enum ErrorCode {

    QUERY_RESULT_IS_EMPTY(3001, "查询结果不存在"),
    PARAMS_ERROR(3002,"参数错误"),
    NETWORK_EXCEPTION(3003,"网络异常")
    ;

    private int code;
    private String msg;

    /**
     * @description:  有参构造,无无参构造方式
     * @param:
     * @author Hedley
     * @date: 2022-06-24 23:08
     */
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
