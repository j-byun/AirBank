package com.pangpang.airbank.domain.group.service;

import com.pangpang.airbank.domain.group.dto.CommonFundManagementResponseDto;
import com.pangpang.airbank.domain.group.dto.GetPartnersResponseDto;
import com.pangpang.airbank.domain.group.dto.PatchConfirmRequestDto;
import com.pangpang.airbank.domain.group.dto.PatchFundManagementRequestDto;
import com.pangpang.airbank.domain.group.dto.PostEnrollChildRequestDto;

public interface GroupService {
	GetPartnersResponseDto getPartners(Long memberId);

	Long enrollChild(Long memberId, PostEnrollChildRequestDto postEnrollChildRequestDto);

	Long confirmEnrollment(Long memberId, PatchConfirmRequestDto patchConfirmRequestDto, Long groupId);

	CommonFundManagementResponseDto updateFundManagement(Long memberId,
		PatchFundManagementRequestDto patchFundManagementRequestDto, Long groupId);
}
