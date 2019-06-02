package ntheurer.dodatesapp;

import org.junit.Test;

import java.util.List;

import DAO.ClassDAO;
import model.UserClass;

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
//            classDAO.addClass("cID", "CS452", "blue", "ntgID2");
//            classDAO.addClass("cID2", "CS142", "green", "ntgID2");
//            classDAO.addClass("cID3", "CS500", "red", "ntgID3");
            List<UserClass> myClasses = classDAO.getClasses("ntgID");
            System.out.println(myClasses.size());

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}