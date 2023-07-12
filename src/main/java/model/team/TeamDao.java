package model.team;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    private  Connection connection;
    public TeamDao(Connection connection) {
        this.connection = connection;
    }

    // 팀 등록
    public void registerTeam(int stadiumId, String name, Timestamp createdAt) throws SQLException {
        String query = "INSERT INTO team(stadium_id, name, created_at) VALUES(?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, stadiumId);
            statement.setString(2, name);
            statement.setTimestamp(3, createdAt);
            statement.executeUpdate();
        }
    }

    // 전체 팀 목록보기
    public List<Team> getAllTeams() throws SQLException {
        List<Team> teams = new ArrayList<>();
        String query = "SELECT * FROM team";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Team team = buildTeamFromResultSet(resultSet);
                    teams.add(team);
                }
            }
        }
        return teams;
    }


    private Team buildTeamFromResultSet(ResultSet resultSet) throws SQLException {
        int stadium_id = resultSet.getInt("stadium_id");
        String name = resultSet.getString("name");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

       return new Team(stadium_id, name, createdAt);
    }

}