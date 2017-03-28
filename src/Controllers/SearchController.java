package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchController {

	@FXML
	Button search;

	@FXML
	CheckBox courseTime, department,
		professor, courseNumber, courseTitle;

	@FXML
	TextField professorText, courseNumberText, courseTitleText;

	@FXML
	ChoiceBox courseTimeSelector, departmentSelector;




	@FXML
	public void initialize(){

	}

	@FXML
	public void searchFunction(ActionEvent event){
		Stage stage = (Stage) search.getScene().getWindow();
		stage.close();
	}


}
