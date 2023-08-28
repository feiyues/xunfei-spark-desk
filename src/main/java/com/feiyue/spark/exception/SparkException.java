package com.feiyue.spark.exception;

/**
 * @author feiyue
 */
public class SparkException extends RuntimeException{
    private static final long serialVersionUID = 8366868170166172535L;

    private Integer code;

    public SparkException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SparkException(Integer code, String message, Throwable t) {
        super(message, t);
        this.code = code;
    }

}
