package project.ORS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home_Page {
    JButton bookTicket, cancelTicket , exit;
    JLabel label;
    JFrame frame;
    Home_Page(){
        frame = new JFrame("Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setSize(300,300);
        label = new JLabel("Select an Action");
        label.setBounds(50,50,150,60);
        bookTicket = new JButton("Book Ticket");
        cancelTicket = new JButton("Cancellation form");
        exit = new JButton("Exit");
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        label.setFont(new Font("Algerian", 1,18));
        exit.setBounds(0,230,50,40);
        bookTicket.setBounds(75,100,150,50);
        cancelTicket.setBounds(75,150,150,50);
        frame.add(bookTicket);
        frame.add(cancelTicket);
        frame.add(label);
        frame.add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(new JOptionPane(),"Are you want to Exit ? ","Exit",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(a == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }

        });
        bookTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Booking_window();

            }
        });
        cancelTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new Cancellation_Form();
                }
        });
    }
}
