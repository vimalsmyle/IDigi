/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class AlertResponseVO {

	private int alertID;	
	private String noAMRInterval;
	private String lowBatteryVoltage;
	private String timeOut;
	private String registeredDate;
	private float perUnitValue;
	private int reconnectionCharges;
	private int lateFee;
	private int GST;
	private int dueDayCount;
		
	private List<AlertResponseVO> data;

	public int getAlertID() {
		return alertID;
	}

	public void setAlertID(int alertID) {
		this.alertID = alertID;
	}

	public String getNoAMRInterval() {
		return noAMRInterval;
	}

	public void setNoAMRInterval(String noAMRInterval) {
		this.noAMRInterval = noAMRInterval;
	}

	public String getLowBatteryVoltage() {
		return lowBatteryVoltage;
	}

	public void setLowBatteryVoltage(String lowBatteryVoltage) {
		this.lowBatteryVoltage = lowBatteryVoltage;
	}

	public String getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(String timeOut) {
		this.timeOut = timeOut;
	}

	public String getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(String registeredDate) {
		this.registeredDate = registeredDate;
	}

	public List<AlertResponseVO> getData() {
		return data;
	}

	public void setData(List<AlertResponseVO> data) {
		this.data = data;
	}

	public float getPerUnitValue() {
		return perUnitValue;
	}

	public void setPerUnitValue(float perUnitValue) {
		this.perUnitValue = perUnitValue;
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

}
