/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

import java.util.List;

/**
 * @author VmL
 *
 */
public class MQTTRequestVO {
	
	private String applicationID;
	private String applicationName;
	private String deviceName;
	private String devEUI;
	private List<RxInfo> rxInfo;
	private TxInfo txInfo;
	private boolean adr;
	private int fCnt;
	private int fPort;
	private String data;
	
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDevEUI() {
		return devEUI;
	}
	public void setDevEUI(String devEUI) {
		this.devEUI = devEUI;
	}
	public List<RxInfo> getRxInfo() {
		return rxInfo;
	}
	public void setRxInfo(List<RxInfo> rxInfo) {
		this.rxInfo = rxInfo;
	}
	public TxInfo getTxInfo() {
		return txInfo;
	}
	public void setTxInfo(TxInfo txInfo) {
		this.txInfo = txInfo;
	}
	public boolean isAdr() {
		return adr;
	}
	public void setAdr(boolean adr) {
		this.adr = adr;
	}
	public int getfCnt() {
		return fCnt;
	}
	public void setfCnt(int fCnt) {
		this.fCnt = fCnt;
	}
	public int getfPort() {
		return fPort;
	}
	public void setfPort(int fPort) {
		this.fPort = fPort;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
