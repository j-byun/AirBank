package com.pangpang.airbank.global.common.api.nh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GetFinAccountResponseDto {
	@JsonProperty
	private CommonHeaderDto Header;
	@JsonProperty
	private String Rgno;
}
