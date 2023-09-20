package com.pangpang.airbank.global.common.api.nh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CommonHeaderDto {
	private String ApiNm;
	private String Tsymd;
	private String Trtm;
	private String Iscd;
	private String FintechApsno;
	private String APISvcCd;
	private String Istuno;
	private String AccessToken;
	private String Rpcd;
	private String Rsms;
	private String Rgno;

	// @EventListener(CommonHeaderRequestDto.class)
	// public void initTime() {
	// 	SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");
	// 	SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
	// 	Date now = new Date();
	// 	this.Tsymd = dayFormatter.format(now);
	// 	this.Trtm = timeFormatter.format(now);
	// }
}
