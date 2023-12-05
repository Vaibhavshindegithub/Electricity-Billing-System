package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class Signup  extends JFrame implements ActionListener {
   Choice loginAs;
   JTextField t1,t2,t3,t4,t5;

   JButton create,back;
    Signup()
    {
        super("Signup");

        getContentPane().setBackground(Color.pink);
        JLabel createAs=new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        loginAs=new Choice();
        loginAs.add("Admin");
        loginAs.add("Customer");
        loginAs.setBounds(170,50,120,20);
        add(loginAs);

        JLabel meterName=new JLabel("Meter Number");
        meterName.setBounds(30,100,125,20);
        meterName.setVisible(false);
        add(meterName);

        t1=new JTextField();
        t1.setBounds(170,100,125,20);
        t1.setVisible(false);
        add(t1);

        JLabel Employer=new JLabel("Employer Id");
        Employer.setBounds(30,100,125,20);
        Employer.setVisible(true);
        add(Employer);

        t2=new JTextField();
        t2.setBounds(170,100,125,20);
        t2.setVisible(true);
        add(t2);

        JLabel username=new JLabel("User Name");
        username.setBounds(30,140,125,20);
        add(username);

        t3=new JTextField();
        t3.setBounds(170,140,125,20);
        add(t3);



        JLabel name=new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        t4=new JTextField("");
        t4.setBounds(170,180,125,20);
        add(t4);

        t1.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
              try
              {
               Database c=new Database();
                  ResultSet resultset=c.statement.executeQuery("select * from signup where meter_number='"+t1.getText()+"'");
                 if (resultset.next())
                  {
                      t4.setText(resultset.getString("name"));
                  }
              }catch (Exception E)
              {
                  E.printStackTrace();
              }
            }
        });

        JLabel password=new JLabel("Password");
        password.setBounds(30,220,125,20);
        add(password);

        t5=new JTextField();
        t5.setBounds(170,220,125,20);
        add(t5);

        loginAs.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user=loginAs.getSelectedItem();
               if( user.equals("Customer"))
               {
                  Employer.setVisible(false);
                   t4.setEditable(false);
                  t2.setVisible(false);
                  meterName.setVisible(true);
                 t1.setVisible(true);
               }
               else {
                   Employer.setVisible(true);
                   t2.setVisible(true);
                   meterName.setVisible(false);
                   t1.setVisible(false);

               }
            }
        });

        create=new JButton("Create");
        create.setBounds(50,285,120,25);
        create.addActionListener(this);
        create.setBackground(Color.MAGENTA);
        create.setForeground(Color.black);
        add(create);

        back=new JButton("Back");
        back.setBounds(200,285,120,25);
        back.setBackground(Color.MAGENTA);
        back.addActionListener(this);
        back.setForeground(Color.black);
        add(back);

        ImageIcon profileicon2=new ImageIcon(ClassLoader.getSystemResource("com/Icons/Signup.jpg"));
        Image profiletwo=profileicon2.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon profileone=new ImageIcon(profiletwo);
        JLabel profilelable1=new JLabel(profileone);
        profilelable1.setBounds(330,50,250,250);
        add(profilelable1);



        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource()== create)
     {
         String loginChoise=loginAs.getSelectedItem();
         String Suser_Name=t3.getText();
         String Sname=t4.getText();
         String Spassword=t5.getText();
         String Smeter=t1.getText();

         try
         {
             Database d=new Database();
             String query=null;
             if(loginChoise.equals("Admin")) {
                 query = "insert into signup value('" + Smeter + "','" + Suser_Name + "','" + Sname + "','" + Spassword + "','" + loginChoise + "')";
                 d.statement.executeUpdate(query);
             }
             else {
                 query="update signup set username='"+Suser_Name+"','"+Spassword+"',user_type='"+loginChoise+"',where meter_number='"+Smeter+"'";
             }
             JOptionPane.showMessageDialog(null,"Account Created");
             setVisible(false);
             new Login();
         }catch(Exception e1)
         {
             e1.printStackTrace();
         }
     }
     else if(e.getSource()== back)
        {
           setVisible(false);
           new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }



}

