
drop database seatmanagement;

create database seatmanagement;

use seatmanagement;

CREATE TABLE `seatmanagement`.`building` ( 
 `building_id` VARCHAR(36) NOT NULL,  
 `building_name` VARCHAR(45) NOT NULL, 
 `building_address` VARCHAR(255) NOT NULL, 
 `building_location` VARCHAR(45) NOT NULL,
 PRIMARY KEY (`building_id`))ENGINE=InnoDB;
 
CREATE TABLE `seatmanagement`.`floor` (  
  `floor_id` VARCHAR(36) NOT NULL,
  `building_id` VARCHAR(36) NOT NULL,  
  `floor_type` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`floor_id`),  
  INDEX `building_id_idx` (`building_id` ASC), 
  CONSTRAINT `building_id`    FOREIGN KEY (`building_id`)   
  REFERENCES `seatmanagement`.`building` (`building_id`)   
  ON DELETE NO ACTION    ON UPDATE NO ACTION)ENGINE=InnoDB;
  
CREATE TABLE `seatmanagement`.`block` ( 
	 `block_id` VARCHAR(36) NOT NULL, 
	 `block_type` VARCHAR(45) NOT NULL,  
	 `block_capacity` VARCHAR(45) NOT NULL,  
	 `block_description` VARCHAR(255) NOT NULL,  
	 `block_measurement` DOUBLE NOT NULL,  
	 `floor_id` VARCHAR(36) NOT NULL, 
	 PRIMARY KEY (`block_id`), 
	 INDEX `floor_id_idx` (`floor_id` ASC), 
	 CONSTRAINT `floor_id`   
	 FOREIGN KEY (`floor_id`)    
	 REFERENCES `seatmanagement`.`floor` (`floor_id`)  
	 ON DELETE NO ACTION    ON UPDATE NO ACTION
 )ENGINE=InnoDB;
 

 CREATE TABLE `seatmanagement`.`utilities` (
 	 `utility_id` VARCHAR(36) NOT NULL,
	 `utility_name` VARCHAR(100) NOT NULL,
 	 PRIMARY KEY (`utility_id`))ENGINE=InnoDB;


 CREATE TABLE `seatmanagement`.`block_utilities` (
	`block_id` VARCHAR(36) NOT NULL,
	`utility_id` VARCHAR(36) NOT NULL,
	PRIMARY KEY (`block_id`, `utility_id`),
	INDEX `utility_id_idx` (`utility_id` ASC),
	CONSTRAINT `block_id`
	FOREIGN KEY (`block_id`)
	REFERENCES `seatmanagement`.`block` (`block_id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
	CONSTRAINT `utility_id`
	FOREIGN KEY (`utility_id`)
	REFERENCES `seatmanagement`.`utilities` (`utility_id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION)ENGINE=InnoDB;

 
CREATE TABLE seatmanagement.team (   
 team_id varchar(36) NOT NULL,  
 team_name varchar(100) NOT NULL,   
 team_head varchar(50) NOT NULL,    
 team_members_count int,    
 PRIMARY KEY (team_id)
 )ENGINE=InnoDB;
 
CREATE TABLE seatmanagement.employee (
	employee_id varchar(36) NOT NULL,
    employee_roll varchar(50) NOT NULL,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    designation varchar(50) NOT NULL,
    date_of_joining DATE NOT NULL,
    team_id varchar(36),
    PRIMARY KEY (employee_id),
    KEY `employee_fk_1` (`team_id`),
    CONSTRAINT `employee_fk_1` FOREIGN KEY (`team_id`) 
 	REFERENCES `team` (`team_id`)
)ENGINE=InnoDB;

CREATE TABLE seatmanagement.system (
    system_id varchar(36) NOT NULL,
	employee_id varchar(36),
	system_name varchar(50) NOT NULL,
    system_type varchar(50) NOT NULL,
    network_type varchar(50),
    allotment_status varchar(50) NOT NULL,
    PRIMARY KEY (system_id),
	KEY `system_fk_1` (`employee_id`),
    CONSTRAINT `system_fk_1` FOREIGN KEY (`employee_id`) 
 	REFERENCES `employee` (`employee_id`)
)ENGINE=InnoDB;

CREATE TABLE `seatmanagement`.`seating` (  
`seating_id` VARCHAR(36) NOT NULL, 
 `block_id` VARCHAR(36) NOT NULL,  
 `seat_occupied` INT NOT NULL,  
 `team_id` VARCHAR(36) NOT NULL,  
 PRIMARY KEY (`seating_id`),  
 INDEX `block_id_idx` (`block_id` ASC), 
 INDEX `team_id_idx` (`team_id` ASC), 
 CONSTRAINT `block_id1`     FOREIGN KEY (`block_id`)
 REFERENCES `seatmanagement`.`block` (`block_id`)    
 ON DELETE NO ACTION    ON UPDATE NO ACTION, 
 CONSTRAINT `team_id`     FOREIGN KEY (`team_id`)   
 REFERENCES `seatmanagement`.`team` (`team_id`)     ON DELETE NO ACTION    ON UPDATE NO ACTION 
 )ENGINE=InnoDB;
 
CREATE TABLE seatmanagement.seating_detail (
    seating_detail_id varchar(36) NOT NULL,
	seating_id varchar(36) NOT NULL,
	system_id varchar(36)  NOT NULL,
    seating_date date,
    PRIMARY KEY (seating_detail_id),
	KEY `seating_detail_fk_1` (`seating_id`),
    CONSTRAINT `seating_detail_fk_1` FOREIGN KEY (`seating_id`) 
 	REFERENCES `seating` (`seating_id`),
	KEY `seating_detail_fk_2` (`system_id`),
    CONSTRAINT `seating_detail_fk_2` FOREIGN KEY (system_id) 
 	REFERENCES `system` (`system_id`)
)ENGINE=InnoDB;

CREATE TABLE seatmanagement.reallocation (
    reallocation_id varchar(36) NOT NULL,
    current_seating_detail_id varchar(36) NOT NULL,
	employee_id varchar(36) NOT NULL,
	block_id varchar(36) NOT NULL,
    reallocated_seating_detail_id varchar(36),
    reallocation_date date,
    reallocation_status varchar(50) NOT NULL,
	alloted_by varchar(50) NOT NULL,
    PRIMARY KEY (reallocation_id),
	KEY `reallocation_fk_1` (`employee_id`),
    CONSTRAINT `reallocation_fk_1` FOREIGN KEY (`employee_id`) 
 	REFERENCES `employee` (`employee_id`),
	KEY `reallocation_fk_2` (`block_id`),
    CONSTRAINT `reallocation_fk_2` FOREIGN KEY (`block_id`) 
 	REFERENCES `block` (`block_id`),
 	KEY `reallocation_fk_3` (`reallocated_seating_detail_id`),
    CONSTRAINT `reallocation_fk_3` FOREIGN KEY (`reallocated_seating_detail_id`) 
 	REFERENCES `seating_detail` (`seating_detail_id`)
)ENGINE=InnoDB;

CREATE TABLE seatmanagement.additional_device (
    additional_device_id varchar(36) NOT NULL,
	device_name varchar(100) NOT NULL,
    PRIMARY KEY (additional_device_id)
)ENGINE=InnoDB;

CREATE TABLE `seatmanagement`.`system_additional_device` (
	`system_id` VARCHAR(36) NOT NULL,
	`additional_device_id` VARCHAR(36) NOT NULL,
	PRIMARY KEY (`system_id`, `additional_device_id`),
	KEY `system_additional_device_fk_1` (`system_id`),
    CONSTRAINT `system_additional_device_fk_1` FOREIGN KEY (`system_id`) 
 	REFERENCES `system` (`system_id`),
	KEY `system_additional_device_fk_2` (`additional_device_id`),
    CONSTRAINT `system_additional_device_fk_2` FOREIGN KEY (additional_device_id) 
 	REFERENCES `additional_device` (`additional_device_id`)
)ENGINE=InnoDB;

# 9/19/2018
ALTER TABLE `seatmanagement`.`seating_detail` 
ADD COLUMN `x_axis` VARCHAR(45) NULL AFTER `seating_date`,
ADD COLUMN `y_axis` VARCHAR(45) NULL AFTER `x_axis`;

CREATE TABLE `seatmanagement`.`organisation` ( 
 `organisation_id` VARCHAR(36) NOT NULL,  
 `organisation_name` VARCHAR(100) NOT NULL,
 PRIMARY KEY (`organisation_id`)
 )ENGINE=InnoDB;
 
ALTER TABLE building ADD COLUMN `organisation_id` VARCHAR(36) NOT NULL;
ALTER TABLE building ADD COLUMN `square_feet` FLOAT NOT NULL;
 
ALTER TABLE building 
ADD CONSTRAINT `building_fk_1` FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id);

