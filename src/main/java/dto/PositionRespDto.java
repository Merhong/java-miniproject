package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PositionRespDto {
    private String position;
    private String teamName1;
    private String teamName2;
    private String teamName3;
}
