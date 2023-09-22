package com.pangpang.airbank.global.common.api.nh;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangpang.airbank.domain.account.dto.PostEnrollAccountRequestDto;
import com.pangpang.airbank.global.common.api.nh.domain.NhApiManagement;
import com.pangpang.airbank.global.common.api.nh.dto.CommonHeaderDto;
import com.pangpang.airbank.global.common.api.nh.dto.GetFinAccountRequestDto;
import com.pangpang.airbank.global.common.api.nh.dto.GetFinAccountResponseDto;
import com.pangpang.airbank.global.common.api.nh.repository.NhApiManagementRepository;
import com.pangpang.airbank.global.common.api.nh.service.NhApiManagementService;

import lombok.RequiredArgsConstructor;

/**
 *  NH API 연동 함수
 *
 * @See NHApiConstantProvider
 * @See NhApiManagementRepository
 * @See ObjectMapper
 */
@Component
@RequiredArgsConstructor
public class NHApi {
	private final NhApiManagementService nhApiManagementService;
	private final NHApiConstantProvider nhApiConstantProvider;
	private final ObjectMapper objectMapper;

	/**
	 *  계좌 핀-어카운트 발급받는 API 호출
	 *
	 * @param postEnrollAccountRequestDto PostEnrollAccountRequestDto
	 * @return GetFinAccountResponseDto
	 * @see GetFinAccountRequestDto
	 */
	public GetFinAccountResponseDto getFinAccountDirect(PostEnrollAccountRequestDto postEnrollAccountRequestDto) throws
		JsonProcessingException {
		GetFinAccountRequestDto getFinAccountRequestDto = GetFinAccountRequestDto.builder()
			.header(
				getRequestHeader().toBuilder()
					.apiNm("OpenFinAccountDirect")
					.apiSvcCd("DrawingTransferA")
					.build()
			)
			.drtrRgyn("Y")
			.brdtBrno(nhApiConstantProvider.getBirth())
			.bncd(postEnrollAccountRequestDto.getBankCode())
			.acno(postEnrollAccountRequestDto.getAccountNumber())
			.build();

		String result = WebClient.create()
			.post()
			.uri(nhApiConstantProvider.getUrl() + "/OpenFinAccountDirect.nh")
			.header("Content-type", "application/json;charset=utf-8")
			.bodyValue(
				objectMapper.writeValueAsString(getFinAccountRequestDto))
			.retrieve()
			.bodyToMono(String.class)
			.block();

		return objectMapper.readValue(result, GetFinAccountResponseDto.class);
	}

	/**
	 * NH API의 Request 데이터 중 Header 셋팅하는 함수
	 *
	 * @return CommonHeaderDto
	 * @see NhApiManagement
	 * @see NhApiManagementRepository
	 */
	public CommonHeaderDto getRequestHeader() {
		SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
		Date now = new Date();

		return CommonHeaderDto.builder()
			.tsymd(dayFormatter.format(now))
			.trtm(timeFormatter.format(now))
			.iscd(nhApiConstantProvider.getIscd())
			.fintechApsno("001")
			.isTuno(nhApiManagementService.updateIsTuno().toString())
			.accessToken(nhApiConstantProvider.getAccessToken())
			.build();
	}

}
