package com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Meter_Info extends JFrame implements ActionListener {
    JLabel l1;

    Choice meterlocch ,metertypecho,phsecodecho,billtypecho;

    JButton submit;
    String meternumber1;


    Meter_Info(String meternumber1)
    {
        this.meternumber1=meternumber1;
        JPanel panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(120,150,180));
        add(panel);

        JLabel heading=new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meternumber=new JLabel("Meter Number");
        meternumber.setBounds(50,80,100,20);
        panel.add(meternumber);

        JLabel meternumbertext=new JLabel(meternumber1);
        meternumbertext.setBounds(180,80,150,20);
        panel.add(meternumbertext);

        JLabel meterloc1=new JLabel("Meter Location");
        meterloc1.setBounds(50,120,100,20);
        panel.add(meterloc1);

        meterlocch=new Choice();
        meterlocch.setBounds(180,120,150,20);
        meterlocch.add("Outside");
        meterlocch.add("Inside");
        panel.add(meterlocch);

        JLabel metertype=new JLabel("Meter Type");
        metertype.setBounds(50,160,100,20);
        panel.add(metertype);

        metertypecho=new Choice();
        metertypecho.setBounds(180,160,150,20);
        metertypecho.add("Electric Meter");
        metertypecho.add("Solar Meter");
        metertypecho.add("Smart Meter");
        panel.add( metertypecho);

        JLabel phasecode=new JLabel("Phase Code");
        phasecode.setBounds(50,200,100,20);
        panel.add(phasecode);

        phsecodecho=new Choice();
        phsecodecho.setBounds(180,200,150,20);
        phsecodecho.add("011");
        phsecodecho.add("022");
        phsecodecho.add("033");
        phsecodecho.add("044");
        phsecodecho.add("055");
        phsecodecho.add("066");
        phsecodecho.add("077");
        phsecodecho.add("088");
        phsecodecho.add("099");
        panel.add( phsecodecho);

        JLabel billtype=new JLabel("Bill Type");
        billtype.setBounds(50,240,100,20);
        panel.add(billtype);

       billtypecho=new Choice();
        billtypecho.setBounds(180,240,150,20);
        billtypecho.add("Normal");
        billtypecho.add("Industrial");
        panel.add( billtypecho);

        JLabel day=new JLabel("30 Days Billing Time....");
        day.setBounds(50,280,150,20);
        panel.add(day);

        JLabel note=new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel note1=new JLabel("By default Bill is Calculated for 30 days only");
        note1.setBounds(50,340,300,20);
        panel.add(note1);

        submit=new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        panel.add(submit);

        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon  i1=new ImageIcon(ClassLoader.getSystemResource("com/icons/details.png"));
        Image i2=i1.getImage().getScaledInstance(230,200,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel i4=new JLabel(i3);
        add(i4,"East");



       setSize(700,500);
       setLocation(400,200);
       setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   if(e.getSource()==submit)
   {
       String smeternum=meternumber1;
       String smeterloc=meterlocch.getSelectedItem();
       String smetertyep=metertypecho.getSelectedItem();
       String sphasecode=phsecodecho.getSelectedItem();
       String sbilltype=billtypecho.getSelectedItem();
       String sday="30";

       String query_meterinfo="insert into Meter_Info values('"+smeternum+"','"+smeterloc+"','"+smetertyep+"','"+sphasecode+"','"+sbilltype+"','"+sday+"')";

       try
       {
            Database c=new Database();
            c.statement.executeUpdate(query_meterinfo);

            JOptionPane.showMessageDialog(null,"Meter Information Submited Successfully...");


            setVisible(false);
       }catch(Exception e1)
       {
           e1.printStackTrace();
       }
   }
   else {
       setVisible(false);
   }
    }

    public static void main(String[] args) {

        new Meter_Info("");
    }
}
