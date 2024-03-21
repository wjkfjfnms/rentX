package com.example.user.vo;

import com.example.user.enums.HttpStatusEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class RE {

    private Boolean success;

    private Integer code;

    private String message;

    private Map<String, Object> data = new HashMap<>();

    // 把构造方法私有化
    private RE() {}

    // 成功静态方法
    public static RE ok() {
        RE r = new RE();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    // 失败静态方法
    public static RE error() {
        RE r = new RE();
        r.setSuccess(false);
        r.setCode(20001);
        r.setMessage("失败");
        return r;
    }

    // 失败静态方法
    public static RE error(HttpStatusEnum httpStatus) {
        RE r = new RE();
        r.setSuccess(false);
        r.setCode(httpStatus.getCode());
        r.setMessage(httpStatus.getMsg());
        return r;
    }

    public RE success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    public RE message(String message){
        this.setMessage(message);
        return this;
    }

    public RE code(Integer code){
        this.setCode(code);
        return this;
    }

    public RE data(String key, Object value){
        this.data.put(key, value);
        return this;
    }

    public RE data(Map<String, Object> map){
        this.setData(map);
        return this;
    }

}

