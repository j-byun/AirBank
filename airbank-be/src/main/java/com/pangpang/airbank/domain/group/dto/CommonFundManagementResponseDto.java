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

	public static CommonFundManagementResponseDto of(PatchFundManagementRequestDto patchFundManagementRequestDto) {
		return CommonFundManagementResponseDto.builder()
			.taxRate(patchFundManagementRequestDto.getTaxRate())
			.allowanceAmount(patchFundManagementRequestDto.getAllowanceAmount())
			.allowanceDate(patchFundManagementRequestDto.getAllowanceDate())
			.confiscationRate(patchFundManagementRequestDto.getConfiscationRate())
			.loanLimit(patchFundManagementRequestDto.getLoanLimit())
			.build();
	}
}
