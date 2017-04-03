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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainController {

	@FXML
	ChoiceBox<String> semesterSelector;

	List<String> semesters = new ArrayList<>(Arrays.asList("Fall", "Spring", "Summer"));

	@FXML
	public void initialize() throws FileNotFoundException, ClassNotFoundException{
		ConstructJson file = new ConstructJson();
		file.loadJson("http://hoike.hendrix.edu/api/CourseModel?$filter=YearCode%20eq%202016%20and%20TermCode%20eq%20%271S%27&$orderby=CourseId%20asc", "sample");
		ConstructDatabase db = new ConstructDatabase();
		db.Construct("sample");
		for(String semester: semesters){
			semesterSelector.getItems().add(semester);
		}
		semesterSelector.getSelectionModel().select(0);
	}



	@FXML
	public void openCourseList(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("CourseList.fxml"));
			Pane root = (Pane) loader.load();

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

			String url = "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";

			Image anotherIcon = new Image(url);
	        secondStage.getIcons().add(anotherIcon);
	        secondStage.setTitle("  Hendrix College: 2017 - 2018 Course Selector");

			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
			Alert r = new Alert(AlertType.NONE, "Cannot open Course List." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
		}
	}


}
