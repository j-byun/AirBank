package com.pangpang.airbank.domain.savings.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pangpang.airbank.domain.group.domain.Group;
import com.pangpang.airbank.domain.group.dto.CommonIdResponseDto;
import com.pangpang.airbank.domain.group.repository.GroupRepository;
import com.pangpang.airbank.domain.member.domain.Member;
import com.pangpang.airbank.domain.member.repository.MemberRepository;
import com.pangpang.airbank.domain.savings.domain.Savings;
import com.pangpang.airbank.domain.savings.domain.SavingsItem;
import com.pangpang.airbank.domain.savings.dto.GetCurrentSavingsResponseDto;
import com.pangpang.airbank.domain.savings.dto.PostSaveSavingsRequestDto;
import com.pangpang.airbank.domain.savings.repository.SavingsItemRepository;
import com.pangpang.airbank.domain.savings.repository.SavingsRepository;
import com.pangpang.airbank.global.error.exception.GroupException;
import com.pangpang.airbank.global.error.exception.MemberException;
import com.pangpang.airbank.global.error.exception.SavingsException;
import com.pangpang.airbank.global.error.info.GroupErrorInfo;
import com.pangpang.airbank.global.error.info.MemberErrorInfo;
import com.pangpang.airbank.global.error.info.SavingsErrorInfo;
import com.pangpang.airbank.global.meta.domain.MemberRole;
import com.pangpang.airbank.global.meta.domain.SavingsStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavingsServiceImpl implements SavingsService {
	private final SavingsRepository savingsRepository;
	private final SavingsItemRepository savingsItemRepository;
	private final GroupRepository groupRepository;
	private final MemberRepository memberRepository;

	/**
	 *  현재 진행중인 티끌모으기 정보를 조회하는 메소드
	 *
	 * @param groupId Long
	 * @return GetCurrentSavingsResponseDto
	 * @see SavingsRepository
	 * @see SavingsItemRepository
	 */
	@Transactional(readOnly = true)
	@Override
	public GetCurrentSavingsResponseDto getCurrentSavings(Long groupId) {
		Savings savings = savingsRepository.findByGroupIdAndStatusEquals(groupId, SavingsStatus.PROCEEDING)
			.orElseThrow(() -> new SavingsException(SavingsErrorInfo.NOT_FOUND_SAVINGS_IN_PROCEEDING));

		SavingsItem savingsItem = savingsItemRepository.findBySavings(savings)
			.orElseThrow(() -> new SavingsException(SavingsErrorInfo.NOT_FOUND_SAVINGS_ITEM));
		return GetCurrentSavingsResponseDto.of(savings, savingsItem);
	}

	/**
	 *  티끌모으기를 생성하는 메소드, 자녀만 생성할 수 있음.
	 *
	 * @param memberId Long
	 * @param postSaveSavingsRequestDto PostSaveSavingsRequestDto
	 * @return CommonIdResponseDto
	 * @see MemberRepository
	 * @see GroupRepository
	 * @see SavingsRepository
	 * @see SavingsItemRepository
	 */
	@Transactional
	@Override
	public CommonIdResponseDto saveSavings(Long memberId, PostSaveSavingsRequestDto postSaveSavingsRequestDto) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorInfo.NOT_FOUND_MEMBER));

		if (!member.getRole().getName().equals(MemberRole.CHILD.getName())) {
			throw new SavingsException(SavingsErrorInfo.ENROLL_SAVINGS_PERMISSION_DENIED);
		}

		Group group = groupRepository.findByChildId(member.getId())
			.orElseThrow(() -> new GroupException(GroupErrorInfo.NOT_FOUND_GROUP_BY_CHILD_ID));

		Savings savings = Savings.of(group, postSaveSavingsRequestDto);
		SavingsItem savingsItem = SavingsItem.of(savings, postSaveSavingsRequestDto);

		savingsRepository.save(savings);
		savingsItemRepository.save(savingsItem);
		return new CommonIdResponseDto(savings.getId());
	}
}
