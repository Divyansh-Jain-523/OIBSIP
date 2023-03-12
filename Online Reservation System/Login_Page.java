package project.ORS;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_Page {
    JFrame loginPage;
    JLabel idL, passwordL;
    JTextField idV;
    JPasswordField passwordV;
    JButton loginB;
    Login_Page(){
        idV = new JTextField();
        loginB = new JButton("Login");
        loginPage = new JFrame("Login");
        loginPage.setLocationRelativeTo(null);
        passwordV = new JPasswordField();
        idL = new JLabel("Enter User ID : ");
        passwordL = new JLabel("Enter Password : ");
        loginPage.setLayout(null);
        loginPage.setVisible(true);
        loginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginPage.setSize(300,350);
        idL.setBounds(40,50,120,50);
        passwordL.setBounds(40,120,120,50);
        idV.setBounds(150,50,120,50);
        passwordV.setBounds(150,120,120,50);
        loginB.setBounds(125,200,75,60);
        loginPage.add(idL);
        loginPage.add(passwordL);
        loginPage.add(idV);
        loginPage.add(passwordV);
        loginPage.add(loginB);
        loginPage.setResizable(false);
        loginB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (String.valueOf(idV.getText()).equals("User") && String.valueOf(passwordV.getPassword()).equals("123")){
                    new Home_Page();
                    loginPage.dispose();
                }
                else {
                    JOptionPane.showMessageDialog(new JOptionPane(), "Invalid credentials.", "Error", JOptionPane.WARNING_MESSAGE);
                    idV.setText(null);
                    passwordV.setText(null);
                }
            }
        });
    }
}
