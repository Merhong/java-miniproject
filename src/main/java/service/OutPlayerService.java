package service;

import db.DBConnection;
import dto.OutPlayerDto;
import dto.OutPlayerRespDTO;
import model.outplayer.OutPlayer;
import model.outplayer.OutPlayerDao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.team.TeamDao;

import java.sql.Connection;
import java.util.List;

@Getter
@Setter
@ToString
public class OutPlayerService {
    private OutPlayerDao outPlayerDao;

    // 생성자
    public OutPlayerService() {
        this.outPlayerDao = new OutPlayerDao();
    }
    
    // 3.7 선수 퇴출 등록
    public List<OutPlayer> registerKickPlayer(int playerId, String reason) {
        return outPlayerDao.registerKickPlayer(playerId, reason);
    }

    // 3.8 선수 퇴출 목록
    public List<OutPlayerRespDTO> listKickPlayer() {
        return outPlayerDao.listKickPlayer();
    }


}
