package com.pangpang.airbank.domain.member.dto;

import com.pangpang.airbank.domain.member.domain.Member;
import com.pangpang.airbank.global.meta.domain.MemberRole;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetMemberResponseDto {
	private String name;
	private String phoneNumber;
	private Integer creditScore;
	private String imageUrl;
	private MemberRole role;

	public static GetMemberResponseDto from(Member member) {
		return GetMemberResponseDto.builder()
			.name(member.getName())
			.phoneNumber(member.getPhoneNumber())
			.creditScore(member.getCreditScore())
			.imageUrl(member.getImageUrl())
			.role(member.getRole())
			.build();
	}
}
