package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileNotFoundException;
import java.sql.*;

import dataBaseConstructor.ConstructDatabase;
import dataBaseConstructor.ConstructJson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CourseListController {

	int columns = 6;
	@FXML
	TableView<CourseInfo> courses;
	@FXML
	TableColumn<TableView<CourseInfo>, String> code;
	@FXML
	TableColumn<TableView<CourseInfo>, String> title;
	@FXML
	TableColumn<TableView<CourseInfo>, String> prof;
	@FXML
	TableColumn<TableView<CourseInfo>, String> period;
	@FXML
	TableColumn<TableView<CourseInfo>, String> time;

	@FXML
	MainController main;

	String semester;
	CourseInfo info;

	@FXML
	public void initialize() throws ClassNotFoundException, SQLException, FileNotFoundException{

		code.setCellValueFactory(new PropertyValueFactory<>("code"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		prof.setCellValueFactory(new PropertyValueFactory<>("prof"));
		period.setCellValueFactory(new PropertyValueFactory<>("period"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));

		courses.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			//System.out.println("In Listener");
		    if (newSelection != null) {
		        info = courses.getSelectionModel().getSelectedItem();
		        //System.out.println(info.toString());
		     }
		});
	}

	@FXML
	public void chooseSemester() throws ClassNotFoundException, FileNotFoundException, SQLException{
		//semester = main.selectedSemester;

		if(semester.equals("Fall")) semester = "1S";
		else if(semester.equals("Spring")) semester = "2S";
		else{semester = "3S";}

		populate(semester);
	}
	
	@FXML
	public void openSearch(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Search.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

			SearchController search = (SearchController)loader.getController();
			search.importVariables(this);

			String url = "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";

			Image anotherIcon = new Image(url);
	        secondStage.getIcons().add(anotherIcon);
	        secondStage.setTitle("  Search for a course");

			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
			Alert r = new Alert(AlertType.NONE, "Cannot open search menu." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
		}
	}


	public void populate(String semester) throws ClassNotFoundException, SQLException, FileNotFoundException{
		Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:sample.db");
        Statement stat = con.createStatement();
        if (stat.execute("select * from course")) {
            ResultSet results = stat.getResultSet();
            while (results.next()) {
            	if(semester.equals(results.getString(2))){
            		courses.getItems().add(new CourseInfo(results.getString(3), results.getString(4),
            				results.getString(7), results.getString(5), results.getString(6), results.getString(8)));
            	}
            }
        }
	}

	@FXML
	public void viewCourse(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Decided.fxml"));
			Pane root = (Pane) loader.load();

			DecidedController chosen = (DecidedController)loader.getController();
			chosen.importVariables(this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

			String url = "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";

			Image anotherIcon = new Image(url);
	        secondStage.getIcons().add(anotherIcon);
	        secondStage.setTitle("  ");

			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
			Alert r = new Alert(AlertType.NONE, "Cannot view course." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
		}
	}

	@FXML
	public void importVariables(MainController main) throws ClassNotFoundException, FileNotFoundException, SQLException {
		this.main = main;
		this.semester = main.selectedSemester;
		chooseSemester();
	}

}

