package pl.mg.javatst.cert.ocp;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class JdbcTests {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/world?serverTimezone=GMT&useTimezone=true";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,
                    "root",
                    "qaz123");

            Statement stm = connection.createStatement();
            ResultSet resultSet = stm.executeQuery("SELECT * from city");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {

            }
        }
    }
}
