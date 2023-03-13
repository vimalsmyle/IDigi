/**
 * 
 */
package com.idigitronics.IDigi.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.idigitronics.IDigi.constants.DataBaseConstants;
import com.idigitronics.IDigi.request.vo.DataRequestVO;
import com.idigitronics.IDigi.request.vo.FilterVO;
import com.idigitronics.IDigi.request.vo.MailRequestVO;
import com.idigitronics.IDigi.request.vo.SMSRequestVO;
import com.idigitronics.IDigi.response.vo.AllGraphResponseVO;
import com.idigitronics.IDigi.response.vo.DashboardResponseVO;
import com.idigitronics.IDigi.response.vo.GraphResponseVO;
import com.idigitronics.IDigi.response.vo.HomeResponseVO;
import com.idigitronics.IDigi.response.vo.IndividualDashboardResponseVO;
import com.idigitronics.IDigi.response.vo.ResponseVO;
import com.idigitronics.IDigi.response.vo.Series;


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

	public List<DashboardResponseVO> getDashboarddetails(String type, String communityName, String blockName, String customerUniqueID, int filter)
			throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs3 = null;
		List<DashboardResponseVO> dashboard_list = null;
		List<IndividualDashboardResponseVO> individualDashboardList = null;
		IndividualDashboardResponseVO individualDashboardResponseVO = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		int communityID = 0;
		int blockID = 0;
		String mainquery = "";
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			int nonCommunicating = 0;
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}
			
			if(communityName.equalsIgnoreCase("0")) {
				
				mainquery = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID";
				
			} else {
				
				String IDquery = "SELECT * FROM <tablename>";
				PreparedStatement pstmt4 = con.prepareStatement(IDquery.replaceAll("<tablename>", (!blockName.equalsIgnoreCase("0") ? "block WHERE BlockName = '"+blockName+"' AND CommunityID = (SELECT CommunityID FROM community WHERE CommunityName = '"+communityName+"')" : "community WHERE CommunityName = '"+communityName+"'")));
				ResultSet rs4 = pstmt4.executeQuery();
				if(rs4.next()) {
					blockID = (!blockName.equalsIgnoreCase("0") ? rs4.getInt("BlockID") : 0);
					communityID = rs4.getInt("CommunityID");
				}
				
				mainquery = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID <main>";
				
				mainquery = mainquery.replaceAll("<main>", (blockID == 0 && customerUniqueID.equalsIgnoreCase("0")) ?"WHERE cd.CommunityID = "+communityID : (blockID !=0 && customerUniqueID.equalsIgnoreCase("0")) ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID : (blockID !=0 && !customerUniqueID.equalsIgnoreCase("0")) ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID + " AND cd.CustomerUniqueID = '" + customerUniqueID +"'" : "");
				
			}
			
			pstmt = con.prepareStatement(mainquery);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				dashboardvo = new DashboardResponseVO();
				individualDashboardList = new LinkedList<IndividualDashboardResponseVO>();
				
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				
				String query = "SELECT dbl.ReadingID, dbl.MainBalanceLogID, dbl.CustomerMeterID, dbl.MIUID, cmd.MeterSerialNumber, cmd.PayType, cmd.MeterType, ms.MeterSize, ms.PerUnitValue, g.GatewayName, dbl.Tariff, dbl.Reading, dbl.Balance, dbl.EmergencyCredit, dbl.ValveStatus, dbl.BatteryVoltage, "
						+ "dbl.LowBattery, dbl.DoorOpenTamper, dbl.MagneticTamper, dbl.RTCFault, dbl.Vacation, dbl.LowBalance, dbl.LogDate FROM displaybalancelog AS dbl LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerMeterID = dbl.CustomerMeterID LEFT JOIN metersize AS ms ON ms.MeterSizeID = cmd.MeterSizeID LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID WHERE cmd.CustomerID = ? AND cmd.MeterType = '" + type +"'";
				
				StringBuilder stringBuilder = new StringBuilder(query);
				if(filter != 0) {
					
//					1 = valve open(active), 2 = valve close(inactive) 3 = communicating(live), 4 = non-communicating(non-live) 5 = low battery 6 = emergency credit
					
					stringBuilder.append((filter == 1 || filter == 2) ? " AND dbl.ValveStatus = "+ (filter == 1 ? 1 : 0) : (filter == 3 || filter == 4) ? (filter == 3 ? " AND dbl.LogDate >= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " : " AND dbl.LogDate <= (NOW() - INTERVAL (SELECT NoAMRInterval/(24*60) FROM alertsettings) DAY) " ) :  (filter == 5) ? " AND dbl.LowBattery = "+ 1: (filter == 6) ? " AND dbl.Balance <= 0" : "");
					
				}
				stringBuilder.append(" ORDER BY dbl.LogDate DESC");
				pstmt3 = con.prepareStatement(stringBuilder.toString());
				pstmt3.setInt(1, rs.getInt("CustomerID"));
				rs3 = pstmt3.executeQuery();
				
				while(rs3.next()) {
					
					individualDashboardResponseVO = new IndividualDashboardResponseVO();
					
					individualDashboardResponseVO.setMeterSerialNumber(rs3.getString("MeterSerialNumber"));
					individualDashboardResponseVO.setPayType(rs3.getString("PayType"));
					individualDashboardResponseVO.setMeterType(rs3.getString("MeterType"));
					individualDashboardResponseVO.setMiuID(rs3.getString("MIUID"));
					individualDashboardResponseVO.setCustomerMeterID(rs3.getInt("CustomerMeterID"));
					individualDashboardResponseVO.setMeterSize(rs3.getFloat("MeterSize"));
					individualDashboardResponseVO.setGatewayName(rs3.getString("GatewayName"));
					individualDashboardResponseVO.setReading(rs3.getFloat("Reading"));
					individualDashboardResponseVO.setConsumption((int) (individualDashboardResponseVO.getReading() * rs3.getFloat("PerUnitValue")));
					individualDashboardResponseVO.setBattery(rs3.getInt("BatteryVoltage"));
					individualDashboardResponseVO.setBatteryColor((rs3.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
					individualDashboardResponseVO.setDoorOpenTamper((rs3.getInt("DoorOpenTamper") == 0) ? "NO" : (rs3.getInt("DoorOpenTamper") == 1) ? "YES" : "NO");
					individualDashboardResponseVO.setDooropentamperColor((rs3.getInt("DoorOpenTamper") == 0) ? "GREEN" : "RED");
					individualDashboardResponseVO.setMagneticTamper((rs3.getInt("MagneticTamper") == 0) ? "NO" : (rs3.getInt("MagneticTamper") == 1) ? "YES" : "NO");
					individualDashboardResponseVO.setMagnetictamperColor((rs3.getInt("MagneticTamper") == 0) ? "GREEN" : "RED");
					individualDashboardResponseVO.setTariff((rs3.getString("Tariff").equalsIgnoreCase("0.00") ? "---" : rs3.getString("Tariff")));
					individualDashboardResponseVO.setValveStatus((rs3.getInt("ValveStatus") == 1) ? "OPEN" : (rs3.getInt("ValveStatus") == 0) ? "CLOSED" : "");
					individualDashboardResponseVO.setValveStatusColor((rs3.getInt("ValveStatus") == 1) ? "GREEN" : (rs3.getInt("ValveStatus") == 0) ? "RED" : "");
					individualDashboardResponseVO.setVacationStatus(rs3.getInt("Vacation") == 1 ? "YES" : "NO");
					individualDashboardResponseVO.setVacationColor(rs3.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
					
					if(rs3.getString("PayType").equalsIgnoreCase("Prepaid")) {
						
						individualDashboardResponseVO.setBalance(rs3.getString("Balance"));
						individualDashboardResponseVO.setEmergencyCredit(rs3.getString("EmergencyCredit"));
						
					} else {
						individualDashboardResponseVO.setBalance("---");
						individualDashboardResponseVO.setEmergencyCredit("---");
						individualDashboardResponseVO.setLastTopupAmount("---");
						individualDashboardResponseVO.setLastRechargeDate("---");
					}
					
					individualDashboardResponseVO.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs3.getString("LogDate")));
					
					Date currentDateTime = new Date();
					
					long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs3.getTimestamp("LogDate")).getTime());

					if(minutes > noAMRInterval) {
						nonCommunicating++;
						individualDashboardResponseVO.setDateColor("RED");
						individualDashboardResponseVO.setCommunicationStatus("NO");
					}else if(minutes > 1440 && minutes < noAMRInterval) {
						individualDashboardResponseVO.setDateColor("ORANGE");
						individualDashboardResponseVO.setCommunicationStatus("YES");
					} else {
						individualDashboardResponseVO.setDateColor("GREEN");
						individualDashboardResponseVO.setCommunicationStatus("YES");
					}
					
					if(!customerUniqueID.isEmpty() && rs3.getString("PayType").equalsIgnoreCase("Prepaid")) {
						PreparedStatement pstmt2 = con.prepareStatement("SELECT Amount, TransactionDate FROM topup WHERE CustomerMeterID = "+rs3.getInt("CustomerMeterID")+" AND STATUS = 0 ORDER BY TransactionID DESC LIMIT 0,1") ;
						ResultSet rs2 = pstmt2.executeQuery();
						if(rs2.next()) {
							individualDashboardResponseVO.setLastTopupAmount(rs2.getString("Amount"));
							individualDashboardResponseVO.setLastRechargeDate(ExtraMethodsDAO.datetimeformatter(rs2.getString("TransactionDate")));
						} else {
						
						individualDashboardResponseVO.setLastTopupAmount("---");
						individualDashboardResponseVO.setLastRechargeDate("---");
						}
					}
					
					individualDashboardList.add(individualDashboardResponseVO);
				}
				dashboardvo.setNonCommunicating(nonCommunicating);
				dashboardvo.setDasboarddata(individualDashboardList);
				dashboard_list.add(dashboardvo);
				
				dashboard_list.removeIf(e -> e.getDasboarddata().size()==0);
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
	
	public ResponseVO dashboardFile(DashboardResponseVO dashboardResponseVO) {
		// TODO Auto-generated method stub
		
		ResponseVO responsevo = new ResponseVO();
		ByteArrayInputStream in = null;
		
		try {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet spreadsheet = workbook.createSheet("Dashboard List");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		
		XSSFRow header = spreadsheet.createRow(0);
		
		int columnCount = 0;
        
        Cell headercell1 = header.createCell(columnCount);
        headercell1.setCellValue("Community");
        
        Cell headercell2 = header.createCell(++columnCount);
        headercell2.setCellValue("Block");
        
        Cell headercell3 = header.createCell(++columnCount);
        headercell3.setCellValue("CRN/CAN/CUI");
        
        Cell headercell4 = header.createCell(++columnCount);
        headercell4.setCellValue("Name");
        
        Cell headercell5 = header.createCell(++columnCount);
        headercell5.setCellValue("House Number");
        
        Cell headercell6 = header.createCell(++columnCount);
        headercell6.setCellValue("Time Stamp");
        
        Cell headercell7 = header.createCell(++columnCount);
        headercell7.setCellValue("Meter Serial Number");
        
        Cell headercell8 = header.createCell(++columnCount);
        headercell8.setCellValue("MIU ID");
        
        Cell headercell9 = header.createCell(++columnCount);
        headercell9.setCellValue("Reading");
        
        Cell headercell10 = header.createCell(++columnCount);
        headercell10.setCellValue("Consumption");
        
        Cell headercell11 = header.createCell(++columnCount);
        headercell11.setCellValue("Battery");
        
        Cell headercell12 = header.createCell(++columnCount);
        headercell12.setCellValue("Box Tamper");
        
        Cell headercell13 = header.createCell(++columnCount);
        headercell13.setCellValue("Magnetic Tamper");
        
        Cell headercell14 = header.createCell(++columnCount);
        headercell14.setCellValue("Balance");
        
        Cell headercell15 = header.createCell(++columnCount);
        headercell15.setCellValue("Emergency Credit");
                
        Cell headercell16 = header.createCell(++columnCount);
        headercell16.setCellValue("Pay Type");
        
        Cell headercell17 = header.createCell(++columnCount);
        headercell17.setCellValue("Tariff");
        
        Cell headercell18 = header.createCell(++columnCount);
        headercell18.setCellValue("Valve Status");
        
        Cell headercell19 = header.createCell(++columnCount);
        headercell19.setCellValue("Vacation Status");
        
        Cell headercell20 = header.createCell(++columnCount);
        headercell20.setCellValue("Last Topup Amount");
        
        for(int i = 0; i< dashboardResponseVO.getData().size(); i++) {
        	
        	
        	XSSFRow data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1);
        	
        	/*Cell cell1 = data.createCell(dataColumnCount);
            cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
            
            Cell cell2 = data.createCell(++dataColumnCount);
            cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
            
            Cell cell3 = data.createCell(++dataColumnCount);
            cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
            
            Cell cell4 = data.createCell(++dataColumnCount);
            cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
            
            Cell cell5 = data.createCell(++dataColumnCount);
            cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());*/
            
            for(int j = 0; j < dashboardResponseVO.getData().get(i).getDasboarddata().size(); j++) {
            	
            	int dataColumnCount = 0;
            	
            	Cell cell1 = data.createCell(dataColumnCount);
                cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
                
                Cell cell2 = data.createCell(++dataColumnCount);
                cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
                
                Cell cell3 = data.createCell(++dataColumnCount);
                cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
                
                Cell cell4 = data.createCell(++dataColumnCount);
                cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
                
                Cell cell5 = data.createCell(++dataColumnCount);
                cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());
            	
            	int dashboarDataColumnCount = 5;
            	
            	Cell cell6 = data.createCell(dashboarDataColumnCount);
            	cell6.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTimeStamp());
            	
            	Cell cell7 = data.createCell(++dashboarDataColumnCount);
            	cell7.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMeterSerialNumber());
            	
            	Cell cell8 = data.createCell(++dashboarDataColumnCount);
            	cell8.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMiuID());
            	
            	Cell cell9 = data.createCell(++dashboarDataColumnCount);
            	cell9.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getReading());
            	
            	Cell cell10 = data.createCell(++dashboarDataColumnCount);
            	cell10.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getConsumption());
            	
            	Cell cell11 = data.createCell(++dashboarDataColumnCount);
            	cell11.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBattery());
            	
            	Cell cell12 = data.createCell(++dashboarDataColumnCount);
            	cell12.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getDoorOpenTamper());
            	
            	Cell cell13 = data.createCell(++dashboarDataColumnCount);
            	cell13.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMagneticTamper());
            	
            	Cell cell14 = data.createCell(++dashboarDataColumnCount);
            	cell14.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBalance());
            	
            	Cell cell15 = data.createCell(++dashboarDataColumnCount);
            	cell15.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getEmergencyCredit());
            	
            	Cell cell16 = data.createCell(++dashboarDataColumnCount);
            	cell16.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getPayType());
            	
            	Cell cell17 = data.createCell(++dashboarDataColumnCount);
            	cell17.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTariff());
            	
            	Cell cell18 = data.createCell(++dashboarDataColumnCount);
            	cell18.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getValveStatus());
            	
            	Cell cell19 = data.createCell(++dashboarDataColumnCount);
            	cell19.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getVacationStatus());
            	
            	Cell cell20 = data.createCell(++dashboarDataColumnCount);
            	cell20.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getLastTopupAmount());
            	
            	if(j < dashboardResponseVO.getData().get(i).getDasboarddata().size() - 1) { data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1); }
            	
            }
        	
        }
        
		workbook.write(outByteStream);
		in = new ByteArrayInputStream(outByteStream.toByteArray());
		workbook.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        responsevo.setResult("Success");
		responsevo.setFileName("Dashboard.xlsx");
		responsevo.setByteArrayInputStream(in);
        
		return responsevo;
		
	}

	public List<DashboardResponseVO> getFilterDashboarddetails(String communityName, String blockName, FilterVO filtervo, String type) throws SQLException {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DashboardResponseVO> dashboard_list = null;
		DashboardResponseVO dashboardvo = null;
		int noAMRInterval = 0;
		int communityID = 0;
		int blockID = 0;
		
		List<IndividualDashboardResponseVO> individualDashboardList = null;
		IndividualDashboardResponseVO individualDashboardResponseVO = null;
		
		try {
			con = getConnection();
			dashboard_list = new LinkedList<DashboardResponseVO>();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}

			PreparedStatement pstmt4 = con.prepareStatement("SELECT b.CommunityID, b.BlockID FROM block AS b Where b.BlockName = '" + blockName.trim() +"' AND b.CommunityID = (SELECT CommunityID FROM community WHERE CommunityName = '"+communityName+"')");
			ResultSet rs4 = pstmt4.executeQuery();
				
				if(rs4.next()) {
					blockID = rs4.getInt("BlockID");
					communityID = rs4.getInt("CommunityID");
				}
				
//				mainquery = mainquery.replaceAll("<main>", (blockID == 0) ? "WHERE cd.CommunityID = "+communityID : (blockID !=0) ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID : (blockID !=0) ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID + " AND cd.CustomerUniqueID = '" + customerUniqueID +"'" : "");
				
//			String mainquery = "SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID <main>";
			
//			mainquery = mainquery.replaceAll("<main>", blockID ==0 ?"WHERE cd.CommunityID = "+communityID : blockID !=0 ? "WHERE cd.CommunityID = "+communityID +" AND cd.BlockID = "+blockID : "");
			
			pstmt = con.prepareStatement("SELECT c.CommunityName, b.BlockName, cd.HouseNumber, cd.FirstName, cd.LastName, cd.CustomerUniqueID, cd.CustomerID FROM customerdetails AS cd LEFT JOIN community AS c ON cd.CommunityID = c.CommunityID LEFT JOIN block AS b ON b.BlockID = cd.BlockID WHERE cd.BlockID = "+ blockID+" AND cd.CommunityID = " +communityID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				dashboardvo = new DashboardResponseVO();
				individualDashboardList = new LinkedList<IndividualDashboardResponseVO>();
				
				dashboardvo.setCommunityName(rs.getString("CommunityName"));
				dashboardvo.setBlockName(rs.getString("BlockName"));
				dashboardvo.setHouseNumber(rs.getString("HouseNumber"));
				dashboardvo.setFirstName(rs.getString("FirstName"));
				dashboardvo.setLastName(rs.getString("LastName"));
				dashboardvo.setCustomerUniqueID(rs.getString("CustomerUniqueID"));
				
				String query = "SELECT dbl.ReadingID, dbl.MainBalanceLogID, dbl.CustomerMeterID, dbl.MIUID, cmd.MeterSerialNumber, cmd.PayType, g.GatewayName, dbl.Tariff, dbl.Reading, dbl.Balance, dbl.EmergencyCredit, dbl.ValveStatus, dbl.BatteryVoltage, "
						+ "dbl.LowBattery, dbl.DoorOpenTamper, dbl.MagneticTamper, dbl.RTCFault, dbl.Vacation, dbl.LowBalance, dbl.LogDate, ms.MeterSize, ms.PerUnitValue FROM displaybalancelog AS dbl LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerMeterID = dbl.CustomerMeterID LEFT JOIN metersize AS ms ON ms.MeterSizeID = cmd.MeterSizeID LEFT JOIN gateway AS g ON g.GatewayID = cmd.GatewayID WHERE cmd.CustomerID = ? AND cmd.MeterType = '" + type+"'";
				
				/*String oldquery = "SELECT DISTINCT c.CommunityName, b.BlockName, cd.FirstName,cd.CustomerUniqueID, cd.LastName, cd.HouseNumber, cmd.MeterSerialNumber, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, \r\n" + 
						"dbl.MIUID, dbl.Reading, dbl.Balance, dbl.BatteryVoltage, dbl.LowBattery, dbl.Tariff, dbl.ValveStatus, dbl.DoorOpenTamper, dbl.MagneticTamper, dbl.RTCFault, dbl.Vacation, dbl.LowBalance, dbl.LogDate\r\n" + 
						"FROM displaybalancelog AS dbl LEFT JOIN community AS c ON c.communityID = dbl.CommunityID LEFT JOIN block AS b ON b.BlockID = dbl.BlockID\r\n" + 
						"LEFT JOIN customerdetails AS cd ON cd.CustomerID = dbl.CustomerID LEFT JOIN customermeterdetails AS cmd ON cmd.CustomerMeterID = dbl.CustomerMeterID WHERE 1=1 <change>";*/
				
						StringBuilder stringBuilder = new StringBuilder(query);
					
						LocalDateTime dateTime = LocalDateTime.now();  
					    DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
						
						if(!filtervo.getDateFrom().equalsIgnoreCase("null") || !filtervo.getDateTo().equalsIgnoreCase("null")) {
							stringBuilder.append(" AND dbl.LogDate BETWEEN '" + filtervo.getDateFrom() + "' AND '" + (filtervo.getDateTo() != null ? filtervo.getDateTo()+"'" : "'"+dateTime.format(dateTimeFormat)+"'"));
						}
						if(filtervo.getReadingFrom() != 0 || filtervo.getReadingTo() != 0) {
							stringBuilder.append(" AND dbl.Reading BETWEEN " + (filtervo.getReadingFrom() != 0 ? filtervo.getReadingFrom() : 0) + " AND " + (filtervo.getReadingTo() != 0 ? filtervo.getReadingTo() : 9999999));
						}
						if(filtervo.getBatteryVoltageFrom() != 0 || filtervo.getBatteryVoltageTo() != 0) {
							stringBuilder.append(" AND dbl.BatteryVoltage BETWEEN " + (filtervo.getBatteryVoltageFrom() != 0 ? (filtervo.getBatteryVoltageFrom()) : 0) + " AND " + (filtervo.getBatteryVoltageTo() != 0 ? (filtervo.getBatteryVoltageTo()) : 100));
						}
						if(filtervo.getTamperType() > 0) {
							stringBuilder.append(filtervo.getTamperType() == 1 ? " AND dbl.DoorOpenTamper = 1" : " AND dbl.MagneticTamper = 1");
						}
							stringBuilder.append(" ORDER BY dbl.LogDate DESC");

						PreparedStatement pstmt2 = con.prepareStatement(stringBuilder.toString());
						pstmt2.setInt(1, rs.getInt("CustomerID"));
						ResultSet rs2 = pstmt2.executeQuery();
						while (rs2.next()) {
							
							individualDashboardResponseVO = new IndividualDashboardResponseVO();
							
							individualDashboardResponseVO.setMeterSerialNumber(rs2.getString("MeterSerialNumber"));
							individualDashboardResponseVO.setMiuID(rs2.getString("MIUID"));
							individualDashboardResponseVO.setGatewayName(rs2.getString("GatewayName"));
							individualDashboardResponseVO.setReading(rs2.getFloat("Reading"));
							individualDashboardResponseVO.setConsumption((int) (individualDashboardResponseVO.getReading() * rs2.getFloat("PerUnitValue")));
							individualDashboardResponseVO.setBattery(rs2.getInt("BatteryVoltage"));
							individualDashboardResponseVO.setBatteryColor((rs2.getInt("LowBattery") == 1 ) ? "RED" : "GREEN");
							individualDashboardResponseVO.setDoorOpenTamper((rs2.getInt("DoorOpenTamper") == 0) ? "NO" : (rs2.getInt("DoorOpenTamper") == 1) ? "YES" : "NO");
							individualDashboardResponseVO.setDooropentamperColor((rs2.getInt("DoorOpenTamper") == 0) ? "GREEN" : "RED");
							individualDashboardResponseVO.setMagneticTamper((rs2.getInt("MagneticTamper") == 0) ? "NO" : (rs2.getInt("MagneticTamper") == 1) ? "YES" : "NO");
							individualDashboardResponseVO.setMagnetictamperColor((rs2.getInt("MagneticTamper") == 0) ? "GREEN" : "RED");
							
							if(rs2.getString("PayType").equalsIgnoreCase("Prepaid")) {
								
								individualDashboardResponseVO.setBalance(rs2.getString("Balance"));
								individualDashboardResponseVO.setEmergencyCredit(rs2.getString("EmergencyCredit"));
								individualDashboardResponseVO.setTariff((rs2.getString("Tariff")));
								individualDashboardResponseVO.setValveStatus((rs2.getInt("ValveStatus") == 1) ? "OPEN" : (rs2.getInt("ValveStatus") == 0) ? "CLOSED" : "");
								individualDashboardResponseVO.setValveStatusColor((rs2.getInt("ValveStatus") == 1) ? "GREEN" : (rs2.getInt("ValveStatus") == 0) ? "RED" : "");
								individualDashboardResponseVO.setVacationStatus(rs2.getInt("Vacation") == 1 ? "YES" : "NO");
								individualDashboardResponseVO.setVacationColor(rs2.getInt("Vacation") == 1 ? "ORANGE" : "BLACK");
							} else {
								individualDashboardResponseVO.setBalance("---");
								individualDashboardResponseVO.setEmergencyCredit("---");
								individualDashboardResponseVO.setTariff("---");
								individualDashboardResponseVO.setValveStatus("---");
								individualDashboardResponseVO.setValveStatusColor("---");
								individualDashboardResponseVO.setVacationStatus("---");
								individualDashboardResponseVO.setVacationColor("---");
							}
							
							individualDashboardResponseVO.setTimeStamp(ExtraMethodsDAO.datetimeformatter(rs2.getString("LogDate")));
							
							Date currentDateTime = new Date();
							
							long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs2.getTimestamp("LogDate")).getTime());
							
							if(minutes > noAMRInterval) {
								individualDashboardResponseVO.setDateColor("RED");
								individualDashboardResponseVO.setCommunicationStatus("NO");
							}else if(minutes > 1440 && minutes < noAMRInterval) {
								individualDashboardResponseVO.setDateColor("ORANGE");
								individualDashboardResponseVO.setCommunicationStatus("YES");
							} else {
								individualDashboardResponseVO.setDateColor("GREEN");
								individualDashboardResponseVO.setCommunicationStatus("YES");
							}
							individualDashboardList.add(individualDashboardResponseVO);
				
			}
					dashboardvo.setDasboarddata(individualDashboardList);
					dashboard_list.add(dashboardvo);
					dashboard_list.removeIf(e -> e.getDasboarddata().size()==0);
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
	
	public ResponseVO filterDashboardFile(DashboardResponseVO dashboardResponseVO) {
		// TODO Auto-generated method stub
		ResponseVO responsevo = new ResponseVO();

		ByteArrayInputStream in = null;
		
		try {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		XSSFSheet spreadsheet = workbook.createSheet("Filter Dashboard List");
		
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		
		XSSFRow header = spreadsheet.createRow(0);
		
		int columnCount = 0;
        
        Cell headercell1 = header.createCell(columnCount);
        headercell1.setCellValue("Community");
        
        Cell headercell2 = header.createCell(++columnCount);
        headercell2.setCellValue("Block");
        
        Cell headercell3 = header.createCell(++columnCount);
        headercell3.setCellValue("CRN/CAN");
        
        Cell headercell4 = header.createCell(++columnCount);
        headercell4.setCellValue("Name");
        
        Cell headercell5 = header.createCell(++columnCount);
        headercell5.setCellValue("House Number");
        
        Cell headercell6 = header.createCell(++columnCount);
        headercell6.setCellValue("Time Stamp");
        
        Cell headercell7 = header.createCell(++columnCount);
        headercell7.setCellValue("Meter Serial Number");
        
        Cell headercell8 = header.createCell(++columnCount);
        headercell8.setCellValue("MIU ID");
        
        Cell headercell9 = header.createCell(++columnCount);
        headercell9.setCellValue("Reading");
        
        Cell headercell10 = header.createCell(++columnCount);
        headercell10.setCellValue("Consumption");
        
        Cell headercell11 = header.createCell(++columnCount);
        headercell11.setCellValue("Battery");
        
        Cell headercell12 = header.createCell(++columnCount);
        headercell12.setCellValue("Bax Tamper");
        
        Cell headercell13 = header.createCell(++columnCount);
        headercell13.setCellValue("Magnetic Tamper");
        
        Cell headercell14 = header.createCell(++columnCount);
        headercell14.setCellValue("Balance");
        
        Cell headercell15 = header.createCell(++columnCount);
        headercell15.setCellValue("Emergency Credit");
                
        Cell headercell16 = header.createCell(++columnCount);
        headercell16.setCellValue("Pay Type");
        
        Cell headercell17 = header.createCell(++columnCount);
        headercell17.setCellValue("Tariff");
        
        Cell headercell18 = header.createCell(++columnCount);
        headercell18.setCellValue("Valve Status");
        
        Cell headercell19 = header.createCell(++columnCount);
        headercell19.setCellValue("Vacation Status");
        
        Cell headercell20 = header.createCell(++columnCount);
        headercell20.setCellValue("Last Topup Amount");
        
        for(int i = 0; i< dashboardResponseVO.getData().size(); i++) {
        	
        	
        	XSSFRow data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1);
        	
        	/*Cell cell1 = data.createCell(dataColumnCount);
            cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
            
            Cell cell2 = data.createCell(++dataColumnCount);
            cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
            
            Cell cell3 = data.createCell(++dataColumnCount);
            cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
            
            Cell cell4 = data.createCell(++dataColumnCount);
            cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
            
            Cell cell5 = data.createCell(++dataColumnCount);
            cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());*/
            
            for(int j = 0; j < dashboardResponseVO.getData().get(i).getDasboarddata().size(); j++) {
 
            	int dataColumnCount = 0;
            	
            	Cell cell1 = data.createCell(dataColumnCount);
                cell1.setCellValue(dashboardResponseVO.getData().get(i).getCommunityName());
                
                Cell cell2 = data.createCell(++dataColumnCount);
                cell2.setCellValue(dashboardResponseVO.getData().get(i).getBlockName());
                
                Cell cell3 = data.createCell(++dataColumnCount);
                cell3.setCellValue(dashboardResponseVO.getData().get(i).getCustomerUniqueID());
                
                Cell cell4 = data.createCell(++dataColumnCount);
                cell4.setCellValue(dashboardResponseVO.getData().get(i).getFirstName()+" "+dashboardResponseVO.getData().get(i).getLastName());
                
                Cell cell5 = data.createCell(++dataColumnCount);
                cell5.setCellValue(dashboardResponseVO.getData().get(i).getHouseNumber());
                
            	int dashboarDataColumnCount = 5;
            	
            	Cell cell6 = data.createCell(dashboarDataColumnCount);
            	cell6.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTimeStamp());
            	
            	Cell cell7 = data.createCell(++dashboarDataColumnCount);
            	cell7.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMeterSerialNumber());
            	
            	Cell cell8 = data.createCell(++dashboarDataColumnCount);
            	cell8.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMiuID());
            	
            	Cell cell9 = data.createCell(++dashboarDataColumnCount);
            	cell9.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getReading());
            	
            	Cell cell10 = data.createCell(++dashboarDataColumnCount);
            	cell10.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getConsumption());
            	
            	Cell cell11 = data.createCell(++dashboarDataColumnCount);
            	cell11.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBattery());
            	
            	Cell cell12 = data.createCell(++dashboarDataColumnCount);
            	cell12.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getDoorOpenTamper());
            	
            	Cell cell13 = data.createCell(++dashboarDataColumnCount);
            	cell13.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getMagneticTamper());
            	
            	Cell cell14 = data.createCell(++dashboarDataColumnCount);
            	cell14.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getBalance());
            	
            	Cell cell15 = data.createCell(++dashboarDataColumnCount);
            	cell15.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getEmergencyCredit());
            	
            	Cell cell16 = data.createCell(++dashboarDataColumnCount);
            	cell16.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getPayType());
            	
            	Cell cell17 = data.createCell(++dashboarDataColumnCount);
            	cell17.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getTariff());
            	
            	Cell cell18 = data.createCell(++dashboarDataColumnCount);
            	cell18.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getValveStatus());
            	
            	Cell cell19 = data.createCell(++dashboarDataColumnCount);
            	cell19.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getVacationStatus());
            	
            	Cell cell20 = data.createCell(++dashboarDataColumnCount);
            	cell20.setCellValue(dashboardResponseVO.getData().get(i).getDasboarddata().get(j).getLastTopupAmount());
            	
            	if(j < dashboardResponseVO.getData().get(i).getDasboarddata().size() - 1) { data = spreadsheet.createRow(spreadsheet.getLastRowNum()+1); }
            	
            }
        	
        }
        
        workbook.write(outByteStream);
		in = new ByteArrayInputStream(outByteStream.toByteArray());
		workbook.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        responsevo.setResult("Success");
		responsevo.setByteArrayInputStream(in);
		responsevo.setFileName("FilterDashboard.xlsx");
        
		return responsevo;
	}
	
	public GraphResponseVO getGraphDashboardDetails(String type, int year, int month, String communityName) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		
		GraphResponseVO graphResponseVO = new GraphResponseVO();
		List<String> xAxis;
		List<Integer> yAxis;
		int id = 0;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			yAxis = new LinkedList<Integer>();
			
			if(!communityName.equalsIgnoreCase("0")) {
				ResultSet rs1 = con.prepareStatement("SELECT * FROM block WHERE CommunityID = (SELECT CommunityID FROM community WHERE CommunityName = '"+communityName+"')").executeQuery();
				if(rs1.next()) { id = rs1.getInt("CommunityID"); }				
				}
			
					if(year == 0 && month == 0) {
						
						String start = "SELECT * FROM <tablename> ";
						PreparedStatement pstmt3 = con.prepareStatement(start.replaceAll("<tablename>", id != 0 ? "block WHERE CommunityID = "+id : "community"));
						ResultSet rs3 = pstmt3.executeQuery();
						
						while(rs3.next()) {
							
							int totalConsumptionPerDayMonthYear = 0;
						
						// last 30 days	
							
//						for(int i = 2; i>0; i-- ) {
							
								int customerConsumption = 0;
								
								String mainquery = "SELECT * FROM customerdetails <main>";
								
								mainquery = mainquery.replaceAll("<main>", id != 0 ? "WHERE CommunityID = "+id+" AND BlockID = "+ rs3.getInt("BlockID")+" ORDER BY CustomerID ASC" : "WHERE CommunityID = "+rs3.getInt("CommunityID"));
								
								PreparedStatement pstmt2 = con.prepareStatement(mainquery);
								ResultSet rs2 = pstmt2.executeQuery();
								while (rs2.next()) {
									PreparedStatement pstmt1 =  con.prepareStatement("SELECT CustomerMeterID, MIUID FROM Customermeterdetails WHERE CustomerID = " + rs2.getLong("CustomerID") + " AND MeterType = '"+type+"'");
									ResultSet rs1 = pstmt1.executeQuery();
									
									int individualMeterConsumption = 0;
									
									while(rs1.next()) {
										
										String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
												 		"- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
							
										pstmt = con.prepareStatement(query.replaceAll("<day>", ""+1));
										pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
										pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
										rs = pstmt.executeQuery();
										
										if(rs.next()) {individualMeterConsumption = individualMeterConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));}
										}
									
									customerConsumption = customerConsumption +  individualMeterConsumption;
								}
								
								totalConsumptionPerDayMonthYear = totalConsumptionPerDayMonthYear + customerConsumption;
								
