package com.example.phonebook;
public class Contacts//pojo class
{
	private String personname,mobileno1,mobileno2,homeaddress,emailaddress,cityname,statename;
	
	public Contacts(String personname,String mobileno1,String mobileno2,String emailaddress,String homeaddress)
	{
		this.personname=personname;
		this.mobileno1=mobileno1;
		this.mobileno2=mobileno2;
		this.emailaddress=emailaddress;
		this.homeaddress=homeaddress;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getMobileno1() {
		return mobileno1;
	}

	public void setMobileno1(String mobileno1) {
		this.mobileno1 = mobileno1;
	}

	public String getMobileno2() {
		return mobileno2;
	}

	public void setMobileno2(String mobileno2) {
		this.mobileno2 = mobileno2;
	}

	public String getHomeaddress() {
		return homeaddress;
	}

	public void setHomeaddress(String homeaddress) {
		this.homeaddress = homeaddress;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
}
