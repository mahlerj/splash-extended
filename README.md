# Apcoa Backend

Acoa Backend provides a REST API for CRUD operations on customer Data.

## API Specification

### Login and get a User Token back
- Request: http://apcoabackend.herokuapp.com/user/login
- Method Type: POST
- Header Values: Content-Type: application/json; 

Example:      
- Request Data: {"username":"75121-9992-01","password":"1234"}          
- Response Data: 16b63e94679ec680d1395b541ba7b2ad     

### Get User Info Object
- Request: http://apcoabackend.herokuapp.com/user/edit
- Method Type: GET
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad

Example:      
- Request Data:
- Response Data: {"userInfoId":1,"userIdFk":2,"customerId":"7506-8002-01","typ":"B2B","identityNr":"",
			"orgNr":"502032-9081","company":"Skandinaviska Enskilda Banken AB","invoiceLab":"",
			"facilityNr":"08-600 25 50","contactPersNr":"","email":"","mainPoPox":"",
			"mainStreet":"","mainZipcode":"","mainCity":"","mainName":"","mainSurname":"",
			"billingPoPox":"","billingStreet":"Lokaladministration/M4","billingZipcode":"106 40",
			"billingCity":"Stockholm","billingName":"","billingSurname":""}   
   
### Update Info Object
- Request: http://apcoabackend.herokuapp.com/user/edit
- Method Type: PUT
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad

Example:      
- Request Data: {"userInfoId":1,"userIdFk":2,"customerId":"7506-8002-01","typ":"B2B","identityNr":"0815",
			"orgNr":"502032-9081","company":"Skandinaviska Enskilda Banken AB","invoiceLab":"",
			"facilityNr":"08-600 25 50","contactPersNr":"","email":"","mainPoPox":"",
			"mainStreet":"","mainZipcode":"","mainCity":"","mainName":"","mainSurname":"",
			"billingPoPox":"","billingStreet":"Lokaladministration/M4","billingZipcode":"106 40",
			"billingCity":"Stockholm","billingName":"","billingSurname":""}   		        
- Response Data: 

### Get Contract List
- Request: http://apcoabackend.herokuapp.com/contract/list
- Method Type: GET
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad 

Example:      
- Request Data: 
- Response Data: {"1":"32170-8002-01","2":"32170-8002-02"}

### Get Contract Info (for upper part in edit VRM):
- Request: http://apcoabackend.herokuapp.com/contract/info?contractInfoId=1
- Method Type: GET (GET paramter contractInfoId is the key from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad 

Example:      
- Request Data: 
- Response Data: {"contractInfo":{"contractInfoId":1,"userIdFk":2,"contractId":"32170-8002-01","facilityName":"Globen","parkingLotCount":3}} MISSING! some attributes from mockup (Customer ID, Prename, Surename etc.)

### Get the VRMs (licence plates) for a choosen Contract
- Request: http://apcoabackend.herokuapp.com/vrm/edit?contractInfoId=1
- Method Type: GET (GET paramter contractInfoId is the key from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad 

Example:      
- Request Data: 
- Response Data: {"1":"PNC296","2":"PGH111","3":"PGO584","4":"PNB765","5":"PUU253","6":"PHU383","7":"PTT632"}
	(i.e. 7 vrms already used from 15 possible (parkingLotCount:3) x 5)
	
### Update or Delete VRMs
- Request: http://apcoabackend.herokuapp.com/vrm/edit?contractInfoId=1
- Method Type: PUT (GET paramter contractInfoId is the key from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad 

Example:      
- Request Data: {"1":"XXXX","2":"PGH111","3":"PGO584","4":"PNB765","5":"PUU253","6":"PHU383","7":""}
	(i.e. Nr.1 for update to "XXXX", Nr.7 for delete)
- Response Data: 

### Create / New VRM
- Request: http://apcoabackend.herokuapp.com/vrm/edit/create?contractInfoId=1
- Method Type: POST (GET paramter contractInfoId is the key from the chosen Contract i.e. from contract with number "32170-8002-01" )
- Header Values: Content-Type: application/json; token: 16b63e94679ec680d1395b541ba7b2ad 

Example:      
- Request Data: ["TTV235", "POI475", "POU273"]
	(i.e. 3 new VRMs for contract with contractInfoId=1)
- Response Data: 
     