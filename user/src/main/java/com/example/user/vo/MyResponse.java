package com.example.user.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class MyResponse implements Serializable {
    private static final long serialVersionUID = -2L;
//    private static final long serialVersionUID = 1L;
    private String status;
    private String message;

    public MyResponse(String status, String message) {
        this.status=status;
        this.message=message;
    }
}
