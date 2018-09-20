
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

