/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

/**
 * @author VmL
 *
 */
public class BillDetailsResponseVO {
	
	private long customerBillingID;
	private float totalConsumption;
	private float totalAmount;
	private int lateFee;
	private String dueDate;
	private String billingDate;
	private String billMonth;
	private int billYear;
	
	public long getCustomerBillingID() {
		return customerBillingID;
	}
	public void setCustomerBillingID(long customerBillingID) {
		this.customerBillingID = customerBillingID;
	}
	public float getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(float totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
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
	public int getLateFee() {
		return lateFee;
	}
	public void setLateFee(int lateFee) {
		this.lateFee = lateFee;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	
}
