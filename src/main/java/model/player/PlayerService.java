package model.player;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Connection;
import java.util.List;

@Getter
@Setter
@ToString
public class PlayerService {
    private PlayerDao playerDao;

    // 생성자
    public PlayerService(Connection connection) {
        playerDao = new PlayerDao(connection);
    }

    // 3.5 선수등록 registerPlayer()
    public List<Player> registerPlayer(int teamId, String name, String position) throws Exception {
        return playerDao.registerPlayer(teamId, name, position);
    }

    // 3.6 팀별 선수 목록 getTeamPlayers()
    public List<Player> getTeamPlayers(int teamId) throws Exception {
        return playerDao.getTeamPlayers(teamId);
    }

    // 선수 퇴출 메소드 updateKickPlayer()
    public List<Player> updateKickPlayer() {
        return playerDao.updateKickPlayer();
    }
}
