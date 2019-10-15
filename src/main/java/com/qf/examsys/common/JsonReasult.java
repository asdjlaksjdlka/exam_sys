package com.qf.examsys.common;

public class JsonReasult<T> {
    private Integer code;
    private T data;
    private String msg;
    private Long count;
    private Integer num;

    public JsonReasult(Integer code, Integer num) {
        this.code = code;
        this.num = num;
    }

    public JsonReasult() {
    }

    public JsonReasult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public JsonReasult(Integer code, T data, String msg, Long count) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.count = count;
    }


    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
