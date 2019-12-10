package GUI;

import Exceptions.RegistrationException;
import BusinessLogic.RegisterNewUser;
import Database.Mysql;
import Objects.User;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;

import javafx.scene.layout.AnchorPane;
import java.util.ResourceBundle;

public class RegistrationUI extends Application {

Button registerButton;
Stage window;
TextField firstNameText, lastNameText, addressText, zipText, emailText, usernameText, stateBox, securityATextField;
PasswordField passwordText, ssnTextField;
ChoiceBox<String> securityQChoiceBox;
User user;

public static void main(String[] args) {
launch(args);
}

/**
* private String newUserName;
private String newPassword;
private String firstName;
private String lastName;
private String zip;
private String state;
private String email;
private String ssn;
private String secQuestions;
*/
@Override
public void start(Stage primaryStage) throws Exception {
window = primaryStage;
window.getIcons().add(new Image("icon.png"));
window.setTitle("Bus Resvoir - Registration");
window.setResizable(false);

//Creating gridPane
GridPane gridPane = new GridPane();

gridPane.setAlignment(Pos.CENTER);

gridPane.setVgap(10);

gridPane.setHgap(10);

gridPane.setPadding(new Insets(50, 50, 50, 50));

// Add Column Constraints


       // columnOneConstraints will be applied to all the nodes placed in column one.
       ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
       columnOneConstraints.setHalignment(HPos.RIGHT);

       // columnTwoConstraints will be applied to all the nodes placed in column two.
       ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
       columnTwoConstrains.setHgrow(Priority.ALWAYS);

       gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

/**
* Adding inputs such as buttons and etc.
*/
// Add Header
        Label headerLabel = new Label("Registration Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add First Name Label
        Label firstNameLabel = new Label("First Name : ");
        gridPane.add(firstNameLabel, 0,1);

        // Add Name First Name Text Field
        TextField firstNameText = new TextField();
        firstNameText.setPrefWidth(230);
        firstNameText.setMaxWidth(230);
        firstNameText.setPromptText("First Name");

        gridPane.add(firstNameText, 1,1);
       
        // Add Last Name Label
        Label lastNameLabel = new Label("Last Name : ");
        gridPane.add(lastNameLabel, 0,2);
       
        // Add Last Name Text Field
        TextField lastNameText = new TextField();
        lastNameText.setPrefWidth(230);
        lastNameText.setMaxWidth(230);
        lastNameText.setPromptText("Last Name");

        gridPane.add(lastNameText, 1, 2);
       
        // Add Address Label
        Label addressLabel = new Label("Address : ");
        gridPane.add(addressLabel, 0,3);
       
        // Add Address Text Field
        TextField addressText = new TextField();
        addressText.setPrefWidth(230);
        addressText.setMaxWidth(230);
        addressText.setPromptText("Address");
        gridPane.add(addressText, 1,3);
       
        // Add ZIP Label
        Label zipLabel = new Label("Zip Code : ");
        gridPane.add(zipLabel, 0,4);
       
        // Add zip Text Field
        TextField zipText = new TextField();
        zipText.setPrefWidth(230);
        zipText.setMaxWidth(230);;
        zipText.setPromptText("Zip Code");
        gridPane.add(zipText, 1,4);
       
        // Add state Label
        Label stateLabel = new Label("State Name : ");
        gridPane.add(stateLabel, 0,5);    
        //Add state drop down menu
        TextField stateBox = new TextField();
        stateBox.setPromptText("State");
        stateBox.setPrefWidth(230);
        stateBox.setMaxWidth(230);;
        gridPane.add(stateBox, 1, 5);
       
       
        //Add Email Label
        Label emailLabel = new Label("Email : ");
        gridPane.add(emailLabel, 0,6);
       
        //Add email Text Field
        emailText = new TextField();
        emailText.setPromptText("Email");
        emailText.setPrefWidth(230);
        emailText.setMaxWidth(230);
        gridPane.add(emailText, 1,6);
       
        //Add Username Label
        Label usernameLabel = new Label("Username : ");
        gridPane.add(usernameLabel, 0,7);
       
        //Add email Username text
        usernameText = new TextField();
        usernameText.setPromptText("Username");
        usernameText.setPrefWidth(230);
        usernameText.setMaxWidth(230);
        gridPane.add(usernameText, 1,7);
       
       
        //Add Password Label
        Label passwordLabel = new Label("Password : ");
        gridPane.add(passwordLabel, 0,8);
       
        //Add password text
        passwordText = new PasswordField();
        passwordText.setPrefWidth(230);
        passwordText.setMaxWidth(230);
        passwordText.setPromptText("Password");
        gridPane.add(passwordText, 1,8);
       
        //Add SSN label
        Label ssnLabel = new Label("SSN: ") ;
        gridPane.add(ssnLabel, 0, 9);
       
        //Add SSN text field
        ssnTextField = new PasswordField();
        ssnTextField.setPrefWidth(230);
        ssnTextField.setMaxWidth(230);
        ssnTextField.setPromptText("Social Security Number");
        gridPane.add(ssnTextField, 1, 9);
       
        //Add Security Question Label
        Label securityQLabel = new Label("Security Question");
        gridPane.add(securityQLabel, 0, 10);
       
        //Add Security Question ChoiceBox
        securityQChoiceBox = new ChoiceBox();
        securityQChoiceBox.getItems().addAll("What is the name of your first teacher",
        "What is the name of your first pet",
        "What is the name of your School");
        securityQChoiceBox.setValue("What is the name of your first teacher");
        securityQChoiceBox.setPrefWidth(230);
        securityQChoiceBox.setMaxWidth(230);
        gridPane.add(securityQChoiceBox, 1, 10);
       
     
        //Add Security Answer Label
        Label securityALabel = new Label("Security Answer");
        gridPane.add(securityALabel, 0, 11);
       
        //Add Security Answer Field
        securityATextField = new TextField();
        securityATextField .setPrefWidth(230);
        securityATextField .setMaxWidth(230);
        securityATextField.setPromptText("Security Answer");
       
        gridPane.add(securityATextField, 1, 11);
       
       
       
        Button backToMain = new Button();
        //Adding back to Main Menu button
        backToMain = new Button("Back");
      backToMain.setOnAction(e -> {
      Login mm = new Login();
      try {
      mm.start(window);
      } catch (Exception e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
      }
      });
       
        Button registerButton = new Button("Sign Up");
       
        /**
* This will create a user object and gather all the inputed user Data to the database;
*/

// Created this object to register user and then registered them
RegisterNewUser r = new RegisterNewUser();
RegistrationException re = new RegistrationException();

        registerButton.setOnAction(e -> {
       
        user = new User(usernameText.getText(), passwordText.getText(), firstNameText.getText(), lastNameText.getText(),
    addressText.getText(), zipText.getText(), stateBox.getText(), emailText.getText(), ssnTextField.getText(),
    securityQChoiceBox.getSelectionModel().getSelectedItem(), securityATextField.getText());

        if(firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() ||
        addressText.getText().isEmpty() ||  zipText.getText().isEmpty() ||
        emailText.getText().isEmpty() ||  usernameText.getText().isEmpty() ||
        passwordText.getText().isEmpty() ||  ssnTextField.getText().isEmpty() ||
        securityATextField.getText().isEmpty()) {
        AlertBox.display("Finish the registration", "You are missing some inputs.");
       
        }else if(re.isZip(user) == false){
        AlertBox.display("Invalid ZIP", "Invalid Zip Code");
        System.out.print(user.getZip());
        }else if(re.emailValid(user) == false){
        AlertBox.display("Invalid Email Input", "Make the email contains @ and .com " + user.getEmail());
        }else if(re.lengthUsernameCheck(user) == false){
        AlertBox.display("Incorrect Username Input","Make the length between 4 and 15 characters long");

        }else if(re.hasSpace(user) == true) {
        AlertBox.display("Invalid Password", "Password shouldn't contain a space");
        }else if(re.hasSpecialCharacter(user) == false) {
        AlertBox.display("Invalid Password", "Password should contain a special character");
        }else if(re.hasUpperCase(user) == false) {
        AlertBox.display("Invalid Password", "Password should contain a an uppercase letter");
        }else if(re.hasCorrectSnn(user) == false) {
        AlertBox.display("Invalid SSN", "Format of SSN XXX-XX-XXXX");
        } else
			try {
				if(Mysql.checkMemberID(usernameText.getText().toString())==true) {
					 AlertBox.display("Username already taken! ", "Please enter another Username!");
				}else{
					try { 
						r.addUserToDataBase(user);
						}
					 catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					// This code take user to login.
					Login lgn = new Login();
					try {
						lgn.start(primaryStage);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
});
       
        HBox hbox = new HBox();
        hbox.getChildren().addAll(registerButton, backToMain);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(10, 10, 10, 10));
        hbox.setSpacing(50);
       
       
        gridPane.add(hbox, 1, 12);
       
        // Create a scene with registration form
  Scene scene = new Scene(gridPane, 500, 600);
  scene.getStylesheets().add("Layout.css");
        // Set the scene in primary stage
        window.setScene(scene);
       
        window.show();

}

public GridPane registrationGrid() {
GridPane gridPane = new GridPane();

gridPane.setAlignment(Pos.CENTER);

gridPane.setVgap(10);

gridPane.setHgap(10);

gridPane.setPadding(new Insets(50, 50, 50, 50));

// Add Column Constraints


        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
       
       
   
}



}