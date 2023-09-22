package com.pangpang.airbank.global.error.info;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum SavingsErrorInfo {
	NOT_FOUND_SAVINGS_IN_PROCEEDING(HttpStatus.NOT_FOUND, 1800, "진행 중인 티끌모으기를 찾을 수 없습니다."),
	NOT_FOUND_SAVINGS_ITEM(HttpStatus.NOT_FOUND, 1801, "티끌모으기 상품 정보를 찾을 수 없습니다."),
	ENROLL_SAVINGS_PERMISSION_DENIED(HttpStatus.FORBIDDEN, 1802, "티끌모으기는 자녀만 등록할 수 있습니다.");

	private final HttpStatus status;
	private final Integer code;
	private final String message;

	SavingsErrorInfo(HttpStatus status, Integer code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
}
