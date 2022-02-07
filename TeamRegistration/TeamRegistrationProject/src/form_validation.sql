CREATE DATABASE IF NOT EXISTS form_validation;

USE form_validation;

CREATE TABLE info (
  info_id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  dob varchar(255) DEFAULT NULL,
  PRIMARY KEY (info_id)
) ENGINE = InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;