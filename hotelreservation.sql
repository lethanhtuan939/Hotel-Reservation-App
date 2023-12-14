-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotelreservation
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `favorites`
--

DROP TABLE IF EXISTS `favorites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favorites` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqa4ycc3qde1un6g79ycq24766` (`hotel_id`),
  KEY `FKk7du8b8ewipawnnpg76d55fus` (`user_id`),
  CONSTRAINT `FKk7du8b8ewipawnnpg76d55fus` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKqa4ycc3qde1un6g79ycq24766` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorites`
--

LOCK TABLES `favorites` WRITE;
/*!40000 ALTER TABLE `favorites` DISABLE KEYS */;
INSERT INTO `favorites` VALUES (19,1100,2),(21,1900,2),(24,1400,2),(26,1903,2),(27,1205,2),(28,1200,2);
/*!40000 ALTER TABLE `favorites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotels`
--

DROP TABLE IF EXISTS `hotels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotels` (
  `id` int NOT NULL,
  `active` bit(1) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  `acreage` double NOT NULL,
  `rating` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqs8u4n6x2f5anae9lllt3857p` (`location_id`),
  CONSTRAINT `FKqs8u4n6x2f5anae9lllt3857p` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotels`
--

LOCK TABLES `hotels` WRITE;
/*!40000 ALTER TABLE `hotels` DISABLE KEYS */;
INSERT INTO `hotels` VALUES (1100,_binary '','327d51aa0746408a8262dbd1b1ca1b5f.jpg','Royal Hotel',7000,214,5),(1111,_binary '','310f941e1aa54c44aeec18046e949ec1.jpg','Quang Luan Hotel',7020,215,4.5),(1200,_binary '','a01b7e762372429aa8a90511a2a9276c.jpg','Sheraton Hanoi Hotel',7000,220,5),(1203,_binary '','a157c8c6eeb34dba9d670981887849ad.jpg','Tuan Hosue Da Nang',7020,224,5),(1205,_binary '\0','9b35eb3d98014bd8946b306972e92b0b.jpg','Nightly Hotel',7000,160,4.3),(1300,_binary '','c0dca617b31c470083f7a2d81fea951e.jpg','JW Marriott Hanoi',7000,210,5),(1400,_binary '','e58c97ba6d83493ab77df7172452a56f.jpg','Tan Son Nhat Hotel',7010,196,4.6),(1500,_binary '','813abf66cfde46b685f01b0df81b2199.jpg','Eastin Grand Hotel Saigon',7010,180,4.8),(1600,_binary '','ea5b36b34b124b608655b6e7e6407cfb.jpg','Pavilion Hotel',7020,250,4.2),(1700,_binary '','3af8717224ad406b8c6d4f345f89a1da.jpg','Vinpearl Hotel Imperia Haiphong',7030,265,4.8),(1800,_binary '','8bb2bc4c20fb42889c1d9bf2553f6228.jpg','Muong Thanh Luxury Quang Ninh Hotel',7040,272,5),(1900,_binary '','7419e0b2b1614e8290bc8ab42294e7af.jpg','Saphir DaLat Hotel',7060,188,3.8),(1901,_binary '','42e99fa40b2e4575bc883c45f474f809.jpg','Phuoc Cho Hotel',7010,200,4.2),(1902,_binary '','ba349e6a52224302831935e0e627216e.jpg','Hotel of Thanh Tuan Le Handsome',7020,200,5),(1903,_binary '\0','b4028e54fb504609987b963e6e2ec2af.jpg','Facility of Digital Technology',7030,212,1);
/*!40000 ALTER TABLE `hotels` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `locations`
--

DROP TABLE IF EXISTS `locations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `locations` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `locations`
--

