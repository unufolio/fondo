package com.unufolio.fondo.common;

import com.unufolio.fondo.common.enums.IResultCode;
import com.unufolio.fondo.common.enums.ResultCodeEnum;

import java.util.Date;

/**
 * @author Unufolio unufolio@gmail.com
 * @since 2021/05/25
 */
public class ResultEntity<T> {

    protected Boolean isSuccess;
    protected String code;
    protected String message;
    protected T data;
    protected Date timestamp;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    private ResultEntity() {
    }

    public ResultEntity(boolean success, String code, String message, T data, Date timestamp) {
        this.isSuccess = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = new Date();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        protected boolean success;
        protected String code;
        protected String message;
        protected Date timestamp;

        public Builder success(boolean success) {
            this.success = success;
            return this;
        }

        public Builder code(String code) {
            this.code = code;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public <T> ResultEntity<T> data(T data) {
            return new ResultEntity<>(success, code, message, data, timestamp);
        }

        public <T> ResultEntity<T> build() {
            return this.data(null);
        }
    }

    public static <T> ResultEntity<T> success() {
        return ResultEntity.builder()
                .success(true)
                .build();
    }

    public static <T> ResultEntity<T> success(T data) {
        return ResultEntity.builder()
                .success(true)
                .data(data);
    }

    public static <T> ResultEntity<T> error() {
        return ResultEntity.builder()
                .success(false)
                .code(ResultCodeEnum.ERROR.value())
                .message(ResultCodeEnum.ERROR.message())
                .build();
    }

    public static <T> ResultEntity<T> error(String code, String message) {
        return ResultEntity.builder()
                .success(false)
                .code(code)
                .message(message)
                .build();
    }

    public static <T> ResultEntity<T> error(IResultCode resultCode) {
        return ResultEntity.builder()
                .success(false)
                .code(resultCode.value())
                .message(resultCode.message())
                .build();
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "isSuccess=" + isSuccess +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
