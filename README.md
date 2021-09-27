# Employee-producer-service

This service is designed to publish kafka records to a local kafka topic "test_topic".

Prerequisites:

Local Instances of zookeeper and kafka should be up and running.

Service:
The Producer service exposes 3 endpoints : store, update and read.
Store and Update accepts JSON payloads of an Employee class and also a header "FileType" which can be "CSV" or "XML".
Read can be performed irrespective of fileType, using a param "id" which is a combination of name_dob.
All the 3 endpoints publish record to Kafka, and the consumer service consumes records and performs the corresponding operation.

Unit tests have been written for each operation.


Topic Name :  "test_topic".
STORE : http://localhost:9001/file/store
UPDATE: http://localhost:9001/file/update
READ: http://localhost:9001/file/read/csv2_20-08-2111

Only Read operation is done over HTTP.


