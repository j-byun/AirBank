package com.pangpang.airbank.domain.account.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pangpang.airbank.domain.account.domain.AccountHistory;
import com.pangpang.airbank.domain.account.dto.SaveWithdrawalHistoryRequestDto;
import com.pangpang.airbank.domain.account.repository.AccountHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountHistoryServiceImpl implements AccountHistoryService {
	private final AccountHistoryRepository accountHistoryRepository;

	@Override
	public UUID saveWithdrawalHistory(SaveWithdrawalHistoryRequestDto saveWithdrawalHistoryRequestDto) {
		AccountHistory accountHistory = AccountHistory.of(saveWithdrawalHistoryRequestDto);
		accountHistoryRepository.save(accountHistory);

		return accountHistory.getTransactionIdentifier();
	}
}
