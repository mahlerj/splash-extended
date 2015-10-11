# Heroku Template


REST API Calls to Java _ Tier

## API Specification
###Login and get a User Token back
- Request: http://localhost:8080/logiclineportal/user/login
- Method Type: POST
- Header Values: Content-Type: application/json;
Example: 
- Request Data: {"username":"user1","password":"user1"} 
- Response Data: {
    "firstName": "Max",
    "lastName": "Bauer",
    "role": "ROLE_CUSTOMER",
    "userId": "2",
    "token": "332bc0ddba3c4bc792c4829ff3834ca9"
}

###Login with CAPTCHA and get a User Token back
- Request: http://localhost:8080/logiclineportal/user/login
- Method Type: POST
- Header Values: Content-Type: application/json;
Example: 
- Request Data: {"username":"user1","password":"user1", "recaptcha_challenge_field":"xxxx", "recaptcha_response_field":"yyyy"}
- Response Data: 
ResponseCode: 200
Response Data: {
    "firstName": "Max",
    "lastName": "Bauer",
    "role": "ROLE_CUSTOMER",
    "userId": "2",
    "token": "332bc0ddba3c4bc792c4829ff3834ca9"
}
ResponseCode: 412 (captcha failed)
null
ResponseCode: 401 (login failed)
null			


### @Deprecated Get User Info Object (for current User)
- Request: http://localhost:8080/logiclineportal/user/edit
- Method Type: GET
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Example: 
- Request Data:
- Response Data: {
    "userInfoId": 1,
    "userIdFk": 2,
    "customerId": "5121-9992-01",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "max@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Planiestr. 10",
    "mainZipcode": "71063",
    "mainCity": "Sindelfingen",
    "mainName": "Max",
    "mainSurname": "Bauer"
}

###Get User Info Object 
- Request: http://localhost:8080/logiclineportal/user/edit/:userIdFk
- Method Type: GET
- Header Values: Content-Type: application/json; token: 332bc0ddba3c4bc792c4829ff3834ca9
Updated URL to retrieve UserInfo by User ID field 
Example: 
- Request URL: http://localhost:8080/logiclineportal/user/edit/2
- Request Data:
- Response Data: 
{
    "userInfoId": 1,
    "userIdFk": 2,
    "customerId": "5121-9992-01",
    "typ": null,
    "identityNr": null,
    "orgNr": null,
    "company": "nothing",
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": null,
    "email": "max@gmx.de",
    "mainPoPox": "",
    "mainStreet": "Planiestr. 10",
    "mainZipcode": "71063",
    "mainCity": "Sindelfingen",
    "mainName": "Max",
    "mainSurname": "Bauer"
}

###Update User Info Object (for current User)
- Request: http://apcoabackend.herokuapp.com/user/edit
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: {
    "userInfoId": 1,
    "userIdFk": "0031100000ZOVoGAAX",
    "customerId": "DPD-00000001",
    "typ": null,
    "identityNr": null,
    "orgNr": "501011-6472",
    "company": null,
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": "0123123",
    "email": "max@max.de",
    "mainPoPox": "999999",
    "mainStreet": "Badstrasse 12",
    "mainZipcode": "70197",
    "mainCity": "Frankfurt",
    "mainName": "Max",
    "mainSurname": "Mustermann",
    "businessCustomer": false,
    "creditRatingEnabled": true
}
- Response Data:

###Update User Info Object
- Request: http://apcoabackend.herokuapp.com/user/edit/:userIdFk
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Updated URL to update UserInfo by User ID field 
Example: 
- Request URL: http://apcoabackend.herokuapp.com/user/edit/2
- Request Data: {
    "userInfoId": 1,
    "userIdFk": "0031100000ZOVoGAAX",
    "customerId": "DPD-00000001",
    "typ": null,
    "identityNr": null,
    "orgNr": "501011-6472",
    "company": null,
    "invoiceLab": null,
    "facilityNr": null,
    "contactPersNr": "0123123",
    "email": "max@max.de",
    "mainPoPox": "999999",
    "mainStreet": "Badstrasse 12",
    "mainZipcode": "70197",
    "mainCity": "Frankfurt",
    "mainName": "Max",
    "mainSurname": "Mustermann",
    "businessCustomer": false,
    "creditRatingEnabled": true
}
- Response Data:

