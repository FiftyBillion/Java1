/*
 * Assignment 8
 * the user can set/replace the information of an employee
 * this class can also get the information of an employee
 * @author Po-I Wu
 * @version 20170308
 */
public class Employee {
	private String surName = "unknown";
	private String givenName = "unknown";
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	private String streetAddress = "Street address not set yet";
	private String city = "City not set yet";
	private String state = "State not set yet";
	private String zipCode = "Zipcode not set yet";
	private String phoneNumber = "Phone number not set yet";
	private String jobTitle = "Job title not set yet";
	private double salary;
	
	/*
	 * Constructors
	 */
	public Employee(String SN, String GN) {
		surName = SN;
		givenName = GN;
	}
	public Employee(String SN, String GN, int BY, int BM, int BD) {
		this(SN, GN);
		birthYear = BY;
		birthMonth = BM;
		birthDay = BD;
	}
	public Employee(String SN, String GN, int BY, int BM, int BD, double SA) {
		this(SN, GN, BY, BM, BD);
		salary = SA;
	}
	
	/*
	 * Accessors
	 */
	public String getName() {
		return surName + ", " + givenName;
	}
	public String getSurName() {
		return surName;
	}
	public String getGivenName() {
		return givenName;
	}
	public String getBirthDate() {
		String monthDisplay = "" + birthMonth;
		String dayDisplay = "" + birthDay;
		String yearDisplay = "" + birthYear;
		if (birthYear < 10) {               // If not enter, set default
			yearDisplay = "000" + birthYear;
		}
		if (birthMonth < 10) {
			monthDisplay = "0" + birthMonth;
		}
		if (birthDay < 10) {
			dayDisplay = "0" + birthDay;
		}
		String birthDateDisplay = yearDisplay + "-" + monthDisplay + "-" + dayDisplay;
		return birthDateDisplay;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public String getPhoneNumber() {
		if (phoneNumber != "Phone number not set yet")
			return phoneNumber.substring(0, 3)+"-"+phoneNumber.substring(3,6)+"-"+phoneNumber.substring(6);
		else
			return phoneNumber;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public String getSalary() {
		return String.format("$%.2f", salary);
	}
	public String toString() {
		String name = getName() + "\n";
		String address = getStreetAddress() + "\n" + getCity() + "\n" + getState() + " " +getZipCode();
		return name + address;
	}
	public boolean equals(Employee other) {
		if (this.getName().equals(other.getName()) && this.getBirthDate().equals(other.getBirthDate())) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Mutators
	 */
	public void setSurName(String SN) {
		surName = SN;
	}
	public void setGivenName(String GN) {
		givenName = GN;
	}
	public void setBirthYear(int BY) {
		birthYear = BY;
	}
	public void setBirthMonth(int BM) {
		birthMonth = BM;
	}
	public void setBirthDay(int BD) {
		birthDay = BD;
	}
	public void setStreetAddress(String SA) {
		streetAddress = SA;
	}
	public void setCity(String CT) {
		city = CT;
	}
	public void setState(String ST) {
		state = ST;
	}
	public void setZipCode(String ZC) {
		zipCode = ZC;
	}
	public void setPhoneNumber(String PN) {
		phoneNumber = PN;
	}
	public void setJobTitle(String JT) {
		jobTitle = JT;
	}
	public void setSalary(double SA) {
		salary = SA;
	}
	public void raise(int percent) {
		salary *= 1 + percent/100;
	}
}
