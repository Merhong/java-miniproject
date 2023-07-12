package model.stadium;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class StadiumService {
    private StadiumDao stadiumDao;

    // 생성자
    public StadiumService(Connection connection) {
        stadiumDao = new StadiumDao(connection);
    }

    // 3.1 야구장 등록
    public void registerStadium(String name, Timestamp createdAt) {
        try {
            stadiumDao.registerStadium(name, createdAt);
        } catch (SQLException e) {
            // 예외 처리를 수행합니다.
            e.printStackTrace();
        }
    }

    // 3.2 야구장 전체 목록보기
    public List<Stadium> getAllStadiums() throws SQLException {
        List<Stadium> stadiums = new ArrayList<>();
        try {
            stadiums = stadiumDao.getAllStadiums();
        } catch (SQLException e) {
            // 예외 처리를 수행합니다.
            e.printStackTrace();
        }
        return stadiums;
    }
}
