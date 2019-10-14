package com.qf.examsys.common;

public class JsonReasult<T> {
    private Integer code;
    private T data;

    public JsonReasult() {
    }

    public JsonReasult(Integer code, T data) {
        this.code = code;
        this.data = data;
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
}
