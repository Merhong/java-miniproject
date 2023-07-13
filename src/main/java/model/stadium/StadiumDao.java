package model.stadium;

import db.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDao {
    private Connection connection;

    public StadiumDao() {
        connection = DBConnection.getInstance();
    }

    // 3.1 야구장 등록 registerStadium()
    public void registerStadium(String name, Timestamp createdAt) throws SQLException {
        String query = "INSERT INTO stadium (name, created_at) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setTimestamp(2, createdAt);
            statement.executeUpdate();
        }
    }

    // 3.2 야구장 전체 목록보기 getAllStadiums()
    public List<Stadium> getAllStadiums() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        String query = "SELECT * FROM stadium;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Stadium stadium = buildStadiumFromResultSet(resultSet);
                    stadiums.add(stadium);
                }
                System.out.println("야구장 목록 출력");
                System.out.println("========================");
                if (!stadiums.isEmpty()) {
                    for (Stadium stadium : stadiums) {
                        System.out.println("야구장 이름: " + stadium.getName());
                        System.out.println("개장일: " + stadium.getCreatedAt());
                    }
                }
                System.out.println("========================");
            }
        }
        return stadiums;
    }

    // 야구장 집합 만드는 메소드
    private Stadium buildStadiumFromResultSet(ResultSet resultSet) throws SQLException {
        int id= resultSet.getInt("id");
        String name = resultSet.getString("name");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Stadium(id, name, createdAt);
    }
}
