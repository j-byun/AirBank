package com.pangpang.airbank.global.common.api.nh.domain;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.pangpang.airbank.domain.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "nh_api_management")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE NhApiManagement SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class NhApiManagement extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 30)
	@NotNull
	@Column
	private Long isTuno;

	public Long update() {
		return ++id;
	}
}
