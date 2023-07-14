package db;

import java.io.BufferedReader;
import java.io.FileReader;

public class DBInit {

    public String readTeardown() {
        String sql = "";

        try {
            String filePath = "src/main/resources/teardown.sql";
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            StringBuilder queryBuilder = new StringBuilder();

            while ((sql = br.readLine())!= null) {
                queryBuilder.append(sql);
            }
            sql = queryBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sql;
    }
}