###Get Contract List (for current User)
- Request: http://apcoabackend.herokuapp.com/contract/list
- Method Type: GET
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
- Response Data8: [{"contractInfoId": 1,"userIdFk": 2,"contractId": "32170-8002-01","facilityName": "Globen","parkingLotCount": 3},{"contractInfoId": 2,"userIdFk": 2,"contractId": "32170-8002-02","facilityName": "Globen","parkingLotCount": 1}]

###Get Contract List
- Request: http://apcoabackend.herokuapp.com/contract/list/:userIdFk
- Method Type: GET
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
- Response Data:[ {"contractInfoId": 1,"userIdFk": 2,"contractId": "32170-8002-01","facilityName": "Globen","parkingLotCount": 3},{"contractInfoId": 2,"userIdFk": 2,"contractId": "32170-8002-02","facilityName": "Globen","parkingLotCount": 1}]

###Get Contract Info Object (for upper part in edit VRM):
- Request: http://apcoabackend.herokuapp.com/contract/edit/:contractInfoId
- Method Type: GET (the PathVariable (i.e. 1) is the contractInfoId from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
- Response Data: {"contractInfo":{"contractInfoId":1,"userIdFk":2,"contractId":"32170-8002-01","facilityName":"Globen","parkingLotCount":3}} 

###Update Contract Info Object :
- Request: http://apcoabackend.herokuapp.com/contract/edit/:contractInfoId
- Method Type: PUT (the PathVariable (i.e. 1) is the contractInfoId from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: {"facilityName":"GlobenNew","parkingLotCount":4}
- Response Data: 


###Get Contact List by Contract ID for Search functionality
- Request: http://apcoabackend.herokuapp.com/user/search/:customerId
- Method Type: GET (the PathVariable (i.e. 1234,  12, 1) is  (part of) customerId 
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
- Response Data (i.e. 1234): [{"1":"12340-8002-01","17":"32170-8002-02"}] (1st value is userIdFk)
Get Contract Info (for upper part in edit VRM):
- Request: http://apcoabackend.herokuapp.com/contract/info/1
- Method Type: GET (the PathVariable (i.e. 1) is the contractInfoId from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
- Response Data: {"contractInfo":{"contractInfoId":1,"userIdFk":2,"contractId":"32170-8002-01","facilityName":"Globen","parkingLotCount":3}} 

###Get the VRMs (licence plates) for a choosen Contract
- Request: http://apcoabackend.herokuapp.com/vrm/edit/1
- Method Type: GET (the PathVariable (i.e. 1) is the contractInfoId from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
- Response Data: [{"vrmId": 1, "userIdFk": 2, "contractInfoIdFk": 1, "vrmName": "PNC296", "description": "description 1"},{"vrmId": 2,"userIdFk": 2,"contractInfoIdFk": 1,"vrmName": "PGH111","description": ""}]  (i.e. 2 vrms already used from 15 possible (parkingLotCount:3) x 5)

###Update or Delete VRMs
- Request: http://apcoabackend.herokuapp.com/vrm/edit/ 1
- Method Type: PUT (the PathVariable (i.e. 1) is the contractInfoId from the chosen Contract i.e. from contract with number "32170-8002-01")
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: [{“vrmid”: "1", ”vrmname”: "XXXX", “description”: “test1”}, {“vrmid”: "2"", ”vrmname”: "PGH111", “description”: “XXXX”}, {“vrmid”: "3", ”vrmname”: "PGO584”, “description”: “test3”}, {“vrmid”: "4", “vrmname”: "PNB765", “description”: “test4”}, {“vrmid”: "5", “vrmname”: "PUU253", “description”: “test5”}, {“vrmid”: "6", “vrmname”: "PHU383", “description”: “test6”}, {“vrmid”: "7", “vrmname”: "", “description”: “”}]
(i.e. Nr.1, 2 for update to "XXXX", Nr.7 for delete) 
I'm not sure if there is a need to send all VRM's back to the server or just send updated VRM's.Carsten, could you please clarify it for me?  yes only updates is better
- Response Data:

###Create / New VRM
- Request: http://apcoabackend.herokuapp.com/vrm/edit/create/1
- Method Type: POST (the PathVariable (i.e. 1) is the contractInfoId from the chosen Contract i.e. from contract with number "32170-8002-01"))
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: [{"vrnname": "TTV235", "description": "test1"}, {"vrnname": "POI475", "description": "test1"}, {"vrnname": "POU273", "description": "test1"}] (i.e. 3 new VRMs for contract with contractInfoId=1) 
- Response Data:

###Update/Create new Password
- Request: http://apcoabackend.herokuapp.com/user/edit/password/:userIdFk
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example:  
- Request Data: 
Option 1: {"oldPassword":"1234","newPassword":"5678"} 	 (verify oold password)
Option 2: {newPassword":"5678"}
Option 3: {}  or {"":""}						 (create new password)
- Response Data:

###Create New User
- Request: http://apcoabackend.herokuapp.com/user/create/
- Method Type: POST
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad

Example: 
- Request Data:
{
    "contactDto": {
        "customerId": "555",
        "identityNr": "ID-231122",
        "orgNr": "888",
        "company": "South Bank",
        "contactPersNr": "CP: 999999",
        "email": "martin@sauber.de",
        "mainPoPox": "PO-Box-73",
        "mainStreet": "MainStreet 23",
        "mainZipcode": "23234",
        "mainCity": "Stockholm",
        "mainName": "Martin",
        "mainSurname": "Sauber",
        "billingPoPox": "Billing PO 3464",
        "billingStreet": "Lokaladministration/M4",
        "billingZipcode": "106 40",
        "billingCity": "Stockholm",
        "billingName": "ff",
        "billingSurname": "rr"
    },
    "contractInfoEntity": {
        "contractId": "sdsdsd",
        "facilityName": "Globen",
        "parkingLotCount": 4
    }
}

- Response Data: 

###Get UC Business Info
- Request: http://apcoabackend.herokuapp.com/uc/business/:inputNumber
- Method Type: GET
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
http://apcoabackend.herokuapp.com/uc/business/5561682518
- Response Data:
{
    "valid": true,
    "message": null,
    "positiveRating": true,
    "firstName": null,
    "lastName": "Görberga Bokhandel AB",
    "street": "Box 261",
    "zipcode": "31298",
    "city": "Våxtorp",
    "email": null,
    "phone": "+46-345-52 91 31"
}
Example: 
- Request Data: 
http://apcoabackend.herokuapp.com/uc/business/9999
- Response Data:
{
    "valid": false,
    "message": "Object number is incorrectly/incompletly entered!",
    "positiveRating": false,
    "firstName": null,
    "lastName": null,
    "street": null,
    "zipcode": null,
    "city": null,
    "email": null,
    "phone": null
}

###Get UC Individual Info
- Request: http://apcoabackend.herokuapp.com/uc/individual/:inputNumber
- Method Type: GET
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad
Example: 
- Request Data: 
http://apcoabackend.herokuapp.com/uc/individual/4907011813
- Response Data:
{
    "valid": true,
    "message": null,
    "positiveRating": true,
    "firstName": "Håkan",
    "lastName": "Zerykier",
    "street": "Lillavan 7",
    "zipcode": "44692",
    "city": "Skepplanda",
    "email": null,
    "phone": null



     