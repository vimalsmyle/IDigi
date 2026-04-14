/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author vml
 *
 */
public class ElMeasureRequestVO {
	
	 public String utcDateTime;
	 public String deviceInstanceId;
     public String tagId;
	 public int tagValue;
	 
	 
	public String getUtcDateTime() {
		return utcDateTime;
	}
	public void setUtcDateTime(String utcDateTime) {
		this.utcDateTime = utcDateTime;
	}
	public String getDeviceInstanceId() {
		return deviceInstanceId;
	}
	public void setDeviceInstanceId(String deviceInstanceId) {
		this.deviceInstanceId = deviceInstanceId;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public int getTagValue() {
		return tagValue;
	}
	public void setTagValue(int tagValue) {
		this.tagValue = tagValue;
	}
	 
}
