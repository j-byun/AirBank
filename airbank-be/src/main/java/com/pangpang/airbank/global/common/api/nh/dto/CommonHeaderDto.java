package com.pangpang.airbank.global.common.api.nh.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
@JsonNaming(value = PropertyNamingStrategy.PascalCaseStrategy.class)
public class CommonHeaderDto {
	private String apiNm;
	private String tsymd;
	private String trtm;
	private String iscd;
	private String fintechApsno;
	private String apiSvcCd;
	private String isTuno;
	private String accessToken;
	private String rpcd;
	private String rsms;
	private String rgno;
}
