/**
 * 
 */
package com.idigitronics.IDigi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idigitronics.IDigi.dao.DashboardDAO;
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
	
	
	@RequestMapping(value = "/dashboard/{type}/{communityName}/{blockName}/{customerUniqueID}/{filter}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody DashboardResponseVO dashboarddetails(@PathVariable("type") String type, @PathVariable("communityName") String communityName, @PathVariable("blockName") String blockName, @PathVariable("customerUniqueID") String customerUniqueID, @PathVariable("filter") int filter) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getDashboarddetails(type, communityName, blockName, customerUniqueID, filter));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/dashboard/excel", method = RequestMethod.POST, produces = "application/vnd.ms-excel", consumes = "application/json")
	public ResponseEntity<File> dashboardFile(@RequestBody DashboardResponseVO dashboardResponseVO) throws SQLException, FileNotFoundException {

		ResponseVO responsevo = new ResponseVO();
		DashboardDAO dashboarddao = new DashboardDAO();

		responsevo = dashboarddao.dashboardFile(dashboardResponseVO);
		
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
		
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + responsevo.getFileName())
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file);
		
//		return response;
	}
	
	@RequestMapping(value = "/filterdashboard/{type}/{communityName}/{blockName}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DashboardResponseVO filterdashboarddetails(@PathVariable("type") String type, @PathVariable("communityName") String communityName, @PathVariable("blockName") String blockName, @RequestBody FilterVO filtervo) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();

		dasboardresponsevo.setData(dashboarddao.getFilterDashboarddetails(communityName, blockName, filtervo, type));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
//		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
//		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/filterdashboard/excel", method = RequestMethod.POST, produces = "application/xlsx", consumes = "application/json")
	public ResponseEntity<InputStreamResource> filterDashboardFile(@RequestBody DashboardResponseVO dashboardResponseVO) throws SQLException, FileNotFoundException {

		ResponseVO responsevo = new ResponseVO();
		DashboardDAO dashboarddao = new DashboardDAO();

		responsevo = dashboarddao.filterDashboardFile(dashboardResponseVO);
		
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
//		return ResponseEntity.ok()
//		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + responsevo.getFileName())
//		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
//		        .body(file);
		return response;
	}
	
	@RequestMapping(value = "/homedashboard/{type}/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeResponseVO homedashboarddetails(@PathVariable("type") String type, @PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getHomeDashboardDetails(type, roleid, id);
	}
	
	@RequestMapping(value = "/graph/{type}/{year}/{month}/{communityName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO graphdashboarddetails(@PathVariable("type") String type, @PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("communityName") String communityName) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getGraphDashboardDetails(type, year, month, communityName);
	}
	
	@RequestMapping(value = "/customergraph/{type}/{year}/{month}/{customerUniqueID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO customergraphdashboarddetails(@PathVariable("type") String type, @PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("customerUniqueID") String customerUniqueID) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getCustomerGraphDashboardDetails(type, year, month, customerUniqueID);
	}
	
	@RequestMapping(value = "/server/api/{device_eui}/status", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO postDashboardDetails(@RequestBody DataRequestVO dataRequestVO, @PathVariable("device_eui") String miuID) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		
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
