package main.java.br.com.cryslefundes.repository.jdbc;

import java.sql.*;

public class ConnectionFactory {
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = initConnetion();
        } else if (connection.isClosed()) {
            connection = initConnetion();
        }
        return connection;
    }

    private static Connection initConnetion() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:15432/sistemaVendas", "postgres", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet rset) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
        if (rset != null && !rset.isClosed()) {
            rset.close();
        }
    }
}
