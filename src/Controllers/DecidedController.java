package Controllers;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
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

	public ArrayList<String> periods;

	private ListView<String> currentCourses;

	private String semester;

	@FXML
	public void initialize(){
		description.setEditable(false);
	}


	public void importVariables(CourseListController courseList, MainController main) {
		this.currentCourses = main.currentCourses;
		//this.springPeriods = courseList.springPeriods;
		this.periods = main.periods;
		this.main = courseList.main;
		this.semester = main.selectedSemester;
		this.courseList = courseList;
		this.info = courseList.info;
		fillDescription();
	}

	@FXML
	private void fillDescription() {
		description.setText(info.toString());
	}


	public boolean isConflict(){
		//System.out.println(periods.toString());
		if(periods.contains(info.getPeriod())){
			int index = periods.indexOf(info.getPeriod());
			ButtonType class1 = new ButtonType(info.getTitle(), ButtonData.OK_DONE);
			String classForClass2 = currentCourses.getItems().get(index);
			int num = classForClass2.indexOf("\n");
			int num2 = classForClass2.indexOf("\n", num + 1);
			ButtonType class2 = new ButtonType(classForClass2.substring(num + 1,num2),  ButtonData.OK_DONE);
			Alert r = new Alert(AlertType.NONE, "There is a scheduling conflict.\nPlease select which course you would like to keep or cancel." ,
					class1, class2, ButtonType.CANCEL);
			r.setTitle("TIME CONFLICT");
			r.showAndWait();

			if(r.getResult() == class1){
				currentCourses.getItems().remove(index);
				currentCourses.getItems().add(String.format("%s %s %s", info.getCode(), info.getTitle(),
						  info.getProf()));
			}

			r.close();
			return true;
		}

		periods.add(info.getPeriod());
		return false;

	}

	@FXML
	public void pickClass(){
		String viewedInfo = String.format("%s %s %s", info.getCode(), info.getTitle(),
										  info.getProf());
		if(isConflict() == false){
			main.currentCourses.getItems().add(viewedInfo);
		}

		pick.getScene().getWindow().hide();
	}


}
