package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileStudentDAO {
    private File studentDir;

    public FileStudentDAO() {
        studentDir = new File("students");
        studentDir.mkdir();
    }

    protected void write(String data, String filename) throws IOException {
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(data);
        }
    }

    protected String readFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append('\n');
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String content = stringBuilder.toString();
        return content;
    }

    public boolean addStudent(String userID, String userName, String password){
        String data = userID + "\n" + password;
        try {
            write(data, studentDir + File.separator + userName);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public String login(String username){

        String userData = "";
        try{
            userData = readFile(studentDir.toString() + File.separator + username);
            String delims = "[\n]";
            String[] userDatas = userData.split(delims);
            return userDatas[0];
        }catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
