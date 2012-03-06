package com.snapquest.components;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Authenticate {

	public Authenticate() {
		System.out.println("whens this called?");
	}

	public static String authenticate(String data) {
		String username = getUsername(data);
		String password = clearTextPassword(data);
		System.out.println("username:" + username + ", password:" + password);
		String sessionKey = auth(username, password);
		return sessionKey;
	}

	private static String auth(String username, String password) {
		PreparedStatement ps;
		String sql = "SELECT id FROM users WHERE username=? AND password=? LIMIT 1";
		Connection con = getTheConnection();
		String sessionKey = "";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			System.out.println(ps.toString());
			ResultSet rs = ps.executeQuery();
			boolean bool = false;
			while (rs.next()) {
				sessionKey = nextSessionId();
				String insert = "insert into sessions(uid,sessionkey,checkin) values ('"
						+ rs.getString(1) + "','" + sessionKey+"',now())";
				String remove = "delete from sessions where uid="+rs.getString(1);
				System.out.println(insert);
				Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				st.executeUpdate(remove);
				st.executeUpdate(insert);
				bool = true;
			}
			return sessionKey;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private static String nextSessionId() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	private static String clearTextPassword(String data) {
		try {
			data = data.split(";password=")[1];
			data = data.split(";")[0];

			return data;
		} catch (Exception e) {
			System.out.println("Malformed password");
		}

		return null;
	}

	private static String getUsername(String data) {
		System.out.println(data);
		try {
			data = data.split("username=")[1];
			System.out.println("d " + data);
			data = data.split(";")[0];
			System.out.println();
			return data;
		} catch (Exception e) {
			System.out.println("Malformed username");
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
