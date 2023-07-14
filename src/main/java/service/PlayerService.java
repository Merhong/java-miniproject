package service;

import dto.PositionRespDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.player.Player;
import model.player.PlayerDao;

import java.util.List;

@Getter
@Setter
@ToString
public class PlayerService {
    private PlayerDao playerDao;

    // 생성자
    public PlayerService() {
        this.playerDao = new PlayerDao();
    }

    // 3.5 선수등록 registerPlayer()
    public List<Player> registerPlayer(int teamId, String name, String position) throws Exception {
        return playerDao.registerPlayer(teamId, name, position);
    }

    // 3.6 팀별 선수 목록 getTeamPlayers()
    public List<Player> getTeamPlayers(int teamId) throws Exception {
        return playerDao.getTeamPlayers(teamId);
    }

    // 3.7 선수 퇴출 업데이트 메소드 updateKickPlayer()
    public List<Player> updateKickPlayer() {
        return playerDao.updateKickPlayer();
    }

    // 3.10 포지션별 팀 야구 선수 페이지 getPositionList()
    public List<PositionRespDto> getPositionList() {
        return playerDao.getPositionList();
    }
}
