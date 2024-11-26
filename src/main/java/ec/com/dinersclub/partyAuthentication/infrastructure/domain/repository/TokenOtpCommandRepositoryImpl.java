package ec.com.dinersclub.partyAuthentication.infrastructure.domain.repository;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRq;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRs;
import ec.com.dinersclub.partyAuthentication.infrastructure.outbound.external.rest.TokenOtpRestClient;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TokenOtpCommandRepositoryImpl implements TokenOtpCommandRepository {

	private final TokenOtpRestClient tokenOtpRestClient;
	
	@Override
	public RecEvaluateTokenRs tokenOtpValidate(RecEvaluateTokenRq request, HttpHeaders headers) {
		return tokenOtpRestClient.tokenOtpValidate(request, headers);
	}

}
