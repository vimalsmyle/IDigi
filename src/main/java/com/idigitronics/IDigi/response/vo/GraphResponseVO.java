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
	
	private List<SolarDashboardResponseVO> block1List;
	private List<SolarDashboardResponseVO> block2List;
	private List<SolarDashboardResponseVO> block3List;
	
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

	public List<SolarDashboardResponseVO> getBlock1List() {
		return block1List;
	}

	public void setBlock1List(List<SolarDashboardResponseVO> block1List) {
		this.block1List = block1List;
	}

	public List<SolarDashboardResponseVO> getBlock2List() {
		return block2List;
	}

	public void setBlock2List(List<SolarDashboardResponseVO> block2List) {
		this.block2List = block2List;
	}

	public List<SolarDashboardResponseVO> getBlock3List() {
		return block3List;
	}

	public void setBlock3List(List<SolarDashboardResponseVO> block3List) {
		this.block3List = block3List;
	}
	
	

}
