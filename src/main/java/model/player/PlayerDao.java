package model.player;

import db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    private Connection connection;

    public PlayerDao() {
        connection = DBConnection.getInstance();
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
            System.out.println("선수등록 완료.");
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
                System.out.println(teamId + "번팀 선수 목록");
                System.out.println("==================================");

                while (resultSet.next()) {
                    Player player = buildPlayerFromResultSet(resultSet);
                    players.add(player);
                }
                if (!players.isEmpty()) {
                    for (Player player : players) {
                        System.out.println("선수 ID: " + player.getId());
                        System.out.println("선수 이름: " + player.getName());
                        System.out.println("포지션: " + player.getPosition());
                        System.out.println("데뷔날짜: " + player.getCreatedAt());
                        System.out.println("==================================");
                    }
                }
            }
        }
        return players;
    }

    // 선수를 만드는 메소드 buildPlayerFromResultSet()
    private Player buildPlayerFromResultSet(ResultSet resultSet) throws Exception {
        int id = resultSet.getInt("id");
        int teamId = resultSet.getInt("team_id");
        String name = resultSet.getString("name");
        String position = resultSet.getString("position");
        Timestamp createdAt = resultSet.getTimestamp("created_at");

        return new Player(id, teamId, name, position, createdAt);
    }

    // 3.7 선수 퇴출 메소드 updateKickPlayer()
    public List<Player> updateKickPlayer() {
        List<Player> players = new ArrayList<>();
        // player 테이블의 id 와 out_player 테이블의 player_id가 같다면, player 테이블의 team_id를 null로 변경
        String query = "UPDATE player, out_player SET team_id = null WHERE player.id = out_player.player_id;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            statement.executeUpdate();

            // 출력
            System.out.println("업데이트 성공");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("업데이트 실패!!!");
        }
        return null;
    }
}