LOCK TABLES `locations` WRITE;
/*!40000 ALTER TABLE `locations` DISABLE KEYS */;
INSERT INTO `locations` VALUES (7000,'Hà Nội'),(7010,'Hồ Chí Minh'),(7020,'Đà Nẵng'),(7030,'Hải Phòng'),(7040,'Quảng Ninh'),(7050,'Quảng Bình'),(7060,'Đà Lạt'),(7070,'Huế'),(7080,'Khánh Hoà'),(7090,'Lạng Sơn'),(7110,'Thanh Hoá'),(7220,'Nghệ An'),(7330,'Bắc Ninh'),(7440,'Hưng Yên'),(7550,'Nam Định'),(7660,'Phú Yên'),(7770,'Buôn Ma Thuật'),(7880,'Cần Thơ'),(7990,'Kiên Giang');
/*!40000 ALTER TABLE `locations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `day_end` date DEFAULT NULL,
  `day_start` date DEFAULT NULL,
  `price` double NOT NULL,
  `hotel_id` int DEFAULT NULL,
  `room_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `booking_date` date DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl9pwunfdvwhds6qvg1e4l2kcr` (`hotel_id`,`room_id`),
  KEY `FKb5g9io5h54iwl2inkno50ppln` (`user_id`),
  CONSTRAINT `FKb5g9io5h54iwl2inkno50ppln` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKl9pwunfdvwhds6qvg1e4l2kcr` FOREIGN KEY (`hotel_id`, `room_id`) REFERENCES `rooms` (`hotel_id`, `id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (1,'2023-11-04','2023-10-02',100,1100,2100,1,'ĐÃ XONG','2023-11-02','','TRẢ BẰNG TIỀN MẶT'),(2,'2023-11-08','2022-11-06',100,1100,2100,1,'ĐÃ HỦY','2023-11-04','','TRẢ BẰNG TIỀN MẶT'),(4,'2023-11-08','2023-11-06',100,1100,2103,1,'ĐANG CHỜ','2023-11-04','Le Thanh Tuan books','TRẢ BẰNG TIỀN MẶT'),(5,'2023-11-08','2023-11-06',100,1100,2103,2,'ĐÃ XONG','2023-11-04','Le Quang Luan books','TRẢ BẰNG TIỀN MẶT'),(8,'2023-11-28','2023-11-26',100,1200,2102,2,'ĐÃ CHẤP NHẬN','2023-11-24','Le Quang Luan books','TRẢ BẰNG TIỀN MẶT'),(9,'2023-11-28','2023-11-27',100,1500,2107,2,'ĐÃ HỦY','2023-11-27','Le Quang Luan books','TRẢ BẰNG TIỀN MẶT'),(10,'2023-12-02','2023-11-30',100,1500,2107,2,'ĐANG CHỜ','2023-11-30','Le Quang Luan books','TRẢ BẰNG TIỀN MẶT'),(11,'2023-12-02','2023-11-30',14.72,1200,2102,2,'ĐÃ HỦY','2023-11-30','ok','TRẢ BẰNG TIỀN MẶT'),(12,'2023-12-06','2023-12-04',100,1300,2103,2,'ĐÃ HỦY','2023-11-30',NULL,'TRẢ BẰNG TIỀN MẶT'),(14,'2023-12-03','2023-12-02',45,1100,2100,2,'ĐANG CHỜ','2023-12-02',NULL,'TRẢ BẰNG TIỀN MẶT'),(15,'2023-12-05','2023-12-02',28.19,1200,2100,2,'ĐÃ XONG','2023-12-02','ok','TRẢ BẰNG THẺ'),(16,'2023-12-15','2023-12-12',100,1500,2107,2,'ĐANG CHỜ','2023-12-12','Le Quang Luan books','TRẢ BẰNG TIỀN MẶT'),(17,'2023-12-16','2023-12-13',27,1100,2103,2,'ĐANG CHỜ','2023-12-13',NULL,'TRẢ BẰNG TIỀN MẶT');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_types`
--

DROP TABLE IF EXISTS `room_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_types`
--

LOCK TABLES `room_types` WRITE;
/*!40000 ALTER TABLE `room_types` DISABLE KEYS */;
INSERT INTO `room_types` VALUES (7,'Phòng đơn'),(8,'Phòng đôi'),(9,'Phòng ba'),(10,'Phòng gia đình'),(11,'Villa'),(12,'Phòng nhóm'),(13,' Studio');
/*!40000 ALTER TABLE `room_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` int NOT NULL,
  `floor` int NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `hotel_id` int NOT NULL,
  `price` double NOT NULL,
  `room_type_id` int DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `sale` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`,`id`),
  KEY `FKh9m2n1paq5hmd3u0klfl7wsfv` (`room_type_id`),
  CONSTRAINT `FKh9m2n1paq5hmd3u0klfl7wsfv` FOREIGN KEY (`room_type_id`) REFERENCES `room_types` (`id`),
  CONSTRAINT `FKp5lufxy0ghq53ugm93hdc941k` FOREIGN KEY (`hotel_id`) REFERENCES `hotels` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (2100,5,'ba4ac8f2da57421883ad045c189b2b8f.jpg',1100,50,7,'ĐÃ ĐƯỢC ĐẶT',10,'B101'),(2103,7,'7b97f86f126f4f5daba1496e1e928d76.jpg',1100,10,7,'ĐÃ ĐƯỢC ĐẶT',10,'A702'),(2109,6,'22cde8dd9da440c08d53d3ffe016c44e.jpg',1100,5,8,'PHÒNG TRỐNG',5,'B603'),(2111,7,'317b9be4fd6c43eb8c94eda18694d0d6.jpg',1100,12,8,'PHÒNG TRỐNG',6,NULL),(2113,6,'ac48c00936704c39a6e542cf7affe3b0.jpg',1100,12,10,'PHÒNG TRỐNG',6,'A612'),(2100,5,'bbf70860f0a34124a16c822d996bc0ff.jpg',1200,10,8,'PHÒNG TRỐNG',6,'C504'),(2102,7,'d93403b435ce44b08543ba029335485c.jpg',1200,8,10,'ĐÃ ĐƯỢC ĐẶT',8,'A707'),(2108,5,'907559055d164accaafb53cf5dd807aa.jpg',1200,10,9,'PHÒNG TRỐNG',10,'A506'),(2110,6,'9d26ab3a508b4ed8991525bf2b22ff12.jpg',1200,12,7,'PHÒNG TRỐNG',12,'A620'),(2115,6,'1a2754a8e22c48f68d635ea11bb05022.jpg',1203,12,7,'PHÒNG TRỐNG',6,'F601'),(2103,8,'7bd44098e069450f8eda23bf4fe58e69.jpg',1300,8,8,'ĐÃ ĐƯỢC ĐẶT',15,'G705'),(2101,5,'091dbe9e507047338b4a0306b861aa33.jpg',1400,8,7,'PHÒNG TRỐNG',10,'A505'),(2102,5,'8523e0ce80e24ff8b7566cad67406577.jpg',1400,10,10,'PHÒNG TRỐNG',5,'A503'),(2104,8,'ffbe056a3a6a4c5db2fdc00b98c0aa4b.jpg',1400,15,11,'PHÒNG TRỐNG',8,'C506'),(2105,6,'88ada04fb8fb4e97a24a934e84a4f4b9.jpg',1500,16,12,'PHÒNG TRỐNG',8,'B603'),(2107,5,'dfe7a09d8e054100b2bb5be5550fdf8e.jpg',1500,20,13,'ĐÃ ĐƯỢC ĐẶT',10,'G502'),(2106,6,'3d38bb73907542ff88e9e2052bddc18a.jpg',1600,10,10,'PHÒNG TRỐNG',12,'6A31');
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,2),(4,2),(5,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdk0xfnnthbj8afp1ira6sndte` (`location_id`),
  CONSTRAINT `FKdk0xfnnthbj8afp1ira6sndte` FOREIGN KEY (`location_id`) REFERENCES `locations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'thanhtuan@gmail.com',_binary '','male','596f0ab772d445599b4b2c6877f34b2e.jpg','Thanh Tuan Le','$2a$10$hnTUKaI03JVq3mAoiNTOSuz/VeJOEICSH3Uw0N7PrAfCXLQR2J33G','0378315209',7020),(2,'quangluan0212@gmail.com',_binary '','female','3978523d601e464bbc8606a574f9bb11.png','Luan Le Quang','$2a$10$bGXh3hN2bapIre.PKZyJG.ubDy1V9RsKAQa7VRvLCCbuQq63B4vyu','12344567890',7030),(4,'test@gmail.com',_binary '','other','135f971110774bc894f1338e823890fd.jpg','test','$2a$10$3qfcZlOeh2yG9qufoU5l1Onuqmm./BfWPE3CeF4terk/nITaLmKee','123443321',7000),(5,'test1@gmail.com',_binary '','male','avt.png','tuan test','$2a$10$dQrci/S8/3VmtKWlvuwySeYB7aYqxz7fBSg1WVbI8YSF1c4xQ9E9a','123',7020);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-14 11:39:20
