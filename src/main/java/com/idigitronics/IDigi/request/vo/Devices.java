/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

import java.util.ArrayList;

/**
 * @author vml
 *
 */
public class Devices {
	
	public String device_instance_id;
    public ArrayList<Tags_Raw> tags_raw;
    
	public String getDevice_instance_id() {
		return device_instance_id;
	}
	public void setDevice_instance_id(String device_instance_id) {
		this.device_instance_id = device_instance_id;
	}
	public ArrayList<Tags_Raw> getTags_raw() {
		return tags_raw;
	}
	public void setTags_raw(ArrayList<Tags_Raw> tags_raw) {
		this.tags_raw = tags_raw;
	}

}
