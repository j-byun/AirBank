package com.pangpang.airbank.domain.group.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pangpang.airbank.domain.group.domain.MemberRelationship;

@Repository
public interface MemberRelationshipRepository extends JpaRepository<MemberRelationship, Long> {
	@Query("select m from member_relationship m "
		+ "join fetch m.child c "
		+ "where m.parent.id = :parentId and m.activated = true ")
	List<MemberRelationship> findAllByParentIdWithChildAsActive(@Param("parentId") Long parentId);

	@Query("select m from member_relationship m "
		+ "join fetch m.parent c "
		+ "where m.child.id = :childId and m.activated = true ")
	List<MemberRelationship> findAllByChildIdWithParentAsActive(@Param("childId") Long childId);

	Optional<MemberRelationship> findByIdAndChildId(Long id, Long childId);

	Optional<MemberRelationship> findByChildId(Long childId);

	Optional<MemberRelationship> findByIdAndParentId(Long id, Long parentId);

	@Query("select case when count(m) > 0 then true else false end from member_relationship m "
		+ "where m.id = :id and (m.parent.id = :partnerId or (m.child.id = :partnerId and m.activated = true))")
	Boolean existsByIdAndPartnerId(Long id, Long partnerId);
}
