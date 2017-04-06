package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

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


	@FXML
	public void initialize(){
		description.setEditable(false);
	}

	@FXML
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

	@FXML
	public void pickClass(){
		String viewedInfo = String.format("%s\n%s\n%s\n%s\t%s", info.getCode(), info.getTitle(),
										  info.getProf(), info.getTime(), info.getPeriod());
		main.currentCourses.getItems().add(viewedInfo);
		pick.getScene().getWindow().hide();
	}


}
