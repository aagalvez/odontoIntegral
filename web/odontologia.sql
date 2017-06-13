
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `Fecha` datetime DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `Paciente_idPaciente` int(11) NOT NULL,
  `Dentista_idDentista` int(11) NOT NULL,
  PRIMARY KEY (`idCita`),
  KEY `fk_Cita_Paciente1_idx` (`Paciente_idPaciente`),
  KEY `fk_Cita_Dentista1_idx` (`Dentista_idDentista`),
  CONSTRAINT `fk_Cita_Dentista1` FOREIGN KEY (`Dentista_idDentista`) REFERENCES `dentista` (`idDentista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Cita_Paciente1` FOREIGN KEY (`Paciente_idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` VALUES (1500,'2017-06-15 00:00:00','15:00:00',1000,1),(1501,'2017-06-13 00:00:00','12:00:00',1000,1);
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dentista`
--

DROP TABLE IF EXISTS `dentista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dentista` (
  `idDentista` int(11) NOT NULL,
  `NombreDentista` varchar(45) COLLATE utf8_esperanto_ci NOT NULL,
  `TelefonoDentista` int(11) NOT NULL,
  `PerfitlProfDentista` varchar(45) COLLATE utf8_esperanto_ci NOT NULL,
  `CorreoDentista` varchar(45) COLLATE utf8_esperanto_ci NOT NULL,
  `PassDentista` varchar(15) COLLATE utf8_esperanto_ci DEFAULT NULL,
  PRIMARY KEY (`idDentista`),
  UNIQUE KEY `idDentista_UNIQUE` (`idDentista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dentista`
--

LOCK TABLES `dentista` WRITE;
/*!40000 ALTER TABLE `dentista` DISABLE KEYS */;
INSERT INTO `dentista` VALUES (1,'arturo',554932630,'Odontologo','prueba@gmail.com',NULL);
/*!40000 ALTER TABLE `dentista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `idEquipo` int(11) NOT NULL,
  `NombreEquipo` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `MarcaEquipo` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `Dentista_idDentista` int(11) NOT NULL,
  PRIMARY KEY (`idEquipo`,`Dentista_idDentista`),
  KEY `fk_Equipo_Dentista_idx` (`Dentista_idDentista`),
  CONSTRAINT `fk_Equipo_Dentista` FOREIGN KEY (`Dentista_idDentista`) REFERENCES `dentista` (`idDentista`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (100,'Espejo Bucal','Dental',1);
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `material`
--

DROP TABLE IF EXISTS `material`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `material` (
  `idMaterial` int(11) NOT NULL,
  `CaducidadMaterial` datetime DEFAULT NULL,
  `Tipo` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `CostoMaterial` int(11) DEFAULT NULL,
  `Tratamiento especial_idTratamiento especial` int(11) NOT NULL,
  PRIMARY KEY (`idMaterial`),
  KEY `fk_Material_Tratamiento especial1_idx` (`Tratamiento especial_idTratamiento especial`),
  CONSTRAINT `fk_Material_Tratamiento especial1` FOREIGN KEY (`Tratamiento especial_idTratamiento especial`) REFERENCES `tratamiento especial` (`idTratamiento especial`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `material`
--

LOCK TABLES `material` WRITE;
/*!40000 ALTER TABLE `material` DISABLE KEYS */;
INSERT INTO `material` VALUES (150,'2017-08-01 00:00:00','braquets',1500,250);
/*!40000 ALTER TABLE `material` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `idPaciente` int(11) NOT NULL,
  `NombrePaciente` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `TelefonoPaciente` int(11) DEFAULT NULL,
  `DireccionPaciente` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `passPaciente` varchar(15) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `correoPaciente` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  PRIMARY KEY (`idPaciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (1000,'Xavier',553256478,'Av cofradia de san miguel',NULL,NULL);
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `receta`
--

DROP TABLE IF EXISTS `receta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `receta` (
  `idReceta` int(11) NOT NULL,
  `Descripcion` varchar(145) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `Dentista_idDentista` int(11) NOT NULL,
  `Paciente_idPaciente` int(11) NOT NULL,
  PRIMARY KEY (`idReceta`),
  KEY `fk_Receta_Dentista1_idx` (`Dentista_idDentista`),
  KEY `fk_Receta_Paciente1_idx` (`Paciente_idPaciente`),
  CONSTRAINT `fk_Receta_Dentista1` FOREIGN KEY (`Dentista_idDentista`) REFERENCES `dentista` (`idDentista`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Receta_Paciente1` FOREIGN KEY (`Paciente_idPaciente`) REFERENCES `paciente` (`idPaciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `receta`
--

LOCK TABLES `receta` WRITE;
/*!40000 ALTER TABLE `receta` DISABLE KEYS */;
INSERT INTO `receta` VALUES (10000,'consulta',1,1000);
/*!40000 ALTER TABLE `receta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tratamiento`
--

DROP TABLE IF EXISTS `tratamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tratamiento` (
  `idTratamiento` int(11) NOT NULL,
  `DescripcionTratamiento` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `DuracionTratamiento` time DEFAULT NULL,
  `PresupuestoTratamiento` int(11) DEFAULT NULL,
  `Cita_idCita` int(11) NOT NULL,
  PRIMARY KEY (`idTratamiento`),
  KEY `fk_Tratamiento_Cita1_idx` (`Cita_idCita`),
  CONSTRAINT `fk_Tratamiento_Cita1` FOREIGN KEY (`Cita_idCita`) REFERENCES `cita` (`idCita`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tratamiento`
--

LOCK TABLES `tratamiento` WRITE;
/*!40000 ALTER TABLE `tratamiento` DISABLE KEYS */;
INSERT INTO `tratamiento` VALUES (300,'consulta','00:00:01',300,1501);
/*!40000 ALTER TABLE `tratamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tratamiento especial`
--

DROP TABLE IF EXISTS `tratamiento especial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tratamiento especial` (
  `idTratamiento especial` int(11) NOT NULL,
  `DescripcionTratamientoEspecial` varchar(45) COLLATE utf8_esperanto_ci DEFAULT NULL,
  `DuracionTratamientoEspecial` time DEFAULT NULL,
  `PresupuestoTratamientoEspecial` int(11) DEFAULT NULL,
  `Cita_idCita` int(11) NOT NULL,
  PRIMARY KEY (`idTratamiento especial`),
  KEY `fk_Tratamiento especial_Cita1_idx` (`Cita_idCita`),
  CONSTRAINT `fk_Tratamiento especial_Cita1` FOREIGN KEY (`Cita_idCita`) REFERENCES `cita` (`idCita`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_esperanto_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tratamiento especial`
--

LOCK TABLES `tratamiento especial` WRITE;
/*!40000 ALTER TABLE `tratamiento especial` DISABLE KEYS */;
INSERT INTO `tratamiento especial` VALUES (250,'Intervencion dental de braquets','00:00:03',4000,1500);
/*!40000 ALTER TABLE `tratamiento especial` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-13  8:46:31
