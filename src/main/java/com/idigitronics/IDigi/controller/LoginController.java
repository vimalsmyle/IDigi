/**
 * 
 */
package com.idigitronics.IDigi.controller;

import java.sql.SQLException;

import javax.mail.MessagingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.idigitronics.IDigi.bo.LoginBO;
import com.idigitronics.IDigi.constants.ExtraConstants;
import com.idigitronics.IDigi.exceptions.BusinessException;
import com.idigitronics.IDigi.request.vo.LoginVO;
import com.idigitronics.IDigi.request.vo.UserManagementRequestVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;
import com.idigitronics.IDigi.utils.Encryptor;

/**
 * @author K VimaL Kumar
 * 
 */
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO validateUser(@RequestBody LoginVO loginvo)
			throws ClassNotFoundException, BusinessException, SQLException {

		LoginBO loginbo = new LoginBO();
		ResponseVO responsevo = new ResponseVO();

		loginvo.setPassword(Encryptor.encrypt(ExtraConstants.key1,
				ExtraConstants.key2, loginvo.getPassword()));

		try {
			responsevo = loginbo.validateUser(loginvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		
		return responsevo;
	}

	@RequestMapping(value = "/forgotpassword/{userid}", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody
	ResponseVO forgotpassword(@PathVariable("userid") String userid)
			throws ClassNotFoundException, BusinessException, SQLException {

		LoginBO loginbo = new LoginBO();
		ResponseVO responsevo = new ResponseVO();

		try {
			responsevo = loginbo.forgotpassword(userid);
			responsevo.setResult("Success");
		}
		 catch (BusinessException e) {
				String message = e.getMessage();
				responsevo.setMessage(message);
			}
		catch (MessagingException e) {
			String message = e.getMessage();
			responsevo.setMessage(message);
		}
		return responsevo;
	}
	
	@RequestMapping(value = "/changepassword/{userID}", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody
	ResponseVO changepassword(@PathVariable("userID") String userid,
			@RequestBody UserManagementRequestVO usermanagementvo) throws ClassNotFoundException,
			BusinessException, SQLException {

		LoginBO loginbo = new LoginBO();
		ResponseVO responsevo = new ResponseVO();
		
		usermanagementvo.setOldPassword(Encryptor.encrypt(ExtraConstants.key1,
				ExtraConstants.key2, usermanagementvo.getOldPassword()));
		usermanagementvo.setNewPassword(Encryptor.encrypt(
				ExtraConstants.key1, ExtraConstants.key2,
				usermanagementvo.getNewPassword()));

		usermanagementvo.setUserID(userid);
		
		try{
		responsevo = loginbo.changepassword(usermanagementvo);
		} catch (BusinessException e) {
			String message = e.getMessage();
			responsevo.setResult("Failure");
			responsevo.setMessage(message);
		}

		return responsevo;
	}
}
