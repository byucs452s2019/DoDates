package ntheurer.dodatesapp;

import org.junit.Test;

import java.util.List;

import ntheurer.dodatesapp.DAO.ClassDAO;
import ntheurer.dodatesapp.model.UserClass;

import static org.junit.Assert.*;

public class ClassDAOTest {

    @Test
    public void addClass() {
        ClassDAO classDAO = new ClassDAO();

        try{
            classDAO.addClass("cID", "CS452", "blue", "ntgID2");
            classDAO.addClass("cID2", "CS142", "green", "ntgID2");

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getClasses(){
        ClassDAO classDAO = new ClassDAO();

        try{
//            classDAO.addClass("cID", "CS452", "blue", "ntgID2");
//            classDAO.addClass("cID2", "CS142", "green", "ntgID2");
//            classDAO.addClass("cID3", "CS500", "red", "ntgID3");
            List<UserClass> myClasses = classDAO.getClasses("ntgID2");
            System.out.println(myClasses.size());

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}