package ec.com.dinersclub.partyAuthentication.application.create;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRq;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRs;
import ec.com.dinersclub.partyAuthentication.infrastructure.domain.repository.TokenOtpCommandRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenOtpCreateUseCase {

	TokenOtpCommandRepository tokenOtpCommandRepository;
	
	public RecEvaluateTokenRs tokenOtpValidate(RecEvaluateTokenRq request,HttpHeaders headers) {
		return tokenOtpCommandRepository.tokenOtpValidate(request, headers);
	}
}
