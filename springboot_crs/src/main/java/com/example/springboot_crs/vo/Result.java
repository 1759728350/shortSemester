package com.example.springboot_crs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 统一结果json返回格式
 * @author Hedley
 * @date: 2022-06-24 20:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    //交互执行是否成功
    private boolean success;
    //状态码
    private int code;
    //错误信息
    private String msg;
    //返回数据
    private Object data;

    /**
     * @description:  成功返回
     * @param:  携带数据
     * @author Hedley
     * @date: 2022-06-24 20:53
     */
    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }
    /**
     * @description: 业务执行失败返回
     * @param: 错误码,错误信息
     * @author Hedley
     * @date: 2022-06-24 21:10
     */
    public static Result fail(Integer code,String msg){
        return new Result(false,code,msg,null);
    }

}
