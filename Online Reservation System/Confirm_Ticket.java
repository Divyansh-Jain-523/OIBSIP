package project.ORS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class Confirm_Ticket {
    JFrame frame;
    JLabel nameL , ageL , trainNameL , trainNameLV , dateL , classL , classLV;
    JTextField nameV , ageV , dateV ;
    JButton confirm , back ;
    Confirm_Ticket(String Uname , int age , int trainNum , String trainName, String date , String classType){
        frame = new JFrame("Confirm Details");
        frame.setLayout(null);
        frame.setSize(300,350);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
        nameL = new JLabel("Name : ");
        nameL.setBounds(50,40,80,30);
        ageL = new JLabel("Age : ");
        ageL.setBounds(50,80,80,30);
        trainNameL = new JLabel("Train : ");
        trainNameL.setBounds(50,120,80,30);
        trainNameLV = new JLabel("("+trainNum+") "+trainName);
        trainNameLV.setBounds(120,120,150,30);
        dateL = new JLabel("Date : ");
        dateL.setBounds(50,160,80,30);
        classL = new JLabel("Class : ");
        classL.setBounds(50,200,80,30);
        classLV = new JLabel(classType);
        classLV.setBounds(120,200,100,30);
        nameV = new JTextField();
        nameV.setBounds(120,40,100,30);
        nameV.setText(Uname);
        nameV.setEditable(false);
        ageV = new JTextField(String.valueOf(age));
        ageV.setEditable(false);
        ageV.setBounds(120,80,100,30);
        dateV = new JTextField(String.valueOf(date));
        dateV.setBounds(120,160,100,30);
        dateV.setEditable(false);
        confirm = new JButton("Confirm");
        back = new JButton("Back");
        back.setBounds(50,280,50,40);
        confirm.setBounds(120,280,80,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Booking_window();
                }
        });
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                long pnr = r.nextLong(10000000,100000000);
                try {
                    Connection con = Online_Reservation_System.conn();
                    PreparedStatement s = con.prepareStatement("insert into tickets values (?,?,?,?,?,?)");
                    s.setLong(1,pnr);
                    s.setString(2,Uname);
                    s.setInt(3,age);
                    s.setString(4, date);
                    s.setString(5, trainNameLV.getText());
                    s.setString(6,classType);
                    s.executeUpdate();
                    JOptionPane.showMessageDialog(new JOptionPane(),"Your PNR is : "+pnr);
                    con.close();
                    s.close();
                    frame.dispose();
                    new Home_Page();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        frame.add(confirm);
        frame.add(back);
        frame.add(dateV);
        frame.add(classLV);
        frame.add(classL);
        frame.add(nameL);
        frame.add(dateL);
        frame.add(nameV);
        frame.add(trainNameLV);
        frame.add(ageL);
        frame.add(ageV);
        frame.add(trainNameL);
    }
}