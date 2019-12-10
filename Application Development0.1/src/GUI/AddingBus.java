package GUI;

import Database.Mysql;
import Exceptions.RegistrationException;
import BusinessLogic.RegisterNewUser;
import BusinessLogic.Schedules;
import Objects.BusSchedule;

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
import javafx.scene.layout.AnchorPane;
import java.util.ResourceBundle;

public class AddingBus extends Application {
	
	Button registerButton;
	Stage window;
	TextField busIDNumber, deprtCityText,arrivalCityText ,departTimeText, arrivalTimeText, PassengerTimeText;
	BusSchedule BusSchedule;
	
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * 	private String newUserName;
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
        Label headerLabel = new Label("Adding Buses");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add First Name Label
        Label busID = new Label("Bus ID : ");
        gridPane.add(busID, 0,1);

        // Add Name First Name Text Field
        TextField busIDText = new TextField();
        busIDText.setPrefWidth(230);
        busIDText.setMaxWidth(230);
        busIDText.setPromptText("Bus ID");
        gridPane.add(busIDText, 1,1);
        
        Label departCity = new Label("Depart City: ");
        gridPane.add(departCity, 0,2);
        
        // Add Last Name Text Field
        TextField departCityText = new TextField();
        departCityText.setPrefWidth(230);
        departCityText.setMaxWidth(230);
        departCityText.setPromptText("Depart City");

        gridPane.add(departCityText, 1, 2);
    
     // Add Address Label
        Label arrivalCity = new Label("Arrival City : ");
        gridPane.add(arrivalCity, 0,3);
        
        // Add Address Text Field
        TextField arrivalCityText = new TextField();
        arrivalCityText.setPrefWidth(230);
        arrivalCityText.setMaxWidth(230);
        arrivalCityText.setPromptText("Arrival City");
        gridPane.add(arrivalCityText, 1,3);
        
        Label departTime = new Label("Depart Time : ");
        gridPane.add(departTime, 0,4);
        
        // Add zip Text Field
        TextField departTimeText = new TextField();
        departTimeText.setPrefWidth(230);
        departTimeText.setMaxWidth(230);;
        departTimeText.setPromptText("Depart Time");
        gridPane.add(departTimeText, 1,4);
        
        
        Label arrivalTime = new Label("Arrival Time : ");
        gridPane.add(arrivalTime, 0,5);
        
        // Add zip Text Field
        TextField arrivalTimeTextText = new TextField();
        arrivalTimeTextText.setPrefWidth(230);
        arrivalTimeTextText.setMaxWidth(230);;
        arrivalTimeTextText.setPromptText("Arrival Time");
        gridPane.add(arrivalTimeTextText, 1,5);
        
        
        // Add state Label
        Label passengarCount = new Label("Passengar Count : ");
        gridPane.add(passengarCount, 0,6);     
        //Add state drop down menu
        TextField passengarCountText = new TextField();
        passengarCountText.setPromptText("Passengar CountText");
        passengarCountText.setPrefWidth(230);
        passengarCountText.setMaxWidth(230);;
        gridPane.add(passengarCountText, 1, 6);
        
        Button backToMain = new Button();
        	//Adding back to Main Menu button
        	backToMain = new Button("Back");
      		gridPane.add(backToMain, 2, 12);
      		backToMain.setOnAction(e -> {
      			Login mm = new Login();
      			try {
      				mm.start(window);
      			} catch (Exception e1) {
      				// TODO Auto-generated catch block
      				e1.printStackTrace();
      			}
      		});
        
   		
   		// Created this object to register user and then registered them
   		Schedules r = new Schedules();
   		RegistrationException re = new RegistrationException();
   		Button Adding = new Button("ADD");
   		gridPane.add( Adding, 1, 12);
           Adding.setOnAction(e -> {
           	String BusIDINT=busIDText.getText();
           	int passengarCountINT= Integer.parseInt(passengarCountText.getText());
           	BusSchedule= new BusSchedule(BusIDINT,
           			departCityText.getText(),arrivalCityText.getText(), 
           			arrivalTimeTextText.getText(), departTimeText.getText()
           			,passengarCountINT);

           	if(busIDText.getText().isEmpty() || departCityText.getText().isEmpty() ||
           			arrivalCityText.getText().isEmpty() ||  departTimeText.getText().isEmpty() ||
           			passengarCountText.getText().isEmpty()) {
           		AlertBox.display("ERROR!", "You are missing some inputs.");
           	}
           	else
           	{
           		MainApplication ma = new MainApplication();
           		Mysql ms = new Mysql();
           		try {
   					ms.adminUpdateBus(BusSchedule);
   					
   				} catch (Exception e2) {
   					// TODO Auto-generated catch block
   					e2.printStackTrace();
   				}
           		
           		
           		// This code take user to login.
           		
           	}
           });
           
           HBox hbox = new HBox();
        
        
        // Create a scene with registration form 
 		Scene scene = new Scene(gridPane, 500, 600);
 		scene.getStylesheets().add("Layout.css");
        // Set the scene in primary stage	
        window.setScene(scene);
        window.show();

	}
	

	
}
