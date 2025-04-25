package org.example.base;
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result() {}

    private Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> ok() {
        return new Result<>(0, "成功");
    }


    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "成功", data);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(-1, msg, null);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
