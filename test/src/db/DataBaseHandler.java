package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 This class is responsible for connecting to the database and sending the "INSERT INTO" request.
 */

public class DataBaseHandler extends Configs {
    static Connection dbConnection;

    public static Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    /*
    This method is called when you click the "save answer" button after selecting the answer to the last unanswered question.
    Fields with information about the test participant and its results are passed to it.
    Then, using constants, the received information is sent to the database
    */
    public static void signUp(String name, String soname, String job, String results, int time, int answer) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" +
                Const.USER_NAME + "," + Const.USER_SONAME + "," +
                Const.USER_JOB + "," + Const.USER_RESULTS + "," +
                Const.USER_TIME + "," + Const.USER_ANSWER + ")" +
                "VALUES(?,?,?,?,?,?)";

        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, name);
        prSt.setString(2, soname);
        prSt.setString(3, job);
        prSt.setString(4, results);
        prSt.setString(5, String.valueOf(time));
        prSt.setString(6, String.valueOf(answer));

        prSt.executeUpdate();
    }
}
