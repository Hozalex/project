package Server;

import java.sql.*;

public class AuthService {

    private static Connection connection;
    private static Statement statement;

    public static String getNickByLoginPass(String login, String pass) {
        String sql = String.format("SELECT nickname, password FROM userlist where login = '%s'", login);

        try {
            ResultSet rs = statement.executeQuery(sql);
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
//                return rs.getString("nickname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:usersDB");
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void addUserBlacklist(String nickToBlackList) {
        String queryGetLogin = String.format("Select login from userlist where nickname = '%s'", nickToBlackList);
        String queryPutUserToBlaclist;

        try {
            ResultSet rs = statement.executeQuery(queryGetLogin);
            String login = rs.getString(1);
            queryPutUserToBlaclist = String.format("Insert into blacklist (login, nickname) " +
                    "Values ('%s','%s')", login, nickToBlackList);
            PreparedStatement st = connection.prepareStatement(queryPutUserToBlaclist);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeUserBlacklist(String nickToBlackList) {
        String queryRemoveUser = String.format("DELETE from blacklist where nickname = '%s'", nickToBlackList);

        try {
            PreparedStatement st = connection.prepareStatement(queryRemoveUser);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {

        try {
            String query = "INSERT INTO userlist (login, password, nickname) VALUES (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();

        } catch (SQLException e) {
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
