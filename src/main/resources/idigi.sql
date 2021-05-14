/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.5.62 : Database - idigi
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`idigi` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `idigi`;

/*Table structure for table `alertsettings` */

DROP TABLE IF EXISTS `alertsettings`;

CREATE TABLE `alertsettings` (
  `AlertID` int(11) NOT NULL AUTO_INCREMENT,
  `NoAMRInterval` bigint(255) NOT NULL,
  `LowBatteryVoltage` decimal(10,2) NOT NULL,
  `TimeOut` int(11) NOT NULL,
  `PerUnitValue` float NOT NULL,
  `ReconnectionCharges` int(11) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `AlertID` (`AlertID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 MAX_ROWS=1;

/*Data for the table `alertsettings` */

/*Table structure for table `balancelog` */

DROP TABLE IF EXISTS `balancelog`;

CREATE TABLE `balancelog` (
  `ReadingID` bigint(255) NOT NULL AUTO_INCREMENT,
  `MIUID` varchar(100) NOT NULL,
  `CommunityID` int(11) DEFAULT NULL,
  `BlockID` int(11) DEFAULT NULL,
  `CustomerID` bigint(255) DEFAULT NULL,
  `CustomerMeterID` bigint(255) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CustomerUniqueID` varchar(100) DEFAULT NULL,
  `MeterType` int(255) NOT NULL,
  `SyncTime` varchar(100) NOT NULL,
  `SyncInterval` int(100) NOT NULL,
  `PayType` int(100) NOT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `ValveConfiguration` tinyint(2) NOT NULL,
  `ValveStatus` tinyint(2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `TariffID` int(255) DEFAULT NULL,
  `Tariff` decimal(10,2) NOT NULL,
  `EmergencyCredit` decimal(10,2) NOT NULL,
  `Minutes` bigint(255) DEFAULT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `TamperDetect` tinyint(2) DEFAULT NULL,
  `TimeStamp` varchar(80) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `balancelog` */

/*Table structure for table `block` */

DROP TABLE IF EXISTS `block`;

CREATE TABLE `block` (
  `BlockID` bigint(255) NOT NULL AUTO_INCREMENT,
  `BlockName` varchar(80) NOT NULL,
  `Location` varchar(300) DEFAULT NULL,
  `MobileNumber` varchar(10) DEFAULT NULL,
  `Email` varchar(300) DEFAULT NULL,
  `CommunityID` int(255) NOT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`BlockID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `block` */

/*Table structure for table `command` */

DROP TABLE IF EXISTS `command`;

CREATE TABLE `command` (
  `TransactionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CustomerID` bigint(255) NOT NULL,
  `CustomerMeterID` bigint(255) NOT NULL,
  `MIUID` varchar(80) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `CreatedDate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `command` */

/*Table structure for table `commanddetails` */

DROP TABLE IF EXISTS `commanddetails`;

CREATE TABLE `commanddetails` (
  `CommandDetailsID` bigint(255) NOT NULL AUTO_INCREMENT,
  `TransactionID` bigint(255) NOT NULL,
  `CommandType` int(50) NOT NULL,
  `Value` varchar(500) NOT NULL,
  `Status` tinyint(5) NOT NULL DEFAULT '0',
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CommandDetailsID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `commanddetails` */

/*Table structure for table `community` */

DROP TABLE IF EXISTS `community`;

CREATE TABLE `community` (
  `CommunityID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CommunityName` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `Address` varchar(300) NOT NULL,
  `CreatedDate` datetime NOT NULL,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CommunityID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `community` */

/*Table structure for table `customerdeletedetails` */

DROP TABLE IF EXISTS `customerdeletedetails`;

CREATE TABLE `customerdeletedetails` (
  `CustomerDeleteID` int(255) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(255) NOT NULL,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(255) NOT NULL,
  `HouseNumber` varchar(30) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ActiveStatus` tinyint(4) DEFAULT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerDeleteID`,`CustomerUniqueID`),
  UNIQUE KEY `CRNNumber` (`CustomerUniqueID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customerdeletedetails` */

/*Table structure for table `customerdeletemeter` */

DROP TABLE IF EXISTS `customerdeletemeter`;

CREATE TABLE `customerdeletemeter` (
  `CustomerMeterDeleteID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CustomerMeterID` bigint(20) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `MeterSerialNumber` varchar(100) NOT NULL,
  `MeterType` varchar(12) NOT NULL,
  `MeterSize` int(11) DEFAULT NULL,
  `PayType` varchar(10) NOT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`CustomerMeterDeleteID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customerdeletemeter` */

/*Table structure for table `customerdetails` */

DROP TABLE IF EXISTS `customerdetails`;

CREATE TABLE `customerdetails` (
  `CustomerID` int(255) NOT NULL AUTO_INCREMENT,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(255) NOT NULL,
  `HouseNumber` varchar(30) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `LastName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ActiveStatus` tinyint(4) DEFAULT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerID`,`CustomerUniqueID`),
  UNIQUE KEY `CRNNumber` (`CustomerUniqueID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customerdetails` */

/*Table structure for table `customermeterdetails` */

DROP TABLE IF EXISTS `customermeterdetails`;

CREATE TABLE `customermeterdetails` (
  `CustomerMeterID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CustomerID` bigint(255) NOT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `MeterSerialNumber` varchar(100) NOT NULL,
  `MeterType` varchar(12) NOT NULL,
  `MeterSize` int(11) DEFAULT NULL,
  `PayType` varchar(10) NOT NULL,
  `TariffID` int(255) NOT NULL,
  `GatewayID` int(255) NOT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`CustomerMeterID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `customermeterdetails` */

/*Table structure for table `displaybalancelog` */

DROP TABLE IF EXISTS `displaybalancelog`;

CREATE TABLE `displaybalancelog` (
  `ReadingID` bigint(255) NOT NULL AUTO_INCREMENT,
  `MainBalanceLogID` bigint(255) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `CommunityID` int(11) DEFAULT NULL,
  `BlockID` int(11) DEFAULT NULL,
  `CustomerID` bigint(255) DEFAULT NULL,
  `CustomerMeterID` bigint(255) DEFAULT NULL,
  `MeterSerialNumber` varchar(100) DEFAULT NULL,
  `CustomerUniqueID` varchar(100) DEFAULT NULL,
  `MeterType` int(255) NOT NULL,
  `SyncTime` varchar(100) NOT NULL,
  `SyncInterval` int(100) NOT NULL,
  `PayType` int(100) NOT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `ValveConfiguration` tinyint(2) NOT NULL,
  `ValveStatus` tinyint(2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `TariffID` int(10) DEFAULT NULL,
  `Tariff` decimal(10,2) NOT NULL,
  `EmergencyCredit` decimal(10,2) NOT NULL,
  `Minutes` bigint(255) DEFAULT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `TamperDetect` tinyint(2) NOT NULL,
  `TimeStamp` varchar(80) NOT NULL,
  `LogDate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `displaybalancelog` */

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `FeedbackID` bigint(20) NOT NULL AUTO_INCREMENT,
  `Feedback` varchar(200) DEFAULT NULL,
  `Description` varchar(2000) DEFAULT NULL,
  `Status` int(1) NOT NULL DEFAULT '0',
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `CustomerUniqueID` varchar(50) NOT NULL,
  `MeterID` varchar(20) NOT NULL,
  `Remarks` varchar(2000) DEFAULT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`FeedbackID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `feedback` */

/*Table structure for table `gateways` */

DROP TABLE IF EXISTS `gateways`;

CREATE TABLE `gateways` (
  `GatewayID` int(11) NOT NULL AUTO_INCREMENT,
  `GatewayName` varchar(100) DEFAULT NULL,
  `GatewaySerialNumber` varchar(100) NOT NULL,
  `GatewayIP` varchar(100) NOT NULL,
  `GatewayPort` int(10) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GatewayID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `gateways` */

/*Table structure for table `mailsettings` */

DROP TABLE IF EXISTS `mailsettings`;

CREATE TABLE `mailsettings` (
  `Host` varchar(50) NOT NULL,
  `User` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `port` varchar(50) NOT NULL,
  `FromMail` varchar(50) NOT NULL,
  `ToMail` varchar(50) NOT NULL,
  `Subject` varchar(60) NOT NULL,
  `Text` varchar(60) NOT NULL,
  `CommunityID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `mailsettings` */

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `Sno` int(11) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bank_trans_id` varchar(50) DEFAULT NULL,
  `card_brand` varchar(50) DEFAULT NULL,
  `card_issuer` varchar(50) DEFAULT NULL,
  `card_issuer_country` varchar(50) DEFAULT NULL,
  `card_issuer_country_code` varchar(50) DEFAULT NULL,
  `card_no` varchar(50) DEFAULT NULL,
  `card_type` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `store_amount` double DEFAULT NULL,
  `store_id` varchar(50) DEFAULT NULL,
  `trans_date` datetime DEFAULT NULL,
  `trans_id` varchar(50) DEFAULT NULL,
  `val_id` varchar(50) DEFAULT NULL,
  `verify_sign` varchar(50) DEFAULT NULL,
  `verify_key` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`Sno`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

/*Table structure for table `tariff` */

DROP TABLE IF EXISTS `tariff`;

CREATE TABLE `tariff` (
  `TariffID` int(11) NOT NULL AUTO_INCREMENT,
  `Tariff` float NOT NULL,
  `TariffName` varchar(100) NOT NULL,
  `EmergencyCredit` float DEFAULT NULL,
  `AlarmCredit` float DEFAULT NULL,
  `FixedCharges` float DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TariffID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tariff` */

/*Table structure for table `topup` */

DROP TABLE IF EXISTS `topup`;

CREATE TABLE `topup` (
  `TransactionID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) unsigned NOT NULL DEFAULT '0',
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `MeterID` varchar(50) NOT NULL,
  `TariffID` int(11) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Status` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `FixedCharges` int(10) unsigned DEFAULT '0',
  `ReconnectionCharges` int(10) unsigned DEFAULT '0',
  `Source` varchar(10) NOT NULL,
  `ModeOfPayment` varchar(50) NOT NULL,
  `PaymentStatus` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `RazorPayOrderID` varchar(50) DEFAULT NULL,
  `RazorPayPaymentID` varchar(50) DEFAULT NULL,
  `RazorPaySignature` varchar(5000) DEFAULT NULL,
  `ErrorResponse` varchar(10000) DEFAULT NULL,
  `RazorPayRefundID` varchar(50) DEFAULT NULL,
  `RazorPayRefundStatus` varchar(50) DEFAULT NULL,
  `RazorPayRefundEntity` varchar(10000) DEFAULT NULL,
  `CreatedByID` int(11) NOT NULL,
  `CreatedByRoleID` int(11) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime NOT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `topup` */

/*Table structure for table `updaterequestcustomermeterdetails` */

DROP TABLE IF EXISTS `updaterequestcustomermeterdetails`;

CREATE TABLE `updaterequestcustomermeterdetails` (
  `RequestID` bigint(255) NOT NULL AUTO_INCREMENT,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(20) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `MobileNumber` varchar(10) NOT NULL,
  `ToBeApprovedByID` int(11) NOT NULL,
  `RegistrationDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`RequestID`),
  UNIQUE KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `updaterequestcustomermeterdetails_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customerdetails` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `updaterequestcustomermeterdetails` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` varchar(100) DEFAULT NULL,
  `UserName` varchar(100) NOT NULL,
  `UserPassword` varchar(100) NOT NULL,
  `RoleID` int(11) NOT NULL,
  `ActiveStatus` int(11) NOT NULL,
  `CommunityID` bigint(11) NOT NULL,
  `BlockID` bigint(20) DEFAULT NULL,
  `CustomerID` bigint(20) DEFAULT NULL,
  `CustomerUniqueID` varchar(100) DEFAULT NULL,
  `CreatedByID` bigint(20) NOT NULL,
  `CreatedByRoleID` tinyint(4) NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CommunityID` (`CommunityID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`UserID`,`UserName`,`UserPassword`,`RoleID`,`ActiveStatus`,`CommunityID`,`BlockID`,`CustomerID`,`CustomerUniqueID`,`CreatedByID`,`CreatedByRoleID`,`RegisteredDate`,`ModifiedDate`) values 
(1,'Superadmin','Idigitronics','cvp/LzpadrQT+2k0WDjyOQ==',1,1,0,0,0,NULL,0,0,'2021-05-01 17:19:40','2021-05-01 17:19:37'),
(2,'kvkadmin','Kvk\r\n','cvp/LzpadrQT+2k0WDjyOQ==\r\n',2,1,1,1,0,NULL,1,1,'2021-05-01 17:24:22','2021-05-01 17:24:12'),
(3,'Vimal\r\n','Vimal Kumar\r\n','cvp/LzpadrQT+2k0WDjyOQ==\r\n',3,1,1,1,1,'IDIGI1',2,2,'2021-05-01 17:25:54','2021-05-01 17:25:56'),
(4,'Superadminsupervisor\r\n','HanbitSuperadminsupervisor\r\n','cvp/LzpadrQT+2k0WDjyOQ==\r\n',4,1,0,0,0,NULL,1,1,'2021-05-01 17:26:45','2021-05-01 17:26:48'),
(5,'Adminsupervisor\r\n','KvkAdminsupervisor\r\n','cvp/LzpadrQT+2k0WDjyOQ==\r\n',5,1,1,1,0,NULL,1,1,'2021-05-01 17:28:26','2021-05-01 17:28:29');

/*Table structure for table `userrole` */

DROP TABLE IF EXISTS `userrole`;

CREATE TABLE `userrole` (
  `RoleID` int(11) NOT NULL,
  `RoleDescription` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userrole` */

insert  into `userrole`(`RoleID`,`RoleDescription`) values 
(1,'Super Admin'),
(2,'Admin'),
(3,'User'),
(4,'SuperAdminSupervisor'),
(5,'AdminSupervisor'),
(6,'Support');

/*Table structure for table `vacation` */

DROP TABLE IF EXISTS `vacation`;

CREATE TABLE `vacation` (
  `VacationID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TataReferenceNumber` bigint(20) NOT NULL,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `MeterID` varchar(20) NOT NULL,
  `VacationName` varchar(300) DEFAULT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Status` tinyint(4) NOT NULL,
  `Source` varchar(10) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `mode` varchar(100) NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`VacationID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `vacation` */

/*Table structure for table `customermeterdetailsview` */

DROP TABLE IF EXISTS `customermeterdetailsview`;

/*!50001 DROP VIEW IF EXISTS `customermeterdetailsview` */;
/*!50001 DROP TABLE IF EXISTS `customermeterdetailsview` */;

/*!50001 CREATE TABLE  `customermeterdetailsview`(
 `CommunityName` varchar(100) ,
 `BlockName` varchar(80) ,
 `CustomerID` int(255) ,
 `HouseNumber` varchar(30) ,
 `FirstName` varchar(50) ,
 `LastName` varchar(50) ,
 `Email` varchar(100) ,
 `MobileNumber` varchar(10) ,
 `MIUID` varchar(100) ,
 `MeterSerialNumber` varchar(100) ,
 `CustomerUniqueID` varchar(50) ,
 `ModifiedDate` datetime ,
 `CreatedByID` int(11) ,
 `CreatedByRoleID` tinyint(4) 
)*/;

/*View structure for view customermeterdetailsview */

/*!50001 DROP TABLE IF EXISTS `customermeterdetailsview` */;
/*!50001 DROP VIEW IF EXISTS `customermeterdetailsview` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `customermeterdetailsview` AS (select `community`.`CommunityName` AS `CommunityName`,`block`.`BlockName` AS `BlockName`,`customerdetails`.`CustomerID` AS `CustomerID`,`customerdetails`.`HouseNumber` AS `HouseNumber`,`customerdetails`.`FirstName` AS `FirstName`,`customerdetails`.`LastName` AS `LastName`,`customerdetails`.`Email` AS `Email`,`customerdetails`.`MobileNumber` AS `MobileNumber`,`customermeterdetails`.`MIUID` AS `MIUID`,`customermeterdetails`.`MeterSerialNumber` AS `MeterSerialNumber`,`customerdetails`.`CustomerUniqueID` AS `CustomerUniqueID`,`customerdetails`.`ModifiedDate` AS `ModifiedDate`,`customerdetails`.`CreatedByID` AS `CreatedByID`,`customerdetails`.`CreatedByRoleID` AS `CreatedByRoleID` from (((`customerdetails` left join `community` on((`community`.`CommunityID` = `customerdetails`.`CommunityID`))) left join `block` on((`block`.`BlockID` = `customerdetails`.`BlockID`))) left join `customermeterdetails` on((`customermeterdetails`.`CustomerID` = `customerdetails`.`CustomerID`))) order by `customerdetails`.`CustomerID` desc) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
