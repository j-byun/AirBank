package com.pangpang.airbank.domain.loan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.airbank.domain.loan.dto.GetLoanResponseDto;
import com.pangpang.airbank.domain.loan.service.LoanService;
import com.pangpang.airbank.global.common.response.EnvelopeResponse;
import com.pangpang.airbank.global.resolver.dto.AuthenticatedMemberArgument;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/loans")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "loans", description = "땡겨쓰기 API")
public class LoanController {

	private final LoanService loanService;

	/**
	 *  땡겨쓰기 조회
	 *
	 * @param groupId Long
	 * @return ResponseEntity<EnvelopeResponse < GetLoanResponseDto>>
	 * @see LoanService
	 */
	// @CheckGroup
	@GetMapping()
	public ResponseEntity<EnvelopeResponse<GetLoanResponseDto>> getLoan(@RequestParam("group_id") Long groupId) {
		AuthenticatedMemberArgument member = new AuthenticatedMemberArgument(2L);

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<GetLoanResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(loanService.getLoan(member.getMemberId(), groupId))
				.build());
	}
}
