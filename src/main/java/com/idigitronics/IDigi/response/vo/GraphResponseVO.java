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
	
	private List<SolarDashboardResponseVO> block1;
	private List<SolarDashboardResponseVO> block2;
	private List<SolarDashboardResponseVO> block3;
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

	public List<SolarDashboardResponseVO> getBlock1() {
		return block1;
	}

	public void setBlock1(List<SolarDashboardResponseVO> block1) {
		this.block1 = block1;
	}

	public List<SolarDashboardResponseVO> getBlock2() {
		return block2;
	}

	public void setBlock2(List<SolarDashboardResponseVO> block2) {
		this.block2 = block2;
	}

	public List<SolarDashboardResponseVO> getBlock3() {
		return block3;
	}

	public void setBlock3(List<SolarDashboardResponseVO> block3) {
		this.block3 = block3;
	}

	public List<SolarDashboardResponseVO> getMaster() {
		return master;
	}

	public void setMaster(List<SolarDashboardResponseVO> master) {
		this.master = master;
	}
	
}
