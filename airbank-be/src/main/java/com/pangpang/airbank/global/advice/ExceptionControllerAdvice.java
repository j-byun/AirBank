package com.pangpang.airbank.global.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pangpang.airbank.global.common.response.EnvelopeResponse;
import com.pangpang.airbank.global.meta.exception.MetaException;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	@ExceptionHandler(MetaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public EnvelopeResponse metaExceptionHandler(MetaException exception) {
		return EnvelopeResponse.builder()
			.code(exception.getInfo().getCode())
			.message(exception.getInfo().getMessage())
			.build();
	}

}
