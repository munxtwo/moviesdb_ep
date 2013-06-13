package com.moviesdb;

import java.util.ArrayList;

public class Movie {
	
	private int id;
	private String name;
	private int releaseYear;
	private String status;
	private String type;
	
	public Movie(ArrayList<String> data) {
		name = data.get(0);
		if (data.get(1).isEmpty()) {
			releaseYear = 0;
		}
		else {
			releaseYear = Integer.parseInt(data.get(1));
		}
		status = data.get(2);
		type = data.get(3);
	}
	
	public Movie(int id, String engName, int releaseYear, String status, String type) {
		this.setId(id);
		this.name = engName;
		this.releaseYear = releaseYear;
		this.status = status;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return String.format("Movie info: %s, %d, %s, %s", name, releaseYear, status, type);
	}

}
