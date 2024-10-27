module com.example.csc311week09regexandjavadoc {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.csc311week09regexandjavadoc to javafx.fxml;
    exports com.example.csc311week09regexandjavadoc;
}