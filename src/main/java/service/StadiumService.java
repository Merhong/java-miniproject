package service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import model.stadium.StadiumDao;

import java.sql.SQLException;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class StadiumService {
    private StadiumDao stadiumDao;

    // 생성자
    public StadiumService() {
        this.stadiumDao = new StadiumDao();
    }

    // 3.1 야구장 등록
    public void registerStadium(String name, Timestamp createdAt) throws SQLException {
        stadiumDao.registerStadium(name, createdAt);
    }

    // 3.2 야구장 전체 목록보기
    public void getAllStadiums() throws SQLException {
        stadiumDao.getAllStadiums();
    }

}
