package ntheurer.dodatesapp;

import org.junit.Test;

import ntheurer.dodatesapp.DAO.AssignmentDAO;

import static org.junit.Assert.*;

public class AssignmentDAOTest {

    @Test
    public void addAssignment() {
        AssignmentDAO assignmentDAO = new AssignmentDAO();

        try{
            assignmentDAO.addAssignment("aID", "MVP", "cID", "5/22/19", "5/21/19");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}