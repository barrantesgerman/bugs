CREATE TABLE `usuario` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(64) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`)
) COMMENT='Representa un usuario del sistema';

CREATE TABLE `proyecto` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) DEFAULT NULL,
  `estado` enum('EN_DESARROLLO','ENTREGADO','OBSOLETO') NOT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='Representa un proyecto';

CREATE TABLE `modulo` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `proyecto_id` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `modulo_proyecto_fk_idx` (`proyecto_id`),
  CONSTRAINT `modulo_proyecto_fk` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) COMMENT='Representa un modulo del proyecto';

CREATE TABLE `categoria` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `proyecto_id` int(11) UNSIGNED NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_proyecto_fk_idx` (`proyecto_id`),
  CONSTRAINT `categoria_proyecto_fk` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) COMMENT='Representa la categoría de una incidencia';

CREATE TABLE `incidencia` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `proyecto_id` int(11) UNSIGNED NOT NULL,
  `modulo_id` int(11) UNSIGNED DEFAULT NULL,
  `categoria_id` int(11) UNSIGNED NOT NULL,
  `estado` enum('NUEVA','SE_NECESITAN_MAS_DATOS','ACEPTADA','CONFIRMADA','ASIGNADA','ATENDIENDO','RESUELTA','REVISANDO','CERRADA') NOT NULL,
  `prioridad` enum('NINGUNA','BAJA','NORMAL','ALTA','URGENTE','INMEDIATA') NOT NULL,
  `reproducibilidad` enum('SIEMPRE','ALEATORIO','DESCONOCIDO') NOT NULL,
  `resolucion` enum('ABIERTA','CORREGIDA','REABIERTA','NO_REPRODUCIBLE','NO_ES_CORREGIBLE','DUPLICADA','NO_ES_UNA_INCIDENCIA','SUSPENDIDA','NO_SE_ARREGLARA') NOT NULL,
  `resumen` varchar(100) NOT NULL,
  `descripcion` varchar(5000) NOT NULL,
  `pasos` varchar(5000) DEFAULT NULL,
  `informacion_adicional` varchar(5000) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  `usuario_creacion_id` int(11) UNSIGNED NOT NULL,
  `fecha_creacion` datetime NOT NULL,
  `usuario_actualizacion_id` int(11) UNSIGNED DEFAULT NULL,
  `fecha_actualizacion` datetime DEFAULT NULL,
  `usuario_asignacion_id` int(11) UNSIGNED DEFAULT NULL,
  `fecha_asignacion` datetime DEFAULT NULL,
  `usuario_atencion_id` int(11) UNSIGNED DEFAULT NULL,
  `fecha_atencion` datetime DEFAULT NULL,
  `usuario_resolucion_id` int(11) UNSIGNED DEFAULT NULL,
  `fecha_resolucion` datetime DEFAULT NULL,
  `usuario_revision_id` int(11) UNSIGNED DEFAULT NULL,
  `fecha_revision` datetime DEFAULT NULL,
  `usuario_cierre_id` int(11) UNSIGNED DEFAULT NULL,
  `fecha_cierre` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `incidencia_proyecto_fk_idx` (`proyecto_id`),
  KEY `incidencia_modulo_fk_idx` (`modulo_id`,`proyecto_id`),
  KEY `incidencia_categoria_fk_idx` (`categoria_id`,`proyecto_id`),
  CONSTRAINT `incidencia_categoria_fk` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_modulo_fk` FOREIGN KEY (`modulo_id`) REFERENCES `modulo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_proyecto_fk` FOREIGN KEY (`proyecto_id`) REFERENCES `proyecto` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_creacion_fk` FOREIGN KEY (`usuario_creacion_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_actualizacion_fk` FOREIGN KEY (`usuario_actualizacion_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_asignacion_fk` FOREIGN KEY (`usuario_asignacion_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_atencion_fk` FOREIGN KEY (`usuario_atencion_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_resolucion_fk` FOREIGN KEY (`usuario_resolucion_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_revision_fk` FOREIGN KEY (`usuario_revision_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `incidencia_usuario_cierre_fk` FOREIGN KEY (`usuario_cierre_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) COMMENT='Representa una incidencia';

CREATE TABLE `nota` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `incidencia_id` int(11) UNSIGNED NOT NULL,
  `usuario_id` int(11) UNSIGNED NOT NULL,
  `fecha` datetime NOT NULL,
  `nota` varchar(5000) NOT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `nota_incidencia_fk_idx` (`incidencia_id`),
  CONSTRAINT `nota_incidencia_fk` FOREIGN KEY (`incidencia_id`) REFERENCES `incidencia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `nota_usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) COMMENT='Representa una nota de una incidencia';

CREATE TABLE `archivo` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `incidencia_id` int(11) UNSIGNED NOT NULL,
  `usuario_id` int(11) UNSIGNED NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `mimeType` varchar(50) NOT NULL,
  `fecha` datetime NOT NULL,
  `contenido` longblob NOT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `archivo_incidencia_fk_idx` (`incidencia_id`),
  CONSTRAINT `archivo_incidencia_fk` FOREIGN KEY (`incidencia_id`) REFERENCES `incidencia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `archivo_usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) COMMENT='Representa un archivo asociado a una incidencia';

CREATE TABLE `historico` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `incidencia_id` int(11) UNSIGNED NOT NULL,
  `usuario_id` int(11) UNSIGNED NOT NULL,
  `campo` varchar(50) NOT NULL,
  `fecha` datetime NOT NULL,
  `antes` varchar(5000) DEFAULT NULL,
  `ahora` varchar(5000) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `historico_incidencia_fk_idx` (`incidencia_id`),
  CONSTRAINT `historico_incidencia_fk` FOREIGN KEY (`incidencia_id`) REFERENCES `incidencia` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `historico_usuario_fk` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) COMMENT='Representa la auditoria de los cambios realizados sobre una incidencia';

CREATE TABLE `filtro` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `proyecto_id` int(11) UNSIGNED DEFAULT NULL,
  `modulo_id` int(11) UNSIGNED DEFAULT NULL,
  `categoria_id` int(11) UNSIGNED DEFAULT NULL,
  `usuario_id` int(11) UNSIGNED DEFAULT NULL,
  `estado` set('NUEVA','SE_NECESITAN_MAS_DATOS','ACEPTADA','CONFIRMADA','ASIGNADA','ATENDIENDO','RESUELTA','REVISANDO','CERRADA') DEFAULT NULL,
  `prioridad` set('NINGUNA','BAJA','NORMAL','ALTA','URGENTE','INMEDIATA') DEFAULT NULL,
  `reproducibilidad` set('SIEMPRE','ALEATORIO','DESCONOCIDO') DEFAULT NULL,
  `resolucion` set('ABIERTA','CORREGIDA','REABIERTA','NO_REPRODUCIBLE','NO_ES_CORREGIBLE','DUPLICADA','NO_ES_UNA_INCIDENCIA','SUSPENDIDA','NO_SE_ARREGLARA') DEFAULT NULL,
  `resumen` varchar(100) DEFAULT NULL,
  `activo` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) COMMENT='Representa el último filtro utilizado por un usuario';

CREATE VIEW `incidencia_view` AS
select `inc`.`id` AS `id`,
`inc`.`proyecto_id` AS `proyecto_id`,
`pro`.`nombre` AS `proyecto_nombre`,
`inc`.`modulo_id` AS `modulo_id`,
`mdl`.`nombre` AS `modulo_nombre`,
`inc`.`categoria_id` AS `categoria_id`,
`cat`.`descripcion` AS `categoria_descripcion`,
`inc`.`estado` AS `estado`,
`inc`.`prioridad` AS `prioridad`,
`inc`.`reproducibilidad` AS `reproducibilidad`,
`inc`.`resolucion` AS `resolucion`,
`inc`.`resumen` AS `resumen`,
`inc`.`activo` AS `activo`,
`inc`.`usuario_creacion_id` AS `usuario_creacion_id`,
`inc`.`fecha_creacion` AS `fecha_creacion`,
`inc`.`usuario_actualizacion_id` AS `usuario_actualizacion_id`,
`inc`.`fecha_actualizacion` AS `fecha_actualizacion`,
`inc`.`usuario_asignacion_id` AS `usuario_asignacion_id`,
`inc`.`fecha_asignacion` AS `fecha_asignacion`,
`inc`.`usuario_atencion_id` AS `usuario_atencion_id`,
`inc`.`fecha_atencion` AS `fecha_atencion`,
`inc`.`usuario_resolucion_id` AS `usuario_resolucion_id`,
`inc`.`fecha_resolucion` AS `fecha_resolucion`,
`inc`.`usuario_revision_id` AS `usuario_revision_id`,
`inc`.`fecha_revision` AS `fecha_revision`,
`inc`.`usuario_cierre_id` AS `usuario_cierre_id`,
`inc`.`fecha_cierre` AS `fecha_cierre` 
from 
`incidencia` `inc` 
inner join `proyecto` `pro` on `inc`.`proyecto_id` = `pro`.`id` 
left join `modulo` `mdl` on `inc`.`modulo_id` = `mdl`.`id` 
inner join `categoria` `cat` on `inc`.`categoria_id` = `cat`.`id`;
