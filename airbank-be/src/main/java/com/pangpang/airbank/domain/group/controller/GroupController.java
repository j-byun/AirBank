package com.pangpang.airbank.domain.group.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.airbank.domain.group.dto.CommonFundManagementRequestDto;
import com.pangpang.airbank.domain.group.dto.CommonFundManagementResponseDto;
import com.pangpang.airbank.domain.group.dto.GetPartnersResponseDto;
import com.pangpang.airbank.domain.group.dto.PatchConfirmRequestDto;
import com.pangpang.airbank.domain.group.dto.PostEnrollChildRequestDto;
import com.pangpang.airbank.domain.group.service.GroupService;
import com.pangpang.airbank.global.common.response.EnvelopeResponse;
import com.pangpang.airbank.global.resolver.dto.AuthenticatedMemberArgument;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Slf4j
public class GroupController {
	private final GroupService groupService;

	@GetMapping()
	public ResponseEntity<EnvelopeResponse<GetPartnersResponseDto>> getPartners() {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(1L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<GetPartnersResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(groupService.getPartners(member.getMemberId()))
				.build());
	}

	@PostMapping()
	public ResponseEntity<EnvelopeResponse<Long>> enrollChild(
		@RequestBody PostEnrollChildRequestDto postEnrollChildRequestDto) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(1L);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(EnvelopeResponse.<Long>builder()
				.code(HttpStatus.CREATED.value())
				.data(groupService.enrollChild(member.getMemberId(), postEnrollChildRequestDto))
				.build());
	}

	@PatchMapping("/confirm")
	public ResponseEntity<EnvelopeResponse<Long>> confirmEnrollment(
		@RequestBody PatchConfirmRequestDto patchConfirmRequestDto, @RequestParam Long groupId) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(2L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<Long>builder()
				.code(HttpStatus.OK.value())
				.data(groupService.confirmEnrollment(member.getMemberId(), patchConfirmRequestDto, groupId))
				.build());
	}

	@PostMapping("/fund")
	public ResponseEntity<EnvelopeResponse<CommonFundManagementResponseDto>> saveFundManagement(
		@RequestBody CommonFundManagementRequestDto commonFundManagementRequestDto, @RequestParam() Long groupId) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(1L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<CommonFundManagementResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(null)
				.build());
	}

	@PatchMapping("/fund")
	public ResponseEntity<EnvelopeResponse<CommonFundManagementResponseDto>> updateFundManagement(
		@RequestBody CommonFundManagementRequestDto commonFundManagementRequestDto, @RequestParam() Long groupId) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(1L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<CommonFundManagementResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(groupService.updateFundManagement(member.getMemberId(), commonFundManagementRequestDto, groupId))
				.build());
	}
}
