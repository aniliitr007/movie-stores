create database akgcloud;

CREATE TABLE movie (
  id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE theater (
  id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  location_id int(10),
  PRIMARY KEY (id)
);

CREATE TABLE rating (
  movie_id int(10),
  rating tinyint unsigned
);

CREATE TABLE location (
  id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  latitude varchar(20) NOT NULL,
  longitude varchar(20) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE user_profile (
  user_id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  mobile varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  PRIMARY KEY (user_id)
);
