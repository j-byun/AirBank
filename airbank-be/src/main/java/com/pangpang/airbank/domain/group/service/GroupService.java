package com.pangpang.airbank.domain.group.service;

import com.pangpang.airbank.domain.group.dto.CommonFundManagementRequestDto;
import com.pangpang.airbank.domain.group.dto.CommonFundManagementResponseDto;
import com.pangpang.airbank.domain.group.dto.GetPartnersResponseDto;
import com.pangpang.airbank.domain.group.dto.PatchConfirmRequestDto;
import com.pangpang.airbank.domain.group.dto.PostEnrollChildRequestDto;

public interface GroupService {
	GetPartnersResponseDto getPartners(Long memberId);

	Long enrollChild(Long memberId, PostEnrollChildRequestDto postEnrollChildRequestDto);

	Long confirmEnrollment(Long memberId, PatchConfirmRequestDto patchConfirmRequestDto, Long groupId);

	// CommonFundManagementResponseDto saveFundManagement(Long memberId,)

	CommonFundManagementResponseDto updateFundManagement(Long memberId,
		CommonFundManagementRequestDto commonFundManagementRequestDto, Long groupId);
}
