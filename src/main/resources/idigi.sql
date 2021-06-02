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
  `ReconnectionCharges` int(11) NOT NULL,
  `LateFee` int(10) NOT NULL,
  `DueDayCount` int(10) NOT NULL,
  `GST` int(10) NOT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `AlertID` (`AlertID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 MAX_ROWS=1;

/*Data for the table `alertsettings` */

insert  into `alertsettings`(`AlertID`,`NoAMRInterval`,`LowBatteryVoltage`,`TimeOut`,`PerUnitValue`,`ReconnectionCharges`,`LateFee`,`DueDayCount`,`GST`,`RegisteredDate`,`ModifiedDate`) values 
(1,2880,3.00,330,2.4,50,50,10,9,'2021-05-18 18:52:34','2021-05-18 18:52:37');

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
  `MeterType` varchar(100) NOT NULL,
  `SyncTime` varchar(100) NOT NULL,
  `SyncInterval` int(100) NOT NULL,
  `PayType` varchar(100) NOT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `ValveConfiguration` tinyint(2) NOT NULL,
  `ValveStatus` tinyint(2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `TariffID` int(255) DEFAULT NULL,
  `Tariff` decimal(10,2) NOT NULL,
  `EmergencyCredit` decimal(10,2) NOT NULL,
  `Minutes` bigint(255) DEFAULT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `DoorOpenTamper` tinyint(2) DEFAULT NULL,
  `MagneticTamper` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(2) DEFAULT NULL,
  `RTCFault` tinyint(2) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `LowBalance` tinyint(2) DEFAULT NULL,
  `LogDate` datetime NOT NULL,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

/*Data for the table `balancelog` */

insert  into `balancelog`(`ReadingID`,`MIUID`,`CommunityID`,`BlockID`,`CustomerID`,`CustomerMeterID`,`MeterSerialNumber`,`CustomerUniqueID`,`MeterType`,`SyncTime`,`SyncInterval`,`PayType`,`BatteryVoltage`,`ValveConfiguration`,`ValveStatus`,`Balance`,`TariffID`,`Tariff`,`EmergencyCredit`,`Minutes`,`Reading`,`DoorOpenTamper`,`MagneticTamper`,`Vacation`,`RTCFault`,`LowBattery`,`LowBalance`,`LogDate`) values 
(1,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,300.00,2,20.00,40.00,12,1.00,0,0,0,0,0,0,'2021-04-08 16:49:58'),
(2,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,100.00,1,10.00,20.00,12,5.00,0,0,0,0,0,0,'2021-05-18 18:37:12'),
(3,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,10.00,0,0,0,0,0,0,'2021-04-29 19:00:27'),
(4,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,15.00,0,0,0,0,0,0,'2021-05-03 19:00:38'),
(5,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,20.00,0,0,0,0,0,0,'2021-05-18 19:00:44'),
(6,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,25.00,0,0,0,0,0,0,'2021-05-18 19:00:48'),
(7,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,30.00,0,0,0,0,0,0,'2021-05-18 19:00:53'),
(8,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,1000.00,1,10.00,20.00,12,10.00,0,0,0,0,0,0,'2021-05-19 15:01:04'),
(9,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,900.00,1,10.00,20.00,12,12.00,0,0,0,0,0,0,'2021-05-19 17:01:11'),
(10,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,800.00,1,10.00,20.00,12,15.00,0,0,0,0,0,0,'2021-05-20 15:01:15'),
(11,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,700.00,1,10.00,20.00,12,18.00,0,0,0,0,0,0,'2021-05-20 18:01:20'),
(12,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,600.00,1,10.00,20.00,12,24.00,0,0,0,0,0,0,'2021-05-21 15:01:26'),
(13,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,500.00,1,10.00,20.00,12,29.00,0,0,0,0,0,0,'2021-05-21 17:01:31'),
(14,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,400.00,1,10.00,20.00,12,34.00,0,0,0,0,0,0,'2021-05-22 15:01:35'),
(15,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,300.00,1,10.00,20.00,12,38.00,0,0,0,0,0,0,'2021-05-22 18:01:42'),
(16,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,250.00,1,10.00,20.00,12,41.00,0,0,0,0,0,0,'2021-05-23 15:01:47'),
(17,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,150.00,1,10.00,20.00,12,45.00,0,0,0,0,0,0,'2021-05-23 15:30:50'),
(18,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,100.00,1,10.00,20.00,12,50.00,0,0,0,0,0,0,'2021-05-27 15:01:57'),
(19,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,50.00,1,10.00,20.00,12,55.00,0,0,0,0,0,0,'2021-05-27 19:03:16'),
(20,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,35.00,0,0,0,0,0,0,'2021-05-27 20:55:25'),
(21,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,40.00,0,0,0,0,0,0,'2021-05-27 22:14:19'),
(22,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,45.00,0,0,0,0,0,0,'2021-05-27 22:39:14'),
(23,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,1.00,0,0,0,0,0,0,'2021-05-01 00:54:33'),
(24,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,3.00,0,0,0,0,0,0,'2021-05-01 20:54:55'),
(25,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,5.00,0,0,0,0,0,0,'2021-05-03 10:55:02'),
(26,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,8.00,0,0,0,0,0,0,'2021-05-03 20:55:06'),
(27,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,10.00,0,0,0,0,0,0,'2021-05-04 11:55:10'),
(28,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,14.00,0,0,0,0,0,0,'2021-05-04 20:55:27'),
(29,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,19.00,0,0,0,0,0,0,'2021-05-06 12:55:32'),
(30,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,22.00,0,0,0,0,0,0,'2021-05-06 20:55:35'),
(31,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,25.00,0,0,0,0,0,0,'2021-05-10 13:55:40'),
(32,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,29.00,0,0,0,0,0,0,'2021-05-10 20:55:49'),
(33,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,34.00,0,0,0,0,0,0,'2021-05-15 15:57:21'),
(34,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,38.00,0,0,0,0,0,0,'2021-05-15 20:57:26'),
(35,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,41.00,0,0,0,0,0,0,'2021-05-20 17:57:32'),
(36,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,47.00,0,0,0,0,0,0,'2021-05-20 20:57:38'),
(37,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,49.00,0,0,0,0,0,0,'2021-05-25 18:57:44'),
(38,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,53.00,0,0,0,0,0,0,'2021-05-25 20:57:51'),
(39,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,57.00,0,0,0,0,0,0,'2021-05-27 19:57:54'),
(40,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,58.00,0,0,0,0,0,0,'2021-05-27 20:57:58'),
(41,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,59.00,0,0,0,0,0,0,'2021-05-29 20:58:02'),
(42,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,62.00,0,0,0,0,0,0,'2021-06-01 20:58:06');

/*Table structure for table `billingdetails` */

DROP TABLE IF EXISTS `billingdetails`;

CREATE TABLE `billingdetails` (
  `BillingID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CommunityID` int(10) NOT NULL,
  `BlockID` int(10) NOT NULL,
  `CustomerID` bigint(255) NOT NULL,
  `CustomerUniqueID` varchar(500) NOT NULL,
  `CustomerMeterID` bigint(255) NOT NULL,
  `MeterType` varchar(100) NOT NULL,
  `MIUID` varchar(100) NOT NULL,
  `PreviousReading` decimal(10,2) NOT NULL,
  `PresentReading` decimal(10,2) NOT NULL,
  `Consumption` float NOT NULL,
  `TariffID` int(10) NOT NULL,
  `Tariff` float NOT NULL,
  `BillAmount` float NOT NULL,
  `BillMonth` int(10) DEFAULT NULL,
  `BillYear` int(10) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`BillingID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `billingdetails` */

insert  into `billingdetails`(`BillingID`,`CommunityID`,`BlockID`,`CustomerID`,`CustomerUniqueID`,`CustomerMeterID`,`MeterType`,`MIUID`,`PreviousReading`,`PresentReading`,`Consumption`,`TariffID`,`Tariff`,`BillAmount`,`BillMonth`,`BillYear`,`LogDate`,`ModifiedDate`) values 
(1,1,1,1,'IDIGI1',2,'Water','2233',1.00,10.00,9,2,20,180,4,2021,'2021-05-22 19:27:50',NULL);

/*Table structure for table `billingpaymentdetails` */

DROP TABLE IF EXISTS `billingpaymentdetails`;

CREATE TABLE `billingpaymentdetails` (
  `TransactionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CustomerBillingID` bigint(255) NOT NULL,
  `CustomerID` bigint(255) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `TotalAmount` float NOT NULL,
  `LateFee` float DEFAULT NULL,
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
  `TransactionDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `AcknowledgeDate` datetime DEFAULT NULL,
  PRIMARY KEY (`TransactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `billingpaymentdetails` */

insert  into `billingpaymentdetails`(`TransactionID`,`CustomerBillingID`,`CustomerID`,`CustomerUniqueID`,`TotalAmount`,`LateFee`,`Source`,`ModeOfPayment`,`PaymentStatus`,`RazorPayOrderID`,`RazorPayPaymentID`,`RazorPaySignature`,`ErrorResponse`,`RazorPayRefundID`,`RazorPayRefundStatus`,`RazorPayRefundEntity`,`CreatedByID`,`CreatedByRoleID`,`TransactionDate`,`AcknowledgeDate`) values 
(1,1,1,'IDIGI1',212.4,0,'web','cash',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2,2,'2021-05-22 23:45:19','2021-05-22 23:45:17');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `block` */

insert  into `block`(`BlockID`,`BlockName`,`Location`,`MobileNumber`,`Email`,`CommunityID`,`CreatedByID`,`CreatedByRoleID`,`CreatedDate`,`ModifiedDate`) values 
(1,'idigiblock','secunderabad','8498890001','vimal_smyle2006@yahoo.com',1,1,1,'2021-05-15 23:23:08','2021-05-15 23:24:59');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `command` */

insert  into `command`(`TransactionID`,`CustomerID`,`CustomerMeterID`,`MIUID`,`CustomerUniqueID`,`CreatedDate`) values 
(2,1,1,'1122','IDIGI1','2021-05-23 15:13:25'),
(3,1,1,'1122','IDIGI1','2021-05-23 15:20:58');

/*Table structure for table `commanddetails` */

DROP TABLE IF EXISTS `commanddetails`;

CREATE TABLE `commanddetails` (
  `CommandDetailsID` bigint(255) NOT NULL AUTO_INCREMENT,
  `TransactionID` bigint(255) NOT NULL,
  `CommandType` int(50) NOT NULL,
  `Value` varchar(500) NOT NULL,
  `Status` tinyint(5) NOT NULL DEFAULT '10',
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CommandDetailsID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `commanddetails` */

insert  into `commanddetails`(`CommandDetailsID`,`TransactionID`,`CommandType`,`Value`,`Status`,`RegisteredDate`,`ModifiedDate`) values 
(1,2,3,'2',0,'2021-05-23 15:15:22','2021-05-23 15:15:22'),
(2,3,3,'1',10,'2021-05-23 15:21:09','2021-05-23 15:21:09');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `community` */

insert  into `community`(`CommunityID`,`CommunityName`,`Email`,`MobileNumber`,`Address`,`CreatedDate`,`ModifiedDate`) values 
(1,'Idigi','vimal_smyle2006@yahoo.com','8498890000','hyderabad','2021-05-15 23:21:57',NULL);

/*Table structure for table `customerbillingdetails` */

DROP TABLE IF EXISTS `customerbillingdetails`;

CREATE TABLE `customerbillingdetails` (
  `CustomerBillingID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CommunityID` int(100) NOT NULL,
  `BlockID` int(100) NOT NULL,
  `CustomerID` bigint(255) NOT NULL,
  `CustomerUniqueID` varchar(500) NOT NULL,
  `TotalAmount` float NOT NULL,
  `TaxAmount` float NOT NULL,
  `TotalConsumption` int(100) NOT NULL,
  `PreviousDues` float DEFAULT NULL,
  `Status` tinyint(5) NOT NULL DEFAULT '0',
  `DueDate` date NOT NULL,
  `BillMonth` int(11) DEFAULT NULL,
  `BillYear` int(11) DEFAULT NULL,
  `LogDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime DEFAULT NULL,
  PRIMARY KEY (`CustomerBillingID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `customerbillingdetails` */

insert  into `customerbillingdetails`(`CustomerBillingID`,`CommunityID`,`BlockID`,`CustomerID`,`CustomerUniqueID`,`TotalAmount`,`TaxAmount`,`TotalConsumption`,`PreviousDues`,`Status`,`DueDate`,`BillMonth`,`BillYear`,`LogDate`,`ModifiedDate`) values 
(1,1,1,1,'IDIGI1',180,32.4,9,NULL,0,'2021-06-01',4,2021,'2021-05-22 19:34:29','2021-05-22 19:34:29');

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
  `HouseNumber` varchar(50) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `customerdetails` */

insert  into `customerdetails`(`CustomerID`,`CommunityID`,`BlockID`,`HouseNumber`,`FirstName`,`LastName`,`Email`,`MobileNumber`,`ActiveStatus`,`CustomerUniqueID`,`CreatedByID`,`CreatedByRoleID`,`RegistrationDate`,`ModifiedDate`) values 
(1,1,1,'101','Vimal','Kumar','kvk9889@gmail.com','8498890000',1,'IDIGI1',2,2,'2021-05-17 23:20:44','2021-05-18 14:26:45'),
(2,1,1,'102','vml','kvk','vimal_smyle2006@yahoo.com','8498890000',1,'IDIGI2',1,1,'2021-05-23 16:30:27','2021-05-23 16:30:30'),
(4,1,1,'1003','bharat','sriram','bhrtsriram@gmail.com','9000941911',1,'IDIGI3',1,1,'2021-05-26 15:49:40','2021-05-26 15:49:40');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `customermeterdetails` */

insert  into `customermeterdetails`(`CustomerMeterID`,`CustomerID`,`CustomerUniqueID`,`MIUID`,`MeterSerialNumber`,`MeterType`,`MeterSize`,`PayType`,`TariffID`,`GatewayID`,`Location`,`RegisteredDate`,`ModifiedDate`) values 
(1,1,'IDIGI1','1122','11223344','Gas',NULL,'Prepaid',1,1,'Kitchen','2021-05-17 23:22:24','2021-05-17 23:24:13'),
(2,1,'IDIGI1','2233','22334455','Water',NULL,'Postpaid',2,1,'Bathroom','2021-05-17 23:24:09','2021-05-17 23:24:17'),
(3,1,'IDIGI1','3344','33445566','Water',NULL,'Postpaid',3,1,'Bedroom','2021-05-17 23:25:15','2021-05-17 23:25:13'),
(4,1,'IDIGI1','4455','44556677','Water',NULL,'Postpaid',2,1,'Washarea','2021-05-17 23:26:11','2021-05-17 23:26:10'),
(5,2,'IDIGI2','5566','55667788','Gas',NULL,'Prepaid',1,1,'Kitchen','2021-05-23 16:31:15','2021-05-23 16:31:18'),
(6,4,'IDIGI3','B6677','B66778899','Gas',1000,'Prepaid',1,1,'Kitchen','2021-05-26 15:50:17','2021-05-26 15:50:17');

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
  `MeterType` varchar(100) NOT NULL,
  `SyncTime` varchar(100) NOT NULL,
  `SyncInterval` int(100) NOT NULL,
  `PayType` varchar(100) NOT NULL,
  `BatteryVoltage` decimal(10,2) NOT NULL,
  `ValveConfiguration` tinyint(2) NOT NULL,
  `ValveStatus` tinyint(2) NOT NULL,
  `Balance` decimal(10,2) NOT NULL,
  `TariffID` int(255) DEFAULT NULL,
  `Tariff` decimal(10,2) NOT NULL,
  `EmergencyCredit` decimal(10,2) NOT NULL,
  `Minutes` bigint(255) DEFAULT NULL,
  `Reading` decimal(10,2) NOT NULL,
  `DoorOpenTamper` tinyint(2) DEFAULT NULL,
  `MagneticTamper` tinyint(2) DEFAULT NULL,
  `Vacation` tinyint(2) DEFAULT NULL,
  `RTCFault` tinyint(2) DEFAULT NULL,
  `LowBattery` tinyint(2) DEFAULT NULL,
  `LowBalance` tinyint(2) DEFAULT NULL,
  `LogDate` datetime NOT NULL,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `displaybalancelog` */

insert  into `displaybalancelog`(`ReadingID`,`MainBalanceLogID`,`MIUID`,`CommunityID`,`BlockID`,`CustomerID`,`CustomerMeterID`,`MeterSerialNumber`,`CustomerUniqueID`,`MeterType`,`SyncTime`,`SyncInterval`,`PayType`,`BatteryVoltage`,`ValveConfiguration`,`ValveStatus`,`Balance`,`TariffID`,`Tariff`,`EmergencyCredit`,`Minutes`,`Reading`,`DoorOpenTamper`,`MagneticTamper`,`Vacation`,`RTCFault`,`LowBattery`,`LowBalance`,`LogDate`) values 
(1,22,'2233',1,1,1,2,'22334455','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,2,20.00,40.00,10,45.00,0,0,0,0,0,0,'2021-05-27 22:39:14'),
(2,19,'1122',1,1,1,1,'11223344','IDIGI1','Gas','00:00:05',1440,'Prepaid',90.00,1,1,50.00,1,10.00,20.00,12,55.00,0,0,0,0,0,0,'2021-05-27 15:03:16'),
(3,42,'3344',1,1,1,3,'33445566','IDIGI1','Water','00:00:05',1440,'Postpaid',90.00,1,1,0.00,3,20.00,40.00,10,62.00,0,0,0,0,0,0,'2021-06-01 20:58:06');

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

/*Table structure for table `gateway` */

DROP TABLE IF EXISTS `gateway`;

CREATE TABLE `gateway` (
  `GatewayID` int(11) NOT NULL AUTO_INCREMENT,
  `GatewayName` varchar(100) DEFAULT NULL,
  `GatewaySerialNumber` varchar(100) NOT NULL,
  `GatewayIP` varchar(100) NOT NULL,
  `GatewayPort` int(10) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`GatewayID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `gateway` */

insert  into `gateway`(`GatewayID`,`GatewayName`,`GatewaySerialNumber`,`GatewayIP`,`GatewayPort`,`RegisteredDate`,`ModifiedDate`) values 
(1,'Gateway1','123','123.123.123.123',1234,'2021-05-17 23:23:10','2021-05-17 23:23:12');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tariff` */

insert  into `tariff`(`TariffID`,`Tariff`,`TariffName`,`EmergencyCredit`,`AlarmCredit`,`FixedCharges`,`RegisteredDate`,`ModifiedDate`) values 
(1,10,'tariff1',20,30,50,'2021-05-17 23:26:58','2021-05-17 23:27:01'),
(2,20,'tariff2',40,50,100,'2021-05-17 23:27:23','2021-05-17 23:27:24'),
(3,15,'tariff3',30,40,70,'2021-05-17 23:27:55','2021-05-17 23:27:57');

/*Table structure for table `topup` */

DROP TABLE IF EXISTS `topup`;

CREATE TABLE `topup` (
  `TransactionID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` bigint(20) NOT NULL,
  `MIUID` varchar(50) NOT NULL,
  `CustomerMeterID` bigint(255) NOT NULL,
  `TariffID` int(11) NOT NULL,
  `Amount` decimal(10,0) NOT NULL,
  `Status` int(4) unsigned NOT NULL DEFAULT '10',
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`ID`,`UserID`,`UserName`,`UserPassword`,`RoleID`,`ActiveStatus`,`CommunityID`,`BlockID`,`CustomerID`,`CustomerUniqueID`,`CreatedByID`,`CreatedByRoleID`,`RegisteredDate`,`ModifiedDate`) values 
(1,'Superadmin','Idigitronics','cvp/LzpadrQT+2k0WDjyOQ==',1,1,0,0,0,NULL,0,0,'2021-05-01 17:19:40','2021-05-01 17:19:37'),
(2,'kvkadmin','Kvk','cvp/LzpadrQT+2k0WDjyOQ==',2,1,1,1,0,NULL,1,1,'2021-05-01 17:24:22','2021-05-01 17:24:12'),
(3,'IDIGI1','Vimal Kumar','cvp/LzpadrQT+2k0WDjyOQ==',3,1,1,1,1,'IDIGI1',2,2,'2021-05-01 17:25:54','2021-05-01 17:25:56'),
(4,'Superadminsupervisor','Idigisuperadminsupervisor','cvp/LzpadrQT+2k0WDjyOQ==',4,1,0,0,0,NULL,1,1,'2021-05-01 17:26:45','2021-05-01 17:26:48'),
(5,'Adminsupervisor','Kvkadminsupervisor','cvp/LzpadrQT+2k0WDjyOQ==',5,1,1,1,0,NULL,1,1,'2021-05-01 17:28:26','2021-05-01 17:28:29'),
(6,'idigiblock','idigiblock','yotlEHVjsFQOtJ1njm6kXQ==',2,1,1,1,0,'NULL',1,1,'2021-05-15 23:23:08','2021-05-15 23:23:08'),
(7,'IDIGI2','vml kvk','cvp/LzpadrQT+2k0WDjyOQ==',3,1,1,1,2,'IDIGI2',1,1,'2021-05-23 16:46:19','2021-05-23 16:46:22'),
(8,'IDIGI3','bharat sriram','Ysgo3gABsnRMLWt4mO5WNg==',3,1,1,1,4,'IDIGI3',1,1,'2021-05-26 15:51:17','2021-05-26 15:51:17');

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
  `VacationID` bigint(255) NOT NULL AUTO_INCREMENT,
  `CommunityID` int(11) NOT NULL,
  `BlockID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `MIUID` varchar(20) NOT NULL,
  `CustomerMeterID` bigint(255) NOT NULL,
  `VacationName` varchar(300) DEFAULT NULL,
  `StartDate` datetime NOT NULL,
  `EndDate` datetime NOT NULL,
  `Status` tinyint(4) NOT NULL DEFAULT '10',
  `Source` varchar(10) NOT NULL,
  `CustomerUniqueID` varchar(100) NOT NULL,
  `mode` varchar(100) NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`VacationID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `vacation` */

insert  into `vacation`(`VacationID`,`CommunityID`,`BlockID`,`CustomerID`,`MIUID`,`CustomerMeterID`,`VacationName`,`StartDate`,`EndDate`,`Status`,`Source`,`CustomerUniqueID`,`mode`,`RegisteredDate`,`ModifiedDate`) values 
(1,1,1,1,'1122',1,'summer','2021-05-22 16:54:13','2021-05-24 16:54:25',0,'web','IDIGI1','add','2021-05-23 16:54:48','2021-05-23 16:54:46'),
(2,1,1,1,'2233',2,'winter','2021-05-27 16:56:28','2021-05-31 16:56:34',0,'web','IDIGI1','add','2021-05-23 16:56:53','2021-05-23 16:56:56');

/*Table structure for table `customermeterdetailsview` */

DROP TABLE IF EXISTS `customermeterdetailsview`;

/*!50001 DROP VIEW IF EXISTS `customermeterdetailsview` */;
/*!50001 DROP TABLE IF EXISTS `customermeterdetailsview` */;

/*!50001 CREATE TABLE  `customermeterdetailsview`(
 `CommunityName` varchar(100) ,
 `BlockName` varchar(80) ,
 `CustomerID` int(255) ,
 `HouseNumber` varchar(50) ,
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