//							}
						
						xAxis.add(id != 0 ? rs3.getString("BlockName") : rs3.getString("CommunityName"));
						yAxis.add(totalConsumptionPerDayMonthYear);
							
						}
					} 
					else if (year != 0 &&  month == 0) {
						
						String start = "SELECT * FROM <tablename> ";
						PreparedStatement pstmt3 = con.prepareStatement(start.replaceAll("<tablename>", id != 0 ? "block  WHERE CommunityID = "+id : "community"));
						ResultSet rs3 = pstmt3.executeQuery();
						
						while(rs3.next()) {
							
							int totalConsumptionPerDayMonthYear = 0;
						
						for(int i = 1; i<=12; i++) {
							
							int customerConsumption = 0;
							
							String mainquery = "SELECT * FROM customerdetails <main>";
							
							mainquery = mainquery.replaceAll("<main>", id != 0 ? "WHERE CommunityID = "+rs3.getInt("CommunityID")+" AND BlockID = "+ id+" ORDER BY CustomerID ASC" : "WHERE CommunityID = "+rs3.getInt("CommunityID"));
							
							PreparedStatement pstmt2 = con.prepareStatement(mainquery);
							ResultSet rs2 = pstmt2.executeQuery();
							while (rs2.next()) {
								PreparedStatement pstmt1 =  con.prepareStatement("SELECT CustomerMeterID, MIUID FROM Customermeterdetails WHERE CustomerID = " + rs2.getLong("CustomerID") + " AND MeterType = '"+type+"'");
								ResultSet rs1 = pstmt1.executeQuery();
								
								int individualMeterConsumption = 0;
								
								while(rs1.next()) {
									
								String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
									      "-(SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
								pstmt = con.prepareStatement(query.replaceAll("<month>", ""+i));
								pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(2, year);
								pstmt.setInt(3, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(4, year);
								rs = pstmt.executeQuery();
								
								if(rs.next()) {individualMeterConsumption = individualMeterConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));}
								}
								
								customerConsumption = customerConsumption +  individualMeterConsumption;
							}
							
							totalConsumptionPerDayMonthYear = totalConsumptionPerDayMonthYear + customerConsumption;
						}
							
							xAxis.add(id != 0 ? rs3.getString("BlockName") : rs3.getString("CommunityName"));
							yAxis.add(totalConsumptionPerDayMonthYear);
						}
					} else if(year != 0 && month != 0) {
						
						int j = (month == 2 ? 28 : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30);
						
						String start = "SELECT * FROM <tablename> ";
						PreparedStatement pstmt3 = con.prepareStatement(start.replaceAll("<tablename>", id != 0 ? "block  WHERE CommunityID = "+id : "community"));
						ResultSet rs3 = pstmt3.executeQuery();
						
						while(rs3.next()) {
							
							int totalConsumptionPerDayMonthYear = 0;
						
						for(int i = 1; i <= j ; i++) {
							
							int customerConsumption = 0;
							
							String mainquery = "SELECT * FROM customerdetails <main>";
							
							mainquery = mainquery.replaceAll("<main>", id != 0 ? "WHERE CommunityID = "+rs3.getInt("CommunityID")+" AND BlockID = "+ id+" ORDER BY CustomerID ASC" : "WHERE CommunityID = "+rs3.getInt("CommunityID"));
							
							PreparedStatement pstmt2 = con.prepareStatement(mainquery);
							ResultSet rs2 = pstmt2.executeQuery();
							while (rs2.next()) {
								PreparedStatement pstmt1 =  con.prepareStatement("SELECT CustomerMeterID, MIUID FROM Customermeterdetails WHERE CustomerID = " + rs2.getLong("CustomerID") + " AND MeterType = '"+type+"'");
								ResultSet rs1 = pstmt1.executeQuery();
								
								int individualMeterConsumption = 0;
								
								while(rs1.next()) {
								
								String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
										 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
					
								pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
								pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(2, year);
								pstmt.setInt(3, month);
								pstmt.setInt(4, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(5, year);
								pstmt.setInt(6, month);
								rs = pstmt.executeQuery();
								
								if(rs.next()) {individualMeterConsumption = individualMeterConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));}
								}
								
								customerConsumption = customerConsumption +  individualMeterConsumption;
							}
							
							totalConsumptionPerDayMonthYear = totalConsumptionPerDayMonthYear + customerConsumption;
							
							}
						xAxis.add(id != 0 ? rs3.getString("BlockName") : rs3.getString("CommunityName"));
						yAxis.add(totalConsumptionPerDayMonthYear);
						}
					}
			graphResponseVO.setXAxis(xAxis);
			graphResponseVO.setYAxis(yAxis);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return graphResponseVO;
	}
	
	public HomeResponseVO getHomeDashboardDetails(String type, int roleid, String id)
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
		int communityid = 0;
		
		try {
			
//			1 = valve open(active), 2 = valve close(inactive) 3 = communicating(live), 4 = non-communicating(non-live) 5 = low battery 6 = emergency credit
			
			con = getConnection();
			homeResponseVO = new HomeResponseVO();
			
			PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM alertsettings");
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next()) {
				
				noAMRInterval = rs1.getInt("NoAMRInterval");
			}
			
			if(!id.equalsIgnoreCase("0") && (roleid == 1 || roleid == 4)) {
				PreparedStatement pstmt5 = con.prepareStatement("SELECT CommunityID FROM community WHERE CommunityName = '"+id+"'");
				ResultSet rs5 = pstmt5.executeQuery();
				if(rs5.next()) {
					communityid = rs5.getInt("CommunityID");
				}
			}
			
			List<DashboardResponseVO> responselist = ((roleid == 1 || roleid == 4) ? getDashboarddetails(type, id, "0", "0", 0) : getDashboarddetails(type, "0", id, "0", 0));
			int size = responselist.size();
			
			for(int i = 0; i < size; i++) {
				
				for(int j = 0; j < responselist.get(i).getDasboarddata().size(); j++) {
					
					amr++;
					Date currentDateTime = new Date();
					
//					long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (responselist.get(i).getDasboarddata().get(j).getTimeStamp().getTime());
					
//					if(minutes > noAMRInterval) { nonLive++; } else { live++; }
					if(responselist.get(i).getDasboarddata().get(j).getValveStatus().equalsIgnoreCase("Open")) { active++; } else { inActive++; }
					if(!responselist.get(i).getDasboarddata().get(j).getBalance().equalsIgnoreCase("---") && responselist.get(i).getDasboarddata().get(j).getPayType().equalsIgnoreCase("Prepaid")) { if(Integer.parseInt((responselist.get(i).getDasboarddata().get(j).getBalance())) <= 0) { emergency++; } }
					if(responselist.get(i).getDasboarddata().get(j).getBatteryColor().equalsIgnoreCase("RED")) { lowBattery++; }
				}
			}
			
			/*String query = "SELECT DISTINCT dbl.CustomerUniqueID, dbl.ReadingID, dbl.MainBalanceLogID, dbl.EmergencyCredit, dbl.CustomerMeterID, dbl.MIUID, dbl.PayType, dbl.MeterType, dbl.Reading, dbl.Balance, dbl.LowBattery, dbl.ValveStatus, dbl.ValveConfiguration, dbl.DoorOpenTamper, dbl.MagneticTamper, dbl.Vacation, dbl.RTCFault, dbl.LowBalance, dbl.Minutes, dbl.LogDate FROM displaybalancelog AS dbl WHERE dbl.MeterType = '"+type+"' AND dbl.MIUID IN (SELECT MIUID FROM customermeterdetails WHERE MeterType = '"+type+"') <change>";

			pstmt = con.prepareStatement(query.replaceAll("<change>", ((roleid == 1 || roleid == 4) && !id.equalsIgnoreCase("0")) ? "AND dbl.CommunityID = "+communityid+" ORDER BY dbl.LogDate DESC" : ((roleid == 1 || roleid == 4) && id.equalsIgnoreCase("0")) ? "ORDER BY dbl.LogDate DESC" : (roleid == 2 || roleid == 5) ? "AND dbl.BlockID = "+id+ " ORDER BY dbl.LogDate DESC" : (roleid == 3) ? "AND dbl.CustomerUniqueID = '"+id+"'":""));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				
				amr++;

				Date currentDateTime = new Date();
				
				long minutes = TimeUnit.MILLISECONDS.toMinutes(currentDateTime.getTime() - (rs.getTimestamp("LogDate")).getTime());
				
				if(minutes > noAMRInterval) { nonLive++; } else { live++; }
				if(rs.getInt("ValveStatus") == 1) { active++; } else { inActive++; }
				if(rs.getInt("Balance") <= 0 && rs.getString("PayType").equalsIgnoreCase("Prepaid")) { emergency++; }
				if(rs.getInt("LowBattery") == 1) { lowBattery++; }
				
			}*/
			
			homeResponseVO.setLive(live);
			homeResponseVO.setLivePercentage(amr == 0 ? 0 : (live*100/amr));
			homeResponseVO.setNonLive(nonLive);
			homeResponseVO.setNonLivePercentage(amr == 0 ? 0 : (nonLive*100/amr));
			homeResponseVO.setActive(active);
			homeResponseVO.setActivePercentage(amr == 0 ? 0 : (active*100/amr));
			homeResponseVO.setInActive(inActive);
			homeResponseVO.setInActivePercentage(amr == 0 ? 0 : (inActive*100/amr));
			homeResponseVO.setEmergency(emergency);
			homeResponseVO.setEmergencyPercentage(amr == 0 ? 0 : (emergency*100/amr));
			homeResponseVO.setLowBattery(lowBattery);
			homeResponseVO.setLowBatteryPercentage(amr == 0 ? 0 : (lowBattery*100/amr));
			homeResponseVO.setAmr(amr);
			homeResponseVO.setAmrPercentage(amr == 0 ? 0 : 100);
			
			String query1 = "SELECT SUM(Amount) AS topup FROM topup WHERE Status = 0 AND PaymentStatus = 1 AND TransactionDate BETWEEN CONCAT(CURDATE(), ' 00:00:00') AND CONCAT(CURDATE(), ' 23:59:59') <change>";
			pstmt2 = con.prepareStatement(query1.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "AND BlockID = "+id :""));
			rs2 = pstmt2.executeQuery();
			if(rs2.next()) { homeResponseVO.setTopup(rs2.getInt("topup")); } else { homeResponseVO.setTopup(0); }
			
			String query2 = "SELECT cmd.CustomerMeterID, cd.CustomerID FROM customermeterdetails AS cmd LEFT JOIN customerdetails AS cd ON cd.CustomerID = cmd.CustomerID WHERE cmd.MeterType = '"+type+"' <change>";
			pstmt3 = con.prepareStatement(query2.replaceAll("<change>", (roleid == 2 || roleid == 5) ? "AND BlockID = "+id + " ORDER BY CustomerID ASC" :""));
			rs3 = pstmt3.executeQuery();
			while(rs3.next()) {
				
				String query3 = "SELECT ABS((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID DESC LIMIT 0,1)\r\n" + 
						"- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN (NOW() - INTERVAL 1 DAY) AND NOW() ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";

				pstmt4 = con.prepareStatement(query3);
				pstmt4.setInt(1, rs3.getInt("CustomerMeterID"));
				pstmt4.setInt(2, rs3.getInt("CustomerMeterID"));
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
	
	public GraphResponseVO getCustomerGraphDashboardDetails(String type, int year, int month, String customerUniqueID) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
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
					
					int totalMetersConsumptionPerDay = 0;
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = '"+ type+"'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
			
						pstmt = con.prepareStatement(query.replaceAll("<day>", "" + i));
						pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
						pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
						rs = pstmt.executeQuery();

						if (rs.next()) {
							
							totalMetersConsumptionPerDay = totalMetersConsumptionPerDay + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						}
					}
					
					xAxis.add(rs.getString("consumptiondate"));
					yAxis.add(totalMetersConsumptionPerDay);
				}
			} else if (year != 0 &&  month == 0) {
				
				for(int i = 1; i<=12; i++ ) {
					
					int totalMetersConsumption = 0;
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = '"+ type+"'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
							      "-(SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = <month> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
			
							pstmt = con.prepareStatement(query.replaceAll("<month>", ""+i));
							pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
							pstmt.setInt(2, year);
							pstmt.setInt(3, rs1.getInt("CustomerMeterID"));
							pstmt.setInt(4, year);
							rs = pstmt.executeQuery();
			
								if(rs.next()) {
									
									totalMetersConsumption = totalMetersConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
									
									}
						}
					xAxis.add(i==1 ? "JAN-"+year : i==2 ? "FEB-"+year : i==3 ? "MAR-"+year : i==4 ? "APR-"+year : i==5 ? "MAY-"+year : i==6 ? "JUN-"+year : i==7 ? "JUL-"+year : i==8 ? "AUG-"+year : i==9 ? "SEP-"+year : i==10 ? "OCT-"+year : i==11 ? "NOV-"+year : i==12 ? "DEC-"+year : "");
					yAxis.add(totalMetersConsumption);
					
				}
				
			} else if(year != 0 && month != 0) {
				
				int j = (month == 2 ? 28 : (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) ? 31 : 30);
				
				for(int i = 1; i <= j ; i++) {
					
					int totalMetersConsumption = 0;
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = '"+ type+"'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND YEAR(LogDate) = ? AND MONTH(LogDate) = ? AND DAY(LogDate) = <day> ORDER BY ReadingID ASC LIMIT 0,1)) AS Units";
			
								pstmt = con.prepareStatement(query.replaceAll("<day>", ""+i));
								pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(2, year);
								pstmt.setInt(3, month);
								pstmt.setInt(4, rs1.getInt("CustomerMeterID"));
								pstmt.setInt(5, year);
								pstmt.setInt(6, month);
								rs = pstmt.executeQuery();
			
									if(rs.next()) {
										totalMetersConsumption = totalMetersConsumption + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
										}
					}
					xAxis.add(Integer.toString(i)+"-"+month+"-"+year);
					yAxis.add(totalMetersConsumption);
					
					}
				}
			
			graphResponseVO.setXAxis(xAxis);
			graphResponseVO.setYAxis(yAxis);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return graphResponseVO;
	}
	
	public AllGraphResponseVO getCustomerAllGraphDashboardDetails(int year, int month, String customerUniqueID) {
		// TODO Auto-generated method stub
		
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Connection con = null;
		
		AllGraphResponseVO allGraphResponseVO = new AllGraphResponseVO();
		Series series = null;
		List<String> xAxis;
		List<Series> seriesList;
		List<Integer> gasData;
		List<Integer> waterData;
		
		try {
			con = getConnection();
			xAxis = new LinkedList<String>();
			seriesList = new LinkedList<Series>();
			gasData = new LinkedList<Integer>();
			waterData = new LinkedList<Integer>();
			
			if(year == 0 && month == 0) {
				
				for(int i = 30; i>0; i-- ) {
					
					int totalGasMetersConsumptionPerDay = 0;
					
					series = new Series();
					series.setName("Gas");
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = 'Gas'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
			
						pstmt = con.prepareStatement(query.replaceAll("<day>", "" + i));
						pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
						pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
						rs = pstmt.executeQuery();

						if (rs.next()) {
							
							totalGasMetersConsumptionPerDay = totalGasMetersConsumptionPerDay + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						}
					}
					
					xAxis.add(rs.getString("consumptiondate"));
					gasData.add(totalGasMetersConsumptionPerDay);
				}
				
				series.setData(gasData);
				seriesList.add(series);
				
				for(int i = 30; i>0; i-- ) {
					
					int totalWaterMetersConsumptionPerDay = 0;
					gasData = new LinkedList<Integer>();
					series = new Series();
					series.setName("Water");
					
					pstmt1 = con.prepareStatement("SELECT * FROM customermeterdetails WHERE CustomerUniqueID = '"+customerUniqueID+"' AND MeterType = 'Water'");
					rs1 = pstmt1.executeQuery();
					while(rs1.next()) {
						String query = "SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID DESC LIMIT 0,1) \r\n" + 
								 "- (SELECT Reading FROM balancelog WHERE CustomerMeterID = ? AND LogDate BETWEEN CONCAT(CURDATE() - INTERVAL <day> DAY, ' 00:00:00') AND CONCAT(CURDATE() - INTERVAL <day> DAY, ' 23:59:59') ORDER BY ReadingID ASC LIMIT 0,1)) AS Units, CURDATE() - INTERVAL <day> DAY AS consumptiondate";
			
						pstmt = con.prepareStatement(query.replaceAll("<day>", "" + i));
						pstmt.setInt(1, rs1.getInt("CustomerMeterID"));
						pstmt.setInt(2, rs1.getInt("CustomerMeterID"));
						rs = pstmt.executeQuery();

						if (rs.next()) {
							
							totalWaterMetersConsumptionPerDay = totalWaterMetersConsumptionPerDay + (rs.getString("Units") == null ? 0 : rs.getInt("Units"));
						}
					}
					
					waterData.add(totalWaterMetersConsumptionPerDay);
				}
				
				series.setData(waterData);
				seriesList.add(series);

			} 
			
			allGraphResponseVO.setxAxis(xAxis);
			allGraphResponseVO.setSeries(seriesList);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return allGraphResponseVO;
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
			
				PreparedStatement pstmt2 = con.prepareStatement("SELECT cd.CommunityID, cd.BlockID, cd.CustomerID, cmd.CustomerMeterID, cmd.TariffID, t.Tariff, cmd.MeterSerialNumber, cd.CustomerUniqueID, cmd.MeterSizeID, cmd.ThresholdMaximum, cmd.ThresholdMinimum from customerdetails as cd LEFT JOIN customermeterdetails as cmd ON cd.CustomerID = cmd.CustomerID LEFT JOIN tariff as t on t.TariffID = cmd.TariffID WHERE cmd.MIUID = ?");
				pstmt2.setString(1, miuID);
				ResultSet rs = pstmt2.executeQuery();
				if(rs.next()) {
					
					pstmt = con.prepareStatement("INSERT INTO balancelog (MIUID, CommunityID, BlockID, CustomerID, CustomerMeterID, MeterSizeID, MeterSerialNumber, CustomerUniqueID, MeterType, SyncTime, SyncInterval, PayType, BatteryVoltage, TariffID, Tariff, ValveConfiguration, ValveStatus, Balance, EmergencyCredit, Minutes, Reading, DoorOpenTamper, MagneticTamper, Vacation, RTCFault, LowBattery, LowBalance, LogDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
					pstmt.setString(1, miuID);
					pstmt.setInt(2, rs.getInt("CommunityID"));
					pstmt.setInt(3, rs.getInt("BlockID"));
					pstmt.setInt(4, rs.getInt("CustomerID"));
					pstmt.setInt(5, rs.getInt("CustomerMeterID"));
					pstmt.setInt(6, rs.getInt("MeterSizeID"));
					pstmt.setString(7, rs.getString("MeterSerialNumber"));
					pstmt.setString(8, rs.getString("CustomerUniqueID"));
					pstmt.setString(9, dataRequestVO.getType() == 1 ? "Water" : dataRequestVO.getType() == 2 ? "Gas" : dataRequestVO.getType() == 3 ? "Energy" : "");
					pstmt.setString(10, dataRequestVO.getSync_time());
					pstmt.setInt(11, dataRequestVO.getSync_interval());
					pstmt.setString(12, dataRequestVO.getPre_post_paid() == 1 ? "Prepaid" : "Postpaid");
					pstmt.setFloat(13, dataRequestVO.getBat_volt());
					pstmt.setInt(14, rs.getInt("TariffID"));
					pstmt.setFloat(15, dataRequestVO.getTariff());
					pstmt.setInt(16, dataRequestVO.getValve_configuration());
					pstmt.setInt(17, dataRequestVO.getPre_post_paid() == 1 ? dataRequestVO.getValve_configuration() : 1);
					pstmt.setFloat(18, dataRequestVO.getCredit());
					pstmt.setFloat(19, dataRequestVO.getEmergency_credit());
					pstmt.setInt(20, dataRequestVO.getDays_elapsed_after_valve_trip());
					pstmt.setFloat(21, dataRequestVO.getReading());
					pstmt.setInt(22, dataRequestVO.getStatus().getDoor_open());
					pstmt.setInt(23, dataRequestVO.getStatus().getMagnetic());
					pstmt.setInt(24, dataRequestVO.getStatus().getSchedule_disconnect());
					pstmt.setInt(25, dataRequestVO.getStatus().getRtc_fault());
					pstmt.setInt(26, dataRequestVO.getStatus().getLow_bat());
					pstmt.setInt(27, dataRequestVO.getStatus().getLow_bal());
					
					if (pstmt.executeUpdate() > 0) {
						
						PreparedStatement pstmt4 = con.prepareStatement("SELECT MAX(ReadingID) as ReadingID FROM balancelog WHERE MIUID = ?");
						pstmt4.setString(1, miuID);
						ResultSet rs2 = pstmt4.executeQuery();
						
						if(rs2.next()) {
							
							PreparedStatement pstmt3 = con.prepareStatement("SELECT * FROM displaybalancelog WHERE MIUID = ? AND CustomerMeterID = " + rs.getInt("CustomerMeterID"));
							pstmt3.setString(1, miuID);
							ResultSet rs1 = pstmt3.executeQuery();
							
							if(rs1.next()) {
								pstmt1 = con.prepareStatement("UPDATE displaybalancelog SET MainBalanceLogID = ?, MIUID = ?, CommunityID = ?, BlockID = ?, CustomerID = ?, CustomerMeterID = ?, MeterSizeID =?, MeterSerialNumber = ?, CustomerUniqueID = ?, MeterType = ?, SyncTime = ?, SyncInterval = ?, PayType = ?, BatteryVoltage = ?, TariffID = ?, Tariff = ?, ValveConfiguration = ?,  ValveStatus = ?, Balance = ?, EmergencyCredit = ?, Minutes = ?, Reading = ?, DoorOpenTamper = ?, MagneticTamper = ?, Vacation = ?, RTCFault = ?, LowBattery = ?, LowBalance = ?, LogDate = NOW() WHERE CustomerMeterID = ? ");
								pstmt1.setInt(1, rs2.getInt("ReadingID"));
								pstmt1.setString(2, miuID);
								pstmt1.setInt(3, rs.getInt("CommunityID"));
								pstmt1.setInt(4, rs.getInt("BlockID"));
								pstmt1.setInt(5, rs.getInt("CustomerID"));
								pstmt1.setInt(6, rs.getInt("CustomerMeterID"));
								pstmt1.setInt(7, rs.getInt("MeterSizeID"));
								pstmt1.setString(8, rs.getString("MeterSerialNumber"));
								pstmt1.setString(9, rs.getString("CustomerUniqueID"));
								pstmt1.setString(10, dataRequestVO.getType() == 1 ? "Water" : dataRequestVO.getType() == 2 ? "Gas" : dataRequestVO.getType() == 3 ? "Energy" : "");
								pstmt1.setString(11, dataRequestVO.getSync_time());
								pstmt1.setInt(12, dataRequestVO.getSync_interval());
								pstmt1.setString(13, dataRequestVO.getPre_post_paid() == 1 ? "Prepaid" : "Postpaid");
								pstmt1.setFloat(14, dataRequestVO.getBat_volt());
								pstmt1.setInt(15, rs.getInt("TariffID"));
								pstmt1.setFloat(16, dataRequestVO.getTariff());
								pstmt1.setInt(17, dataRequestVO.getValve_configuration());
								pstmt1.setInt(18, dataRequestVO.getPre_post_paid() == 1 ? dataRequestVO.getValve_configuration() : 1);
								pstmt1.setFloat(19, dataRequestVO.getCredit());
								pstmt1.setFloat(20, dataRequestVO.getEmergency_credit());
								pstmt1.setInt(21, dataRequestVO.getDays_elapsed_after_valve_trip());
								pstmt1.setFloat(22, dataRequestVO.getReading());
								pstmt1.setInt(23, dataRequestVO.getStatus().getDoor_open());
								pstmt1.setInt(24, dataRequestVO.getStatus().getMagnetic());
								pstmt1.setInt(25, dataRequestVO.getStatus().getSchedule_disconnect());
								pstmt1.setInt(26, dataRequestVO.getStatus().getRtc_fault());
								pstmt1.setInt(27, dataRequestVO.getStatus().getLow_bat());
								pstmt1.setInt(28, dataRequestVO.getStatus().getLow_bal());
								pstmt1.setInt(29, rs.getInt("CustomerMeterID"));
								
							} else {
								
									pstmt1 = con.prepareStatement("INSERT INTO displaybalancelog (MainBalanceLogID, MIUID, CommunityID, BlockID, CustomerID, CustomerMeterID, MeterSizeID, MeterSerialNumber, CustomerUniqueID, MeterType, SyncTime, SyncInterval, PayType, BatteryVoltage, TariffID, Tariff, ValveConfiguration, ValveStatus, Balance, EmergencyCredit, Minutes, Reading, DoorOpenTamper, MagneticTamper, Vacation, RTCFault, LowBattery, LowBalance, LogDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");
									pstmt1.setInt(1, rs2.getInt("ReadingID"));
									pstmt1.setString(2, miuID);
									pstmt1.setInt(3, rs.getInt("CommunityID"));
									pstmt1.setInt(4, rs.getInt("BlockID"));
									pstmt1.setInt(5, rs.getInt("CustomerID"));
									pstmt1.setInt(6, rs.getInt("CustomerMeterID"));
									pstmt1.setInt(7, rs.getInt("MeterSizeID"));
									pstmt1.setString(8, rs.getString("MeterSerialNumber"));
									pstmt1.setString(9, rs.getString("CustomerUniqueID"));
									pstmt1.setString(10, dataRequestVO.getType() == 1 ? "Water" : dataRequestVO.getType() == 2 ? "Gas" : dataRequestVO.getType() == 3 ? "Energy" : "");
									pstmt1.setString(11, dataRequestVO.getSync_time());
									pstmt1.setInt(12, dataRequestVO.getSync_interval());
									pstmt1.setString(13, dataRequestVO.getPre_post_paid() == 1 ? "Prepaid" : "Postpaid");
									pstmt1.setFloat(14, dataRequestVO.getBat_volt());
									pstmt1.setInt(15, rs.getInt("TariffID"));
									pstmt1.setFloat(16, dataRequestVO.getTariff());
									pstmt1.setInt(17, dataRequestVO.getValve_configuration());
									pstmt1.setInt(18, dataRequestVO.getPre_post_paid() == 1 ? dataRequestVO.getValve_configuration() : 1);
									pstmt1.setFloat(19, dataRequestVO.getCredit());
									pstmt1.setFloat(20, dataRequestVO.getEmergency_credit());
									pstmt1.setInt(21, dataRequestVO.getDays_elapsed_after_valve_trip());
									pstmt1.setFloat(22, dataRequestVO.getReading());
									pstmt1.setInt(23, dataRequestVO.getStatus().getDoor_open());
									pstmt1.setInt(24, dataRequestVO.getStatus().getMagnetic());
									pstmt1.setInt(25, dataRequestVO.getStatus().getSchedule_disconnect());
									pstmt1.setInt(26, dataRequestVO.getStatus().getRtc_fault());
									pstmt1.setInt(27, dataRequestVO.getStatus().getLow_bat());
									pstmt1.setInt(28, dataRequestVO.getStatus().getLow_bal());
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
								alertMessage = "The Battery in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is low.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								sendalertmail("Low Battery Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
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
								alertMessage = "There is a <tamper> Tamper at <timestamp>, in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block>.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								alertMessage = alertMessage.replaceAll("<tamper>", dataRequestVO.getStatus().getDoor_open() == 1 ? "Door Open Tamper" : dataRequestVO.getStatus().getMagnetic() == 1 ? "Magnetic Tamper" : "");
								alertMessage = alertMessage.replaceAll("<timestamp>", resultSet.getString("LogDate"));
								sendalertmail("Tamper Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
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
								alertMessage = "Balance in your MIUID: <MIU> with CRN/CAN/UAN: <CRN> is low. Please Recharge.";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								sendalertmail("Low Balance Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(1, alertMessage, resultSet.getString("MIUID"));								
							}
							
						}
						
						if(dataRequestVO.isTopupSMS()) {
							alertMessage = "Your Recharge for MIUID: <MIU> with CRN/CAN/UAN: <CRN> is successful. Available Balance: "+dataRequestVO.getCredit()+"/- and Emergency Credit: "+dataRequestVO.getEmergency_credit()+"/-.";
							alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
							sendalertsms(1, alertMessage, resultSet.getString("MIUID"));
						}
						
						PreparedStatement thresholdAlert = con.prepareStatement("SELECT ((SELECT Reading FROM balancelog WHERE CustomerMeterID = 2 ORDER BY ReadingID DESC LIMIT 0,1) - (SELECT Reading FROM balancelog WHERE CustomerMeterID = 2 ORDER BY ReadingID DESC LIMIT 1,1)) AS Threshold");
						ResultSet thresholdResult = thresholdAlert.executeQuery();
						
						if(thresholdResult.next()) {
							
							if(thresholdResult.getFloat("Threshold") >= rs.getFloat("ThresholdMaximum")) {
								
								alertMessage = "The Consumption in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is above Threshold Value i.e. "+rs.getFloat("ThresholdMaximum")+".";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								sendalertmail("Maximum Threshold Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
								
							} else if(thresholdResult.getFloat("Threshold") <= rs.getFloat("ThresholdMinimum")) {
								
								alertMessage = "The Consumption in MIUID: <MIU> with CRN/CAN/UAN: <CRN>, at H.No: <house>, Community Name: <community>, Block Name: <block> is below Threshold Value i.e. "+rs.getFloat("ThresholdMinimum")+".";
								alertMessage = alertMessage.replaceAll("<MIU>", resultSet.getString("MIUID"));
								sendalertmail("Minimum Threshold Alert!!!", alertMessage, resultSet.getString("MIUID"));
								sendalertsms(0, alertMessage, resultSet.getString("MIUID"));
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
				
				smsRequestVO.setMessage(i == 1 ? "Dear Customer, "+message : "Dear Admin, "+message);
				
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
				mailRequestVO.setFileLocation("NoAttachment");
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
			result = "Failure";
		}
		
		return result;
	}

}
