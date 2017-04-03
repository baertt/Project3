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
	Button pick;

	CourseInfo info;



	@FXML
	public void initialize(){
		description.setEditable(false);

	}

	@FXML
	public void importVariables(CourseListController courseList) {
		this.courseList = courseList;
		this.info = courseList.info;
		fillDescription();
	}

	private void fillDescription() {
		description.setText(info.toString());
	}



}
