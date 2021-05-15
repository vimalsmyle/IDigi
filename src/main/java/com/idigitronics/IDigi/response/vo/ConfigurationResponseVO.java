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
	
	private String miuID;
	private List<ConfigurationResponseVO> data;
	private List<CommandGroupResponseVO> commands;
	private int transactionID;
	
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public List<ConfigurationResponseVO> getData() {
		return data;
	}
	public void setData(List<ConfigurationResponseVO> data) {
		this.data = data;
	}
	public List<CommandGroupResponseVO> getCommands() {
		return commands;
	}
	public void setCommands(List<CommandGroupResponseVO> commands) {
		this.commands = commands;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
}
