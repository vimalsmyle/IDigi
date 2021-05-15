/**
 * 
 */
package com.idigitronics.IDigi.dao;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;
import com.idigitronics.IDigi.constants.DataBaseConstants;
import com.idigitronics.IDigi.constants.ExtraConstants;
import com.idigitronics.IDigi.exceptions.BusinessException;
import com.idigitronics.IDigi.request.vo.CheckOutRequestVO;
import com.idigitronics.IDigi.request.vo.CommandGroupRequestVO;
import com.idigitronics.IDigi.request.vo.ConfigurationRequestVO;
import com.idigitronics.IDigi.request.vo.RazorPayOrderVO;
import com.idigitronics.IDigi.request.vo.RazorpayRequestVO;
import com.idigitronics.IDigi.request.vo.RestCallVO;
import com.idigitronics.IDigi.request.vo.TopUpRequestVO;
import com.idigitronics.IDigi.response.vo.CheckoutDetails;
import com.idigitronics.IDigi.response.vo.ConfigurationResponseVO;
import com.idigitronics.IDigi.response.vo.Notes;
import com.idigitronics.IDigi.response.vo.Prefill;
import com.idigitronics.IDigi.response.vo.RazorPayResponseVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;
import com.idigitronics.IDigi.response.vo.StatusResponseVO;
import com.idigitronics.IDigi.response.vo.TataResponseVO;
import com.idigitronics.IDigi.response.vo.Theme;
import com.idigitronics.IDigi.utils.Encoding;
import com.idigitronics.IDigi.utils.Signature;
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
public class AccountDAO {

