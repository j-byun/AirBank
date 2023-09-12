package com.pangpang.airbank.global.meta.exception;

import lombok.Getter;

@Getter
public enum MetaErrorInfo {
	INVALID_METADATA("???", "유효하지 않은 메타데이터 입니다.");

	private final String code;
	private final String message;

	MetaErrorInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}
}
