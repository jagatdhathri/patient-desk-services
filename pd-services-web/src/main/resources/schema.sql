
CREATE TABLE Patient 
  ( 
     patient_id VARCHAR(10) NOT NULL, 
     first_name VARCHAR(255), 
     last_name VARCHAR(255), 
     email VARCHAR(255), 
     age INTEGER, 
     PRIMARY KEY (patient_id) 
  );