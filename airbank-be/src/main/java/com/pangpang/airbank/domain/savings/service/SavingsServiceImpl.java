package com.pangpang.airbank.domain.savings.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pangpang.airbank.domain.savings.domain.Savings;
import com.pangpang.airbank.domain.savings.domain.SavingsItem;
import com.pangpang.airbank.domain.savings.dto.GetCurrentSavingsResponseDto;
import com.pangpang.airbank.domain.savings.repository.SavingsItemRepository;
import com.pangpang.airbank.domain.savings.repository.SavingsRepository;
import com.pangpang.airbank.global.error.exception.SavingsException;
import com.pangpang.airbank.global.error.info.SavingsErrorInfo;
import com.pangpang.airbank.global.meta.domain.SavingsStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavingsServiceImpl implements SavingsService {
	private final SavingsRepository savingsRepository;
	private final SavingsItemRepository savingsItemRepository;

	/**
	 *  현재 진행중인 티끌모으기 정보를 조회하는 메소드
	 *
	 * @param memberId Long
	 * @param groupId Long
	 * @return GetCurrentSavingsResponseDto
	 * @see SavingsRepository
	 * @see SavingsItemRepository
	 */
	@Transactional(readOnly = true)
	@Override
	public GetCurrentSavingsResponseDto getCurrentSavings(Long memberId, Long groupId) {
		Savings savings = savingsRepository.findByGroupIdAndStatusEquals(groupId, SavingsStatus.PROCEEDING)
			.orElseThrow(() -> new SavingsException(SavingsErrorInfo.NOT_FOUND_SAVINGS_IN_PROCEEDING));

		SavingsItem savingsItem = savingsItemRepository.findBySavings(savings)
			.orElseThrow(() -> new SavingsException(SavingsErrorInfo.NOT_FOUND_SAVINGS_ITEM));
		return GetCurrentSavingsResponseDto.of(savings, savingsItem);
	}
}
