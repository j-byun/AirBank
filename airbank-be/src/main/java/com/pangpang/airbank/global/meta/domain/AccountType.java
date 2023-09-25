package com.pangpang.airbank.global.meta.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pangpang.airbank.global.error.exception.MetaException;
import com.pangpang.airbank.global.error.info.MetaErrorInfo;

import lombok.Getter;

@Getter
public enum AccountType {
	MAIN_ACCOUNT(1, "MAIN_ACCOUNT"),
	LOAN_ACCOUNT(2, "LOAN_ACCOUNT"),
	SAVINGS_ACCOUNT(3, "SAVINGS_ACCOUNT");

	private final Integer id;
	private final String name;

	AccountType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@JsonCreator
	public static AccountType ofName(String name) {
		return Arrays.stream(AccountType.values())
			.filter(value -> value.getName().equals(name))
			.findAny()
			.orElseThrow(() -> new MetaException(MetaErrorInfo.INVALID_METADATA));
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
