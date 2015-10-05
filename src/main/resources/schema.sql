-- Attention IF EXISTS is Postgres specific

DROP TABLE IF EXISTS VRMEntity;
DROP TABLE IF EXISTS UserInfoEntity;
DROP TABLE IF EXISTS ContractInfoEntity;
DROP TABLE IF EXISTS UserEntity;


CREATE TABLE UserEntity(
	userId SERIAL PRIMARY KEY,
  	username varchar(255) UNIQUE,	
	password varchar(255),
	token varchar(255) UNIQUE,
  	role varchar(255)
  	
);

CREATE TABLE ContactEntity
(
	id serial NOT NULL,
	userIdFk INTEGER references UserEntity(userId),
	customerId character varying(40),
	email character varying(80),
  	firstname character varying(40),
  	lastname character varying(80),
  	name character varying(121),
  	mailingcity character varying(40),
  	mailingcountry character varying(80),
  	mailingpostalcode character varying(20),
  	mailingstreet character varying(255),
  	phone character varying(40),
  	CONSTRAINT contact_pkey PRIMARY KEY (id)
);

