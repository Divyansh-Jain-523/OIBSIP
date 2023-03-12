package project.ORS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cancellation_Form {
    JFrame frame;
    JLabel pnrL;
    JTextField pnrV;
    JButton check , back;
    Cancellation_Form(){
        frame = new JFrame("Cancellation Form");
        frame.setSize(300,350);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        pnrL = new JLabel("PNR : ");
        pnrL.setBounds(50,100,60,30);
        pnrV = new JTextField();
        pnrV.setBounds(100,100,120,30);
        check = new JButton("Check");
        check.setBounds(120,150,80,30);
        back = new JButton("Back");
        back.setBounds(30,290,60,30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Home_Page();
            }
        });
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long pnr = Long.parseLong(pnrV.getText());
                Connection con = Online_Reservation_System.conn();
                try {
                    PreparedStatement s = con.prepareStatement("select * from tickets where pnr = ?");
                    s.setLong(1,pnr);
                    ResultSet r = s.executeQuery();
                    r.next();
                    frame.dispose();
                    new Cancel_Preview(r.getLong(1),r.getString(2),r.getShort(3),r.getString(4),r.getString(5),r.getString(6));
                    con.close();
                    s.close();
                    r.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(new JOptionPane(),"Invalid PNR!!!");
                    frame.dispose();
                    new Cancellation_Form();
                }

            }
        });
        frame.add(pnrL);
        frame.add(pnrV);
        frame.add(check);
        frame.add(back);
    }

    public static void main(String[] args) {
        new Cancellation_Form();
    }
}