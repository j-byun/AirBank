package com.pangpang.airbank.domain.account.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pangpang.airbank.domain.account.dto.SaveWithdrawalHistoryRequestDto;
import com.pangpang.airbank.domain.account.dto.TransferRequestDto;
import com.pangpang.airbank.domain.account.dto.WithdrawalTransferRequestDto;
import com.pangpang.airbank.global.common.api.nh.NHApi;
import com.pangpang.airbank.global.error.exception.AccountException;
import com.pangpang.airbank.global.error.info.AccountErrorInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
	private final NHApi nhApi;
	private final AccountHistoryService accountHistoryService;

	@Override
	@Transactional
	public void transfer(TransferRequestDto transferRequestDto) {
		withdraw(transferRequestDto);
	}

	private void withdraw(TransferRequestDto transferRequestDto) {

		UUID transactionIdentifier = accountHistoryService.saveWithdrawalHistory(
			SaveWithdrawalHistoryRequestDto.from(transferRequestDto));

		try {
			nhApi.withdrawalTransfer(WithdrawalTransferRequestDto.of(transferRequestDto, transactionIdentifier));
		} catch (Exception e) {
			throw new AccountException(AccountErrorInfo.ACCOUNT_NH_SERVER_ERROR);
		}
	}
}
