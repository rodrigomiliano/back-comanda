/************ COLLATION DB ***********/
-- ALTER DATABASE comandabd CHARACTER SET UTF8 COLLATE UTF8_GENERAL_CI;

/********** BASE A UTILIZAR **********/
USE comandabd;

/********** QUITAR VALIDACIÓN **********/
SET SQL_SAFE_UPDATES = 0;

/********** CREACIÓN DE TABLAS **********/

CREATE TABLE `CATEGORIAS` (
   `CATEGO_ID` int NOT NULL AUTO_INCREMENT,
   `CATEGO_NOMBRE` varchar(255) NOT NULL,  
   `CATEGO_IMG` varchar(255),
   `CATEGO_DESTACADO` TINYINT NOT NULL DEFAULT 0,
   PRIMARY KEY (`CATEGO_ID`),
   UNIQUE KEY (`CATEGO_NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
;

CREATE TABLE `LOCALES` (
  `LOCAL_ID` int NOT NULL AUTO_INCREMENT,
  `LOCAL_NOMBRE` varchar(255) NOT NULL,
  `LOCAL_CALLE`	varchar(255) NOT NULL,
  `LOCAL_ALTURA` int NOT NULL,
  `LOCAL_CODPOS` int NOT NULL,
  `LOCAL_TELEFN` int,
  `LOCAL_IMG` varchar(255) NOT NULL,
  `LOCAL_DESCRIPCION` varchar(255) NOT NULL,
  PRIMARY KEY (`LOCAL_ID`),
  UNIQUE KEY (`LOCAL_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

CREATE TABLE `PRODUCTOS` (
  `PRODUC_ID` int NOT NULL AUTO_INCREMENT,
  `PRODUC_NOMBRE` varchar(255) NOT NULL,
  `PRODUC_DESCRP` varchar(255) DEFAULT NULL,
  `PRODUC_PRECIO` double NOT NULL,
  `PRODUC_CATEGO` int NOT NULL,
  `PRODUC_IMG` varchar(255) ,
  `PRODUC_LOCAL` int NOT NULL,  
  `PRODUC_ACTIVO` TINYINT DEFAULT 1,
  PRIMARY KEY (`PRODUC_ID`),
  -- UNIQUE KEY (`PRODUC_NOMBRE`),
  KEY `FK_PRODUC_CATEGO_ID` (`PRODUC_CATEGO`),  
  KEY `FK_PRODUC_LOCAL_ID` (`PRODUC_LOCAL`),    
  CONSTRAINT `FK_PRODUC_CATEGO` FOREIGN KEY (`PRODUC_CATEGO`) REFERENCES `CATEGORIAS` (`CATEGO_ID`),
  CONSTRAINT `FK_PRODUC_LOCAL` FOREIGN KEY (`PRODUC_LOCAL`) REFERENCES `LOCALES` (`LOCAL_ID`)  
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

CREATE TABLE `ESTADOS` (
  `ESTADO_ID` int NOT NULL AUTO_INCREMENT,
  `ESTADO_NOMBRE` varchar(255) NOT NULL,  
  PRIMARY KEY (`ESTADO_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

CREATE TABLE `ROLES` (
  `ROL_ID` int NOT NULL AUTO_INCREMENT,
  `ROL_NOMBRE` varchar(255) NOT NULL,  
  PRIMARY KEY (`ROL_ID`),
  UNIQUE KEY (`ROL_NOMBRE`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

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
  `USER_ACTIVO` TINYINT DEFAULT 0,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY (`USER_USER`),
  UNIQUE KEY (`USER_EMAIL`),
  KEY `FK_USER_ROL` (`USER_ROL`),
  CONSTRAINT `FK_USER_ROL` FOREIGN KEY (`USER_ROL`) REFERENCES `ROLES` (`ROL_ID`)
) ENGINE = InnoDB /*AUTO_INCREMENT = 1*/ DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

CREATE TABLE `COMANDAS` (
  `COMAND_ID` int NOT NULL AUTO_INCREMENT,    
  `COMAND_ESTADO` int,
  `TOTAL` FLOAT,
  `COMAND_MESA` int NOT NULL,
  `FECHA` TIMESTAMP NOT NULL,
  PRIMARY KEY (`COMAND_ID`),    
  KEY `FK_COMAND_ESTADO` (`COMAND_ESTADO`),
  KEY `FK_COMAND_MESA` (`COMAND_MESA`),    
  CONSTRAINT `FK_COMAND_ESTADO` FOREIGN KEY (`COMAND_ESTADO`) REFERENCES `ESTADOS` (`ESTADO_ID`),
  CONSTRAINT `FK_COMAND_MESA` FOREIGN KEY (`COMAND_MESA`) REFERENCES `MESAS` (`MESA_ID`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci
;

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
(`CATEGO_NOMBRE`,`CATEGO_IMG`,`CATEGO_DESTACADO`)
VALUES
('Entradas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744598/entradas_zszzun.jpg',0),
('Parrilla','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744597/parrilla_wy0eyc.jpg',1),
('Pastas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744598/pastas_qyqeeh.jpg',1),
('Minutas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744599/minutas_qr5aql.jpg',0),
('Pizzas y Empanadas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744600/pizza_wjrgnq.png',1),
('Bebidas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744594/bebidas_gukq3d.png',0),
('Postres','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744594/postres_k7zd1v.jpg',0),
('Vinos','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744594/vino_bg9anh.png',0),
('Hamburguesas','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744595/hamburguesas_htqojk.png',1),
('Vegano','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744595/vegano_m1wkbx.jpg',1),
('Mexicano','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744596/mexicano_e2pr78.jpg',1),
('Vegetariano','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704744596/vegetariano_h6s390.png',0)
;

INSERT INTO `comandabd`.`LOCALES`
(`LOCAL_NOMBRE`,`LOCAL_CALLE`,`LOCAL_ALTURA`,`LOCAL_CODPOS`,`LOCAL_TELEFN`,`LOCAL_IMG`,`LOCAL_DESCRIPCION`)
VALUES
('Kentucky Almagro','Av. Corrientes',3599,1194,'48625377','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809041/comanda/kentucky_yhvcvz.webp','Amamos que la pizza sea parte de nuestras vidas. Tanto la amamos, que desde 1942 estamos abiertos desde la mañana hasta la madrugada, porque no hay hora para comer una buena pizza. Una pizza hecha con amor, nuestra pizza.'),
('La Reverde: Parrilla Vegana','Montevideo',40,1019,'43841093','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809040/comanda/la_reverde_xqq2in.jpg','Bienvenidos a la 1° parrilla vegana de la Argentina. Arrabalera de barrio, desde 2015 nos ubicamos a unas cuadras del Congreso con nuestro local típico parrilla, pero sin sufrimiento animal.'),
('Il Ballo del Mattone','Gorriti',5893,1414 ,'47745681','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809040/comanda/il_ballo_del_mattone_jsqc8g.jpg','Fundada en el 2007 por los Francolini, una familia de Argentanos , que con trabajo duro y apasionado redefinió el concepto tradicional de una Trattoria a la italiana, a partir de la incorporación de un valor diferencial: El Arte.'),
('Burguer 54','Nueva York',4074,1419,'45038587','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809043/comanda/burguer54_pc1vvs.jpg','A principios de 2014 nos embarcamos en un viaje para reinventar las hamburguesas, para volver más rico lo que ya era rico. Para lograrlo, nos focalizamos en dos conceptos: Natural y Fresco.'),
('La Choza','Gascón',1701,1414,'48333334','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809039/comanda/la_choza_mw8zjq.jpg','Fundada en 2004 en una antigua casona del Barrio Porteño de Palermo, La Choza de Gascón ha llegado para quedarse.'),
('No Mames Wey','Fitz Roy',1617,1414,'1150366960','https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704988563/no_mames_wey_fmi6df.jpg','No Mames Wey nació en 2017 con el propósito de ser la casa más grande de tacos. Ofrecemos tacos, burritos, nachos y tragos en un ambiente pensado para que nuestros clientes se sumerjan en la cultura mexicana.')
;

INSERT INTO `comandabd`.`PRODUCTOS`
(`PRODUC_NOMBRE`,`PRODUC_DESCRP`,`PRODUC_PRECIO`,`PRODUC_CATEGO`,`PRODUC_IMG`,`PRODUC_LOCAL`,`PRODUC_ACTIVO`)
VALUES
/*N°1: Kentucky Almagro*/
('Pizza Mozzarella Grande','Pizza Mozzarella y salsa de tomate 8 porciones, para compartir.',9100,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809029/comanda/muzza_qp3jqs.jpg',1,0),
('Pizza de Verdura y Salsa blanca','Pizza de 8 porciones con verdura y salsa blanca.',11300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809034/comanda/verdura_e2y2iu.jpg',1,0),
('Pizza de Palmitos','Pizza con queso, palmitos, salsa golf y aceitunas.',11300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809028/comanda/palmitos_sghuho.jpg',1,1),
('Pizza Napolitana','Pizza con mozzarella y rodajas de tomate.',11300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809026/comanda/napolitana_awmcqf.jpg',1,1),
('Pizza Jamón y Morrón','Pizza con mozzarella, jamón cocido y morrón.',11300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809024/comanda/jamonymorron_yqajzp.jpg',1,1),
('Pizza Fugazzeta con Jamón','Pizza con relleno de mozzarella, jamón y cebolla.',11300,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809023/comanda/fugazeta_qg73lk.jpg',1,1),
('Fainá','Porción de fainá.',800,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809023/comanda/faina_ouyomj.jpg',1,1),
('Empanada J&Q','Al horno, rellena de jamón y queso.',1560,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/empanada_fnqp9v.jpg',1,1),
('Empanada Pollo','Al horno, rellena de pollo.',1560,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/empanada_fnqp9v.jpg',1,1),
('Empanada Humita','Al horno, rellena de choclo.',1560,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/empanada_fnqp9v.jpg',1,1),
('Flan Casero','Flan casero con crema y dulce de leche.',2400,7,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/flan-crema-dulce_tlxmia.jpg',1,1),
('Coca-Cola','Gaseosa "Coca-Cola", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/coca-cola-vidrio_mt76ba.jpg',1,1),
('Sprite','Gaseosa "Sprite", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/sprite-vidrio_a5uhos.jpg',1,1),
('Fanta','Gaseosa "Fanta", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/fanta-vidrio_l0z2sy.jpg',1,1),
('Agua sin gas','Agua sin gas "Eco", 354 ml.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/agua-eco_m967h0.jpg',1,1),
('Agua con gas','Agua con gas "Eco", 354ml.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/agua-eco_m967h0.jpg',1,1),
/*N°2: La Reverde: Parrilla Vegana*/
('Empanada J&Q vegano','Frita, rellena de jamón y queso veganos.',1600,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',2,1),
('Empanada caprese','Frita, rellena de tomate, albahaca y queso vegano.',1600,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',2,1),
('Empanada Q&Champi','Frita, rellena de champiñón y queso vegano.',1600,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',2,1),
('Vacío c/papas','Vacío de seitán con papas fritas',4750,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/vacioconpapasvegano_gduegw.jpg',2,1),
('Matambrito c/papas','Matambrito de seitán con papas fritas',4750,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809025/comanda/matambritovegano_bxgjcu.jpg',2,1),
('Girgolas a la provenzal c/papas','Girgolas a la provenzal con papas fritas',4880,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809024/comanda/girgolasprovenzal_d0vwo7.jpg',2,1),
('Chorisaurio','Sandwich de chorizo vegano con tomate, lechuga y papas fritas',4800,10,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809021/comanda/chorisaurio_f5f7gd.jpg',2,1),
('Helado Vegano','Dos bochas de helado vegano: chocolate y vainilla',2300,7,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/helado-2-bochas_js1aet.jpg',2,1),
('Pepsi','Gaseosa "Pepsi", 354ml.',1050,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/pepsi-vidrio_eqyvwx.jpg',2,1),
('Seven-Up','Gaseosa "Seven-Up", 354ml.',1050,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/7up-vidrio_avetip.jpg',2,1),
('Mirinda','Gaseosa "Mirinda", 354ml.',1050,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825567/mirinda-vidrio_ysa5sy.jpg',2,1),
('Agua sin gas','Agua sin gas "Eco", 354 ml.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/agua-eco_m967h0.jpg',2,1),
('Agua con gas','Agua con gas "Eco", 354ml.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/agua-eco_m967h0.jpg',2,1),
/*N°3: Il Ballo del Mattone*/
('Bruschetta','Pan con tomate y albahaca picada, queso y aceite de oliva.',2300,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809029/comanda/BruschettaItaliana_dtbukn.jpg',3,1),
('Empanada Casera','Empanda sabor a elección: carne, pollo, jamon y queso.',1500,5,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/empanadavegana_wsowxx.jpg',3,1),
('Tortilla de Papas','Clásica tortilla de papas con cebolla. Para compartir.',2900,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704746536/tortillapapas_bnvmwj.jpg',3,1),
('Milanesa napolitana c/puré','Milanesa de carne con salsa de tomate y queso, con puré.',3920,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809025/comanda/milanapopure_pani2y.jpg',3,1),
('Albóndigas c/puré','Albóndigas caseras con salsa roja, con puré.',3600,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/albondigapure_ryjihc.jpg',3,1),
('Pollo c/salsa de puerro y papas','Pollo al horno con salsa de puerro y papas noisette.',4110,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809031/comanda/polloypapas_tcti8z.jpg',3,1),
('Tallarines Fileto','Tallarines con salsa fileto',3300,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745970/tallarinesfileto_wvwh41.jpg',3,1),
('Ñoquis c/boloñesa','Ñoquis de papa con salsa boloñesa.',4700,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809030/comanda/noquis-de-calabaza_z8xwir.jpg',3,1),
('Helado','Dos bochas de helado, gustos a elección.',2300,7,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/helado-2-bochas_js1aet.jpg',3,1),
('Levité Pomelo','Agua saborizada "Levité" sabor pomelo, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/levite-pomelo-chica_x6gcja.jpg',3,1),
('Levité Manzana','Agua saborizada "Levité" sabor manzana, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/levite-manzana-chica_vxx0ed.png',3,1),
('Levité Naranja','Agua saborizada "Levité" sabor naranja, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825567/levite-naranja-chica_dot7ip.jpg',3,1),
('Agua sin gas','Agua sin gas "Eco", 354 ml.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/agua-eco_m967h0.jpg',3,1),
('Agua con gas','Agua con gas "Eco", 354ml.',950,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/agua-eco_m967h0.jpg',3,1),
/*N°4: Burguer 54*/
('Veggie Burguer','Hamburguesa de quinoa, arroz integral, porotos negros y remolacha con lechuga. Tomate y cebolla.',3400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809034/comanda/Veggie-Burger-1_tkocui.png',4,1),
('Salmón Burguer','Hamburguesa casera de salmon rosado con lechuga morada, cebolla morada, tomate y salsa tártara.',4400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/salmon_xpmznb.png',4,1),
('Not Cheese Burguer','Hamburguesa casera vegana a base de plantas con lechuga, tomate, cebolla y salsa burger 54.',3800,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809029/comanda/NOT-Cheese-Burger_mq4vew.png',4,1),
('Chicken Sandwich','Pechuga de pollo a la plancha con lechuga, tomate. Cebolla y salsa burger 54.',3700,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809023/comanda/chicken_a2bg4e.png',4,1),
('Cheese Burguer','Hamburguesa casera con queso cheddar, lechuga, tomate, cebolla y salsa burger 54.',3400,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809018/comanda/cheeseburger_o72j6s.png',4,1),
('Breaded Chicken','Sandwich de pechuga de pollo rebozada con lechuga, tomate, cebolla morada y mayonesa.',3700,9,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/Breaded-Chicken-1_vjhdpl.png',4,1),
('Aros de cebolla','Aros empanados de cebolla c/ketchup.',1900,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/aroscebolla_ocs28v.jpg',4,1),
('Coca-Cola','Gaseosa "Coca-Cola", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/coca-cola-vidrio_mt76ba.jpg',4,1),
('Sprite','Gaseosa "Sprite", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/sprite-vidrio_a5uhos.jpg',4,1),
('Fanta','Gaseosa "Fanta", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/fanta-vidrio_l0z2sy.jpg',4,1),
('Levité Pomelo','Agua saborizada "Levité" sabor pomelo, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/levite-pomelo-chica_x6gcja.jpg',4,1),
('Levité Manzana','Agua saborizada "Levité" sabor manzana, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/levite-manzana-chica_vxx0ed.png',4,1),
('Levité Naranja','Agua saborizada "Levité" sabor naranja, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825567/levite-naranja-chica_dot7ip.jpg',4,1),
('Stella Artois','Cerveza "Stella Artois", 1L.',5000,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/stella-artois_q3czv1.png',4,1),
('Quilmes','Cerveza "Quilmes", 1L.',4600,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/quilmes_xvmbsr.png',4,1),
/*N°5: La Choza*/
('Provoleta','Provoleta grillada a la parrilla con finas hierbas.',3100,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745969/provoleta_urv3vp.jpg',5,1),
('Tabla de Quesos','Variedad de quesos.',2900,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745971/tablaquesos_glyvse.png',5,1),
('Mozzarelitas','Bastones de mozzarella con panko y salsa criolla.',2800,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745969/mozzarelita_w2jxsl.jpg',5,1),
('Entraña Provenzal','Entraña a la provenzal con papas fritas.',4600,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745969/entranaprovenzal_dhtwjy.jpg',5,1),
('Vacío','Porción de vacío con guarnición.',4300,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745969/vacioternera_uu5ljn.jpg',5,1),
('Asado','Porción de asado con guarnición.',4300,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745970/asado_sfpak5.png',5,1),
('Mix de achuras','Molleja, riñón, chinchulín, chorizo y morcilla.',4750,2,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745970/mixachuras_gr2rxc.png',5,1),
('Tagliatelle Bolognesa','Tagliatelle con salsa bolognesa.',3550,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704745970/Tagliatelle_xncxzf.jpg',5,1),
('Penne rigate y hongos','Fideos penne rigate, portobellos, champingnon, cebolla, puerro y parmesano.',3800,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809031/comanda/pennehongo_nmdhx2.png',5,1),
('Canelones de verdura','Canelones rellenos de espinaca y ricota, con salsa fileto.',4900,3,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809017/comanda/canelonesverdura_pxa982.jpg',5,1),
('Risotto de la casa','Arroz con hongos de estación, cebolla, manteca, queso parmesano y pimienta.',3500,12,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/rissoto_js3dx4.jpg',5,1),
('Ensalada mixta','Tomates, cebolla y lechuga',2600,4,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809022/comanda/ensaladamixta_s1m59r.jpg',5,1),
('Flan Casero','Flan casero con crema y dulce de leche.',2400,7,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/flan-crema-dulce_tlxmia.jpg',5,1),
('Coca-Cola','Gaseosa "Coca-Cola", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/coca-cola-vidrio_mt76ba.jpg',5,1),
('Sprite','Gaseosa "Sprite", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/sprite-vidrio_a5uhos.jpg',5,1),
('Fanta','Gaseosa "Fanta", 354ml.',1150,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/fanta-vidrio_l0z2sy.jpg',5,1),
('Levité Pomelo','Agua saborizada "Levité" sabor pomelo, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/levite-pomelo-chica_x6gcja.jpg',5,1),
('Levité Manzana','Agua saborizada "Levité" sabor manzana, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/levite-manzana-chica_vxx0ed.png',5,1),
('Levité Naranja','Agua saborizada "Levité" sabor naranja, 500ml.',1100,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825567/levite-naranja-chica_dot7ip.jpg',5,1),
('Trumpeter','Malbec, 750ml.',4041,8,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/trumpeter-malbec_nduy8c.jpg', 5,1),
('Lugi Bosca','Malbec, 750ml.',4230,8,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825569/luigi-bosca-malbec_y9kvyn.jpg',5,1),
('Emilia','Sauvignon Blanc, 750ml.',4120,8,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825567/emilia-blanco_mlxayj.jpg',5,1),
/*N°6: No Mames Wey*/
('Quesadilla de queso x 3','Tortilla de maíz con queso y champiñón.',2700,1,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809038/comanda/quesadilla_n5xkcu.jpg',6,1),
('Nachos c/guacamole','Nachos con guacamole.',2900,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809028/comanda/nachosguacamole_iypjbm.jpg',6,1),
('Nachos c/cheddar','Nachos con cheddar.',2900,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809026/comanda/nachoschedar_j0mnxv.jpg',6,1),
('Tacos de carne x 3','Tacos de RES con cebolla morada, choclo, morrones',4720,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/tacoscarne_ql2fpl.jpg',6,1),
('Tacos veggie x 3','Tacos de verduras: cebolla, morrones, hongos, chile',4320,11,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1701809032/comanda/tacoscarne_ql2fpl.jpg',6,1),
('Corona Cerveza','Cerveza "Corona", 410ml.',1820,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704831587/cerveza-corona_hg1gst.png',6,1),
('Sol Cerveza','Cerveza "Sol", 473ml.',1890,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704831587/cerveza-sol_d5y1zg.jpg',6,1),
('Jarra de Limonada','Limonada con menta y jengibre, 1L.',4000,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704825568/jarra-limonada_oelehv.jpg',6,1),
('Jarra de Tamarindo','Agua de Tamarindo, 1L.',4000,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704832404/tamarindo_eghsu5.jpg',6,1),
('Jarra de Jamaica','Agua de Jamaica, 1L.',4000,6,'https://res.cloudinary.com/dgyxvj3xu/image/upload/v1704832405/jamaica_im5obv.png',6,1)
;

INSERT INTO `comandabd`.`ROLES`
(`ROL_NOMBRE`)
VALUES
('ADMIN'),
('USER_LOCAL')
;

INSERT INTO `comandabd`.`USUARIOS`
(`USER_USER`,`USER_NOMBRE`,`USER_APELLI`,`USER_NRODOC`,`USER_EMAIL`,`USER_TELEFN`,`USER_ROL`,`USER_PASWRD`,`USER_ACTIVO`)
VALUES
('RMILIANO','Rodrigo','Miliano',99999999,'rodrigo.miliano@davinci.edu.ar','1167390317',1,'1234',1),
('MPRINCIPE','Maximiliano','Príncipe',99999999,'maximiliano.principe@davinci.edu.ar','1131681566',2,'1234',0),
('IESSES','Ignacio','Esses',99999999,'ignacio.esses@davinci.edu.ar','1169344326',2,'1234',1),
('adminsinlocal','adminsinlocal','adminsinlocal',4444444,'adminsinlocal@gmail.com','1144444444',1,'1234',1),
('localconlocal1','localconlocal1','localconlocal1',5555555,'localconlocal1@gmail.com','1155555555',2,'1234',1),
('localconlocal2','localconlocal2','localconlocal2',6666666,'localconlocal2@gmail.com','1166666666',2,'1234',1)
;

INSERT INTO `comandabd`.`MESAS` 
(`MESA_SILLAS`,`MESA_OBSERV`,`MESA_ESTADO`,`MESA_USUARIO`,`MESA_LOCAL`)
VALUES
(2,'atiende José',1,1,1),
(2,'atiende José',1,1,1),
(4,'atiende Micaela',1,1,1),
(4,'atiende Micaela',1,1,1),
(4,'atiende Micaela',1,1,1),
(4,'esquina.',1,3,2),
(4,'ventana',1,3,2),
(6,'ventana',1,3,2),
(6,'ventana',1,3,2),
(8,'barra',1,3,2),
(2,'Mozo: Matias',1,1,3),
(4,'Mozo: Matias',1,1,3),
(4,'Mozo: Eduardo',1,1,3),
(4,'Mozo: Eduardo',1,1,3),
(6,'Mozo: Eduardo',1,1,3),
(2,'Número 1',1,1,4),
(2,'Número 2',1,1,4),
(4,'Número 3',1,1,4),
(6,'Número 4',1,1,4),
(6,'Número 5',1,1,4),
(2,'Externa',1,1,5),
(2,'Externa',1,1,5),
(4,'Interna',1,1,5),
(4,'Interna',1,1,5),
(4,'Interna',1,1,5),
(2,'Afuera',1,1,6),
(2,'Afuera',1,1,6),
(4,'Adentro',1,1,6),
(4,'Adentro',1,1,6),
(4,'Adentro',1,1,6)
;

INSERT INTO `comandabd`.`ESTADOS`
(`ESTADO_NOMBRE`)
VALUES
('DISPONIBLE'),
('EN CURSO'),
('FINALIZADO')
;

INSERT INTO `comandabd`.`USUARIOS_LOCALES`
(`USRLOC_LOCAL`,`USRLOC_USER`)
VALUES
(1,5),
(2,6)
;

/********** PARA BORRAR LAS TABLAS (DATOS) **********/
/*
TRUNCATE TABLE LOCALES;
TRUNCATE TABLE USUARIOS;
TRUNCATE TABLE USUARIOS_LOCALES;
TRUNCATE TABLE ROLES;
TRUNCATE TABLE MESAS;
TRUNCATE TABLE ESTADOS;
TRUNCATE TABLE PRODUCTOS;
TRUNCATE TABLE CATEGORIAS;
TRUNCATE TABLE COMANDAS;
TRUNCATE TABLE ITEMCOMANDAS;
*/

/********** PARA ELIMINAR LAS TABLAS **********/
/*
DROP TABLE ITEMCOMANDAS;
DROP TABLE COMANDAS;
DROP TABLE PRODUCTOS;
DROP TABLE CATEGORIAS;
DROP TABLE MESAS;
DROP TABLE USUARIOS_LOCALES;
DROP TABLE LOCALES;
DROP TABLE USUARIOS;
DROP TABLE ESTADOS;
DROP TABLE ROLES;
*/

/********** CONSULTAR LAS TABLAS **********/
/*
SELECT * FROM CATEGORIAS;
SELECT * FROM PRODUCTOS;
SELECT * FROM LOCALES;
SELECT * FROM USUARIOS;
SELECT * FROM ROLES;
SELECT * FROM MESAS;
SELECT * FROM ESTADOS;
SELECT * FROM COMANDAS;
SELECT * FROM ITEMCOMANDAS;
SELECT * FROM USUARIOS_LOCALES;
*/

/********** RESTAURAR NUMERACIÓN (ID) **********/
/*
ALTER TABLE PRODUCTOS AUTO_INCREMENT = 0;
ALTER TABLE CATEGORIAS AUTO_INCREMENT = 0;
ALTER TABLE LOCALES AUTO_INCREMENT = 0;
ALTER TABLE USUARIOS AUTO_INCREMENT = 0;
ALTER TABLE ROLES AUTO_INCREMENT = 0;
ALTER TABLE MESAS AUTO_INCREMENT = 0;
ALTER TABLE ESTADOS AUTO_INCREMENT = 0;
ALTER TABLE COMANDAS AUTO_INCREMENT = 5;
ALTER TABLE ITEMCOMANDAS AUTO_INCREMENT = 0;
ALTER TABLE USUARIOS_LOCALES AUTO_INCREMENT = 0;
*/