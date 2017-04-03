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
	public void initialize() throws ClassNotFoundException, SQLException, FileNotFoundException{
		code.setCellValueFactory(new PropertyValueFactory<>("code"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		prof.setCellValueFactory(new PropertyValueFactory<>("prof"));
		period.setCellValueFactory(new PropertyValueFactory<>("period"));
		time.setCellValueFactory(new PropertyValueFactory<>("time"));
		populate();
	}

	@FXML
	public void openSearch(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Search.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

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


	public void populate() throws ClassNotFoundException, SQLException, FileNotFoundException{
		Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:sample.db");
        Statement stat = con.createStatement();
        if (stat.execute("select * from course")) {
            ResultSet results = stat.getResultSet();
            while (results.next()) {
                for (int c = 1; c <= columns; ++c) {
                    System.out.println(results.getString(c) + "\t");
                }
               // System.out.println();
            }
        }
		//courses.getItems().add(arg0)
	}

}
