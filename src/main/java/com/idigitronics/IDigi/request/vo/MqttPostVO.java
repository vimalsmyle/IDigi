/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author vml
 *
 */
public class MqttPostVO {
	
	private String reference;
	private boolean confirmed;
	private int fPort;
	private String data;
	
	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
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
