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
	
	private List<List<SolarDashboardResponseVO>> blocks;
	private List<SolarDashboardResponseVO> master;
	
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

	public List<List<SolarDashboardResponseVO>> getBlocks() {
		return blocks;
	}

	public void setBlocks(List<List<SolarDashboardResponseVO>> blocks) {
		this.blocks = blocks;
	}

	public List<SolarDashboardResponseVO> getMaster() {
		return master;
	}

	public void setMaster(List<SolarDashboardResponseVO> master) {
		this.master = master;
	}
	
}
