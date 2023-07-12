package model.outplayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        String query1 = "INSERT INTO out_player (player_id, reason, created_at) VALUES (?, ?, now())";
        try (PreparedStatement statement = connection.prepareStatement(query1)) {
            statement.setInt(1, playerId);
            statement.setString(2, reason);
            statement.executeUpdate();
            // 출력
            System.out.println("선수 퇴출 등록 성공.");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("선수 퇴출 등록 실패!!!");
        }
        return null;
    }



    // 3.8 선수 퇴출 목록



}
