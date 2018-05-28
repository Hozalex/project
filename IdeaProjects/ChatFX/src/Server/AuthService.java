package Server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement statement;

    public static String getNickByLoginPass(String login, String pass) {
        String sql = String.format("SELEct nickname from main\n" +
                "where login = '%s'\n" +
                "And password = '%s'", login, pass);

        try {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:usersDB.db");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
