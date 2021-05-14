package com.idigitronics.IDigi.request.vo;

import java.util.List;

/**
 * @author K VimaL Kumar
 * 
 */
public class RestCallVO {
	
	private String miuID;
	private int transaction_id;
	private int parameter_id;
	private String value;

	private List<CommandGroupRequestVO> parameters;
	private String urlExtension;
	
	public String getMiuID() {
		return miuID;
	}
	public void setMiuID(String miuID) {
		this.miuID = miuID;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getParameter_id() {
		return parameter_id;
	}
	public void setParameter_id(int parameter_id) {
		this.parameter_id = parameter_id;
	}
	public String getUrlExtension() {
		return urlExtension;
	}
	public void setUrlExtension(String urlExtension) {
		this.urlExtension = urlExtension;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<CommandGroupRequestVO> getParameters() {
		return parameters;
	}
	public void setParameters(List<CommandGroupRequestVO> parameters) {
		this.parameters = parameters;
	}
	
}
