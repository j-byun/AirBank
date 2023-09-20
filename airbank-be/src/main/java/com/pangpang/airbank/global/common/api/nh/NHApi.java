package com.pangpang.airbank.global.common.api.nh;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.pangpang.airbank.domain.account.dto.PostEnrollAccountRequestDto;
import com.pangpang.airbank.global.common.api.nh.domain.NhApiManagement;
import com.pangpang.airbank.global.common.api.nh.dto.CommonHeaderDto;
import com.pangpang.airbank.global.common.api.nh.dto.GetFinAccountRequestDto;
import com.pangpang.airbank.global.common.api.nh.dto.GetFinAccountResponseDto;
import com.pangpang.airbank.global.common.api.nh.repository.NhApiManagementRepository;
import com.pangpang.airbank.global.error.exception.AccountException;
import com.pangpang.airbank.global.error.info.AccountErrorInfo;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NHApi {
	private final NHApiConstantProvider nhApiConstantProvider;
	private final NhApiManagementRepository nhApiManagementRepository;

	public GetFinAccountResponseDto getFinAccountDirect(PostEnrollAccountRequestDto postEnrollAccountRequestDto) {
		// MultiValueMap<String, String> params = setParameters(code);
		try {
			// GetFinAccountResponseDto getFinAccountResponseDto = GetFinAccountResponseDto.builder()
			// 	.commonHeaderDto(getRequestHeader().getApiNm("DrawingTransfer"))
			// 	.build();

			return WebClient.create()
				.post()
				.uri(nhApiConstantProvider.getUrl())
				.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
				.bodyValue(GetFinAccountRequestDto.builder()
					.Bncd(postEnrollAccountRequestDto.getBankCode())
					.Acno(postEnrollAccountRequestDto.getAccountNumber())
				)
				.retrieve()
				.bodyToMono(GetFinAccountResponseDto.class)
				.block();
		} catch (Exception e) {
			throw new AccountException(AccountErrorInfo.ACCOUNT_SERVER_ERROR);
		}
	}

	@Transactional
	public CommonHeaderDto getRequestHeader() {
		SimpleDateFormat dayFormatter = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeFormatter = new SimpleDateFormat("HHmmss");
		Date now = new Date();
		NhApiManagement nhApiManagement = nhApiManagementRepository.findById(1L)
			.orElseThrow(() -> new AccountException(AccountErrorInfo.ACCOUNT_REQUEST_DATA_ERROR));

		return CommonHeaderDto.builder()
			.Tsymd(dayFormatter.format(now))
			.Trtm(timeFormatter.format(now))
			.Iscd(nhApiConstantProvider.getIscd())
			.FintechApsno("001")
			.Istuno(nhApiManagement.update().toString())
			.AccessToken(nhApiConstantProvider.getAccessToken())
			.build();
	}

	/**
	 *  토큰 발급에 필요한 파라미터 세팅
	 *
	 * @param code String
	 * @return map 형식으로 세팅된 파라미터
	 *
	 * {
	 *     "Header": {
	 *         "ApiNm": "OpenFinAccountDirect",
	 *         "Tsymd": "20191129",
	 *         "Trtm": "113604",
	 *         "Iscd": "900001",
	 *         "FintechApsno": "001",
	 *         "ApiSvcCd": "DrawingTransferA",
	 *         "IsTuno": "201911290000000001",
	 *         "AccessToken": "6500e3a81fe2deafd996ea437b6a4b7cfbd04a3ab7e26480b431fdf3ccf3b39b"
	 *     },
	 *     "DrtrRgyn": "Y",
	 *     "BrdtBrno": "20191029",
	 *     "Bncd": "011",
	 *     "Acno": "3020000000039"
	 * }
	 */
	// private MultiValueMap<String, String> setParameters(String code) {
	// 	MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	//
	// 	params.add("grant_type", "authorization_code");
	// 	params.add("client_id", authConstantProvider.getClientId());
	// 	params.add("client_secret", authConstantProvider.getClientSecret());
	// 	params.add("redirect_uri", authConstantProvider.getRedirectUri());
	// 	params.add("code", code);
	//
	// 	return params;
	// }
}
