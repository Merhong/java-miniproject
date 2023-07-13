package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class TeamRespDTO {
    private String teamName;
    private String stadiumName;

}
