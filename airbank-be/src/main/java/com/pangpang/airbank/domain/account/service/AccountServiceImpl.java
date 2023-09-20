package com.pangpang.airbank.domain.account.service;

import org.springframework.stereotype.Service;

import com.pangpang.airbank.domain.account.dto.CommonAccountIdResponseDto;
import com.pangpang.airbank.domain.account.dto.PostEnrollAccountRequestDto;
import com.pangpang.airbank.domain.account.repository.AccountRepository;
import com.pangpang.airbank.domain.member.domain.Member;
import com.pangpang.airbank.domain.member.repository.MemberRepository;
import com.pangpang.airbank.global.common.api.nh.NHApi;
import com.pangpang.airbank.global.error.exception.MemberException;
import com.pangpang.airbank.global.error.info.MemberErrorInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {
	private final MemberRepository memberRepository;
	private final AccountRepository accountRepository;
	private final NHApi nhApi;

	@Override
	public CommonAccountIdResponseDto saveAccount(PostEnrollAccountRequestDto postEnrollAccountRequestDto,
		Long memberId) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorInfo.NOT_FOUND_MEMBER));

		// nhApi.getFinAccountDirect(PostEnrollAccountRequestDto postEnrollAccountRequestDto);

		return null;
	}
}
