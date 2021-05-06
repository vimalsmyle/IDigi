/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 *
 */
public class ConfigurationResponseVO {
	
	private String meterID;
	private String commandType;
	private String modifiedDate;
	private String status;
	private String value;
	private List<ConfigurationResponseVO> data;
	private int transactionID;
	
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public String getMeterID() {
		return meterID;
	}
	public void setMeterID(String meterID) {
		this.meterID = meterID;
	}
	public String getCommandType() {
		return commandType;
	}
	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ConfigurationResponseVO> getData() {
		return data;
	}
	public void setData(List<ConfigurationResponseVO> data) {
		this.data = data;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
