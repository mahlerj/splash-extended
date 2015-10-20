-- Attention IF EXISTS is Postgres specific
DROP TABLE IF EXISTS ContactEntity;
DROP TABLE IF EXISTS UserEntity;
DROP sequence hibernate_sequence;

CREATE TABLE UserEntity(
	userId INTEGER NOT NULL,
  	username varchar(255),	
	password varchar(255),
	token varchar(255),
  	role varchar(255),
  	PRIMARY KEY (userid)
);

CREATE TABLE ContactEntity
(
	id INTEGER NOT NULL,
    userIdFk INTEGER,
	email character varying(80),
  	firstname character varying(40),
  	lastname character varying(80),
  	mailingcity character varying(40),
  	mailingcountry character varying(80),
  	mailingpostalcode character varying(20),
  	mailingstreet character varying(255),
  	phone character varying(40),
  	PRIMARY KEY (id)
);

CREATE sequence hibernate_sequence;



