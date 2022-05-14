package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Interrupt {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/egsystem", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readInterrupt() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Interrupt Area</th>" + "<th>Interrupt Start Time</th><th>Interrupt End Time</th><th>Interrupt Hours</th>"
					+ "<th>Interrupt Message</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from interrupts";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String interruptID = Integer.toString(rs.getInt("interruptID"));
				String interruptArea = rs.getString("interruptArea");
				String interruptStartTime = rs.getString("interruptStartTime");
				String interruptEndTime = rs.getString("interruptEndTime");
				String interruptHours = rs.getString("interruptHours");
				String interruptMessage = rs.getString("interruptMessage");
				

				// Add into the html table

				output += "<tr><td><input id='hidinterruptIDUpdate' name='hidinterruptIDUpdate' type='hidden' value='"
						+ interruptID + "'>" + interruptArea + "</td>";
				output += "<td>" + interruptStartTime + "</td>";
				output += "<td>" + interruptEndTime + "</td>";
				output += "<td>" + interruptHours + "</td>";
				output += "<td>" + interruptMessage + "</td>";
				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-interruptID='"
						+ interruptID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Interrupt Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Interrupt
	public String insertInterrupt(String interruptArea, String interruptStartTime, String interruptEndTime, String interruptHours, String interruptMessage) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into interrupts (`interruptID`,`interruptArea`,`interruptStartTime`,`interruptEndTime`,`interruptHours`,`interruptMessage`)"
					+ " values (?, ?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, interruptArea);
			preparedStmt.setString(3, interruptStartTime);
			preparedStmt.setString(4, interruptEndTime);
			preparedStmt.setString(5, interruptHours);
			preparedStmt.setString(6, interruptMessage);
		

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newInterrupt = readInterrupt();
			output = "{\"status\":\"success\", \"data\": \"" + newInterrupt + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Interrupt.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Interrupt
	public String updateInterrupt(String interruptID, String interruptArea, String interruptStartTime, String interruptEndTime, String interruptHours,String interruptMessage
) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE interrupts SET interruptArea=?,interruptStartTime=?,interruptEndTime=?,interruptMessage=?,interruptHours=? WHERE interruptID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, interruptArea);
			preparedStmt.setString(2, interruptStartTime);
			preparedStmt.setString(3, interruptEndTime);
			preparedStmt.setString(4, interruptMessage);
			preparedStmt.setString(5, interruptHours);
			preparedStmt.setInt(6, Integer.parseInt(interruptID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newInterrupt = readInterrupt();
			output = "{\"status\":\"success\", \"data\": \"" + newInterrupt + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Interrupt Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteInterrupt(String interruptID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM interrupts WHERE interruptID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(interruptID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newInterrupt = readInterrupt();
			output = "{\"status\":\"success\", \"data\": \"" + newInterrupt + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Interrupt.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

}
