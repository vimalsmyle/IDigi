package com.idigitronics.IDigi.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.idigitronics.IDigi.constants.DataBaseConstants;
import com.idigitronics.IDigi.constants.ExtraConstants;
import com.idigitronics.IDigi.request.vo.MailRequestVO;
import com.idigitronics.IDigi.request.vo.RazorPayOrderVO;
import com.idigitronics.IDigi.request.vo.RazorpayRequestVO;
import com.idigitronics.IDigi.request.vo.RestCallVO;
import com.idigitronics.IDigi.request.vo.SMSRequestVO;
import com.idigitronics.IDigi.response.vo.RazorPayResponseVO;
import com.idigitronics.IDigi.response.vo.TataResponseVO;
/**
 * @author K VimaL Kumar
 * 
 */
@EnableScheduling
public class ExtraMethodsDAO {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}
	
	Gson gson = new Gson();
	
	public String sendmail(MailRequestVO mailrequestvo) {
		
		String result = "Failure";
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(ExtraConstants.fromEmail, ExtraConstants.fromEmailPassword);// change accordingly
			}});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ExtraConstants.fromEmail));// change accordingly
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailrequestvo.getToEmail()));
			message.setSubject(mailrequestvo.getSubject());
			message.setText(mailrequestvo.getMessage());

			Transport.send(message);
			result = "Success";

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public ResponseEntity<String> sendsms(SMSRequestVO smsRequestVO) {
		// TODO Auto-generated method stub
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.postForEntity(ExtraConstants.SMSAPI+smsRequestVO.getToMobileNumber()+ExtraConstants.SenderID+smsRequestVO.getMessage(), HttpMethod.POST, String.class);
		
		return response;
		
	}
	
	public int postdata(RestCallVO restcallvo) throws IOException {
		
	URL url = new URL(ExtraConstants.StaticIP+restcallvo.getMiuID()+"/"+restcallvo.getUrlExtension());
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    
    urlConnection.setRequestProperty("Content-Type", "application/json"); 
    
//    final String tataAuthenication = "Basic "	+ Base64.getEncoder().encodeToString((ExtraConstants.TataUserName + ':' + ExtraConstants.TataPassword).getBytes());

//	urlConnection.setRequestProperty("Authorization", tataAuthenication);
	
	String data = gson.toJson(restcallvo, RestCallVO.class);
		// Send post request
		urlConnection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
	
	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	String inputLine;
	StringBuffer responses = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		responses.append(inputLine);
	}
	in.close();
