/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class VacationRequestVO {
	
	private String customerUniqueID;
	private int customerMeterID;
	private String vacationName;
	private String startDateTime;
	private String endDateTime;
	private int startDay;
	private int endDay;
	private String source;
	
	private int communityID;
	private int blockID;
	private int customerID;
	private String miuID;
	private int status;
	private int vacationID;
	private String mode;
	
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
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public String getVacationName() {
		return vacationName;
	}
	public void setVacationName(String vacationName) {
		this.vacationName = vacationName;
	}
	public String getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	public String getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	public int getStartDay() {
		return startDay;
	}
	public void setStartDay(int startDay) {
		this.startDay = startDay;
	}
	public int getEndDay() {
		return endDay;
	}
	public void setEndDay(int endDay) {
		this.endDay = endDay;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getVacationID() {
		return vacationID;
	}
	public void setVacationID(int vacationID) {
		this.vacationID = vacationID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

}
