package com.idigitronics.IDigi.request.vo;

public class CustomerSolarMasterRequestVO {
	
	private long customerID;
	private int communityID;
	private int blockID;
	private String houseNumber;
	private String customerUniqueID;
	private long masterCustomerID;
	private long customerSolarMasterID;
	
	
	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public long getMasterCustomerID() {
		return masterCustomerID;
	}
	public void setMasterCustomerID(long masterCustomerID) {
		this.masterCustomerID = masterCustomerID;
	}
	public long getCustomerSolarMasterID() {
		return customerSolarMasterID;
	}
	public void setCustomerSolarMasterID(long customerSolarMasterID) {
		this.customerSolarMasterID = customerSolarMasterID;
	}

}
