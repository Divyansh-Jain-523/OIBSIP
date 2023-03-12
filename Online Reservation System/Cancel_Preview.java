package project.ORS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Cancel_Preview {
    JFrame frame;
    JLabel pnrL  , pnrV , nameL , nameV , ageL , ageV , classL , classV , dateL , dateV , trainL , trainV , glitch;
    JButton back , cancel;
    Cancel_Preview(Long pnr , String pName , short age , String date , String train ,String classType){
        frame = new JFrame("Cancel ticket");
        frame.setSize(380,380);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        pnrL = new JLabel("PNR : ");
        pnrL.setBounds(50,50,60,30);
        pnrV = new JLabel(String.valueOf(pnr));
        pnrV.setBounds(120,50,80,30);
        ageL = new JLabel("Age : ");
        ageL.setBounds(50,90,60,30);
        ageV = new JLabel(String.valueOf(age));
        ageV.setBounds(120,90,80,30);
        nameL = new JLabel("Name : ");
        nameL.setBounds(50,130,60,30);
        nameV = new JLabel(pName);
        nameV.setBounds(120,130,80,30);
        classL = new JLabel("Class : ");
        classL.setBounds(50,170,60,30);
        classV = new JLabel(classType);
        classV.setBounds(120,170,80,30);
        trainL = new JLabel("Train : ");
        trainL.setBounds(50,210,60,30);
        trainV = new JLabel(train);
        trainV.setBounds(120,210,200,30);
        dateL = new JLabel("Date of Journey : ");
        dateL.setBounds(50,250,150,30);
        dateV = new JLabel(date);
        dateV.setBounds(200,250,100,30);
        back = new JButton("Back");
        back.setBounds(30,300,60,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Home_Page();
            }
        });
        cancel = new JButton("Cancel Ticket");
        cancel.setBounds(135,300,120,30);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(new JOptionPane(),"Cancel Ticket","Confirm",JOptionPane.OK_CANCEL_OPTION);
                if (res == JOptionPane.OK_OPTION){
                    Connection con = Online_Reservation_System.conn();
                    try{
                        PreparedStatement s = con.prepareStatement("delete from tickets where PNR = ?");
                        s.setLong(1,pnr);
                        s.executeUpdate();
                        JOptionPane.showMessageDialog(new JOptionPane(),"Ticket Successfully cancelled!!!");
                        frame.dispose();
                        s.close();
                        con.close();
                        new Home_Page();
                    }
                    catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        glitch = new JLabel();
        frame.add(pnrL);
        frame.add(trainV);
        frame.add(trainL);
        frame.add(classL);
        frame.add(classV);
        frame.add(nameL);
        frame.add(nameV);
        frame.add(ageL);
        frame.add(ageV);
        frame.add(pnrV);
        frame.add(dateL);
        frame.add(dateV);
        frame.add(back);
        frame.add(cancel);
        frame.add(glitch);
    }
}
