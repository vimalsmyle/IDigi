/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class ConfigurationRequestVO {
	
	private String miuID;
	private int communityID;
	private int blockID;
	private int customerID;
	private String customerUniqueID;
	private int customerMeterID;
	private int transactionID;
	private int cmd_status;
	
	private List<CommandGroupRequestVO> commands;
	
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
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public int getCustomerMeterID() {
		return customerMeterID;
	}
	public void setCustomerMeterID(int customerMeterID) {
		this.customerMeterID = customerMeterID;
	}
	public List<CommandGroupRequestVO> getCommands() {
		return commands;
	}
	public void setCommands(List<CommandGroupRequestVO> commands) {
		this.commands = commands;
	}
	public int getCmd_status() {
		return cmd_status;
	}
	public void setCmd_status(int cmd_status) {
		this.cmd_status = cmd_status;
	}
	
}
