package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class Calculate_Bill extends JFrame implements ActionListener {
  Choice meternumcho,monthcho;
  JTextField unittxt;
  JButton submit,cancel;
  JLabel nametxt,addresstxt;
    Calculate_Bill()
    {
        super("Calculate Bill");
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(214,195,247));
        add(panel);

        JLabel heading=new JLabel("Calculate Bill");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meternum=new JLabel("Meter Number");
        meternum.setBounds(50,80,100,20);
        panel.add(meternum);

        meternumcho=new Choice();
        try
        {
          Database c=new Database();
            ResultSet resultset=c.statement.executeQuery("select * from new_customer");
            while (resultset.next())
            {
                meternumcho.add(resultset.getString("meter_no"));
            }
        }catch (Exception e1)
        {
            e1.printStackTrace();
        }
        meternumcho.setBounds(180,80,100,20);
        panel.add(meternumcho);

        JLabel name=new JLabel("Name");
        name.setBounds(50,120,100,20);
        panel.add(name);

        nametxt=new JLabel("");
        nametxt.setBounds(180,120,150,20);
        panel.add(nametxt);

        JLabel address=new JLabel("Address");
        address.setBounds(50,160,100,20);
        panel.add(address);

        addresstxt =new JLabel("");
        addresstxt.setBounds(180,160,150,20);
        panel.add(addresstxt);

        try
        {
          Database c=new Database();
          ResultSet resultset1=c.statement.executeQuery("select * from New_Customer where meter_no='"+meternumcho.getSelectedItem()+"'");
          while (resultset1.next())
          {
              nametxt.setText(resultset1.getString("name"));
              addresstxt.setText(resultset1.getString("address"));
          }
        }catch (Exception e2)
        {
            e2.printStackTrace();
        }

        meternumcho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try
                {
                    Database c=new Database();
                    ResultSet resultset1=c.statement.executeQuery("select * from New_Customer where meter_no='"+meternumcho.getSelectedItem()+"'");
                    while (resultset1.next())
                    {
                        nametxt.setText(resultset1.getString("name"));
                        addresstxt.setText(resultset1.getString("address"));
                    }
                }catch (Exception e2)
                {
                    e2.printStackTrace();
                }
            }
        });

        JLabel unit=new JLabel("Unit Consumed");
        unit.setBounds(50,200,100,20);
        panel.add(unit);


        unittxt=new JTextField();
        unittxt.setBounds(180,200,100,20);
        panel.add(unittxt);

        JLabel month=new JLabel("Months");
        month.setBounds(50,240,100,20);
        panel.add(month);

        monthcho=new Choice();
        monthcho.add("January");
        monthcho.add("February");
        monthcho.add("March");
        monthcho.add("April");
        monthcho.add("May");
        monthcho.add("June");
        monthcho.add("July");
        monthcho.add("August");
        monthcho.add("September");
        monthcho.add("Octomber");
        monthcho.add("November");
        monthcho.add("December");

        monthcho.setBounds(180,240,100,20);
        panel.add(monthcho);

//
//        submit=new JButton("Submit");
//        submit.setBounds(80,300,100,25);
//        panel.add(submit);

        submit=new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        cancel=new JButton("Cancel");
        cancel.setBounds(200,300,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        panel.add(cancel);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon imageicon=new ImageIcon(ClassLoader.getSystemResource("com/icons/budget.png"));
        Image l1=imageicon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon i2=new ImageIcon(l1);
        JLabel l3=new JLabel(i2);
        add(l3,"East");


       setSize(650,400);
       setLocation(400,200);
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==submit)
        {
           String smeterno=meternumcho.getSelectedItem();
           String sunit=unittxt.getText();
           String smonth=monthcho.getSelectedItem();
           int total_bill=0;
           int unit=Integer.parseInt(sunit);
           String query_txt="select * from tax";
           try
           {
             Database c=new Database();
             ResultSet resultset=c.statement.executeQuery(query_txt);
             while (resultset.next())
             {
                 total_bill+=unit+Integer.parseInt(resultset.getString("cost_per_unit"));
                 total_bill +=Integer.parseInt(resultset.getString("meter_rent"));
                 total_bill +=Integer.parseInt(resultset.getString("service_charg"));
                 total_bill +=Integer.parseInt(resultset.getString("swacch_bharat"));
                 total_bill +=Integer.parseInt(resultset.getString("fixed_tax"));
             }
           }catch(Exception e1)
           {
               e1.printStackTrace();
           }

           String query_total_bill="insert into bill values('"+smeterno+"','"+smonth+"','"+sunit+"','"+total_bill+"','Not Paid')";

           try
           {
              Database c=new Database();
              c.statement.executeUpdate(query_total_bill);

              JOptionPane.showMessageDialog(null,"Customer bill UpdatedSuccessfully");
              setVisible(false);
           }catch (Exception e6)
           {
               e6.printStackTrace();
           }
        }else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Calculate_Bill();

    }
}
