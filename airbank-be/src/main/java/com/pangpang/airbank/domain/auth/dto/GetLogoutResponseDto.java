package com.pangpang.airbank.domain.auth.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetLogoutResponseDto {
	private Long id;

	public static GetLogoutResponseDto from(Long id) {
		return GetLogoutResponseDto.builder()
			.id(id)
			.build();
	}
}
