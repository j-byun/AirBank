package com.pangpang.airbank.domain.group.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommonFundManagementResponseDto {
	private Integer taxRate;
	private Long allowanceAmount;
	private Integer allowanceDate;
	private Integer confiscationRate;
	private Long loanLimit;

	public static CommonFundManagementResponseDto from(CommonFundManagementRequestDto commonFundManagementRequestDto) {
		return CommonFundManagementResponseDto.builder()
			.taxRate(commonFundManagementRequestDto.getTaxRate())
			.allowanceAmount(commonFundManagementRequestDto.getAllowanceAmount())
			.allowanceDate(commonFundManagementRequestDto.getAllowanceDate())
			.confiscationRate(commonFundManagementRequestDto.getConfiscationRate())
			.loanLimit(commonFundManagementRequestDto.getLoanLimit())
			.build();
	}
}
