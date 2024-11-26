package ec.com.dinersclub.partyAuthentication.domain.model;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DinOtpRs {
    private DinHeader dinHeader;
    private DinBody dinBody;
    private DinError dinError;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinHeader {
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

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Paginado {
            private int cantRegistros;
            private int numTotalPag;
            private int numPagActual;
        }

        @Getter
        @Setter
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Tag {
            private String clave;
            private String valor;
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinBody {
        private String respuestaSolicitud;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DinError {
        private String tipo;
        private String fecha;
        private String origen;
        private String codigo;
        private String codigoErrorProveedor;
        private String mensaje;
        private String detalle;
    }
} 