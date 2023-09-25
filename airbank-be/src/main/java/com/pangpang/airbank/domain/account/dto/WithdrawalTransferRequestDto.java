package com.pangpang.airbank.domain.account.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WithdrawalTransferRequestDto {
	private String finAcno;
	private String tram;
	private String dractOtlt;

	public static WithdrawalTransferRequestDto of(TransferRequestDto transferRequestDto, UUID transactionIdentifier) {
		return WithdrawalTransferRequestDto.builder()
			.finAcno(transferRequestDto.getSenderAccount().getFinAccountNumber())
			.tram(transferRequestDto.getAmount().toString())
			.dractOtlt(transactionIdentifier.toString())
			.build();
	}
}
