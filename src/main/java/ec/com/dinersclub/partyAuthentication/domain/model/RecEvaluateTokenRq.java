package ec.com.dinersclub.partyAuthentication.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecEvaluateTokenRq {
    private PartyAuthenticationAssessment partyAuthenticationAssessment;
    private String username;
    private String transactionCode;
    private Organisation organisation;
    private TokenAssignment tokenAssignment;

    // Define las clases internas o importaciones necesarias
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyAuthenticationAssessment {
        private PartyReference partyReference;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PartyReference {
        private String referenceId;
        private String partyType;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Organisation {
        private OrganisationIdentification[] organisationIdentification;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrganisationIdentification {
        private Identifier identifier;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Identifier {
        private String identifierValue;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenAssignment {
        private String tokenStoredValue;
        private TokenIdentificationCode tokenIdentificationCode;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenIdentificationCode {
        private IdentifierValue identifierValue;
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdentifierValue {
        private String value;
    }
} 