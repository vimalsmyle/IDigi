package com.idigitronics.IDigi.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
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
import java.time.LocalDate;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
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
import com.idigitronics.IDigi.response.vo.IndividualBillingResponseVO;
import com.idigitronics.IDigi.response.vo.RazorPayResponseVO;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
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
		
	URL url = new URL("http://" + restcallvo.getGatewayIP() + ":" + restcallvo.getGatewayPort() +"/"+ restcallvo.getMiuID()+"/"+restcallvo.getUrlExtension());
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
	
	
//	@Scheduled(cron="0 30 0 2 * ? *") // scheduled for every month 2nd day at 0:30
	public void individualbillgeneration() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		float consumption = 0;
		int billAmount = 0;
		
		try {
			
			con = getConnection();
			
//			fetch reconnection charges from alertsettings after discussion
			
			pstmt = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cd.CustomerUniqueID, cmd.CustomerMeterID, cmd.MIUID, cmd.MeterType, cmd.TariffID, t.Tariff, t.FixedCharges FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN tariff AS t ON t.TariffID = cmd.TariffID WHERE cmd.PayType = 'Postpaid'");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				pstmt1 = con.prepareStatement("SELECT Reading, LogDate FROM balancelog WHERE CustomerMeterID = ? AND Logdate BETWEEN CONCAT((SELECT LAST_DAY(CURDATE() - INTERVAL 2 MONTH) + INTERVAL 1 DAY AS 'FIRST DAY OF PREVIOUS MONTH'), ' 00:00:00') AND CONCAT((SELECT DATE_SUB(LAST_DAY(NOW()),INTERVAL DAY(LAST_DAY(NOW()))- 1 DAY) AS 'FIRST DAY OF CURRENT MONTH'), ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1");
				pstmt1.setInt(1, rs.getInt("CustomerMeterID"));
				rs1 = pstmt1.executeQuery();
				
				if(rs1.next()) {
					
					pstmt2 = con.prepareStatement("SELECT Reading, LogDate FROM balancelog WHERE CustomerMeterID = ? AND Logdate BETWEEN CONCAT((SELECT LAST_DAY(CURDATE() - INTERVAL 2 MONTH) + INTERVAL 1 DAY AS 'FIRST DAY OF PREVIOUS MONTH'), ' 00:00:00') AND CONCAT((SELECT DATE_SUB(LAST_DAY(NOW()),INTERVAL DAY(LAST_DAY(NOW()))- 1 DAY) AS 'FIRST DAY OF CURRENT MONTH'), ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1");
					pstmt2.setInt(1, rs.getInt("CustomerMeterID"));
					rs2 = pstmt2.executeQuery();
					
					if(rs2.next()) {
						
						LocalDate currentdate = LocalDate.now();
						
						consumption = rs2.getFloat("Reading") - rs1.getFloat("Reading");
						billAmount = (int) (consumption * rs.getFloat("Tariff"));
						
						pstmt3 = con.prepareStatement("INSERT INTO billingdetails (CommunityID, BlockID, CustomerID, CustomerUniqueID, CustomerMeterID, MeterType, MIUID, PreviousReading, PresentReading, Consumption, TariffID, Tariff, BillAmount, FixedCharges, ReconnectionCharges, BillMonth, BillYear) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						pstmt3.setInt(1, rs.getInt("CommunityID"));
						pstmt3.setInt(2, rs.getInt("BlockID"));
						pstmt3.setInt(3, rs.getInt("CustomerID"));
						pstmt3.setString(4, rs.getString("CustomerUniqueID"));
						pstmt3.setInt(5, rs.getInt("CustomerMeterID"));
						pstmt3.setString(6, rs.getString("MeterType"));
						pstmt3.setString(7, rs.getString("MIUID"));
						pstmt3.setFloat(8, rs1.getFloat("Reading"));
						pstmt3.setFloat(9, rs2.getFloat("Reading"));
						pstmt3.setFloat(10, consumption);
						pstmt3.setInt(11, rs.getInt("TariffID"));
						pstmt3.setFloat(12, rs.getFloat("Tariff"));
						pstmt3.setInt(13, billAmount);
						pstmt3.setInt(14, rs.getInt("FixedCharges"));
						pstmt3.setInt(15, 0); // add reconnection charges after discussion
						pstmt3.setInt(16, currentdate.getMonthValue() - 1);
						pstmt3.setInt(17, currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear());// add year 
						
						if(pstmt3.executeUpdate() > 0) {
//					perform some actions after discussion
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
		
	}
	
//	@Scheduled(cron="0 30 4 2 * ? *") // scheduled for every month 2nd day at 4:30
	public void billgeneration() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		SMSRequestVO smsRequestVO = null;
		List<IndividualBillingResponseVO> individualBillsList = null;
		IndividualBillingResponseVO individualBillingResponseVO = null;
		
		try {
			
			con = getConnection();
			LocalDate currentdate = LocalDate.now();
			String drivename = "D:/Bills/" + (currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear()+"/"+(currentdate.getMonthValue() - 1));
			individualBillsList = new LinkedList<IndividualBillingResponseVO>();
			pstmt = con.prepareStatement("SELECT * FROM customerdetails JOIN alertsettings");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				int totalamount = 0;
				int totalConsumption = 0;
				smsRequestVO = new SMSRequestVO();
				
				pstmt1 = con.prepareStatement("SELECT * FROM billingdetails WHERE CustomerID = " + rs.getInt("CustomerID") + " AND BillMonth = "+ (currentdate.getMonthValue() - 1) + " AND BillYear = " + (currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear()));
				rs1 = pstmt1.executeQuery();
				while (rs1.next()) {
					individualBillingResponseVO = new IndividualBillingResponseVO();
					totalamount = rs1.getInt("BillAmount") + totalamount;
					totalConsumption = rs1.getInt("Consumption") + totalConsumption;
					individualBillingResponseVO.setBillingID(rs1.getLong("BillingID"));
					individualBillingResponseVO.setCustomerMeterID(rs1.getLong("CustomerMeterID"));
					individualBillingResponseVO.setMeterType(rs1.getString("MeterType"));
					individualBillingResponseVO.setMiuID(rs1.getString("MIUID"));
					individualBillingResponseVO.setPreviousReading(rs1.getFloat("PreviousReading"));
					individualBillingResponseVO.setPresentReading(rs1.getFloat("PresentReading"));
					individualBillingResponseVO.setConsumption(rs1.getInt("Consumption"));
					individualBillingResponseVO.setTariff(rs1.getFloat("Tariff"));
					individualBillingResponseVO.setBillAmount(rs1.getInt("BillAmount"));
					
					individualBillsList.add(individualBillingResponseVO);
				
				}
				
				pstmt2 = con.prepareStatement("INSERT INTO customerbillingdetails (CommunityID, BlockID, CustomerID, CustomerUniqueID, TotalAmount, TaxAmount, TotalConsumption, DueDate, BillMonth, BillYear, ModifiedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
				pstmt2.setInt(1, rs.getInt("CommunityID"));
				pstmt2.setInt(2, rs.getInt("BlockID"));
				pstmt2.setInt(3, rs.getInt("CustomerID"));
				pstmt2.setString(4, rs.getString("CustomerUniqueID"));
				pstmt2.setInt(5, totalamount);
				pstmt2.setInt(6, (totalamount * ((rs.getInt("GST") * 2)/100)));
				pstmt2.setInt(7, totalConsumption);
				pstmt2.setString(8, currentdate.plusDays(rs.getInt("DueDayCount")).toString());
				pstmt2.setInt(9, currentdate.getMonthValue() - 1);
				pstmt2.setInt(10, currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear());
				
				if(pstmt2.executeUpdate() > 0) {
					
					File directory = new File(drivename);
					if (!directory.exists()) {
						directory.mkdir();
					}

					PdfWriter writer = new PdfWriter(drivename + rs.getString("CustomerUniqueID") + ".pdf");
					PdfDocument pdfDocument = new PdfDocument(writer);
					pdfDocument.addNewPage();
					Document document = new Document(pdfDocument);
					Paragraph newLine = new Paragraph("\n");
					Paragraph head = new Paragraph("Bill");
					Paragraph disclaimer = new Paragraph(ExtraConstants.Disclaimer);
					Paragraph copyRight = new Paragraph("------------------------------------All  rights reserved by IDigitronics ® Hyderabad-----------------------------------");
					PdfFont font = new PdfFontFactory().createFont(FontConstants.TIMES_BOLD);

					// change according to the image directory

					URL idigiurl = new URL(ExtraConstants.IDIGIIMAGEURL);
					URL clienturl = new URL(ExtraConstants.CLIENTIMAGEURL);
					Image idigi = new Image(ImageDataFactory.create(idigiurl));
					Image client = new Image(ImageDataFactory.create(clienturl));

					float[] headingWidths = { 200F, 130F, 200F };

					Table headTable = new Table(headingWidths);

					Cell headtable1 = new Cell();
					headtable1.add(idigi);
					headtable1.setTextAlignment(TextAlignment.LEFT);

					Cell headtable2 = new Cell();
					headtable2.add(head.setFontSize(20));
					headtable2.setTextAlignment(TextAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
							.setBold().setUnderline().setFont(font);

					Cell headtable3 = new Cell();
					headtable3.add(client);
					headtable3.setTextAlignment(TextAlignment.RIGHT);

					headTable.addCell(headtable1.setBorder(Border.NO_BORDER));
					headTable.addCell(headtable2.setBorder(Border.NO_BORDER));
					headTable.addCell(headtable3.setBorder(Border.NO_BORDER));

					document.add(headTable);
					document.add(newLine);

					float[] headerWidths = { 200F, 180F, 170F };

					Table table1 = new Table(headerWidths);

					Cell table1cell1 = new Cell();
					table1cell1.add("Customer Name: " +rs.getString("FirstName") + " " + rs.getString("LastName"));
					table1cell1.setTextAlignment(TextAlignment.LEFT);

					Cell table1cell2 = new Cell();
					table1cell2.add("CAN Number: " + rs.getString("CustomerUniqueID"));
					table1cell2.setTextAlignment(TextAlignment.CENTER);
					
					long invoiceNumber = 0;
					PreparedStatement ps = con.prepareStatement("SELECT MAX(CustomerBillingID) AS InvoiceNumber FROM customerbillingdetails");
					ResultSet rs2 = ps.executeQuery();
					
					if(rs2.next()) {
						invoiceNumber = rs2.getInt("InvoiceNumber");
					}
					
					Cell table1cell3 = new Cell();
					table1cell3.add("Invoice No. : " + invoiceNumber);
					table1cell3.setTextAlignment(TextAlignment.RIGHT);

					table1.addCell(table1cell1.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell2.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell3.setBorder(Border.NO_BORDER));

					document.add(table1.setHorizontalAlignment(HorizontalAlignment.CENTER));
					document.add(newLine);

					float[] columnWidths = { 100F, 100F, 100F, 100F, 100F, 100F };
					
					Table datatablehead = new Table(columnWidths);
					
					Cell datatablecell1 = new Cell();
					datatablecell1.add("MIUID");
					datatablecell1.setTextAlignment(TextAlignment.CENTER);
					
					Cell datatablecell2 = new Cell();
					datatablecell2.add("Tariff");
					datatablecell2.setTextAlignment(TextAlignment.CENTER);
					
					Cell datatablecell3 = new Cell();
					datatablecell3.add("PreviousReading");
					datatablecell3.setTextAlignment(TextAlignment.CENTER);
					
					Cell datatablecell4 = new Cell();
					datatablecell4.add("PresentReading");
					datatablecell4.setTextAlignment(TextAlignment.CENTER);
					
					Cell datatablecell5 = new Cell();
					datatablecell5.add("Consumption");
					datatablecell5.setTextAlignment(TextAlignment.CENTER);
					
					Cell datatablecell6 = new Cell();
					datatablecell6.add("BillAmount");
					datatablecell6.setTextAlignment(TextAlignment.CENTER);
					
					datatablehead.addCell(datatablecell1);
					datatablehead.addCell(datatablecell2);
					datatablehead.addCell(datatablecell3);
					datatablehead.addCell(datatablecell4);
					datatablehead.addCell(datatablecell5);
					datatablehead.addCell(datatablecell6);

					Table datatable = new Table(columnWidths);
					
					for(int i = 0; i<=individualBillsList.size(); i++) {
						
						Cell datacell1 = new Cell();
						datacell1.add("" + individualBillsList.get(i).getMiuID());
						datacell1.setTextAlignment(TextAlignment.CENTER);
						datatablehead.addCell(datacell1);
						
						Cell datacell2 = new Cell();
						datacell2.add("" +individualBillsList.get(i).getTariff());
						datacell2.setTextAlignment(TextAlignment.CENTER);
						datatablehead.addCell(datacell2);
						
						Cell datacell3 = new Cell();
						datacell3.add("" +individualBillsList.get(i).getPreviousReading());
						datacell3.setTextAlignment(TextAlignment.CENTER);
						datatablehead.addCell(datacell3);
						
						Cell datacell4 = new Cell();
						datacell4.add("" +individualBillsList.get(i).getPresentReading());
						datacell4.setTextAlignment(TextAlignment.CENTER);
						datatablehead.addCell(datacell4);
						
						Cell datacell5 = new Cell();
						datacell5.add("" +individualBillsList.get(i).getConsumption());
						datacell5.setTextAlignment(TextAlignment.CENTER);
						datatablehead.addCell(datacell5);
						
						Cell datacell6 = new Cell();
						datacell6.add("" +individualBillsList.get(i).getBillAmount());
						datacell6.setTextAlignment(TextAlignment.CENTER);
						datatablehead.addCell(datacell6);
						
						datatablehead.startNewRow();
					}
					
					Cell billAmountCell = new Cell();
					billAmountCell.add("Bill Amount : ");
					billAmountCell.setTextAlignment(TextAlignment.CENTER);

					Cell totalAmount = new Cell();
					totalAmount.add(""+totalamount);
					totalAmount.setTextAlignment(TextAlignment.CENTER);
					
					Cell CGSTCell = new Cell();
					CGSTCell.add("CGST : ");
					CGSTCell.setTextAlignment(TextAlignment.CENTER);

					Cell CGSTAmount = new Cell();
					CGSTAmount.add(""+(totalamount * ((rs.getInt("GST"))/100)));
					CGSTAmount.setTextAlignment(TextAlignment.CENTER);

					Cell SGSTCell = new Cell();
					SGSTCell.add("CGST : ");
					SGSTCell.setTextAlignment(TextAlignment.CENTER);

					Cell SGSTAmount = new Cell();
					SGSTAmount.add(""+(totalamount * ((rs.getInt("GST"))/100)));
					SGSTAmount.setTextAlignment(TextAlignment.CENTER);
					
					Cell totalBillAmountCell = new Cell();
					totalBillAmountCell.add("Total Amount : ");
					totalBillAmountCell.setTextAlignment(TextAlignment.CENTER);

					Cell totalBillAmount = new Cell();
					totalBillAmount.add(""+(totalamount * ((rs.getInt("GST") * 2)/100)));
					totalBillAmount.setTextAlignment(TextAlignment.CENTER);
					
					Cell cell8 = new Cell();
					cell8.add("Date of Transaction: ");
					cell8.setTextAlignment(TextAlignment.CENTER);

					Cell transactionDate = new Cell();
					transactionDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
					transactionDate.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell8);
					datatable.addCell(transactionDate);
					datatable.startNewRow();

					document.add(datatable.setHorizontalAlignment(HorizontalAlignment.CENTER));
					document.add(disclaimer.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);
					document.add(newLine);

					document.add(copyRight.setHorizontalAlignment(HorizontalAlignment.CENTER).setFont(font));
					document.close();
					
				}
				
				smsRequestVO.setMessage("Dear "+ rs.getString("FirstName") + " " + rs.getString("LastName") + ", \n \n Your Bill of Amount" + (totalamount + (totalamount * ((rs.getInt("GST") * 2)/100))) + "/- for the month of " + ((currentdate.getMonthValue() - 1 == 0) ? "January," : (currentdate.getMonthValue() - 1 == 1) ? "February," : (currentdate.getMonthValue() - 1 == 2) ? "March," : (currentdate.getMonthValue() - 1 == 3) ? "April," : (currentdate.getMonthValue() - 1 == 4) ? "May," : (currentdate.getMonthValue() - 1 == 5) ? "June," : (currentdate.getMonthValue() - 1 == 6) ? "July," : (currentdate.getMonthValue() - 1 == 7) ? "August," : (currentdate.getMonthValue() - 1 == 8) ? "Septmeber," : (currentdate.getMonthValue() - 1 == 9) ? "October," : (currentdate.getMonthValue() - 1 == 10) ? "November," : (currentdate.getMonthValue() - 1 == 11) ? "December," :"" ) + ((currentdate.getMonthValue() - 1 == 0) ? currentdate.getYear() - 1 : currentdate.getYear()) +" has been generated. Kindly pay the bill before " + currentdate.plusDays(rs.getInt("DueDayCount")).toString() + " to avoid late fee charges. Thank You");
				smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
				
//				sendsms(smsRequestVO);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		
	}
	
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
//	@Scheduled(cron="0 0 7 ? * TUE,FRI") //every tuesday and friday at 7:00
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
			pstmt = con.prepareStatement("SELECT cmd.MIUID, b.Email, b.MobileNumber, cd.HouseNumber, cd.CustomerUniqueID, cmd.CustomerMeterID FROM customerdetails as cd LEFT JOIN customermeterdetails AS cmd on cd.CustomerID = cmd.CustomerID LEFT JOIN block AS b ON cd.BlockID = b.BlockID");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				pstmt1 = con.prepareStatement("SELECT ((SELECT (TIMESTAMPDIFF (MINUTE, (SELECT LogDate FROM displaybalancelog WHERE MIUID = ?), NOW()))) - (SELECT NoAMRInterval FROM alertsettings)) AS diff");
				pstmt1.setString(1, rs.getString("MIUID"));
				rs1 = pstmt1.executeQuery();
				if(rs1.next()) {
					if(rs1.getInt("diff") > 0) {
						
						mailRequestVO = new MailRequestVO();
						smsRequestVO = new SMSRequestVO();
						
						mailRequestVO.setSubject("No Communication from MIU ID: "+rs.getString("MIUID"));
						mailRequestVO.setToEmail(rs.getString("Email"));
						mailRequestVO.setMessage("Dear Admin, \n \n CRN/CAN/UAN: "+rs.getString("CustomerUniqueID")+ " is not up to date for more than 3 days.");
						
						smsRequestVO.setMessage("Dear Admin, \n \n CRN/CAN/UAN: "+rs.getString("CustomerUniqueID")+ " is not up to date for more than 3 days.");
						smsRequestVO.setToMobileNumber(rs.getString("MobileNumber"));
						
						sendmail(mailRequestVO);
//						sendsms(smsRequestVO);
						
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
		
		SimpleDateFormat IdigiFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat clientFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return clientFormat.format(IdigiFormat.parse(dateTime));
	}
}
