package ch02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int id;
	private String name;
	private String username;
	private String emeil;
	private Address address;
	private String phone;
	private String website;
	private Company company;
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", emeil=" + emeil + ", address="
				+ address + ", phone=" + phone + ", website=" + website + ", company=" + company + ", getId()="
				+ getId() + ", getName()=" + getName() + ", getUsername()=" + getUsername() + ", getEmeil()="
				+ getEmeil() + ", getAddress()=" + getAddress() + ", getPhone()=" + getPhone() + ", getWebsite()="
				+ getWebsite() + ", getCompany()=" + getCompany() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}

class Address {
	@Getter
	@Setter
	String street;
	String suite;
	String city;
	String zipcode;
	Geo geo;
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", suite=" + suite + ", city=" + city + ", zipcode=" + zipcode + ", geo="
				+ geo + ", getStreet()=" + getStreet() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	class Geo {
		@Getter
		@Setter
		String lat;
		String lng;
		@Override
		public String toString() {
			return "Geo [lat=" + lat + ", lng=" + lng + ", getLat()=" + getLat() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		
	}
}

class Company {
	@Getter
	@Setter
	String name;
	String catchPhrase;
	String bs;
	@Override
	public String toString() {
		return "Company [name=" + name + ", catchPhrase=" + catchPhrase + ", bs=" + bs + ", getName()=" + getName()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}
