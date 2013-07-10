-- Create database Series_Db
CREATE DATABASE IF NOT EXISTS MOVIESDB character set utf8;
GRANT all privileges on MOVIESDB.* to kt@'localhost' identified by 'kt';

use MOVIESDB;

-- Create table TMOVIES
CREATE TABLE IF NOT EXISTS TMOVIES (
    id int not null AUTO_INCREMENT,
    name varchar(255) not null,
    release_year int(4) not null,
    status varchar(255) not null,
    mtype varchar(255) not null,
    PRIMARY KEY (id)
);


-- Create table TMOVIEATTRIBUTES
CREATE TABLE IF NOT EXISTS TMOVIEATTRIBUTES (
    id int not null AUTO_INCREMENT,
    attr_name varchar(255) not null,
    attr_value varchar(255) not null,
    m_id int not null,
    PRIMARY KEY (id),
    FOREIGN KEY (m_id)
	REFERENCES TMOVIES (id)
);

