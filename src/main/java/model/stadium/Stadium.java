package model.stadium;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Stadium {
    private Integer id;
    private String name;
    private Timestamp createdAt;
}

