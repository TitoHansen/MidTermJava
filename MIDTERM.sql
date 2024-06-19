CREATE DATABASE DiagnosisDB;

USE DiagnosisDB;

CREATE TABLE Diagnosis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(255),
    diagnosis VARCHAR(255)
);
