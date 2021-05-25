/**
 * 
 */
package com.idigitronics.IDigi.controller;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.idigitronics.IDigi.dao.DashboardDAO;
import com.idigitronics.IDigi.request.vo.DashboardRequestVO;
import com.idigitronics.IDigi.request.vo.DataRequestVO;
import com.idigitronics.IDigi.request.vo.FilterVO;
import com.idigitronics.IDigi.response.vo.DashboardResponseVO;
import com.idigitronics.IDigi.response.vo.GraphResponseVO;
import com.idigitronics.IDigi.response.vo.HomeResponseVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;


/**
 * @author K VimaL Kumar
 * 
 */

@Controller
public class DashboardController {

	private static final Logger logger = Logger.getLogger(DashboardController.class);
	
	
	@RequestMapping(value = "/dashboard/{type}/{communityID}/{blockID}/{customerUniqueID}/{filter}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DashboardResponseVO dashboarddetails(@PathVariable("type") int type, @PathVariable("communityID") int communityID, @PathVariable("blockID") int blockID, @PathVariable("customerUniqueID") String customerUniqueID, @PathVariable("filter") int filter) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getDashboarddetails(type, communityID, blockID, customerUniqueID, filter));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/homedashboard/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeResponseVO homedashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getHomeDashboardDetails(roleid, id);
	}
	
	@RequestMapping(value = "/graph/{type}/{year}/{month}/{customerUniqueID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO homedashboarddetails(@PathVariable("type") int type, @PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("customerUniqueID") String customerUniqueID) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getCustomerGraphDashboardDetails(year, month, customerUniqueID, type);
	}
	
	@RequestMapping(value = "/filterdashboard/{roleid}/{id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DashboardResponseVO filterdashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @RequestBody FilterVO filtervo) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getFilterDashboarddetails(roleid, id, filtervo));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/server/api/{device_eui}/status", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
//	public @ResponseBody ResponseVO postDashboardDetails(HttpEntity<String> httpEntity, @PathVariable("device_eui") String miuID) {
	public @ResponseBody ResponseVO postDashboardDetails(@RequestBody DataRequestVO dataRequestVO, @PathVariable("device_eui") String miuID) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		
//		Gson gson = new Gson();
		
//		String json = httpEntity.getBody();
		
//		DataRequestVO dataRequestVO = gson.fromJson(json, DataRequestVO.class);
		
		try {
			responsevo = dashboarddao.postDashboarddetails(dataRequestVO, miuID);
		} catch (Exception ex) {
			logger.error("This is Error message", ex);
			ex.printStackTrace();
		}
		return responsevo;
	}
	
	
	@RequestMapping(value = "/datafrommobile", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO datafrommobile(@RequestBody DataRequestVO dataRequestVO) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();

		try {
			responsevo.setResult(dashboarddao.insertdashboard(dataRequestVO, dataRequestVO.getMiuID()));
			responsevo.setMessage("Data Inserted Successfully");
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Data Insertion Failed");
		}
		return responsevo;
	}
	
}
