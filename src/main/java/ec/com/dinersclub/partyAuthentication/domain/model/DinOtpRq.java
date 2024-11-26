package ec.com.dinersclub.partyAuthentication.domain.model;

import java.util.List;

import lombok.*;

@Data
@Builder
public class DinOtpRq {
    private DinHeader dinHeader;
    private DinBody dinBody;

} 