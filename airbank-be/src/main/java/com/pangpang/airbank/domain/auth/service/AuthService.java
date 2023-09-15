package com.pangpang.airbank.domain.auth.service;

import com.pangpang.airbank.domain.member.dto.PostLoginRequestDto;

import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
	void sendRedirectUrl(HttpServletResponse response);
	String getKakaoAccessToken(String code);
	PostLoginRequestDto getKakaoProfile(String accessToken);
}
