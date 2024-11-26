package ec.com.dinersclub.partyAuthentication.domain.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DinHeader {
	 	private String aplicacionId;
	    private String canalId;
	    private String sesionId;
	    private String dispositivo;
	    private String idioma;
	    private String portalId;
	    private String uuid;
	    private String ip;
	    private String horaTransaccion;
	    private String llaveSimetrica;
	    private String usuario;
	    private Paginado paginado;
	    private List<Tag> tags;
}
