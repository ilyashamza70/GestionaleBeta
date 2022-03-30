package Connection;

import java.sql.SQLException;
import java.sql.*;

public class Connessione {
    Statement statement;

    public Connessione() throws SQLException {
        DBManager.setConnection(
                Utils.JDBC_Driver_SQLite,
                Utils.JDBC_URL_SQLite);
        statement = DBManager.getConnection().createStatement();

        try
        {
            /*
             * Simple query for testing that everything is OK. If an exception raised, the
             * db is deleted and created from scratch.
             */
            statement.executeQuery("SELECT * FROM ordine LIMIT 1");
        }
        catch(SQLException e)
        {
            statement.executeUpdate("DROP TABLE IF EXISTS ordine");

            statement.executeUpdate("CREATE TABLE ordine (" + "nordine INTEGER, " + "ID INTEGER NOT NULL UNIQUE, "
                    + "orario VARCHAR(30) NOT NULL, " + "codpizza INTEGER NOT NULL, "
                    + "PRIMARY KEY(nordine AUTOINCREMENT))");
/*
            statement.executeUpdate("CREATE TABLE pizza (" + "codpizza INTEGER, " + "nomepizza VARCHAR(30) NOT NULL UNIQUE  " + "ingredienti VARCHAR(300), "
                    + "nvolte INTEGER, " + "PRIMARY KEY(codpizza))");

            statement.executeUpdate("CREATE TABLE utente (" + "ID INTEGER, " + "indirizzo VARCHAR(100) NOT NULL, "
                    + "citofono VARCHAR(30) NOT NULL, " + "telefono VARCHAR(10) NOT NULL, "
                    + "PRIMARY KEY(ID AUTOINCREMENT))");

            statement.executeUpdate(
                    "INSERT INTO test (id, title, author, pages) VALUES(1, 'The Lord of the Rings', 'Tolkien', 241)");
            statement.executeUpdate("INSERT INTO test (id, title, author, pages) VALUES(2, 'Fight Club', 'Palahniuk', 212)");
            statement.executeUpdate(
                    "INSERT INTO test (id, title, author, pages) VALUES(3, 'Computer Networks', 'Tanenbaum', 313)");
            statement.executeUpdate(
                    "INSERT INTO test (id, title, author, pages) VALUES(4, 'Affective Computing', 'Picard', 127)");

 */
        }

    }
}