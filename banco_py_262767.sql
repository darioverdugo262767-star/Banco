CREATE DATABASE  IF NOT EXISTS `banco_py1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `banco_py1`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: banco_py1
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-24  5:34:01

delimiter //

create trigger tg_checar_saldo
before insert on transacciones
for each row
begin
    if new.monto < 0 and (select saldo from cuentas where numerocuenta = new.numero_cuenta) + new.monto < 0 then
        signal sqlstate '45000' set message_text = 'no tienes dinero suficiente';
    end if;
end //

create procedure sp_transferir(u_origen varchar(20), u_destino varchar(20), cantidad decimal(10,2))
begin
    declare exit handler for sqlexception rollback;

    start transaction;
        update cuentas set saldo = saldo - cantidad where numerocuenta = u_origen;
        insert into transacciones(fechahora, monto, numero_cuenta, fechahora) values(now(), -cantidad, u_origen, now());
        update cuentas set saldo = saldo + cantidad where numerocuenta = u_destino;
    commit;
end // 

delimiter ;

