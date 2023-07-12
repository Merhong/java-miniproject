package model.player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    private Connection connection;

    public PlayerDao(Connection connection) {
        this.connection = connection;
    }

    // 3.5 선수등록 registerPlayer()
    // 요청(Input) : 선수등록?teamId=1&name=이대호&position=1루수
    //      1. BaseBallApp에서 키보드로 입력 받은 String에 대한 Split()이 2번 필요함.
    //      2. "?"로 Split() 후 [0] = 선수등록           -> 서비스 메뉴 호출에 활용
    //      3. [1] = teamId=1&name=이대호&position=1루수 -> 쿼리문에서 사용
    //       3.1 [1]을 가지고 다시 "&"으로 split()
    //       3.2 [1][0] = teamID, [1][1] = name, [1][2] = position
    // 응답 : 성공이라는 메시지를 출력한다.
    public List<Player> registerPlayer(int teamId, String name, String position) throws Exception {
        // Insert 쿼리문 : (팀ID, 이름, 포지션)
        String query = "INSERT INTO player (team_id, name, position, created_at) VALUES (?, ?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            statement.setString(2, name);
            statement.setString(3, position);
            statement.executeUpdate();

            // 출력
            System.out.println("성공");
        }
        return null;
    }

    // 3.6 팀별 선수 목록 getTeamPlayers()
    //요청(Input) : 선수목록?teamId=1
    //      1. BaseBallApp에서 split() 1번 필요함.
    //      2. "?"로 split() 후 [0] = 선수목록 -> 서비스 호출에 활용
    //      3. [1] = teamId                    -> 쿼리문에서 사용
    //응답 : 선수 목록은 Model -> Player를 List에 담아서 출력한다. (team_id는 출력하지 않아도 된다)
    public List<Player> getTeamPlayers(int teamId) throws Exception {
        List<Player> players = new ArrayList<>();
        String query = "SELECT * FROM player WHERE team_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, teamId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {

                    Player player = buildPlayerFromResultSet(resultSet);
                    players.add(player);
                }
            }
        }
        return players;
    }

    // 선수를 만드는 메소드 buildPlayerFromResultSet()
    private Player buildPlayerFromResultSet(ResultSet resultSet) throws Exception {
        int teamId = resultSet.getInt("team_id");
        String name = resultSet.getString("name");
        String position = resultSet.getString("position");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Player(teamId, name, position, createdAt);
    }
}
