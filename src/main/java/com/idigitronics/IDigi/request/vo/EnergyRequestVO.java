/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author vml
 *
 */
public class EnergyRequestVO {
	
		public String Jsondata;
		public String deviceName;
		public String data;
		public String publishedAt;
		public int count;
		
		
		public String getJsondata() {
			return Jsondata;
		}
		public void setJsondata(String jsondata) {
			Jsondata = jsondata;
		}
		public String getDeviceName() {
			return deviceName;
		}
		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}
		public String getData() {
			return data;
		}
		public void setData(String data) {
			this.data = data;
		}
		public String getPublishedAt() {
			return publishedAt;
		}
		public void setPublishedAt(String publishedAt) {
			this.publishedAt = publishedAt;
		}
		public int getCount() {
			return count;
		}
		public void setCount(int count) {
			this.count = count;
		}
		
}
