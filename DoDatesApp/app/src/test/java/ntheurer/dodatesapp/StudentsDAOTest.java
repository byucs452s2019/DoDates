package ntheurer.dodatesapp;

import org.junit.Test;

import ntheurer.dodatesapp.DAO.StudentsDAO;

import static org.junit.Assert.assertEquals;

public class StudentsDAOTest {

    @Test
    public void testAddStudent() {
        String userID = "ntgID2";
        String userName = "ntg2";
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
        String userName = "ntg2";
        StudentsDAO studentsDAO = new StudentsDAO();
        try {
            String userID = studentsDAO.login(userName);
            assertEquals("ntgID2", userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRemoveStudent() {
        String userID = "ntgID2";
        StudentsDAO studentsDAO = new StudentsDAO();

        try {
            studentsDAO.removeStudent(userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
