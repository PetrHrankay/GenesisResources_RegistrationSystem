-- MySQL dump 10.13  Distrib 8.3.0, for macos12.6 (x86_64)
--
-- Host: localhost    Database: GenesisResources
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `person_id` varchar(12) NOT NULL,
  `uuid` varchar(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_unique` (`person_id`),
  UNIQUE KEY `users_unique_1` (`uuid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=cp1250;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Joe','Bazooka','jXa4g3H7oPq2','484beec9-b78c-4de1-a20b-ac566ec3df8a'),(2,'Timmy','Tornado','yB9fR6tK0wLm','be1617b4-66d4-4791-992a-a095c0cce25a'),(3,'Nancy','Turbo','cN1vZ8pE5sYx','923b961e-9256-4127-9517-e67b8f6af672'),(4,'Ralf','Rocket','tQdG2kP3mJfB','d9f57566-9058-4f2e-9548-f4ce1dee9ef9'),(5,'Lucy','Laser','iM5sO6zXcW7v','dd32dc70-c466-4ff0-9d92-42a3663ccbb1'),(6,'Tom','Thunder','rU8nA9eT2bYh','aaac396e-f0d2-4697-b557-f88beebf8505');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'GenesisResources'
--

--
-- Dumping routines for database 'GenesisResources'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-19 18:46:49
