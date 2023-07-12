package model.outplayer;

import model.player.Player;
import model.team.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDao {
    private Connection connection;
    public OutPlayerDao(Connection connection) {
        this.connection = connection;
    }


    // 3.7 선수 퇴출 등록
    // 두 개 이상의 write 문이 실행되어야 합니다. 이때는 반드시 트랜잭션 관리가 Service에서 필요합니다.
    // out_player에 퇴출 선수를 insert하고, player 테이블에서 해당 선수의 team_id를 null로 변경합니다
    public List<OutPlayer> registerKickPlayer(int playerId, String reason) {
        String query1 = "INSERT INTO out_player (player_id, reason, created_at) VALUES (?, ?, now());";
        try (PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.executeQuery();
            // 출력
            System.out.println("퇴출 명단 등록 성공.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("퇴출 명단 등록 실패!!!");
        }
        return null;
    }


    // 3.8 선수 퇴출 목록
    // input : 퇴출목록
    public  List<OutPlayer> listKickPlayer() {
        List<OutPlayer> outPlayers = new ArrayList<>();
        String query = "SELECT DISTINCT p.id pid, p.name, p.position, op.reason, MAX(op.created_at) AS 퇴출일\n" +
                "FROM player AS p\n" +
                "LEFT JOIN out_player AS op\n" +
                "ON p.team_id IS NULL \n" +
                "GROUP BY p.id, p.name, p.position, op.reason;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    OutPlayer outPlayer = buildOutPlayerFromResultSet(resultSet);
                    outPlayers.add(outPlayer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 출력
            System.out.println("퇴출 명단 조인 성공.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("퇴출 명단 조인 실패!!!");
        }
        return outPlayers;
    }


    // 위의 최종 컬럼명을 찾아서 (AS 컬럼명) 변수에 할당한다 !!!
    private OutPlayer buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int playerId = resultSet.getInt("pid");
        String reason = resultSet.getString("reason");
        Timestamp createdAt = resultSet.getTimestamp("퇴출일");

        return new OutPlayer(playerId, reason, createdAt);
    }

}
