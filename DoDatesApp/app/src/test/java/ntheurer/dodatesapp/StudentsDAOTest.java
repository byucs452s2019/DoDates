package ntheurer.dodatesapp;

import org.junit.Test;

import ntheurer.dodatesapp.DAO.StudentsDAO;

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
