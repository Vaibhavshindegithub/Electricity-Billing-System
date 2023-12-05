package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
     JTextField usertext,passtext;
     Choice lodinchoise;
     JButton loginButton,cancelButton,signupButton;

    Login()
    {
        super("Login");
        getContentPane().setBackground(Color.PINK);
        JLabel username=new JLabel("UserName");
        username.setBounds(300,60,100,20);
        add(username);

         usertext=new JTextField();
        usertext.setBounds(400,60,150,20);
        add(usertext);

        JLabel password=new JLabel("Password");
        password.setBounds(300,100,100,20);
        add(password);

         passtext=new JTextField();
        passtext.setBounds(400,100,150,20);
        add(passtext);

        JLabel loggin=new JLabel("Login In As");
        loggin.setBounds(300,140,100,20);
        add(loggin);

        lodinchoise=new Choice();
        lodinchoise.add("Admin");
        lodinchoise.add("Customer");
        lodinchoise.setBounds(400,140,150,20);
        add(lodinchoise);

        loginButton =new JButton("Login");
        loginButton.setBounds(330,180,80,30);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton =new JButton("Cancel");
        cancelButton.setBounds(460,180,80,30);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton =new JButton("Signup");
        signupButton.setBounds(400,220,80,30);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileicon=new ImageIcon(ClassLoader.getSystemResource("com/Icons/profile.jpg"));
        Image profiletwo=profileicon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon profileone=new ImageIcon(profiletwo);
        JLabel profilelable=new JLabel(profileone);
        profilelable.setBounds(5,5,250,250);
        add(profilelable);








       setSize(600,300);
       setLocation(500,200);
       setLayout(null);
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==loginButton)
        {
               String susername=usertext.getText();
               String spassword=passtext.getText();
               String user=lodinchoise.getSelectedItem();

               try
               {
                 Database c=new Database();
                 String query="select * from signup where user_name='"+susername+"' and password ='"+spassword+"' and user_type='"+user+"' ";
                   ResultSet resultset=c.statement.executeQuery(query);

                   if(resultset.next())
                   {
                       String meter=resultset.getString("meter_number");
                       setVisible(false);
                       new MainFrame(user,meter);
                   }
                   else {
                       JOptionPane.showMessageDialog(null,"Invalid Login");
                   }
               }catch (Exception E)
               {
                   E.printStackTrace();
               }

        }
        else if(e.getSource()==cancelButton)
        {
            setVisible(false);

        }
        else if(e.getSource()==signupButton)
        {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {

        new Login();
    }
}