ALTER TABLE `seatmanagement`.`floor` 
ADD COLUMN `floor_name` VARCHAR(45) NOT NULL AFTER `floor_type`;


#9/25/2018
ALTER TABLE block ADD COLUMN `block_name` VARCHAR(45) NOT NULL;
ALTER TABLE block ADD COLUMN `square_feet` FLOAT NOT NULL;

alter table seatmanagement.seating_detail drop column x_axis;
alter table seatmanagement.seating_detail drop column y_axis;
alter table seatmanagement.seating_detail add seating_position varchar(255);

ALTER TABLE `seatmanagement`.`seating` 
DROP FOREIGN KEY `team_id`;
ALTER TABLE `seatmanagement`.`seating` 
DROP COLUMN `team_id`,
DROP INDEX `team_id_idx` ;

ALTER TABLE `seatmanagement`.`reallocation` CHANGE `current_seating_detail_id` `seating_detail_id` varchar(36) NOT NULL;
ALTER TABLE `seatmanagement`.`reallocation` CHANGE `block_id` `reallocated_block_id` varchar(36) NOT NULL;
alter table `seatmanagement`.`reallocation` drop foreign key reallocation_fk_1;
alter table `seatmanagement`.`reallocation` drop column employee_id;
alter table `seatmanagement`.`reallocation` ADD COLUMN `reallocated_position` VARCHAR(100) NOT NULL;

