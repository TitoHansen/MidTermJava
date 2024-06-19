module org.example.diagnosisapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens org.example.diagnosisapp to javafx.fxml;
    exports org.example.diagnosisapp;
    exports com.example;

}

