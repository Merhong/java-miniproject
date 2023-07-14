package db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class DBConnectionTest {

    @Test
    public void getInstance_test() {
        Connection connection = DBConnection.getInstance();
        if (connection == null) {
            System.out.println("DB 연결안됨");
        } else {
            System.out.println("DB 연결됨");
        }
    }
}
