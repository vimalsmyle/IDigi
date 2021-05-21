/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

/**
 * @author VmL
 *
 */
public class IndividualBillingResponseVO {
	
	private long billingID;
	private long customerMeterID;
	private String miuID;
	private String meterType;
	private float previousReading;
	private float presentReading;
	private int consumption;
	private float tariff;
	private int billAmount;
	private int fixedCharges;
	private int reconnectionCharges;
	private String billingDate;
	
	public long getBillingID() {
		return billingID;
	}
	public void setBillingID(long billingID) {
		this.billingID = billingID;
	}
	public long getCustomerMeterID() {
		return customerMeterID;
	}
	public void setCustomerMeterID(long customerMeterID) {
		this.customerMeterID = customerMeterID;
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
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	
}
