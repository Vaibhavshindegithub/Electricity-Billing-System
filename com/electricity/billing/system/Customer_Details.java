package com.electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Customer_Details extends JFrame implements ActionListener {
  JTextField cnametxt;
  JTable table;
  JButton search,print,close;
  Choice searchmetercho,searchnamecho;
    Customer_Details()
    {
        super("Customer Details");

//        JLabel heading=new JLabel("Customer Details");
//        heading.setBounds(250,10,250,20);
//        heading.setForeground(Color.orange);
//        heading.setFont(new Font("Georgia" ,Font.BOLD,20));
//        add(heading);

        JLabel l1=new JLabel("Search By meter Number");
        l1.setBounds(20,20,150,20);
        add(l1);


        searchmetercho =new Choice();
        searchmetercho.setBounds(180,20,150,20);
        add(searchmetercho);

        try
        {
          Database c=new Database();
            ResultSet resultset=c.statement.executeQuery("select * from new_customer");
            while (resultset.next())
            {
                searchmetercho.add(resultset.getString("meter_no"));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

//        cnametxt=new JTextField("");
//        cnametxt.setBounds(150,50,100,20);
//        add(cnametxt);



        JLabel searchbyname=new JLabel("Search By  Name");
        searchbyname.setBounds(400,20,100,20);
        add(searchbyname);

        searchnamecho=new Choice();
        searchnamecho.setBounds(520,20,150,20);
        add(searchnamecho);

        try
        {
            Database c=new Database();
            ResultSet resultset=c.statement.executeQuery("select * from new_customer");
            while (resultset.next())
            {
                searchnamecho.add(resultset.getString("name"));
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }


        table=new JTable();
        try
        {
          Database c=new Database();
          ResultSet resultset=c.statement.executeQuery("select * from new_customer");
          table.setModel(DbUtils.resultSetToTableModel(resultset));

        }catch(Exception E)
        {
            E.printStackTrace();
        }



        JScrollPane scroll=new JScrollPane(table);
        scroll.setBounds(0,100,700,500);
        scroll.setBackground(Color.white);
        add(scroll);

        search=new JButton("Search");
        search.setBackground(Color.white);
        search.addActionListener(this);
        search.setBounds(20,70,80,20);
        add(search);

       print=new JButton("Print");
        print.setBackground(Color.white);
        print.addActionListener(this);
        print.setBounds(120,70,80,20);
        add(print);

        close=new JButton("Close");
        close.setBackground(Color.white);
        close.addActionListener(this);
        close.setBounds(600,70,80,20);
        add(close);


         setSize(700,500);
         setLayout(null);
         getContentPane().setBackground(new Color(200,150,190));
         setLocation(400,200);
         setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==search)
        {
          String query_search="select * from new_customer where meter_no='"+searchmetercho.getSelectedItem()+"' and name='"+searchnamecho.getSelectedItem()+"'";
         try
         {
             Database c=new Database();
             ResultSet resultSet=c.statement.executeQuery(query_search);
             table.setModel(DbUtils.resultSetToTableModel(resultSet));
         }catch (Exception E)
         {
             E.printStackTrace();
         }
        }
        else if(e.getSource()==print)
        {
            try {
                table.print();
            }catch (Exception E)
            {
                E.printStackTrace();
            }
        }
        else
        {
            setVisible(false);

        }

    }

    public static void main(String[] args) {

        new Customer_Details();

    }
}
