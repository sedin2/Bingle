package com.bingle.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ApiResponseDto<T> {

    public static final ApiResponseDto<String> DEFAULT_OK = new ApiResponseDto<>(ApiResponseCode.OK);

    private ApiResponseCode code;

    private String message;

    private T data;

    private ApiResponseDto(ApiResponseCode status) {
        this.bindStatus(status);
    }

    private ApiResponseDto(ApiResponseCode code, T data) {
        this.bindStatus(code);
        this.data = data;
    }

    private ApiResponseDto(ApiResponseCode code, String name, T data) {
        this.code = code;
        this.message = code.getMessage();
        this.data = (T) bindData(name, data);
    }

    private void bindStatus(ApiResponseCode status) {
        this.code = status;
        this.message = status.getMessage();
        this.data = (T) "";
    }

    private Map<String, T> bindData(String name, T data) {
        return Collections.singletonMap(name, data);
    }

    public static <T> ApiResponseDto<T> OK(T data) {
        return new ApiResponseDto<>(ApiResponseCode.OK, data);
    }

    public static <T> ApiResponseDto<T> OK(String name, T data) {
        return new ApiResponseDto<>(ApiResponseCode.OK, name, data);
    }

    public static <T> ApiResponseDto<T> ERROR(ApiResponseCode code, T data) {
        return new ApiResponseDto<>(code, data);
    }
}
