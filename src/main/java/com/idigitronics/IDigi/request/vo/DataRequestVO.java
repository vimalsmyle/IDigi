/**
 * 
 */
package com.idigitronics.IDigi.request.vo;

/**
 * @author K VImal Kumar
 *
 */
public class DataRequestVO {
	
	private int readingID;
	private String miuID;
	private String timestamp;
	private int type;
	private String sync_time;
	private int sync_interval;
	private int pre_post_paid;
	private float bat_volt;
	private int valve_configuration;
	private int valve_live_status;
	private float credit;
	private float tariff;
	private float emergency_credit;
	private int min_elapsed_after_valve_trip;
	private float reading;
	private int status;
	
	private String source;
	
	public int getReadingID() {
		return readingID;
	}
	public void setReadingID(int readingID) {
		this.readingID = readingID;
	}
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSync_time() {
		return sync_time;
	}
	public void setSync_time(String sync_time) {
		this.sync_time = sync_time;
	}
	public int getSync_interval() {
		return sync_interval;
	}
	public void setSync_interval(int sync_interval) {
		this.sync_interval = sync_interval;
	}
	public int getPre_post_paid() {
		return pre_post_paid;
	}
	public void setPre_post_paid(int pre_post_paid) {
		this.pre_post_paid = pre_post_paid;
	}
	public float getBat_volt() {
		return bat_volt;
	}
	public void setBat_volt(float bat_volt) {
		this.bat_volt = bat_volt;
	}
	public int getValve_configuration() {
		return valve_configuration;
	}
	public void setValve_configuration(int valve_configuration) {
		this.valve_configuration = valve_configuration;
	}
	public int getValve_live_status() {
		return valve_live_status;
	}
	public void setValve_live_status(int valve_live_status) {
		this.valve_live_status = valve_live_status;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getTariff() {
		return tariff;
	}
	public void setTariff(float tariff) {
		this.tariff = tariff;
	}
	public float getEmergency_credit() {
		return emergency_credit;
	}
	public void setEmergency_credit(float emergency_credit) {
		this.emergency_credit = emergency_credit;
	}
	public int getMin_elapsed_after_valve_trip() {
		return min_elapsed_after_valve_trip;
	}
	public void setMin_elapsed_after_valve_trip(int min_elapsed_after_valve_trip) {
		this.min_elapsed_after_valve_trip = min_elapsed_after_valve_trip;
	}
	public float getReading() {
		return reading;
	}
	public void setReading(float reading) {
		this.reading = reading;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
