package com.electricity.billing.system;

import java.sql.*;

public class Database {
    Connection connection;
    Statement statement;
    Database() throws SQLException {
       connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Bill_System","root" ,"Vaibhav#0079");
        statement=connection.createStatement();



    }

}
