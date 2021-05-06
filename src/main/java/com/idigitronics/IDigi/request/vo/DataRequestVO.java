/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

import java.util.List;

/**
 * @author K VImal Kumar
 *
 */
public class DataRequestVO {
	
	private String gateway_id;
    public List<Devices> devices;

    public String getGateway_id() {
		return gateway_id;
	}
	public void setGateway_id(String gateway_id) {
		this.gateway_id = gateway_id;
	}
	public List<Devices> getDevices() {
		return devices;
	}
	public void setDevices(List<Devices> devices) {
		this.devices = devices;
	}

}
