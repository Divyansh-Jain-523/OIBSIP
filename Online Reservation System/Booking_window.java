package project.ORS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;

class Booking_window {
    HashMap<Integer,String> trains = new HashMap<>();
    JFrame frame;
    JLabel nameL , ageL , trainNumL , trainNameL , trainNameLV , dateL , classL ;
    JTextField nameV , ageV , trainNumV , dateV;
    JButton insert , check , back ;
    JComboBox classV , dd ,mm ,yyyy;
    String Uname, trainName ,  classType ,date;
    int age , trainNum;
    Booking_window(){
        try {
            Connection con = Online_Reservation_System.conn();
            PreparedStatement s = con.prepareStatement("select * from train_information");
            ResultSet r = s.executeQuery();
            while (r.next()){
                trains.put(r.getInt(1),r.getString(2));
            }
            con.close();
            r.close();
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        frame = new JFrame("Booking Window");
        frame.setLayout(null);
        frame.setSize(300,350);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        back = new JButton("Back");
        back.setBounds(50,280,50,40);
        insert = new JButton("Insert");
        classL = new JLabel("Class : ");
        String[] category = {"Select","2S","SL","3 AC","2 AC","1 AC"};
        classV = new JComboBox<>(category);
        classV.setEditable(false);
        classV.setBounds(150,50,50,30);
        dateL = new JLabel("Date : ");
        dateV = new JTextField();
        nameL = new JLabel("Name : ");
        nameL.setBounds(50,50,80,30);
        nameV = new JTextField();
        nameV.setBounds(150,50,100,30);
        ageL = new JLabel("Age : ");
        ageL.setBounds(50,90,80,30);
        ageV = new JTextField();
        ageV.setBounds(150,90,100,30);
        trainNumL = new JLabel("Train no. : ");
        trainNumL.setBounds(50,130,80,30);
        trainNumV = new JTextField();
        trainNumV.setBounds(150,130,50,30);
        trainNameL = new JLabel();
        dateL.setBounds(50,170,80,30);
        dateV.setBounds(150,170,100,30);
        classV.setBounds(150,210,100,30);
        classL.setBounds(50,210,80,30);
        check = new JButton("Check");
        check.setBounds(190,130,65,30);
        trainNameL.setBounds(50,250,50,30);
        trainNameLV = new JLabel();
        trainNameLV.setBounds(100,250,180,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Home_Page();
            }
        });
        check.addActionListener(e -> {
            int tempnum = Integer.parseInt(trainNumV.getText());
            String trainNametemp = trains.get(tempnum);
            if(trainNametemp != null) {
                trainNameLV.setText(trainNametemp);
                trainName = trainNameLV.getText();
                trainNameL.setText("Train : ");
                insert.setBounds(120,290,80,30);
            }
            else {
                JOptionPane.showMessageDialog(new JOptionPane(), "Invalid Train Number!!!");
                trainNumV.setText(null);
            }
        });
        insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Uname = nameV.getText();
                age = Integer.parseInt(ageV.getText());
                trainNum = Integer.parseInt(trainNumV.getText());
                date = dateV.getText();
                classType = String.valueOf(classV.getSelectedItem());
                trainName = trainNameLV.getText();
                if (Uname != null && age > 0 && trainName != null && classType != "Select" && date != null) {
                        frame.dispose();
                        new Confirm_Ticket(Uname,age,trainNum,trainName, date,classType);
                }
            }
        });
        frame.add(trainNumV);
        frame.add(trainNumL);
        frame.add(trainNameLV);
        frame.add(trainNameL);
        frame.add(back);
        frame.add(check);
        frame.add(ageL);
        frame.add(nameL);
        frame.add(nameV);
        frame.add(ageV);
        frame.add(classL);
        frame.add(classV);
        frame.add(dateL);
        frame.add(dateV);
        frame.add(insert);
    }
}
