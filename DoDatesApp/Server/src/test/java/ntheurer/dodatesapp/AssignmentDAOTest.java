package ntheurer.dodatesapp;

import org.junit.Test;

import java.util.List;

import model.Assignment;

import DAO.AssignmentDAO;

public class AssignmentDAOTest {

    @Test
    public void addAssignment() {
        AssignmentDAO assignmentDAO = new AssignmentDAO();

        try{
            assignmentDAO.addAssignment("aID", "Final", "cID", "6/19/2019", "6/18/2019");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void getAssignments(){
        AssignmentDAO classDAO = new AssignmentDAO();

        try{
//            classDAO.addClass("cID", "CS452", "blue", "ntgID2");
//            classDAO.addClass("cID2", "CS142", "green", "ntgID2");
//            classDAO.addClass("cID3", "CS500", "red", "ntgID3");
            List<Assignment> myAssignments = classDAO.getAssignments("cID");
            System.out.println(myAssignments.size());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}