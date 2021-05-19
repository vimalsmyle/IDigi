/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * @author K Vimal Kumar
 *
 */
public class BillingResponseVO {

	private long customerBillingID;
	private String communityName;
	private String blockName;
	private String customerName;
	private String houseNumber;
	private int totalConsumption;
	private int totalAmount;
	private String billingDate;
	private String billMonth;
	private int billYear;
	private String logDate;
	
	private List<IndividualBillingResponseVO> individualbills;
	private List<BillingResponseVO> data;
	
	public long getCustomerBillingID() {
		return customerBillingID;
	}
	public void setCustomerBillingID(long customerBillingID) {
		this.customerBillingID = customerBillingID;
	}
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}
	public int getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(int totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public String getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(String billingDate) {
		this.billingDate = billingDate;
	}
	public String getBillMonth() {
		return billMonth;
	}
	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	public int getBillYear() {
		return billYear;
	}
	public void setBillYear(int billYear) {
		this.billYear = billYear;
	}
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public List<IndividualBillingResponseVO> getIndividualbills() {
		return individualbills;
	}
	public void setIndividualbills(List<IndividualBillingResponseVO> individualbills) {
		this.individualbills = individualbills;
	}
	public List<BillingResponseVO> getData() {
		return data;
	}
	public void setData(List<BillingResponseVO> data) {
		this.data = data;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
