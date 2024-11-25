package ec.com.dinersclub.partyAuthentication.infrastructure.inbound.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ec.com.dinersclub.partyAuthentication.application.create.TokenOtpCreateUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRq;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRs;
import ec.com.dinersclub.partyAuthentication.infrastructure.validation.ValidHeaders;
import ec.com.dinersclub.partyAuthentication.infrastructure.validation.ValidTokenRequest;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/v1/token")
public class TokenOtpController {

	private static final Logger log = LoggerFactory.getLogger(TokenOtpController.class.getName());

	private final TokenOtpCreateUseCase tokenOtpCreateUseCase;
	
	 @PostMapping("/evaluate")
	    public ResponseEntity<RecEvaluateTokenRs> tokenOtpValidate(@Valid @ValidTokenRequest @RequestBody RecEvaluateTokenRq request,
	    														   @Valid @ValidHeaders @RequestHeader HttpHeaders headers) {
	        log.info("Recibiendo solicitud para validar OTP request REST: {}, headers:{}",request,headers);
	        return new ResponseEntity<>( tokenOtpCreateUseCase.tokenOtpValidate(request, headers),HttpStatus.OK);
	    }

}
