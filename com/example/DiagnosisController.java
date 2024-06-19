package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DiagnosisController {

    @FXML
    private TextField patientIdField;
    @FXML
    private TextField symptomsField;
    @FXML
    private TextField diagnosisField;
    @FXML
    private TextField medicinesField;
    @FXML
    private CheckBox wardRequiredCheckBox;
    @FXML
    private TableView<DiagnosisRecord> tableView;
    @FXML
    private TableColumn<DiagnosisRecord, Integer> colPatientID;
    @FXML
    private TableColumn<DiagnosisRecord, String> colSymptoms;
    @FXML
    private TableColumn<DiagnosisRecord, String> colDiagnosis;
    @FXML
    private TableColumn<DiagnosisRecord, String> colMedicines;
    @FXML
    private TableColumn<DiagnosisRecord, Boolean> colWardRequired;

    private Connection connect() {
        String url = "jdbc:mysql://localhost:3306/HospitalDB";
        String user = "root";
        String password = "Hansen21!";
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    private void handleSaveButtonAction() {
        String patientId = patientIdField.getText();
        String symptoms = symptomsField.getText();
        String diagnosis = diagnosisField.getText();
        String medicines = medicinesField.getText();
        boolean wardRequired = wardRequiredCheckBox.isSelected();

        String query = "INSERT INTO Diagnosis (PatientID, Symptoms, Diagnosis, Medicines, WardRequired) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(patientId));
            pstmt.setString(2, symptoms);
            pstmt.setString(3, diagnosis);
            pstmt.setString(4, medicines);
            pstmt.setBoolean(5, wardRequired);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSearchButtonAction() {
        String query = "SELECT * FROM Diagnosis";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ObservableList<DiagnosisRecord> data = FXCollections.observableArrayList();
            while (rs.next()) {
                data.add(new DiagnosisRecord(rs.getInt("PatientID"),
                        rs.getString("Symptoms"),
                        rs.getString("Diagnosis"),
                        rs.getString("Medicines"),
                        rs.getBoolean("WardRequired")));
            }
            tableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        colPatientID.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        colSymptoms.setCellValueFactory(new PropertyValueFactory<>("symptoms"));
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));
        colMedicines.setCellValueFactory(new PropertyValueFactory<>("medicines"));
        colWardRequired.setCellValueFactory(new PropertyValueFactory<>("wardRequired"));
    }

    public void handleButtonAction(ActionEvent actionEvent) {
    }
}
