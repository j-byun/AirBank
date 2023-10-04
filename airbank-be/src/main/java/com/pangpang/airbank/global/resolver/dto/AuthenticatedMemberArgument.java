package com.pangpang.airbank.global.resolver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(accessMode = Schema.AccessMode.READ_ONLY)
public class AuthenticatedMemberArgument {
	private Long memberId;

}
