package com.bingle.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiResponseCode {

    OK("요청이 성공하였습니다."),
    BAD_PARAMETER("요청 파라미터가 잘못되었습니다."),
    DUPLICATED_ERROR("이미 등록된 리소스 입니다."),
    NOT_FOUND("리소스를 찾지 못했습니다."),
    UNAUTHORIZED("인증에 실패했습니다."),
    FORBIDDEN("접근 권한이 없습니다."),
    SERVER_ERROR("서버 에러입니다.");

    private final String message;

}
