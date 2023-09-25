package com.pangpang.airbank.domain.savings.dto;

import com.pangpang.airbank.domain.savings.domain.Savings;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PatchConfirmSavingsResponseDto {
	private Long id;
	private String status;

	public static PatchConfirmSavingsResponseDto from(Savings savings) {
		return PatchConfirmSavingsResponseDto.builder()
			.id(savings.getId())
			.status(savings.getStatus().getName())
			.build();
	}
}
