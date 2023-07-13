package model.player;

import lombok.*;

import java.sql.Time;
import java.sql.Timestamp;

@Getter @Setter @AllArgsConstructor @ToString
public class Player {
    private Integer id;
    private Integer teamId;
    private String name;
    private String position;
    private Timestamp createdAt;
}
