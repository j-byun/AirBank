package com.pangpang.airbank.domain.group.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.pangpang.airbank.domain.group.domain.Group;
import com.pangpang.airbank.domain.member.domain.Member;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPartnersResponseDto {
	private List<PartnerElement> members;

	public static GetPartnersResponseDto of(List<Group> groups, Member member) {
		return GetPartnersResponseDto.builder()
			.members(groups.stream()
				.map(group -> PartnerElement.of(group,
					group.getPartnerMember(member)))
				.collect(Collectors.toList()))
			.build();
	}
}
