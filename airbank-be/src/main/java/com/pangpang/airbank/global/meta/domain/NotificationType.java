package com.pangpang.airbank.global.meta.domain;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.pangpang.airbank.global.error.exception.MetaException;
import com.pangpang.airbank.global.error.info.MetaErrorInfo;

import lombok.Getter;

@Getter
public enum NotificationType {
	TAX(1, "TAX"),
	INTEREST(2, "INTEREST"),
	BONUS(3, "BONUS"),
	ALLOWANCE(4, "ALLOWANCE"),
	MISSION(5, "MISSION"),
	CONFISCATION(6, "CONFISCATION"),
	LOAN(7, "LOAN"),
	SAVINGS(8, "SAVINGS"),
	TAX_REFUND(9, "TAX_REFUND"),
	SAVINGS_CONFIRM(10, "SAVINGS_CONFIRM"),
	SAVINGS_REWARD_CONFIRM(11, "SAVINGS_REWARD_CONFIRM"),
	GROUP(12, "GROUP"),
	GROUP_CONFIRM(13, "GROUP_CONFIRM");

	private final Integer id;
	private final String name;

	NotificationType(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@JsonCreator
	public static NotificationType ofName(String name) {
		return Arrays.stream(NotificationType.values())
			.filter(value -> value.getName().equals(name))
			.findAny()
			.orElseThrow(() -> new MetaException(MetaErrorInfo.INVALID_METADATA));
	}

	@JsonValue
	public String getName() {
		return name;
	}
}
