CREATE TABLE `client` (
  `idt_client` bigint NOT NULL AUTO_INCREMENT,
  `nam_client` varchar(255) NOT NULL,
  `age_client` decimal(10,0) DEFAULT NULL,
  `cod_ip_adress` varchar(255) NOT NULL,
  `dat_creation` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `idt_client_location` bigint,
  `idt_client_weather` bigint 
  PRIMARY KEY (`idt_client`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `client_location` (
  `idt_client_location` bigint NOT NULL AUTO_INCREMENT,
  `nam_country` varchar(255) NOT NULL,
  `nam_region` varchar(255) NOT NULL,
  `nam_city` varchar(255) NOT NULL,
  `cod_zip` varchar(255) NOT NULL,
  `cod_latitude` float(20,10) NOT NULL,
  `cod_longitude` float(20,10) NOT NULL,
  PRIMARY KEY (`idt_client_location`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `client_weather` (
  `idt_client_weather` bigint NOT NULL AUTO_INCREMENT,
  `min_temperature` float(20,10) NOT NULL,
  `max_temperature` float(20,10) NOT NULL,
  `dat_applicable` date
  PRIMARY KEY (`idt_client_weather`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `client` ADD CONSTRAINT `clieloca_clie_fk` FOREIGN KEY (`idt_client_location`) REFERENCES `client_location` (`idt_client_location`);

ALTER TABLE `client` ADD CONSTRAINT `clieweat_clie_fk` FOREIGN KEY (`idt_client_weather`) REFERENCES `client_weather` (`idt_client_weather`);