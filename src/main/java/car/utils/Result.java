package car.utils;

import lombok.Data;

@Data
public class Result<T> {


    private boolean success;
    private String code;
    private String message;
    private T data;

    public Result(T object) {
        this.success = true;
        this.code = "0";
        this.message = "";
        this.data = object;
    }

    public Result(String code, String message) {
        this.success = false;
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public static <T> Result<T> ok(){
        return new Result<>(null);
    }

    public static <T> Result<T> ok(T t){
        return new Result<>(t);
    }

    public static <T> Result<T> failed(String code, String message){
        return new Result<>(code, message);
    }

    public static <T> Result<T> failed(T t){ return new Result<>(t); }
}
