- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: banco_py1v2
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(100) NOT NULL,
  `apellidoPaterno` varchar(50) NOT NULL,
  `apellidoMaterno` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `edad` int NOT NULL,
  `contrasenia` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (8,'Dario','Verdugo','Tineo','2006-02-13',20,'pepe343'),(9,'Pepe','Pepusio','Wick','2000-01-01',26,'pepe777');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `numeroCuenta` varchar(20) NOT NULL,
  `saldo` decimal(10,2) NOT NULL DEFAULT '0.00',
  `fechaApertura` datetime NOT NULL,
  `id_cliente` int NOT NULL,
  `estado` varchar(20) DEFAULT 'ACTIVA',
  PRIMARY KEY (`numeroCuenta`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES ('0000000000000010',1000.00,'2026-02-24 12:45:45',9,'ACTIVA'),('0000000000000011',20000.00,'2026-02-24 12:45:59',9,'INACTIVA'),('0000000000000012',10000.00,'2026-02-24 12:47:30',8,'ACTIVA'),('0000000000000013',50000.00,'2026-02-24 12:47:41',8,'ACTIVA');
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccionescliente`
--

DROP TABLE IF EXISTS `direccionescliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccionescliente` (
  `id_cliente` int NOT NULL,
  `calle` varchar(100) NOT NULL,
  `numero` varchar(10) NOT NULL,
  `colonia` varchar(100) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `codigoPostal` varchar(10) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  CONSTRAINT `direccionescliente_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccionescliente`
--

LOCK TABLES `direccionescliente` WRITE;
/*!40000 ALTER TABLE `direccionescliente` DISABLE KEYS */;
INSERT INTO `direccionescliente` VALUES (8,'mercadito','1','Centro','Obregon','85000'),(9,'Beltrones','2','Beltrones','Obregon','85000');
/*!40000 ALTER TABLE `direccionescliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retirossincuenta`
--

DROP TABLE IF EXISTS `retirossincuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retirossincuenta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_transaccion` int NOT NULL,
  `folio` int NOT NULL,
  `contraseña` varchar(8) NOT NULL,
  `estado` enum('automatico','manual') NOT NULL,
  `fechaVencimiento` datetime NOT NULL,
  `numeroCuenta` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_transaccion` (`id_transaccion`),
  CONSTRAINT `retirossincuenta_ibfk_1` FOREIGN KEY (`id_transaccion`) REFERENCES `transacciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retirossincuenta`
--

LOCK TABLES `retirossincuenta` WRITE;
/*!40000 ALTER TABLE `retirossincuenta` DISABLE KEYS */;
/*!40000 ALTER TABLE `retirossincuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacciones`
--

DROP TABLE IF EXISTS `transacciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transacciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fechaHora` datetime NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `numero_Cuenta` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `numero_Cuenta` (`numero_Cuenta`),
  CONSTRAINT `transacciones_ibfk_1` FOREIGN KEY (`numero_Cuenta`) REFERENCES `cuentas` (`numeroCuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacciones`
--

LOCK TABLES `transacciones` WRITE;
/*!40000 ALTER TABLE `transacciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `transacciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transferencias`
--

DROP TABLE IF EXISTS `transferencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transferencias` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_transaccion` int NOT NULL,
  `cuentaDestino` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_transaccion` (`id_transaccion`),
  KEY `cuentaDestino` (`cuentaDestino`),
  CONSTRAINT `transferencias_ibfk_1` FOREIGN KEY (`id_transaccion`) REFERENCES `transacciones` (`id`),
  CONSTRAINT `transferencias_ibfk_2` FOREIGN KEY (`cuentaDestino`) REFERENCES `cuentas` (`numeroCuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transferencias`
--

LOCK TABLES `transferencias` WRITE;
/*!40000 ALTER TABLE `transferencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `transferencias` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-24 12:48:40

delimiter ;

