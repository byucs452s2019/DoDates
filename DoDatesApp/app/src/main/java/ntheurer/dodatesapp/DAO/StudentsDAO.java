package ntheurer.dodatesapp.DAO;

import java.sql.Connection;
import java.sql.Statement;

public class StudentsDAO {

    private Connection connection = null;
    private Statement stmt = null;
    private ParentDAO parentDAO = null;

    public StudentsDAO() {
        parentDAO = new ParentDAO();
    }

    public boolean addStudent(String userID, String userName, String password) {
        parentDAO.openConnection();
        connection = parentDAO.connection;
        try {
            stmt = connection.createStatement();

            stmt.executeUpdate("INSERT INTO Students\n" +
                    "VALUES (\n" +
                    "\t'" + userID + "',\n" +
                    "\t'" + userName + "',\n" +
                    "\t'" + password + "'\n" +
                    ");");

            closeStatement();
            parentDAO.closeConnection(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            closeStatement();
            parentDAO.closeConnection(false);
            return false;
        }
    }

    public boolean removeStudent(String userID) {
        parentDAO.openConnection();
        connection = parentDAO.connection;
        try {
            stmt = connection.createStatement();

            stmt.executeUpdate("DELETE FROM Students\n" +
                    "WHERE UserID = '" + userID + "';");

            closeStatement();
            parentDAO.closeConnection(true);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            closeStatement();
            parentDAO.closeConnection(false);
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
