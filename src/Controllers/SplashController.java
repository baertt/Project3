package Controllers;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SplashController {

	@FXML
	ProgressBar progress;

	@FXML
	Button deleteLater;

	@FXML
	public void initialize(){


	}


	@FXML
	public void openMain(){
		try {FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Main_Ver2.fxml"));
			Pane root = (Pane) loader.load();

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

			Stage current = (Stage) progress.getScene().getWindow();
			current.close();

			String url = "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";

			Image anotherIcon = new Image(url);
	        secondStage.getIcons().add(anotherIcon);
	        secondStage.setTitle("  Hendrix College: 2017 - 2018 Course Selector");

			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
			Alert r = new Alert(AlertType.NONE, "Cannot open main." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
		}



	}
}
