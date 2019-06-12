package DAO;

import java.sql.*;

public class ParentDAO {
//starts the database and makes the file
    static{
        try {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
            // ERROR! Could not load database driver
        }
    }
    protected Connection connection = null;
    protected Statement stmt = null;

    public void openConnection() {
        String connectionURL = "jdbc:sqlite:DoDates.sqlite";

        try {
            // Open a database connection
            connection = DriverManager.getConnection(connectionURL);

            // Start a transaction
            connection.setAutoCommit(false);

        } catch (SQLException e) {
            e.printStackTrace();
            // ERROR
        }
    }

    public void closeConnection(boolean commit){
        try{
            //closes the connection and roles back if needed
            if(commit){
                connection.commit();
                connection.close();
                connection = null;
            }
            else{
                connection.rollback();
                connection.close();
                connection = null;
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public boolean createTables(){
        try{
            try{
                stmt = connection.createStatement();

                //create tables in database
                createStudentsTable(stmt);
                createStudentClassesTable(stmt);
                createClassesTable(stmt);
                createAssignmentsTable(stmt);
            }finally{
                if(stmt != null){
                    stmt.close();
                    stmt = null;
                }
                return true;
            }

        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public void dropTables(){
        try{
            //drops all database tables
            stmt = connection.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS Students");
            stmt.executeUpdate("DROP TABLE IF EXISTS StudentClasses");
            stmt.executeUpdate("DROP TABLE IF EXISTS Classes");
            stmt.executeUpdate("DROP TABLE IF EXISTS Assignments");

        }catch(Exception e){
            e.printStackTrace();
        }
    }



    void createStudentsTable(Statement stmt){
        try{
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Students` (\n" +
                    "\t`UserID`\tTEXT NOT NULL UNIQUE,\n" +
                    "\t`UserName`\tTEXT NOT NULL,\n" +
                    "\t`Password`\tTEXT NOT NULL,\n" +
                    "\tPRIMARY KEY(`UserID`)\n" +
                    ")");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void createStudentClassesTable(Statement stmt){
        try{
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `StudentClasses` (\n" +
                    "\t`UserID`\tTEXT NOT NULL,\n" +
                    "\t`ClassID`\tTEXT NOT NULL,\n" +
                    "\tFOREIGN KEY (`UserID`) REFERENCES `Students`\n" +
                    "\tFOREIGN KEY (`ClassID`) REFERENCES `Classes`\n" +
                    ")");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void createClassesTable(Statement stmt){
        try{
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Classes` (\n" +
                    "\t`ClassID`\tTEXT NOT NULL UNIQUE,\n" +
                    "\t`ClassName`\tTEXT NOT NULL,\n" +
                    "\t`ColorName`\tTEXT NOT NULL,\n" +
                    "\tPRIMARY KEY(`ClassID`)\n" +
                    ")");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    void createAssignmentsTable(Statement stmt){
        try{
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `Assignments` (\n" +
                    "\t`AssignmentID`\tTEXT NOT NULL UNIQUE,\n" +
                    "\t`AssignmentName`\tTEXT NOT NULL,\n" +
                    "\t`ClassID`\tTEXT NOT NULL,\n" +
                    "\t`DueDate`\tTEXT NOT NULL,\n" +
                    "\t`DoDate`\tTEXT NOT NULL,\n" +
                    "\tPRIMARY KEY(`AssignmentID`)\n" +
                    "\tFOREIGN KEY (`ClassID`) REFERENCES `Classes`\n" +
                    ")");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}