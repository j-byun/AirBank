package com.pangpang.airbank.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetLoginResponseDto {
	private String name;
	private String phoneNumber;

	public GetLoginResponseDto(String name, String phoneNumber) {
		this.name = getName(name);
		this.phoneNumber = getPhoneNumber(phoneNumber);
	}

	private String getName(String name) {
		return (name == null) ? "" : name;
	}

	private String getPhoneNumber(String phoneNumber) {
		return (phoneNumber == null) ? "" : phoneNumber;
	}
}
