CREATE DATABASE  IF NOT EXISTS `db_tfgalba` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_tfgalba`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: db_tfgalba
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
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `image` varchar(2500) DEFAULT NULL,
  `s_body` varchar(2500) NOT NULL,
  `s_title` varchar(250) NOT NULL,
  `dt_creationdate` date,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (17,'8','El próximo 12 de abril a las 10:00 h tendrá lugar el webinar formativo sobre la situación hipotecaria actual organizado por nuestra oficina de Bankinter dónde se analizarán los procesos de subrogación de hipotecas y, te aconsejarán sobre cómo tomar una de las decisiones financieras más importantes ¡No te lo pierdas!','Webinar Bankinter','2024-03-28');
INSERT INTO `article` VALUES (16,'7','Si quieres ampliar tus capacidades técnicas y tecnológicas, os recordamos que puedes aprovechar nuestras licencias de cursos externos.','¡Apúntate a los cursos de Open Webinars!','2024-02-02');
INSERT INTO `article` VALUES (14,'1','El próximo jueves, 25 de abril, celebramos un nuevo LAST a partir de las 20:30h en Vyta.','¡Apúntate al LAST!','2024-04-15');
INSERT INTO `article` VALUES (10,'6','Nos complace anunciar nuestra participación en el proyecto Discovery Front to Back de notas estructuradas y warrants para BBVA México. Desde la Oficina de México, seguimos trabajando seguimos liderando la práctica de Caplyso y Murex.','Nuevo proyecto: Discovery F2B Notas estructuradas y Warrants','2024-01-10');
INSERT INTO `article` VALUES (15,'5','Nuestros compañeros del área de datos participaron en la 4ª edición de Data Driven Day organizado por iKN Spain en el WiZink Center.','Data Driven Day 2024','2024-04-15');
INSERT INTO `article` VALUES (6,'4','Durante la mañana de ayer, alumnos de Bachillerato del colegio Runnymede han visitado nuestras oficinas de Madrid, dónde hemos tenido la oportunidad de acercar la realidad y las tendencias de la consultoría a los futuros talentos.','Visita Corporativa','2024-01-13');
INSERT INTO `article` VALUES (5,'3','El próximo 25 de mayo tendrá lugar el torneo de pádel entre empleados. En esta ocasión, tenemos muchas novedades (Torneo de Beerpong, BBQ y mucho más). ¡Os esperamos!','¡Apúntate al Torneo de Pádel + BBQ!','2024-04-13');
INSERT INTO `article` VALUES (9,'2','Estamos orgullosos de anunciar un nuevo proyecto relevante en Intercam: Apoyo Validación Murex. Este proyecto amplía nuestra presencia dentro de la entidad, específicamente se trata de un proyecto donde ayudaremos al departamento de riesgos financieros a validar los inputs de mercado, cálculos de valoración, VaR, CVA y DVA.','Nuevo proyecto: Apoyo Validación Murex - Intercam','2024-02-10');

/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_article_like`
--

DROP TABLE IF EXISTS `user_article_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_article_like` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `active` bit(1) DEFAULT NULL,
  `article_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK49wb7bphntuxc1ssrvu59pe94` (`article_id`),
  KEY `FKpyk23rwwyihlxeo3ymyq4ukbr` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_article_like`
--

LOCK TABLES `user_article_like` WRITE;
/*!40000 ALTER TABLE `user_article_like` DISABLE KEYS */;
-- INSERT INTO `user_article_like` VALUES (1,_binary,1,1),(2,_binary,2,1),(3,_binary,1,2);
/*!40000 ALTER TABLE `user_article_like` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `user_rol`
--

DROP TABLE IF EXISTS `user_rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_rol` (
  `user_id` bigint NOT NULL,
  `rol_id` bigint NOT NULL,
  KEY `FKpfraq7jod5w5xd3sxm3m6y1o` (`rol_id`),
  KEY `FK3xg2nuaohq3m1jidxctddln2j` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_rol`
--

LOCK TABLES `user_rol` WRITE;
/*!40000 ALTER TABLE `user_rol` DISABLE KEYS */;
INSERT INTO `user_rol` VALUES (1,1);
/*!40000 ALTER TABLE `user_rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `name` varchar(500) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@tfg.com','admin','$2a$12$7.hBuseMi0bg8kQzeHtbNeL16JLMwkLb8eUa3ofcuUT7jAWj3RqI2');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `Tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `tag_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(500) NOT NULL,
  `tags_type_id` bigint,
  PRIMARY KEY (`tag_id`),
  FOREIGN KEY (`tags_type_id`) REFERENCES `tags`(`tag_id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `Tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES 
(1,'Tipologia',NULL),
(2,'Localizacion',NULL),
(3,'Vida',1),
(4,'Coorporativo',1),
(5,'Madrid',2),
(6,'Sevilla',2),
(7,'Valencia',2),
(8,'Global',2),
(9,'Informacion Empleado',1);
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `ArticleTag`
--

DROP TABLE IF EXISTS `article_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_tag` (
  `article_id` bigint NOT NULL,
  `tag_id` bigint NOT NULL,
  KEY (`article_id`),
  KEY (`tag_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Dumping data for table `article_tag`
--

LOCK TABLES `article_tag` WRITE;
/*!40000 ALTER TABLE `article_tag` DISABLE KEYS */;
INSERT INTO `article_tag` VALUES (5,5),(5,3),(6,5),(6,4),(9,4),(10,4),(14,3),(14,5),(15,4),(16,9),(16,8),(17,9),(17,8);
/*!40000 ALTER TABLE `article_tag` ENABLE KEYS */;
UNLOCK TABLES;



--
-- Dumping routines for database 'db_tfgalba'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-24 17:28:48
