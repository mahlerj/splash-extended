insert into UserEntity(userId, username, password, token, role) values (1,'admin', 'admin',md5('adminadmin'),'ROLE_ADMIN');
insert into UserEntity(userId, username, password, token, role) values (2,'user1', 'user1', md5('5121-9992-011234'),'ROLE_CUSTOMER');
insert into UserEntity(userId, username, password, token, role) values (3,'user2', 'user2',md5('5121-5323-015678'),'ROLE_CUSTOMER');

insert into UserInfoEntity(userInfoId, userIdFk, customerId, typ, identityNr, orgNr, company, invoiceLab, facilityNr, contactPersNr, email, mainPoPox, mainStreet, mainZipcode, mainCity, mainName, mainSurname, billingPoPox, billingStreet, billingZipcode, billingCity, billingName, billingSurname) values (1, 2, '5121-9992-01', 'B2B', 'ID-23432','902177-93231', 'Capital Bank', 'invoiceLab-CaBa', '08-600 25 50', 'CP: 12211234', 'peter@schober.de', 'PO-Box-73', 'MainStreet 23', '23234', 'Stockholm', 'Peter', 'Schober', 'Billing PO 3464', 'Lokaladministration/M4', '106 40', 'Stockholm', 'Martin','Schmidt');
insert into UserInfoEntity(userInfoId, userIdFk, customerId, typ, identityNr, orgNr, company, invoiceLab, facilityNr, contactPersNr, email, mainPoPox, mainStreet, mainZipcode, mainCity, mainName, mainSurname, billingPoPox, billingStreet, billingZipcode, billingCity, billingName, billingSurname) values (2, 3, '5121-5323-01', 'B2B', '', '92333-1939', 'Memira AB', 'Motkod:7365566631544', '08-39 18 00', '', '', '', 'Arenavägen 61, plan 9' , '', 'Johanneshov', '', '', '', 'Box 171', '831 22', 'Östersund', '', '');

insert into ContractInfoEntity(contractInfoId, userIdFk, contractId, facilityName, parkingLotCount) values (1, 2, '32170-8002-01', 'Globen', 3);
insert into ContractInfoEntity(contractInfoId, userIdFk, contractId, facilityName, parkingLotCount) values (2, 2, '32170-8002-02', 'Globen', 1);
insert into ContractInfoEntity(contractInfoId, userIdFk, contractId, facilityName, parkingLotCount) values (3, 3, '32170-8003-01', 'Globen', 1);

insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (1, 2, 1, 'PNC296','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (2, 2, 1, 'PGH111','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (3, 2, 1, 'PGO584','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (4, 2, 1, 'PNB765','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (5, 2, 1, 'PUU253','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (6, 2, 1, 'PHU383','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (7, 2, 1, 'PTT632','');

insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (8, 3, 2, 'HD-UW-1210','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (9, 3, 2, 'BB-ZU-8273','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (10, 3, 2, 'BB-OK-2927','');
insert into VRMEntity(vrmId, userIdFk, contractInfoIdFk, vrmName, description) values (11, 3, 2, 'BB-OK-1237','');