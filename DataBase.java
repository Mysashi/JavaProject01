package ForJava;


import java.sql.*;
import java.util.HashMap;

import org.sqlite.JDBC;

public class DataBase {
    private static final String CON_STR = "jdbc:sqlite:D:/steam.db";
    private Connection connection;


    {
        DbHandler();
    }
    private void DbHandler(){
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(CON_STR);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createGames(){

        try (Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE games " +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(255)," +
                    " genre VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " markFavorite BOOLEAN, " +
                    " PRIMARY KEY ( id ))";
            statement.executeUpdate(sql);
            System.out.println("Table is created");

        }
        catch (SQLException exception) {
            System.out.println("Not working");
            System.out.println(exception.getMessage());
        }

    }

    protected void addGame(String name, String description,String genre, Boolean markFavorite) {
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO games(name, description, genre, markFavorite) VALUES (?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, genre);
            stmt.setBoolean(4, markFavorite);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected void deleteGame(String name){
        String deleteSQL = String.format("DELETE FROM games WHERE name = '%s'", name);
        try {
            PreparedStatement statement = connection.prepareStatement(deleteSQL);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Data deleted successfully.");
    }

    protected void updateGameDescription(String description, String name){
        String updateSQL = "UPDATE games SET description =? WHERE name =?";
        try {
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setString(1, description);
            statement.setString(2, name);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Description updated successfully.");
    }

    protected HashMap<String, Integer>  selectGenreCount() {
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery("SELECT genre, COUNT(*) AS total FROM games GROUP BY genre;");
            return counter(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    private HashMap<String, Integer> counter(ResultSet rs) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        try {
            while (rs.next()) {
                String genre = rs.getString("genre");
                int count = rs.getInt("total");
                hashMap.put(genre, count);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hashMap;
    }



}
