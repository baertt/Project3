package Controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiMain.class.getResource("Splash.fxml"));
		AnchorPane root = (AnchorPane) loader.load();

        primaryStage.setTitle("  Hendrix College: 2017 - 2018 Course Selector");

		Scene scene = new Scene(root, 597, 463);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args){
		launch(args);
	}
}


