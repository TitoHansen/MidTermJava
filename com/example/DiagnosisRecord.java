package com.example;

public class DiagnosisRecord {
    private final Integer patientID;
    private final String symptoms;
    private final String diagnosis;
    private final String medicines;
    private final Boolean wardRequired;

    public DiagnosisRecord(Integer patientID, String symptoms, String diagnosis, String medicines, Boolean wardRequired) {
        this.patientID = patientID;
        this.symptoms = symptoms;
        this.diagnosis = diagnosis;
        this.medicines = medicines;
        this.wardRequired = wardRequired;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public Boolean getWardRequired() {
        return wardRequired;
    }
}
