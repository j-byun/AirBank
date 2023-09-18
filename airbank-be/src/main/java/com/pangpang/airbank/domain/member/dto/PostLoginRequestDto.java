package com.pangpang.airbank.domain.member.dto;

import lombok.Getter;

@Getter
public class PostLoginRequestDto {
	private String id;
	private KakaoAccount kakao_account;

	@Getter
	public class KakaoAccount {
		private Profile profile;

		@Getter
		public class Profile {
			private String profile_image_url;
			private Boolean is_default_image;
		}
	}
}
