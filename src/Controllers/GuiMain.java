package Controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiMain extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiMain.class.getResource("Main_Ver2.fxml"));
		Pane root = (Pane) loader.load();

		String url = "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";

		Image anotherIcon = new Image(url);
        primaryStage.getIcons().add(anotherIcon);
        primaryStage.setTitle("  Hendrix College: 2017 - 2018 Course Selector");

		Scene scene = new Scene(root, 504, 455);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}


