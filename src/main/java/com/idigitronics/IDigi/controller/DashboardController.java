/**
 * 
 */
package com.idigitronics.IDigi.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import com.idigitronics.IDigi.request.vo.SensorDataRequestVO;
import com.idigitronics.IDigi.request.vo.SolarDataRequestVO;
import com.idigitronics.IDigi.response.vo.AllGraphResponseVO;
import com.idigitronics.IDigi.response.vo.DashboardResponseVO;
import com.idigitronics.IDigi.response.vo.GraphResponseVO;
import com.idigitronics.IDigi.response.vo.HomeResponseVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;
import com.idigitronics.IDigi.response.vo.SensorDashboardResponseVO;
import com.idigitronics.IDigi.response.vo.SolarDashboardResponseVO;


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
		
		communityName = (!communityName.equalsIgnoreCase("0") ? communityName.replace("%20", " ") : communityName);
		blockName = (!blockName.equalsIgnoreCase("0") ? blockName.replace("%20", " ") : blockName);

		dasboardresponsevo.setData(dashboarddao.getDashboarddetails(type, communityName, blockName, customerUniqueID, filter, null));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/dashboard/excel", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> dashboardFile(@RequestBody DashboardResponseVO dashboardResponseVO) throws SQLException, IOException {

		ResponseVO responsevo = new ResponseVO();
		DashboardDAO dashboarddao = new DashboardDAO();

		responsevo = dashboarddao.dashboardFile(dashboardResponseVO);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + responsevo.getFileName())
		        .contentType(MediaType.parseMediaType("application/octet-stream"))
		        .body(new InputStreamResource(responsevo.getByteArrayInputStream()));
		
	}
	
	@RequestMapping(value = "/filterdashboard/{type}/{communityName}/{blockName}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody DashboardResponseVO filterdashboarddetails(@PathVariable("type") String type, @PathVariable("communityName") String communityName, @PathVariable("blockName") String blockName, @RequestBody FilterVO filtervo) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		DashboardResponseVO dasboardresponsevo = new DashboardResponseVO();
		
		communityName = (!communityName.equalsIgnoreCase("0") ? communityName.replace("%20", " ") : communityName);
		blockName = (!blockName.equalsIgnoreCase("0") ? blockName.replace("%20", " ") : blockName);

		dasboardresponsevo.setData(dashboarddao.getFilterDashboarddetails(communityName, blockName, filtervo, type));
		dasboardresponsevo.setTotal(dasboardresponsevo.getData().size());
		dasboardresponsevo.setNonCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().get(dasboardresponsevo.getData().size()-1).getNonCommunicating());
		dasboardresponsevo.setCommunicating(dasboardresponsevo.getData().size() == 0 ? 0 : dasboardresponsevo.getData().size()-dasboardresponsevo.getNonCommunicating());
		
		return dasboardresponsevo;
	}
	
	@RequestMapping(value = "/filterdashboard/excel", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> filterDashboardFile(@RequestBody DashboardResponseVO dashboardResponseVO) throws SQLException, FileNotFoundException {

		ResponseVO responsevo = new ResponseVO();
		DashboardDAO dashboarddao = new DashboardDAO();

		responsevo = dashboarddao.filterDashboardFile(dashboardResponseVO);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + responsevo.getFileName())
		        .contentType(MediaType.parseMediaType("application/octet-stream"))
		        .body(new InputStreamResource(responsevo.getByteArrayInputStream()));
	}
	
	@RequestMapping(value = "/homedashboard/{type}/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeResponseVO homedashboarddetails(@PathVariable("type") String type, @PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		
		id = (!id.equalsIgnoreCase("0") ? id.replace("%20", " ") : id);

		return dashboarddao.getHomeDashboardDetails(type, roleid, id);
	}
	
	@RequestMapping(value = "/graph/{type}/{year}/{month}/{communityName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO graphdashboarddetails(@PathVariable("type") String type, @PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("communityName") String communityName) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		communityName = (!communityName.equalsIgnoreCase("0") ? communityName.replace("%20", " ") : communityName);

		return dashboarddao.getGraphDashboardDetails(type, year, month, communityName);
	}
	
	@RequestMapping(value = "/customergraph/{type}/{year}/{month}/{customerUniqueID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO customergraphdashboarddetails(@PathVariable("type") String type, @PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("customerUniqueID") String customerUniqueID) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getCustomerGraphDashboardDetails(type, year, month, customerUniqueID);
	}
	
	@RequestMapping(value = "/customergraphall/{year}/{month}/{customerUniqueID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody AllGraphResponseVO customerallgraphdashboarddetails(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("customerUniqueID") String customerUniqueID) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();

		return dashboarddao.getCustomerAllGraphDashboardDetails(year, month, customerUniqueID);
	}
	
	@RequestMapping(value = "/server/api/{device_eui}/status", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO postDashboardDetails(@RequestBody DataRequestVO dataRequestVO, @PathVariable("device_eui") String miuID) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		
		try {
			dataRequestVO.setTopupSMS(false);
			dataRequestVO.setSource("Gateway");
			responsevo = dashboarddao.postDashboarddetails(dataRequestVO, miuID);
		} catch (Exception ex) {
			logger.error("This is Error message", ex);
			ex.printStackTrace();
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/datafrommobile/{device_eui}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO datafrommobile(@RequestBody DataRequestVO dataRequestVO, @PathVariable("device_eui") String miuID) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();

		try {
			dataRequestVO.setSource("Mobile");
			dataRequestVO.setMiuID(miuID);
			
			try {
				
				logger.debug("Data of Device ID: "+miuID+" received from mobile");
				
				
					if (dataRequestVO.getType() > 0) {
						
						if(dashboarddao.validateToken(dataRequestVO)) {
							
							logger.debug("Battery Voltage: "+dataRequestVO.getBat_volt());
							
							responsevo.setResult(dashboarddao.insertdashboard(dataRequestVO, dataRequestVO.getMiuID()));
							responsevo.setMessage(responsevo.getResult().equalsIgnoreCase("Success") ? "Data Inserted Successfully" : "Data Insertion Failed");
							
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Token Validation Failed");
						}
						
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Invalid Meter Type");
					}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Data Insertion Failed");
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/datafromble/{device_eui}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO dataFromBle(@RequestBody DataRequestVO dataRequestVO, @PathVariable("device_eui") String miuID) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();

		try {
			dataRequestVO.setSource("BLE");
			dataRequestVO.setMiuID(miuID);
			
			try {
				
				logger.debug("Data of Device ID: "+miuID+" received from BLE");
				
				
					if (dataRequestVO.getType() > 0) {
						
						if(dashboarddao.validateToken(dataRequestVO)) {
							
							logger.debug("Battery Voltage: "+dataRequestVO.getBat_volt());
							
							responsevo.setResult(dashboarddao.insertdashboard(dataRequestVO, dataRequestVO.getMiuID()));
							responsevo.setMessage(responsevo.getResult().equalsIgnoreCase("Success") ? "Data Inserted Successfully" : "Data Insertion Failed");
							
						} else {
							responsevo.setResult("Failure");
							responsevo.setMessage("Token Validation Failed");
						}
						
					} else {
						responsevo.setResult("Failure");
						responsevo.setMessage("Invalid Meter Type");
					}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("Data Insertion Failed");
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/SENSORS/{equipment_serial_id}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO postSensorDashboardDetails(@RequestBody SensorDataRequestVO sensorDataRequestVO, @PathVariable("equipment_serial_id") String equipment_serial_id) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		
		try {
			sensorDataRequestVO.setEquipment_serial_id(equipment_serial_id);
			responsevo = dashboarddao.postSensorDashboarddetails(sensorDataRequestVO);
		} catch (Exception ex) {
			logger.error("This is Error message", ex);
			ex.printStackTrace();
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/sensordashboard/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SensorDashboardResponseVO sensorDashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") int id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		SensorDashboardResponseVO sensorDasboardresponsevo = new SensorDashboardResponseVO();
		
		sensorDasboardresponsevo.setData(dashboarddao.getSensorDashboarddetails());
		
		return sensorDasboardresponsevo;
	}
	
	@RequestMapping(value = "/solarstatus/{blockid}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody ResponseVO postSolarDashboardDetails(@RequestBody SolarDataRequestVO solarDataRequestVO, @PathVariable("blockid") int blockid) {

		DashboardDAO dashboarddao = new DashboardDAO();
		ResponseVO responsevo = new ResponseVO();
		
		try {
			solarDataRequestVO.setBlockid(blockid);
			responsevo = dashboarddao.postSolarDashboarddetails(solarDataRequestVO);
		} catch (Exception ex) {
			logger.error("This is Error message", ex);
			ex.printStackTrace();
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/solardashboard/{communityName}/{blockName}/{customerUniqueID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SolarDashboardResponseVO solarDashboarddetails(@PathVariable("communityName") String communityName, @PathVariable("blockName") String blockName, @PathVariable("customerUniqueID") String customerUniqueID) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		SolarDashboardResponseVO solarDasboardresponsevo = new SolarDashboardResponseVO();
		
		communityName = (!communityName.equalsIgnoreCase("0") ? communityName.replace("%20", " ") : communityName);
		blockName = (!blockName.equalsIgnoreCase("0") ? blockName.replace("%20", " ") : blockName);
		
		solarDasboardresponsevo.setData(dashboarddao.getSolarDashboarddetails(communityName, blockName, customerUniqueID));
		
		return solarDasboardresponsevo;
	}
	
	@RequestMapping(value = "/solarhomedashboard/{roleid}/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody HomeResponseVO solarHomedashboarddetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		
		id = (!id.equalsIgnoreCase("0") ? id.replace("%20", " ") : id);

		return dashboarddao.getSolarHomeDashboardDetails(roleid, id);
	}
	
	@RequestMapping(value = "/solargraph/{communityName}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody GraphResponseVO solarGraphdashboarddetails(@PathVariable("communityName") String communityName) throws SQLException {

		DashboardDAO dashboarddao = new DashboardDAO();
		communityName = (!communityName.equalsIgnoreCase("0") ? communityName.replace("%20", " ") : communityName);

		return dashboarddao.getSolarGraphDashboardDetails(communityName);
	}
	
}
