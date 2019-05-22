package ntheurer.dodatesapp.DAO;

import java.sql.Connection;
import java.sql.Statement;

public class StudentsDAO extends ParentDAO {

    public boolean addStudent(String userID, String userName, String password) {
        openConnection();
        try {
            stmt = connection.createStatement();

            stmt.executeUpdate("INSERT INTO Students\n" +
                    "VALUES (\n" +
                    "\t'" + userID + "',\n" +
                    "\t'" + userName + "',\n" +
                    "\t'" + password + "'\n" +
                    ");");

            closeStatement();
            closeConnection(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            closeStatement();
            closeConnection(false);
            return false;
        }
    }

    public boolean removeStudent(String userID) {
        openConnection();
        try {
            stmt = connection.createStatement();

            stmt.executeUpdate("DELETE FROM Students\n" +
                    "WHERE UserID = '" + userID + "';");

            closeStatement();
            closeConnection(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            closeStatement();
            closeConnection(false);
            return false;
        }
    }

    private void closeStatement() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
