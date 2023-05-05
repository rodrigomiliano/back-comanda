/************ COLLATION DB ***********/
-- ALTER DATABASE comandabd CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;

/********** BASE A UTILIZAR **********/
USE comandabd;

/********** QUITAR VALIDACIÓN **********/
SET SQL_SAFE_UPDATES = 0;

/********** CREACIÓN DE TABLAS **********/

-- CATEGORÍAS: OK
CREATE TABLE `CATEGORIAS` (
  `CATEGO_ID` int NOT NULL AUTO_INCREMENT,
  `CATEGO_NOMBRE` varchar(255) NOT NULL,
  -- `CATEGO_DESCRP` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CATEGO_ID`),
  UNIQUE KEY (`CATEGO_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ETIQUETAS: NO VA MAS
/*CREATE TABLE `ETIQUETAS` (
`ETIQUE_ID` int NOT NULL AUTO_INCREMENT,
`ETIQUE_NOMBRE` varchar(255) NOT NULL,
`ETIQUE_DESCRP` varchar(255) DEFAULT NULL,
PRIMARY KEY (`ETIQUE_ID`),
UNIQUE KEY (`ETIQUE_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;*/

-- PRODCUTOS: OK, VUELVO A PONER PRECIO
CREATE TABLE `PRODUCTOS` (
  `PRODUC_ID` int NOT NULL AUTO_INCREMENT,
  `PRODUC_NOMBRE` varchar(255) NOT NULL,
  `PRODUC_DESCRP` varchar(255) DEFAULT NULL,
  `PRODUC_PRECIO` double NOT NULL,
  `PRODUC_CATEGO` int NOT NULL,
  `PRODUC_IMG` varchar(255) NOT NULL,
  -- `ITEMCOM_ID` int DEFAULT NULL,
  -- `PRODUC_ETIQUE` int NOT NULL,
  PRIMARY KEY (`PRODUC_ID`),
  UNIQUE KEY (`PRODUC_NOMBRE`),
  KEY `FK_PRODUC_CATEGO_ID` (`PRODUC_CATEGO`),
  -- KEY `FK_PRODUC_ETIQUE_ID` (`PRODUC_ETIQUE`),
  -- KEY `FK_ITEMCOM_ID` (`ITEMCOM_ID`),
  CONSTRAINT `FK_PRODUC_CATEGO` FOREIGN KEY (`PRODUC_CATEGO`) REFERENCES `CATEGORIAS` (`CATEGO_ID`)
  -- CONSTRAINT `FK_ITEMCOM_ID` FOREIGN KEY (`ITEMCOM_ID`) REFERENCES `ITEMCOMANDAS` (`ITEMCOM_ID`)
  -- CONSTRAINT `FK_PRODUC_ETIQUE` FOREIGN KEY (`PRODUC_ETIQUE`) REFERENCES `ETIQUETAS` (`ETIQUE_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- PRECIOS (LISTA DE PRECIOS): OK, agrego al ID auto increment, NO VA MASSSSSS
/*CREATE TABLE `PRECIOS` (
  `PRELIS_ID` int NOT NULL AUTO_INCREMENT,
  `PRELIS_NOMBRE` varchar(255) NOT NULL,
  `PRELIS_PRODUC` int NOT NULL,
  `PRELIS_PRECIO` double NOT NULL,
  `PRELIS_VIGDDE` DATE NOT NULL,
  `PRELIS_VIGHTA` DATE NOT NULL,
  PRIMARY KEY (`PRELIS_ID`,`PRELIS_PRODUC`),
  -- KEY (`PRELIS_NOMBRE`),
  -- UNIQUE KEY (`PRELIS_NOMBRE`),
  KEY `FK_PRELIS_PRODUC` (`PRELIS_PRODUC`),
  CONSTRAINT `FK_PRELIS_PRODUC` FOREIGN KEY (`PRELIS_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;*/

-- MENU: OK, NO VA MASSSS
/*CREATE TABLE `MENU` (
  `MENU_ID` int NOT NULL AUTO_INCREMENT,
  `MENU_NOMBRE` varchar(255) NOT NULL,
  `MENU_VIGDDE` DATE NOT NULL,
  `MENU_VIGHTA` DATE NOT NULL,
  `MENU_PRELIS` INT NOT NULL,
  PRIMARY KEY (`MENU_ID`),
  UNIQUE KEY (`MENU_NOMBRE`),
  KEY `FK_MENU_PRELIS` (`MENU_PRELIS`),
  CONSTRAINT `FK_MENU_PRELIS` FOREIGN KEY (`MENU_PRELIS`) REFERENCES `PRECIOS` (`PRELIS_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;*/

-- MENUIT: OK, agregue el auto increment, NO VA MASSSS
/*CREATE TABLE `MENUIT` ( 
  `MENUIT_ID` int NOT NULL AUTO_INCREMENT,
  `MENUIT_MENU` int NOT NULL,
  `MENUIT_PRODUC` int NOT NULL,
  PRIMARY KEY (`MENUIT_ID`),
  KEY `FK_MENUIT_MENU` (`MENUIT_MENU`),
  KEY `FK_MENUIT_PRODUC` (`MENUIT_PRODUC`),
  CONSTRAINT `FK_MENUIT_MENU` FOREIGN KEY (`MENUIT_MENU`) REFERENCES `MENU` (`MENU_ID`),
  CONSTRAINT `FK_MENUIT_PRODUC` FOREIGN KEY (`MENUIT_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;*/

-- LOCALES: OK, le agregue auto increment, ver como poner los horarios
CREATE TABLE `LOCALES` (
  `LOCAL_ID` int NOT NULL AUTO_INCREMENT,
  `LOCAL_NOMBRE` varchar(255) NOT NULL,
  `LOCAL_CALLE`	varchar(255) NOT NULL,
  `LOCAL_ALTURA` int NOT NULL,
  `LOCAL_CODPOS` int NOT NULL,
  `LOCAL_TELEFN` int,
  `LOCAL_IMG` varchar(255) NOT NULL,
  -- `LOCAL_HORARIO` 
  PRIMARY KEY (`LOCAL_ID`),
  UNIQUE KEY (`LOCAL_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- HORARIOS DE LOCALES:  
/*
CREATE TABLE `HORARIOS` (
ID
DIA
HORA DESDE
HORA HASTA
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;
*/

-- ESTADOS: OK, VAMOS A USAR ESTA PARA ESTADO DE MESA Y ESTADO DE PEDIDOS TODO UNIFICADO?
CREATE TABLE `ESTADOS` (
  `ESTADO_ID` int NOT NULL AUTO_INCREMENT,
  `ESTADO_NOMBRE` varchar(255) NOT NULL,
  `ESTADO_DESCRP` varchar(255) NOT NULL,
  PRIMARY KEY (`ESTADO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ROLES: OK
CREATE TABLE `ROLES` (
  `ROL_ID` int NOT NULL AUTO_INCREMENT,
  `ROL_NOMBRE` varchar(255) NOT NULL,
  -- `ROL_NIVEL` int NOT NULL,
  PRIMARY KEY (`ROL_ID`),
  UNIQUE KEY (`ROL_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- USUARIOS: OK
CREATE TABLE `USUARIOS` (
  `USER_ID` int NOT NULL AUTO_INCREMENT,
  `USER_USER` varchar(255) NOT NULL,
  `USER_NOMBRE` varchar(255) NOT NULL,
  `USER_APELLI` varchar(255) DEFAULT NULL,
  `USER_NRODOC` int NOT NULL,  
  `USER_EMAIL` varchar(255) NOT NULL,
  `USER_TELEFN` varchar(255) DEFAULT NULL,
  `USER_ROL` int NOT NULL,
  `USER_PASWRD` varchar(255) NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY (`USER_USER`),
  KEY `FK_USER_ROL` (`USER_ROL`),
  CONSTRAINT `FK_USER_ROL` FOREIGN KEY (`USER_ROL`) REFERENCES `ROLES` (`ROL_ID`)
) ENGINE = InnoDB /*AUTO_INCREMENT = 1*/ DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- CLIENTES: OK
CREATE TABLE `CLIENTES` (
  `CLIENT_ID` int NOT NULL AUTO_INCREMENT,
  `CLIENT_USER` varchar(255) NOT NULL,
  `CLIENT_NOMBRE` varchar(255) NOT NULL,
  `CLIENT_APELLI` varchar(255) DEFAULT NULL,
  `CLIENT_NRODOC` int NOT NULL,  
  `CLIENT_EMAIL` varchar(255) NOT NULL,
  `CLIENT_TELEFN` varchar(255) DEFAULT NULL,
  `CLIENT_ROL` int NOT NULL,
  `CLIENT_PASWRD` varchar(255) NOT NULL,
  PRIMARY KEY (`CLIENT_ID`),
  UNIQUE KEY (`CLIENT_USER`),
  KEY `FK_CLIENT_ROL` (`CLIENT_ROL`),
  CONSTRAINT `FK_CLIENT_ROL` FOREIGN KEY (`CLIENT_ROL`) REFERENCES `ROLES` (`ROL_ID`)
) ENGINE = InnoDB /*AUTO_INCREMENT = 1*/ DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- MESAS: OK, VER LO DE LOS ESTADOS 
CREATE TABLE `MESAS` (
  `MESA_ID` int NOT NULL AUTO_INCREMENT,
  `MESA_SILLAS` int NOT NULL,
  `MESA_OBSERV` varchar(255) DEFAULT NULL,
  `MESA_ESTADO` varchar(255) NOT NULL,
  `MESA_USUARIO` int DEFAULT NULL,
  `MESA_LOCAL` int NOT NULL,
  PRIMARY KEY (`MESA_ID`),
  KEY `FK_MESA_USUARIO` (`MESA_USUARIO`),
  KEY `FK_MESA_LOCAL` (`MESA_LOCAL`),
  CONSTRAINT `FK_MESA_USUARIO` FOREIGN KEY (`MESA_USUARIO`) REFERENCES `USUARIOS` (`USER_ID`),
  CONSTRAINT `FK_MESA_LOCAL` FOREIGN KEY (`MESA_LOCAL`) REFERENCES `LOCALES` (`LOCAL_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- TURNOS: 
CREATE TABLE `TURNOS` (
  `TURNO_ID` int NOT NULL AUTO_INCREMENT,
  `TURNO_HORARIO` varchar(255) NOT NULL,  
  `TURNO_ESTADO` int NOT NULL,
  PRIMARY KEY (`TURNO_ID`)  
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- RESEVAS: OK, ver lo de estado.
CREATE TABLE `RESERVAS` (
  `RESERV_ID` int NOT NULL AUTO_INCREMENT,
  `RESERV_CLIENTE` int NOT NULL, 
  `RESERV_FECALT` date NOT NULL,
  `RESERV_FECRES` datetime NOT NULL,
  -- `RESERV_COMENS` int NOT NULL,
  `RESERV_MESA` int NOT NULL,
  `RESERV_ESTADO` int NOT NULL,
  `RESERV_TURNO` int NOT NULL,
  PRIMARY KEY (`RESERV_ID`),
  KEY `FK_RESERV_MESA` (`RESERV_MESA`),
  KEY `FK_RESERV_ESTADO` (`RESERV_ESTADO`),
  KEY `FK_RESERV_TURNO` (`RESERV_TURNO`),
  CONSTRAINT `FK_RESERV_MESA` FOREIGN KEY (`RESERV_MESA`) REFERENCES `MESAS` (`MESA_ID`),
  CONSTRAINT `FK_RESERV_ESTADO` FOREIGN KEY (`RESERV_ESTADO`) REFERENCES `ESTADOS` (`ESTADO_ID`),
  CONSTRAINT `FK_RESERV_TURNO` FOREIGN KEY (`RESERV_TURNO`) REFERENCES `TURNOS` (`TURNO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- MESAUSOS:
CREATE TABLE `MESAUSOS` (
  `MESAUSO_ID` int NOT NULL AUTO_INCREMENT,
  `MESAUSO_MESA` int NOT NULL,
  -- `MESAUSO_COMPROBANTE` int NOT NULL,
  -- `MESAUSO_COMANDA` int NOT NULL,
  -- `MESAUSO_ITEMCOMANDA` int NOT NULL,
  -- `MESAUSO_TOTAL` double DEFAULT NULL,
  PRIMARY KEY (`MESAUSO_ID`),
  KEY `FK_MESAUSO_MESA` (`MESAUSO_MESA`),
  -- KEY `FK_MESAUSO_COMPROBANTE` (`MESAUSO_COMPROBANTE`),
  -- KEY `FK_MESAUSO_COMANDA` (`MESAUSO_COMANDA`),
  CONSTRAINT `FK_MESAUSO_MESA` FOREIGN KEY (`MESAUSO_MESA`) REFERENCES `MESAS` (`MESA_ID`)
  -- CONSTRAINT `FK_MESAUSO_COMPROBANTE` FOREIGN KEY (`MESAUSO_COMPROBANTE`) REFERENCES `COMPROBANTES` (`COMPROBANTE_ID`)
  -- CONSTRAINT `FK_MESAUSO_COMANDA` FOREIGN KEY (`MESAUSO_COMANDA`) REFERENCES `COMANDAS` (`COMAND_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

ALTER TABLE `COMPROBANTES` ADD `COMPROBANTE_FECHA` datetime DEFAULT NULL AFTER `COMPROBANTE_ID`;

-- COMPROBANTES:
CREATE TABLE `COMPROBANTES` (
  `COMPROBANTE_ID` int NOT NULL AUTO_INCREMENT,
  `COMPROBANTE_FECHA` datetime DEFAULT NULL,
  `COMPROBANTE_MESAUSO` int NOT NULL,
  `COMPROBANTE_TOTAL` double,
  PRIMARY KEY (`COMPROBANTE_ID`),  
  UNIQUE KEY (`COMPROBANTE_MESAUSO`),
  KEY `FK_COMPROBANTE_MESAUSO` (`COMPROBANTE_MESAUSO`),
  CONSTRAINT `FK_COMPROBANTE_MESAUSO` FOREIGN KEY (`COMPROBANTE_MESAUSO`) REFERENCES `MESAUSOS` (`MESAUSO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `ITEMS_COMPROBANTE` (
	`ITMCOM_ID` int NOT NULL AUTO_INCREMENT,
	`COMPRO_ID` int NOT NULL,
	`ITMCOM_PRODUC` INT,
	`ITMCOM_CANTID` INT,
	PRIMARY KEY (`ITMCOM_ID`), 
    KEY `FK_ITMCOM_ID` (`ITMCOM_ID`),
	KEY `FK_COMPRO_ID` (`COMPRO_ID`),
  CONSTRAINT `FK_ITMCOM_PRODUC` FOREIGN KEY (`ITMCOM_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- COMANDAS: saco MENU y RESERVA, cambio mesa x mesauso
CREATE TABLE `COMANDAS` (
  `COMAND_ID` int NOT NULL AUTO_INCREMENT,
  -- `COMAND_RESERV` int NOT NULL,
  -- `COMAND_MESA` int NOT NULL,
  -- `COMAND_MENU` int NOT NULL,
  `COMAND_ESTADO` int NOT NULL,
  `COMAND_MESAUSO` int NOT NULL,
  PRIMARY KEY (`COMAND_ID`),
  -- KEY `FK_COMAND_RESERV` (`COMAND_RESERV`),
  -- KEY `FK_COMAND_MESA` (`COMAND_MESA`),
  -- KEY `FK_COMAND_MENU` (`COMAND_MENU`),
  KEY `FK_COMAND_ESTADO` (`COMAND_ESTADO`),
  KEY `FK_COMAND_MESAUSO` (`COMAND_MESAUSO`),
  -- CONSTRAINT `FK_COMAND_RESERV` FOREIGN KEY (`COMAND_RESERV`) REFERENCES `RESERVAS` (`RESERV_ID`),
  -- CONSTRAINT `FK_COMAND_MESA` FOREIGN KEY (`COMAND_MESA`) REFERENCES `MESAS` (`MESA_ID`),
  -- CONSTRAINT `FK_COMAND_MENU` FOREIGN KEY (`COMAND_MENU`) REFERENCES `MENU` (`MENU_ID`),
  CONSTRAINT `FK_COMAND_ESTADO` FOREIGN KEY (`COMAND_ESTADO`) REFERENCES `ESTADOS` (`ESTADO_ID`),
  CONSTRAINT `FK_COMAND_MESAUSO` FOREIGN KEY (`COMAND_MESAUSO`) REFERENCES `MESAUSOS` (`MESAUSO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- COMITEMS: queda pendiente, replico y la hago de prueba en ITEMCOMANDAS
/*CREATE TABLE `COMITEMS` (
  `COMITS_ID` int NOT NULL AUTO_INCREMENT,
  `COMITS_COMAND` int NOT NULL,
  `COMITS_PRODUC` int NOT NULL,
  `COMITS_CANTID` int NOT NULL,
  `COMITS_ESTADO` int NOT NULL,
  PRIMARY KEY (`COMITS_ID`),
  KEY `FK_COMITS_COMAND` (`COMITS_COMAND`),
  KEY `FK_COMITS_PRODUC` (`COMITS_PRODUC`),
  KEY `FK_COMITS_ESTADO` (`COMITS_ESTADO`),
  CONSTRAINT `FK_COMITS_COMAND` FOREIGN KEY (`COMITS_COMAND`) REFERENCES `COMANDAS` (`COMAND_ID`),
  CONSTRAINT `FK_COMITS_PRODUC` FOREIGN KEY (`COMITS_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`),
  CONSTRAINT `FK_COMITS_ESTADO` FOREIGN KEY (`COMITS_ESTADO`) REFERENCES `ESTADOS` (`ESTADO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;*/

-- ITEMCOMANDAS: 
CREATE TABLE `ITEMCOMANDAS` (
  `ITEMCOM_ID` int NOT NULL AUTO_INCREMENT,
  `ITEMCOM_COMANDA` int NOT NULL,
  `ITEMCOM_PRODUC` int NOT NULL,
  `ITEMCOM_PRECIO` double DEFAULT NULL,
  `ITEMCOM_CANTIDAD` int NOT NULL,
  `ITEMCOM_TOTAL` double DEFAULT NULL,  
  PRIMARY KEY (`ITEMCOM_ID`),
  KEY `FK_ITEMCOM_COMANDA` (`ITEMCOM_COMANDA`),
  KEY `FK_ITEMCOM_PRODUC` (`ITEMCOM_PRODUC`),
  -- KEY `FK_COMITS_ESTADO` (`COMITS_ESTADO`),
  CONSTRAINT `FK_ITEMCOM_COMANDA` FOREIGN KEY (`ITEMCOM_COMANDA`) REFERENCES `COMANDAS` (`COMAND_ID`),
  CONSTRAINT `FK_ITEMCOM_PRODUC` FOREIGN KEY (`ITEMCOM_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`)
  -- CONSTRAINT `FK_COMITS_ESTADO` FOREIGN KEY (`COMITS_ESTADO`) REFERENCES `ESTADOS` (`ESTADO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;


/********** PARA ELIMINAR LAS TABLAS **********/
/*
DROP TABLE ITEMCOMANDAS;
DROP TABLE COMANDAS;
DROP TABLE ITEMS_COMPROBANTE;
DROP TABLE PRODUCTOS;
DROP TABLE CATEGORIAS;
DROP TABLE RESERVAS;
DROP TABLE COMPROBANTES;
DROP TABLE MESAUSOS;
DROP TABLE MESAS;
DROP TABLE LOCALES;
DROP TABLE USUARIOS;
DROP TABLE CLIENTES;
DROP TABLE ESTADOS;
DROP TABLE ROLES;
DROP TABLE TURNOS;
*/

/********** PARA BORRAR LAS TABLAS (DATOS) **********/
TRUNCATE TABLE LOCALES;
TRUNCATE TABLE USUARIOS;
TRUNCATE TABLE CLIENTES;
TRUNCATE TABLE ROLES;
TRUNCATE TABLE MESAS;
TRUNCATE TABLE RESERVAS;
TRUNCATE TABLE ESTADOS;
TRUNCATE TABLE PRODUCTOS;
TRUNCATE TABLE CATEGORIAS;
TRUNCATE TABLE COMANDAS;
TRUNCATE TABLE ITEMCOMANDAS;
TRUNCATE TABLE MESAUSOS;
TRUNCATE TABLE COMPROBANTES;
TRUNCATE TABLE ITEMS_COMPROBANTE;
TRUNCATE TABLE TURNOS;

/********** INSERTAR REGISTROS **********/
INSERT INTO `comandabd`.`CATEGORIAS`
(`CATEGO_NOMBRE`/*,`CATEGO_DESCRP`*/)
VALUES
('Entradas'/*,'Entradas'*/),
('Parrilla'/*,'Cortes a la leña'*/),
('Pastas'/*,'Pastas caseras'*/),
('Minutas'/*,'Minutas'*/),
('Pizzas y Empanadas'/*,''*/),
('Bebidas'/*,'Jugos, aguas y gaseosas'*/),
('Postres'/*,'Postres caseros'*/),
('Vinos'/*,'Bodega selección propia'*/)
;

/*INSERT INTO `comandabd`.`ETIQUETAS`
(`ETIQUE_NOMBRE`,`ETIQUE_DESCRP`)
VALUES
('Varios','Varios.'),
('Vegano','Productos sin origen animal.'),
('Vegetariano','Productos sin carne.'),
('Sodio','Productos sin sodio agregado.'),
('Light','Productos bajos en grasas.'),
('Kids','Productos aptos para chicos.')
;*/

INSERT INTO `comandabd`.`PRODUCTOS`
(`PRODUC_NOMBRE`,`PRODUC_DESCRP`,`PRODUC_PRECIO`,`PRODUC_CATEGO`,`PRODUC_IMG`)
VALUES
('Provoleta','Provoleta grillada a la parrilla con finas hierbas.',950,1,'provo.png'),
('Mozzarelitas','Bastones de mozzarella con panko y salsa criolla.',1100,1,'mozza.png'),
('Tabla de Quesos','Variedad de quesos.',850,1,'queso.png'),
('Entraña Provenzal','Entraña a la provenzal con papas fritas.',2600,2,'entrana.png'),
('Vacío','Porción de vacío con guarnición.',2300,2,'vacio.png'),
('Asado','Porción de asado con guarnición.',2300,2,'asado.png'),
('Mix de achuras','Molleja, riñón, chinchulín, chorizo y morcilla.',2750,2,'achuras.png'),
('Tallarines Fileto','Tallarines con salsa fileto',1300,3,'tallarines.png'),
('Tagliatelle Bolognesa','Tagliatelle con salsa bolognesa.',1550,3,'taglia.png'),
('Ñoquis Azafrán','Ñoquis con salsa crema de azafrán.',1450,3,'noquis.png'),
('Tortilla de Papas','Clásica tortilla de papas con cebolla. Para compartir.',1900,4,'tortilla.png'),
('Empanada Casera','Empanda sabor a elección: carne, pollo, jamon y queso.',250,5,'empanada.png'),
('Pizza Mozzarella Chica','Pizza Mozzarella 6 porciones.',1650,5,'pizzamuzza.png'),
('Pizza Mozzarella Grande','Pizza Mozzarella 8 porciones, para compartir.',2450,5,'pizzamuzza.png'),
('Aguas','Agua con o sin gas.',650,6,'agua.png'),
('Gaseosas','Gaseosa lína Coca - Cola.',650,6,'gaseosa.png'),
('Jarra de Limonada','Limonada con menta y jengibre.',950,6,'limonada.png'),
('Flan Casero','Flan casero con crema y dulce de leche.',1500,7,'flan.png'),
('Helado','Dos bochas de helado, gustos a elección.',1300,7,'helado.png'),
('Trumpeter','Malbec, Cabernet - Sauvignon.',2500,8,'trumpeter.png'),
('Lugi Bosca','Malbec, Cabernet - Sauvignon.',300,8,'luigibosca.png')
;

/*INSERT INTO `comandabd`.`PRECIOS`
(`PRELIS_ID`,`PRELIS_NOMBRE`,`PRELIS_PRODUC`,`PRELIS_PRECIO`,`PRELIS_VIGDDE`,`PRELIS_VIGHTA`)
VALUES 
(1,'2022/10',1,1000.00,'20221001','20221031'),
(1,'2022/10',2,850.00,'20221001','20221031'),
(1,'2022/10',3,2100.00,'20221001','20221031'),
(1,'2022/10',4,3200.00,'20221001','20221031'),
(1,'2022/10',5,2800.00,'20221001','20221031'),
(2,'2022/11',1,1200.00,'20221101','20221030'),
(2,'2022/11',2,1150.00,'20221101','20221030'),
(2,'2022/11',3,2450.00,'20221101','20221030'),
(2,'2022/11',4,3400.00,'20221101','20221030'),
(2,'2022/11',5,3100.00,'20221101','20221030'),
(2,'2022/11',14,3200.00,'20221101','20221030')
;*/

/*INSERT INTO `comandabd`.`MENU`
(`MENU_NOMBRE`,`MENU_VIGDDE`,`MENU_VIGHTA`,`MENU_PRELIS`)
VALUES 
('Ejecutivo - Primavera 2022','20220901','20220930',1),
('Mediodía - Primavera 2022','20220901','20220930',1),
('Fin de Semana - Primavera 2022','20220901','20221230',1),
('Ejecutivo - Verano 2023','20230101','20230331',2),
('Mediodía - Verano 2023','20230101','20230331',2),
('Fin de Semana - Verano 2023','20230101','20230331',2)
;*/

/*INSERT INTO `comandabd`.`MENUIT`
(`MENUIT_MENU`,`MENUIT_PRODUC`)
VALUES 
(1,1),
(1,3),
(1,8),
(1,13),
(1,14),
(4,2),
(4,3),
(4,5),
(4,6),
(4,14)
;*/

INSERT INTO `comandabd`.`LOCALES`
(`LOCAL_NOMBRE`,`LOCAL_CALLE`,`LOCAL_ALTURA`,`LOCAL_CODPOS`,`LOCAL_TELEFN`,`LOCAL_IMG`)
VALUES 
('Kentucky Palermo','Calle Palermo',1234,1111,'44444444','kentuckypal.png'),
('Kentucky Belgrano','Calle Belgrano',1234,1111,'44444444','kentuckybel.png'),
('Kentucky Recoleta','Calle Recoleta',1234,1111,'44444444','kentuckyrec.png'),
('Kentucky Microcentro 1','Calle Microcentro 1',1234,1111,'44444444','kentuckymic1.png'),
('Kentucky Microcentro 2','Calle Microcentro 2',1234,1111,'44444444','kentuckymic2.png')
;

INSERT INTO `comandabd`.`ROLES`
(/*`ROL_ID`,*/`ROL_NOMBRE`)
VALUES 
(/*1,*/'ADMIN'),
(/*2,*/'USER_LOCAL'),
(/*3,*/'USER_APP')
;

INSERT INTO `comandabd`.`USUARIOS`
(/*`USER_ID`,*/`USER_USER`,`USER_NOMBRE`,`USER_APELLI`,`USER_NRODOC`,`USER_EMAIL`,`USER_TELEFN`,`USER_ROL`,`USER_PASWRD`)
VALUES 
(/*1,*/'RMILANO','Rodrigo','Miliano',99999999,'rodrigo.miliano@davinci.edu.ar','1167390317',1,'1234'),
(/*2,*/'MPRINCIPE','Maximiliano','Príncipe',99999999,'maximiliano.principe@davinci.edu.ar','1131681566',2,'1234'),
(/*3,*/'IESSES','Ignacio','Esses',99999999,'ignacio.esses@davinci.edu.ar','1169344326',2,'1234')
;

INSERT INTO `comandabd`.`CLIENTES`
(/*`USER_ID`,*/`CLIENT_USER`,`CLIENT_NOMBRE`,`CLIENT_APELLI`,`CLIENT_NRODOC`,`CLIENT_EMAIL`,`CLIENT_TELEFN`,`CLIENT_ROL`,`CLIENT_PASWRD`)
VALUES 
(/*1,*/'JMILANO','Juan','Miliano',99999999,'rodrigo.miliano@davinci.edu.ar','1167390317',3,'1234'),
(/*2,*/'JPRINCIPE','Juan','Príncipe',99999999,'maximiliano.principe@davinci.edu.ar','1131681566',3,'1234'),
(/*3,*/'JESSES','Juan','Esses',99999999,'ignacio.esses@davinci.edu.ar','1169344326',3,'1234')
;

INSERT INTO `comandabd`.`MESAS` /*OJO: aca cambie el string por integer para hacer el join con estados*/
(`MESA_SILLAS`,`MESA_OBSERV`,`MESA_ESTADO`,`MESA_USUARIO`,`MESA_LOCAL`)
VALUES 
(2,'',1,1,1),
(2,'Cerca de los baños.',1,1,1),
(4,'',2,2,1),
(4,'',1,3,1),
(4,'Ventanal.',3,3,1),
(4,'Ventanal.',4,1,2),
(4,'',4,1,2),
(6,'',1,2,2),
(6,'',1,3,2),
(8,'Solo reservas.',1,3,2)
;

INSERT INTO `comandabd`.`ESTADOS`
(`ESTADO_NOMBRE`,`ESTADO_DESCRP`)
VALUES 
('ALTA','De alta'),
('BAJA','Dado de baja'),
('RESERVADO','Para mesas reservadas'),
('OCUPADO','Para mesas ocupadas'),
('PREPARANDO','Producto en preparación'),
('DESPACHADO','Producto despachado'),
('EN CURSO','Para comanda activa'),
('FINALIZADO','Para comanda finalizada sin facturar'),
('CERRADO','Para comanda finalizada y facturada'),
('CANCELADO','Cancelado')
;

INSERT INTO `comandabd`.`TURNOS`
(`TURNO_HORARIO`,`TURNO_ESTADO`)
VALUES 
('12:00HS',1),
('14:00HS',4),
('20:00HS',5),
('22:00HS',1)
;

INSERT INTO `comandabd`.`RESERVAS`
(`RESERV_CLIENTE`,`RESERV_FECALT`,`RESERV_FECRES`,/*`RESERV_COMENS`,*/`RESERV_MESA`,`RESERV_ESTADO`,`RESERV_TURNO`)
VALUES 
(3,'2023-02-05','2023-02-06 20:00:00',/*4,*/5,1,1),
(3,'2023-02-05','2023-02-13 21:00:00',/*4,*/5,1,3),
(2,'2023-02-05','2023-02-08 20:00:00',/*2,*/1,1,3),
(1,'2023-02-05','2023-02-10 20:00:00',/*7,*/10,1,4)
;

INSERT INTO `comandabd`.`MESAUSOS`
(`MESAUSO_MESA`/*,`MESAUSO_COMPROBANTE`,`MESAUSO_TOTAL`*/)
VALUES 
(5),
(5),
(8)
;

INSERT INTO `comandabd`.`COMPROBANTES`
(`COMPROBANTE_ID`,`COMPROBANTE_MESAUSO`)
VALUES 
(1,1),
(2,2),
(3,3)
;

INSERT INTO `comandabd`.`COMANDAS`
(/*`COMAND_RESERV`*/`COMAND_ESTADO`,`COMAND_MESAUSO`)
VALUES 
(7,1),
(4,1),
(5,1)
;

/*INSERT INTO `comandabd`.`COMITEMS`
(`COMITS_COMAND`,`COMITS_PRODUC`,`COMITS_CANTID`,`COMITS_ESTADO`)
VALUES 
(1,1,2,6),
(1,5,1,6),
(1,3,1,10),
(1,14,1,5)
;*/

INSERT INTO `comandabd`.`ITEMCOMANDAS`
(`ITEMCOM_COMANDA`,`ITEMCOM_PRODUC`,`ITEMCOM_CANTIDAD`/*,`ITEMCOM_TOTAL`*/)
VALUES 
(1,1,2),
(1,2,1),
(2,5,1),
(3,8,2)
;




/******************** PARA ANALIZAR GENERACIÓN DE COMPROBANTES ********************/
SET @MESAUSO = 2;
SET @NROCOM = (SELECT CASE WHEN ISNULL(MAX(COMPROBANTE_MESAUSO)) THEN 1 ELSE MAX(COMPROBANTE_MESAUSO) + 1 END AS NROCOM FROM COMPROBANTES);
SET @TOTAL = (SELECT SUM(ITEMCOM_TOTAL) FROM COMANDAS INNER JOIN ITEMCOMANDAS ON COMAND_ID = ITEMCOM_COMANDA WHERE COMAND_MESAUSO = @MESAUSO);

INSERT INTO `comandabd`.`COMPROBANTES`
(`COMPROBANTE_ID`,`COMPROBANTE_MESAUSO`,`COMPROBANTE_TOTAL`)
VALUES
(@NROCOM, @MESAUSO, @TOTAL)
;

SELECT * FROM COMPROBANTES;
SELECT * FROM ITEMS_COMPROBANTE;

/***************************************************/

DELIMITER $$
	DROP PROCEDURE IF EXISTS insertarComprobanteItems $$
	CREATE PROCEDURE insertarComprobanteItems (COMPROB INT, MESAUSO INT) 
BEGIN
    DECLARE PRODUCTO INT;
    DECLARE CANTIDAD INT;

	DEClARE ITEMS
		CURSOR FOR 
			SELECT ITEMCOM_PRODUC, SUM(ITEMCOM_CANTIDAD) 
            FROM COMANDAS INNER JOIN ITEMCOMANDAS ON COMAND_ID = ITEMCOM_COMANDA 
            WHERE COMAND_MESAUSO = MESAUSO GROUP BY ITEMCOM_PRODUC;
	
	OPEN ITEMS;

	FETCH ITEMS INTO PRODUCTO, CANTIDAD;

        INSERT INTO `comandabd`.`ITEMS_COMPROBANTE`
		(`COMPRO_ID`,`ITMCOM_PRODUC`,`ITMCOM_CANTID`)
		VALUES
		(COMPROB, PRODUCTO, CANTIDAD);

	CLOSE ITEMS;
END$$
DELIMITER ;

CALL insertarComprobanteItems (1, 1);
CALL insertarComprobanteItems (2, 2);

/******************** HASTA ACÁ ********************/

/********** CONSULTAR LAS TABLAS **********/
SELECT * FROM CATEGORIAS;
SELECT * FROM PRODUCTOS;
SELECT * FROM LOCALES;
SELECT * FROM USUARIOS;
SELECT * FROM CLIENTES;
SELECT * FROM ROLES;
SELECT * FROM MESAS;
SELECT * FROM RESERVAS;
SELECT * FROM ESTADOS;
SELECT * FROM COMANDAS;
SELECT * FROM ITEMCOMANDAS;
SELECT * FROM MESAUSOS;
SELECT * FROM COMPROBANTES;
SELECT * FROM ITEMS_COMPROBANTE;
SELECT * FROM TURNOS;

-- LISTADO DE PRECIOS POR CATEGORÍA Y VIGENCIA:
SELECT CATEGO_DESCRP CATEGORIA, PRODUC_ID ID, PRODUC_DESCRP PRODUCTO, PRELIS_PRECIO PRECIO, ETIQUE_DESCRP ETIQUETA
FROM PRODUCTOS
INNER JOIN CATEGORIAS ON PRODUCTOS.PRODUC_CATEGO = CATEGORIAS.CATEGO_ID
INNER JOIN ETIQUETAS ON PRODUCTOS.PRODUC_ETIQUE = ETIQUETAS.ETIQUE_ID
INNER JOIN PRECIOS ON PRODUCTOS.PRODUC_ID = PRECIOS.PRELIS_PRODUC
WHERE PRELIS_VIGDDE <= '20221015' AND PRELIS_VIGHTA >= '20221015'
ORDER BY CATEGORIAS.CATEGO_ID;

-- USUARIOS CON SUS ROLES:
SELECT USER_USER, ROL_NOMBRE FROM USUARIOS
INNER JOIN ROLES ON USER_ROL = ROL_ID;

-- MENU CON ITEMS, PRODUCTOS Y PRECIOS: --> REVISAR
/*SELECT * FROM MENU
INNER JOIN MENUIT ON MENUIT_MENU = MENU_ID
INNER JOIN PRODUCTOS ON MENUIT_PRODUC = PRODUC_ID
INNER JOIN PRECIOS ON PRELIS_ID = MENU_PRELIS;*/

-- EJEMPLO:
SELECT USER_USER, RESERV_FECRES, PRODUC_NOMBRE, PRODUC_DESCRP, COMITS_CANTID, ESTADO_NOMBRE, COMITS_CANTID * PRELIS_PRECIO AS TOTAL 
FROM COMITEMS
INNER JOIN COMANDAS ON COMAND_ID = COMITS_COMAND
INNER JOIN PRODUCTOS ON PRODUC_ID = COMITS_PRODUC
INNER JOIN ESTADOS ON ESTADO_ID = COMITS_ESTADO
INNER JOIN RESERVAS ON COMAND_RESERV = RESERV_ID
INNER JOIN USUARIOS ON RESERV_USER = USER_ID
INNER JOIN PRECIOS ON PRELIS_PRODUC = COMITS_PRODUC AND PRELIS_NOMBRE = '2022/11';

/********** ACTUALIZAR REGISTROS **********/
UPDATE CATEGORIAS SET CATEGO_DESCRP = 'Pizzas y Empanadas' WHERE CATEGO_ID = 5;

/********** ELIMINAR REGISTROS **********/
DELETE CATEGORIAS FROM CATEGORIAS WHERE CATEGO_ID >= 1;
DELETE PRODUCTOS FROM PRODUCTOS WHERE PRODUC_ID >= 1;

/********** RESTAURAR NUMERACIÓN (ID) **********/
ALTER TABLE PRODUCTOS AUTO_INCREMENT = 0;
ALTER TABLE CATEGORIAS AUTO_INCREMENT = 0;
ALTER TABLE LOCALES AUTO_INCREMENT = 0;
ALTER TABLE USUARIOS AUTO_INCREMENT = 0;
ALTER TABLE CLIENTES AUTO_INCREMENT = 0;
ALTER TABLE ROLES AUTO_INCREMENT = 0;
ALTER TABLE MESAS AUTO_INCREMENT = 0;
ALTER TABLE RESERVAS AUTO_INCREMENT = 0;
ALTER TABLE ESTADOS AUTO_INCREMENT = 0;
ALTER TABLE COMANDAS AUTO_INCREMENT = 0;
ALTER TABLE ITEMCOMANDAS AUTO_INCREMENT = 0;
ALTER TABLE MESAUSOS AUTO_INCREMENT = 0;
ALTER TABLE COMPROBANTES AUTO_INCREMENT = 0;
ALTER TABLE TURNOS AUTO_INCREMENT = 0;


/* SIN USO
SELECT @MAX := MAX(ID)+ 1 FROM CATEGORIAS;
PREPARE stmt FROM 'ALTER TABLE CATEGORIAS AUTO_INCREMENT = ?';
EXECUTE stmt USING @max;
DEALLOCATE PREPARE stmt;
*/