package com.pangpang.airbank.domain.member.service;

import org.springframework.stereotype.Service;

import com.pangpang.airbank.domain.auth.dto.GetLoginResponseDto;
import com.pangpang.airbank.domain.member.domain.Member;
import com.pangpang.airbank.domain.member.dto.GetMemberResponseDto;
import com.pangpang.airbank.domain.member.dto.PostLoginRequestDto;
import com.pangpang.airbank.domain.member.repository.MemberRepository;
import com.pangpang.airbank.global.error.exception.MemberException;
import com.pangpang.airbank.global.error.info.MemberErrorInfo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberRepository memberRepository;

	/*
	 사용자 조회
	 */
	@Override
	public GetMemberResponseDto getMember(Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorInfo.NOT_FOUND_MEMBER));

		return GetMemberResponseDto.from(member);
	}
	
	/*
	카카오 식별자로 사용자 조회
	 */
	@Override
	public GetLoginResponseDto getMemberByOauthIdentifier(PostLoginRequestDto postLoginRequestDto) {
		Member member = memberRepository.findByOauthIdentifier(postLoginRequestDto.getId())
			.orElse(saveMember(postLoginRequestDto));


		return null;
	}

	/*
	회원가입
	*/
	@Override
	public GetLoginResponseDto saveMember(PostLoginRequestDto postLoginRequestDto) {

		return null;
	}

}
