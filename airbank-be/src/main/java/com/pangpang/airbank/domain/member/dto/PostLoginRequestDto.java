package com.pangpang.airbank.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostLoginRequestDto {
	private String id;
	private KakaoAccount kakao_account;

	@Getter
	@NoArgsConstructor
	public class KakaoAccount {
		private Profile profile;

		@Getter
		@NoArgsConstructor
		public class Profile {
			private String profile_image_url;
			private boolean is_default_image;
		}
	}
}
