/**
 * 
 */
package com.idigitronics.IDigi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.idigitronics.IDigi.constants.DataBaseConstants;
import com.idigitronics.IDigi.request.vo.AlarmRequestVO;
import com.idigitronics.IDigi.request.vo.FinancialReportsRequestVO;
import com.idigitronics.IDigi.request.vo.TopUpSummaryRequestVO;
import com.idigitronics.IDigi.request.vo.UserConsumptionRequestVO;
import com.idigitronics.IDigi.response.vo.AlarmsResponseVO;
import com.idigitronics.IDigi.response.vo.FinancialReportsResponseVO;
import com.idigitronics.IDigi.response.vo.TopUpSummaryResponseVO;
import com.idigitronics.IDigi.response.vo.UserConsumptionReportsResponseVO;

/**
 * @author K VimaL Kumar
 * 
 */
public class ReportsDAO {

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	/* Financial Reports */
	
	public List<FinancialReportsResponseVO> getFinancialReportsdetails(FinancialReportsRequestVO financialreportsrequestvo, int roleid, int id) throws SQLException { 

		 // TODO Auto-generated method stub
	 
	 Connection con = null; 
	 PreparedStatement pstmt = null; 
	 PreparedStatement pstmt1 = null;
	 PreparedStatement pstmt2 = null;
	 ResultSet rs = null;
	 ResultSet rs1 = null;
	 ResultSet rs2 = null;
	 
	 FinancialReportsResponseVO financialreportsresponsevo = null;
	 List<FinancialReportsResponseVO> financialreportsresponselist = null;
	 int totalAmountForSelectedPeriod = 0;
	 int totalUnitsForSelectedPeriod = 0;
	 
		try {
			con = getConnection();

			financialreportsresponselist = new ArrayList<FinancialReportsResponseVO>();
			
			String query = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cmd.MIUID FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN community AS C on c.communityID = cd.CommunityID LEFT JOIN block AS b on b.BlockID = cd.BlockID WHERE cmd.PayType = 'Prepaid' <change>";
			pstmt1 = con.prepareStatement(query.replaceAll("<change>", (financialreportsrequestvo.getBlockID() == 0 && (roleid==1 || roleid==4)) ? "AND cd.CommunityID = "+financialreportsrequestvo.getCommunityID() + " ORDER BY cd.CustomerID ASC" : (financialreportsrequestvo.getBlockID() != 0 && (roleid==1 || roleid==4)) ? "AND cd.BlockID = "+financialreportsrequestvo.getBlockID() + " ORDER BY cd.CustomerID ASC" :(roleid==2 || roleid==5) ? "AND cd.CommunityID = "+financialreportsrequestvo.getCommunityID() + " AND cd.BlockID = "+id+" ORDER BY cd.CustomerID ASC":""));
			rs1 = pstmt1.executeQuery();
			while(rs1.next()) {
				
				financialreportsresponsevo = new FinancialReportsResponseVO();
				financialreportsresponsevo.setCommunityName(rs1.getString("CommunityName"));
				financialreportsresponsevo.setBlockName(rs1.getString("BlockName"));
				financialreportsresponsevo.setHouseNumber(rs1.getString("HouseNumber"));
				financialreportsresponsevo.setMiuID(rs1.getString("MIUID"));
				
				String query1 = "SELECT SUM(Amount) AS Total FROM topup WHERE MIUID = ? AND YEAR(TransactionDate) = ? <change> AND STATUS = 0";
				query1 = query1.replaceAll("<change>", (financialreportsrequestvo.getMonth() > 0) ? "AND MONTH(TransactionDate) = "+financialreportsrequestvo.getMonth() : "");
				pstmt = con.prepareStatement(query1);
				pstmt.setString(1, rs1.getString("MIUID"));
				pstmt.setInt(2, financialreportsrequestvo.getYear());
				rs = pstmt.executeQuery();
				if (rs.next()) {
					financialreportsresponsevo.setTotalAmount(rs.getInt("Total"));
					totalAmountForSelectedPeriod = financialreportsresponsevo.getTotalAmount() + totalAmountForSelectedPeriod;
				}
				
				String query2 = "SELECT ABS((SELECT Reading FROM balancelog WHERE MeterID = ? AND YEAR(IoTTimeStamp) = ? <change> ORDER BY ReadingID DESC LIMIT 0,1)\r\n" + 
						"- (SELECT Reading FROM balancelog WHERE MeterID = ? AND YEAR(IoTTimeStamp) = ? <change> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
				query2 = query2.replaceAll("<change>", (financialreportsrequestvo.getMonth() > 0) ? "AND MONTH(IoTTimeStamp) = "+financialreportsrequestvo.getMonth() : "");
				pstmt2 = con.prepareStatement(query2);
				pstmt2.setString(1, rs1.getString("MeterID"));
				pstmt2.setInt(2, financialreportsrequestvo.getYear());
				pstmt2.setString(3, rs1.getString("MeterID"));
				pstmt2.setInt(4, financialreportsrequestvo.getYear());
				rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					financialreportsresponsevo.setTotalUnits(rs2.getInt("Units"));
					totalUnitsForSelectedPeriod = financialreportsresponsevo.getTotalUnits() + totalUnitsForSelectedPeriod;
				}
				financialreportsresponselist.add(financialreportsresponsevo);
			}
			financialreportsresponsevo.setTotalAmountForSelectedPeriod(totalAmountForSelectedPeriod);
			financialreportsresponsevo.setTotalUnitsForSelectedPeriod(totalUnitsForSelectedPeriod);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			pstmt1.close();
			rs.close();
			rs1.close();
			con.close();
		}
		
