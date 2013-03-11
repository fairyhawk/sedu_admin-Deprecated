package com.shangde.common.exception;

public class CommException extends RuntimeException{
    public static String ERROR_CODE_KEY = "errCode";//错误信息存储key值
    public static String ERROR_RAND_CODE = "err.randCode";//验证码错误

    public CommException() {
        super();
    }

    public CommException(String message) {
        super(message);
    }

    public CommException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommException(Throwable cause) {
        super(cause);
    }
}
