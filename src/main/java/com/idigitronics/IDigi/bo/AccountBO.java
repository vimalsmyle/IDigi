/**
 * 
 */
package com.idigitronics.IDigi.bo;

import java.sql.SQLException;

import com.idigitronics.IDigi.dao.AccountDAO;
import com.idigitronics.IDigi.exceptions.BusinessException;
import com.idigitronics.IDigi.request.vo.ConfigurationRequestVO;
import com.idigitronics.IDigi.request.vo.TopUpRequestVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
public class AccountBO {

	AccountDAO accountdao = new AccountDAO();
	
	/* TopUp */
	
	public ResponseVO addtopup(TopUpRequestVO topupvo) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(accountdao.validateamount(topupvo)){
			throw new BusinessException("RECHARGE AMOUNT MUST BE GREATER THAN EMERGENCY CREDIT AND UNIT RATE");
		}
		
		if(accountdao.checktopup(topupvo.getMeterID())) {
			throw new BusinessException("PREVIOUS TOPUP REQUEST IS PENDING");
		}
		
		return accountdao.addtopup(topupvo);
	}
	
	/* Configuration */
	
	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();
			if (accountdao.checkconfigstatus(configurationvo.getMiuID())) {
				throw new BusinessException("PREVIOUS COMMAND REQUEST IS PENDING");
			}
			
			responsevo = accountdao.addconfiguration(configurationvo);
			
		return responsevo;
	}

}
