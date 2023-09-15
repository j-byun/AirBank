package com.pangpang.airbank.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetLoginResponseDto {
	private String name;
	private String phoneNumber;

	public static GetLoginResponseDto of(String name, String phoneNumber) {
		return GetLoginResponseDto.builder()
			.name(name)
			.phoneNumber(phoneNumber)
			.build();
	}
}
