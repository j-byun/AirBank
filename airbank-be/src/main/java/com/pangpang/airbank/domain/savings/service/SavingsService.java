package com.pangpang.airbank.domain.savings.service;

import com.pangpang.airbank.domain.savings.dto.GetCurrentSavingsResponseDto;

public interface SavingsService {
	GetCurrentSavingsResponseDto getCurrentSavings(Long memberId, Long groupId);
}
