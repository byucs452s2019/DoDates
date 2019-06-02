package ntheurer.dodatesapp;

import org.junit.Test;

import DAO.StudentsDAO;

import static org.junit.Assert.assertEquals;

public class StudentsDAOTest {

    @Test
    public void testAddStudent() {
        String userID = "ntgID";
        String userName = "ntg";
        String password = "password";
        StudentsDAO studentsDAO = new StudentsDAO();

        try {
            studentsDAO.addStudent(userID, userName, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLogin() {
        String userName = "ntg";
        StudentsDAO studentsDAO = new StudentsDAO();
        try {
            String userID = studentsDAO.login(userName);
            assertEquals("ntgID", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveStudent() {
        String userID = "ntgID";
        StudentsDAO studentsDAO = new StudentsDAO();

        try {
            studentsDAO.removeStudent(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
