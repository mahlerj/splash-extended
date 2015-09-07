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

CREATE TABLE UserInfoEntity (
        userInfoId SERIAL PRIMARY KEY,
        userIdFk INTEGER references UserEntity(userId),
       	customerId varchar(255),	
       	typ varchar(255),	
        identityNr varchar(255),
        orgNr varchar(255),
        company varchar(255),	
        invoiceLab varchar(255),
        facilityNr varchar(255),
        contactPersNr varchar(255),	
        email varchar(255),
        mainPoPox varchar(255),
        mainStreet varchar(255),
        mainZipcode varchar(255),
        mainCity varchar(255),
        mainName varchar(255),
        mainSurname varchar(255),
        billingPoPox varchar(255),
        billingStreet varchar(255),	
        billingZipcode varchar(255),
        billingCity varchar(255),
        billingName varchar(255),
        billingSurname varchar(255)
);

CREATE TABLE ContractInfoEntity (
        contractInfoId SERIAL PRIMARY KEY,
		userIdFk INTEGER references UserEntity(userId),
       	contractId varchar(255),
       	facilityName varchar(255),
       	parkingLotCount INTEGER      	
);

CREATE TABLE VRMEntity (
        vrmId SERIAL PRIMARY KEY,
		userIdFk INTEGER references UserEntity(userId),
       	contractInfoIdFk INTEGER references ContractInfoEntity(contractInfoId),
		vrmName varchar(255),
		description varchar(255)
);