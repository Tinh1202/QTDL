-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: qtdl
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(50) NOT NULL,
  `password` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('huongnt','abcdef'),('hvan','an123'),('nguyenvana','aaaaa'),('nguyenvanb','bbbbb'),('nguyenvanc','ccccc'),('nguyenvand','ddddd'),('npbinh','binh012'),('nthuy','huy789'),('phuongdt','pword123'),('thanhnv','123456'),('vntinh','tinh456');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id_country` varchar(10) NOT NULL,
  `name_country` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES ('CN','Trung Quốc'),('JP','Nhật Bản'),('USA','Hoa Kỳ'),('VN','Việt Nam');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id_customer` varchar(10) NOT NULL,
  `fullname_customer` varchar(50) DEFAULT NULL,
  `phone_customer` varchar(20) DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `address` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('C001','Trần Văn Bình','0912456789','1988-12-25','Hà Nội'),('C002','Lê Thị Thu Hà','0939876543','1995-07-14','TP HCM');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_note`
--

DROP TABLE IF EXISTS `delivery_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery_note` (
  `id_dn` varchar(10) NOT NULL,
  `id_staff` varchar(10) DEFAULT NULL,
  `id_customer` varchar(10) DEFAULT NULL,
  `date_shipment` datetime DEFAULT NULL,
  PRIMARY KEY (`id_dn`),
  KEY `id_staff` (`id_staff`),
  KEY `id_customer` (`id_customer`),
  CONSTRAINT `delivery_note_ibfk_1` FOREIGN KEY (`id_staff`) REFERENCES `staff` (`id_staff`),
  CONSTRAINT `delivery_note_ibfk_2` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_note`
--

