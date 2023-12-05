package  com.electricity.billing.system;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    Splash()
    {

        ImageIcon imageicon1=new ImageIcon(ClassLoader.getSystemResource("com/Icons/Electricity.jpg"));
        Image image1=imageicon1.getImage().getScaledInstance(600,400,Image.SCALE_DEFAULT);
        ImageIcon imageicon2=new ImageIcon(image1);
        JLabel imagelable=new JLabel(imageicon2);
        add(imagelable);
        setSize(600,400);
        setLocation(500,200);
        setVisible(true);
        try
        {
              Thread.sleep(9000);
              setVisible(false);


              new Login();
        }catch (Exception e)
        {
            e.printStackTrace();

        }
    }
    public static void main(String[] args) {
        new Splash();
    }
}
