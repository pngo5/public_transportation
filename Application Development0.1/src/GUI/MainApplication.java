package GUI;


import java.sql.Connection;


import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import Database.Mysql;
import Objects.BusSchedule;



import Objects.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This is the user main application It will include the ability for the user to
 * book reservations, delete their reservations, and view reservations.
 *
 * .. . . private Date startTime; private String startLocation; private String
 * endLocation; private double ticketPrice;
 * 
 */
public class MainApplication extends Application {
	Stage window;
	ChoiceBox<String> startCity;
	ChoiceBox<String> endCity;
	Button backToMainMenu, logOut, removeBooking, addBooking, adminUpdateBusSystem, adminRemoveBusSystem;
	TableColumn<BusSchedule, String> busNumber, startTime, endTime, startLocation, endLocation, passengerCount;
	TableView<BusSchedule> table;
	TableView<BusSchedule> userBookingTable;
	TableColumn<BusSchedule, String> tUserName,tBusNumber, tStartTime, tEndTime, tStartLocation, tEndLocation, tPassengerCount;
	Label fromCityLabel, toCityLabel, title, view, myBookedFlights;
	TextField fromCityText, toCityText;
    TextField busIDInput;
    static User user;
    VBox inputs;
   final ObservableList<BusSchedule> BusSystem1 = FXCollections.observableArrayList();	
   final ObservableList<BusSchedule> BusSystem2 = FXCollections.observableArrayList();	
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Main Application");
		window.setResizable(false);
		//window.getIcons().add(new ("icon.png"));

		//Create BorderPane
		BorderPane border = new BorderPane();
		
		//Creating HBox
		HBox topMenu = addHBox();
		
		//Creating the Tables
		GridPane grid = createGridPane();
		
		//TODO Make an if statement to show user inputs or admin inputs.
		
		
		Login l = new Login();
		//Creating the main menu button, logout button, and add table button
		if(l.getL() == 1) {
			inputs = addAdminInputs();
		}else {
			inputs = addInputs();
		}
		
		System.out.print(l.getL());
		
		border.setTop(topMenu);
		border.setCenter(grid);
		border.setRight(inputs);
		
