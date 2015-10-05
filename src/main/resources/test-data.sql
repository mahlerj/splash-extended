insert into UserEntity(userId, username, password, token, role) values (1,'admin', 'admin',md5('adminadmin'),'ROLE_ADMIN');
insert into UserEntity(userId, username, password, token, role) values (2,'user1', 'user1', md5('5121-9992-011234'),'ROLE_CUSTOMER');
insert into UserEntity(userId, username, password, token, role) values (3,'user2', 'user2',md5('5121-5323-015678'),'ROLE_CUSTOMER');

insert into ContactEntity(id, userIdFk, customerId, email, firstname, lastname, name, mailingcity, mailingcountry, mailingpostalcode, mailingstreet, phone ) values (1, 2, '5121-9992-01', 'max@gmx.de', 'Max', 'Bauer', 'Max Bauer', 'Sindelfingen', 'Germany', '71063', 'Planiestr. 10', '0703112345');
insert into ContactEntity(id, userIdFk, customerId, email, firstname, lastname, name, mailingcity, mailingcountry, mailingpostalcode, mailingstreet, phone ) values (2, 2, '5121-9992-02', 'peter@gmx.de', 'Peter', 'Metzger', 'Peter Metzger', 'Stuttgart', 'Germany', '70376', 'Naststr. 27', '071112345');