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

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.control.MenuBar;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {
	NewDataConstructer newdata = new NewDataConstructer();
	ConstructDatabase db = new ConstructDatabase();
	List<String> semesters = new ArrayList<>(Arrays.asList("Fall", "Spring", "Summer"));

	@FXML
	ChoiceBox<String> semesterSelector;

	CourseInfo info;

	@FXML
	MenuItem create;

	@FXML
	MenuItem open;

	@FXML
	ListView<String> currentCourses;

	String selectedSemester;

	ArrayList<String> periods = new ArrayList<String>();

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
	void doWhat(){
		if(!(currentCourses.getItems().size() == 0)){
			ButtonType remove = new ButtonType("Remove course", ButtonData.OK_DONE);
			ButtonType describe = new ButtonType("View Description", ButtonData.OK_DONE);
			int index = currentCourses.getSelectionModel().getSelectedItem().toString().indexOf("/n") + 1;
			Alert r = new Alert(AlertType.NONE, "What would you like to do?" , remove, describe,  ButtonType.CANCEL);
			r.setTitle(currentCourses.getSelectionModel().getSelectedItem().toString().substring(0,index));
			r.showAndWait();

			if (r.getResult() == remove) {
				remove();

			} else if(r.getResult() == describe){
				describe();
			}
		}
	}

	void describe(){
		ButtonType remove = new ButtonType("Remove course", ButtonData.OK_DONE);
		int index = currentCourses.getSelectionModel().getSelectedItem().toString().indexOf("/n") + 1;
		//info = currentCourses.getSelectionModel().getSelectedItem();
		//String viewedInfo = String.format("%s\n%s\n%s\n%s\t%s", info.getCode(), info.getTitle(),
				 // info.getProf(), info.getTime(), info.getPeriod());
		Alert r = new Alert(AlertType.NONE,currentCourses.getSelectionModel().getSelectedItem().toString(), ButtonType.OK, remove);
		r.setTitle(currentCourses.getSelectionModel().getSelectedItem().toString().substring(0,index));
		r.showAndWait();

		if(r.getResult() == remove){
			remove();
		}
	}

	void remove(){
		Alert r = new Alert(AlertType.NONE, "Remove this course from your schedule?" , ButtonType.YES, ButtonType.NO);
		r.setTitle("Remove course?");
		r.showAndWait();

		if (r.getResult() == ButtonType.YES) {
			String itemToRemove = currentCourses.getSelectionModel().getSelectedItem();
			currentCourses.getItems().remove(itemToRemove);
			r.close();

		} else{
			r.close();
		}
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
