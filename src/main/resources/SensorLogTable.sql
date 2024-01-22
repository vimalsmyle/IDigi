-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: idigitest
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `sensorlog`
--

DROP TABLE IF EXISTS `sensorlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sensorlog` (
  `ReadingID` int NOT NULL AUTO_INCREMENT,
  `equipment_serial_id` varchar(45) NOT NULL,
  `CommunityID` int NOT NULL,
  `BlockID` int NOT NULL,
  `CustomerID` int NOT NULL,
  `CustomerUniqueID` varchar(45) NOT NULL,
  `readings` int NOT NULL,
  `reader_sensor_status` tinyint NOT NULL,
  `per_day_flow_rate` float NOT NULL,
  `live_flow_rate` float NOT NULL,
  `record_interval` int NOT NULL,
  `sync_interval` int NOT NULL,
  `rssi` int NOT NULL,
  `digital_outputs` tinyint NOT NULL,
  `analog_inputs` float NOT NULL,
  `analog_outputs` float NOT NULL,
  `voltage_outputs` float NOT NULL,
  `battery_percentage` int NOT NULL,
  `online_powersupply` tinyint NOT NULL,
  `gsm_status` tinyint NOT NULL,
  `ethernet_status` tinyint NOT NULL,
  `nfc_status` tinyint NOT NULL,
  `flash_status` tinyint NOT NULL,
  `nfc_memory_status` tinyint NOT NULL,
  `flash_memory_status` tinyint NOT NULL,
  `low_gsm` tinyint NOT NULL,
  `low_battery` tinyint NOT NULL,
  `sensor_detachment` tinyint NOT NULL,
  `door_open_switch` tinyint NOT NULL,
  `magnetic_tamper` tinyint NOT NULL,
  `timestamp` int NOT NULL,
  `LogDate` datetime NOT NULL,
  PRIMARY KEY (`ReadingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sensorlog`
--

LOCK TABLES `sensorlog` WRITE;
/*!40000 ALTER TABLE `sensorlog` DISABLE KEYS */;
/*!40000 ALTER TABLE `sensorlog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-08 16:06:34
