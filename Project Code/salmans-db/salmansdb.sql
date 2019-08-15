CREATE DATABASE  IF NOT EXISTS `salmansdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `salmansdb`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: salmansdb
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appointment_id` int(11) NOT NULL AUTO_INCREMENT,
  `appointment_date` date NOT NULL,
  `date_scheduled` date NOT NULL,
  `customer_fk` int(11) DEFAULT NULL,
  `hairstyle_fk` int(11) DEFAULT NULL,
  `hairstylist_fk` int(11) DEFAULT NULL,
  `service_time_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `FKfyomtycveyqubamgdlc5uynfr` (`customer_fk`),
  KEY `FK7hskcqx74a4a1i7v51j7kmh22` (`hairstyle_fk`),
  KEY `FKrkvlxrvgtg9jm0cy9o504en99` (`hairstylist_fk`),
  KEY `FK79x87q0i0ulbptsanaevng2sw` (`service_time_fk`),
  CONSTRAINT `FK79x87q0i0ulbptsanaevng2sw` FOREIGN KEY (`service_time_fk`) REFERENCES `service_times` (`time_id`),
  CONSTRAINT `FK7hskcqx74a4a1i7v51j7kmh22` FOREIGN KEY (`hairstyle_fk`) REFERENCES `hairstyles` (`hairstyle_id`),
  CONSTRAINT `FKfyomtycveyqubamgdlc5uynfr` FOREIGN KEY (`customer_fk`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKrkvlxrvgtg9jm0cy9o504en99` FOREIGN KEY (`hairstylist_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_days`
--

DROP TABLE IF EXISTS `business_days`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_days` (
  `day_id` int(11) NOT NULL AUTO_INCREMENT,
  `day_of_the_week` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`day_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_days`
--

LOCK TABLES `business_days` WRITE;
/*!40000 ALTER TABLE `business_days` DISABLE KEYS */;
INSERT INTO `business_days` VALUES (1,'Sunday'),(2,'Monday'),(3,'Tuesday'),(4,'Wednesday'),(5,'Thursday'),(6,'Friday'),(7,'Saturday');
/*!40000 ALTER TABLE `business_days` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_days_service_times`
--

DROP TABLE IF EXISTS `business_days_service_times`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_days_service_times` (
  `business_day_day_id` int(11) NOT NULL,
  `service_times_time_id` int(11) NOT NULL,
  KEY `FKgcni4q6spvgdqcgiet2yifh1i` (`service_times_time_id`),
  KEY `FKk35k3sdswhu0f4mpxxqdk0ahb` (`business_day_day_id`),
  CONSTRAINT `FKgcni4q6spvgdqcgiet2yifh1i` FOREIGN KEY (`service_times_time_id`) REFERENCES `service_times` (`time_id`),
  CONSTRAINT `FKk35k3sdswhu0f4mpxxqdk0ahb` FOREIGN KEY (`business_day_day_id`) REFERENCES `business_days` (`day_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_days_service_times`
--

LOCK TABLES `business_days_service_times` WRITE;
/*!40000 ALTER TABLE `business_days_service_times` DISABLE KEYS */;
INSERT INTO `business_days_service_times` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(2,10),(2,11),(2,12),(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),(3,7),(3,8),(3,9),(3,10),(3,11),(3,12),(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(4,10),(4,11),(4,12),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(5,12),(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),(6,7),(6,8),(6,9),(6,10),(6,11),(6,12),(7,1),(7,2),(7,3),(7,4),(7,5),(7,6),(7,7),(7,8),(7,9),(7,10),(7,11),(7,12);
/*!40000 ALTER TABLE `business_days_service_times` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hairstyles`
--

DROP TABLE IF EXISTS `hairstyles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hairstyles` (
  `hairstyle_id` int(11) NOT NULL AUTO_INCREMENT,
  `hairstyle_name` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hairstyle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hairstyles`
--

LOCK TABLES `hairstyles` WRITE;
/*!40000 ALTER TABLE `hairstyles` DISABLE KEYS */;
INSERT INTO `hairstyles` VALUES (1,'Crew Cut',NULL),(2,'Buzz Cut',NULL),(3,'Undercut',NULL),(4,'Mohawk',NULL),(5,'Ponytail',NULL),(6,'Quiff',NULL),(7,'Bangs',NULL),(8,'Bun',NULL),(9,'Beehive',NULL),(10,'Cornrows',NULL);
/*!40000 ALTER TABLE `hairstyles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviews`
--

DROP TABLE IF EXISTS `reviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reviews` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_of_review` date NOT NULL,
  `review_description` varchar(255) NOT NULL,
  `review_rating` varchar(255) NOT NULL,
  `user_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  KEY `FKoenktpe8m7k0ag3wio7v866x3` (`user_fk`),
  CONSTRAINT `FKoenktpe8m7k0ag3wio7v866x3` FOREIGN KEY (`user_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviews`
--

LOCK TABLES `reviews` WRITE;
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UK_716hgxp60ym1lifrdgp67xt5k` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_CUSTOMER'),(2,'ROLE_HAIRSTYLIST');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seats`
--

DROP TABLE IF EXISTS `seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seats` (
  `seat_id` int(11) NOT NULL AUTO_INCREMENT,
  `seat_number` int(11) NOT NULL,
  PRIMARY KEY (`seat_id`),
  UNIQUE KEY `UK_59lhvaw20it7jsdokxk5vc0k2` (`seat_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seats`
--

LOCK TABLES `seats` WRITE;
/*!40000 ALTER TABLE `seats` DISABLE KEYS */;
INSERT INTO `seats` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_times`
--

DROP TABLE IF EXISTS `service_times`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_times` (
  `time_id` int(11) NOT NULL AUTO_INCREMENT,
  `end_time` time NOT NULL,
  `start_time` time NOT NULL,
  PRIMARY KEY (`time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_times`
--

LOCK TABLES `service_times` WRITE;
/*!40000 ALTER TABLE `service_times` DISABLE KEYS */;
INSERT INTO `service_times` VALUES (1,'08:30:00','07:30:00'),(2,'09:30:00','08:30:00'),(3,'10:30:00','09:30:00'),(4,'11:30:00','10:30:00'),(5,'12:30:00','11:30:00'),(6,'13:30:00','12:30:00'),(7,'14:30:00','13:30:00'),(8,'15:30:00','14:30:00'),(9,'16:30:00','15:30:00'),(10,'17:30:00','16:30:00'),(11,'18:30:00','17:30:00'),(12,'19:30:00','18:30:00');
/*!40000 ALTER TABLE `service_times` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_times_seats`
--

DROP TABLE IF EXISTS `service_times_seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_times_seats` (
  `service_time_time_id` int(11) NOT NULL,
  `seats_seat_id` int(11) NOT NULL,
  KEY `FKnlwg7wehmbqfo8js7nrlpk1qj` (`seats_seat_id`),
  KEY `FKgug9twmna4hrxo7r0pk76i1j7` (`service_time_time_id`),
  CONSTRAINT `FKgug9twmna4hrxo7r0pk76i1j7` FOREIGN KEY (`service_time_time_id`) REFERENCES `service_times` (`time_id`),
  CONSTRAINT `FKnlwg7wehmbqfo8js7nrlpk1qj` FOREIGN KEY (`seats_seat_id`) REFERENCES `seats` (`seat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_times_seats`
--

LOCK TABLES `service_times_seats` WRITE;
/*!40000 ALTER TABLE `service_times_seats` DISABLE KEYS */;
INSERT INTO `service_times_seats` VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(3,3),(4,1),(4,2),(4,3),(5,1),(5,2),(5,3),(6,1),(6,2),(6,3),(7,1),(7,2),(7,3),(8,1),(8,2),(8,3),(9,1),(9,2),(9,3),(10,1),(10,2),(10,3),(11,1),(11,2),(11,3),(12,1),(12,2),(12,3);
/*!40000 ALTER TABLE `service_times_seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_registered` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role_role_id` int(11) DEFAULT NULL,
  `seat_fk` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKruo12mi6hchjfi06jhln9tdkt` (`role_role_id`),
  KEY `FK251nkdrrc079b4wojqsqp5r38` (`seat_fk`),
  CONSTRAINT `FK251nkdrrc079b4wojqsqp5r38` FOREIGN KEY (`seat_fk`) REFERENCES `seats` (`seat_id`),
  CONSTRAINT `FKruo12mi6hchjfi06jhln9tdkt` FOREIGN KEY (`role_role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2019-08-13','luns@salmans.com','Mr Luns','$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq',1,NULL),(2,'2019-08-13','chinedu@salmans.com','Chinedu Ugwu','$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq',2,1),(3,'2019-08-11','kelvin@salmans.com','Kelvin Amiaka','$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq',2,2),(4,'2019-08-12','wago@salmans.com','Wago Kedi','$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq',2,3),(5,'2019-07-10','john@salmans.com','John Lawal','$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq',3,NULL),(6,'2019-07-11','moses@salmans.com','Moses Niyonshuti','$2a$10$/X9HVgmtzI3nbCfKgsJPde1mFpY4tMU3v5NObDwIY.1FKKNyaJHYq',3,NULL),(7,'2019-08-15','blessing@salmans.com','Blessing Ohaz','$2a$10$zGDWQGu9ijmU06GRUCdqaeOJCElJRlRd7GmlkGe5bf8GjPrPPtUl2',3,NULL);
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

-- Dump completed on 2019-08-15 11:53:55
