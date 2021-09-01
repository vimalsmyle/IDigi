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
  `TimeOut` int(11) NOT NULL,
  `PerUnitGasValue` float NOT NULL,
  `PerUnitWaterValue` float NOT NULL,
  `PerUnitEnergyValue` float DEFAULT NULL,
  `ReconnectionCharges` int(11) NOT NULL,
  `LateFee` int(10) NOT NULL,
  `ReconnectionChargeDays` int(10) NOT NULL,
  `DueDayCount` int(10) NOT NULL,
  `BillGenerationDate` varchar(1000) NOT NULL,
  `GST` int(10) NOT NULL,
  `VendorGSTNumber` varchar(100) DEFAULT NULL,
  `CustomerGSTNumber` varchar(100) DEFAULT NULL,
  `RegisteredDate` datetime NOT NULL,
  `ModifiedDate` datetime NOT NULL,
  PRIMARY KEY (`AlertID`),
  KEY `AlertID` (`AlertID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 MAX_ROWS=1;

/*Data for the table `alertsettings` */

insert  into `alertsettings`(`AlertID`,`NoAMRInterval`,`TimeOut`,`PerUnitGasValue`,`PerUnitWaterValue`,`PerUnitEnergyValue`,`ReconnectionCharges`,`LateFee`,`ReconnectionChargeDays`,`DueDayCount`,`BillGenerationDate`,`GST`,`VendorGSTNumber`,`CustomerGSTNumber`,`RegisteredDate`,`ModifiedDate`) values 
(1,2880,330,2.4,1,1,50,50,10,10,'2nd of everymonth',9,'GST1234567890','GST0987654321','2021-05-18 18:52:34','2021-05-18 18:52:37');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
