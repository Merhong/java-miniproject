package model.outplayer;

import lombok.*;

import java.sql.Timestamp;

@Getter @Setter @AllArgsConstructor @ToString
public class OutPlayer {
    private Integer id;
    private Integer playerId;
    private String reason;
    private Timestamp createdAt;

}