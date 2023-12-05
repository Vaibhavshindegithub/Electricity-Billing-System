package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class New_Customer  extends JFrame  implements ActionListener {
  JTextField cusnametext,addresstext,citytext,statetext,emailtext,phonetext;
  JLabel   customername,meternum,address,city,state,email,phone,meternotext;

  JButton next,cancel;
    New_Customer()
    {
        super("New Customer");

        JPanel panel1=new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(120,150,180));
        add(panel1);

        JLabel label1=new JLabel("New Customer");
        label1.setBounds(150,10,400,25);
        label1.setForeground(Color.green);
        label1.setFont(new Font("tohoma",Font.BOLD,30));
        panel1.add(label1);

        customername=new JLabel("Customer Name");
        customername.setBounds(50,80,100,20);
        panel1.add(customername);

        cusnametext=new JTextField();
        cusnametext.setBounds(180,80,130,20);
        panel1.add(cusnametext);

        meternum=new JLabel("Meter Number");
        meternum.setBounds(50,120,100,20);
        panel1.add(meternum);

        meternotext=new JLabel("");
        meternotext.setBounds(180,120,130,20);
        panel1.add(meternotext);

        Random rn=new Random();
        long number=rn.nextLong() % 100000;
        meternotext.setText(" "+Math.abs(number));

       address=new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel1.add(address);

        addresstext=new JTextField();
        addresstext.setBounds(180,160,130,20);
        panel1.add(addresstext);

        city=new JLabel("City");
        city.setBounds(50,200,100,20);
        panel1.add(city);

        citytext=new JTextField();
        citytext.setBounds(180,200,130,20);
        panel1.add(citytext);

        state=new JLabel("State");
        state.setBounds(50,240,100,20);
        panel1.add(state);

        statetext=new JTextField();
        statetext.setBounds(180,240,130,20);
        panel1.add(statetext);

        email=new JLabel("Email");
        email.setBounds(50,280,100,20);
        panel1.add(email);

        emailtext=new JTextField();
        emailtext.setBounds(180,280,130,20);
        panel1.add(emailtext);

        phone=new JLabel("Phone");
        phone.setBounds(50,320,100,20);
        panel1.add(phone);

        phonetext=new JTextField();
        phonetext.setBounds(180,320,130,20);
        panel1.add(phonetext);

        next=new JButton("Next");
        next.setBounds(120,390,100,25);
        next.setBackground(Color.black);
        next.setForeground(Color.orange);
        next.addActionListener(this);
        panel1.add(next);

        cancel=new JButton("Cancel");
        cancel.setBounds(230,390,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.orange);
       cancel.addActionListener(this);
        panel1.add(cancel);


        setLayout(new BorderLayout());
        add(panel1,"Center");

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("com/icons/boy.png"));
        Image i2=i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel imglable=new JLabel(i3);
        add(imglable,"West");

        setSize(700,500);
        setLocation(400,200);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==next)
        {
            String sname=cusnametext.getText();
            String smeter=meternotext.getText();
            String saddress=addresstext.getText();
            String scity=citytext.getText();
            String sstate=statetext.getText();
            String semail=emailtext.getText();
            String sphone=phonetext.getText();

            String query_customer="insert into New_Customer values('"+sname+"','"+smeter+"','"+saddress+"','"+scity+"','"+sstate+"','"+semail+"','"+sphone+"')";
            String query_signup="insert into signup values('"+smeter+"','','"+sname+"','','')";
            try
            {
                Database c=new Database();
                c.statement.executeUpdate(query_customer);
                c.statement.executeUpdate(query_signup);

                JOptionPane.showMessageDialog(null,"Customer details added successfully");
                setVisible(false);
                new Meter_Info(smeter);
            }catch ( Exception E1)
            {
                E1.printStackTrace();
            }

        }
        else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new New_Customer();

    }
}
