/**
 * 
 */
package com.idigitronics.IDigi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.core.io.InputStreamResource;
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

import com.google.gson.Gson;
import com.idigitronics.IDigi.bo.AccountBO;
import com.idigitronics.IDigi.dao.AccountDAO;
import com.idigitronics.IDigi.dao.ExtraMethodsDAO;
import com.idigitronics.IDigi.exceptions.BusinessException;
import com.idigitronics.IDigi.request.vo.CheckOutRequestVO;
import com.idigitronics.IDigi.request.vo.ConfigurationRequestVO;
import com.idigitronics.IDigi.request.vo.DataRequestVO;
import com.idigitronics.IDigi.request.vo.PayBillRequestVO;
import com.idigitronics.IDigi.request.vo.TopUpRequestVO;
import com.idigitronics.IDigi.response.vo.BillingResponseVO;
import com.idigitronics.IDigi.response.vo.ConfigurationResponseVO;
import com.idigitronics.IDigi.response.vo.ConfigurationStatusResponseVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;
import com.idigitronics.IDigi.response.vo.StatusResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */


@Controller
public class AccountController {

	Gson gson = new Gson();
	AccountBO accountbo = new AccountBO();
	AccountDAO accountdao = new AccountDAO();

	/* TopUp */

	@RequestMapping(value = "/topup", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addtopup(@RequestBody TopUpRequestVO topupRequestVO) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = accountbo.addtopup(topupRequestVO);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO updatetopup(@RequestBody CheckOutRequestVO checkOutRequestVO) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = accountdao.updatepayment(checkOutRequestVO);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/server/api/{device_eui}/topup/response", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO updatetopup(@RequestBody DataRequestVO dataRequestVO, @PathVariable("device_eui") String miuID)
			throws ClassNotFoundException, SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		
		try{
			responsevo = accountdao.updatetopupstatus(dataRequestVO, miuID);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	/* Status */

	@RequestMapping(value = "/status/{roleid}/{id}/{filterCid}/{day}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	StatusResponseVO statusdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid, @PathVariable("day") int day) throws SQLException {

		StatusResponseVO statusresponsevo = new StatusResponseVO();

		statusresponsevo.setData(accountdao.getStatusdetails(roleid, id, filterCid, day));

		return statusresponsevo;
	}

	@RequestMapping(value = "/status/delete/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO deletestatus(@PathVariable("transactionID") int transactionID)
			throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo = accountdao.deletestatus(transactionID);

		return responsevo;
	}
	
	@RequestMapping(value = "/status/print/{transactionID}", method = RequestMethod.GET, produces = "application/pdf")
	 public ResponseEntity<InputStreamResource> download(@PathVariable("transactionID") int transactionID) throws IOException, SQLException {
			
		ResponseVO responsevo = new ResponseVO();
		
		responsevo = accountdao.printreceipt(transactionID);
	
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
		return response;
	
	}
	
	/* Billing Details */

	@RequestMapping(value = "/billing/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	BillingResponseVO billdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid) throws SQLException {

		BillingResponseVO billingresponsevo = new BillingResponseVO();

		billingresponsevo.setData(accountdao.getbillingdetails(roleid, id, filterCid));

		return billingresponsevo;
	}
	
	@RequestMapping(value = "/billing/excel", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> dashboardFile(@RequestBody BillingResponseVO billingResponseVO) throws SQLException, FileNotFoundException {

		ResponseVO responsevo = new ResponseVO();

		responsevo = accountdao.billingFile(billingResponseVO);
		
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + responsevo.getFileName())
		        .contentType(MediaType.parseMediaType("application/octet-stream"))
		        .body(new InputStreamResource(responsevo.getByteArrayInputStream()));
	}
	
	@RequestMapping(value = "/paybill", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO paybill(@RequestBody PayBillRequestVO paybillRequestVO) throws ClassNotFoundException,
			BusinessException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		try {
			responsevo = accountbo.payBill(paybillRequestVO);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	@RequestMapping(value = "/billing/print/{customerUniqueID}", method = RequestMethod.GET, produces = "application/pdf")
	 public ResponseEntity<InputStreamResource> downloadbill(@PathVariable("customerUniqueID") String customerUniqueID) throws IOException, SQLException {
			
		ResponseVO responsevo = new ResponseVO();
		
		LocalDate currentdate = LocalDate.now();
		
		responsevo.setLocation("D:/Bills/" + (currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear()+"/"+(currentdate.getMonthValue() - 1) + "/"));
		responsevo.setFileName(customerUniqueID + ".pdf");
	
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
		return response;
	
	}
	
	@RequestMapping(value = "/billing/printreceipt/{transactionID}", method = RequestMethod.GET, produces = "application/pdf")
	 public ResponseEntity<InputStreamResource> downloadreceipt(@PathVariable("transactionID") int transactionID) throws IOException, SQLException {
			
		ResponseVO responsevo = new ResponseVO();
		
		responsevo = accountdao.printbillreceipt(transactionID);
	
		File file = new File(responsevo.getLocation() + responsevo.getFileName());
	    InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
		ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(resource, HttpStatus.OK);
		
		return response;
	
	}
	
	/* Configuration */

	@RequestMapping(value = "/configuration/{roleid}/{id}/{filterCid}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ConfigurationResponseVO configurationdetails(@PathVariable("roleid") int roleid, @PathVariable("id") String id, @PathVariable("filterCid") int filterCid) throws SQLException {

		ConfigurationResponseVO configurationresponsevo = new ConfigurationResponseVO();

		configurationresponsevo.setData(accountdao.getConfigurationdetails(roleid, id, filterCid));

		return configurationresponsevo;
	}

	@RequestMapping(value = "/configuration/add", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO addconfiguration(@RequestBody ConfigurationRequestVO configurationvo)
			throws ClassNotFoundException, SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		try{
			responsevo = accountbo.addconfiguration(configurationvo);
		} catch (BusinessException e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}

	
	@RequestMapping(value = "/server/api/{device_eui}/set/response", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO updateconfiguration(@RequestBody ConfigurationStatusResponseVO configurationstatusvo, @PathVariable("device_eui") String miuID)
			throws ClassNotFoundException, SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		try{
			responsevo = accountdao.updateconfiguration(configurationstatusvo, miuID);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/server/api/{device_eui}/group/set/response", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO updategroupconfiguration(@RequestBody ConfigurationStatusResponseVO configurationstatusvo, @PathVariable("device_eui") String miuID)
			throws ClassNotFoundException, SQLException, BusinessException {

		ResponseVO responsevo = new ResponseVO();
		try{
			responsevo = accountdao.updateconfiguration(configurationstatusvo, miuID);
		} catch (Exception e) {
			responsevo.setResult("Failure");
			responsevo.setMessage(e.getMessage());
		}

		return responsevo;
	}
	
	@RequestMapping(value = "/configuration/delete/{transactionID}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO editcommunity(@PathVariable("transactionID") int transactionID)
			throws ClassNotFoundException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		responsevo = accountdao.deleteconfiguration(transactionID);

		return responsevo;
	}
	
	@RequestMapping(value = "/test/{transactionID}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	ResponseVO testmethod(@PathVariable("transactionID") int transactionID)
			throws ClassNotFoundException, SQLException {
		ResponseVO responsevo = new ResponseVO();
		/*MailRequestVO mailrequestvo = new MailRequestVO();
		mailrequestvo.setToEmail("kvk9889@gmail.com");
		mailrequestvo.setSubject("testmail");
		mailrequestvo.setMessage("hello test");
		mailrequestvo.setFileLocation("NoAttachment");*/
		ExtraMethodsDAO extramethods = new ExtraMethodsDAO();
//		responsevo = accountdao.printbillreceipt(transactionID);
//		responsevo.setMessage(extramethods.sendmail(mailrequestvo));
//		extramethods.individualbillgeneration();
		
		extramethods.billgeneration();

		return responsevo;
	}

}
