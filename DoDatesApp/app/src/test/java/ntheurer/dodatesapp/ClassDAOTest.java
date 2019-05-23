package ntheurer.dodatesapp;

import org.junit.Test;

import ntheurer.dodatesapp.DAO.ClassDAO;

import static org.junit.Assert.*;

public class ClassDAOTest {

    @Test
    public void addClass() {
        ClassDAO classDAO = new ClassDAO();

        try{
            classDAO.addClass("cID", "CS452", "blue", "ntgID2");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}