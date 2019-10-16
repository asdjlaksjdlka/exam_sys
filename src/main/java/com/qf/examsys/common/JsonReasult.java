package com.qf.examsys.common;

public class JsonReasult<T> {
    private Integer code;
    private T data;// 1 正常 0 异常
    private String msg;
    private Integer count;

    public JsonReasult() {
    }

    public JsonReasult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public JsonReasult(Integer code, T data, String msg, Integer count) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.count = count;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "JsonReasult{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                '}';
    }
}
