package com.xubo.json;

public class Address {
	private String workAddress;
	private String homeAddress;
	public String getWorkAddress() {
		return workAddress;
	}
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address(String workAddress, String homeAddress) {
		super();
		this.workAddress = workAddress;
		this.homeAddress = homeAddress;
	}
	
	public Address() {
	}
	@Override
	public String toString() {
		return "Address [workAddress=" + workAddress + ", homeAddress=" + homeAddress + "]";
	}
	
}
