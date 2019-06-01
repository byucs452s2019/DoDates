package ntheurer.dodatesapp;

import org.junit.Test;

import DAO.ParentDAO;

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