	Gson gson = new Gson();

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL, DataBaseConstants.USER_NAME,
				DataBaseConstants.PASSWORD);
		return connection;
	}

	/* TopUp */

	public ResponseVO addtopup(TopUpRequestVO topUpRequestVO) throws SQLException, BusinessException {
		// TODO Auto-generated method stub

		Connection con = null;
		Gson gson = new Gson();
		ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
		ResponseVO responsevo = new ResponseVO();
		RazorPayOrderVO razorPayOrderVO = new RazorPayOrderVO();
		CheckoutDetails checkoutDetails = new CheckoutDetails();
		RazorpayRequestVO razorpayRequestVO = new RazorpayRequestVO();
		Prefill prefill = new Prefill();
		Notes notes = new Notes();
		Theme theme = new Theme();

		try {
			con = getConnection();

			// if (topUpRequestVO.getSource().equalsIgnoreCase("web")) {

			// change the query accordingly after billing and topup discussion
			PreparedStatement pstmt1 = con.prepareStatement(
					"SELECT tr.EmergencyCredit, tr.AlarmCredit, tr.FixedCharges, tr.TariffID, tr.Tariff, CONCAT(cd.FirstName, ' ', cd.LastName) AS CustomerName, cd.Email, cd.MobileNumber, cd.CustomerUniqueID, cd.HouseNumber FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd on cmd.customerID = cd.CustomerID LEFT JOIN tariff AS tr ON tr.TariffID = cd.TariffID WHERE cd.CustomerUniqueID = '"
							+ topUpRequestVO.getCustomerUniqueID() + "'");
			ResultSet rs1 = pstmt1.executeQuery();
			if (rs1.next()) {

				topUpRequestVO.setAlarmCredit(rs1.getFloat("AlarmCredit"));
				topUpRequestVO.setEmergencyCredit(rs1.getFloat("EmergencyCredit"));
				topUpRequestVO.setTariff(rs1.getFloat("Tariff"));

				LocalDateTime dateTime = LocalDateTime.now();

				PreparedStatement pstmt = con.prepareStatement(
						"SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 2 and CRNNumber = '"
								+ topUpRequestVO.getCustomerUniqueID() + "'" + "ORDER BY TransactionID DESC LIMIT 0,1");
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getInt("previoustopupmonth") != dateTime.getMonthValue()) {
						topUpRequestVO.setFixedCharges((rs1.getInt("FixedCharges")
								* (dateTime.getMonthValue() - rs.getInt("previoustopupmonth"))));
					}

				} else {
					topUpRequestVO.setFixedCharges(rs1.getInt("FixedCharges"));
				}

				PreparedStatement pstmt2 = con.prepareStatement(
						"SELECT al.ReconnectionCharges, dbl.Minutes FROM displaybalancelog AS dbl JOIN alertsettings AS al WHERE dbl.CRNNumber = '"
								+ topUpRequestVO.getCustomerUniqueID() + "'");
				ResultSet rs2 = pstmt2.executeQuery();

				if (rs2.next()) {
					if (rs2.getInt("Minutes") != 0) {
						topUpRequestVO.setReconnectionCharges(rs2.getInt("ReconnectionCharges"));
					}
				}

				if (topUpRequestVO.getAmount() <= topUpRequestVO.getFixedCharges()
						|| topUpRequestVO.getAmount() <= topUpRequestVO.getReconnectionCharges()) {
					throw new BusinessException(
							"RECHARGE AMOUNT MUST BE GREATER THAN FIXED CHARGES & RECONNECTION CHARGES");
				}

				if (topUpRequestVO.getModeOfPayment().equalsIgnoreCase("Online")) {

					// creating transactionID for payment process

					long transactionID = inserttopuponline(topUpRequestVO);

					if (transactionID != 0) {

						// creating order in razor pay

						razorPayOrderVO.setAmount(topUpRequestVO.getAmount() * 100);
						razorPayOrderVO.setCurrency("INR");
						razorPayOrderVO.setPayment_capture(1);

						razorpayRequestVO.setApi("orders");
						String rzpRestCallResponse = extramethodsdao.razorpaypost(razorPayOrderVO, razorpayRequestVO);

						RazorPayResponseVO razorPayResponseVO = gson.fromJson(rzpRestCallResponse,
								RazorPayResponseVO.class);

						topUpRequestVO.setRazorPayOrderID(razorPayResponseVO.getId());

						PreparedStatement pstmt4 = con.prepareStatement(
								"UPDATE topup SET RazorPayOrderID = ? WHERE TransactionID = " + transactionID);
						pstmt4.setString(1, topUpRequestVO.getRazorPayOrderID());
						if (pstmt4.executeUpdate() > 0) {

							checkoutDetails.setKey(ExtraConstants.RZPKeyID);
							checkoutDetails.setAmount(topUpRequestVO.getAmount() * 100);
							checkoutDetails.setCurrency(ExtraConstants.PaymentCurrency);
							checkoutDetails.setOrder_id(topUpRequestVO.getRazorPayOrderID());
							checkoutDetails.setButtonText(ExtraConstants.PaymentButtonText);
							checkoutDetails.setName(ExtraConstants.CompanyName);
							checkoutDetails.setDescription("Recharge of INR " + topUpRequestVO.getAmount()
									+ "/- for CRN: " + rs1.getString("CRNNumber") + ".");
							checkoutDetails.setImage(ExtraConstants.HANBITIMAGEURL);

							prefill.setName(rs1.getString("CustomerName"));
							prefill.setEmail(rs1.getString("Email"));
							prefill.setContact(rs1.getString("MobileNumber"));
							checkoutDetails.setPrefill(prefill);

							theme.setColor(ExtraConstants.PaymentThemeColor);
							checkoutDetails.setTheme(theme);

							notes.setAddress(rs1.getString("HouseNumber"));
							checkoutDetails.setTransactionID(transactionID);

							responsevo.setCheckoutDetails(checkoutDetails);

							responsevo.setPaymentMode("Online");
							responsevo.setResult("Success");
							responsevo.setMessage("Order Created Successfully. Proceed to CheckOut");
						}
					} else {

						responsevo.setResult("Failure");
						responsevo.setMessage("Order Creation Failed. Please Try After Sometime");
					}
				} else {
					topUpRequestVO.setPaymentStatus(1);
					responsevo.setPaymentMode("Cash");
					responsevo.setResult(sendPayLoadToTata(topUpRequestVO));
					responsevo.setMessage("Topup Request Submitted Successfully");
				}
			}
			/*
			 * } else { topUpRequestVO.setTransactionIDForTata(0); if
			 * (inserttopup(topUpRequestVO).equalsIgnoreCase("Success")) {
			 * responsevo.setResult("Success");
			 * responsevo.setMessage("Topup Request Inserted Successfully"); } else {
			 * responsevo.setResult("Failure");
			 * responsevo.setMessage("Topup Request Insertion Failed"); }
			 * 
			 * }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			// pstmt.close();
			// ps.close();
			con.close();
		}

		return responsevo;
	}

	public long inserttopuponline(TopUpRequestVO topUpRequestVO) {

		Connection con = null;
		PreparedStatement ps = null;
		long transactionID = 0;

		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement(
					"SELECT CommunityID, BlockID, CustomerID, MeterID, TariffID FROM customermeterdetails WHERE CRNNumber = '"
							+ topUpRequestVO.getCustomerUniqueID() + "'");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				ps = con.prepareStatement(
						"INSERT INTO topup (CommunityID, BlockID, CustomerID, MeterID, TariffID, Amount, FixedCharges, ReconnectionCharges, ModeOfPayment, Source, CreatedByID, CreatedByRoleID, CRNNumber, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");

				ps.setInt(1, rs.getInt("CommunityID"));
				ps.setInt(2, rs.getInt("BlockID"));
				ps.setInt(3, rs.getInt("CustomerID"));
				ps.setString(4, rs.getString("MeterID"));
				ps.setInt(5, rs.getInt("TariffID"));
				ps.setFloat(6, topUpRequestVO.getAmount());
				ps.setInt(7, topUpRequestVO.getFixedCharges());
				ps.setFloat(8, topUpRequestVO.getReconnectionCharges());
				ps.setString(9, topUpRequestVO.getModeOfPayment());
				ps.setString(10, topUpRequestVO.getSource());
				ps.setInt(11, topUpRequestVO.getTransactedByID());
				ps.setInt(12, topUpRequestVO.getTransactedByRoleID());
				ps.setString(13, topUpRequestVO.getCustomerUniqueID());

				if (ps.executeUpdate() > 0) {

					PreparedStatement pstmt1 = con.prepareStatement(
							"SELECT TransactionID FROM topup WHERE TataReferenceNumber = 0 AND CRNNumber = ? AND Source = ? AND ModeOfPayment = 'Online' AND STATUS = 0 AND PaymentStatus = 0 ORDER BY TransactionID DESC LIMIT 0,1");
					pstmt1.setString(1, topUpRequestVO.getCustomerUniqueID());
					pstmt1.setString(2, topUpRequestVO.getSource());
					ResultSet rs1 = pstmt1.executeQuery();
					if (rs1.next()) {
						transactionID = rs1.getLong("TransactionID");
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return transactionID;
	}

	public ResponseVO updatetopup(CheckOutRequestVO checkOutRequestVO) throws SQLException, ClassNotFoundException {

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responseVO = new ResponseVO();
		TopUpRequestVO topUpRequestVO = null;

		try {
			con = getConnection();

			String generated_signature = Signature.calculateRFC2104HMAC(
					checkOutRequestVO.getRazorpay_order_id() + "|" + checkOutRequestVO.getRazorpay_payment_id(),
					ExtraConstants.RZPKeySecret);

			if (generated_signature.equalsIgnoreCase(checkOutRequestVO.getRazorpay_signature())) {

				ps = con.prepareStatement(
						"UPDATE topup SET PaymentStatus = 1, RazorPayPaymentID = ?, RazorPaySignature = ? WHERE RazorPayOrderID = ? AND TransactionID = ?");

				ps.setString(1, checkOutRequestVO.getRazorpay_payment_id());
				ps.setString(2, checkOutRequestVO.getRazorpay_signature());
				ps.setString(3, checkOutRequestVO.getRazorpay_order_id());
				ps.setLong(4, checkOutRequestVO.getTransactionID());

				if (ps.executeUpdate() > 0) {

					PreparedStatement pstmt = con.prepareStatement(
							"SELECT t.TransactionID, t.MeterID, t.Amount, t.Source, t.FixedCharges, t.ReconnectionCharges, tr.Tariff, tr.AlarmCredit, tr.EmergencyCredit FROM topup AS t LEFT JOIN tariff AS tr ON tr.TariffID = t.TariffID WHERE t.RazorPayOrderID = '"
									+ checkOutRequestVO.getRazorpay_order_id() + "' AND t.TransactionID = "
									+ checkOutRequestVO.getTransactionID());

					ResultSet rs = pstmt.executeQuery();
					if (rs.next()) {

						topUpRequestVO = new TopUpRequestVO();

						topUpRequestVO.setTransactionID(checkOutRequestVO.getTransactionID());
						topUpRequestVO.setAmount(rs.getInt("Amount"));
						topUpRequestVO.setMeterID(rs.getString("MeterID"));
						topUpRequestVO.setFixedCharges(rs.getInt("FixedCharges"));
						topUpRequestVO.setReconnectionCharges(rs.getInt("ReconnectionCharges"));
						topUpRequestVO.setAlarmCredit(rs.getFloat("AlarmCredit"));
						topUpRequestVO.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
						topUpRequestVO.setTariff(rs.getFloat("Tariff"));
						topUpRequestVO.setModeOfPayment("Online");

						if (rs.getString("Source").equalsIgnoreCase("Mobile")) {
							responseVO.setResult("Success");
							responseVO.setMessage("Payment Captured Successfully");
						} else {
							if (sendPayLoadToTata(topUpRequestVO).equalsIgnoreCase("Success")) {
								responseVO.setResult("Success");
								responseVO.setMessage("Payment Captured & Topup Request Submitted Successfully");
							} else {
								responseVO.setResult("Failure");
								responseVO.setMessage(
										"Payment Captured but Topup Request Submission Failed. Deducted Amount will be Refunded in 14 Days");
							}
						}

					}

				}

			} else {
				ps = con.prepareStatement(
						"UPDATE topup SET PaymentStatus = 2, RazorPayPaymentID = ?, ErrorResponse = ? WHERE RazorPayOrderID = ? AND TransactionID = ?");

				ps.setString(1, checkOutRequestVO.getError().getMetadata().getPaymentId());
				ps.setString(2, checkOutRequestVO.getError().toString());
				ps.setString(3, checkOutRequestVO.getError().getMetadata().getOrderId());
				ps.setLong(4, checkOutRequestVO.getTransactionID());

				if (ps.executeUpdate() > 0) {
					responseVO.setResult("Success");
					responseVO.setMessage("Payment Failed");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return responseVO;
	}

	public String sendPayLoadToTata(TopUpRequestVO topUpRequestVO) throws SQLException {

		Random randomNumber = new Random();
		Gson gson = new Gson();
		RestCallVO restcallvo = new RestCallVO();
		ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
		String result = "Failure";
		Connection con = null;

		try {
			con = getConnection();

			String hexaAmount = Integer
					.toHexString(Float.floatToIntBits(topUpRequestVO.getAmount()
							- (topUpRequestVO.getFixedCharges() + topUpRequestVO.getReconnectionCharges())))
					.toUpperCase();

			String hexaAlarmCredit = Integer.toHexString(Float.floatToIntBits(topUpRequestVO.getAlarmCredit()))
					.toUpperCase();

			String hexaEmergencyCredit = Integer.toHexString(Float.floatToIntBits(topUpRequestVO.getEmergencyCredit()))
					.toUpperCase();

			String hexaTariff = Integer.toHexString(Float.floatToIntBits(topUpRequestVO.getTariff())).toUpperCase();

			String serialNumber = String.format("%04x", randomNumber.nextInt(65000));

			String dataFrame = "0A1800" + serialNumber + "020C0023" + hexaAmount + hexaTariff + hexaEmergencyCredit
					+ hexaAlarmCredit + "17";

//			restcallvo.setDataFrame(Encoding.getHexBase644(dataFrame));
			restcallvo.setMiuID(topUpRequestVO.getMeterID().toLowerCase());

			// sending payload to tata gateway

			int restcallresponse = extramethodsdao.postdata(restcallvo);

			if (topUpRequestVO.getModeOfPayment().equalsIgnoreCase("Cash")) {
				result = inserttopup(topUpRequestVO);
			} else {
				PreparedStatement pstmt = con.prepareStatement(
						"UPDATE topup SET TataReferenceNumber = ?, Status = ? WHERE TransactionID = ?");
				pstmt.setLong(1, topUpRequestVO.getTransactionIDForTata());
				pstmt.setInt(2, topUpRequestVO.getStatus());
				pstmt.setLong(3, topUpRequestVO.getTransactionID());

				if (pstmt.executeUpdate() > 0) {
					result = "Success";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public String inserttopup(TopUpRequestVO topUpRequestVO) {

		Connection con = null;
		PreparedStatement ps = null;
		String result = "Failure";

		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement(
					"SELECT CommunityID, BlockID, CustomerID, MeterID, TariffID FROM customermeterdetails WHERE CRNNumber = ?");
			pstmt.setString(1, topUpRequestVO.getCustomerUniqueID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				String sql = "INSERT INTO topup (TataReferenceNumber, CommunityID, BlockID, CustomerID, MeterID, TariffID, Amount, FixedCharges, ReconnectionCharges, Status, ModeOfPayment, PaymentStatus, Source, RazorPayOrderID, RazorPayPaymentID, RazorPaySignature, CreatedByID, CreatedByRoleID, CRNNumber, AcknowledgeDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
				ps = con.prepareStatement(sql);

				ps.setLong(1, topUpRequestVO.getTransactionIDForTata());
				ps.setInt(2, rs.getInt("CommunityID"));
				ps.setInt(3, rs.getInt("BlockID"));
				ps.setInt(4, rs.getInt("CustomerID"));
				ps.setString(5, rs.getString("MeterID"));
				ps.setInt(6, rs.getInt("TariffID"));
				ps.setFloat(7, topUpRequestVO.getAmount());
				ps.setInt(8, topUpRequestVO.getFixedCharges());
				ps.setFloat(9, topUpRequestVO.getReconnectionCharges());
				ps.setInt(10, topUpRequestVO.getStatus());
				ps.setString(11, topUpRequestVO.getModeOfPayment());
				ps.setInt(12, topUpRequestVO.getPaymentStatus());
				ps.setString(13, topUpRequestVO.getSource());
				ps.setString(14, topUpRequestVO.getRazorPayOrderID());
				ps.setString(15, topUpRequestVO.getRazorPayPaymentID());
				ps.setString(16, topUpRequestVO.getRazorPaySignature());
				ps.setInt(17, topUpRequestVO.getTransactedByID());
				ps.setInt(18, topUpRequestVO.getTransactedByRoleID());
				ps.setString(19, topUpRequestVO.getCustomerUniqueID());

				if (ps.executeUpdate() > 0) {
					result = "Success";

				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

	/* Status */

	public List<StatusResponseVO> getStatusdetails(int roleid, String id, int filterCid, int day) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		List<StatusResponseVO> statuslist = null;
		StatusResponseVO statusvo = null;
		try {
			con = getConnection();
			statuslist = new LinkedList<StatusResponseVO>();

			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cmd.FirstName, cmd.HouseNumber, cmd.CreatedByID, cmd.LastName, cmd.CRNNumber, t.MeterID, t.Amount, tr.AlarmCredit, tr.EmergencyCredit, t.Status, t.ModeOfPayment, t.PaymentStatus, t.RazorPayOrderID, t.RazorPayPaymentID, t.RazorPayRefundID, t.RazorPayRefundStatus, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n"
					+ "LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n"
					+ "LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber WHERE t.TransactionDate BETWEEN CONCAT(CURDATE() <day>, ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') AND t.PaymentStatus !=0 <change>";
			query = query.replaceAll("<day>", (day == 1) ? "" : "- INTERVAL 90 DAY");
			pstmt = con.prepareStatement(query.replaceAll("<change>",
					((roleid == 1 || roleid == 4) && (filterCid == -1)) ? "ORDER BY t.TransactionDate DESC"
							: ((roleid == 1 || roleid == 4) && (filterCid != -1))
									? " AND t.CommunityID = " + filterCid + " ORDER BY t.TransactionDate DESC"
									: (roleid == 2 || roleid == 5)
											? "AND t.BlockID = " + id + " ORDER BY t.TransactionDate DESC"
											: (roleid == 3)
													? "AND t.CRNNumber = '" + id + "' ORDER BY t.TransactionDate DESC"
													: ""));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				statusvo = new StatusResponseVO();
				statusvo.setTransactionID(rs.getInt("TransactionID"));
				statusvo.setCommunityName(rs.getString("CommunityName"));
				statusvo.setBlockName(rs.getString("BlockName"));
				statusvo.setFirstName(rs.getString("FirstName"));
				statusvo.setLastName(rs.getString("LastName"));
				statusvo.setHouseNumber(rs.getString("HouseNumber"));
				statusvo.setCRNNumber(rs.getString("CRNNumber"));
				statusvo.setMeterID(rs.getString("MeterID"));
				statusvo.setAmount(rs.getString("Amount"));
				statusvo.setModeOfPayment(rs.getString("ModeOfPayment"));
				statusvo.setRazorPayOrderID(rs.getString("ModeOfPayment").equalsIgnoreCase("Cash") ? "---"
						: rs.getString("RazorPayOrderID"));
				statusvo.setRazorPayPaymentID(rs.getString("ModeOfPayment").equalsIgnoreCase("Cash") ? "---"
						: rs.getString("RazorPayPaymentID"));
				statusvo.setRazorPayRefundID(
						(rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundID") : "---"));
				statusvo.setRazorPayRefundStatus(
						(rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundStatus") : "---"));
				statusvo.setPaymentStatus((rs.getInt("PaymentStatus") == 1 ? "PAID"
						: (rs.getInt("PaymentStatus") == 2) ? "FAILED"
								: (rs.getInt("PaymentStatus") == 3) ? "REFUND INITITATED" : "NOT PAID"));
				statusvo.setAlarmCredit(rs.getString("AlarmCredit"));
				statusvo.setEmergencyCredit(rs.getString("EmergencyCredit"));
				statusvo.setTransactionDate(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
				statusvo.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge"
						: (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" : "Failed");

				pstmt1 = con.prepareStatement(
						"SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "
								+ rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {
					statusvo.setTransactedByUserName(rs1.getString("UserName"));
					statusvo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
				}

				statuslist.add(statusvo);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();

			con.close();
		}
		return statuslist;
	}

	public ResponseVO deletestatus(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();

			pstmt = con.prepareStatement("DELETE FROM topup where TransactionID = " + transactionID);

			if (pstmt.executeUpdate() > 0) {
				responsevo.setResult("Success");
				responsevo.setMessage("Deleted Successfully");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");

		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO printreceipt(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResponseVO responsevo = new ResponseVO();
		String drivename = "C:/TopupReceipts/";

		try {
			con = getConnection();

			String query = "SELECT 	DISTINCT t.TransactionID, c.CommunityName, b.BlockName, cmd.FirstName, cmd.HouseNumber, cmd.CreatedByID, cmd.LastName, cmd.CRNNumber, t.MeterID, t.Amount, tr.AlarmCredit, tr.EmergencyCredit, t.Status, t.FixedCharges, t.ReconnectionCharges, t.ModeOfPayment, t.PaymentStatus, t.RazorPayOrderID, t.RazorPayPaymentID, t.TransactionDate, t.AcknowledgeDate FROM topup AS t \r\n"
					+ "LEFT JOIN community AS c ON t.CommunityID = c.CommunityID LEFT JOIN block AS b ON t.BlockID = b.BlockID LEFT JOIN tariff AS tr ON tr.TariffID = t.tariffID \r\n"
					+ "LEFT JOIN customermeterdetails AS cmd ON t.CRNNumber = cmd.CRNNumber WHERE t.TransactionID = "
					+ transactionID;

			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				pstmt1 = con.prepareStatement(
						"SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "
								+ rs.getInt("CreatedByID"));
				rs1 = pstmt1.executeQuery();
				if (rs1.next()) {

					File directory = new File(drivename);
					if (!directory.exists()) {
						directory.mkdir();
					}

					PdfWriter writer = new PdfWriter(drivename + transactionID + ".pdf");
					PdfDocument pdfDocument = new PdfDocument(writer);
					pdfDocument.addNewPage();
					Document document = new Document(pdfDocument);
					Paragraph newLine = new Paragraph("\n");
					Paragraph head = new Paragraph("Receipt");
					Paragraph disclaimer = new Paragraph(ExtraConstants.Disclaimer);
					Paragraph copyRight = new Paragraph(
							"------------------------------------All  rights reserved by HANBIT � Hyderabad-----------------------------------");
					PdfFont font = new PdfFontFactory().createFont(FontConstants.TIMES_BOLD);

					// change according to the image directory

					URL hanbiturl = new URL(ExtraConstants.HANBITIMAGEURL);
					URL clienturl = new URL(ExtraConstants.CLIENTIMAGEURL);
					Image hanbit = new Image(ImageDataFactory.create(hanbiturl));
					Image client = new Image(ImageDataFactory.create(clienturl));
					// Image technology = new
					// Image(ImageDataFactory.create("C:/TopupReceipts/lorawan.png"));
					// Image mode = new
					// Image(ImageDataFactory.create("C:/TopupReceipts/bluetooth.png"));

					float[] headingWidths = { 200F, 130F, 200F };

					Table headTable = new Table(headingWidths);

					Cell headtable1 = new Cell();
					headtable1.add(hanbit);
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
					table1cell1.add("MIU ID: " + rs.getString("MeterID"));
					table1cell1.setTextAlignment(TextAlignment.LEFT);

					Cell table1cell2 = new Cell();
					table1cell2.add("CRN Number: " + rs.getString("CRNNumber"));
					table1cell2.setTextAlignment(TextAlignment.CENTER);

					Cell table1cell3 = new Cell();
					table1cell3.add("Invoice No. : " + rs.getInt("TransactionID"));
					table1cell3.setTextAlignment(TextAlignment.RIGHT);

					table1.addCell(table1cell1.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell2.setBorder(Border.NO_BORDER));
					table1.addCell(table1cell3.setBorder(Border.NO_BORDER));

					document.add(table1.setHorizontalAlignment(HorizontalAlignment.CENTER));
					document.add(newLine);

					float[] columnWidths = { 400F, 150F };

					Table datatable = new Table(columnWidths);

					Cell cell1 = new Cell();
					cell1.add("Customer Name: ");
					cell1.setTextAlignment(TextAlignment.CENTER);

					Cell customerName = new Cell();
					customerName.add(rs.getString("FirstName") + " " + rs.getString("LastName"));
					customerName.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell1);
					datatable.addCell(customerName);
					datatable.startNewRow();

					Cell cell2 = new Cell();
					cell2.add("Amount: ");
					cell2.setTextAlignment(TextAlignment.CENTER);

					Cell Amount = new Cell();
					Amount.add(rs.getString("Amount"));
					Amount.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell2);
					datatable.addCell(Amount);
					datatable.startNewRow();

					Cell cell3 = new Cell();
					cell3.add("FixedCharges(if any): ");
					cell3.setTextAlignment(TextAlignment.CENTER);

					Cell fixedCharges = new Cell();
					fixedCharges.add(rs.getString("FixedCharges"));
					fixedCharges.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell3);
					datatable.addCell(fixedCharges);
					datatable.startNewRow();

					Cell cell4 = new Cell();
					cell4.add("Reconnection Charges(if any): ");
					cell4.setTextAlignment(TextAlignment.CENTER);

					Cell reconnectionCharges = new Cell();
					reconnectionCharges.add(rs.getString("ReconnectionCharges"));
					reconnectionCharges.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell4);
					datatable.addCell(reconnectionCharges);
					datatable.startNewRow();

					Cell cell5 = new Cell();
					cell5.add("Amount Updated to Device After Deductions (if any): ");
					cell5.setTextAlignment(TextAlignment.CENTER);

					Cell finalAmount = new Cell();
					finalAmount.add(Integer.toString(
							(rs.getInt("Amount") - (rs.getInt("FixedCharges") + rs.getInt("ReconnectionCharges")))));
					finalAmount.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell5);
					datatable.addCell(finalAmount);
					datatable.startNewRow();

					Cell cell6 = new Cell();
					cell6.add("Mode of Payment: ");
					cell6.setTextAlignment(TextAlignment.CENTER);

					Cell modeOfPayment = new Cell();
					modeOfPayment.add(rs.getString("ModeOfPayment"));
					modeOfPayment.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell6);
					datatable.addCell(modeOfPayment);
					datatable.startNewRow();

					Cell cell7 = new Cell();
					cell7.add("Transaction Initiated By: ");
					cell7.setTextAlignment(TextAlignment.CENTER);

					Cell transactedBy = new Cell();
					transactedBy.add(rs1.getString("UserName"));
					transactedBy.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell7);
					datatable.addCell(transactedBy);
					datatable.startNewRow();

					Cell cell8 = new Cell();
					cell8.add("Date of Transaction: ");
					cell8.setTextAlignment(TextAlignment.CENTER);

					Cell transactionDate = new Cell();
					transactionDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
					transactionDate.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell8);
					datatable.addCell(transactionDate);
					datatable.startNewRow();

					Cell cell9 = new Cell();
					cell9.add("Acknowledge Date: ");
					cell9.setTextAlignment(TextAlignment.CENTER);

					Cell acknowledgeDate = new Cell();
					acknowledgeDate.add(ExtraMethodsDAO.datetimeformatter(rs.getString("AcknowledgeDate")));
					acknowledgeDate.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell9);
					datatable.addCell(acknowledgeDate);
					datatable.startNewRow();

					Cell cell10 = new Cell();
					cell10.add("Order ID: ");
					cell10.setTextAlignment(TextAlignment.CENTER);

					System.out.println(rs.getString("RazorPayOrderID"));

					Cell OrderID = new Cell();
					OrderID.add(rs.getString("RazorPayOrderID") == null ? "---" : rs.getString("RazorPayOrderID"));
					OrderID.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell10);
					datatable.addCell(OrderID);
					datatable.startNewRow();

					Cell cell11 = new Cell();
					cell11.add("Payment ID: ");
					cell11.setTextAlignment(TextAlignment.CENTER);

					Cell PaymentID = new Cell();
					PaymentID
							.add(rs.getString("RazorPayPaymentID") == null ? "---" : rs.getString("RazorPayPaymentID"));
					PaymentID.setTextAlignment(TextAlignment.CENTER);

					datatable.addCell(cell11);
					datatable.addCell(PaymentID);
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

					responsevo.setResult("Success");
					responsevo.setLocation(drivename);
					responsevo.setFileName(transactionID + ".pdf");

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setResult("Failure");
			responsevo.setMessage("INTERNAL SERVER ERROR");
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return responsevo;

	}

	/* Configuration */

	public List<ConfigurationResponseVO> getConfigurationdetails(int roleid, String id, int filterCid)
			throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ConfigurationResponseVO> configurationdetailslist = null;
		try {
			con = getConnection();
			configurationdetailslist = new LinkedList<ConfigurationResponseVO>();

			String query = "SELECT cmd.TransactionID, cmd.CustomerUniqueID, cmd.MIUID, cmd.CommandType, cmd.CustomerMeterID, cmd.ModifiedDate, cmd.Status, cmd.Value FROM command AS cmd \r\n"
					+ "LEFT JOIN customerdetails AS cm ON cm.CustomerUniqueID = cmd.CustomerUniqueID\r\n"
					+ "LEFT JOIN community AS c ON cm.CommunityID = c.CommunityID\r\n"
					+ "LEFT JOIN block AS b ON cm.BlockID = b.blockID <change>";

			pstmt = con.prepareStatement(query.replaceAll("<change>",
					((roleid == 1 || roleid == 4) && (filterCid == -1)) ? " ORDER BY cmd.ModifiedDate DESC"
							: ((roleid == 1 || roleid == 4) && (filterCid != -1))
									? " WHERE cm.CommunityID = " + filterCid + " ORDER BY cmd.ModifiedDate DESC"
									: (roleid == 2 || roleid == 5)
											? "WHERE cm.BlockID = " + id + " ORDER BY cmd.ModifiedDate DESC"
											: (roleid == 3)
													? "WHERE cm.CRNNumber = '" + id + "' ORDER BY cmd.ModifiedDate DESC"
													: ""));

			rs = pstmt.executeQuery();
			ConfigurationResponseVO configurationvo = null;

			while (rs.next()) {
				configurationvo = new ConfigurationResponseVO();
				configurationvo.setMeterID(rs.getString("MeterID"));
				configurationvo.setCommandType((rs.getInt("CommandType") == 40) ? "Valve Open"
						: (rs.getInt("CommandType") == 0) ? "Valve Close"
								: (rs.getInt("CommandType") == 3) ? "Clear Meter"
										: (rs.getInt("CommandType") == 1) ? "Clear Tamper"
												: (rs.getInt("CommandType") == 5) ? "Set RTC"
														: (rs.getInt("CommandType") == 6) ? "Set Meter Index"
																: (rs.getInt("CommandType") == 10) ? "Set Tariff" : "");
				configurationvo.setValue(
						(rs.getInt("CommandType") == 6) || (rs.getInt("CommandType") == 10) ? rs.getString("Value")
								: "---");
				configurationvo.setModifiedDate(ExtraMethodsDAO.datetimeformatter(rs.getString("ModifiedDate")));
				configurationvo.setStatus((rs.getInt("Status") == 0) ? "Pending...waiting for acknowledge"
						: (rs.getInt("Status") == 1) ? "Pending" : (rs.getInt("Status") == 2) ? "Passed" : "Failed");
				configurationvo.setTransactionID(rs.getInt("TransactionID"));
				configurationdetailslist.add(configurationvo);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return configurationdetailslist;
	}

	public ResponseVO addconfiguration(ConfigurationRequestVO configurationvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		ResponseVO responsevo = new ResponseVO();
		CommandGroupRequestVO parameters = null;
		List<CommandGroupRequestVO> parameterslist;

		try {
			con = getConnection();
			
			ExtraMethodsDAO extramethodsdao = new ExtraMethodsDAO();
			RestCallVO restcallvo = new RestCallVO();
			
			ps = con.prepareStatement("INSERT INTO command (CustomerID, CustomerMeterID, MIUID, CustomerUniqueID, ModifiedDate) VALUES (?, ?, ?, ?, NOW())");
			ps.setInt(1, configurationvo.getCustomerID());
			ps.setInt(2, configurationvo.getCustomerMeterID());
			ps.setString(3, configurationvo.getMiuID());
			ps.setString(4, configurationvo.getCustomerUniqueID());
			
			if (ps.executeUpdate() > 0) {
			
			ps1 = con.prepareStatement("SELECT MAX(TransactionID) as TransactionID from command");
			ResultSet rs = ps1.executeQuery();
			
			if(rs.next()) {
				
				restcallvo.setMiuID(configurationvo.getMiuID());
				restcallvo.setTransaction_id(rs.getInt("TransactionID"));
				
				parameterslist = new LinkedList<CommandGroupRequestVO>();
				
				if(configurationvo.getCommands().size() > 1) {
					
					for(int i = 0; i < configurationvo.getCommands().size(); i++) {
						
						parameters = new CommandGroupRequestVO();
						
						ps2 = con.prepareStatement("INSERT INTO commanddetails (TransactionID, CommandType, Value, ModifiedDate) VALUES (?, ?, ?, NOW() )");
						ps2.setInt(1, rs.getInt("TransactionID"));
						ps2.setInt(2, configurationvo.getCommands().get(i).getParameter_id());
						ps2.setString(3, configurationvo.getCommands().get(i).getValue());
						
						ps2.executeUpdate();
						
						parameters.setParameter_id(configurationvo.getCommands().get(i).getParameter_id());
						parameters.setValue(configurationvo.getCommands().get(i).getValue());
						parameterslist.add(parameters);
						
						}
					restcallvo.setParameters(parameterslist);
					restcallvo.setUrlExtension("/group/set");
					
				} else {
					ps2 = con.prepareStatement("INSERT INTO commanddetails (TransactionID, CommandType, Value, ModifiedDate) VALUES (?, ?, ?, NOW())");
					ps2.setInt(1, rs.getInt("TransactionID"));
					ps2.setInt(2, configurationvo.getCommands().get(0).getParameter_id());
					ps2.setString(3, configurationvo.getCommands().get(0).getValue());
					
					if (ps2.executeUpdate() > 0) {
						restcallvo.setParameter_id(configurationvo.getCommands().get(0).getParameter_id());
						restcallvo.setValue(configurationvo.getCommands().get(0).getValue());
						restcallvo.setUrlExtension("/set");
						
						if (configurationvo.getCommands().get(0).getParameter_id() == 3) {

							PreparedStatement pstmt2 = con.prepareStatement("UPDATE customermeterdetails SET TariffID = ? Where CustomerUniqueID = ? AND CustomerMeterID = ?");
							pstmt2.setInt(1, Integer.parseInt(configurationvo.getCommands().get(0).getValue()));
							pstmt2.setString(2, configurationvo.getCustomerUniqueID());
							pstmt2.setInt(3, configurationvo.getCustomerMeterID());
							pstmt2.executeUpdate();

						}
					}
					
				}
				
				}
			}
			
			if(extramethodsdao.postdata(restcallvo) == 200) {
			responsevo.setResult("Success");
			responsevo.setMessage("Command Request Submitted Successfully");
			} else {
				PreparedStatement ps3 = con.prepareStatement("DELETE FROM commanddetails WHERE TransactionID = "+ restcallvo.getTransaction_id());
				if(ps3.executeUpdate() > 0) {
					
					PreparedStatement ps4 = con.prepareStatement("DELETE FROM command WHERE TransactionID = " + restcallvo.getTransaction_id());
					
					if(ps4.executeUpdate() > 0) {
						responsevo.setResult("Failure");
						responsevo.setMessage("Command Request failed");						
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			// ps.close();
			con.close();
		}

		return responsevo;

	}
	
	public ResponseVO updateconfiguration(ConfigurationRequestVO configurationvo, String miuID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement ps = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			ps = con.prepareStatement("UPDATE commanddetails SET ModifiedDate = NOW(), Status = "+ configurationvo.getCmd_status() +" WHERE TransactionID = "+ configurationvo.getTransactionID());
			
			if(ps.executeUpdate() > 0) {
				
					responsevo.setResult("Success");
					responsevo.setMessage("Status Updated Successfully");
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			ps.close();
			con.close();
		}

		return responsevo;
	}

	public ResponseVO deleteconfiguration(int transactionID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResponseVO responsevo = new ResponseVO();

		try {
			con = getConnection();
			
			PreparedStatement ps = con.prepareStatement("DELETE FROM commanddetails WHERE TransactionID = "+ transactionID);
			if(ps.executeUpdate() > 0) {
				
				pstmt = con.prepareStatement("DELETE FROM command WHERE TransactionID = " + transactionID);

				if (pstmt.executeUpdate() > 0) {
					responsevo.setResult("Success");
					responsevo.setMessage("Deleted Successfully");
				}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			responsevo.setMessage("INTERNAL SERVER ERROR");
			responsevo.setResult("Failure");
		} finally {
			pstmt.close();
			con.close();
		}

		return responsevo;
	}

	public boolean checkconfigstatus(String meterID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT cd.MIUID, cmd.Status FROM command as cd LEFT JOIN commanddetails AS cmd ON cd.TransactionID = cmd.TransactionID WHERE MIUID = ? order by TransactionID DESC LIMIT 0,1");
			pstmt.setString(1, meterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("Status").equals("0")) {
					result = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean checktopup(String meterID) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"SELECT transactionID, MeterID, STATUS FROM topup WHERE MeterID = ? AND STATUS IN (0,1) AND PaymentStatus = 1 AND Source = 'web' AND TataReferenceNumber !=0 ORDER BY TransactionID DESC LIMIT 0,1");
			pstmt.setString(1, meterID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

	public boolean validateamount(TopUpRequestVO topupvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean result = false;

		try {
			con = getConnection();
			pstmt = con.prepareStatement(
					"SELECT tr.EmergencyCredit, tr.Tariff, tr.TariffID, cmd.CRNNumber FROM customermeterdetails as cmd LEFT JOIN tariff AS tr ON tr.TariffID = cmd.TariffID WHERE cmd.CRNNumber = ?");
			pstmt.setString(1, topupvo.getCustomerUniqueID());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getFloat("Tariff") + "==>@@@==>" + rs.getFloat("EmergencyCredit"));
				if (topupvo.getAmount() < rs.getFloat("EmergencyCredit") || topupvo.getAmount() < rs.getFloat("Tariff"))

					result = true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return result;
	}

}
