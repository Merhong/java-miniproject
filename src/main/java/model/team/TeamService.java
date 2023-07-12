package model.team;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private TeamDao teamDao;

    // 생성자
    public TeamService(TeamDao teamDao) {
        this.teamDao = teamDao;
    }

    public TeamService(Connection connection) {
        teamDao = new TeamDao(connection);
    }

    // 3.3 팀 등록
    public void registerTeam(int stadiumId, String name, Timestamp createdAt) throws SQLException {
        try {
            teamDao.registerTeam(stadiumId, name, createdAt);
        } catch (SQLException e) {
            // 예외 처리를 수행합니다.
            e.printStackTrace();
        }
    }

    // 3.4 전체 팀 목록보기
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        try {
            teams = teamDao.getAllTeams();
        } catch (SQLException e) {
            // 예외 처리를 수행합니다.
            e.printStackTrace();
        }
        return teams;
    }

}
