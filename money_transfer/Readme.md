## How to build
`gradle createJar`  
In *build/libs* will be jar file to run

## How to run
`java -jar money_transfer-all-1.0-SNAPSHOT.jar`  
url - `localhost:4567`
## How to use
Each user has default account with 0 INR  

User End Points :-
GET `/getAllUsers` - get all users  
GET `/user/:id` - get user  
POST `/addUser` - create new user  
PUT `/editUser/:id` - update existing user  
DELETE `/deleteUser/:id` - delete user
OPTION `/isUserExist/:id` - check if user exist 
GET `/user/:id/accounts` - get user accounts   

Account End Points :-
GET `/getAllAccounts` - get all accounts  
GET `/account/:accountNumber` - get account by its number
GET `/account/:accountNumber/getAllTransactions` - get account by its number  
POST `/addAccount` - create new accounts
POST `/account/:accountNumber/transact` - perform transaction on accounts   
PUT `/editAccount/:accountNumber` - update existing accounts  
DELETE `/deleteAccount/:accountNumber` - delete accounts 
OPTION `/isAccountExist/:id` - check if account exist 

Transaction End Points :-
GET `/getAllTransactions` - get all transactions   
GET `/transactions/:id` - get transaction by its id  

User body example  
`{"firstName":"Vivek","lastName":"Grewal","email":"vivek.grewal@gmail.com","accounts":[]}`

Account body example   
`{"name":"EUR account for savings","currency":"EUR","amount":"100"}`

Transaction body example   
`{"accountNumberTo": "1000005", "amount":100}`

GET `http://localhost:4567/getAllUsers` 
`{"status": "SUCCESS","data": [{"id": "1","firstName": "Vivek","lastName": "Grewal","email":"vivek.g@gmail.com","accounts": [{"name": "Vivek Savings Account","accountNumber": "1000001","balance": 1000,"currency": "INR","transactionIds": []},{"name": "Shreya OD Account","accountNumber":"1000002","balance": 500,"currency": "EUR","transactionIds": []}]},{"id": "2","firstName": "Shreya","lastName": "S","email": "s.shreya@gmail.com","accounts": [{"name": "Default account","accountNumber":"1000004","balance": 0,"currency": "INR","transactionIds": []}]},{"id": "3","firstName": "Aakash","lastName":"Grewal","email": "aakash.g@yahoo.in","accounts": [{"name": "Default account","accountNumber": "1000005","balance": 0,"currency": "INR","transactionIds": []}]}]}`


GET `http://localhost:4567/accounts/1000001`  
`{"status": "SUCCESS","data": {"name": "Vivek Savings Account","accountNumber": "1000001","balance": 1000,"currency": "INR","transactionIds": []}}`

POST `http://localhost:4567/account/1000001/transact`   body - `{"accountNumberTo": "1000005", "amount":100}`

`{"status": "SUCCESS","message": "{\"idSequence\":1,\"id\":\"1\",\"amount\":100.0,\"accountFrom\":{\"name\":\"Vivek Savings Account\",\"accountNumber\":\"1000001\",\"balance\":900.0,\"currency\":\"INR\",\"transactionIds\":[\"1\"]},\"accountTo\":{\"name\":\"Default account\",\"accountNumber\":\"1000005\",\"balance\":100.0,\"currency\":\"INR\",\"transactionIds\":[\"1\"]},\"reasonForTransfer\":\"\",\"status\":\"SUCCESS\"}"}`