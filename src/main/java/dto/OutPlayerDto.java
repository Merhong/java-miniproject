package dto;

import db.DBConnection;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import model.outplayer.OutPlayer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @Builder
public class OutPlayerDto {
    private Integer playerId;
    private String name;
    private String position;
    private String reason;
    private Timestamp createdAt;
}