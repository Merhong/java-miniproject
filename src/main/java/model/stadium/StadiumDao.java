package model.stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDao {
    private Connection connection;
    public StadiumDao(Connection connection) {
        this.connection = connection;
    }

    // 야구장 등록
    public void registerStadium(String name, Timestamp createdAt) throws SQLException {
        String query = "INSERT INTO stadium (name, created_at) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setTimestamp(2, createdAt);
            statement.executeUpdate();
        }
    }

    // 야구장 전체 목록보기
    public List<Stadium> getAllStadiums() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try(ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Stadium stadium = buildStadiumFromResultSet(resultSet);
                    stadiums.add(stadium);
                }
            }
        }
        return stadiums;
    }


    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Stadium(name, createdAt);
    }

}
