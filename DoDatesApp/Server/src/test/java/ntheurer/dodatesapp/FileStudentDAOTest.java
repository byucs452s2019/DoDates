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
        String userID2 = "thomas1234";
        String userName2 = "thomas";
        String password2 = "passwordt";
        FileStudentDAO FileStudentDAO = new FileStudentDAO();

        try {
            FileStudentDAO.addStudent(userID, userName, password);
            FileStudentDAO.addStudent(userID2, userName2, password2);
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
