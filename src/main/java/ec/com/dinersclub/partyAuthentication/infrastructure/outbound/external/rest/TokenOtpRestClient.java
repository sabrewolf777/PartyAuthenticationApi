package ec.com.dinersclub.partyAuthentication.infrastructure.outbound.external.rest;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRq;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRs;

public interface TokenOtpRestClient {
	public RecEvaluateTokenRs tokenOtpValidate(RecEvaluateTokenRq request, HttpHeaders headers);
}
