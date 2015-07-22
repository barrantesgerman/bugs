CREATE TABLE `usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `usuario` VARCHAR(50) NOT NULL,
  `clave` VARCHAR(64) NOT NULL,
  `nombre` VARCHAR(50) NOT NULL,
  `correo` VARCHAR(50) NULL,
  `activo` BIT(1) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `usuario_UNIQUE` (`usuario` ASC))
COMMENT = 'Representa un usuario del sistema';

CREATE TABLE `bugs`.`archivo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(50) NOT NULL,
  `mimeType` VARCHAR(50) NOT NULL,
  `contenido` BLOB NULL,
  `activo` BIT(1) NULL,
  PRIMARY KEY (`id`))
COMMENT = 'Representa un archivo subido en el sistema';
