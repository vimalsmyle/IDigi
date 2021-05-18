/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

import com.idigitronics.IDigi.request.vo.CommandGroupRequestVO;
import com.idigitronics.IDigi.request.vo.MeterRequestVO;

/**
 * @author K VimaL Kumar
 *
 */
public class UserDetails {
	
	private int roleID;
	private int blockID;
	private String email;
	private String mobileNumber;
	private String houseNo;
	private int communityID;
	private String userName;
	private int ID;
	private int customerID;
	private String CustomerUniqueID;
	private String communityName;
	private List<CommandGroupRequestVO> pendingCommands;
	private int pendingTransactionID;
	private String blockName;
	private List<MeterRequestVO> meters;
	
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public int getBlockID() {
		return blockID;
	}
	public void setBlockID(int blockID) {
		this.blockID = blockID;
	}
	public String getHouseNo() {
		return houseNo;
	}
	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}
	public int getCommunityID() {
		return communityID;
	}
	public void setCommunity(int communityID) {
		this.communityID = communityID;
	}
	public String getuserName() {
		return userName;
	}
	public void setuserName(String userName) {
		this.userName = userName;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getPendingTransactionID() {
		return pendingTransactionID;
	}
	public void setPendingTransactionID(int pendingTransactionID) {
		this.pendingTransactionID = pendingTransactionID;
	}
	public void setCommunityID(int communityID) {
		this.communityID = communityID;
	}
	public String getCustomerUniqueID() {
		return CustomerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		CustomerUniqueID = customerUniqueID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public List<CommandGroupRequestVO> getPendingCommands() {
		return pendingCommands;
	}
	public void setPendingCommands(List<CommandGroupRequestVO> pendingCommands) {
		this.pendingCommands = pendingCommands;
	}
	public List<MeterRequestVO> getMeters() {
		return meters;
	}
	public void setMeters(List<MeterRequestVO> meters) {
		this.meters = meters;
	}
	
}
