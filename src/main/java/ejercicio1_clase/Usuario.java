package ejercicio1_clase;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Usuario implements Serializable {
	private String gender;
	private Name name;
	private Location location; // por definir
	private String email;
	@JsonIgnore
	private Login login; // por definir
	private Dob dob;
	private Registered registered;
	private String phone;
	private String cell;
	private Id id;
	@JsonIgnore
	private Picture picture; // por definir
	private String nat;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Dob getDob() {
		return dob;
	}

	public void setDob(Dob dob) {
		this.dob = dob;
	}

	public Registered getRegistered() {
		return registered;
	}

	public void setRegistered(Registered registered) {
		this.registered = registered;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getNat() {
		return nat;
	}

	public void setNat(String nat) {
		this.nat = nat;
	}

	@Override
	public String toString() {
		return "Usuario [gender=" + gender + ", name=" + name + ", location=" + location + ", email=" + email
				+ ", login=" + login + ", dob=" + dob + ", registered=" + registered + ", phone=" + phone + ", cell="
				+ cell + ", id=" + id + ", picture=" + picture + ", nat=" + nat + "]";
	}

	public String datos() {
		return "Nombre: " + name.getFirst() + "\nApellido: " + name.getLast() + "\nEmail: " + email + "\nEdad: "
				+ dob.getAge() + "\nDireccion: C/" + location.getStreet().getName() + ", "
				+ location.getStreet().getNumber() + ", " + location.getCity() + ", " + location.getCountry() + ". "
				+ "CP: " + location.getPostcode() + "\n";
	}
}
