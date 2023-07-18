package db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;

public class DBInit {

    public static void teardown() {
        String sql = readTeardown();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readTeardown() {
        String sql = "";
        try {
            String filePath = "src/main/resources/teardown.sql";
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            while (true) {
                String line = br.readLine();

                if (line == null) break;

                sql = sql + line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }
}
