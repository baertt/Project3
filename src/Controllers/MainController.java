package Controllers;

import java.io.FileNotFoundException;

import dataBaseConstructor.ConstructDatabase;
import dataBaseConstructor.ConstructJson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {

	List<String> semesters = new ArrayList<>(Arrays.asList("Fall", "Spring", "Summer"));

	@FXML
	ChoiceBox<String> semesterSelector;
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


}
