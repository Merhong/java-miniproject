package db;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitTest {

    @Test
    public void init_test(){
        DBInit dbInit = new DBInit();
        String sql = dbInit.readTeardown();
        try {
            Connection connection = DBConnection.getInstance();
            Statement statement = connection.createStatement();
            String[] queries = sql.split(";");
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query);
                }
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}