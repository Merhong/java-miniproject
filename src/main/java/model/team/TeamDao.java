package model.team;


import db.DBConnection;
import dto.TeamRespDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private Connection connection;

    public TeamDao() {
        connection = DBConnection.getInstance();
        System.out.println("디버그 : DB연결 성공");
    }

    // 3.3 팀 등록 registerTeam()
    public void registerTeam(int stadiumId, String name) throws SQLException {
        String query = "INSERT INTO team(id, stadium_id, name, created_at) VALUES(null, ?, ?, now());";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, name);
            statement.executeUpdate();
        }
    }

    // 3.4 전체 팀 목록보기 getAllTeams()
    public List<TeamRespDTO> getAllTeams() throws SQLException {
        List<TeamRespDTO> teams = new ArrayList<>();
//        String query = "SELECT * FROM team";
        String query = "SELECT team.name as 팀명, stadium.name as 야구장\n" +
                "FROM team\n" +
                "INNER JOIN stadium \n" +
                "ON team.stadium_id = stadium.id \n" +
                "AND team.id = stadium. id;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TeamRespDTO team = buildTeamFromResultSet(resultSet);
                    teams.add(team);
                }
                System.out.println("팀 목록 출력");
                System.out.println("========================");
                if (!teams.isEmpty()) {
                    for (TeamRespDTO team : teams) {
                        System.out.println("팀: " + team.getTeamName());
                        System.out.println("야구장: " + team.getStadiumName());
                        System.out.println("========================");
                    }
                }
            }
        }
        return teams;
    }

    private TeamRespDTO buildTeamFromResultSet(ResultSet resultSet) throws SQLException {
        String teamName = resultSet.getString("팀명");
        String stadiumName = resultSet.getString("야구장");

        return TeamRespDTO.builder()
                .teamName(teamName)
                .stadiumName(stadiumName)
                .build();
    }
}
