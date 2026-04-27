/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author vml
 *
 */
public class Tags_Raw {
	
	public String utctimestamp;
    public String tag_id;
    public int tag_value;
    
	public String getUtctimestamp() {
		return utctimestamp;
	}
	public void setUtctimestamp(String utctimestamp) {
		this.utctimestamp = utctimestamp;
	}
	public String getTag_id() {
		return tag_id;
	}
	public void setTag_id(String tag_id) {
		this.tag_id = tag_id;
	}
	public int getTag_value() {
		return tag_value;
	}
	public void setTag_value(int tag_value) {
		this.tag_value = tag_value;
	}

}
