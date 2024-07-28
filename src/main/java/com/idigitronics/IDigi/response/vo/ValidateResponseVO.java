/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

/**
 * @author K Vimal Kumar
 *
 */
public class ValidateResponseVO {
	
	private boolean result;
	private long previousReading;
	private long readingID;
	
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public long getPreviousReading() {
		return previousReading;
	}
	public void setPreviousReading(long previousReading) {
		this.previousReading = previousReading;
	}
	public long getReadingID() {
		return readingID;
	}
	public void setReadingID(long readingID) {
		this.readingID = readingID;
	}
	
}
