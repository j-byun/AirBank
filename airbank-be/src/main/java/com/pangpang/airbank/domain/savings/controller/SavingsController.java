package com.pangpang.airbank.domain.savings.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.airbank.domain.savings.dto.GetCurrentSavingsResponseDto;
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
				.data(savingsService.getCurrentSavings(member.getMemberId(), groupId))
				.build());
	}
}
