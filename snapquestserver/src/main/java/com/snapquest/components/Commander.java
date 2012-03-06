package com.snapquest.components;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Commander {

	public static String consume(String data) {
		PreparedStatement ps;
		String sql = "SELECT uid FROM sessions WHERE sessionkey=?";
		Connection con = getTheConnection();
		String sessionKey = parseKey(data);
		System.out.println("se: " + sessionKey);
		if (!sessionKey.equals("")) {
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, sessionKey);
				ResultSet rs = ps.executeQuery();
				String uid = "";
				while (rs.next()) {
					sql = "SELECT uid, mypath, mydesc, uploadtime, item FROM imageshack WHERE uid="
							+ rs.getString(1);
					uid = rs.getString(1);
				}
				if (uid.equals("")) {
					return "";
				}
				String command = parseCommand(data);
				System.out.println("COMMAND: " + command);
				if (command.equals("getmydata")) {

				} else if (command.equals("gettodaysitem")) {

				} else if (command.equals("getlatestuploads")) {
					
				} else if (command.equals("getmyimages")) {

				} else if (command.contains("getfriends")) {

				} else if (command.contains("getuserdata")) {

				} else if (command.contains("rateimage")) {
				
				} else if (command.contains("imagecomments")) {
					
				} else if (command.contains("leaveacomment")) {
					
				} else {
					
				}
					// sql =
					// "SELECT uid, mypath, mydesc, uploadtime, item FROM imageshack WHERE uid="+uid;
					// System.out.println(sql);
					// Statement
					return "";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	private static String parseCommand(String data) {
		try {
			data = data.split(";")[1];
			return data;
		} catch (Exception e) {
			System.out.println("Could not parse command");
		}
		return null;
	}

	private static String parseKey(String data) {
		try {

			String love = data.split("session=")[1];
			love = love.split(";")[0];
			return love;
		} catch (Exception e) {
			System.out.println("Could not parse key");
		}
		return "";
	}

	public static String getUserPictures(String sessionKey) {
		PreparedStatement ps;
		String sql = "SELECT uid FROM sessions WHERE sessionkey=?";
		Connection con = getTheConnection();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, sessionKey);
			ResultSet rs = ps.executeQuery();
			boolean bool = false;
			while (rs.next()) {
				sql = "";
			}
			return sessionKey;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static Connection getTheConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/snapquest", "chris",
					"manhattan");
			return connection;
		} catch (SQLException e) {
			System.out.println("doh!");
			// e.printStackTrace();
		}

		return null;
	}

}
