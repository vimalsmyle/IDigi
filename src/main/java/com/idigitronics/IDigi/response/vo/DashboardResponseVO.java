/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 * 
 */
public class DashboardResponseVO {

	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String CRNNumber;
	private String meterID;
	private String meterSerialNumber;
	private float reading;
	private int consumption;
	private float balance;
	private float emergencyCredit;
	private int battery;
	private String valveStatus;
	private String valveStatusColor;
	private float tariff;
	private String tariffName;
	private String tamperStatus;
	private String tamperTimeStamp;
	private String timeStamp;
	private String dateColor;
	private String batteryColor;
	private int nonCommunicating;
	private int communicating;
	private int total;
	private String communicationStatus;
	private String tamperColor;
	private String vacationStatus;
	private String vacationColor;
	private String lastRechargeDate;
	private int lastTopupAmount;
	
	private List<DashboardResponseVO> data;

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


	public String getCRNNumber() {
		return CRNNumber;
	}


	public void setCRNNumber(String cRNNumber) {
		CRNNumber = cRNNumber;
	}


	public String getMeterID() {
		return meterID;
	}

	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}


	public String getMeterSerialNumber() {
		return meterSerialNumber;
	}


	public void setMeterSerialNumber(String meterSerialNumber) {
		this.meterSerialNumber = meterSerialNumber;
	}


	public float getReading() {
		return reading;
	}

	public void setReading(float reading) {
		this.reading = reading;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getEmergencyCredit() {
		return emergencyCredit;
	}

	public void setEmergencyCredit(float emergencyCredit) {
		this.emergencyCredit = emergencyCredit;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public String getValveStatus() {
		return valveStatus;
	}

	public void setValveStatus(String valveStatus) {
		this.valveStatus = valveStatus;
	}

	public float getTariff() {
		return tariff;
	}

	public void setTariff(float tariff) {
		this.tariff = tariff;
	}


	public String getTariffName() {
		return tariffName;
	}


	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}


	public String getTamperStatus() {
		return tamperStatus;
	}


	public void setTamperStatus(String tamperStatus) {
		this.tamperStatus = tamperStatus;
	}


	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDateColor() {
		return dateColor;
	}

	public void setDateColor(String dateColor) {
		this.dateColor = dateColor;
	}

	public String getBatteryColor() {
		return batteryColor;
	}

	public void setBatteryColor(String batteryColor) {
		this.batteryColor = batteryColor;
	}

	public List<DashboardResponseVO> getData() {
		return data;
	}

	public void setData(List<DashboardResponseVO> data) {
		this.data = data;
	}

	public int getNonCommunicating() {
		return nonCommunicating;
	}

	public void setNonCommunicating(int nonCommunicating) {
		this.nonCommunicating = nonCommunicating;
	}

	public int getCommunicating() {
		return communicating;
	}

	public void setCommunicating(int communicating) {
		this.communicating = communicating;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getCommunicationStatus() {
		return communicationStatus;
	}

	public void setCommunicationStatus(String communicationStatus) {
		this.communicationStatus = communicationStatus;
	}

	public int getConsumption() {
		return consumption;
	}

	public void setConsumption(int consumption) {
		this.consumption = consumption;
	}

	public String getValveStatusColor() {
		return valveStatusColor;
	}

	public void setValveStatusColor(String valveStatusColor) {
		this.valveStatusColor = valveStatusColor;
	}


	public String getTamperColor() {
		return tamperColor;
	}

	public void setTamperColor(String tamperColor) {
		this.tamperColor = tamperColor;
	}

	public String getVacationStatus() {
		return vacationStatus;
	}

	public void setVacationStatus(String vacationStatus) {
		this.vacationStatus = vacationStatus;
	}

	public String getVacationColor() {
		return vacationColor;
	}

	public void setVacationColor(String vacationColor) {
		this.vacationColor = vacationColor;
	}

	public String getLastRechargeDate() {
		return lastRechargeDate;
	}

	public void setLastRechargeDate(String lastRechargeDate) {
		this.lastRechargeDate = lastRechargeDate;
	}

	public int getLastTopupAmount() {
		return lastTopupAmount;
	}

	public void setLastTopupAmount(int lastTopupAmount) {
		this.lastTopupAmount = lastTopupAmount;
	}

	public String getTamperTimeStamp() {
		return tamperTimeStamp;
	}

	public void setTamperTimeStamp(String tamperTimeStamp) {
		this.tamperTimeStamp = tamperTimeStamp;
	}

}
