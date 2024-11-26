package ec.com.dinersclub.partyAuthentication.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tag {
	 private String clave;
	 private String valor;
}