//	return responses.toString();
	return urlConnection.getResponseCode();
}
	
	public String razorpaypost(RazorPayOrderVO razorPayOrderVO, RazorpayRequestVO razorpayRequestVO) throws IOException {
		
	JSONObject json = new JSONObject();
	String data = "";
	
	String restUrl =  razorpayRequestVO.getApi().equalsIgnoreCase("payments") ? ExtraConstants.RZPBasicUrl+razorpayRequestVO.getApi()+"/"+razorpayRequestVO.getId()+"/"+razorpayRequestVO.getExtension() : ExtraConstants.RZPBasicUrl+razorpayRequestVO.getApi();
		
	URL url = new URL(restUrl);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    
    urlConnection.setRequestProperty("Content-Type", "application/json"); 
    
    final String authHeaderValue = "Basic "	+ Base64.getEncoder().encodeToString((ExtraConstants.RZPKeyID + ':' + ExtraConstants.RZPKeySecret).getBytes());
    
	urlConnection.setRequestProperty("Authorization", authHeaderValue);
	
	if(razorpayRequestVO.getApi().equalsIgnoreCase("orders")) {
	data = gson.toJson(razorPayOrderVO, RazorPayOrderVO.class);
	} else {
		if(razorpayRequestVO.getExtension().equalsIgnoreCase("refund")) {
			json.put("amount", razorpayRequestVO.getAmount());	
		} else {
			json.put("amount", razorpayRequestVO.getAmount());
			json.put("currency", razorpayRequestVO.getCurrency());
		}
		
		data = json.toString();
	}
		// Send post request
		urlConnection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();
	
	BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	String inputLine;
	StringBuffer responses = new StringBuffer();

	while ((inputLine = in.readLine()) != null) {
		responses.append(inputLine);
	}
	in.close();
	return responses.toString();
}
	
	
//	@Scheduled(cron="0 0 * ? * *") // scheduled for 1 hour
//	@Scheduled(cron="0 0/2 * * * ?") // scheduled for every 2 min
/*	public void topupstatusupdatecall() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SMSRequestVO smsRequestVO = new SMSRequestVO();
		MailRequestVO mailRequestVO = new MailRequestVO();
		RazorpayRequestVO razorpayRequestVO = new RazorpayRequestVO();
		
		try {
			
			con = getConnection();
			pstmt = con.prepareStatement("SELECT t.MeterID, t.TataReferenceNumber, t.PaymentStatus, cmd.MobileNumber, cmd.Email, cmd.CRNNumber, t.Amount, t.RazorPayPaymentID, t.TransactionID, t.ModeOfPayment FROM topup AS t LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = t.CRNNumber WHERE t.Status IN (0, 1) AND t.Source = 'web' AND t.TataReferenceNumber != 0");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				RestCallVO restcallvo  = new RestCallVO();
				restcallvo.setUrlExtension("/payloads/dl/");
				restcallvo.setMiuID(rs.getString("MeterID").toLowerCase());
				
				ResponseEntity<TataResponseVO> response = tataget(restcallvo);
				
				PreparedStatement pstmt1 = con.prepareStatement("UPDATE topup SET Status = ?, AcknowledgeDate= NOW() WHERE TataReferenceNumber = ?");

				pstmt1.setInt(1, response.getBody().getTransmissionStatus());
				pstmt1.setLong(2, response.getBody().getId());
				if(pstmt1.executeUpdate() > 0){
					
					smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
					smsRequestVO.setMessage(response.getBody().getTransmissionStatus() == 2 ? "Thank You for Recharging your CRN: "+ rs.getString("CRNNumber")+". Your request has been processed successfully." : response.getBody().getTransmissionStatus() > 2 ? "Your Recharge request has failed to reach the CRN: "+ rs.getString("CRNNumber")+". Kindly retry after sometime. Deducted Amount will be refunded in 5-10 working days. We regret the inconvenience caused." : "");
					
					mailRequestVO.setToEmail(rs.getString("Email"));
					mailRequestVO.setSubject("Recharge Status!!!");
					mailRequestVO.setMessage(response.getBody().getTransmissionStatus() == 2 ? "Thank You for Recharging your CRN: "+ rs.getString("CRNNumber")+". Your request has been processed successfully." : response.getBody().getTransmissionStatus() > 2 ? "Your Recharge request has failed to reach the CRN: "+ rs.getString("CRNNumber")+". Kindly retry after sometime. Deducted Amount will be refunded in 5-10 working days. We regret the inconvenience caused." : "");
					
					if(response.getBody().getTransmissionStatus() >= 2) {
						sendsms(smsRequestVO);
						sendmail(mailRequestVO);
					}
					
					
					if(response.getBody().getTransmissionStatus() >= 3 && rs.getInt("PaymentStatus") == 1 && rs.getString("ModeOfPayment").equalsIgnoreCase("Online")) {
						// initiate refund process
						
						  razorpayRequestVO.setApi("payments");
						  razorpayRequestVO.setId(rs.getString("RazorPayPaymentID"));
						  razorpayRequestVO.setAmount(rs.getInt("Amount")*100);
						  razorpayRequestVO.setExtension("refund");
						  
						  String rzpRestCallResponse = razorpaypost(null, razorpayRequestVO);
						  
						  RazorPayResponseVO razorPayResponseVO = gson.fromJson(rzpRestCallResponse, RazorPayResponseVO.class);
						  
						  PreparedStatement pstmt2 = con.
						  prepareStatement("UPDATE topup SET PaymentStatus = 3, RazorPayRefundID = ?, RazorPayRefundStatus = ?, RazorPayRefundEntity = ? WHERE TransactionID = "+ rs.getLong("TransactionID"));
						  pstmt2.setString(1,razorPayResponseVO.getId()); 
						  pstmt2.setString(2,razorPayResponseVO.getStatus()); 
						  pstmt2.setString(3,rzpRestCallResponse);
						  
						  if (pstmt2.executeUpdate() > 0) {
						  
						  smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
						  smsRequestVO.setMessage("Your Refund of Amount: "+rs.getInt("Amount")
						  +"/- is initiated and will be credited to your original mode of payment in 5-10 working days. We regret the inconvenience caused."
						  );
						  
						   sendsms(smsRequestVO);
						  
						  mailRequestVO.setToEmail(rs.getString("Email"));
						  mailRequestVO.setSubject("Refund Initiated!!!");
						  mailRequestVO.setMessage("Your Refund of Amount: "+rs.getInt("Amount")
						  +"/- is initiated and will be credited to your original mode of payment in 5-10 working days. We regret the inconvenience caused."
						  );
						  
						  sendmail(mailRequestVO);
						  
						  }
					}
					
				}

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
	}*/
	
