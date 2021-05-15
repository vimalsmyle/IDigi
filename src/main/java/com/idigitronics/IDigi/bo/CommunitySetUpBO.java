/**
 * 
 */
package com.idigitronics.IDigi.bo;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.idigitronics.IDigi.dao.CommunitySetUpDAO;
import com.idigitronics.IDigi.exceptions.BusinessException;
import com.idigitronics.IDigi.request.vo.BlockRequestVO;
import com.idigitronics.IDigi.request.vo.CommunityRequestVO;
import com.idigitronics.IDigi.request.vo.CustomerRequestVO;
import com.idigitronics.IDigi.request.vo.GatewayRequestVO;
import com.idigitronics.IDigi.request.vo.TariffRequestVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class CommunitySetUpBO {
	
	CommunitySetUpDAO communitysetupdao = new CommunitySetUpDAO();

	/* Community */

	public ResponseVO addcommunity(CommunityRequestVO communityvo)
			throws SQLException, BusinessException {
		
		// TODO Auto-generated method stub
		
		if (communityvo.getCommunityName().isEmpty() || communityvo.getmobileNumber().isEmpty() || communityvo.getEmail().isEmpty() || communityvo.getAddress().isEmpty()) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		communityvo.setCommunityID(0);
		
		if(communitysetupdao.checkIfCommunityNameExists(communityvo, "add")) {
			throw new BusinessException("COMMUNITY NAME ALREADY EXISTS");
		}
		
		if (!checkEmailID(communityvo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(communityvo.getmobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}
		
		
		
		return communitysetupdao.addcommunity(communityvo);
	}

	public ResponseVO editcommunity(CommunityRequestVO communityvo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if (communityvo.getCommunityName().isEmpty() || communityvo.getmobileNumber().isEmpty() || communityvo.getEmail().isEmpty() || communityvo.getAddress().isEmpty()) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if(communitysetupdao.checkIfCommunityNameExists(communityvo, "edit")) {
			throw new BusinessException("COMMUNITY NAME ALREADY EXISTS");
		}
		
		if (!checkEmailID(communityvo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(communityvo.getmobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}

		return communitysetupdao.editcommunity(communityvo);
	}
	
	/* Gateway */

	public ResponseVO addgateway(GatewayRequestVO gatewayvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		if(gatewayvo.getGatewayName().isEmpty() || gatewayvo.getGatewayIP().isEmpty() || gatewayvo.getGatewayPort().isEmpty() || gatewayvo.getGatewaySerialNumber().isEmpty()){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if(communitysetupdao.checkgateway(gatewayvo.getGatewaySerialNumber())) {
			throw new BusinessException("GATEWAY ALREADY EXISTS");
		}

		return communitysetupdao.addgateway(gatewayvo);
	}
	
	public ResponseVO editgateway(GatewayRequestVO gatewayvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(gatewayvo.getGatewayName().isEmpty() || gatewayvo.getGatewayIP().isEmpty() || gatewayvo.getGatewayPort().isEmpty() || gatewayvo.getGatewaySerialNumber().isEmpty()){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		return communitysetupdao.editgateway(gatewayvo);
	}
	
	public ResponseVO deletegateway(int gatewayID) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		
		if(communitysetupdao.checkgatewayIsSetToCustomers(gatewayID)) {
			throw new BusinessException("GATEWAY CANNOT BE DELETED AS IT IS ASSIGNED TO CUSTOMERS");
		}

		return communitysetupdao.deletegateway(gatewayID);
	}

	/* Block */

	public ResponseVO addblock(BlockRequestVO blockvo) throws SQLException,
			BusinessException {
		// TODO Auto-generated method stub

		if (blockvo.getCommunityID()==0 || blockvo.getBlockName().isEmpty() || blockvo.getEmail().isEmpty() || blockvo.getLocation().isEmpty() || blockvo.getMobileNumber().isEmpty()) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		if (checkName(blockvo.getBlockName())) {
			throw new BusinessException("BLOCK NAME MUST BE ALPHANUMERIC ONLY");
		}
		
		if(communitysetupdao.checkIfBlockNameExists(blockvo, "add")) {
			throw new BusinessException("BLOCK NAME ALREADY EXISTS IN THE SELECTED COMMUNITY");
		}
		
		if (!checkEmailID(blockvo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(blockvo.getMobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}

		return communitysetupdao.addblock(blockvo);
	}

	public ResponseVO editblock(BlockRequestVO blockvo) throws SQLException,
			BusinessException {
		// TODO Auto-generated method stub

		if (checkName(blockvo.getBlockName())) {
			throw new BusinessException("BLOCK NAME MUST BE ALPHANUMERIC ONLY");
		}
		
		if(communitysetupdao.checkIfBlockNameExists(blockvo, "edit")) {
			throw new BusinessException("BLOCK NAME ALREADY EXISTS IN THE SELECTED COMMUNITY");
		}
		
		if (!checkEmailID(blockvo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(blockvo.getMobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}

		return communitysetupdao.editblock(blockvo);
	}

	public ResponseVO deleteblock(int blockID) throws BusinessException {
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = new ResponseVO();
		
		try {

			if (communitysetupdao.checkifhousesexist(blockID)) {
				throw new BusinessException(
						"DELETE ALL CUSTOMERS IN THE BLOCK BEFORE DELETING THE BLOCK");
			}

			responsevo = communitysetupdao.deleteblock(blockID);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			responsevo.setMessage("DATABASE ERROR");
			responsevo.setResult("Failure");
		}

		return responsevo;
	}

	/* Customer */

	public ResponseVO addcustomer(CustomerRequestVO customervo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		if (customervo.getCommunityID()==0
				|| customervo.getBlockID()==0
				|| customervo.getHouseNumber().isEmpty()
				|| customervo.getFirstName().isEmpty()
				|| customervo.getLastName().isEmpty()
				|| customervo.getEmail().isEmpty()
				|| customervo.getMobileNumber().isEmpty()) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if(customervo.getMeters().size() == 0) {
			throw new BusinessException("NO METERS ASSIGNED TO CUSTOMER");
		}

		if (checkName(customervo.getFirstName()) == true || checkName(customervo.getLastName()) == true) {
			throw new BusinessException("NAME MUST BE ALPHANUMERIC ONLY");
		}

		if (!checkEmailID(customervo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(customervo.getMobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}
		
		if(communitysetupdao.checkcustomerName(customervo)) {
			throw new BusinessException("CUSTOMER NAME ALREADY EXISTS");
		}
		
		if(communitysetupdao.checkHouseNumber(customervo)) {
			throw new BusinessException("HOUSE NUMBER IS ALREADY REGISTERED");
		}
		
		if(communitysetupdao.checkCustomerUniqueID(customervo.getCustomerUniqueID())) {
			throw new BusinessException("CUSTOMERUNIQUEID IS ALREADY REGISTERED");
		}
		
		return communitysetupdao.addcustomer(customervo);
	}

	public ResponseVO editcustomer(CustomerRequestVO customervo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		if(communitysetupdao.checkpendingrequest(customervo.getCustomerUniqueID())) {
			throw new BusinessException("PREVIOUS REQUEST IS PENDING FOR APPROVAL");
		}

		if (customervo.getFirstName().isEmpty()
				|| customervo.getEmail().isEmpty()
				|| customervo.getMobileNumber().isEmpty()
				|| customervo.getCustomerUniqueID().isEmpty()) {
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}

		if (checkName(customervo.getFirstName()) == true) {
			throw new BusinessException("NAME CAN CONTAIN ONLY ALPHABETS");
		}

		if (!checkEmailID(customervo.getEmail())) {
			throw new BusinessException("INVALID EMAIL ID");
		}

		if (!checkMobileNo(customervo.getMobileNumber())) {
			throw new BusinessException(
					"MOBILE NUMBER CAN CONTAIN ONLY NUMERIC VALUES OF EXACTLY 10 DIGITS");
		}
		
		return communitysetupdao.editcustomer(customervo);
	}

	public ResponseVO deletecustomer(CustomerRequestVO customervo)
			throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = new ResponseVO();
		
		try {

			responsevo = communitysetupdao.deletecustomer(customervo);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responsevo;
	}
	
	/* Tariff */

	public ResponseVO addtariff(TariffRequestVO tariffvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		if(tariffvo.getTariff()==0 || tariffvo.getAlarmCredit()==0 || tariffvo.getEmergencyCredit()==0 || tariffvo.getFixedCharges()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if(tariffvo.getEmergencyCredit() < tariffvo.getTariff()) {
			throw new BusinessException("EMERGENCY CREDIT MUST BE GREATER THAN TARIFF AMOUNT");
		}
		
		if(communitysetupdao.checktariffamount(tariffvo.getTariff())) {
			throw new BusinessException("TARIFF AMOUNT ALREADY EXISTS");
		}

		return communitysetupdao.addtariff(tariffvo);
	}
	
	public ResponseVO edittariff(TariffRequestVO tariffvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(tariffvo.getTariff()==0 || tariffvo.getAlarmCredit()==0 || tariffvo.getEmergencyCredit()==0 || tariffvo.getFixedCharges()==0){
			throw new BusinessException("ALL FIELDS ARE MANDATORY");
		}
		
		if(tariffvo.getEmergencyCredit() < tariffvo.getTariff()) {
			throw new BusinessException("EMERGENCY CREDIT MUST BE GREATER THAN TARIFF AMOUNT");
		}
		
		return communitysetupdao.edittariff(tariffvo);
	}
	
	public ResponseVO deletetariff(int tariffID) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		
		if(communitysetupdao.checktariffIsSetToCustomers(tariffID)) {
			throw new BusinessException("TARIFF CANNOT BE DELETED AS IT IS ASSIGNED TO CUSTOMERS");
		}

		return communitysetupdao.deletetariff(tariffID);
	}

	/* Validations */

	private boolean checkName(String customerName) {
		// TODO Auto-generated method stub
		boolean result = false;

		Pattern pattern = Pattern.compile("[A-Za-z0-9]");
		Matcher matcher = pattern.matcher(customerName);
		if (!matcher.find()) {
			result = true;
		}
		return result;
	}

	private boolean checkMobileNo(String contactNumber) {
		// TODO Auto-generated method stub
		boolean result = false;
		Pattern pattern = Pattern.compile("[0-9-]\\d{10}");
		Matcher matcher = pattern.matcher(contactNumber);
		
		if (!matcher.find()) {
			result = true;
		}
		return result;
	}

	private boolean checkEmailID(String emailId) {
		// TODO Auto-generated method stub

		boolean result = false;

		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@ [A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(emailId);

		if (!matcher.find()) {
			result = true;
		}

		return result;

	}

}
