/**
 * 
 */
package com.idigitronics.IDigi.response.vo;

import java.util.List;
/**
 * @author K Vimal Kumar
 *
 */
public class GraphResponseVO {
	
	private List<String> xAxis;
	private List<Integer> yAxis;
	
	private List<SolarDashboardResponseVO> green;
	private List<SolarDashboardResponseVO> red;
//	private List<SolarDashboardResponseVO> block3List;
	
	public List<String> getXAxis() {
	return xAxis;
	}

	public void setXAxis(List<String> xAxis) {
	this.xAxis = xAxis;
	}

	public List<Integer> getYAxis() {
	return yAxis;
	}

	public void setYAxis(List<Integer> yAxis) {
	this.yAxis = yAxis;
	}
	public List<SolarDashboardResponseVO> getGreen() {
		return green;
	}

	public void setGreen(List<SolarDashboardResponseVO> green) {
		this.green = green;
	}

	public List<SolarDashboardResponseVO> getRed() {
		return red;
	}

	public void setRed(List<SolarDashboardResponseVO> red) {
		this.red = red;
	}


//
//	public List<SolarDashboardResponseVO> getBlock3List() {
//		return block3List;
//	}
//
//	public void setBlock3List(List<SolarDashboardResponseVO> block3List) {
//		this.block3List = block3List;
//	}
//	
	

}
