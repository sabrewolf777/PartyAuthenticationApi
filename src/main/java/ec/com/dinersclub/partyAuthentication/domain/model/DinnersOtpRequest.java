package ec.com.dinersclub.partyAuthentication.domain.model;

import java.util.List;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DinnersOtpRequest {
    private DinHeader dinHeader;
    private DinBody dinBody;

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
        private String identificacionUsuario;
        private String perfil;
        private String usuarioBiometrico;
        private String transaccion;
        private String rucEmpresa;
        private String codigoOTP;
        private String token;
    }
} 