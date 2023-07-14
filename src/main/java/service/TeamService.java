package service;

import dto.TeamRespDTO;
import model.team.TeamDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamService {
    private TeamDao teamDao;

    // 생성자
    public TeamService() {
        this.teamDao = new TeamDao();
    }

    // 3.3 팀 등록
    public void registerTeam(Integer stadiumId, String name) throws SQLException {
        try {
            teamDao.registerTeam(stadiumId, name);
        } catch (SQLException e) {
            // 예외 처리를 수행합니다.
            e.printStackTrace();
        }
    }

    // 3.4 전체 팀 목록보기
    public List<TeamRespDTO> getAllTeams() throws SQLException {
        List<TeamRespDTO> teams = new ArrayList<>();
        try {
            teams = teamDao.getAllTeams();
        } catch (SQLException e) {
            // 예외 처리를 수행합니다.
            e.printStackTrace();
        }
        return teams;
    }
}
