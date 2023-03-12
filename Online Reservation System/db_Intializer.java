package project.ORS;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.*;

public class db_Intializer {
    db_Intializer(){
        try {
            HashMap<Integer,String> trains = new HashMap<>();
            Connection con = Online_Reservation_System.conn();
            PreparedStatement s = con.prepareStatement("create table Train_Information (Train_Number int(5) primary key,Train_Name varchar(50) not null)");
            s.executeUpdate();
            s = con.prepareStatement("create table Tickets (PNR int(8) primary key,Passenger_Name varchar(20) not null, Age int(3) not null , Date_of_Journey varchar(20) not null, Train varchar(100) not null ,Class_Type varchar(10) not null)");
            s.executeUpdate();
            s= con.prepareStatement("insert into Train_Information values (?,?)");
            Scanner list1 = new Scanner(new File("//Users//divyanshjain//Desktop//Project//ORS//Train_Information//Train Numbers.txt"));
            Scanner list2 = new Scanner(new File("//Users//divyanshjain//Desktop//Project//ORS//Train_Information//Train Names.txt"));
            while (list1.hasNextLine() && list2.hasNextLine()){
               trains.put(Integer.valueOf(list1.nextLine()),list2.nextLine());
            }
            for(int i : trains.keySet()){
                s.setInt(1,i);
                s.setString(2,trains.get(i));
                s.executeUpdate();
            }
            trains.clear();
            s.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Already Done 😃");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}