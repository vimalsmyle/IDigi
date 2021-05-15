/**
 * 
 */
package com.idigitronics.IDigi.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.idigitronics.IDigi.constants.DataBaseConstants;
import com.idigitronics.IDigi.constants.ExtraConstants;
import com.idigitronics.IDigi.request.vo.DashboardRequestVO;
import com.idigitronics.IDigi.request.vo.DataRequestVO;
import com.idigitronics.IDigi.request.vo.FilterVO;
import com.idigitronics.IDigi.request.vo.MailRequestVO;
import com.idigitronics.IDigi.request.vo.SMSRequestVO;
import com.idigitronics.IDigi.request.vo.TataRequestVO;
import com.idigitronics.IDigi.response.vo.DashboardResponseVO;
import com.idigitronics.IDigi.response.vo.GraphResponseVO;
import com.idigitronics.IDigi.response.vo.HomeResponseVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;


/**
 * @author k VimaL Kumar
 * 
 */
public class DashboardDAO {
	
	private static final Logger logger = Logger.getLogger(DashboardDAO.class);

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName(DataBaseConstants.DRIVER_CLASS);
		connection = DriverManager.getConnection(DataBaseConstants.DRIVER_URL,
				DataBaseConstants.USER_NAME, DataBaseConstants.PASSWORD);
		return connection;
	}

	public List<DashboardResponseVO> getDashboarddetails(int roleid, String id, int filter)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		int lowBatteryVoltage = 0;
		float perUnitValue = 0.0f;
		
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			int nonCommunicating = 0;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, LowBatteryVoltage, TimeOut, PerUnitValue FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				lowBatteryVoltage = rs1.getInt("LowBatteryVoltage");
				perUnitValue = rs1.getFloat("PerUnitValue");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage,dbl.LowBattery, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation, dbl.TamperTimeStamp, dbl.DoorOpenTimeStamp, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber WHERE 1=1 <change>";
		
			query = query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "" : (roleid == 2 || roleid == 5) ? "AND dbl.BlockID = "+id : (roleid == 3) ? "AND dbl.CRNNumber = '"+id+"'":"");
			
			StringBuilder stringBuilder = new StringBuilder(query);
			if(roleid !=3 && filter != 0) {
				
//				1 = valve open(active), 2 = valve close(inactive) 3 = communicating(live), 4 = non-communicating(non-live) 5 = low battery 6 = emergency credit
				
				stringBuilder.append((filter == 1 || filter == 2) ? " AND dbl.SolonideStatus = "+ (filter == 1 ? 0 : 1) : (filter == 3 || filter == 4) ? (filter == 3 ? " AND dbl.IotTimeStamp >= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " : " AND dbl.IotTimeStamp <= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " ) :  (filter == 5) ? " AND dbl.BatteryVoltage < "+ lowBatteryVoltage : (filter == 6) ? " AND dbl.Balance <= 0" : "");
				
			}
			stringBuilder.append(" ORDER BY dbl.IoTTimeStamp DESC");
			pstmt = con.prepareStatement(stringBuilder.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setMeterID(rs.getString("MeterID"));
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				dashboardvo.setCRNNumber(rs.getString("CRNNumber"));
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setConsumption((int) (dashboardvo.getReading() * perUnitValue));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");
				dashboardvo.setValveStatusColor((rs.getInt("SolonideStatus") == 0) ? "GREEN" : (rs.getInt("SolonideStatus") == 1) ? "RED" : "");
				dashboardvo.setBattery(rs.getInt("BatteryVoltage"));
				dashboardvo.setBatteryColor((rs.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
				
				// 0 = no tamper 1 = magnetic; 2 = door open
				
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" : (rs.getInt("TamperDetect") == 3) ? "MAG;"+"DOOR OPEN" : "NO");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : (rs.getInt("TamperDetect") == 3) ? rs.getString("TamperTimeStamp") +";"+ rs.getString("DoorOpenTimeStamp") : "---");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs.getString("IoTTimeStamp")));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());

				if(minutes > noAMRInterval) {
					nonCommunicating++;
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else if(minutes > 1440 && minutes < noAMRInterval) {
					dashboardvo.setDateColor("ORANGE");
					dashboardvo.setCommunicationStatus("YES");
				} else {
					dashboardvo.setDateColor("GREEN");
					dashboardvo.setCommunicationStatus("YES");
				}
				dashboardvo.setNonCommunicating(nonCommunicating);
				
				if(roleid==3) {
					PreparedStatement pstmt2 = con.prepareStatement("SELECT Amount, TransactionDate FROM topup WHERE CRNNumber = '"+rs.getString("CRNNumber")+"' AND STATUS BETWEEN 0 AND 2 ORDER BY TransactionID DESC LIMIT 0,1") ;
					ResultSet rs2 = pstmt2.executeQuery();
					if(rs2.next()) {
						dashboardvo.setLastTopupAmount(rs2.getInt("Amount"));
						dashboardvo.setLastRechargeDate(ExtraMethodsDAO.datetimeformatter(rs2.getString("TransactionDate")));
					}
				}
				dashboard_list.add(dashboardvo);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return dashboard_list;
	}
	
	public List<DashboardResponseVO> getFilterDashboarddetails(int roleid, String id, FilterVO filtervo) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		float perUnitValue = 0.0f;
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval, TimeOut, PerUnitValue FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
				perUnitValue = rs1.getFloat("PerUnitValue");
			}
			
			String query = "SELECT DISTINCT c.CommunityName, b.BlockName, cmd.FirstName,cmd.CRNNumber, cmd.LastName, cmd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
					"dbl.MeterID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.TariffAmount, dbl.SolonideStatus, dbl.TamperDetect, dbl.Vacation,  dbl.TamperTimeStamp, dbl.DoorOpenTimeStamp, dbl.IoTTimeStamp, dbl.LogDate\r\n" + 
					"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
					"LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber Where 1=1 <change> ";
			
			query = query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "" : (roleid == 2 || roleid == 5) ? " AND dbl.BlockID = "+id : (roleid == 3) ? "AND dbl.CRNNumber = '"+id+"'":"");
			StringBuilder stringBuilder = new StringBuilder(query);
			if(roleid !=3) {
				
				LocalDateTime dateTime = LocalDateTime.now();  
			    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
				
				if(!filtervo.getDateFrom().equalsIgnoreCase("null") || !filtervo.getDateTo().equalsIgnoreCase("null")) {
					stringBuilder.append(" AND dbl.IoTTimeStamp BETWEEN '" + filtervo.getDateFrom() + "' AND '" + (filtervo.getDateTo() != null ? filtervo.getDateTo()+"'" : "'"+dateTime.format(dateTimeFormat)+"'"));
				}
				if(filtervo.getReadingFrom() != 0 || filtervo.getReadingTo() != 0) {
					stringBuilder.append(" AND dbl.Reading BETWEEN " + (filtervo.getReadingFrom() != 0 ? filtervo.getReadingFrom() : 0) + " AND " + (filtervo.getReadingTo() != 0 ? filtervo.getReadingTo() : 9999999));
				}
				if(filtervo.getBatteryVoltageFrom() != 0 || filtervo.getBatteryVoltageFrom() != 0) {
					stringBuilder.append(" AND dbl.BatteryVoltage BETWEEN " + (filtervo.getBatteryVoltageFrom() != 0 ? (filtervo.getBatteryVoltageFrom()) : 0) + " AND " + (filtervo.getBatteryVoltageTo() != 0 ? (filtervo.getBatteryVoltageTo()) : 100));
				}
				if(filtervo.getTamperType() > 0) {
					stringBuilder.append(" AND dbl.TamperDetect = " + filtervo.getTamperType());
				}
					stringBuilder.append(" ORDER BY dbl.IoTTimeStamp DESC");
			}
			
			pstmt = con.prepareStatement(stringBuilder.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dashboardvo = new DashboardResponseVO();
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setMeterSerialNumber(rs.getString("MeterSerialNumber"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setMeterID(rs.getString("MeterID"));
				dashboardvo.setTariff((rs.getFloat("TariffAmount")));
				dashboardvo.setCRNNumber(rs.getString("CRNNumber"));
				dashboardvo.setReading(rs.getFloat("Reading"));
				dashboardvo.setConsumption((int) (dashboardvo.getReading() * perUnitValue));
				dashboardvo.setBalance(rs.getFloat("Balance"));
				dashboardvo.setEmergencyCredit(rs.getFloat("EmergencyCredit"));
				dashboardvo.setValveStatus((rs.getInt("SolonideStatus") == 0) ? "OPEN" : (rs.getInt("SolonideStatus") == 1) ? "CLOSED" : "");	
				dashboardvo.setValveStatusColor((rs.getInt("SolonideStatus") == 0) ? "GREEN" : (rs.getInt("SolonideStatus") == 1) ? "RED" : "");
//				dashboardvo.setBattery((int)((rs.getInt("BatteryVoltage"))*(100/3.5) > 100 ? 100 : (rs.getFloat("BatteryVoltage"))*(100/3.5)));
				dashboardvo.setBattery(rs.getInt("BatteryVoltage"));
				dashboardvo.setBatteryColor((rs.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
				dashboardvo.setTamperStatus((rs.getInt("TamperDetect") == 0) ? "NO" : (rs.getInt("TamperDetect") == 1) ? "MAG" : (rs.getInt("TamperDetect") == 2) ? "DOOR OPEN" : (rs.getInt("TamperDetect") == 3) ? "MAG;"+"DOOR OPEN" : "NO");
				dashboardvo.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : (rs.getInt("TamperDetect") == 3) ? rs.getString("TamperTimeStamp") +";"+ rs.getString("DoorOpenTimeStamp") : "---");
				dashboardvo.setTamperColor((rs.getInt("TamperDetect") == 0) ? "GREEN" : "RED");
				dashboardvo.setTamperTimeStamp((rs.getInt("TamperDetect") == 1) ? rs.getString("TamperTimeStamp") : (rs.getInt("TamperDetect") == 2) ? rs.getString("DoorOpenTimeStamp") : "---");
				dashboardvo.setVacationStatus(rs.getInt("Vacation") == 1 ? "YES" : "NO");
				dashboardvo.setVacationColor(rs.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
				dashboardvo.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs.getString("IoTTimeStamp")));
				
				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());
				
				if(minutes > noAMRInterval) {
					dashboardvo.setDateColor("RED");
					dashboardvo.setCommunicationStatus("NO");
				}else if(minutes > 1440 && minutes < noAMRInterval) {
					dashboardvo.setDateColor("ORANGE");
					dashboardvo.setCommunicationStatus("YES");
				} else {
					dashboardvo.setDateColor("GREEN");
					dashboardvo.setCommunicationStatus("YES");
				}

				dashboard_list.add(dashboardvo);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return dashboard_list;
	}
	
	public HomeResponseVO getHomeDashboardDetails(int roleid, String id)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		
		HomeResponseVO homeResponseVO = null;
		int noAMRInterval = 0;
		int nonLive = 0;
		int live = 0;
		int active = 0;
		int inActive = 0;
		int emergency = 0;
		int lowBattery = 0;
		int amr = 0;
		int consumption = 0;
		
		try {
			con = getConnection();
			homeResponseVO = new HomeResponseVO();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT NoAMRInterval FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}
			
			String query = "SELECT DISTINCT cmd.CRNNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, dbl.MeterID, dbl.Reading, dbl.Balance, dbl.LowBattery, dbl.SolonideStatus, dbl.TamperDetect, dbl.Minutes, dbl.IoTTimeStamp, dbl.LogDate FROM displaybalancelog AS dbl LEFT JOIN customermeterdetails AS cmd ON cmd.CRNNumber = dbl.CRNNumber <change>";

			pstmt = con.prepareStatement(query.replaceAll("<change>", (roleid == 1 || roleid == 4) ? "ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 2 || roleid == 5) ? "WHERE dbl.BlockID = "+id+ " ORDER BY dbl.IoTTimeStamp DESC" : (roleid == 3) ? "WHERE dbl.CRNNumber = '"+id+"'":""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				amr++;

				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("IoTTimeStamp")).getTime());
				
				if(minutes > noAMRInterval) { nonLive++; } else { live++; }
				if(rs.getInt("SolonideStatus") == 0) { active++; } else { inActive++; }
				if(rs.getInt("Balance") <= 0) { emergency++; }
				if(rs.getInt("LowBattery") == 1) { lowBattery++; }
				
			}
			
			homeResponseVO.setLive(live);
			homeResponseVO.setLivePercentage((live*100/amr));
			homeResponseVO.setNonLive(nonLive);
			homeResponseVO.setNonLivePercentage((nonLive*100/amr));
			homeResponseVO.setActive(active);
			homeResponseVO.setActivePercentage((active*100/amr));
			homeResponseVO.setInActive(inActive);
			homeResponseVO.setInActivePercentage((inActive*100/amr));
			homeResponseVO.setEmergency(emergency);
			homeResponseVO.setEmergencyPercentage((emergency*100/amr));
			homeResponseVO.setLowBattery(lowBattery);
			homeResponseVO.setLowBatteryPercentage((lowBattery*100/amr));
			homeResponseVO.setAmr(amr);
			homeResponseVO.setAmrPercentage(100);
			
			String query1 = "SELECT SUM(Amount) AS topup FROM topup WHERE Status = 2 AND PaymentStatus = 1 AND TransactionDate BETWEEN CONCAT(CURDATE(), ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') <change>";
			pstmt2 = con.prepareStatement(query1.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "AND BlockID = "+id :""));
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) { homeResponseVO.setTopup(rs2.getInt("topup")); } else { homeResponseVO.setTopup(0); }
			
			String query2 = "SELECT MeterID FROM customermeterdetails <change>";
			pstmt3 = con.prepareStatement(query2.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "WHERE BlockID = "+id + " ORDER BY CustomerID ASC" :""));
			rs3 = pstmt3.executeQuery();
			while(rs3.next()) {
				
				String query3 = "SELECT ABS((SELECT Reading FROM balancelog WHERE MeterID = ? AND IoTTImeStamp BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID DESC LIMIT 0,1)\r\n" + 
						"- (SELECT Reading FROM balancelog WHERE MeterID = ? AND IoTTImeStamp BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";

				pstmt4 = con.prepareStatement(query3);
				pstmt4.setString(1, rs3.getString("MeterID"));
				pstmt4.setString(2, rs3.getString("MeterID"));
				rs4 = pstmt4.executeQuery();
				if(rs4.next()) {
					consumption = rs4.getInt("Units") + consumption;
				}
			}
			
			homeResponseVO.setConsumption(consumption);
			
		}

		catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			pstmt.close();
			rs.close();
			con.close();
		}
		return homeResponseVO;
	}
	
	public GraphResponseVO getGraphDashboardDetails(int year, int month, String CRNNumber) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		GraphResponseVO graphResponseVO = new GraphResponseVO();
		List<String> xAxis;
		List<Integer> yAxis;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			yAxis = new LinkedList<Integer>();
			
			if(year == 0 && month == 0) {
				
				for(int i = 30; i>0; i-- ) {
					
					String query = "SELECT ((SELECT Reading FROM balancelog WHERE CRNNumber = ? AND IoTTImeStamp BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
										 "- (SELECT Reading FROM balancelog WHERE CRNNumber = ? AND IoTTImeStamp BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
					
					pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
					pstmt.setString(1, CRNNumber);
					pstmt.setString(2, CRNNumber);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						xAxis.add(rs.getString("consumptiondate"));
						yAxis.add(rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						
						}
				}
			} else if (year != 0 &&  month == 0) {
				
				for(int i = 1; i<=12; i++ ) {
					
					String query = "SELECT ((SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = <month> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
									      "-(SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = <month> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
					pstmt = con.prepareStatement(query.replaceAll("<month>", ""+i));
					pstmt.setString(1, CRNNumber);
					pstmt.setInt(2, year);
					pstmt.setString(3, CRNNumber);
					pstmt.setInt(4, year);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						xAxis.add(i==1 ? "JAN-"+year : i==2 ? "FEB-"+year : i==3 ? "MAR-"+year : i==4 ? "APR-"+year : i==5 ? "MAY-"+year : i==6 ? "JUN-"+year : i==7 ? "JUL-"+year : i==8 ? "AUG-"+year : i==9 ? "SEP-"+year : i==10 ? "OCT-"+year : i==11 ? "NOV-"+year : i==12 ? "DEC-"+year : "");
						yAxis.add(rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						
						}
				}
				
			} else if(year != 0 && month != 0) {
				
				int j = (month == 2 ? 28 : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30);
				
				for(int i = 1; i <= j ; i++) {
					
					String query = "SELECT ((SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = ? AND DAY(IotTimeStamp) = <day> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
										 "- (SELECT Reading FROM balancelog WHERE CRNNumber = ? AND YEAR(IotTimeStamp) = ? AND MONTH(IotTimeStamp) = ? AND DAY(IotTimeStamp) = <day> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
					pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
					pstmt.setString(1, CRNNumber);
					pstmt.setInt(2, year);
					pstmt.setInt(3, month);
					pstmt.setString(4, CRNNumber);
					pstmt.setInt(5, year);
					pstmt.setInt(6, month);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						
						xAxis.add(Integer.toString(i)+"-"+month+"-"+year);
						yAxis.add(rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						
						}
					}
				}
			
			graphResponseVO.setXAxis(xAxis);
			graphResponseVO.setYAxis(yAxis);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return graphResponseVO;
	}

	public ResponseVO postDashboarddetails(DataRequestVO dataRequestVO, String miuID) throws SQLException {
		// TODO Auto-generated method stub

		ResponseVO responsevo = new ResponseVO();
		
		dataRequestVO.setSource("gateway");
		
		try {
			
			logger.debug("Device ID: "+miuID);
			
				if (dataRequestVO.getType() > 0) {
					
					logger.debug("Battery Voltage: "+dataRequestVO.getBat_volt());
					
					responsevo.setResult(insertdashboard(dataRequestVO, miuID));
				
					
				} else {
					responsevo.setResult("Invalid Meter Type");
				}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responsevo;
	}

	public String insertdashboard (DataRequestVO dataRequestVO, String miuID) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		String result = "Failure";
		String alertMessage = "";
		
		try {
			con = getConnection();
			
				PreparedStatement pstmt2 = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cmd.CustomerMeterID, cmd.TariffID, t.Tariff, cmd.MeterSerialNumber, cd.CustomerUniqueID from customerdetails as cd LEFT JOIN customermeterdetails as cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN tariff as t on t.TariffID = cmd.TariffID WHERE cmd.MIUID = ?");
				pstmt2.setString(1, miuID);
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					
					pstmt = con.prepareStatement("INSERT INTO balancelog (MIUID, CommunityID, BlockID, CustomerID, CustomerMeterID, MeterSerialNumber, CustomerUniqueID, MeterType, SyncTime, SyncInterval, PayType, BatteryVoltage, TariffID, Tariff, ValveConfiguration, ValveStatus, Balance, EmergencyCredit, Minutes, Reading, DoorOpenTamper, MagneticTamper, Vacation, RTCFault, LowBattery, LowBalance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					pstmt.setString(1, miuID);
					pstmt.setInt(2, rs.getInt("CommunityID"));
					pstmt.setInt(3, rs.getInt("BlockID"));
					pstmt.setInt(4, rs.getInt("CustomerID"));
					pstmt.setInt(5, rs.getInt("CustomerMeterID"));
					pstmt.setString(6, rs.getString("MeterSerialNumber"));
					pstmt.setString(7, rs.getString("CustomerUniqueID"));
					pstmt.setInt(8, dataRequestVO.getType());
					pstmt.setString(9, dataRequestVO.getSync_time());
					pstmt.setInt(10, dataRequestVO.getSync_interval());
					pstmt.setInt(11, dataRequestVO.getPre_post_paid());
					pstmt.setFloat(12, dataRequestVO.getBat_volt());
					pstmt.setInt(13, rs.getInt("TariffID"));
					pstmt.setFloat(14, dataRequestVO.getTariff());
					pstmt.setInt(15, dataRequestVO.getValve_configuration());
					pstmt.setInt(16, dataRequestVO.getValve_live_status());
					pstmt.setFloat(17, dataRequestVO.getCredit());
					pstmt.setFloat(18, dataRequestVO.getEmergency_credit());
					pstmt.setInt(19, dataRequestVO.getMin_elapsed_after_valve_trip());
					pstmt.setFloat(20, dataRequestVO.getReading());
					pstmt.setInt(21, dataRequestVO.getStatus().getDoor_open());
					pstmt.setInt(22, dataRequestVO.getStatus().getMagnetic());
					pstmt.setInt(23, dataRequestVO.getStatus().getSchedule_disconnect());
					pstmt.setInt(24, dataRequestVO.getStatus().getRtc_fault());
					pstmt.setInt(25, dataRequestVO.getStatus().getLow_bat());
					pstmt.setInt(26, dataRequestVO.getStatus().getLow_bal());
					
					if (pstmt.executeUpdate() > 0) {
						
						PreparedStatement pstmt4 = con.prepareStatement("SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MIUID = ?");
						pstmt4.setString(1, miuID);
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MIUID = ? ");
							pstmt3.setString(1, miuID);
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MIUID = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, CustomerMeterID = ?, MeterSerialNumber = ?, CustomerUniqueID = ?, MeterType = ?, SyncTime = ?, SyncInterval = ?, PayType = ?, BatteryVoltage = ?, TariffID = ?, Tariff = ?, ValveConfiguration = ?,  ValveStatus = ?, Balance = ?, EmergencyCredit = ?, Minutes = ?, Reading = ?, DoorOpenTamper = ?, MagneticTamper = ?, Vacation = ?, RTCFault = ?, LowBattery = ?, LowBalance = ? WHERE CustomerMeterID = ? ");
								pstmt1.setInt(1, rs2.getInt("ReadingID"));
								pstmt1.setString(2, miuID);
								pstmt1.setInt(3, rs.getInt("CommunityID"));
								pstmt1.setInt(4, rs.getInt("BlockID"));
								pstmt1.setInt(5, rs.getInt("CustomerID"));
								pstmt1.setInt(6, rs.getInt("CustomerMeterID"));
								pstmt1.setString(7, rs.getString("MeterSerialNumber"));
								pstmt1.setString(8, rs.getString("CustomerUniqueID"));
								pstmt1.setInt(9, dataRequestVO.getType());
								pstmt1.setString(10, dataRequestVO.getSync_time());
								pstmt1.setInt(11, dataRequestVO.getSync_interval());
								pstmt1.setInt(12, dataRequestVO.getPre_post_paid());
								pstmt1.setFloat(13, dataRequestVO.getBat_volt());
								pstmt1.setInt(14, rs.getInt("TariffID"));
								pstmt1.setFloat(15, dataRequestVO.getTariff());
								pstmt1.setInt(16, dataRequestVO.getValve_configuration());
								pstmt1.setInt(17, dataRequestVO.getValve_live_status());
								pstmt1.setFloat(18, dataRequestVO.getCredit());
								pstmt1.setFloat(19, dataRequestVO.getEmergency_credit());
								pstmt1.setInt(20, dataRequestVO.getMin_elapsed_after_valve_trip());
								pstmt1.setFloat(21, dataRequestVO.getReading());
								pstmt1.setInt(22, dataRequestVO.getStatus().getDoor_open());
								pstmt1.setInt(23, dataRequestVO.getStatus().getMagnetic());
								pstmt1.setInt(24, dataRequestVO.getStatus().getSchedule_disconnect());
								pstmt1.setInt(25, dataRequestVO.getStatus().getRtc_fault());
								pstmt1.setInt(26, dataRequestVO.getStatus().getLow_bat());
								pstmt1.setInt(27, dataRequestVO.getStatus().getLow_bal());
								pstmt1.setInt(28, rs.getInt("CustomerMeterID"));
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MIUID, CommunityID, BlockID, CustomerID, CustomerMeterID, MeterSerialNumber, CustomerUniqueID, MeterType, SyncTime, SyncInterval, PayType, BatteryVoltage, TariffID, Tariff, ValveConfiguration, ValveStatus, Balance, EmergencyCredit, Minutes, Reading, DoorOpenTamper, MagneticTamper, Vacation, RTCFault, LowBattery, LowBalance) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
									pstmt1.setInt(1, rs2.getInt("ReadingID"));
									pstmt1.setString(2, miuID);
									pstmt1.setInt(3, rs.getInt("CommunityID"));
									pstmt1.setInt(4, rs.getInt("BlockID"));
									pstmt1.setInt(5, rs.getInt("CustomerID"));
									pstmt1.setInt(6, rs.getInt("CustomerMeterID"));
									pstmt1.setString(7, rs.getString("MeterSerialNumber"));
									pstmt1.setString(8, rs.getString("CustomerUniqueID"));
									pstmt1.setInt(9, dataRequestVO.getType());
									pstmt1.setString(10, dataRequestVO.getSync_time());
									pstmt1.setInt(11, dataRequestVO.getSync_interval());
									pstmt1.setInt(12, dataRequestVO.getPre_post_paid());
									pstmt1.setFloat(13, dataRequestVO.getBat_volt());
									pstmt1.setInt(14, rs.getInt("TariffID"));
									pstmt1.setFloat(15, dataRequestVO.getTariff());
									pstmt1.setInt(16, dataRequestVO.getValve_configuration());
									pstmt1.setInt(17, dataRequestVO.getValve_live_status());
									pstmt1.setFloat(18, dataRequestVO.getCredit());
									pstmt1.setFloat(19, dataRequestVO.getEmergency_credit());
									pstmt1.setInt(20, dataRequestVO.getMin_elapsed_after_valve_trip());
									pstmt1.setFloat(21, dataRequestVO.getReading());
									pstmt1.setInt(22, dataRequestVO.getStatus().getDoor_open());
									pstmt1.setInt(23, dataRequestVO.getStatus().getMagnetic());
									pstmt1.setInt(24, dataRequestVO.getStatus().getSchedule_disconnect());
									pstmt1.setInt(25, dataRequestVO.getStatus().getRtc_fault());
									pstmt1.setInt(26, dataRequestVO.getStatus().getLow_bat());
									pstmt1.setInt(27, dataRequestVO.getStatus().getLow_bal());
							}
							
						}
						
						pstmt1.executeUpdate();{
						result = "Success";
						}
						
						String query = "SELECT LogDate, MIUID, DoorOpenTamper, MagneticTamper, LowBattery, LowBalance FROM balancelog WHERE CustomerMeterID = ? AND CustomerUniqueID = ? AND <condition> AND LogDate BETWEEN (CONCAT(CURDATE(), ' 00:00:00')) AND NOW() ORDER BY LogDate DESC";
						
						if (dataRequestVO.getStatus().getLow_bat() == 1) {
							
							ps = con.prepareStatement(query.replaceAll("<condition>", dataRequestVO.getStatus().getLow_bat() == 1 ? "LowBattery = 1" : ""));
							ps.setInt(1, rs.getInt("CustomerMeterID"));
							ps.setString(2, rs.getString("CustomerUniqueID"));
							resultSet = ps.executeQuery(); 

							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "The Battery in MIU: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is low.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
//								sendalertmail("Low Battery Alert!!!", alertMessage, resultSet.getString("MIUID"));
//								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
							}
						} 
						
						if (dataRequestVO.getStatus().getDoor_open() == 1 || dataRequestVO.getStatus().getMagnetic() == 1) {
							ps = con.prepareStatement(query.replaceAll("<condition>", dataRequestVO.getStatus().getDoor_open() == 1 ? "DoorOpenTamper = 1" : dataRequestVO.getStatus().getMagnetic() == 1 ? "MagneticTamper = 1" : ""));
							ps.setInt(1, rs.getInt("CustomerMeterID"));
							ps.setString(2, rs.getString("CustomerUniqueID"));
							resultSet = ps.executeQuery(); 
							
							int size =0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "There is a <tamper> Tamper at <timestamp>, in MIU: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block>.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								alertMessage = alertMessage.replaceAll("<tamper>", dataRequestVO.getStatus().getDoor_open() == 1 ? "Door Open Tamper" : dataRequestVO.getStatus().getMagnetic() == 1 ? "Magnetic Tamper" : "");
								alertMessage = alertMessage.replaceAll("<timestamp>", resultSet.getString("LogDate"));
//								sendalertmail("Tamper Alert!!!", alertMessage, resultSet.getString("MIUID"));
//								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
							}
						}

						if(dataRequestVO.getStatus().getLow_bal() == 1) {
							
							ps = con.prepareStatement(query.replaceAll("<condition>", dataRequestVO.getStatus().getLow_bal() == 1 ? "LowBalance = 1" : ""));
							ps.setInt(1, rs.getInt("CustomerMeterID"));
							ps.setString(2, rs.getString("CustomerUniqueID"));
							resultSet = ps.executeQuery(); 

							int size = 0;  
							if (resultSet != null)   
							{  
								resultSet.beforeFirst();  
								resultSet.last();  
							size = resultSet.getRow();
							}
							
							if(size == 1) {
								alertMessage = "Balance in your MIU: <MIU> with CRN/CAN/UAN: <CRN> is low. Please Recharge.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
//								sendalertmail("Low Balance Alert!!!", alertMessage, resultSet.getString("MIUID"));
//								sendalertsms(1, alertMessage, resultSet.getString("MIUID"));								
							}
							
						}
						
					}
					
				}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String sendalertsms(int i, String message, String miuID) {
		// TODO Auto-generated method stub
		ExtraMethodsDAO extraMethodsDao = new ExtraMethodsDAO();
		SMSRequestVO smsRequestVO = new SMSRequestVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cd.MobileNumber AS customerMobileNumber, b.MobileNumber AS adminMobileNumber, cd.HouseNumber, cd.CustomerUniqueID, b.BlockName as BlockName, c.CommunityName as CommunityName FROM customerdetails as cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN block AS b ON b.BlockID = cd.BlockID LEFT JOIN community AS c ON c.CommunityID = cd.CommunityID WHERE cmd.MIUID = '"+ miuID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				smsRequestVO.setToMobileNumber(i == 1 ? rs.getString("customerMobileNumber") : rs.getString("adminMobileNumber"));
				message = message.replaceAll("<CRN>", rs.getString("CustomerUniqueID"));
				if(i!=1) {
					message = message.replaceAll("<community>", rs.getString("CommunityName"));
					message = message.replaceAll("<block>", rs.getString("BlockName"));
					message = message.replaceAll("<house>", rs.getString("HouseNumber"));	
				}
				
				smsRequestVO.setMessage(message);
				
				result = extraMethodsDao.sendsms(smsRequestVO).toString();				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public String sendalertmail(String subject, String message, String miuID) {
		// TODO Auto-generated method stub
		
		ExtraMethodsDAO extraMethodsDao = new ExtraMethodsDAO();
		MailRequestVO mailRequestVO = new MailRequestVO();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		String result = "Failure";
		
		try {
			con = getConnection();
			
			pstmt = con.prepareStatement("SELECT cd.Email AS customerEmail, b.Email AS adminEmail, b.BlockName as BlockName, c.CommunityName as CommunityName, cd.CustomerUniqueID, cd.HouseNumber FROM customerdetails as cd LEFT JOIN customermeterdetails AS cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN block AS b ON b.BlockID = cd.BlockID LEFT JOIN community AS c ON c.CommunityID = cd.CommunityID WHERE cmd.MIUID = '"+ miuID+"'");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mailRequestVO.setToEmail(subject.equalsIgnoreCase("Low Balance Alert!!!") ? rs.getString("customerEmail") : rs.getString("adminEmail"));
				mailRequestVO.setSubject(subject);
				message = message.replaceAll("<CRN>", rs.getString("CustomerUniqueID"));
				if(!subject.equalsIgnoreCase("Low Balance Alert!!!")) {
					message = message.replaceAll("<community>", rs.getString("CommunityName"));
					message = message.replaceAll("<block>", rs.getString("BlockName"));
					message = message.replaceAll("<house>", rs.getString("HouseNumber"));	
				}
				
				mailRequestVO.setMessage(subject.equalsIgnoreCase("Low Balance Alert!!!") ? "Dear Customer, \n \n"+message : "Dear Admin, \n \n"+ message);
				
				result = extraMethodsDao.sendmail(mailRequestVO);				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
