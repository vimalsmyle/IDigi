/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author VmL
 *
 */
public class RxInfo {
	
	private String gatewayID;
	private String uplinkID;
	private String name;
	private int rssi;
	private float loRaSNR;
	private Location location;
	
	public String getGatewayID() {
		return gatewayID;
	}
	public void setGatewayID(String gatewayID) {
		this.gatewayID = gatewayID;
	}
	public String getUplinkID() {
		return uplinkID;
	}
	public void setUplinkID(String uplinkID) {
		this.uplinkID = uplinkID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public float getLoRaSNR() {
		return loRaSNR;
	}
	public void setLoRaSNR(float loRaSNR) {
		this.loRaSNR = loRaSNR;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

}
