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
	private String customerUniqueID;
	private String miuID;
	private long customerMeterID;
	private String meterSerialNumber;
	private String meterType;
	private String payType;
	private float reading;
	private int consumption;
	private float balance;
	private float emergencyCredit;
	private int battery;
	private String valveStatus;
	private String valveStatusColor;
	private float tariff;
	private String tariffName;
	private String doorOpenTamper;
	private String magneticTamper;
	private String rtcFault;
	private String timeStamp;
	private String dateColor;
	private String batteryColor;
	private int nonCommunicating;
	private int communicating;
	private int total;
	private String communicationStatus;
	private String dooropentamperColor;
	private String magnetictamperColor;
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

	public String getDoorOpenTamper() {
		return doorOpenTamper;
	}

	public void setDoorOpenTamper(String doorOpenTamper) {
		this.doorOpenTamper = doorOpenTamper;
	}

	public String getMagneticTamper() {
		return magneticTamper;
	}

	public void setMagneticTamper(String magneticTamper) {
		this.magneticTamper = magneticTamper;
	}

	public String getRtcFault() {
		return rtcFault;
	}

	public void setRtcFault(String rtcFault) {
		this.rtcFault = rtcFault;
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


	public String getDooropentamperColor() {
		return dooropentamperColor;
	}

	public void setDooropentamperColor(String dooropentamperColor) {
		this.dooropentamperColor = dooropentamperColor;
	}

	public String getMagnetictamperColor() {
		return magnetictamperColor;
	}

	public void setMagnetictamperColor(String magnetictamperColor) {
		this.magnetictamperColor = magnetictamperColor;
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

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public String getMiuID() {
		return miuID;
	}

	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}

	public long getCustomerMeterID() {
		return customerMeterID;
	}

	public void setCustomerMeterID(long customerMeterID) {
		this.customerMeterID = customerMeterID;
	}

	public String getMeterType() {
		return meterType;
	}

	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

}
