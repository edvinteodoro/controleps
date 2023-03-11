-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema datos_eps
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `datos_eps` ;

-- -----------------------------------------------------
-- Schema datos_eps
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `datos_eps` ;
USE `datos_eps` ;

-- -----------------------------------------------------
-- Table `datos_eps`.`rol`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`rol` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`rol` (
  `id_rol` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_rol`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`usuario` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`usuario` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `correo` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `nombres` VARCHAR(50) NOT NULL,
  `apellidos` VARCHAR(50) NOT NULL,
  `registro_academico` VARCHAR(30) NOT NULL,
  `numero_colegiado` VARCHAR(30) NULL,
  `dpi` VARCHAR(20) NOT NULL,
  `direccion` VARCHAR(200) NOT NULL,
  `telefono` VARCHAR(15) NOT NULL,
  `estado_cuenta` ENUM('ACTIVO', 'INACTIVO') NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `id_rol_fk` INT NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `CARNET_UNIQUE` (`id_usuario` ASC) VISIBLE,
  INDEX `fk_USUARIOS_ROL_idx` (`id_rol_fk` ASC) VISIBLE,
  UNIQUE INDEX `CORREO_UNIQUE` (`correo` ASC) VISIBLE,
  CONSTRAINT `fk_USUARIOS_ROL`
    FOREIGN KEY (`id_rol_fk`)
    REFERENCES `datos_eps`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`permisos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`permisos` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`permisos` (
  `id_permisos` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `estado` VARCHAR(1) NULL,
  PRIMARY KEY (`id_permisos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`rol_permisos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`rol_permisos` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`rol_permisos` (
  `id_rol_fk` INT NOT NULL,
  `id_permiso_fk` INT NOT NULL,
  PRIMARY KEY (`id_rol_fk`, `id_permiso_fk`),
  INDEX `fk_ROL_has_PERMISOS_PERMISOS1_idx` (`id_permiso_fk` ASC) VISIBLE,
  INDEX `fk_ROL_has_PERMISOS_ROL1_idx` (`id_rol_fk` ASC) VISIBLE,
  CONSTRAINT `fk_ROL_has_PERMISOS_ROL1`
    FOREIGN KEY (`id_rol_fk`)
    REFERENCES `datos_eps`.`rol` (`id_rol`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ROL_has_PERMISOS_PERMISOS1`
    FOREIGN KEY (`id_permiso_fk`)
    REFERENCES `datos_eps`.`permisos` (`id_permisos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`carrera`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`carrera` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`carrera` (
  `id_carrera` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_carrera`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`carreras_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`carreras_usuario` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`carreras_usuario` (
  `id_usuario_carrera` INT NOT NULL AUTO_INCREMENT,
  `id_usuario_fk` INT NOT NULL,
  `id_carrera_fk` INT NOT NULL,
  `INTENTOS` INT NULL DEFAULT 0,
  INDEX `fk_USUARIOS_has_CARRERA_CARRERA1_idx` (`id_carrera_fk` ASC) VISIBLE,
  INDEX `fk_USUARIOS_has_CARRERA_USUARIOS1_idx` (`id_usuario_fk` ASC) VISIBLE,
  PRIMARY KEY (`id_usuario_carrera`),
  CONSTRAINT `fk_USUARIOS_has_CARRERA_USUARIOS1`
    FOREIGN KEY (`id_usuario_fk`)
    REFERENCES `datos_eps`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USUARIOS_has_CARRERA_CARRERA1`
    FOREIGN KEY (`id_carrera_fk`)
    REFERENCES `datos_eps`.`carrera` (`id_carrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`estado_eps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`estado_eps` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`estado_eps` (
  `id_estado_eps` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `descripcion` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id_estado_eps`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`proyecto_eps`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`proyecto_eps` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`proyecto_eps` (
  `id_anteproyecto` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(400) NOT NULL,
  `coordenadas` VARCHAR(20) NOT NULL,
  `fecha_inicio` DATE NOT NULL,
  `fecha_fin` DATE NOT NULL,
  `id_secretaria_fk` INT NOT NULL,
  `id_estado_eps_fk` INT NOT NULL,
  `id_carreras_usuario_fk` INT NOT NULL,
  `id_carreras_supervisor_fk` INT NOT NULL,
  `id_carreras_asesor_fk` INT NOT NULL,
  PRIMARY KEY (`id_anteproyecto`),
  INDEX `fk_SECRETARIA_USUARIO_idx` (`id_secretaria_fk` ASC) VISIBLE,
  INDEX `fk_PROYECTO_EPS_ESTADO_EPS1_idx` (`id_estado_eps_fk` ASC) VISIBLE,
  INDEX `fk_proyecto_eps_carreras_usuario1_idx` (`id_carreras_usuario_fk` ASC) VISIBLE,
  INDEX `fk_proyecto_eps_carreras_usuario2_idx` (`id_carreras_supervisor_fk` ASC) VISIBLE,
  INDEX `fk_proyecto_eps_carreras_usuario3_idx` (`id_carreras_asesor_fk` ASC) VISIBLE,
  CONSTRAINT `fk_SECRETARIA_USUARIO`
    FOREIGN KEY (`id_secretaria_fk`)
    REFERENCES `datos_eps`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTO_EPS_ESTADO_EPS1`
    FOREIGN KEY (`id_estado_eps_fk`)
    REFERENCES `datos_eps`.`estado_eps` (`id_estado_eps`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_eps_carreras_usuario1`
    FOREIGN KEY (`id_carreras_usuario_fk`)
    REFERENCES `datos_eps`.`carreras_usuario` (`id_usuario_carrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_eps_carreras_usuario2`
    FOREIGN KEY (`id_carreras_supervisor_fk`)
    REFERENCES `datos_eps`.`carreras_usuario` (`id_usuario_carrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_proyecto_eps_carreras_usuario3`
    FOREIGN KEY (`id_carreras_asesor_fk`)
    REFERENCES `datos_eps`.`carreras_usuario` (`id_usuario_carrera`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`bitacora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`bitacora` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`bitacora` (
  `id_bitacora` INT NOT NULL AUTO_INCREMENT,
  `id_usuario_fk` INT NOT NULL,
  `funcionalidad` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(45) NOT NULL,
  `fecha` DATE NOT NULL,
  `request` VARCHAR(250) NOT NULL,
  `response` VARCHAR(250) NOT NULL,
  `estatus_https` INT NOT NULL,
  `id_usuario_afectad_fk` INT NULL,
  PRIMARY KEY (`id_bitacora`),
  INDEX `fk_BITACORA_USUARIOS1_idx` (`id_usuario_fk` ASC) VISIBLE,
  INDEX `fk_BITACORA_USUARIOS2_idx` (`id_usuario_afectad_fk` ASC) VISIBLE,
  CONSTRAINT `fk_BITACORA_USUARIOS1`
    FOREIGN KEY (`id_usuario_fk`)
    REFERENCES `datos_eps`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_BITACORA_USUARIOS2`
    FOREIGN KEY (`id_usuario_afectad_fk`)
    REFERENCES `datos_eps`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`etapa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`etapa` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`etapa` (
  `id_etapa` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(25) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  `limite_dias` DOUBLE NULL,
  `nota_aprovacion` DOUBLE NULL,
  PRIMARY KEY (`id_etapa`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`elemento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`elemento` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`elemento` (
  `id_elemento` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `tipo` VARCHAR(15) NOT NULL,
  `template` VARCHAR(500) NULL,
  `id_etapa_fk` INT NOT NULL,
  PRIMARY KEY (`id_elemento`),
  INDEX `fk_ELEMENTO_ETAPA1_idx` (`id_etapa_fk` ASC) VISIBLE,
  CONSTRAINT `fk_ELEMENTO_ETAPA1`
    FOREIGN KEY (`id_etapa_fk`)
    REFERENCES `datos_eps`.`etapa` (`id_etapa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`etapas_proyecto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`etapas_proyecto` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`etapas_proyecto` (
  `id_etapa_proyecto` INT NOT NULL AUTO_INCREMENT,
  `fecha_creacion` DATE NOT NULL,
  `fecha_modificacion` DATE NOT NULL,
  `fecha_fin` DATE NULL,
  `id_proyecto_eps_fk` INT NOT NULL,
  `id_etapa_fk` INT NOT NULL,
  `estado_fk` INT NOT NULL,
  PRIMARY KEY (`id_etapa_proyecto`),
  INDEX `fk_PROYECTO_EPS_has_ETAPA_ETAPA1_idx` (`id_etapa_fk` ASC) VISIBLE,
  INDEX `fk_PROYECTO_EPS_has_ETAPA_PROYECTO_EPS1_idx` (`id_proyecto_eps_fk` ASC) VISIBLE,
  INDEX `fk_ETAPAS_PROYECTO_ESTADO_EPS1_idx` (`estado_fk` ASC) VISIBLE,
  CONSTRAINT `fk_PROYECTO_EPS_has_ETAPA_PROYECTO_EPS1`
    FOREIGN KEY (`id_proyecto_eps_fk`)
    REFERENCES `datos_eps`.`proyecto_eps` (`id_anteproyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PROYECTO_EPS_has_ETAPA_ETAPA1`
    FOREIGN KEY (`id_etapa_fk`)
    REFERENCES `datos_eps`.`etapa` (`id_etapa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPAS_PROYECTO_ESTADO_EPS1`
    FOREIGN KEY (`estado_fk`)
    REFERENCES `datos_eps`.`estado_eps` (`id_estado_eps`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`elementos_proyecto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`elementos_proyecto` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`elementos_proyecto` (
  `id_elementos_proyecto` INT NOT NULL AUTO_INCREMENT,
  `id_elemento_fk` INT NOT NULL,
  `id_etapa_proyecto_fk` INT NOT NULL,
  `informacion` VARCHAR(500) NOT NULL,
  `fecha_creacion` DATE NOT NULL,
  `fecha_modificacion` DATE NOT NULL,
  `fecha_inicio` DATE NULL,
  `estado` INT NOT NULL,
  INDEX `fk_ELEMENTO_has_ETAPAS_PROYECTO_ETAPAS_PROYECTO1_idx` (`id_etapa_proyecto_fk` ASC) VISIBLE,
  INDEX `fk_ELEMENTO_has_ETAPAS_PROYECTO_ELEMENTO1_idx` (`id_elemento_fk` ASC) VISIBLE,
  PRIMARY KEY (`id_elementos_proyecto`),
  CONSTRAINT `fk_ELEMENTO_has_ETAPAS_PROYECTO_ELEMENTO1`
    FOREIGN KEY (`id_elemento_fk`)
    REFERENCES `datos_eps`.`elemento` (`id_elemento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ELEMENTO_has_ETAPAS_PROYECTO_ETAPAS_PROYECTO1`
    FOREIGN KEY (`id_etapa_proyecto_fk`)
    REFERENCES `datos_eps`.`etapas_proyecto` (`id_etapa_proyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`comentario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`comentario` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`comentario` (
  `id_comentario` INT NOT NULL AUTO_INCREMENT,
  `id_usuario_fk` INT NOT NULL,
  `texto` VARCHAR(500) NOT NULL,
  `fecha_creacion` TIMESTAMP(2) NOT NULL,
  `etapa_proyecto_fk` INT NULL,
  `id_elementos_proyecto_fk` INT NULL,
  INDEX `fk_ETAPAS_PROYECTO_has_USUARIOS_USUARIOS1_idx` (`id_usuario_fk` ASC) VISIBLE,
  INDEX `fk_ETAPAS_PROYECTO_has_USUARIOS_ETAPAS_PROYECTO1_idx` (`etapa_proyecto_fk` ASC) VISIBLE,
  PRIMARY KEY (`id_comentario`),
  INDEX `fk_COMENTARIO_ELEMENTOS_PROYECTO1_idx` (`id_elementos_proyecto_fk` ASC) VISIBLE,
  CONSTRAINT `fk_ETAPAS_PROYECTO_has_USUARIOS_ETAPAS_PROYECTO1`
    FOREIGN KEY (`etapa_proyecto_fk`)
    REFERENCES `datos_eps`.`etapas_proyecto` (`id_etapa_proyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ETAPAS_PROYECTO_has_USUARIOS_USUARIOS1`
    FOREIGN KEY (`id_usuario_fk`)
    REFERENCES `datos_eps`.`usuario` (`id_usuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_COMENTARIO_ELEMENTOS_PROYECTO1`
    FOREIGN KEY (`id_elementos_proyecto_fk`)
    REFERENCES `datos_eps`.`elementos_proyecto` (`id_elementos_proyecto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `datos_eps`.`constantes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `datos_eps`.`constantes` ;

CREATE TABLE IF NOT EXISTS `datos_eps`.`constantes` (
  `id_constante` INT NOT NULL,
  `valor` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`id_constante`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
