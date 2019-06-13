package ntheurer.dodatesapp;

import org.junit.Test;

import DAO.FileStudentDAO;

import static junit.framework.TestCase.assertEquals;

public class FileStudentDAOTest {

    @Test
    public void testAddStudent() {
        String userID = "helpme1234";
        String userName = "helpme";
        String password = "password";
        FileStudentDAO FileStudentDAO = new FileStudentDAO();

        try {
            FileStudentDAO.addStudent(userID, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        String userName = "helpme";
        FileStudentDAO FileStudentDAO = new FileStudentDAO();
        try {
            String userID = FileStudentDAO.login(userName);
            assertEquals("helpme1234", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
