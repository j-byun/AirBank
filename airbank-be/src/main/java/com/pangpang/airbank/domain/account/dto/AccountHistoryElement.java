package com.pangpang.airbank.domain.account.dto;

import java.time.LocalDateTime;

import com.pangpang.airbank.domain.member.domain.Member;
import com.pangpang.airbank.global.meta.domain.TransactionDistinction;
import com.pangpang.airbank.global.meta.domain.TransactionType;

import lombok.Getter;

@Getter
public class AccountHistoryElement {
	private Long amount;
	private LocalDateTime apiCreatedAt;
	private TransactionType transactionType;
	private TransactionDistinction transactionDistinction;
	private Member transactionPartner;

	public AccountHistoryElement(Long amount, LocalDateTime apiCreatedAt, TransactionType transactionType,
		TransactionDistinction transactionDistinction, Member transactionPartner) {
		this.amount = amount;
		this.apiCreatedAt = apiCreatedAt;
		this.transactionType = transactionType;
		this.transactionDistinction = transactionDistinction;
		this.transactionPartner = transactionPartner;
	}
}