LOCK TABLES `delivery_note` WRITE;
/*!40000 ALTER TABLE `delivery_note` DISABLE KEYS */;
INSERT INTO `delivery_note` VALUES ('DN001','S002','C001','2024-03-01 00:00:00'),('DN002','S003','C002','2024-03-05 00:00:00');
/*!40000 ALTER TABLE `delivery_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_deliverynote`
--

DROP TABLE IF EXISTS `detail_deliverynote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_deliverynote` (
  `id_dn` varchar(10) NOT NULL,
  `id_device` varchar(10) NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_dn`,`id_device`),
  KEY `id_device` (`id_device`),
  CONSTRAINT `detail_deliverynote_ibfk_1` FOREIGN KEY (`id_dn`) REFERENCES `delivery_note` (`id_dn`),
  CONSTRAINT `detail_deliverynote_ibfk_2` FOREIGN KEY (`id_device`) REFERENCES `device` (`id_device`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_deliverynote`
--

LOCK TABLES `detail_deliverynote` WRITE;
/*!40000 ALTER TABLE `detail_deliverynote` DISABLE KEYS */;
INSERT INTO `detail_deliverynote` VALUES ('DN001','D001',2,20000000.00),('DN002','D003',1,25000000.00);
/*!40000 ALTER TABLE `detail_deliverynote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_prn`
--

DROP TABLE IF EXISTS `detail_prn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detail_prn` (
  `id_prn` varchar(10) NOT NULL,
  `id_device` varchar(10) NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_prn`,`id_device`),
  KEY `id_device` (`id_device`),
  CONSTRAINT `detail_prn_ibfk_1` FOREIGN KEY (`id_prn`) REFERENCES `product_receipt` (`id_prn`),
  CONSTRAINT `detail_prn_ibfk_2` FOREIGN KEY (`id_device`) REFERENCES `device` (`id_device`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_prn`
--

LOCK TABLES `detail_prn` WRITE;
/*!40000 ALTER TABLE `detail_prn` DISABLE KEYS */;
INSERT INTO `detail_prn` VALUES ('PRN001','D001',10,20000000.00),('PRN002','D002',5,30000000.00);
/*!40000 ALTER TABLE `detail_prn` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device` (
  `id_device` varchar(10) NOT NULL,
  `name_device` varchar(50) DEFAULT NULL,
  `id_type` varchar(10) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_device`),
  KEY `id_type` (`id_type`),
  CONSTRAINT `device_ibfk_1` FOREIGN KEY (`id_type`) REFERENCES `device_type` (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES ('D001','iPhone 12','DT001',20000000.00),('D002','MacBook Air','DT002',30000000.00),('D003','iPad Pro','DT003',25000000.00),('D004','MacBook Air M1','DT002',18490000.00),('D005','MacBook Air M2','DT002',23990000.00),('D006','MacBook Air M3','DT002',26990000.00);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_type`
--

DROP TABLE IF EXISTS `device_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `device_type` (
  `id_type` varchar(10) NOT NULL,
  `name_type` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_type`
--

LOCK TABLES `device_type` WRITE;
/*!40000 ALTER TABLE `device_type` DISABLE KEYS */;
INSERT INTO `device_type` VALUES ('DT001','Điện thoại'),('DT002','Laptop'),('DT003','Máy tính bảng'),('DT004','Bàn phím'),('DT005','Lót chuột'),('DT006','Tai nghe'),('DT007','Màn hình'),('DT008','Chip');
/*!40000 ALTER TABLE `device_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manufacturer`
--

DROP TABLE IF EXISTS `manufacturer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manufacturer` (
  `id_manuf` varchar(10) NOT NULL,
  `name_manuf` varchar(50) DEFAULT NULL,
  `id_country` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_manuf`),
  KEY `id_country` (`id_country`),
  CONSTRAINT `manufacturer_ibfk_1` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manufacturer`
--

LOCK TABLES `manufacturer` WRITE;
/*!40000 ALTER TABLE `manufacturer` DISABLE KEYS */;
INSERT INTO `manufacturer` VALUES ('M001','Công ty ABC','VN'),('M002','Sony','JP'),('M003','Apple','USA'),('M004','Intel Corporation','USA'),('M005','Qualcomm','USA'),('M006','Micron','USA');
/*!40000 ALTER TABLE `manufacturer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_receipt`
--

DROP TABLE IF EXISTS `product_receipt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_receipt` (
  `id_prn` varchar(10) NOT NULL,
  `date_import` datetime DEFAULT NULL,
  `id_staff` varchar(10) DEFAULT NULL,
  `id_supplier` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_prn`),
  KEY `id_staff` (`id_staff`),
  KEY `id_supplier` (`id_supplier`),
  CONSTRAINT `product_receipt_ibfk_1` FOREIGN KEY (`id_staff`) REFERENCES `staff` (`id_staff`),
  CONSTRAINT `product_receipt_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_receipt`
--

LOCK TABLES `product_receipt` WRITE;
/*!40000 ALTER TABLE `product_receipt` DISABLE KEYS */;
INSERT INTO `product_receipt` VALUES ('PRN001','2024-01-10 00:00:00','S001','SUP001'),('PRN002','2024-02-15 00:00:00','S002','SUP002');
/*!40000 ALTER TABLE `product_receipt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specification`
--

DROP TABLE IF EXISTS `specification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specification` (
  `name_spec` varchar(50) NOT NULL,
  `data_spec` varchar(200) DEFAULT NULL,
  `id_device` varchar(10) NOT NULL,
  PRIMARY KEY (`name_spec`,`id_device`),
  KEY `id_device` (`id_device`),
  CONSTRAINT `specification_ibfk_1` FOREIGN KEY (`id_device`) REFERENCES `device` (`id_device`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specification`
--

LOCK TABLES `specification` WRITE;
/*!40000 ALTER TABLE `specification` DISABLE KEYS */;
INSERT INTO `specification` VALUES ('Bộ nhớ','256GB','D003'),('CPU','M1','D002'),('Màn hình','6.1 inch OLED','D001'),('Màn hình','Retina 13.3','D004'),('Màn hình','13.6 inch 2560 x 1664 pixels Liquid Retina display','D005'),('Màn hình','Liquid Retina 13 inch','D006');
/*!40000 ALTER TABLE `specification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id_staff` varchar(10) NOT NULL,
  `fullname_staff` varchar(50) DEFAULT NULL,
  `phone_staff` varchar(20) DEFAULT NULL,
  `birthDate_staff` date DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_staff`),
  KEY `username` (`username`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`username`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('S001','Nguyễn Văn Thành','0912345678','1985-03-15','Quản lý','thanhnv'),('S002','Nguyễn Thị Hương','0987654321','1990-06-12','Nhân viên bán hàng','huongnt'),('S003','Đỗ Thị Phương','0901234567','1993-08-23','Kế toán','phuongdt'),('S004','Nguyễn Phương Bình','0456789123','2003-03-03','Nhân viên bán hàng','npbinh'),('S005','Võ Ngọc Tính','0234567891','2003-02-02','Nhân viên bán hàng','vntinh'),('S006','Nguyễn Trinh Huy','0345678912','2003-03-03','Nhân viên bán hàng','nthuy'),('S007','Huỳnh Văn An','0123456789','2003-01-01','Nhân viên bán hàng','hvan');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `id_supplier` varchar(20) NOT NULL,
  `name_supplier` varchar(50) DEFAULT NULL,
  `address_supplier` varchar(200) DEFAULT NULL,
  `phone_supplier` varchar(20) DEFAULT NULL,
  `id_country` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_supplier`),
  KEY `id_country` (`id_country`),
  CONSTRAINT `supplier_ibfk_1` FOREIGN KEY (`id_country`) REFERENCES `country` (`id_country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('SUP001','Nhà cung cấp 1','Hà Nội','0241234567','VN'),('SUP002','Nhà cung cấp 2','TP HCM','0289876543','VN'),('SUP003','Nhà cung cấp 3','Tokyo','813123456','JP');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-24 13:14:20
