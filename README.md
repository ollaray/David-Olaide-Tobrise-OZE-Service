# David-Olaide-Tobrise-OZE-Service
Java (Spring Boot) Backend Assessment A hospital is planning to migrate their locally available data and medical records into an API service. The API service is meant to be available internally to all staff, and for official use only.


***DELETE API***: 
Description: This actually does a batch delete through a range of dates: 


Method: Delete Endpoint: http://localhost:9090/api/v1/patients/2021-01-18/2021-05-30 


Request Param: {dateFrom}/dateTo 


Header Parameter: uuid. 


Success Output: 200

*****DOWNLOAD API***
Description:This downloads the entire patient list as CSV.


Method: Get Endpoint: http://localhost:9090/api/v1/csv/download/ 


Header Parameter: UUID 




Request Body: None;




Reponse Output: CSV

***FETCH PATIENT RECORDS BY AGE***

Description:This API accepts age as a Request Parameters and returns a JSON list of patients from that age above. 



Method: Get EndPoint: http://localhost:9090/api/v1/patients/2/ 




PathVariable: {age} 




Response Format: JSON, 




Header Parameter: UUID




***UPDATE STAFF***



Description:This API is responsible for updating staff records. 



Method: PUT 



Endpoint: http://localhost:9090/api/v1/staff/2 


Path Variable: id Request Body : JSON 



Response Body: JSON


***CREATE STAFF***



Description: This API is responsible for the creation of Staff and automatically assigns a UUID value to the staff and persists it in the database. 



Method: POST, 



Endpoint: http://localhost:9090/api/v1/staff/ 




Response Body: JSON, 



Request Body: JSON
