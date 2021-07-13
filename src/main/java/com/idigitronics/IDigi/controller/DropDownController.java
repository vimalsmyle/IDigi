/**
 * 
 */
package com.idigitronics.IDigi.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.idigitronics.IDigi.dao.DropDownDAO;
import com.idigitronics.IDigi.response.vo.ResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
@Controller
public class DropDownController {
	
	Gson gson = new Gson();
	DropDownDAO dropdowndao = new DropDownDAO();
	
	@RequestMapping(value = "/communities/{roleID}/{id}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallcommunities(@PathVariable("roleID") int roleid, @PathVariable("id") String id) {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownCommunities(dropdowndao.getallcommunities(roleid, id));

		return responsevo;
	}
	
	@RequestMapping(value = "/blocks/{roleID}/{id}/{communityID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallblocks(@PathVariable("roleID") int roleid, @PathVariable("id") String id, @PathVariable ("communityID") int communityID) {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownBlocks(dropdowndao.getallblocks(communityID, roleid, id));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/customers/{roleID}/{id}/{blockID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getallhouses(@PathVariable("roleID") int roleid, @PathVariable("id") String id, @PathVariable ("blockID") int blockID) {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownCustomers(dropdowndao.getallcustomers(blockID, roleid, id));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/customermeters/{payType}/{CustomerUniqueID}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseVO getallcustomermeters(@PathVariable("CustomerUniqueID") String customerUniqueID, @PathVariable("payType") String payType) throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownCustomerMeters(dropdowndao.getallcustomermeters(customerUniqueID, payType));

		return responsevo;
	}
	
	@RequestMapping(value = "/topupdetails/{CustomerUniqueID}/{CustomerMeterID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO gettopupdetails(@PathVariable ("CustomerUniqueID") String CustomerUniqueID, @PathVariable ("CustomerMeterID") int CustomerMeterID) throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setTopupdetails(dropdowndao.gettopupdetails(CustomerUniqueID, CustomerMeterID));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/billdetails/{CustomerUniqueID}",method = RequestMethod.GET, 
			produces="application/json")
	public @ResponseBody ResponseVO getbilldetails(@PathVariable ("CustomerUniqueID") String CustomerUniqueID) throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setBilldetails(dropdowndao.getbilldetails(CustomerUniqueID));
		
		return responsevo;
	}
	
	@RequestMapping(value = "/tariffs",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseVO getalltariffs() throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownTariffs(dropdowndao.getalltariffs());

		return responsevo;
	}
	
	@RequestMapping(value = "/gateways",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseVO getallgateways() throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownGateways(dropdowndao.getallgateways());

		return responsevo;
	}
	
	@RequestMapping(value = "/metersizes/{type}",method = RequestMethod.GET, produces="application/json")
	public @ResponseBody ResponseVO getallmetersizes(@PathVariable ("type") String type) throws SQLException {
		
		ResponseVO responsevo = new ResponseVO();
		responsevo.setDropDownMeterSizes(dropdowndao.getallmetersizes(type));

		return responsevo;
	}
	
}
