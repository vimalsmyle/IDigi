/**
 * 
 */
package com.idigitronics.IDigi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.idigitronics.IDigi.constants.DataBaseConstants;
import com.idigitronics.IDigi.request.vo.LoginVO;
import com.idigitronics.IDigi.response.vo.BillDetailsResponseVO;
import com.idigitronics.IDigi.response.vo.TopupDetailsResponseVO;

/**
 * @author K VimaL Kumar
 *
 */
public class DropDownDAO {

	static LoginVO loginvo = new LoginVO();

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}
	
	public HashMap<Integer, String> getallcommunities(int roleid, String id) {
		// TODO Auto-generated method stub
		
		HashMap<Integer, String> communities = new HashMap<Integer, String>(); 
		Connection con = null;
		try {
			con = getConnection();
			

			String query = "SELECT c.CommunityID, c.CommunityName FROM community AS c <change> ";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid==2 || roleid==5) ? "LEFT JOIN block AS b ON b.CommunityID = c.CommunityID WHERE b.BlockID = "+id : (roleid == 3) ? "LEFT JOIN customerdetails AS cd ON cd.CommunityID = c.CommunityID WHERE cd.CustomerUniqueID = '"+id+"'": "ORDER BY c.CommunityID DESC"));
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				communities.put(rs.getInt("CommunityID"), rs.getString("CommunityName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return communities;
	}

	public HashMap<Integer, String> getallblocks(int communityID, int roleid, String id) {
		// TODO Auto-generated method stub

		HashMap<Integer, String> blocks = new HashMap<Integer, String>(); 
		Connection con = null;

		try {
			con = getConnection();
			String query = "SELECT BlockID, BlockName FROM block WHERE CommunityID=? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY BlockID ASC" : (roleid == 2 || roleid == 5) ? "AND BlockID = "+id+ " ORDER BY BlockID ASC" : (roleid == 3) ? "AND BlockID = (SELECT BlockID FROM customerdetails WHERE CustomerUniqueID = '"+id+"')":""));
			pstmt.setInt(1, communityID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				blocks.put(rs.getInt("BlockID"), rs.getString("BlockName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return blocks;
	}

	public HashMap<String, String> getallhouses(int blockID, int roleid, String id) {
		// TODO Auto-generated method stub
		HashMap<String, String> houses = new HashMap<String, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CustomerUniqueID, HouseNumber from customerdetails WHERE BlockID = ? <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 2 || roleid == 4 || roleid == 5) ? "ORDER BY CustomerID ASC" : (roleid == 3) ? " AND CustomerUniqueID = '"+id+"'" :""));
			pstmt.setInt(1, blockID);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				houses.put(rs.getString("CustomerUniqueID"), rs.getString("HouseNumber"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		return houses;
	}
	
	
	
	public TopupDetailsResponseVO gettopupdetails(String CustomerUniqueID, int customerMeterID) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TopupDetailsResponseVO topupdetailsresponsevo = new TopupDetailsResponseVO();
		
		try{
			con = getConnection();
			
			topupdetailsresponsevo.setReconnectionCharges(0);
			LocalDateTime dateTime = LocalDateTime.now();

					ps = con.prepareStatement("SELECT cmd.MIUID, cmd.CustomerMeterID, t.TariffID, t.TariffName, t.Tariff, t.EmergencyCredit, t.AlarmCredit, t.FixedCharges FROM customermeterdetails AS cmd LEFT JOIN tariff AS t ON t.TariffID = cmd.TariffID WHERE cmd.CustomerMeterID = ?");
			        ps.setInt(1, customerMeterID);
			        rs = ps.executeQuery();
			        if (rs.next()) {
			        	topupdetailsresponsevo.setMiuID(rs.getString("MIUID"));
			        	topupdetailsresponsevo.setAlarmCredit(rs.getFloat("AlarmCredit"));
			        	topupdetailsresponsevo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
			        	topupdetailsresponsevo.setTariffName(rs.getString("TariffName"));
			        	topupdetailsresponsevo.setTariff(rs.getFloat("Tariff"));
			        	topupdetailsresponsevo.setTariffID(rs.getInt("TariffID"));
			        	topupdetailsresponsevo.setFixedCharges(rs.getInt("FixedCharges"));
			        	topupdetailsresponsevo.setCustomerMeterID(rs.getInt("CustomerMeterID"));
			        	topupdetailsresponsevo.setNoOfMonths(1);
			                    
			                    pstmt = con.prepareStatement("SELECT dbl.LogDate, dbl.Balance, al.ReconnectionCharges, dbl.Minutes FROM displaybalanceLog AS dbl JOIN alertsettings AS al WHERE dbl.CustomerUniqueID = ? AND dbl.CustomerMeterID =" +customerMeterID);
			                    pstmt.setString(1, CustomerUniqueID);
			                    ResultSet rs1 = pstmt.executeQuery();
			                    if (rs1.next()) {
		                        	topupdetailsresponsevo.setCurrentBalance(rs1.getFloat("Balance"));
		                        	topupdetailsresponsevo.setReconnectionCharges(rs1.getInt("Minutes") != 0 ? rs1.getInt("ReconnectionCharges") : 0);
		                        	topupdetailsresponsevo.setNoOfMonths(0);
		                        	
		        					PreparedStatement pstmt2 = con.prepareStatement("SELECT MONTH(TransactionDate) AS previoustopupmonth from topup WHERE Status = 0 AND CustomerUniqueID = '"+CustomerUniqueID+"' AND CustomerMeterID = " + customerMeterID + " ORDER BY TransactionID DESC LIMIT 0,1");
		        					ResultSet rs2 = pstmt2.executeQuery();
		        					
		        					if(rs2.next()) {
		        						topupdetailsresponsevo.setNoOfMonths(dateTime.getMonthValue() - rs2.getInt("previoustopupmonth"));
		        						topupdetailsresponsevo.setFixedCharges(rs2.getInt("previoustopupmonth") != dateTime.getMonthValue() ? (rs.getInt("FixedCharges") * (dateTime.getMonthValue() - rs2.getInt("previoustopupmonth"))) : 0);
		        					}

			                    	} else {
			        					
			                        	topupdetailsresponsevo.setCurrentBalance(0);
			                        	topupdetailsresponsevo.setReconnectionCharges(0);
			                        }
			            }
					
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
//			pstmt.close();
	//		ps.close();
			rs.close();
			con.close();
		}
		
		return topupdetailsresponsevo;
	}
	
	public BillDetailsResponseVO getbilldetails(String customerUniqueID) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con = null;
		BillDetailsResponseVO billDetailsResponseVO = new BillDetailsResponseVO();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LocalDate currentdate = LocalDate.now();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("SELECT CustomerBillingID, TotalAmount, TaxAmount, DueDate, DAY(DueDate) as DueDay, BillMonth, BillYear, LogDate, LateFee, DueDayCount FROM customerbillingdetails JOIN alertsettings WHERE CustomerUniqueID = '" + customerUniqueID + "' AND BillMonth = "+ (currentdate.getMonthValue() - 1) + " AND BillYear = " + (currentdate.getMonthValue() == 1 ? currentdate.getYear() - 1 : currentdate.getYear()));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				billDetailsResponseVO.setCustomerBillingID(rs.getLong("CustomerBillingID"));
				billDetailsResponseVO.setTotalAmount(rs.getInt("TotalAmount") + rs.getInt("TaxAmount"));
				billDetailsResponseVO.setTotalConsumption(rs.getInt("TotalConsumption"));
				billDetailsResponseVO.setLateFee((currentdate.getDayOfMonth() - rs.getInt("DueDay")) >= 1 ? rs.getInt("LateFee") : 0);
				billDetailsResponseVO.setDueDate(rs.getString("DueDate"));
				billDetailsResponseVO.setBillMonth(rs.getInt("BillMonth") == 1 ? "January" : rs.getInt("BillMonth") == 2 ? "February" : rs.getInt("BillMonth") == 3 ? "March" : rs.getInt("BillMonth") == 4 ? "April" : rs.getInt("BillMonth") == 5 ? "May" : rs.getInt("BillMonth") == 6 ? "June" : rs.getInt("BillMonth") == 7 ? "July" : rs.getInt("BillMonth") == 8 ? "August" : rs.getInt("BillMonth") == 9 ? "September" : rs.getInt("BillMonth") == 10 ? "October" : rs.getInt("BillMonth") == 11 ? "November" : rs.getInt("BillMonth") == 12 ? "December" : "");
				billDetailsResponseVO.setBillYear(rs.getInt("BillYear"));
				billDetailsResponseVO.setBillingDate(rs.getString("LogDate"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		 finally {
			 	pstmt.close();
				rs.close();
				con.close();
			}
		
		return billDetailsResponseVO;
	}

	public HashMap<Integer, String> getalltariffs() throws SQLException {
		// TODO Auto-generated method stub
	
		HashMap<Integer, String> tariffs = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT TariffID, TariffName FROM tariff");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				tariffs.put(rs.getInt("TariffID"), rs.getString("TariffName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return tariffs;
	}

	public HashMap<Integer, String> getallgateways() throws SQLException {
		// TODO Auto-generated method stub
	
		HashMap<Integer, String> gateways = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("SELECT GatewayID, GatewayName FROM gateways");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				gateways.put(rs.getInt("GatewayID"), rs.getString("GatewayName"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return gateways;
	}

	public HashMap<Integer, String> getallcustomermeters(String customerUniqueID, String payType) throws SQLException {
		// TODO Auto-generated method stub
		
		HashMap<Integer, String> customermeters = new HashMap<Integer, String>();
		
		Connection con = null;
		try {
			con = getConnection();
			String query = "SELECT CustomerMeterID, MIUID FROM customermeterdetails WHERE CustomerUniqueID = '"+ customerUniqueID.trim() + "' <change>";
			PreparedStatement pstmt = con.prepareStatement(query.replaceAll("<change>", payType.equalsIgnoreCase("Prepaid") ? "AND PayType = 'Prepaid' " : "AND PayType = 'Postpaid' "));
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customermeters.put(rs.getInt("CustomerMeterID"), rs.getString("MIUID"));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			con.close();
			
		}
		return customermeters;
	}

}
