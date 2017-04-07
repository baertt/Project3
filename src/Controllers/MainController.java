package Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import dataBaseConstructor.ConstructDatabase;
import dataBaseConstructor.ConstructJson;
import dataBaseConstructor.NewDataConstructer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.parser.ParseException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
//import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
	NewDataConstructer newdata = new NewDataConstructer();
	ConstructDatabase db = new ConstructDatabase();
	List<String> semesters = new ArrayList<>(Arrays.asList("Fall", "Spring", "Summer"));

	@FXML
	ChoiceBox<String> semesterSelector;


	@FXML
	MenuItem create;

	@FXML
	MenuItem open;

	@FXML
	ListView<String> currentCourses;

	String selectedSemester;

	@FXML
	public void initialize() {
		List<String> semesters = new ArrayList<>(Arrays.asList("Spring", "Summer", "Fall"));
        for(String semester: semesters){
			semesterSelector.getItems().add(semester);
		}
		semesterSelector.getSelectionModel().select(2);
	}

	@FXML
	public void openCourseList(){
		try {
			selectedSemester = semesterSelector.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("CourseList.fxml"));
			Pane root = (Pane) loader.load();

			CourseListController courseList = (CourseListController)loader.getController();
			courseList.importVariables(this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

			String url = "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";

			Image anotherIcon = new Image(url);
	        secondStage.getIcons().add(anotherIcon);
	        secondStage.setTitle("  Hendrix College: Course Selector");

			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot open Course List." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
		}
	}

	@FXML
	void open() {

	}

	@FXML
	void create() throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, ParseException {
		FileChecker fileChecker = new FileChecker();
		if (fileChecker.fileChecker(Integer.toString(newdata.year()))) {
			db.ConstructUserInfo();
			db.addCourseInfo();
		} else {
			db.addUserInfo("OK");
			db.addCourseInfo();
		}
	}


}
