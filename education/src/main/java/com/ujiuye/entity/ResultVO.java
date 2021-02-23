package com.ujiuye.entity;

public class ResultVO{
    private int code;//响应状态码
    private String message;//消息
    private Object data;//数据

    public ResultVO() {
    }
    public ResultVO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
