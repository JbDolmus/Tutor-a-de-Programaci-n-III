package domain;

import java.time.LocalDate;

public class Client {
	private int id;
	private String name;
	private String email;
	private String address;
	private int postal_code;
	private double latitude;
	private double longitude;
	private LocalDate birthdate;
	
	public Client() {
		
	}
	public Client(int id, String name, String email, String address, int postal_code, double latitude, double longitude,
			LocalDate birthdate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.postal_code = postal_code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.birthdate = birthdate;
	}
	
	public Client(String name, String email, String address, int postal_code, double latitude, double longitude,
			LocalDate birthdate) {
		this.id = 0;
		this.name = name;
		this.email = email;
		this.address = address;
		this.postal_code = postal_code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.birthdate = birthdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPostal_code() {
		return postal_code;
	}
	public void setPostal_code(int postal_code) {
		this.postal_code = postal_code;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
}