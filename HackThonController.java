package com.db.hackathon.controller;

// Database imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.hackathon.model.User;

@Controller
public class HackThonController {
	
	/*@Autowired
	private HackathonApplicationDAO hackathonApplicationDAO;
	
*/
   @RequestMapping("/")
   public String root() {
      return "index";
   }

   @RequestMapping("/index")
   public String index() {
      return "index";
   }
   
   @RequestMapping("/carbon")
   public String carbon() {
      return "carbon_cal";
   }
   
   @RequestMapping("/carbonCal")
   public String carbon_val(User user){
	   System.out.println("helloooo --->" + user.getElectricity());
	   Connection c = null;

	   try {
		   System.out.println("In try block");
		   //connect to the database
		   c = DriverManager.getConnection("jdbc:postgresql://juggernauts.postgres.database.azure.com:5432/postgres?user=webuser1@juggernauts&password=webuser1&sslmode=require");

		   System.out.println("connection established");

		   final String SQL_INSERT_HOUSE = "INSERT INTO user_house (id, userid, created, electricity, natural_gas, heating_oil, coal, propane, wood) VALUES (nextval('serial'),?,?,?,?,?,?,?,?)";
		   PreparedStatement prepared_stmt_h = c.prepareStatement(SQL_INSERT_HOUSE);		   
		   
		   // user_house
		   prepared_stmt_h.setInt(1, 3);
		   prepared_stmt_h.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
		   prepared_stmt_h.setInt(3, user.getElectricity());
		   prepared_stmt_h.setInt(4, user.getNatural_gas());
		   prepared_stmt_h.setInt(5, user.getHeating_oil());
		   prepared_stmt_h.setInt(6, user.getCoal());
		   prepared_stmt_h.setInt(7, user.getPropane());
		   prepared_stmt_h.setInt(8, user.getWood());
		   int row_h = prepared_stmt_h.executeUpdate();
		   System.out.println(row_h);
		   
		   // user_transport
		   final String SQL_INSERT_TRANSPORT = "INSERT INTO user_transport (id, userid, created, vehicle_type, Mileage, LPG) VALUES (nextval('serial'),?,?,?,?,?)";
		   PreparedStatement prepared_stmt_t = c.prepareStatement(SQL_INSERT_TRANSPORT);
		   prepared_stmt_t.setInt(1, 3);
		   prepared_stmt_t.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
		   prepared_stmt_t.setString(3, user.getVehicle_type());
		   prepared_stmt_t.setInt(4, user.getMileage());
		   prepared_stmt_t.setInt(5, user.getLpg());
		   int row_t = prepared_stmt_t.executeUpdate();
		   System.out.println(row_t);
		   
		   
		   // user_personal
		   final String SQL_INSERT_PERSONAL = "INSERT INTO user_personal (id, userid, created, water_usage, diet, dairy, fruit, snaks, drink) VALUES (nextval('serial'),?,?,?,?,?,?,?,?)";
		   PreparedStatement prepared_stmt_p = c.prepareStatement(SQL_INSERT_PERSONAL);
		   prepared_stmt_p.setInt(1, 3);
		   prepared_stmt_p.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
		   prepared_stmt_p.setInt(3, user.getWater_usage());
		   prepared_stmt_p.setInt(4, user.getDiet());
		   prepared_stmt_p.setInt(5, user.getDairy());
		   prepared_stmt_p.setInt(6, user.getFruit());
		   prepared_stmt_p.setInt(7, user.getSnaks());
		   prepared_stmt_p.setInt(8, user.getDrinks());
		   int row_p = prepared_stmt_p.executeUpdate();
		   System.out.println(row_p);
		   
		   c.close();
	   } catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
	   }
	   System.out.println("Successfully loaded values into table");
	   
	   return "carbon_sucess";
   }
   
}
