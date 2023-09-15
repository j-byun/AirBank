package com.pangpang.airbank.domain.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pangpang.airbank.domain.auth.dto.GetLoginRequestDto;
import com.pangpang.airbank.domain.auth.dto.GetLoginResponseDto;
import com.pangpang.airbank.domain.auth.dto.GetLogoutResponseDto;
import com.pangpang.airbank.domain.auth.service.AuthService;
import com.pangpang.airbank.domain.member.dto.GetMemberResponseDto;
import com.pangpang.airbank.domain.member.dto.PostLoginRequestDto;
import com.pangpang.airbank.domain.member.service.MemberService;
import com.pangpang.airbank.global.common.response.EnvelopeResponse;
import com.pangpang.airbank.global.resolver.Authentication;
import com.pangpang.airbank.global.resolver.dto.AuthenticatedMemberArgument;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final MemberService memberService;

	/*
	auth별 로그인 창 출력
	 */
	@GetMapping("/login")
	public void login(HttpServletResponse response) {
		authService.sendRedirectUrl(response);
	}

	/*
	인가 코드로 콜백
	토큰 발급 및 사용자 정보 조회
	 */
	@GetMapping("/callback")
	public ResponseEntity<EnvelopeResponse<GetLoginResponseDto>> login(HttpServletRequest request, @RequestParam GetLoginRequestDto getLoginRequestDto){
		String accessToken = authService.getKakaoAccessToken(getLoginRequestDto.getCode());
		PostLoginRequestDto postLoginRequestDto = authService.getKakaoProfile(accessToken);
		GetLoginResponseDto getLoginResponseDto = memberService.getMemberByOauthIdentifier(postLoginRequestDto);

		HttpSession session = request.getSession();
		session.setAttribute("memberId", "1");
	}

	/**
	 *  회원 로그아웃 요청을 처리한다.
	 *
	 * @return 로그아웃한 사용자의 id를 반환한다.
	 */
	@GetMapping("/logout")
	public ResponseEntity<EnvelopeResponse<GetLogoutResponseDto>> logout(HttpServletRequest request,
		@Authentication AuthenticatedMemberArgument authenticatedMemberArgument) {

		request.getSession().invalidate();

		return ResponseEntity.ok()
			.body(EnvelopeResponse.<GetLogoutResponseDto>builder()
				.code(HttpStatus.OK.value())
				.data(GetLogoutResponseDto.from(authenticatedMemberArgument.getMemberId()))
				.build());
	}

}
