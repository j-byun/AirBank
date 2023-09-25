package com.pangpang.airbank.domain.savings.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.airbank.domain.group.dto.CommonIdResponseDto;
import com.pangpang.airbank.domain.savings.dto.GetCurrentSavingsResponseDto;
import com.pangpang.airbank.domain.savings.dto.PatchCancelSavingsRequestDto;
import com.pangpang.airbank.domain.savings.dto.PatchCommonSavingsResponseDto;
import com.pangpang.airbank.domain.savings.dto.PatchConfirmSavingsRequestDto;
import com.pangpang.airbank.domain.savings.dto.PostSaveSavingsRequestDto;
import com.pangpang.airbank.domain.savings.service.SavingsService;
import com.pangpang.airbank.global.common.response.EnvelopeResponse;
import com.pangpang.airbank.global.resolver.dto.AuthenticatedMemberArgument;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/savings")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "savings", description = "티끌모으기 API")
public class SavingsController {

	private final SavingsService savingsService;

	/**
	 *  티끌모으기 현재 내역 조회
	 *
	 * @param groupId Long
	 * @return ResponseEntity<EnvelopeResponse < GetCurrentSavingsResponseDto>>
	 * @see SavingsService
	 */
	@GetMapping("/current")
	public ResponseEntity<EnvelopeResponse<GetCurrentSavingsResponseDto>> getCurrentSavings(
		@RequestParam("group_id") Long groupId) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(2L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<GetCurrentSavingsResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(savingsService.getCurrentSavings(groupId))
				.build());
	}

	/**
	 *  티끌모으기 생성
	 *
	 * @param postSaveSavingsRequestDto PostSaveSavingsRequestDto
	 * @return ResponseEntity<EnvelopeResponse < CommonIdResponseDto>>
	 * @see SavingsService
	 */
	@PostMapping("/item")
	public ResponseEntity<EnvelopeResponse<CommonIdResponseDto>> saveSavings(
		@RequestBody PostSaveSavingsRequestDto postSaveSavingsRequestDto) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(2L);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(EnvelopeResponse.<CommonIdResponseDto>builder()
				.code(HttpStatus.CREATED.value())
				.data(savingsService.saveSavings(member.getMemberId(), postSaveSavingsRequestDto))
				.build());
	}

	/**
	 *  티끌모으기 요청 수락/거절
	 *
	 * @param patchConfirmSavingsRequestDto PatchConfirmSavingsRequestDto
	 * @param groupId Long
	 * @return ResponseEntity<EnvelopeResponse < PatchConfirmSavingsResponseDto>>
	 * @see SavingsService
	 */
	@PatchMapping("/confirm")
	public ResponseEntity<EnvelopeResponse<PatchCommonSavingsResponseDto>> confirmEnrollmentSavings(
		@RequestBody PatchConfirmSavingsRequestDto patchConfirmSavingsRequestDto,
		@RequestParam("group_id") Long groupId) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(1L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<PatchCommonSavingsResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(savingsService.confirmEnrollmentSavings(member.getMemberId(), patchConfirmSavingsRequestDto,
					groupId))
				.build());
	}

	@PatchMapping("/cancel")
	public ResponseEntity<EnvelopeResponse<PatchCommonSavingsResponseDto>> cancelSavings(
		@RequestBody PatchCancelSavingsRequestDto patchCancelSavingsRequestDto) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(2L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<PatchCommonSavingsResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(savingsService.cancelSavings(member.getMemberId(), patchCancelSavingsRequestDto))
				.build());
	}
}
