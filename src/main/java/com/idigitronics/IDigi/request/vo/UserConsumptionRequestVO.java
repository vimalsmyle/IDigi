/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author K VimaL Kumar
 *
 */
public class UserConsumptionRequestVO {

	private String customerUniqueID;
	private String fromDate;
	private String toDate;
	private int fromYear;
	private int toYear;
	private int fromMonth;
	private int toMonth;
	
	public String getCustomerUniqueID() {
		return customerUniqueID;
	}
	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public int getFromYear() {
		return fromYear;
	}
	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}
	public int getToYear() {
		return toYear;
	}
	public void setToYear(int toYear) {
		this.toYear = toYear;
	}
	public int getFromMonth() {
		return fromMonth;
	}
	public void setFromMonth(int fromMonth) {
		this.fromMonth = fromMonth;
	}
	public int getToMonth() {
		return toMonth;
	}
	public void setToMonth(int toMonth) {
		this.toMonth = toMonth;
	}
		
}
