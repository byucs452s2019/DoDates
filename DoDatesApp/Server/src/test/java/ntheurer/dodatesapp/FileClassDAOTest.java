package ntheurer.dodatesapp;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import DAO.FileClassDAO;
import model.UserClass;

import static org.junit.Assert.*;

public class FileClassDAOTest {

    @Test
    public void addClass() {
        String classID = "CSCLASS1234";
        String className = "CS142";
        String colorName = "blue";
        String userID = "thomas1234";

        String classID2 = "CS2CLASS1234";
        String className2 = "CS452";
        String colorName2 = "red";
        String userID2 = "thomas1234";
        FileClassDAO fileClassDAO = new FileClassDAO();

        try{
            fileClassDAO.addClass(classID, className, colorName, userID);
            fileClassDAO.addClass(classID2, className2, colorName2, userID2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getClasses(){
        FileClassDAO fileClassDAO = new FileClassDAO();

        try{
            List<UserClass> classList = new ArrayList<>();
            classList = fileClassDAO.getClasses("thomas1234");
            for (UserClass c: classList) {
                System.out.printf(c.getClassName() + '\n');
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
