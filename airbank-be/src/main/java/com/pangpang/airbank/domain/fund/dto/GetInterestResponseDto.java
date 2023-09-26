package com.pangpang.airbank.domain.fund.dto;

import java.time.LocalDate;

import com.pangpang.airbank.domain.fund.domain.Interest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetInterestResponseDto {
	private Long amount;
	private Long overdueAmount;
	private LocalDate expiredAt;

	public static GetInterestResponseDto of(Interest interest, Long overdueAmount) {
		return GetInterestResponseDto.builder()
			.amount(interest.getAmount() == null ? 0L : interest.getAmount())
			.overdueAmount(overdueAmount == null ? 0L : overdueAmount)
			.expiredAt(interest.getExpiredAt())
			.build();
	}
}
