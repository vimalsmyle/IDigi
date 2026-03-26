-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: idigitest
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `prefixparams`
--

DROP TABLE IF EXISTS `prefixparams`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prefixparams` (
  `PrefixID` int NOT NULL,
  `PrefixName` varchar(45) NOT NULL,
  `CommunityID` bigint NOT NULL,
  `BlockID` bigint NOT NULL,
  `MIUID` varchar(45) DEFAULT NULL,
  `MeterType` varchar(45) NOT NULL,
  `MeterSizeID` int NOT NULL,
  `PayType` varchar(45) NOT NULL,
  `TariffID` int NOT NULL,
  `GatewayID` int NOT NULL,
  `ThresholdMaximum` float NOT NULL,
  `ThresholdMinimum` float NOT NULL,
  `RegisteredDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ModifiedDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`PrefixID`),
  UNIQUE KEY `PrefixID_UNIQUE` (`PrefixID`),
  KEY `MeterSizeID_idx` (`MeterSizeID`),
  KEY `TariffID_idx` (`TariffID`),
  KEY `GatewayID_idx` (`GatewayID`),
  KEY `BlockID_idx` (`BlockID`),
  KEY `CommunityID_idx` (`CommunityID`),
  CONSTRAINT `BlockID` FOREIGN KEY (`BlockID`) REFERENCES `block` (`BlockID`),
  CONSTRAINT `CommunityID` FOREIGN KEY (`CommunityID`) REFERENCES `community` (`CommunityID`),
  CONSTRAINT `GatewayID` FOREIGN KEY (`GatewayID`) REFERENCES `gateway` (`GatewayID`),
  CONSTRAINT `MeterSizeID` FOREIGN KEY (`MeterSizeID`) REFERENCES `metersize` (`MeterSizeID`),
  CONSTRAINT `TariffID` FOREIGN KEY (`TariffID`) REFERENCES `tariff` (`TariffID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prefixparams`
--

LOCK TABLES `prefixparams` WRITE;
/*!40000 ALTER TABLE `prefixparams` DISABLE KEYS */;
/*!40000 ALTER TABLE `prefixparams` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-26 16:49:01
