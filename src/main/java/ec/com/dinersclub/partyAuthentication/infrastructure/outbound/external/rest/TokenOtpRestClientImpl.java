package ec.com.dinersclub.partyAuthentication.infrastructure.outbound.external.rest;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ec.com.dinersclub.partyAuthentication.domain.model.DinBody;
import ec.com.dinersclub.partyAuthentication.domain.model.DinHeader;
import ec.com.dinersclub.partyAuthentication.domain.model.DinOtpRq;
import ec.com.dinersclub.partyAuthentication.domain.model.DinOtpRs;
import ec.com.dinersclub.partyAuthentication.domain.model.Paginado;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRq;
import ec.com.dinersclub.partyAuthentication.domain.model.RecEvaluateTokenRs;
import ec.com.dinersclub.partyAuthentication.domain.model.Tag;

@Component
public class TokenOtpRestClientImpl implements TokenOtpRestClient{

    private static final Logger log = LoggerFactory.getLogger(TokenOtpRestClientImpl.class.getName());
    
	@Value("${msd.seg.otp.api.url}")
	String apiUrl;
	
	public RecEvaluateTokenRs tokenOtpValidate(RecEvaluateTokenRq request, HttpHeaders headers) {
		
		final RestTemplate restTemplate= new RestTemplate();
		
		final HttpHeaders hdrs = new HttpHeaders();
		
		final DinOtpRq reqDinners= getDinnerRequest(request,headers);
		
		log.info(" Request Dinners: apiUrl: {},params: {}",apiUrl, reqDinners);
		
		final HttpEntity<DinOtpRq> entity = new HttpEntity<>(reqDinners, hdrs);

	    final ResponseEntity<DinOtpRs> respDinners = restTemplate.exchange(apiUrl, HttpMethod.POST, entity, DinOtpRs.class);
		
		log.info(" Response Dinners: {}",respDinners);	
		
		return getDinnersRsToTokenRs(respDinners);
	}
	
	public DinOtpRq getDinnerRequest(RecEvaluateTokenRq request,HttpHeaders headers) {
		    	
    	final List<Tag> tags = new ArrayList<>();
        tags.add(Tag.builder().clave(getHeaderValueAsString(headers,"tagsKeyValue"))
						      .valor(getHeaderValueAsString(headers,"tagsKeyValue"))
						      .build());
        	
    	return DinOtpRq.builder().dinHeader(DinHeader.builder()
																	.aplicacionId(getHeaderValueAsString(headers,"applicationId")) 
																	.canalId( getHeaderValueAsString(headers,"channelId"))
																	.sesionId(getHeaderValueAsString(headers,"sesionId"))
																	.dispositivo(getHeaderValueAsString(headers,"device"))
																	.idioma(getHeaderValueAsString(headers,"content-language"))
																	.portalId(getHeaderValueAsString(headers,"portalId"))
																	.uuid(getHeaderValueAsString(headers,"uuid"))
																	.ip(getHeaderValueAsString(headers,"ipaddress"))
																	.horaTransaccion(getHeaderValueAsString(headers,"transactionDate"))
																	.llaveSimetrica(getHeaderValueAsString(headers,"simetricKey"))
																	.usuario(getHeaderValueAsString(headers,"userId"))
																	.paginado(Paginado.builder()
			    																  .cantRegistros(getHeaderValueAsInt(headers,"recordsAmount"))
			    																  .numPagActual(getHeaderValueAsInt(headers,"pagesCurrentIndex"))
			    																  .numTotalPag(getHeaderValueAsInt(headers,"pagesAmount"))
			    															  .build())
																	.tags(tags)
			    											.build())
    										.dinBody(DinBody.builder()
    														.identificacionUsuario(request.getPartyAuthenticationAssessment().getPartyReference().getReferenceId())
    														.perfil(request.getPartyAuthenticationAssessment().getPartyReference().getPartyType())
    														.usuarioBiometrico(request.getUsername())
    														.transaccion(request.getTransactionCode())
    														.rucEmpresa(request.getOrganisation().getOrganisationIdentification()[0].getIdentifier().getIdentifierValue())
    														.codigoOTP(request.getTokenAssignment().getTokenStoredValue())
    												        .token(request.getTokenAssignment().getTokenIdentificationCode().getIdentifierValue().getValue())
    														.build())
    									
    							   .build();    	
    }
	
	private RecEvaluateTokenRs getDinnersRsToTokenRs(ResponseEntity<DinOtpRs> response) {
		RecEvaluateTokenRs res= null;
		DinOtpRs resp=response.getBody();
		if(resp != null) {
    		res= new RecEvaluateTokenRs();
    		if(resp.getDinBody() != null) {
    			res.setTokenAssignment(RecEvaluateTokenRs.TokenAssignment.builder()
    					.requestReference(RecEvaluateTokenRs.RequestReference.builder()
    																		 .tokenRequestReason(resp.getDinBody().getRespuestaSolicitud())	
    																		 .build())	
    					
    					.build());
    		}
    		if(resp.getDinError() != null) {
    			res.setStatusInstanceRecord(RecEvaluateTokenRs.StatusInstanceRecord.builder()
						.description(resp.getDinError().getDetalle())
						.message(resp.getDinError().getMensaje())
						.providerCode(resp.getDinError().getCodigoErrorProveedor())
						.statusCode(resp.getDinError().getCodigo())
						.status(resp.getDinError().getOrigen())
						.transactionDate(resp.getDinError().getFecha())
						.statusType(resp.getDinError().getTipo())
					    .build());
    		}
			
    	}
		return res;
	}
	
	
	public int getHeaderValueAsInt(HttpHeaders headers, String headerName) {
	    String headerValue = headers.getFirst(headerName);
	    if (headerValue != null) {
	         try {
	             return Integer.parseInt(headerValue);
	         } catch (NumberFormatException e) {
	          	log.error("El valor del encabezado no es un número válido: {}" , headerValue);
	         }
	    }
	    return 0;
	}
	    
	public String getHeaderValueAsString(HttpHeaders headers, String headerName) {
	    final String headerValue = headers.getFirst(headerName);
	    if(headerValue != null) {
	     	 return headerValue;
	    }
	    return "";
	}
	    
}
