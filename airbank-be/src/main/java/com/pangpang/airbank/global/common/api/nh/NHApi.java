package com.pangpang.airbank.global.common.api.nh;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangpang.airbank.domain.account.dto.PostEnrollAccountRequestDto;
import com.pangpang.airbank.global.common.api.nh.domain.NhApiManagement;
import com.pangpang.airbank.global.common.api.nh.dto.CommonHeaderDto;
import com.pangpang.airbank.global.common.api.nh.dto.GetFinAccountRequestDto;
import com.pangpang.airbank.global.common.api.nh.dto.GetFinAccountResponseDto;
import com.pangpang.airbank.global.common.api.nh.repository.NhApiManagementRepository;
import com.pangpang.airbank.global.error.exception.AccountException;
import com.pangpang.airbank.global.error.info.AccountErrorInfo;

import lombok.RequiredArgsConstructor;

/**
 *  NH API 연동 함수
 *
 * @See NHApiConstantProvider
 * @See NhApiManagementRepository
 * @See ObjectMapper
 */
@Service
@RequiredArgsConstructor
public class NHApi {
	private final NHApiConstantProvider nhApiConstantProvider;
	private final NhApiManagementRepository nhApiManagementRepository;
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
			.Header(
				getRequestHeader().toBuilder()
					.ApiNm("OpenFinAccountDirect")
					.ApiSvcCd("DrawingTransferA")
					.build()
			)
			.DrtrRgyn("Y")
			.BrdtBrno(nhApiConstantProvider.getBirth())
			.Bncd(postEnrollAccountRequestDto.getBankCode())
			.Acno(postEnrollAccountRequestDto.getAccountNumber())
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
		NhApiManagement nhApiManagement = nhApiManagementRepository.findById(1L)
			.orElseThrow(() -> new AccountException(AccountErrorInfo.ACCOUNT_REQUEST_DATA_ERROR));

		nhApiManagement.updateIsTuno();
		nhApiManagementRepository.save(nhApiManagement);

		return CommonHeaderDto.builder()
			.Tsymd(dayFormatter.format(now))
			.Trtm(timeFormatter.format(now))
			.Iscd(nhApiConstantProvider.getIscd())
			.FintechApsno("001")
			.IsTuno(nhApiManagement.getIsTuno().toString())
			.AccessToken(nhApiConstantProvider.getAccessToken())
			.build();
	}

}
