/**
 * 
 */
package com.idigitronics.IDigi.bo;

import java.sql.SQLException;

import com.idigitronics.IDigi.dao.AccountDAO;
import com.idigitronics.IDigi.exceptions.BusinessException;
import com.idigitronics.IDigi.request.vo.ConfigurationRequestVO;
import com.idigitronics.IDigi.request.vo.PayBillRequestVO;
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
		
		if(accountdao.validateBalance(topupvo)){
			throw new BusinessException("SUM OF AVAILABLE BALANCE AND RECHARGE AMOUNT MUST BE LESS THAN Rs.2000/-");
		} else if(topupvo.getAmount() > 2000) {
			throw new BusinessException("RECHARGE AMOUNT MUST BE LESS THAN Rs.2000/-");
		}
		
		if(accountdao.checktopup(topupvo.getCustomerMeterID())) {
			throw new BusinessException("PREVIOUS TOPUP REQUEST IS PENDING");
		}
		
		return accountdao.addtopup(topupvo);
	}
	
	public ResponseVO payBill(PayBillRequestVO paybillRequestVO) throws SQLException, BusinessException {
		// TODO Auto-generated method stub
		
		if(accountdao.checkBillPaymentStatus(paybillRequestVO.getCustomerBillingID())) {
			throw new BusinessException("BILL IS ALREADY PAID");
		}
		
		return accountdao.paybill(paybillRequestVO);
	}
	
	/* Configuration */
	
	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo) throws BusinessException, SQLException {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();
			/*if (accountdao.checkconfigstatus(configurationvo.getMiuID())) {
				throw new BusinessException("PREVIOUS COMMAND REQUEST IS PENDING");
			}*/
			
			responsevo = accountdao.addconfiguration(configurationvo);
			
		return responsevo;
	}

}
