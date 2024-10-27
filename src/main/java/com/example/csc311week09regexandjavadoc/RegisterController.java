package com.example.csc311week09regexandjavadoc;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterController implements Initializable {
    @FXML
    private TextField lastName;

    @FXML
    private TextField firstName;

    @FXML
    private TextField zipCode;

    @FXML
    private TextField dateOfBrith;

    @FXML
    private TextField email;

    @FXML
    private Button addInfoButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //A generic listener approach
        ChangeListener<String> textChangeListener = (observable, oldValue, newValue) -> changeButtonState();
        //Add a listener for each text fields
        lastName.textProperty().addListener(textChangeListener);
        firstName.textProperty().addListener(textChangeListener);
        email.textProperty().addListener(textChangeListener);
        dateOfBrith.textProperty().addListener(textChangeListener);
        zipCode.textProperty().addListener(textChangeListener);

        addInfoButton.setDisable(true);
    }

    @FXML
    void addInfo() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("success-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 200);
        stage.setTitle("Success!");
        stage.setScene(scene);
        // Keep window on top and wait be close
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();


    }


    /**
     * check the all texts valid input, if yes change the state of button
     *
     */
    private void changeButtonState(){
        String lastNameText = lastName.getText();
        String firstNameText = firstName.getText();
        String emailText = email.getText();
        String dateOfBirthText = dateOfBrith.getText();
        String zipCodeText = zipCode.getText();

        String lastAndLastNameRegex = "^[a-zA-Z]{2,25}$";
        String emailRegex = "^.+@farmingdale.edu$";
        String dateOfBirthRegex = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$";
        String zipCodeRegex = "^\\d{5}$";

        // Check if any of the fields are valid inputs
        boolean isValid = regexCheck(lastAndLastNameRegex,firstNameText)&&
                            regexCheck(lastAndLastNameRegex,lastNameText)&&
                            regexCheck(emailRegex,emailText)&&
                            regexCheck(dateOfBirthRegex,dateOfBirthText)&&
                            regexCheck(zipCodeRegex,zipCodeText);

        addInfoButton.setDisable(!isValid);

    }

    /**
     * a tool method to check the input text is matches the regular expression
     * if not , return false;
     * @param regex regular expression
     * @param text input text
     * @return if text matches regex return true, else return false
     */
    private Boolean regexCheck(String regex,String text){
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

}