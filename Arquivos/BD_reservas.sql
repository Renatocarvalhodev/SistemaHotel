-- MySQL Script generated by MySQL Workbench
-- Sun Nov  3 16:01:01 2024
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema BD_Reserva
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema BD_Reserva
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `BD_Reserva` DEFAULT CHARACTER SET utf8 ;
USE `BD_Reserva` ;

-- -----------------------------------------------------
-- Table `BD_Reserva`.`tb_quartos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_Reserva`.`tb_quartos` (
  `id_quartos` INT NOT NULL AUTO_INCREMENT,
  `numero` VARCHAR(4) NOT NULL,
  `andar` VARCHAR(3) NOT NULL,
  `descricao` VARCHAR(255) NULL,
  `caracteristicas` VARCHAR(512) NULL,
  `preco_diaria` DECIMAL(7,2) NOT NULL,
  `estado` VARCHAR(15) NOT NULL,
  `tipo_quarto` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id_quartos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `BD_Reserva`.`tb_cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `BD_Reserva`.`tb_cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `nome_cliente` VARCHAR(100) NOT NULL,
  `nome_pai` VARCHAR(100) NULL,
  `nome_mae` VARCHAR(100) NOT NULL,
  `tipo_documento` VARCHAR(45) NOT NULL,
  `num_documento` VARCHAR(45) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `telefone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
