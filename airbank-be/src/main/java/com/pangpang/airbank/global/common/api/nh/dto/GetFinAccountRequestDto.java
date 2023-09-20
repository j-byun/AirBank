package com.pangpang.airbank.global.common.api.nh.dto;

import org.springframework.beans.factory.annotation.Value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class GetFinAccountRequestDto {
	private CommonHeaderDto commonHeaderDto;
	private String DrtrRgyn = "Y";
	@Value("${nhapi.birth}")
	private String BrdtBrno;
	private String Bncd;
	private String Acno;
}