# 26/09/2018

ALTER TABLE `seatmanagement`.`building` MODIFY `organisation_id` VARCHAR(36) NULL;

ALTER TABLE building DROP FOREIGN KEY building_fk_1;

ALTER TABLE building 
ADD CONSTRAINT `building_fk_1` FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id) ON DELETE SET NULL;

#27/09/2018

ALTER TABLE block DROP COLUMN square_feet;

ALTER TABLE `seatmanagement`.`seating_detail` MODIFY `seating_id` VARCHAR(36) NULL;

ALTER TABLE seating_detail DROP FOREIGN KEY seating_detail_fk_1;

ALTER TABLE seating_detail 
ADD CONSTRAINT `seating_detail_fk_1` FOREIGN KEY (seating_id) REFERENCES seating(seating_id) ON DELETE SET NULL;


# Block Foriegn key update
ALTER TABLE `seatmanagement`.`block` MODIFY `floor_id` VARCHAR(36) NULL;

ALTER TABLE block DROP FOREIGN KEY floor_id;

ALTER TABLE block 
ADD CONSTRAINT `floor_id` FOREIGN KEY (floor_id) REFERENCES floor(floor_id) ON DELETE SET NULL;

ALTER TABLE `seatmanagement`.`seating` 
ADD COLUMN `xaxis` VARCHAR(45) NOT NULL AFTER `seat_occupied`,
ADD COLUMN `yaxis` VARCHAR(45) NOT NULL AFTER `xaxis`;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(100) NOT NULL ,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username));
  
  
  CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  
  
INSERT INTO `seatmanagement`.`users` (`username`, `password`, `enabled`) VALUES ('Lead', '{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '1');
INSERT INTO `seatmanagement`.`users` (`username`, `password`, `enabled`) VALUES ('HR', '{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG', '1');

INSERT INTO `seatmanagement`.`user_roles` (`user_role_id`, `username`, `role`) VALUES ('1', 'HR', 'ROLE_ADMIN');
INSERT INTO `seatmanagement`.`user_roles` (`user_role_id`, `username`, `role`) VALUES ('2', 'Lead', 'ROLE_LEAD');

#28.9.2018
alter table  seatmanagement.system add column os varchar(20);

ALTER TABLE `seatmanagement`.`system` 
CHANGE COLUMN `network_type` `os` VARCHAR(20) NULL DEFAULT NULL ,
CHANGE COLUMN `allotment_status` `network_type` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `os` `allotment_status` VARCHAR(20) NULL DEFAULT NULL ;


ALTER TABLE `seatmanagement`.`seating_detail` 
CHANGE COLUMN `seating_position` `seating_accessories` VARCHAR(255) NULL DEFAULT NULL ,
ADD COLUMN `seating_row` VARCHAR(45) NULL AFTER `seating_accessories`,
ADD COLUMN `seatingColum` VARCHAR(45) NULL AFTER `seating_row`,
ADD COLUMN `seatingSystemNo` VARCHAR(45) NULL AFTER `seatingColum`;

# 30/09/2018
# Floor Foriegn key update

ALTER TABLE floor DROP FOREIGN KEY building_id;

ALTER TABLE floor 
ADD CONSTRAINT `floor_fk_1` FOREIGN KEY (building_id) REFERENCES building(building_id) ON DELETE CASCADE;

# Team team_members_count update

ALTER TABLE team DROP COLUMN team_members_count;

# Team Organisation foriegn key mapping

ALTER TABLE team add COLUMN organisation_id VARCHAR(36) NULL;

ALTER TABLE team 
ADD CONSTRAINT `team_fk_1` FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id) ON DELETE SET NULL;

# Employee Organisation foriegn key mapping

ALTER TABLE employee add COLUMN organisation_id VARCHAR(36) NULL;

ALTER TABLE employee 
ADD CONSTRAINT `employee_fk_2` FOREIGN KEY (organisation_id) REFERENCES organisation(organisation_id) ON DELETE SET NULL;

# Team Employee foriegn key mapping

ALTER TABLE employee DROP FOREIGN KEY employee_fk_1;

ALTER TABLE employee 
ADD CONSTRAINT `employee_fk_1` FOREIGN KEY (team_id) REFERENCES team(team_id) ON DELETE SET NULL;