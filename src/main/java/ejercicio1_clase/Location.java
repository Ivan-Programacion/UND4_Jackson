package ejercicio1_clase;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Location implements Serializable {
	private Street street;
	private String city;
	private String state;
	private String country;
	private String postcode;
	@JsonIgnore
	private Coordiantes coordinates;
	private Timezone timezone;

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Coordiantes getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordiantes coordinates) {
		this.coordinates = coordinates;
	}

	public Timezone getTimezone() {
		return timezone;
	}

	public void setTimezone(Timezone timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "Location [street=" + street + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", postcode=" + postcode + ", coordinates=" + coordinates + ", timezone=" + timezone + "]";
	}
}
