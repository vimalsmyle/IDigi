/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;

/**
 * @author vml
 *
 */
public class SensorDashboardResponseVO {
	
	private String communityName;
	private String blockName;
	private String HouseNumber;
	private String firstName;
	private String lastName;
	private String customerUniqueID;
	private long readingID;
	private String equipment_serial_id;
	private String readings;
	private String reader_sensor_status;
	private float per_day_flow_rate;
	private float live_flow_rate;
	private int record_interval;
	private int sync_interval;
	private int rssi;
	private String digital_outputs;
	private float analog_inputs;
	private float analog_outputs;
	private float voltage_outputs;
	private int battery_percentage;
	private String online_powersupply;
	private String gsm_status;
	private String ethernet_status;
	private String nfc_status;
	private String flash_status;
	private String nfc_memory_status;
	private String flash_memory_status;
	private String low_gsm; 
	private String low_battery;
	private String sensor_detachment;
	private String door_open_switch;
	private String magnetic_tamper; 
	private String timestamp;
	private String LogDate;
	
	private List<SensorDashboardResponseVO> data;

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

	public String getHouseNumber() {
		return HouseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		HouseNumber = houseNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCustomerUniqueID() {
		return customerUniqueID;
	}

	public void setCustomerUniqueID(String customerUniqueID) {
		this.customerUniqueID = customerUniqueID;
	}

	public long getReadingID() {
		return readingID;
	}

	public void setReadingID(long readingID) {
		this.readingID = readingID;
	}

	public String getEquipment_serial_id() {
		return equipment_serial_id;
	}

	public void setEquipment_serial_id(String equipment_serial_id) {
		this.equipment_serial_id = equipment_serial_id;
	}

	public String getReadings() {
		return readings;
	}

	public void setReadings(String readings) {
		this.readings = readings;
	}

	public String getReader_sensor_status() {
		return reader_sensor_status;
	}

	public void setReader_sensor_status(String reader_sensor_status) {
		this.reader_sensor_status = reader_sensor_status;
	}

	public float getPer_day_flow_rate() {
		return per_day_flow_rate;
	}

	public void setPer_day_flow_rate(float per_day_flow_rate) {
		this.per_day_flow_rate = per_day_flow_rate;
	}

	public float getLive_flow_rate() {
		return live_flow_rate;
	}

	public void setLive_flow_rate(float live_flow_rate) {
		this.live_flow_rate = live_flow_rate;
	}

	public int getRecord_interval() {
		return record_interval;
	}

	public void setRecord_interval(int record_interval) {
		this.record_interval = record_interval;
	}

	public int getSync_interval() {
		return sync_interval;
	}

	public void setSync_interval(int sync_interval) {
		this.sync_interval = sync_interval;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public String getDigital_outputs() {
		return digital_outputs;
	}

	public void setDigital_outputs(String digital_outputs) {
		this.digital_outputs = digital_outputs;
	}

	public float getAnalog_inputs() {
		return analog_inputs;
	}

	public void setAnalog_inputs(float analog_inputs) {
		this.analog_inputs = analog_inputs;
	}

	public float getAnalog_outputs() {
		return analog_outputs;
	}

	public void setAnalog_outputs(float analog_outputs) {
		this.analog_outputs = analog_outputs;
	}

	public float getVoltage_outputs() {
		return voltage_outputs;
	}

	public void setVoltage_outputs(float voltage_outputs) {
		this.voltage_outputs = voltage_outputs;
	}

	public int getBattery_percentage() {
		return battery_percentage;
	}

	public void setBattery_percentage(int battery_percentage) {
		this.battery_percentage = battery_percentage;
	}

	public String getOnline_powersupply() {
		return online_powersupply;
	}

	public void setOnline_powersupply(String online_powersupply) {
		this.online_powersupply = online_powersupply;
	}

	public String getGsm_status() {
		return gsm_status;
	}

	public void setGsm_status(String gsm_status) {
		this.gsm_status = gsm_status;
	}

	public String getEthernet_status() {
		return ethernet_status;
	}

	public void setEthernet_status(String ethernet_status) {
		this.ethernet_status = ethernet_status;
	}

	public String getNfc_status() {
		return nfc_status;
	}

	public void setNfc_status(String nfc_status) {
		this.nfc_status = nfc_status;
	}

	public String getFlash_status() {
		return flash_status;
	}

	public void setFlash_status(String flash_status) {
		this.flash_status = flash_status;
	}

	public String getNfc_memory_status() {
		return nfc_memory_status;
	}

	public void setNfc_memory_status(String nfc_memory_status) {
		this.nfc_memory_status = nfc_memory_status;
	}

	public String getFlash_memory_status() {
		return flash_memory_status;
	}

	public void setFlash_memory_status(String flash_memory_status) {
		this.flash_memory_status = flash_memory_status;
	}

	public String getLow_gsm() {
		return low_gsm;
	}

	public void setLow_gsm(String low_gsm) {
		this.low_gsm = low_gsm;
	}

	public String getLow_battery() {
		return low_battery;
	}

	public void setLow_battery(String low_battery) {
		this.low_battery = low_battery;
	}

	public String getSensor_detachment() {
		return sensor_detachment;
	}

	public void setSensor_detachment(String sensor_detachment) {
		this.sensor_detachment = sensor_detachment;
	}

	public String getDoor_open_switch() {
		return door_open_switch;
	}

	public void setDoor_open_switch(String door_open_switch) {
		this.door_open_switch = door_open_switch;
	}

	public String getMagnetic_tamper() {
		return magnetic_tamper;
	}

	public void setMagnetic_tamper(String magnetic_tamper) {
		this.magnetic_tamper = magnetic_tamper;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getLogDate() {
		return LogDate;
	}

	public void setLogDate(String logDate) {
		LogDate = logDate;
	}

	public List<SensorDashboardResponseVO> getData() {
		return data;
	}

	public void setData(List<SensorDashboardResponseVO> data) {
		this.data = data;
	}

	
}
