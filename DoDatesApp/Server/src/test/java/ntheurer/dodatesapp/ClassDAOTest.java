package ntheurer.dodatesapp;

import org.junit.Test;

import java.util.List;

import DAO.AssignmentDAO;
import DAO.ClassDAO;
import model.UserClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassDAOTest {

    @Test
    public void addClass() {
        ClassDAO classDAO = new ClassDAO();

        try{
            classDAO.addClass("cID", "BlueClass", "blue", "ntgID");
            classDAO.addClass("cID2", "GreenClass", "green", "ntgID");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getClasses(){
        ClassDAO classDAO = new ClassDAO();

        try{
//            classDAO.addClass("cID", "BlueClass", "blue", "ntgID");
//            classDAO.addClass("cID2", "GreenClass", "green", "ntgID");

            List<UserClass> myClasses = classDAO.getClasses("ntgID");
            System.out.println(myClasses.size());
            for (UserClass c : myClasses) {
                System.out.println(c.getUniqueID() + ": " + c.getClassName());
            }

            int expectedNumClasses = 2;
            assertEquals(expectedNumClasses, classDAO.getClasses("ntgID").size());

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void updateClass() {
        ClassDAO classDAO = new ClassDAO();

        try {
            classDAO.updateClass("cID", "blue nOw", "blue");

            List<UserClass> myClasses = classDAO.getClasses("ntgID");
            System.out.println(myClasses.size());
            for (UserClass c : myClasses) {
                System.out.println(c.getUniqueID() + ": " + c.getClassName() + ", " + c.getColorString());
            }

            assertTrue(true);

        } catch (Exception e) {
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void removeClass() {
        ClassDAO classDAO = new ClassDAO();

        try{
//            classDAO.addClass("cID", "BlueClass", "blue", "ntgID");
//            classDAO.addClass("cID2", "GreenClass", "green", "ntgID");

            classDAO.removeClass("cID");
            classDAO.removeClass("cID2");

            int expectedNumClasses = 0;
            assertEquals(expectedNumClasses, classDAO.getClasses("ntgID").size());

            AssignmentDAO assignmentDAO = new AssignmentDAO();
            int expectedNumAssignments = 0;
            assertEquals(expectedNumAssignments, assignmentDAO.getAssignments("cID").size());
            assertEquals(expectedNumAssignments, assignmentDAO.getAssignments("cID2").size());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}