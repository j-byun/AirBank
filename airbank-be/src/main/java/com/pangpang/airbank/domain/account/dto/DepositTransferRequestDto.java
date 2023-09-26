package com.pangpang.airbank.domain.account.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepositTransferRequestDto {
	private static final String NH_BANK_CODE = "011";

	private String bnod;
	private String acno;
	private String tram;
	private String mractOtlt;

	public static DepositTransferRequestDto of(TransferRequestDto transferRequestDto, String transactionIdentifier) {
		return DepositTransferRequestDto.builder()
			.bnod(NH_BANK_CODE)
			.acno(transferRequestDto.getReceiverAccount().getAccountNumber())
			.tram(transferRequestDto.getAmount().toString())
			.mractOtlt(transactionIdentifier)
			.build();
	}
}
