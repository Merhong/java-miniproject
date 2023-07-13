package dto;

import model.player.PlayerDao;

import java.sql.Connection;
import java.sql.Timestamp;

public class PlayerDto {
    private Integer playerId;
    private Integer teamId;
    private String name;
    private String position;
    private Timestamp createdAt;
}
