package com.pangpang.airbank.global.common.api.nh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class CommonHeaderDto {
	@JsonProperty
	private String ApiNm;
	@JsonProperty
	private String Tsymd;
	@JsonProperty
	private String Trtm;
	@JsonProperty
	private String Iscd;
	@JsonProperty
	private String FintechApsno;
	@JsonProperty
	private String ApiSvcCd;
	@JsonProperty
	private String IsTuno;
	@JsonProperty
	private String AccessToken;
	@JsonProperty
	private String Rpcd;
	@JsonProperty
	private String Rsms;
	@JsonProperty
	private String Rgno;

	@Override
	public String toString() {
		return "CommonHeaderDto{" +
			"ApiNm='" + ApiNm + '\'' +
			", Tsymd='" + Tsymd + '\'' +
			", Trtm='" + Trtm + '\'' +
			", Iscd='" + Iscd + '\'' +
			", FintechApsno='" + FintechApsno + '\'' +
			", APISvcCd='" + ApiSvcCd + '\'' +
			", Istuno='" + IsTuno + '\'' +
			", AccessToken='" + AccessToken + '\'' +
			", Rpcd='" + Rpcd + '\'' +
			", Rsms='" + Rsms + '\'' +
			", Rgno='" + Rgno + '\'' +
			'}';
	}
}
