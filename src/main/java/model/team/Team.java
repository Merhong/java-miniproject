package model.team;

import lombok.*;

import java.sql.Timestamp;

@Getter @Setter @AllArgsConstructor @ToString
public class Team {
    private Integer id;
    private Integer stadiumId;
    private String name;
    private Timestamp createdAt;
}