//	@Scheduled(cron="0 0 * ? * *")
/*	@Scheduled(cron="0 0/4 * * * ?") 
	public void razorpayPaymentCapture() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RazorpayRequestVO razorpayRequestVO = new RazorpayRequestVO();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT PaymentStatus, Amount, RazorPayPaymentID, TransactionID, ModeOfPayment FROM topup WHERE STATUS = 2 AND Source = 'web' AND TataReferenceNumber != 0 AND ModeOfPayment = 'Online' AND PaymentStatus = 1");
			rs = pstmt.executeQuery();
			while(rs.next()) {
			
				razorpayRequestVO.setApi("payments");
				razorpayRequestVO.setId(rs.getString("RazorPayPaymentID"));
				razorpayRequestVO.setAmount(rs.getInt("Amount")*100);
				razorpayRequestVO.setCurrency(ExtraConstants.PaymentCurrency);
				razorpayRequestVO.setExtension("capture");
				
			// change notes variable in RazorPayResponseVO according to capture requirement	
			
				String rzpRestCallResponse = razorpaypost(null, razorpayRequestVO);
				  
				  RazorPayResponseVO razorPayResponseVO = gson.fromJson(rzpRestCallResponse, RazorPayResponseVO.class);
				  
				  // insert response in database

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
	}*/
	
//	@Scheduled(cron="0 0 7 ? * *")
//	@Scheduled(cron="0 0 7 ? * TUE,FRI") 
	public void communicationfailurealert() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		MailRequestVO mailRequestVO = null;
		SMSRequestVO smsRequestVO = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT cmd.MeterID, b.Email, b.MobileNumber, cmd.HouseNumber, cmd.CRNNumber FROM customermeterdetails AS cmd LEFT JOIN block AS b ON cmd.BlockID = b.BlockID");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				pstmt1 = con.prepareStatement("SELECT ((SELECT (TIMESTAMPDIFF (MINUTE, (SELECT IotTimeStamp FROM displaybalancelog WHERE MeterID = ?), NOW()))) - (SELECT NoAMRInterval FROM alertsettings)) AS diff");
				pstmt1.setString(1, rs.getString("MeterID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("diff") > 0) {
						
						mailRequestVO = new MailRequestVO();
						smsRequestVO = new SMSRequestVO();
						
						mailRequestVO.setSubject("No Communication from MIU ID: "+rs.getString("MeterID"));
						mailRequestVO.setToEmail(rs.getString("Email"));
						mailRequestVO.setMessage("Dear Admin, \n \n CRNNumber: "+rs.getString("CRNNumber")+ " is not up to date for more than 3 days.");
						
						smsRequestVO.setMessage("Dear Admin, \n \n CRNNumber: "+rs.getString("CRNNumber")+ " is not up to date for more than 3 days.");
						smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
						
						sendmail(mailRequestVO);
						sendsms(smsRequestVO);
						
					}
				}

			} 
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
	}
	
	public static String datetimeformatter(String dateTime) throws ParseException {
		
		SimpleDateFormat IOTFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat clientFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return clientFormat.format(IOTFormat.parse(dateTime));
	}
}
