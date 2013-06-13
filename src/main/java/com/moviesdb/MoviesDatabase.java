package com.moviesdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MoviesDatabase {
	
	private static final String DB_NAME = "MOVIESDB";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/" + DB_NAME + "?user=root&password=password";
	
	private Connection conn;
	private Statement statement;
	
	public void connectDB() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL);
			statement = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot connect to DB");
		}
	}

	public void closeDB() {
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot close DB");
		}
	}
	
	public void createDB() {
		String createDBString = "CREATE DATABASE IF NOT EXISTS " + DB_NAME + " character set utf8";
		try {
			statement.executeUpdate(createDBString);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot create DB");
		}
	}
	
	public void createTables() {
		String createMoviesTableString = "CREATE TABLE IF NOT EXISTS TMOVIES (" +
											"id int not null AUTO_INCREMENT," +
											"name varchar(255) default null," +
											"release_year int(4) default 0," +
											"status varchar(255) default null," +
											"mtype varchar(255) default null," +
											"PRIMARY KEY (id))";
		
		String createMoviesAttrTableString = "CREATE TABLE IF NOT EXISTS TMOVIEATTRIBUTES (" +
												"id int not null AUTO_INCREMENT," +
												"attr_name varchar(255) default null," +
												"attr_value varchar(255) default null," +
												"m_id int not null," +
												"PRIMARY KEY (id)," +
												"FOREIGN KEY (m_id) REFERENCES TMOVIES(id))";
		
		try {
			statement.executeUpdate(createMoviesTableString);
			statement.executeUpdate(createMoviesAttrTableString);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Cannot create tables");
		}
	}
	
	public void insertMoviesData(Movie movieInfo) {
		String insertDataString = String.format("INSERT INTO TMOVIES (name, release_year," +
				"status, mtype) VALUES ('%s', %d, '%s', '%s')", movieInfo.getName(),
				movieInfo.getReleaseYear(),	movieInfo.getStatus(), movieInfo.getType());
		try {
			statement.executeUpdate(insertDataString);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot insert data into Movies table");
		}
	}
	
	public Movie processRow(ResultSet rs) {
		Movie movieInfo = null;
		try {
			movieInfo = new Movie(rs.getInt("id"),
									rs.getString("name"),
									rs.getInt("release_year"),
									rs.getString("status"),
									rs.getString("mtype"));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot read resultset: " + rs.toString());
		}
		return movieInfo;
	}
 
	public Statement getStatement() {
		return statement;
	}

	public Connection getConn() {
		return conn;
	}

	
	public ArrayList<Movie> findAll() {
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		String query = "SELECT * from TMOVIES ORDER BY release_year";
		try {
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				moviesList.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot execute query: " + query);
		}
		return moviesList;
	}
	
	public Movie findById(int id) {
		Movie movie = null;
		String query = "SELECT * from TMOVIES where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				movie = processRow(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot execute query: " + query);
		}
		return movie;
	}
	
	public ArrayList<Movie> findByStatus(String status) {
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		String query = "SELECT * from TMOVIES where status = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				moviesList.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot execute query: " + query);
		}
		return moviesList;
	}
	
	public ArrayList<Movie> findByColumn(String columName, String value) {
		ArrayList<Movie> moviesList = new ArrayList<Movie>();
		String query = "SELECT * from TMOVIES where " + columName + "= ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, value);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				moviesList.add(processRow(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot execute query: " + query);
		}
		return moviesList;
	}
	
}
