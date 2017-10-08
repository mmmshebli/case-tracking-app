DROP SCHEMA IF EXISTS `case-manager`;

CREATE SCHEMA `case-manager`;

USE `case-manager`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `CASEE`;

CREATE TABLE `CASEE` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_number` int(11) DEFAULT NULL,
  `description` TEXT DEFAULT NULL,
  `status` VARCHAR(128) DEFAULT NULL,
  `date_opened` DATE DEFAULT NULL,
  `last_updated` DATE DEFAULT NULL,
  `worker_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `applicant_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`worker_id`) REFERENCES `WORKER` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`location_id`) REFERENCES `LOCATION` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`applicant_id`) REFERENCES `APPLICANT` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `CASEUPDATE`;

CREATE TABLE `CASE_UPDATE` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `case_id` int(11) NOT NULL,
    `update_date` DATE NOT NULL,
    `internal_update_detail` TEXT DEFAULT NULL,
    `applicant_facing_update_detail` TEXT DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`case_id`) REFERENCES `CASEE` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


DROP TABLE IF EXISTS `WORKER`;

CREATE TABLE `WORKER` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `worker_number` int(11) DEFAULT NULL UNIQUE,
  `first_name` VARCHAR(128) DEFAULT NULL,
  `last_name` VARCHAR(128) DEFAULT NULL,
  `login_name` VARCHAR(128) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`location_id`) REFERENCES `LOCATION` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  FOREIGN KEY (`login_name`) REFERENCES `USERS` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

DROP TABLE IF EXISTS `users`;

CREATE TABLE `USERS` (
    `username` VARCHAR(128) NOT NULL UNIQUE,
    `password` VARCHAR(128) NOT NULL,
	`enabled` TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (`username`)
);

DROP TABLE IF EXISTS `ROLES`;

CREATE TABLE `ROLES` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `role` VARCHAR(45) NOT NULL UNIQUE,
    PRIMARY KEY (`id`)
);


DROP TABLE IF EXISTS `USER_ROLES`;

CREATE TABLE `USER_ROLES`(
	`username` VARCHAR(128) NOT NULL,
    `role_id` int(11) NOT NULL,
    PRIMARY KEY (`username`, `role_id`),
    FOREIGN KEY (`username`) REFERENCES `USERS` (`username`),
    FOREIGN KEY (`role_id`) REFERENCES `ROLES` (`id`)
);

/*CREATE TABLE `USER_ROLES`(
	`id` int(11) NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(128) NOT NULL,
    `role` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`id`, `username`),
    FOREIGN KEY (`username`) REFERENCES `USERS` (`username`) ON DELETE NO ACTION ON UPDATE NO ACTION
);*/


DROP TABLE IF EXISTS `APPLICANT`;

CREATE TABLE `APPLICANT` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(128) DEFAULT NULL,
  `last_name` VARCHAR(128) DEFAULT NULL,
  `dob` DATE DEFAULT NULL,
  PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `LOCATION`;

CREATE TABLE `LOCATION` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `LOCATION` VALUES (1,'Minneapolis'), (2,'Seattle'), (3,'New York'), (4, 'Miami');


INSERT INTO `USERS` VALUES
	('mustafa', '$2a$10$nN9lYIPrxACIVnvXUX9Pj.ytXeXXrupfBR/y2TLpBUPJ.UgW83JNW',1),
	('admin1', '$2a$10$Ftw2nKCe2ZIJAlgkIs7gauBQeRXqaUDDxvLIc.0uSyH39nRCfP52G',1),
	('admin2', '$2a$10$Fgio03RprBVv3NISqsKlA.UE3G8fmj72XCsJcbV6qvP0sU8og6l3O',1),
	('worker1', '$2a$10$7TSdFLAqHH5HGUfERV6cp.BjEVyL1T0lgvSn3PkB01Mnp6UFCZZPi',1),
	('worker2', '$2a$10$j3Afuqefs27htOkuey88tefy7nvEbaJfKythStWUfIy7HOycB4UHq',1),
	('worker3', '$2a$10$pwX4G92uGFSVWcDbLRP.UOcb.PXrStuO2m8eV4aQ2h9lqckzeE5S.',1),
	('worker4', '$2a$10$GBulShfhatB464pMO5O7X.mG081KLEwtZLxVTpCprG/4vd3rcgdVi',1),
	('worker5', '$2a$10$e56TgaJtDMrE55vqShRlqu4YVzVL56UhRKPGsxmfigmJWMVS9J3WS',1),
	('worker6', '$2a$10$casDzWFHQIXS7m2F8/KAAuRIV/Cy3wGGUYsVq5OhftdWtgJG2lUfG',1),
	('worker7', '$2a$10$76nML1Vcoyu2hBA3a6j/f.L4LmdQwQrhmlnllZsDydsNupx70BdXe',1),
	('supervisor1', '$2a$10$10CriBVEqSmfyezQlwzyA.9qzEOl9W3tkeFDQhoQahPayHkqbSKba',1),
	('supervisor2', '$2a$10$zgg1RZtC5G9DtA0CLp1ET.lVAu4.yY74Ix5BpMtVPHv9h81mpUaPi',1);


INSERT INTO `ROLES` VALUES
	(1, 'ROLE_ADMIN'),
    (2, 'ROLE_SUPERVISOR'),
    (3, 'ROLE_WORKER');
    
INSERT INTO `USER_ROLES` VALUES
	('mustafa', 1),
	('admin1', 1),
	('admin2', 1),
	('worker1', 3),
	('worker2', 3),
	('supervisor1',2),
	('supervisor2',2),
	('worker3', 3),
	('worker4', 3),
	('worker5', 3),
	('worker6', 3),
	('worker7', 3);

/*INSERT INTO `USER_ROLES` VALUES
	(1,'mustafa', 'ROLE_ADMIN'),
	(2,'admin1', 'ROLE_ADMIN'),
	(3,'admin2', 'ROLE_ADMIN'),
	(4,'worker1', 'ROLE_WORKER'),
	(5,'worker2', 'ROLE_WORKER'),
	(6,'supervisor1','ROLE_SUPERVISOR'),
	(7,'supervisor2','ROLE_SUPERVISOR'),
	(8,'worker3', 'ROLE_WORKER'),
	(9,'worker4', 'ROLE_WORKER'),
	(10,'worker5', 'ROLE_WORKER'),
	(11,'worker6', 'ROLE_WORKER'),
	(12,'worker7', 'ROLE_WORKER');*/

INSERT INTO `WORKER` VALUES 
	(1,1000,'Homer','Simpson','admin1',1),
	(2,1001,'Peter','Griffin','admin2',2),
	(3,1003,'Mickey','Mouse','supervisor1',3),
	(4,1004,'Donald','Duck','supervisor2',4),
	(5,1005,'Oliver','Twist','worker1',3),
    (6,1006,'Agatha','Kristi','worker2',1),
    (7,1007,'Leo','Tolstoi','worker3',1),
    (8,1008,'Arnest','Hemenguay','worker4',2),
    (9,1009,'Charles','Dikens','worker5',3),
    (10,1010,'Thomas','Freidman','worker6',3),
    (11,1011,'Nageeb','Mahfooz','worker7',4),
    (12,1012,'Mustafa','Al Kubaisi','mustafa',4);
    
INSERT INTO `APPLICANT` VALUES 
	(1,'Robert','Dinerro','1979-08-04'),
	(2,'Robin','Williams','1988-05-03'),
	(3,'Jean','Rino','1960-12-11'),
	(4,'George','Cloony','1999-01-01'),
	(5,'Julia','Roberts','1980-03-24'),
    (6,'Kate','Winslet','1990-11-19'),
    (7,'Arnold','Schwarznager','1978-11-19'),
    (8,'Silvister','Staloon','1980-11-19'),
    (0,'Tom','Hanks','1977-11-19'),
    (10,'Julia','Armond','1955-11-19'),
    (11,'Dustin','Huffman','1940-11-19'),
    (12,'Mereil','Strepe','1978-11-19'),
    (13,'John','Travolta','1979-11-19'),
    (14,'Clint','Eastwood','1990-11-19'),
    (15,'Bruce','Willis','1995-11-19'),
    (16,'Jogn','Wyne','1979-08-04'),
	(17,'Walt','Disney','1988-05-03'),
	(18,'Richard','Geer','1960-12-11'),
	(19,'Amitab','Khan','1999-01-01'),
	(20,'Omar','Shareef','1980-03-24'),
    (21,'Dinzel','Washington','1990-11-19'),
    (22,'Anthony','Quinn','1978-11-19'),
    (23,'Al','Pacino','1980-11-19'),
    (24,'Russel','Crow','1977-11-19'),
    (25,'Brad','Pit','1955-11-19'),
    (26,'Angelina','Jolie','1940-11-19'),
    (27,'Morgan','Freeman','1978-11-19'),
    (28,'Keanu','Reeves','1979-11-19'),
    (29,'Nicholas','Cage','1990-11-19'),
    (30,'Mel','Gibson','1995-11-19'),
    (31,'Antonio','Panderas','1979-08-04'),
	(32,'Sandra','Bullock','1988-05-03'),
	(33,'Meg','Ryan','1960-12-11'),
	(34,'Nicole','Kidman','1999-01-01'),
	(35,'Saleem','AlBasri','1980-03-24'),
    (36,'Cameron','Diaz','1990-11-19'),
    (37,'Adil','Imam','1978-11-19'),
    (38,'Donald','Trump','1980-11-19'),
    (39,'Barack','Obama','1977-11-19'),
    (40,'George','Bush','1955-11-19'),
    (41,'Bill','Clinton','1940-11-19'),
    (42,'Ronald','Regan','1978-11-19'),
    (43,'Spong','Bob','1979-11-19'),
    (44,'Patrick','Star','1990-11-19'),
    (45,'Mr','Crab','1995-11-19');
    
INSERT INTO `CASEE` VALUES 
	(1,20070001,'Very promissing case','Pending','2017-01-01','2017-09-05',4,4,1),
	(2,20070002,'There is no hope for acceptance','Pending','2017-02-01','2017-09-05',3,3,2),
	(3,20070003,'This is a very complicated case','Approved','2016-07-30','2017-09-05',4,4,3),
	(4,20070004,'This case is pre approved because app has friends in high places','Denied','2015-12-24','2017-09-05',1,1,4),
	(5,20070005,'NO way this application can be approved','Initial review','2016-07-17','2017-09-05',5,3,5),
    (7,20070006,'This case is soo sad','New','2017-06-22','2017-06-22',5,3,6),
    (8,20070007,'This case will never be approved nor denied','New','2017-06-22','2017-06-22',5,3,7),
    (9,20070008,'This case is opened in a very critical time','New','2017-06-22','2017-06-22',5,3,8),
    (10,20070009,'This case is suite case','New','2017-06-22','2017-06-22',5,3,9),
    (11,20070010,'Detail details details and more details','New','2017-06-22','2017-06-22',6,1,10),
    (12,20070011,'Bl =a bla blablabla bla bla','New','2017-06-22','2017-06-22',6,1,11),
    (13,20070012,'The queen of cases','New','2017-06-22','2017-09-05',6,1,12),
    (14,20070013,'This case will be easily approved','New','2017-06-22','2017-06-22',6,1,13),
    (15,20070014,'This case has no details','New','2017-06-22','2017-06-22',6,1,14),
    (16,20070015,'The details of this case is written with secret ink','New','2017-06-22','2017-06-22',6,1,15),
    (17,20070016,'Very promissing case','Pending','2017-01-01','2017-09-05',4,4,16),
	(18,20070017,'There is no hope for acceptance','Pending','2017-02-01','2017-09-05',3,3,17),
	(19,20070018,'This is a very complicated case','Approved','2016-07-30','2017-09-05',4,4,18),
	(20,20070019,'This case is pre approved because app has friends in high places','Denied','2015-12-24','2017-09-05',1,1,19),
	(21,20070020,'NO way this application can be approved','Initial review','2016-07-17','2017-09-05',5,3,20),
    (22,20070021,'This case is soo sad','New','2017-06-22','2017-06-22',5,3,21),
    (23,20070022,'This case will never be approved nor denied','New','2017-06-22','2017-06-22',5,3,22),
    (24,20070023,'This case is opened in a very critical time','New','2017-06-22','2017-06-22',5,3,23),
    (25,20070024,'This case is suite case','New','2017-06-22','2017-06-22',5,3,24),
    (26,20070025,'Detail details details and more details','New','2017-06-22','2017-06-22',6,1,25),
    (27,20070026,'Bl =a bla blablabla bla bla','New','2017-06-22','2017-06-22',6,1,26),
    (28,20070027,'The queen of cases','New','2017-06-22','2017-09-05',6,1,27),
    (29,20070028,'This case will be easily approved','New','2017-06-22','2017-06-22',6,1,28),
    (30,20070029,'This case has no details','New','2017-06-22','2017-06-22',6,1,29),
    (31,20070030,'The details of this case is written with secret ink','New','2017-06-22','2017-06-22',6,1,30),
    (32,20070031,'Very promissing case','Pending','2017-01-01','2017-09-05',4,4,31),
	(33,20070032,'There is no hope for acceptance','Pending','2017-02-01','2017-09-05',3,3,32),
	(34,20070033,'This is a very complicated case','Approved','2016-07-30','2017-09-05',4,4,33),
	(35,20070034,'This case is pre approved because app has friends in high places','Denied','2015-12-24','2017-09-05',1,1,34),
	(36,20070035,'NO way this application can be approved','Initial review','2016-07-17','2017-09-05',5,3,35),
    (37,20070036,'This case is soo sad','New','2017-06-22','2017-06-22',5,3,36),
    (38,20070037,'This case will never be approved nor denied','New','2017-06-22','2017-06-22',5,3,37),
    (39,20070038,'This case is opened in a very critical time','New','2017-06-22','2017-06-22',5,3,38),
    (40,20070039,'This case is suite case','New','2017-06-22','2017-06-22',5,3,39),
    (41,20070040,'Detail details details and more details','New','2017-06-22','2017-06-22',6,1,40),
    (42,20070041,'Bl =a bla blablabla bla bla','New','2017-06-22','2017-06-22',6,1,41),
    (43,20070042,'The queen of cases','New','2017-06-22','2017-09-05',6,1,42),
    (44,20070043,'This case will be easily approved','New','2017-06-22','2017-06-22',6,1,43),
    (45,20070044,'This case has no details','New','2017-06-22','2017-06-22',6,1,44),
    (46,20070045,'The details of this case is written with secret ink','New','2017-06-22','2017-06-22',6,1,45);
    
    
INSERT INTO `CASE_UPDATE` VALUES 
	(1,1,'2017-09-17', 'initial papers reviewed for the applicant and a request for addition documents sent', 'We sent you an e mail requesting additional docs'),
	(2,3,'2017-09-17', 'AN interview scheduled for the applicant', 'You have an upcoming interview we sent e mail with the place and time'),
    (3,4,'2017-09-17', 'second interview scheduled for the applicant', 'You have a second interview you will receive an e mail with details'),
	(4,5,'2017-09-17', 'second interview scheduled for the applicant', 'You have a second interview you will receive an e mail with details');
    

    