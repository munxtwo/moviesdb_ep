-- Create table TGENRE
CREATE TABLE IF NOT EXISTS TGENRE (
	id int not null AUTO_INCREMENT,
    genre_name varchar(255) not null,
	PRIMARY KEY (id),
	UNIQUE (genre_name)
);

-- Insert values
INSERT INTO TGENRE (genre_name) VALUES
("Comedy"), ("Horror"), ("Documentary"), ("Romance"), ("Action"), ("Drama"), ("Musical");