		Scene scene = new Scene(border, 1300, 500);
		//scene.getStylesheets().add("Layout.css");
		scene.getStylesheets().add("Layout.css");
		window.getIcons().add(new Image("icon.png"));
		window.setScene(scene);
		window.show();

	}
	
	public void refreshTable()  {
		BusSystem2.clear();
		try {
		Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");			
		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM schedule");
		while(rs.next()) {
       BusSystem2.add(new BusSchedule(rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(6)));
		}
		table.setItems(BusSystem2);		
		con.close();
		rs.close();
	}catch(Exception e2) {
		System.err.println(e2);
	
 }
}
	
	public void refreshTableUser()  {
		BusSystem2.clear();
		
		try {
		Connection con=DriverManager.getConnection("jdbc:mysql://34.74.172.98/bus_database","root","cis3270");			
		ResultSet rs = con.createStatement().executeQuery("select booking.user_id, schedule.* FROM booking INNER JOIN schedule ON (schedule.bus_id = booking.bus_id) AND (booking.user_id = '"+Login.username+"');");
		
		
		while(rs.next()) {

			BusSystem2.add(new BusSchedule(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6)));
		

		}
		userBookingTable.setItems(BusSystem2);		
		con.close();
		rs.close();
	}catch(Exception e2) {
		System.err.println(e2);
	
 }
}
	
	
	public GridPane createGridPane() {
		
		
		GridPane grid = new GridPane();
		
		VBox busScheduleTable = createTable();
		
		VBox userBookings = createUserBookingTable();
		
		
		grid.add(busScheduleTable, 1, 1);
		grid.add(userBookings, 2, 1);
		
		grid.setHgap(50);
		grid.setVgap(30);
		
		return grid;
		
	}

	public HBox addHBox() {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");

	    ChoiceBox<String> from = new ChoiceBox<>();
	    from.getItems().addAll("ATL","DAL","NY");
	    from.setPrefSize(100, 20);

	    ChoiceBox<String> to = new ChoiceBox<>();
	    to.getItems().addAll("ATL","DAL","NY");
	    to.setPrefSize(100, 20);
	    
	    from.setPrefSize(100, 20);
	    to.setPrefSize(100, 20);
	    
	    Button searchButton = new Button("Search");
	 
	    
	    hbox.getChildren().addAll(from, to, searchButton);

	    return hbox;
	}
	
	/**
	 * Menu for Main Menu, Logout, and etc for User.
	 * @return
	 */
	public VBox addInputs() {
		VBox v = new VBox();
		v.setPadding(new Insets(10));
	    v.setSpacing(8);
	    v.setAlignment(Pos.CENTER);
	    
		 Text title = new Text("Controllers");
		 title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		 
		
		 logOut = new Button("Logout");
	        logOut.setMinWidth(145);
	        logOut.setOnAction(e -> {
	              Login mm = new Login();
	              try {
	                  mm.start(window);
	              } catch (Exception e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	              }
	          });

	        backToMainMenu = new Button("Main Menu");
	        backToMainMenu.setMinWidth(145);
	        backToMainMenu.setOnAction(e -> {
	              Login mm = new Login();
	              try {
	                  mm.start(window);
	              } catch (Exception e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	              }
	          });

	        v.getChildren().addAll(title, logOut,backToMainMenu);


	        return v;
		
	}
	

	/**
	 * Creates the view tables
	 * 
	 * @return
	 */
	public ObservableList<BusSchedule> getBusSystem(){
		
		ObservableList<BusSchedule> products = Mysql.getBusSchedule();
		
		
		return products;

	}
	public VBox createTable() {

		/**
		 * Creating view column for User and matching it to their respective instances.
		 * 
		 * private int busID; 
		 * private String departCity;
		 * private String arrivalCity;
		 * private String departTime;
		 * private String arrivalTime;
		 * private int passengerCount;
		 */
		busNumber = new TableColumn<>("Bus ID");
		busNumber.setMinWidth(45);
		busNumber.setCellValueFactory(new PropertyValueFactory<>("busID"));

		startTime = new TableColumn<>("Start Time");
		startTime.setMinWidth(45);
		startTime.setCellValueFactory(new PropertyValueFactory<>("departTime"));

		endTime = new TableColumn<>("End Time");
		endTime.setMinWidth(45);
		endTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));

		startLocation = new TableColumn<>("Start Location");
		startLocation.setMinWidth(45);
		startLocation.setCellValueFactory(new PropertyValueFactory<>("departCity"));

		endLocation = new TableColumn<>("End Location");
		endLocation.setMinWidth(45);
		endLocation.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));


		passengerCount = new TableColumn<>("Seats");
		passengerCount.setMinWidth(80);
		passengerCount.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
		
		

		//Creating table
		table = new TableView<>();

		//Populating table system
		table.setItems(getBusSystem());
		table.getColumns().addAll(busNumber, startTime, endTime, startLocation, endLocation, passengerCount);
		
		//Search the Bus ID Input
        busIDInput = new TextField();
        busIDInput.setPromptText("Bus ID");
        busIDInput.setMinWidth(100);
       
        //Button
        Button addButton = new Button("Reserve");
        addButton.setOnAction(e ->{
        	try {
				addUserBooking();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
       
        
        //Create
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");
        hBox.getChildren().addAll(busIDInput, addButton);

		//Adding the table to a VbOX
		VBox vBox = new VBox();
		vBox.getChildren().addAll(table,hBox);

		return vBox;

	}
	



	public VBox createUserBookingTable() {

		/**
		 * 
		 * Creating view column for User and matching it to their respective instances.
		 * tUserName
		 * public static String userName;
			public static String busID;
			private String departCity;
			private String arrivalCity;
			private String departTime;
			private String arrivalTime;
		 */
		
		tBusNumber = new TableColumn<>("Bus ID");
		tBusNumber.setMinWidth(50);
		tBusNumber.setCellValueFactory(new PropertyValueFactory<>("busID"));

		tStartTime = new TableColumn<>("Start Time");
		tStartTime.setMinWidth(50);
		tStartTime.setCellValueFactory(new PropertyValueFactory<>("departCity"));

		tEndTime = new TableColumn<>("End Time");
		tEndTime.setMinWidth(50);
		tEndTime.setCellValueFactory(new PropertyValueFactory<>("arrivalCity"));

		tStartLocation = new TableColumn<>("Start Time");
		tStartLocation.setMinWidth(50);
		tStartLocation.setCellValueFactory(new PropertyValueFactory<>("departTime"));

		tEndLocation = new TableColumn<>("End Time");
		tEndLocation.setMinWidth(50);
		tEndLocation.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
		
		tPassengerCount = new TableColumn<>("End Time");
		tPassengerCount.setMinWidth(50);
		tPassengerCount.setCellValueFactory(new PropertyValueFactory<>("passengerCount"));
		
		// Creating table

		userBookingTable = new TableView<>();

		// Populating table system

		userBookingTable.setItems(getUserBookings());
		userBookingTable.getColumns().addAll(tBusNumber,tStartTime,tEndTime,tStartLocation,tEndLocation,tPassengerCount);
		
		  removeBooking = new Button("Remove");
    
      
        removeBooking.setOnAction(e -> {
            refreshTable();
            try {
            	
            	deleteUserButtonClicked();
 
            	
            } catch (Exception e1) {
                
                e1.printStackTrace();
            }
        });
       
        
        //Create
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.setStyle("-fx-background-color: #336699;");
        hBox.getChildren().addAll(removeBooking);

		// Adding the table to a VbOX
		VBox vBox = new VBox();
		vBox.getChildren().addAll(userBookingTable, hBox);

		return vBox;

	}
	/**
	 * 
	 * 
	 */
	 public ObservableList<BusSchedule> getUserBookings(){
	    	ObservableList<BusSchedule> products = Mysql.getUserBookingSchedule();	    	
	    	return products;
	    	
	    }
	
	
	/**
	 * This will be the admin functions
	 * 
	 */
	public VBox addAdminInputs(){
		VBox v = new VBox();
	    v.setSpacing(8);
	    v.setAlignment(Pos.CENTER);
	    
		 Text title = new Text("Controllers");
		 title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		 
		 adminRemoveBusSystem = new Button("Remove Bus Schedule");
		 adminRemoveBusSystem.setMinWidth(145);
		 adminRemoveBusSystem.setOnAction(e ->{
			 deleteButtonClicked();
		 });
		
		 adminUpdateBusSystem = new Button("Update Bus Schedule");
		 adminUpdateBusSystem.setMinWidth(145);
		 adminUpdateBusSystem.setOnAction(e ->{
			 AddingBus ab = new AddingBus();
			 try {
				ab.start(window);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 
		 });
		 
		 logOut = new Button("Logout");
	        logOut.setMinWidth(145);
	        logOut.setOnAction(e -> {
	              Login mm = new Login();
	              try {
	                  mm.start(window);
	              } catch (Exception e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	              }
	          });

	        backToMainMenu = new Button("Main Menu");
	        backToMainMenu.setMinWidth(145);
	        backToMainMenu.setOnAction(e -> {
	              Login mm = new Login();
	              try {
	                  mm.start(window);
	              } catch (Exception e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	              }
	          });

	        v.getChildren().addAll(title, logOut,backToMainMenu,adminUpdateBusSystem, adminRemoveBusSystem);


	        return v;
		
	}
	
	/**
	 * 
	 */
	 public void deleteButtonClicked() {
	    	ObservableList<BusSchedule> productSelected, allProducts;
	    	
	    	allProducts = table.getItems();
	  
	    	productSelected = table.getSelectionModel().getSelectedItems();
	    	BusSchedule asm = (BusSchedule)table.getSelectionModel().getSelectedItem();
	    	String tempItemTag = asm.getBusID();
	    	
	    	
	    	productSelected.forEach(allProducts::remove);
	    	
	    	Mysql.adminDeleteBus(tempItemTag);
	   }
	 
	 public void deleteUserButtonClicked() {
	    	ObservableList<BusSchedule> productSelected, allProducts;
	    	
	    	allProducts = userBookingTable.getItems();
	  
	    	productSelected = userBookingTable.getSelectionModel().getSelectedItems();
	    	BusSchedule asm = (BusSchedule)userBookingTable.getSelectionModel().getSelectedItem();
	    	String tempItemTag = asm.getBusID();
	    	String u = Login.username;
	    	
	    	
	    	System.out.print(tempItemTag);
	    	Mysql.userDeleteBus(tempItemTag,u);
	    	productSelected.forEach(allProducts::remove);
	    	
	    	
	   }
	 
	 public void addUserBooking() throws SQLIntegrityConstraintViolationException, ClassNotFoundException, SQLException {
		 	ObservableList<BusSchedule> productSelected, allProducts;
	    	
	    	allProducts = table.getItems();
	  
	    	productSelected = table.getSelectionModel().getSelectedItems();
	    	BusSchedule asm = (BusSchedule)table.getSelectionModel().getSelectedItem();
	    	String tempItemTag = asm.getBusID();
	    	String u = Login.username;
	    	
	    	Mysql.userUpdateBus(tempItemTag, u);
	    	
	    	
		 
	 }
	
	/**
	 * Start the main application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}