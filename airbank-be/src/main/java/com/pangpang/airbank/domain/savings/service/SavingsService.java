package com.pangpang.airbank.domain.savings.service;

import com.pangpang.airbank.domain.group.dto.CommonIdResponseDto;
import com.pangpang.airbank.domain.savings.dto.GetCurrentSavingsResponseDto;
import com.pangpang.airbank.domain.savings.dto.PatchConfirmSavingsRequestDto;
import com.pangpang.airbank.domain.savings.dto.PatchConfirmSavingsResponseDto;
import com.pangpang.airbank.domain.savings.dto.PostSaveSavingsRequestDto;

public interface SavingsService {
	GetCurrentSavingsResponseDto getCurrentSavings(Long groupId);

	CommonIdResponseDto saveSavings(Long memberId, PostSaveSavingsRequestDto postSaveSavingsRequestDto);

	PatchConfirmSavingsResponseDto confirmEnrollmentSavings(Long memberId,
		PatchConfirmSavingsRequestDto patchConfirmSavingsRequestDto,
		Long groupId);
}
