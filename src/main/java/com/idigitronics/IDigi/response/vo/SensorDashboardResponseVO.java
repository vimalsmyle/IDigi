/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * @author vml
 *
 */
public class SensorDashboardResponseVO {
	
	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String customerUniqueID;
	private List<SensorDashboardResponseVO> data;
	
	
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
	public List<SensorDashboardResponseVO> getData() {
		return data;
	}
	public void setData(List<SensorDashboardResponseVO> data) {
		this.data = data;
	}
	
}
