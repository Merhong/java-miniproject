package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class OutPlayerDto {
    private Integer playerId;
    private String name;
    private String position;
    private String reason;
    private Timestamp createdAt;
}