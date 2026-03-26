/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * 
 */
public class PrefixResponseVO {
	
	private int prefixID;
	private String prefixName;
	private int communityID;
	private String communityName;
	private int blockID;
	private String blockName;
	private String miuID;
	private String meterType;
	private int meterSizeID;
	private int meterSize;
	private String payType;
	private int tariffID;
	private String tariff;
	private String tariffName;
	private int gatewayID;
	private String gatewayName;
	private float thresholdMaximum;
	private float thresholdMinimum;
	private String modifiedDate;
	
	private List<PrefixResponseVO> data;
	
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
	public int getMeterSize() {
		return meterSize;
	}
	public void setMeterSize(int meterSize) {
		this.meterSize = meterSize;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getTariff() {
		return tariff;
	}
	public void setTariff(String tariff) {
		this.tariff = tariff;
	}
	public String getTariffName() {
		return tariffName;
	}
	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}
	public String getGatewayName() {
		return gatewayName;
	}
	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
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
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public List<PrefixResponseVO> getData() {
		return data;
	}
	public void setData(List<PrefixResponseVO> data) {
		this.data = data;
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
	public int getMeterSizeID() {
		return meterSizeID;
	}
	public void setMeterSizeID(int meterSizeID) {
		this.meterSizeID = meterSizeID;
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
	
}
