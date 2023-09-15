package com.pangpang.airbank.domain.member.service;

import com.pangpang.airbank.domain.auth.dto.GetLoginResponseDto;
import com.pangpang.airbank.domain.member.dto.GetMemberResponseDto;
import com.pangpang.airbank.domain.member.dto.PostLoginRequestDto;

public interface MemberService {

	GetMemberResponseDto getMember(Long memberId);
	GetLoginResponseDto getMemberByOauthIdentifier(PostLoginRequestDto postLoginRequestDto);
	GetLoginResponseDto saveMember(PostLoginRequestDto postLoginRequestDto);
}
