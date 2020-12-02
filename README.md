# MYANT assignment

**To run the project, clone the repo and run below command** \
mvn clean install \
*We need to have a mongo db database "ContactStore", having two documents "Contact" and "Group" running on localhost*

The API has the following entities (stored in MongoDB, create some sample entries):

  - Contact\
 First Name \
 Last Name\
 Address\
 Home Tel\
 Mobile Tel\
 Work Tel

- Groups\
 Any given contact can be added to a group

### Sample End points
**Create Contact**
```sh
curl -X POST \
  http://localhost:8080/contacts \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 1bbe33d7-9f9b-5e8c-e64b-a8fb51fa0eb8' \
  -d '{"firstName" : "firstName10",
		"lastName" : "lastName10",
		"address" : "address",
		"homeTel" : "111111",
		"mobileTel" : "2222222",
		"workTel" : "3333333"}'
```
**Create Group**
```sh
curl -X POST \
  http://localhost:8080/group/ \
  -H 'accept: application/json' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: bf5f823e-d073-d6ce-9f73-b276178aa745' \
  -d '{
"name": "group1",
"contacts": [
{"firstName" : "firstName10",
		"lastName" : "lastName10",
		"address" : "address",
		"homeTel" : "111111",
		"mobileTel" : "2222222",
		"workTel" : "3333333"}]}'
```


**Get contact by address**
```sh
curl -X GET http://localhost:8080/contactbyAddress/address
```


