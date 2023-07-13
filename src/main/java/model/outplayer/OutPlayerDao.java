package model.outplayer;

import db.DBConnection;
import dto.OutPlayerRespDTO;
import model.player.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDao {
    private Connection connection;

    public OutPlayerDao() {
        connection = DBConnection.getInstance();
    }


    // 3.7 선수 퇴출 등록
    // 두 개 이상의 write 문이 실행되어야 합니다. 이때는 반드시 트랜잭션 관리가 Service에서 필요합니다.
    // out_player에 퇴출 선수를 insert하고, player 테이블에서 해당 선수의 team_id를 null로 변경합니다
    public List<OutPlayer> registerKickPlayer(int playerId, String reason) {
        String query = "INSERT INTO out_player (player_id, reason, created_at) VALUES (?, ?, now());";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.executeUpdate();
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
    public List<OutPlayerRespDTO> listKickPlayer() {
        List<OutPlayerRespDTO> outPlayers = new ArrayList<>();
        String query = "SELECT p.id ID, p.name 이름, p.position 포지션, op.reason 이유, MAX(op.created_at) AS 퇴출일\n" +
                "FROM player AS p\n" +
                "LEFT JOIN out_player AS op\n" +
                "ON p.team_id IS NULL and p.id = op.player_id\n" +
                "GROUP BY p.id, p.name, p.position, op.reason;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("==================================");
                while (resultSet.next()) {
                    OutPlayerRespDTO outPlayer = buildOutPlayerFromResultSet(resultSet);
                    outPlayers.add(outPlayer);
                }
                if (!outPlayers.isEmpty()) {
                    for (OutPlayerRespDTO outplayer : outPlayers) {
                        System.out.println("선수 ID: " + outplayer.getPlayerId());
                        System.out.println("이름: " + outplayer.getName());
                        System.out.println("포지션: " + outplayer.getPosition());
                        System.out.println("이유: " + outplayer.getReason());
                        System.out.println("퇴출날짜: " + outplayer.getOutPlayerCreatedAt());
                        System.out.println("==================================");
                    }
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
    private OutPlayerRespDTO buildOutPlayerFromResultSet(ResultSet resultSet) throws SQLException {
        int playerId = resultSet.getInt("ID");
        String name = resultSet.getString("이름");
        String position = resultSet.getString("포지션");
        String reason = resultSet.getString("이유");
        Timestamp outPlayerCreatedAt = resultSet.getTimestamp("퇴출일");

        return OutPlayerRespDTO.builder()
                .playerId(playerId)
                .name(name)
                .position(position)
                .reason(reason)
                .outPlayerCreatedAt(outPlayerCreatedAt)
                .build();
    }
}