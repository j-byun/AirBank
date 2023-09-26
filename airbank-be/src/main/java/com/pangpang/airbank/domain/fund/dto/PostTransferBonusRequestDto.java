package com.pangpang.airbank.domain.fund.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostTransferBonusRequestDto {
	private Long amount;
}