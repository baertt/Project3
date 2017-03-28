package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CourseListController {

	@FXML
	public void initialize(){

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

}
