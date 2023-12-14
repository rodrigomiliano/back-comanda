/*********PARA CREAR TABLAS E INSERTAR DATOS: Ejecutar desde el principio hasta 439 (donde termina el insert)*******/////

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
  `CATEGO_IMG` varchar(255) ,
  PRIMARY KEY (`CATEGO_ID`),
  UNIQUE KEY (`CATEGO_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- LOCALES: OK
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

-- PRODCUTOS: OK
CREATE TABLE `PRODUCTOS` (
  `PRODUC_ID` int NOT NULL AUTO_INCREMENT,
  `PRODUC_NOMBRE` varchar(255) NOT NULL,
  `PRODUC_DESCRP` varchar(255) DEFAULT NULL,
  `PRODUC_PRECIO` double NOT NULL,
  `PRODUC_CATEGO` int NOT NULL,
  `PRODUC_IMG` varchar(255) ,
  `PRODUC_LOCAL` int NOT NULL,  
  PRIMARY KEY (`PRODUC_ID`),
  -- UNIQUE KEY (`PRODUC_NOMBRE`),
  KEY `FK_PRODUC_CATEGO_ID` (`PRODUC_CATEGO`),  
  KEY `FK_PRODUC_LOCAL_ID` (`PRODUC_LOCAL`),    
  CONSTRAINT `FK_PRODUC_CATEGO` FOREIGN KEY (`PRODUC_CATEGO`) REFERENCES `CATEGORIAS` (`CATEGO_ID`),
  CONSTRAINT `FK_PRODUC_LOCAL` FOREIGN KEY (`PRODUC_LOCAL`) REFERENCES `LOCALES` (`LOCAL_ID`)  
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ESTADOS: OK
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
  /*`CLIENT_ROL` int NOT NULL,*/
  `CLIENT_PASWRD` varchar(255) NOT NULL,
  PRIMARY KEY (`CLIENT_ID`),
  UNIQUE KEY (`CLIENT_USER`)
  /*KEY `FK_CLIENT_ROL` (`CLIENT_ROL`),*/
  /*CONSTRAINT `FK_CLIENT_ROL` FOREIGN KEY (`CLIENT_ROL`) REFERENCES `ROLES` (`ROL_ID`)*/
) ENGINE = InnoDB /*AUTO_INCREMENT = 1*/ DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- MESAS: OK
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

-- TURNOS: OK (POSIBLEMENTE NO SE USE)
CREATE TABLE `TURNOS` (
  `TURNO_ID` int NOT NULL AUTO_INCREMENT,
  `TURNO_HORARIO` varchar(255) NOT NULL,
  `TURNO_ESTADO` int NOT NULL,
  PRIMARY KEY (`TURNO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- RESEVAS: OK (POSIBLEMENTE NO SE USE)
CREATE TABLE `RESERVAS` (
  `RESERV_ID` int NOT NULL AUTO_INCREMENT,
  `RESERV_CLIENTE` int NOT NULL,
  `RESERV_FECALT` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  -- `RESERV_FECALT` date DEFAULT NULL,
  -- `RESERV_FECALT` date NOT NULL,
  -- `COMPROBANTE_FECHA` datetime DEFAULT NULL,
  `RESERV_FECRES` datetime NOT NULL,  
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

-- MESAUSOS: OK
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

-- COMPROBANTES: OK
CREATE TABLE `COMPROBANTES` (
  `COMPROBANTE_ID` int NOT NULL AUTO_INCREMENT,
  `COMPROBANTE_FECHA` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
  `COMPROBANTE_MESAUSO` int NOT NULL,
  `COMPROBANTE_TOTAL` double,
  PRIMARY KEY (`COMPROBANTE_ID`),
  UNIQUE KEY (`COMPROBANTE_MESAUSO`),
  KEY `FK_COMPROBANTE_MESAUSO` (`COMPROBANTE_MESAUSO`),
  CONSTRAINT `FK_COMPROBANTE_MESAUSO` FOREIGN KEY (`COMPROBANTE_MESAUSO`) REFERENCES `MESAUSOS` (`MESAUSO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ITEMS_COMPROBANTE: OK
CREATE TABLE `ITEMS_COMPROBANTE` (
	`ITMCOM_ID` int NOT NULL AUTO_INCREMENT,
	`COMPRO_ID` INT,
	`ITMCOM_PRODUC` INT,
	`ITMCOM_CANTID` INT,
	PRIMARY KEY (`ITMCOM_ID`),
    KEY `FK_ITMCOM_ID` (`ITMCOM_ID`),
	KEY `FK_COMPRO_ID` (`COMPRO_ID`),
  CONSTRAINT `FK_ITMCOM_PRODUC` FOREIGN KEY (`ITMCOM_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- COMANDAS: OK
CREATE TABLE `COMANDAS` (
  `COMAND_ID` int NOT NULL AUTO_INCREMENT,
  -- `COMAND_RESERV` int NOT NULL,
  -- `COMAND_MESA` int NOT NULL,  
  `COMAND_ESTADO` int,
  `COMAND_MESAUSO` int NOT NULL,
  PRIMARY KEY (`COMAND_ID`),
  -- KEY `FK_COMAND_RESERV` (`COMAND_RESERV`),
  -- KEY `FK_COMAND_MESA` (`COMAND_MESA`),  
  KEY `FK_COMAND_ESTADO` (`COMAND_ESTADO`),
  KEY `FK_COMAND_MESAUSO` (`COMAND_MESAUSO`),
  -- CONSTRAINT `FK_COMAND_RESERV` FOREIGN KEY (`COMAND_RESERV`) REFERENCES `RESERVAS` (`RESERV_ID`),
  -- CONSTRAINT `FK_COMAND_MESA` FOREIGN KEY (`COMAND_MESA`) REFERENCES `MESAS` (`MESA_ID`),  
  CONSTRAINT `FK_COMAND_ESTADO` FOREIGN KEY (`COMAND_ESTADO`) REFERENCES `ESTADOS` (`ESTADO_ID`),
  CONSTRAINT `FK_COMAND_MESAUSO` FOREIGN KEY (`COMAND_MESAUSO`) REFERENCES `MESAUSOS` (`MESAUSO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ITEMCOMANDAS: OK
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
  CONSTRAINT `FK_ITEMCOM_COMANDA` FOREIGN KEY (`ITEMCOM_COMANDA`) REFERENCES `COMANDAS` (`COMAND_ID`),
  CONSTRAINT `FK_ITEMCOM_PRODUC` FOREIGN KEY (`ITEMCOM_PRODUC`) REFERENCES `PRODUCTOS` (`PRODUC_ID`)  
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `USUARIOS_LOCALES` (
  `USRLOC_ID` int NOT NULL auto_increment,
  `USRLOC_LOCAL` int NOT NULL,
  `USRLOC_USER` int NOT NULL, 
  PRIMARY KEY (`USRLOC_ID`), 
  KEY `FK_USRLOC_LOCAL` (`USRLOC_LOCAL`),
  KEY `FK_USRLOC_USER` (`USRLOC_USER`),
  CONSTRAINT `FK_USRLOC_LOCAL` FOREIGN KEY (`USRLOC_LOCAL`) REFERENCES `locales` (`LOCAL_ID`),
  CONSTRAINT `FK_USRLOC_USER` FOREIGN KEY (`USRLOC_USER`) REFERENCES `usuarios` (`USER_ID`)  
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

/********** INSERTAR REGISTROS **********/

INSERT INTO `comandabd`.`CATEGORIAS`
(`CATEGO_NOMBRE`,`CATEGO_IMG`)
VALUES
('Entradas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Parrilla','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Pastas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Minutas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Pizzas y Empanadas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Bebidas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Postres','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Vinos','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Hamburguesas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Vegano','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Mexicano','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg'),
('Vegetariano','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg')
;

INSERT INTO `comandabd`.`LOCALES`
(`LOCAL_NOMBRE`,`LOCAL_CALLE`,`LOCAL_ALTURA`,`LOCAL_CODPOS`,`LOCAL_TELEFN`,`LOCAL_IMG`)
VALUES
('Kentucky Almagro','Av. Corrientes',3599,1194,'48625377','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809041/comanda/kentucky_yhvcvz.webp'),
('La Reverde: Parrilla Vegana','Montevideo',40,1019,'43841093','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809040/comanda/la_reverde_xqq2in.jpg'),
('Il Ballo del Mattone','Gorriti',5893,1414 ,'47745681','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809040/comanda/il_ballo_del_mattone_jsqc8g.jpg'),
('Burguer 54','Nueva York',4074,1419,'45038587','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809043/comanda/burguer54_pc1vvs.jpg'),
('La Choza','Gascón',1701,1414,'48333334','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809039/comanda/la_choza_mw8zjq.jpg'),
('No Mames Wey','Fitz Roy',1617,1414,'1150366960','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809046/comanda/no_mames_wey_enj2su.jpg')
;

INSERT INTO `comandabd`.`PRODUCTOS`
(`PRODUC_NOMBRE`,`PRODUC_DESCRP`,`PRODUC_PRECIO`,`PRODUC_CATEGO`,`PRODUC_IMG`,`PRODUC_LOCAL`)
VALUES
('Pizza Mozzarella Grande','Pizza Mozzarella y salsa de tomate 8 porciones, para compartir.',6450,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809029/comanda/muzza_qp3jqs.jpg',1),
('Pizza de Verdura y Salsa blanca','Pizza de 8 porciones con verdura y salsa blanca.',7600,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809034/comanda/verdura_e2y2iu.jpg',1),
('Pizza de Palmitos','Pizza con queso, palmitos, salsa golf y aceitunas.',7300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809028/comanda/palmitos_sghuho.jpg',1),
('Pizza Napolitana','Pizza con mozzarella y rodajas de tomate.',7400,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809026/comanda/napolitana_awmcqf.jpg',1),
('Pizza Jamón y Morrón','Pizza con mozzarella, jamón cocido y morrón.',8300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809024/comanda/jamonymorron_yqajzp.jpg',1),
('Pizza Fugazzeta con Jamón','Pizza con relleno de mozzarella, jamón y cebolla.',9450,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809023/comanda/fugazeta_qg73lk.jpg',1),
('Fainá','Porción de fainá.',600,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809023/comanda/faina_ouyomj.jpg',1),
('Empanada J&Q','Al horno, rellena de jamón y queso.',950,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/empanada_fnqp9v.jpg',1),
('Empanada Pollo','Al horno, rellena de pollo.',950,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/empanada_fnqp9v.jpg',1),
('Empanada Humita','Al horno, rellena de choclo.',950,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/empanada_fnqp9v.jpg',1),
('Quesadilla de queso x 3','Tortilla de maíz con queso y champiñón.',1800,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809038/comanda/quesadilla_n5xkcu.jpg',6),
('Veggie Burguer','Hamburguesa de quinoa, arroz integral, porotos negros y remolacha con lechuga. Tomate y cebolla.',3400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809034/comanda/Veggie-Burger-1_tkocui.png',4),
('Salmón Burguer','Hamburguesa casera de salmon rosado con lechuga morada, cebolla morada, tomate y salsa tártara.',4400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/salmon_xpmznb.png',4),
('Vacío c/papas','Vacío de seitán con papas fritas',4250,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/vacioconpapasvegano_gduegw.jpg',2),
('Tacos de carne x 3','Tacos de RES con cebolla morada, choclo, morrones',4320,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/tacoscarne_ql2fpl.jpg',6),
('Risotto de la casa','Arroz con hongos de estación, cebolla, manteca, queso parmesano y pimienta.',3100,12,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/rissoto_js3dx4.jpg',5),
('Penne rigate y hongos','Fideos penne rigate, portobellos, champingnon, cebolla, puerro y parmesano.',3300,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809031/comanda/pennehongo_nmdhx2.png',5),
('Pollo c/salsa de puerro y papas','Pollo al horno con salsa de puerro y papas noisette.',3710,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809031/comanda/polloypapas_tcti8z.jpg',3),
('Ñoquis c/boloñesa','Ñoquis de papa con salsa boloñesa.',4700,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809030/comanda/noquis-de-calabaza_z8xwir.jpg',3),
('Not Cheese Burguer','Hamburguesa casera vegana a base de plantas con lechuga, tomate, cebolla y salsa burger 54.',3400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809029/comanda/NOT-Cheese-Burger_mq4vew.png',4),
('Bruschetta','Pan con tomate y albahaca picada, queso y aceite de oliva.',1900,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809029/comanda/BruschettaItaliana_dtbukn.jpg',3),
('Nachos c/guacamole','Nachos con guacamole.',2700,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809028/comanda/nachosguacamole_iypjbm.jpg',6),
('Nachos c/cheddar','Nachos con cheddar.',2700,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809026/comanda/nachoschedar_j0mnxv.jpg',6),
('Milanesa napolitana c/puré','Milanesa de carne con salsa de tomate y queso, con puré.',3920,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809025/comanda/milanapopure_pani2y.jpg',3),
('Tacos veggie x 3','Tacos de verduras: cebolla, morrones, hongos, chile',4320,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/tacoscarne_ql2fpl.jpg',6),
('Matambrito c/papas','Matambrito de seitán con papas fritas',4250,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809025/comanda/matambritovegano_bxgjcu.jpg',2),
('Girgolas a la provenzal c/papas','Girgolas a la provenzal con papas fritas',4680,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809024/comanda/girgolasprovenzal_d0vwo7.jpg',2),
('Ensalada mixta','Tomates, cebolla y lechuga',2200,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/ensaladamixta_s1m59r.jpg',5),
('Chicken Sandwich','Pechuga de pollo a la plancha con lechuga, tomate. Cebolla y salsa burger 54.',3300,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809023/comanda/chicken_a2bg4e.png',4),
('Empanada J&Q vegano','Frita, rellena de jamón y queso veganos.',1200,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',2),
('Empanada caprese','Frita, rellena de tomate, albahaca y queso vegano.',1200,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',2),
('Empanada Q&Champi','Frita, rellena de champiñón y queso vegano.',1200,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',2),
('Chorisaurio','Sandwich de chorizo vegano con tomate, lechuga y papas fritas',4400,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/chorisaurio_f5f7gd.jpg',2),
('Cheese Burguer','Hamburguesa casera con queso cheddar, lechuga, tomate, cebolla y salsa burger 54.',3400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809018/comanda/cheeseburger_o72j6s.png',4),
('Breaded Chicken','Sandwich de pechuga de pollo rebozada con lechuga, tomate, cebolla morada y mayonesa.',3400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/Breaded-Chicken-1_vjhdpl.png',4),
('Canelones de verdura','Canelones rellenos de espinaca y ricota, con salsa fileto.',4900,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/canelonesverdura_pxa982.jpg',5),
('Aros de cebolla','Aros empanados de cebolla c/ketchup.',1200,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/aroscebolla_ocs28v.jpg',4),
('Albóndigas c/puré','Albóndigas caseras con salsa roja, con puré.',3600,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/albondigapure_ryjihc.jpg',3),


('Provoleta','Provoleta grillada a la parrilla con finas hierbas.',950,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',1),
('Mozzarelitas','Bastones de mozzarella con panko y salsa criolla.',1100,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',1),
('Tabla de Quesos','Variedad de quesos.',850,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',1),
('Entraña Provenzal','Entraña a la provenzal con papas fritas.',2600,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',1),
('Vacío','Porción de vacío con guarnición.',2300,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',1),
('Asado','Porción de asado con guarnición.',2300,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',2),
('Mix de achuras','Molleja, riñón, chinchulín, chorizo y morcilla.',2750,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',2),
('Tallarines Fileto','Tallarines con salsa fileto',1300,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',2),
('Tagliatelle Bolognesa','Tagliatelle con salsa bolognesa.',1550,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',2),
('Tortilla de Papas','Clásica tortilla de papas con cebolla. Para compartir.',1900,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',3),
('Empanada Casera','Empanda sabor a elección: carne, pollo, jamon y queso.',250,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',3),
('Aguas','Agua con o sin gas.',650,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',3),
('Gaseosas','Gaseosa lína Coca - Cola.',650,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',4),
('Jarra de Limonada','Limonada con menta y jengibre.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',4),
('Flan Casero','Flan casero con crema y dulce de leche.',1500,7,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',4),
('Helado','Dos bochas de helado, gustos a elección.',1300,7,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',4),
('Trumpeter','Malbec, Cabernet - Sauvignon.',2500,8,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg', 4),
('Lugi Bosca','Malbec, Cabernet - Sauvignon.',300,8,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809027/comanda/no-imagen_yvqwrt.jpg',5)
;

INSERT INTO `comandabd`.`ROLES`
(`ROL_NOMBRE`)
VALUES
('ADMIN'),
('USER_LOCAL'),
('USER_APP')
;

INSERT INTO `comandabd`.`USUARIOS`
(`USER_USER`,`USER_NOMBRE`,`USER_APELLI`,`USER_NRODOC`,`USER_EMAIL`,`USER_TELEFN`,`USER_ROL`,`USER_PASWRD`)
VALUES
('RMILANO','Rodrigo','Miliano',99999999,'rodrigo.miliano@davinci.edu.ar','1167390317',1,'1234'),
('MPRINCIPE','Maximiliano','Príncipe',99999999,'maximiliano.principe@davinci.edu.ar','1131681566',2,'1234'),
('IESSES','Ignacio','Esses',99999999,'ignacio.esses@davinci.edu.ar','1169344326',2,'1234')
;

INSERT INTO `comandabd`.`CLIENTES`
(`CLIENT_USER`,`CLIENT_NOMBRE`,`CLIENT_APELLI`,`CLIENT_NRODOC`,`CLIENT_EMAIL`,`CLIENT_TELEFN`,/*`CLIENT_ROL`,*/`CLIENT_PASWRD`)
VALUES
('JMILANO','Juan','Miliano',99999999,'rodrigo.miliano@davinci.edu.ar','1167390317','1234'),
('JPRINCIPE','Juan','Príncipe',99999999,'maximiliano.principe@davinci.edu.ar','1131681566','1234'),
('JESSES','Juan','Esses',99999999,'ignacio.esses@davinci.edu.ar','1169344326','1234')
;

INSERT INTO `comandabd`.`MESAS` 
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
(`RESERV_CLIENTE`,/*`RESERV_FECALT`,*/`RESERV_FECRES`,/*`RESERV_COMENS`,*/`RESERV_MESA`,`RESERV_ESTADO`,`RESERV_TURNO`)
VALUES
(3,/*'2023-02-05',*/'2023-02-06 20:00:00',/*4,*/5,1,1),
(3,/*'2023-02-05',*/'2023-02-13 21:00:00',/*4,*/5,1,3),
(2,/*'2023-02-05',*/'2023-02-08 20:00:00',/*2,*/1,1,3),
(1,/*'2023-02-05',*/'2023-02-10 20:00:00',/*7,*/10,1,4)
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
(5,1),
(1,2),
(1,2)
;

INSERT INTO `comandabd`.`ITEMCOMANDAS`
(`ITEMCOM_COMANDA`,`ITEMCOM_PRODUC`,`ITEMCOM_CANTIDAD`/*,`ITEMCOM_TOTAL`*/)
VALUES
(1,1,2),
(1,2,1),
(2,5,1),
(3,8,2)
;

INSERT INTO `comandabd`.`USUARIOS_LOCALES`
(`USRLOC_LOCAL`,`USRLOC_USER`)
VALUES
(1,1),
(1,2),
(2,3)
;

/********** PARA BORRAR LAS TABLAS (DATOS) **********/
/*
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
*/

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
DROP TABLE USUARIOS_LOCALES;
DROP TABLE LOCALES;
DROP TABLE USUARIOS;
DROP TABLE CLIENTES;
DROP TABLE ESTADOS;
DROP TABLE ROLES;
DROP TABLE TURNOS;
*/

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
SELECT * FROM USUARIOS_LOCALES;


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
ALTER TABLE RESERVAS AUTO_INCREMENT = 4;
ALTER TABLE ESTADOS AUTO_INCREMENT = 0;
ALTER TABLE COMANDAS AUTO_INCREMENT = 5;
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