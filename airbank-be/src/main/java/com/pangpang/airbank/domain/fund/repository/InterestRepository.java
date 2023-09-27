package com.pangpang.airbank.domain.fund.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pangpang.airbank.domain.fund.domain.Interest;

public interface InterestRepository extends JpaRepository<Interest, Long> {
	// expiredAt 기준 밀린 이자
	@Query("""
			SELECT sum(amount) FROM interest 
			WHERE group.id=:groupId AND activated=false AND expiredAt<:expiredAt AND billedAt<=:billedAt
		""")
	Long findOverAmountsByGroupIdAndActivatedFalseAndExpiredAtLessThanAndBilledAtLessThanEqual(Long groupId,
		LocalDate expiredAt, LocalDate billedAt);

	// 이번 달 이자
	Optional<Interest> findFirstByGroupIdAndActivatedFalseAndExpiredAtGreaterThanEqualAndBilledAtLessThanEqual(
		Long groupId,
		LocalDate expiredAt, LocalDate billedAt);
}
