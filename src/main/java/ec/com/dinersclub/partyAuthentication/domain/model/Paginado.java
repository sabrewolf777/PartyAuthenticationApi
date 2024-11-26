package ec.com.dinersclub.partyAuthentication.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paginado {
	  private int cantRegistros;
	    private int numTotalPag;
	    private int numPagActual;
}
