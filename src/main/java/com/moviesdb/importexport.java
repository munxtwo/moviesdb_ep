package com.moviesdb;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.moviesdb.domain.Movie;


public class importexport {
	
	public static final String FILENAME = "src/main/resources/moviesdb_data.txt";
	
//	public static void loadMovieTable(String fileName, MoviesDatabase db) {
//		File dataFile = new File(fileName);
//		BufferedReader reader;
//		try {
//			reader = new BufferedReader(new FileReader(dataFile));
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				String[] data = line.split(",");
//				ArrayList<String> list = new ArrayList<String>();
//				for (String s : data) {
//					s = s.replace("'", "\\'");
//					list.add(s.trim());
//				}
//				try {
//					Movie movieInfo = new Movie(list);
//					db.insertMoviesData(movieInfo);
//				} catch (NumberFormatException e) {
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		MoviesDatabase db = new MoviesDatabaseImpl();
//		db.connectDB();
////		db.createDB();
////		db.createTables();
//		loadMovieTable(FILENAME, db);
//		db.closeDB();
//	}

}
