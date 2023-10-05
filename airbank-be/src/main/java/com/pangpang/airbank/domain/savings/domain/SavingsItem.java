package com.pangpang.airbank.domain.savings.domain;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.pangpang.airbank.domain.savings.dto.PostSaveSavingsRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity(name = "savings_item")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class SavingsItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 255)
	@NotNull
	@Column
	private String name;

	@NotNull
	@Column
	private Long amount;

	@Size(max = 255)
	@Column
	private String imageUrl;

	@NotNull
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "savings_id", foreignKey = @ForeignKey(name = "fk_savings_item_to_savings_savings_id"))
	private Savings savings;

	@Lob
	@Column(length = 100000000)
	private byte[] imageFile;

	public static SavingsItem of(Savings savings, PostSaveSavingsRequestDto postSaveSavingsRequestDto,
		MultipartFile file) {
		byte[] imageFile = null;
		try {
			imageFile = file.getBytes();
		} catch (IOException e) {
			log.debug(String.format("아이템 이미지 파일 변환 실패: %s"), e.getMessage());
		}

		return SavingsItem.builder()
			.name(postSaveSavingsRequestDto.getName())
			.amount(postSaveSavingsRequestDto.getAmount())
			.imageUrl(postSaveSavingsRequestDto.getImageUrl())
			.savings(savings)
			.imageFile(imageFile)
			.build();
	}
}
