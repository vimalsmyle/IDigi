/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

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
	private int commandType;
	private int defaultReading;
	private int tariffID;
	private int transactionID;
	private String source;
	
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
	public int getCommandType() {
		return commandType;
	}
	public void setCommandType(int commandType) {
		this.commandType = commandType;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getDefaultReading() {
		return defaultReading;
	}
	public void setDefaultReading(int defaultReading) {
		this.defaultReading = defaultReading;
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
	
}
