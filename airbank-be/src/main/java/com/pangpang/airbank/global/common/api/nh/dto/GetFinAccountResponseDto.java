package com.pangpang.airbank.global.common.api.nh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetFinAccountResponseDto {
	private CommonHeaderDto commonHeaderDto;
	private String Rgno;
}
