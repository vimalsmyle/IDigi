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

	private int billingID;
	private int customerMeterID;
	private String communityName;
	private String blockName;
	private String customerName;
	private String miuID;
	private float previousReading;
	private float presentReading;
	private int consumption;
	private float tariff;
	private int billAmount;
	private int fixedCharges;
	private int reconnectionCharges;
	private String billingDate;
	private String billMonth;
	private int billYear;
	private String logDate;
	
	private List<BillingResponseVO> data;
	
	public int getBillingID() {
		return billingID;
	}
	public void setBillingID(int billingID) {
		this.billingID = billingID;
	}
	public int getCustomerMeterID() {
		return customerMeterID;
	}
	public void setCustomerMeterID(int customerMeterID) {
		this.customerMeterID = customerMeterID;
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
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public float getPreviousReading() {
		return previousReading;
	}
	public void setPreviousReading(float previousReading) {
		this.previousReading = previousReading;
	}
	public float getPresentReading() {
		return presentReading;
	}
	public void setPresentReading(float presentReading) {
		this.presentReading = presentReading;
	}
	public int getConsumption() {
		return consumption;
	}
	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public int getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}
	public int getFixedCharges() {
		return fixedCharges;
	}
	public void setFixedCharges(int fixedCharges) {
		this.fixedCharges = fixedCharges;
	}
	public int getReconnectionCharges() {
		return reconnectionCharges;
	}
	public void setReconnectionCharges(int reconnectionCharges) {
		this.reconnectionCharges = reconnectionCharges;
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
	public List<BillingResponseVO> getData() {
		return data;
	}
	public void setData(List<BillingResponseVO> data) {
		this.data = data;
	}
	
}
