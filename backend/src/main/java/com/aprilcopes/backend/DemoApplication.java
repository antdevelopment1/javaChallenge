package com.aprilcopes.backend;

import java.sql.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		DemoApplication pro = new DemoApplication();
		
		pro.createConnection();
	}

	void createConnection() {
		try {
			Class.forName ("org.h2.Driver");;
			// Get a connection to database
			Connection myConn = DriverManager.getConnection("jdbc:h2://localhost:8080/task", "root", "Robinhood2598");

			// Create a statement
			Statement myStmt = myConn.createStatement();

			// Excecute SQl query
			ResultSet myRs = myStmt.executeQuery("select * from blog;");

			// Process the results
			while (myRs.next()) {
				System.out.println(myRs.getString("title"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
