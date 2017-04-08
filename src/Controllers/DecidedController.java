package Controllers;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class DecidedController {

	@FXML
	TextArea description;
	@FXML
	CourseListController courseList;
	@FXML
	MainController main;
	@FXML
	Button pick;

	CourseInfo info;
	
	ArrayList<String> periods = new ArrayList<String>();


	@FXML
	public void initialize(){
		description.setEditable(false);
	}


	public void importVariables(CourseListController courseList) {
		this.main = courseList.main;
		this.courseList = courseList;
		this.info = courseList.info;
		fillDescription();
	}

	@FXML
	private void fillDescription() {
		description.setText(info.toString());
	}

	public boolean isConflict(){
		System.out.println("isConflict " + periods.toString());
		System.out.println("isConflict " + info.getPeriod().toString());
		if(periods.contains(info.getPeriod())){
			Alert r = new Alert(AlertType.NONE, "There is a scheduling conflict, please select a course or cancel." ,
					ButtonType.OK, ButtonType.CANCEL);
			r.setTitle("TIME CONFLICT");
			r.showAndWait();
			r.close();
			return true;
		} 
		
		periods.add(info.getPeriod());
		System.out.println(periods.toString() + " should have added");
		return false;	
				
	}

	@FXML
	public void pickClass(){
		String viewedInfo = String.format("%s\n%s\n%s\n%s\t%s", info.getCode(), info.getTitle(),
										  info.getProf(), info.getTime(), info.getPeriod());
		if(isConflict() == false){
			main.currentCourses.getItems().add(viewedInfo);
			System.out.println(periods.toString());
		}	
		
		pick.getScene().getWindow().hide();
	}


}
