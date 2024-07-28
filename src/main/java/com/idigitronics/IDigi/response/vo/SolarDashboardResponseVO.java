package com.idigitronics.IDigi.response.vo;

import java.util.List;

public class SolarDashboardResponseVO {
	
	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String customerUniqueID;
	private long readingID;
	private String deviceBlockID;
	private String relayStatus;
	private String rStatus;
	private String yStatus;
	private String bStatus;
	private String LogDate;
	
	private List<SolarDashboardResponseVO> data;

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public String getDeviceBlockID() {
		return deviceBlockID;
	}

	public void setDeviceBlockID(String deviceBlockID) {
		this.deviceBlockID = deviceBlockID;
	}

	public String getRelayStatus() {
		return relayStatus;
	}

	public void setRelayStatus(String relayStatus) {
		this.relayStatus = relayStatus;
	}

	public String getrStatus() {
		return rStatus;
	}

	public void setrStatus(String rStatus) {
		this.rStatus = rStatus;
	}

	public String getyStatus() {
		return yStatus;
	}

	public void setyStatus(String yStatus) {
		this.yStatus = yStatus;
	}

	public String getbStatus() {
		return bStatus;
	}

	public void setbStatus(String bStatus) {
		this.bStatus = bStatus;
	}

	public String getLogDate() {
		return LogDate;
	}

	public void setLogDate(String logDate) {
		LogDate = logDate;
	}

	public List<SolarDashboardResponseVO> getData() {
		return data;
	}

	public void setData(List<SolarDashboardResponseVO> data) {
		this.data = data;
	}

	public long getReadingID() {
		return readingID;
	}

	public void setReadingID(long readingID) {
		this.readingID = readingID;
	}
	
}
