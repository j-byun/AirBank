package com.pangpang.airbank.global.meta.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MetaException extends RuntimeException {
	private final MetaErrorInfo info;
}
