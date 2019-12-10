package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class ForgetPassword extends Application {
 	Stage window;
 	
     

 public static void main(String[] args) {
	 launch(args);
 }


@Override
public void start(Stage primaryStage) throws Exception {
	    window = primaryStage;
	    window.setTitle("Forgot Password Validation Form");
	    window.getIcons().add(new Image("icon.png"));
	    
	    
	    GridPane grid = new GridPane();
	    grid.setPadding(new Insets(10,10,10,10));
	    grid.setAlignment(Pos.CENTER);
	    grid.setVgap(10);
	    grid.setHgap(10);
	    /***
	     * xasdsadas
	     */
	    
	    Label headerLabel = new Label("Forgot Password Validation Form");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        grid.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(9, 1,9,1));
	    
	    Label username = new Label("Username");
	    grid.add(username,0,1);
	    
	    TextField testUsername = new TextField();
	    testUsername.setPromptText("Username");
	    testUsername.setPrefWidth(235);
	    testUsername.setMaxWidth(235);
	    grid.add(testUsername,1,1);
	    
	       //Add Security Question Label
        Label securityQLabel = new Label("Security Question");
        grid.add(securityQLabel, 0, 3);
        
        //Add Security Question ChoiceBox
        ChoiceBox<String> securityQChoiceBox = new ChoiceBox();
        securityQChoiceBox.getItems().addAll("What is the name of your first teacher",
        		"What is the name of your first pet",
        		"What is the name of your School"); 
        securityQChoiceBox.setValue("What is the name of your first teacher");
        grid.add(securityQChoiceBox, 1, 3);
        
      
        //Add Security Answer Label
        Label securityALabel = new Label("Security Answer");
        grid.add(securityALabel, 0, 5);
        
        PasswordField securityQuestion = new PasswordField();
        securityQuestion.setPrefWidth(235);
        securityQuestion.setMaxWidth(235);
        securityQuestion.setPromptText("Security Answer");
        grid.add( securityQuestion, 1,5); 
        
        
        Button passwordButton = new Button("Show Password");
        passwordButton.setAlignment(Pos.CENTER);
        passwordButton.setMinWidth(110);
        grid.add(passwordButton,1,6); 
        passwordButton.setOnAction(e -> {
			Login m1 = new Login();
			
			//TODO use this code to input into the table list
			
			
			try {
				Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");
				PreparedStatement stmta = con.prepareStatement("SELECT * FROM users WHERE BINARY user_id=? AND question=? AND answer=?");
				stmta.setString(1, testUsername.getText());
				stmta.setString(2,securityQChoiceBox.getValue());
				stmta.setString(3, securityQuestion.getText());
				ResultSet rsa = stmta.executeQuery();
				if(rsa.next()) { 
					String s = rsa.getString("password");
                    AlertBox.display("Your password is: ", s);
					m1.start(window);
				}
				else 
					AlertBox.display("ERROR!", "Incorrect Information!");   
				con.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        
        Button backToMain = new Button();
        //Adding back to Main Menu button
        backToMain.setAlignment(Pos.CENTER);
        backToMain = new Button("Back");
        backToMain.setMinWidth(110);
          grid.add(backToMain,1,7 );
          backToMain.setOnAction(e -> {
              Login mm = new Login();
              try {
                  mm.start(window);
              } catch (Exception e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
              }
          });
        
  
        Scene scene = new Scene(grid, 500,500);
    	scene.getStylesheets().add("Layout.css");
        window.setScene(scene);
	    
	    window.show();
	
}
}