/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * 
 */
public class PrefixRequestVO {
	
	private int prefixID;
	private String prefixName;
	private int communityID;
	private int blockID;
	private String miuID;
	private String meterType;
	private int meterSizeID;
	private String payType;
	private int tariffID;
	private int gatewayID;
	private float thresholdMaximum;
	private float thresholdMinimum;
	
	public int getPrefixID() {
		return prefixID;
	}
	public void setPrefixID(int prefixID) {
		this.prefixID = prefixID;
	}
	public String getPrefixName() {
		return prefixName;
	}
	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}
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
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public String getMeterType() {
		return meterType;
	}
	public void setMeterType(String meterType) {
		this.meterType = meterType;
	}
	public int getMeterSizeID() {
		return meterSizeID;
	}
	public void setMeterSizeID(int meterSizeID) {
		this.meterSizeID = meterSizeID;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public int getTariffID() {
		return tariffID;
	}
	public void setTariffID(int tariffID) {
		this.tariffID = tariffID;
	}
	public int getGatewayID() {
		return gatewayID;
	}
	public void setGatewayID(int gatewayID) {
		this.gatewayID = gatewayID;
	}
	public float getThresholdMaximum() {
		return thresholdMaximum;
	}
	public void setThresholdMaximum(float thresholdMaximum) {
		this.thresholdMaximum = thresholdMaximum;
	}
	public float getThresholdMinimum() {
		return thresholdMinimum;
	}
	public void setThresholdMinimum(float thresholdMinimum) {
		this.thresholdMinimum = thresholdMinimum;
	}
	
}
