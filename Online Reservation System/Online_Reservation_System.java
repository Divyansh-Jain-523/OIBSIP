package project.ORS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Online_Reservation_System {
    static List<String> ticketWithDetails = new ArrayList<>();
    Online_Reservation_System(){

    }
    public static void main(String[] args) {
        new db_Intializer();
        new Login_Page();
    }
    public static Connection conn(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ORS", "root", "P@55w0rddb");
            return con;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