	 return financialreportsresponselist; 
	 
	}
	 

	/* User Consumption Reports */

	public List<UserConsumptionReportsResponseVO> getuserconsumptionreportsdetails(
			UserConsumptionRequestVO userconsumptionreportsrequestvo)
			throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<UserConsumptionReportsResponseVO> userconsumptionreportsresponselist = null;
		UserConsumptionReportsResponseVO userconsumptionreportsresponsevo = null;

		try {
			con = getConnection();
			userconsumptionreportsresponselist = new ArrayList<UserConsumptionReportsResponseVO>();
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cd.FirstName, cd.LastName, cd.HouseNumber, cd.CustomerUniqueID, bl.ReadingID, bl.EmergencyCredit, \r\n" + 
					"bl.MIUID, bl.Reading, bl.Balance, bl.BatteryVoltage, bl.Tariff, bl.ValveStatus, bl.DoorOpenTamper, bl.MagneticTamper, bl.LowBattery, bl.LowBalance, bl.LogDate\r\n" + 
					"FROM balancelog AS bl LEFT JOIN community AS c ON c.communityID = bl.CommunityID LEFT JOIN block AS b ON b.BlockID = bl.BlockID\r\n" + 
					"LEFT JOIN customerdetails AS cd ON cd.CustomerUniqueID = bl.CustomerUniqueID WHERE bl.CustomerUniqueID = ? AND bl.LogDate BETWEEN ? AND ? ";
			
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, userconsumptionreportsrequestvo.getCRNNumber());
				pstmt.setString(2, userconsumptionreportsrequestvo.getFromDate()+ " :00.001");
				pstmt.setString(3,userconsumptionreportsrequestvo.getToDate()+ " :59.999");

				rs = pstmt.executeQuery();
				while (rs.next()) {

					userconsumptionreportsresponsevo = new UserConsumptionReportsResponseVO();
					
					userconsumptionreportsresponsevo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
					userconsumptionreportsresponsevo.setMiuID(rs.getString("MIUID"));
					userconsumptionreportsresponsevo.setReading(rs.getFloat("Reading"));
					userconsumptionreportsresponsevo.setBalance(rs.getFloat("Balance"));
					userconsumptionreportsresponsevo.setBattery(rs.getInt("BatteryVoltage"));
					userconsumptionreportsresponsevo.setTariff(rs.getFloat("TariffAmount"));
					userconsumptionreportsresponsevo.setEmergencyCredit(rs.getFloat("Emergencycredit"));
					userconsumptionreportsresponsevo.setDateTime(ExtraMethodsDAO.datetimeformatter(rs.getString("LogDate")));
					
					userconsumptionreportsresponselist.add(userconsumptionreportsresponsevo);
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			 pstmt.close();
			 rs.close();
			con.close();
		}

		return userconsumptionreportsresponselist;
	}

	/* TopUp Summary */

	public List<TopUpSummaryResponseVO> gettopupsummarydetails(TopUpSummaryRequestVO topupsummaryrequestvo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		List<TopUpSummaryResponseVO> topupsummarydetails = null;
		TopUpSummaryResponseVO topupsummaryresponsevo = null;

		try {
			con = getConnection();
			topupsummarydetails = new LinkedList<TopUpSummaryResponseVO>();
			
			String query = "SELECT DISTINCT t.TransactionID, cd.FirstName, cd.LastName, cd.HouseNumber, t.MIUID, cd.CustomerUniqueID, t.Amount, t.Status, t.ModeOfPayment, t.PaymentStatus, t.RazorPayOrderID, t.RazorPayPaymentID, t.RazorPayRefundID, t.RazorPayRefundStatus, t.TransactionDate, t.CreatedByID FROM topup AS t \r\n" + 
					"LEFT JOIN customerdetails AS cd ON cd.CustomerUniqueID = t.CustomerUniqueID WHERE t.CommunityID = ? AND t.TransactionDate BETWEEN ? AND ? <change>";
			
				pstmt = con.prepareStatement(query.replaceAll("<change>", (topupsummaryrequestvo.getBlockID() > 0 && !topupsummaryrequestvo.getCustomerUniqueID().isEmpty()) ? " AND t.CustomerUniqueID = '"+topupsummaryrequestvo.getCustomerUniqueID()+"'" : (topupsummaryrequestvo.getCustomerUniqueID().isEmpty() && topupsummaryrequestvo.getBlockID() > 0) ? " AND t.BlockID = "+topupsummaryrequestvo.getBlockID() : ""));
				
				pstmt.setInt(1, topupsummaryrequestvo.getCommunityID());
				pstmt.setString(2, topupsummaryrequestvo.getFromDate()+ " 00:00:01.001");
				pstmt.setString(3,topupsummaryrequestvo.getToDate()+ " 23:59:59.999");

				rs = pstmt.executeQuery();
				while (rs.next()) {

					topupsummaryresponsevo = new TopUpSummaryResponseVO();
					
					topupsummaryresponsevo.setTransactionID(rs.getInt("TransactionID"));
					topupsummaryresponsevo.setFirstName(rs.getString("FirstName"));
					topupsummaryresponsevo.setLastName(rs.getString("LastName"));
					topupsummaryresponsevo.setHouseNumber(rs.getString("HouseNumber"));
					topupsummaryresponsevo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
					topupsummaryresponsevo.setMiuID(rs.getString("MIUID"));
					topupsummaryresponsevo.setRechargeAmount(rs.getInt("Amount"));
					topupsummaryresponsevo.setModeOfPayment(rs.getString("ModeOfPayment"));
					topupsummaryresponsevo.setRazorPayOrderID(rs.getString("RazorPayOrderID"));
					topupsummaryresponsevo.setRazorPayPaymentID(rs.getString("RazorPayPaymentID"));
					topupsummaryresponsevo.setRazorPayRefundID((rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundID") : "---"));
					topupsummaryresponsevo.setRazorPayRefundStatus((rs.getInt("PaymentStatus") == 3 ? rs.getString("RazorPayRefundStatus") : "---"));
					topupsummaryresponsevo.setPaymentStatus((rs.getInt("PaymentStatus") == 1 ? "PAID" : (rs.getInt("PaymentStatus") == 2) ? "FAILED" : (rs.getInt("PaymentStatus") == 3) ? "REFUND INITITATED" : "NOT PAID"));
					topupsummaryresponsevo.setStatus(rs.getInt("Status") == 0 ? "Passed"	: rs.getInt("Status") == 1 ? "Already Executed" : rs.getInt("Status") == 2 ? "Invalid Syntax"	: rs.getInt("Status") == 3 ? "Invalid Parameters" : rs.getInt("Status") == 4 ? "Value Cannot be Applied" : rs.getInt("Status") == 5 ? "Value Not in Range" : rs.getInt("Status") == 6 ? "Command Not Found"	: rs.getInt("Status") == 7	? "Device Not Found" : rs.getInt("Status") == 8 ? "Transaction Discarded" : rs.getInt("Status") == 9 ? "Transaction not Found" : rs.getInt("Status") == 10 ? "Pending": "Unknown Failure");
					topupsummaryresponsevo.setDateTime(ExtraMethodsDAO.datetimeformatter(rs.getString("TransactionDate")));
					
					pstmt1 = con.prepareStatement("SELECT user.ID, user.UserName, userrole.RoleDescription FROM USER LEFT JOIN userrole ON user.RoleID = userrole.RoleID WHERE user.ID = "+rs.getInt("CreatedByID"));
					rs1 = pstmt1.executeQuery();
					if(rs1.next()) {
						topupsummaryresponsevo.setTransactedByUserName(rs1.getString("UserName"));
						topupsummaryresponsevo.setTransactedByRoleDescription(rs1.getString("RoleDescription"));
					}
					
					topupsummarydetails.add(topupsummaryresponsevo);
				}
			

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}

		return topupsummarydetails;

	}

	/* Alarms */

	public List<AlarmsResponseVO> getAlarmdetails(int roleid, int id, int filterCid) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlarmsResponseVO> alarmsResponseList = null;
		int noAMRInterval = 0;
		
		try {

			con = getConnection();
			alarmsResponseList = new LinkedList<AlarmsResponseVO>();
			AlarmsResponseVO alarmsResponseVO = null;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, TimeOut FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}
			
			String query = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cmd.MIUID, cd.CustomerUniqueID FROM customerdetails AS cd LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerID = cd.CustomerID LEFT JOIN community AS C on c.communityID = cd.CommunityID LEFT JOIN block AS b on b.BlockID = cd.BlockID <change>";
			
			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && (filterCid == -1)) ? " ORDER BY cd.CustomerID ASC" : ((roleid == 1 || roleid == 4) && (filterCid != -1)) ? "WHERE cd.CommunityID = "+filterCid+ " ORDER BY cd.CustomerID ASC" : (roleid==2 || roleid==5) ? "WHERE cd.BlockID = "+id : " ORDER BY cd.CustomerID ASC"));
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PreparedStatement pstmt2 = con.prepareStatement("SELECT TIMESTAMPDIFF(MINUTE, (SELECT LogDate FROM balancelog WHERE MIUID = ? ORDER BY ReadingID DESC LIMIT 1,1), NOW()) AS Minutes");
				pstmt2.setString(1, rs.getString("MIUID"));
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()) {
					
					if(rs2.getInt("Minutes")>noAMRInterval) {
						alarmsResponseVO = new AlarmsResponseVO();
						
						alarmsResponseVO.setCommunityName(rs.getString("CommunityName"));
						alarmsResponseVO.setBlockName(rs.getString("BlockName"));
						alarmsResponseVO.setHouseNumber(rs.getString("HouseNumber"));
						alarmsResponseVO.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
						alarmsResponseVO.setMiuID(rs.getString("MIUID"));
						alarmsResponseVO.setDifference(rs2.getInt("Minutes"));
						PreparedStatement pstmt3 = con.prepareStatement("SELECT BatteryVoltage, DoorOpenTamper, MagneticTamper, LogDate, LowBattery, LowBalance FROM displaybalancelog WHERE MIUID = ?");
						pstmt3.setString(1, rs.getString("MIUID"));
						ResultSet rs3 = pstmt3.executeQuery();
						if(rs3.next()) {
							alarmsResponseVO.setDateTime(ExtraMethodsDAO.datetimeformatter(rs3.getString("LogDate")));
							alarmsResponseVO.setBatteryVoltage(rs3.getInt("LowBattery")==1 ? rs3.getString("BatteryVoltage") : "---");	
							alarmsResponseVO.setDoorOpenTamper(rs3.getInt("DoorOpenTamper")==1 ? "YES" : "NO");	
							alarmsResponseVO.setMagneticTamper(rs3.getInt("MagneticTamper")==1 ? "YES" : "NO");
							alarmsResponseVO.setLowBalance(rs3.getInt("LowBalance")==1 ? "YES" : "NO");
						}
						alarmsResponseList.add(alarmsResponseVO);
					}
					
				}
				
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return alarmsResponseList;
	}

	public List<AlarmsResponseVO> getAlarmreportsdetails(AlarmRequestVO alarmRequestVO) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AlarmsResponseVO> alarmsResponseList = null;
		
		try {

			con = getConnection();
			alarmsResponseList = new LinkedList<AlarmsResponseVO>();
			AlarmsResponseVO alarmsResponseVO = null;
//			bl.SolonideStatus = 1 OR 
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cd.FirstName, cd.LastName, cd.HouseNumber, cd.CustomerUniqueID, bl.ReadingID, bl.EmergencyCredit, \r\n" + 
					"bl.MIUID, bl.Reading, bl.Balance, bl.BatteryVoltage, bl.Tariff, bl.ValveStatus, bl.DoorOpenTamper, bl.MagneticTamper, bl.LowBattery, bl.LowBalance, bl.LogDate\r\n" + 
					"FROM balancelog AS bl LEFT JOIN community AS c ON c.communityID = bl.CommunityID LEFT JOIN block AS b ON b.BlockID = bl.BlockID\r\n" + 
					"LEFT JOIN customerdetails AS cd ON cd.CustomerUniqueID = bl.CustomerUniqueID WHERE bl.CustomerUniqueID = ? AND bl.LogDate BETWEEN ? AND ? AND (bl.DoorOpenTamper = 1 OR bl.MagneticTamper = 1 OR bl.LowBattery = 1 OR bl.LowBalance = 1)";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, alarmRequestVO.getCustomerUniqueID());
				pstmt.setString(2, alarmRequestVO.getFromDate() + " :00.001");
				pstmt.setString(3,alarmRequestVO.getToDate()+ " :59.999");

				rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
						alarmsResponseVO = new AlarmsResponseVO();
						
						alarmsResponseVO.setCommunityName(rs.getString("CommunityName"));
						alarmsResponseVO.setBlockName(rs.getString("BlockName"));
						alarmsResponseVO.setHouseNumber(rs.getString("HouseNumber"));
						alarmsResponseVO.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
						alarmsResponseVO.setMiuID(rs.getString("MIUID"));
						alarmsResponseVO.setBatteryVoltage(rs.getString("BatteryVoltage"));
						alarmsResponseVO.setDoorOpenTamper((rs.getInt("DoorOpenTamper") == 0) ? "NO" : "YES");
						alarmsResponseVO.setMagneticTamper((rs.getInt("MagneticTamper") == 0) ? "NO" : "YES");
//						alarmsResponseVO.setSolonideStatus(rs.getInt("SolonideStatus") == 1 ? "CLOSED" : "OPEN");
						alarmsResponseVO.setDateTime(ExtraMethodsDAO.datetimeformatter(rs.getString("LogDate")));
						alarmsResponseVO.setBatteryColor((rs.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
						alarmsResponseVO.setLowBalance((rs.getInt("LowBalance") == 1 ) ? "YES" : "NO");
						alarmsResponseList.add(alarmsResponseVO);
					}
					
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return alarmsResponseList;
	}

}
