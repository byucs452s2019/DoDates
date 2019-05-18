package ntheurer.dodatesapp;

import org.junit.Test;

import ntheurer.dodatesapp.DAO.ParentDAO;

import static org.junit.Assert.*;

public class ParentDAOTest {
    @Test
    public void addTables() throws Exception{
        ParentDAO parent = new ParentDAO();
        parent.openConnection();
        //parent.dropTables();
        parent.createTables();
        parent.closeConnection(true);
    }
}
