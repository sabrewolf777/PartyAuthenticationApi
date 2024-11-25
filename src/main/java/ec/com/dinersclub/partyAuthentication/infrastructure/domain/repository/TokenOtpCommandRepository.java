package ec.com.dinersclub.partyAuthentication.infrastructure.domain.repository;

import org.springframework.http.HttpHeaders;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRq;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRs;

public interface TokenOtpCommandRepository {
	public RecEvaluateTokenRs tokenOtpValidate(RecEvaluateTokenRq request,HttpHeaders headers);
}
