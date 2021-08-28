/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class AlertRequestVO {
	
	private int alertID;
	private int noAMRInterval;
	private float lowBatteryVoltage;
	private int timeOut;
	private String registeredDate;
	private float perUnitGasValue;
	private float perUnitWaterValue;
	private float perUnitEnergyValue;
	private int reconnectionCharges;
	private int reconnectionChargeDays;
	private String billGenerationDate;
	private int lateFee;
	private int GST;
	private String vendorGSTNumber;
	private String customerGSTNumber;
	private int dueDayCount;
	
	public int getAlertID() {
		return alertID;
	}
	public void setAlertID(int alertID) {
		this.alertID = alertID;
	}
	public int getNoAMRInterval() {
		return noAMRInterval;
	}
	public void setNoAMRInterval(int noAMRInterval) {
		this.noAMRInterval = noAMRInterval;
	}
	public float getLowBatteryVoltage() {
		return lowBatteryVoltage;
	}
	public void setLowBatteryVoltage(float lowBatteryVoltage) {
		this.lowBatteryVoltage = lowBatteryVoltage;
	}
	public int getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}
	public String getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}
	public int getReconnectionCharges() {
		return reconnectionCharges;
	}
	public void setReconnectionCharges(int reconnectionCharges) {
		this.reconnectionCharges = reconnectionCharges;
	}
	public int getLateFee() {
		return lateFee;
	}
	public void setLateFee(int lateFee) {
		this.lateFee = lateFee;
	}
	public int getGST() {
		return GST;
	}
	public void setGST(int gST) {
		GST = gST;
	}
	public int getDueDayCount() {
		return dueDayCount;
	}
	public void setDueDayCount(int dueDayCount) {
		this.dueDayCount = dueDayCount;
	}
	public String getVendorGSTNumber() {
		return vendorGSTNumber;
	}
	public void setVendorGSTNumber(String vendorGSTNumber) {
		this.vendorGSTNumber = vendorGSTNumber;
	}
	public String getCustomerGSTNumber() {
		return customerGSTNumber;
	}
	public void setCustomerGSTNumber(String customerGSTNumber) {
		this.customerGSTNumber = customerGSTNumber;
	}
	public float getPerUnitGasValue() {
		return perUnitGasValue;
	}
	public void setPerUnitGasValue(float perUnitGasValue) {
		this.perUnitGasValue = perUnitGasValue;
	}
	public float getPerUnitWaterValue() {
		return perUnitWaterValue;
	}
	public void setPerUnitWaterValue(float perUnitWaterValue) {
		this.perUnitWaterValue = perUnitWaterValue;
	}
	public float getPerUnitEnergyValue() {
		return perUnitEnergyValue;
	}
	public void setPerUnitEnergyValue(float perUnitEnergyValue) {
		this.perUnitEnergyValue = perUnitEnergyValue;
	}
	public int getReconnectionChargeDays() {
		return reconnectionChargeDays;
	}
	public void setReconnectionChargeDays(int reconnectionChargeDays) {
		this.reconnectionChargeDays = reconnectionChargeDays;
	}
	public String getBillGenerationDate() {
		return billGenerationDate;
	}
	public void setBillGenerationDate(String billGenerationDate) {
		this.billGenerationDate = billGenerationDate;
	}
	
}
