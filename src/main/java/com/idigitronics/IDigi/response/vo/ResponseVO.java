/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.HashMap;

/**
 * @author VmL
 *
 */
public class ResponseVO {
	
	
	private HashMap<Integer, String> dropDownCommunities;
	private HashMap<Integer, String> dropDownBlocks;
	private HashMap<Long, String> dropDownCustomers;
	private HashMap<Integer, String> dropDownTariffs;
	private HashMap<Integer, String> dropDownGateways;
	private HashMap<Integer, Integer> dropDownMeterSizes;
	private HashMap<Long, String> dropDownCustomerMeters;
	private HashMap<Long, String> dropDownAllCustomerMeters;
	private TopupDetailsResponseVO topupdetails;
	private BillDetailsResponseVO billdetails;
	private String result;
	private String Message;
	private String fileName;
	private String location;
	private UserDetails userDetails;
	private CheckoutDetails checkoutDetails;
	private String paymentMode;
	private String payType;
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String Message) {
		this.Message = Message;
	}
	public TopupDetailsResponseVO getTopupdetails() {
		return topupdetails;
	}
	public void setTopupdetails(TopupDetailsResponseVO topupdetails) {
		this.topupdetails = topupdetails;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public HashMap<Integer, String> getDropDownCommunities() {
		return dropDownCommunities;
	}
	public void setDropDownCommunities(HashMap<Integer, String> dropDownCommunities) {
		this.dropDownCommunities = dropDownCommunities;
	}
	public HashMap<Integer, String> getDropDownBlocks() {
		return dropDownBlocks;
	}
	public void setDropDownBlocks(HashMap<Integer, String> dropDownBlocks) {
		this.dropDownBlocks = dropDownBlocks;
	}
	public HashMap<Long, String> getDropDownCustomers() {
		return dropDownCustomers;
	}
	public void setDropDownCustomers(HashMap<Long, String> dropDownCustomers) {
		this.dropDownCustomers = dropDownCustomers;
	}
	public HashMap<Integer, String> getDropDownTariffs() {
		return dropDownTariffs;
	}
	public void setDropDownTariffs(HashMap<Integer, String> dropDownTariffs) {
		this.dropDownTariffs = dropDownTariffs;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public CheckoutDetails getCheckoutDetails() {
		return checkoutDetails;
	}
	public void setCheckoutDetails(CheckoutDetails checkoutDetails) {
		this.checkoutDetails = checkoutDetails;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public HashMap<Integer, String> getDropDownGateways() {
		return dropDownGateways;
	}
	public void setDropDownGateways(HashMap<Integer, String> dropDownGateways) {
		this.dropDownGateways = dropDownGateways;
	}
	public HashMap<Long, String> getDropDownCustomerMeters() {
		return dropDownCustomerMeters;
	}
	public void setDropDownCustomerMeters(HashMap<Long, String> dropDownCustomerMeters) {
		this.dropDownCustomerMeters = dropDownCustomerMeters;
	}
	public BillDetailsResponseVO getBilldetails() {
		return billdetails;
	}
	public void setBilldetails(BillDetailsResponseVO billdetails) {
		this.billdetails = billdetails;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public HashMap<Integer, Integer> getDropDownMeterSizes() {
		return dropDownMeterSizes;
	}
	public void setDropDownMeterSizes(HashMap<Integer, Integer> dropDownMeterSizes) {
		this.dropDownMeterSizes = dropDownMeterSizes;
	}
	public HashMap<Long, String> getDropDownAllCustomerMeters() {
		return dropDownAllCustomerMeters;
	}
	public void setDropDownAllCustomerMeters(HashMap<Long, String> dropDownAllCustomerMeters) {
		this.dropDownAllCustomerMeters = dropDownAllCustomerMeters;
	}
	
}
