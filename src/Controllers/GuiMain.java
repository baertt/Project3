// Splash screen provided by: https://gist.github.com/jewelsea/2305098

package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;

import dataBaseConstructor.ConstructDatabase;
import dataBaseConstructor.ConstructJson;
import dataBaseConstructor.NewDataConstructer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.*;
import javafx.concurrent.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.util.Duration;


public class GuiMain extends Application {
	NewDataConstructer newdata = new NewDataConstructer();
    public static final String APPLICATION_ICON =
            "https://www.hendrix.edu/uploadedImages/Events_and_News/SHIELD%20web%20page.jpg";
    public static final String SPLASH_IMAGE =
            "http://images.collegexpress.com/wg_school/2400118_logo.jpg";

    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage main;
    private static final int SPLASH_WIDTH = 250;
    private static final int SPLASH_HEIGHT = 227;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                SPLASH_IMAGE
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Hendrix College . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                "-fx-background-color: cornsilk; " +
                "-fx-border-width:5; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "chocolate, " +
                        "derive(chocolate, 50%)" +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

    @Override
    public void start(final Stage initStage) throws Exception {
        Task<ObservableList<String>> loadProgram = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException, ClassNotFoundException, SQLException, IOException, ParseException {
                ObservableList<String> seenMessages =
                        FXCollections.<String>observableArrayList();
                ObservableList<String> messages =
                        FXCollections.observableArrayList(
                                "Setting things up. . .",
                                "Planning for next year. . .",
                                "Building the course schedule. . .",
                                "Preparing the scheduler. . .",
                                "Ready to plan your year?"
                        );

                for(int i = 0; i < messages.size(); i++){
                	TimeUnit.SECONDS.sleep(2);
                	updateMessage(messages.get(i));
                }

                constructDB();

                return seenMessages;
            }
        };

        showSplash(
                initStage,
                loadProgram,
                () -> {
					try {
						showMainStage(main);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
        );
        new Thread(loadProgram).start();
    }

    private void showMainStage(Stage main) throws IOException {
        main = new Stage();
		Pane root = (Pane) FXMLLoader.load(getClass().getResource("Main_Ver2.fxml"));

        main.setTitle("  Hendrix College: Course Selector");

		Scene scene = new Scene(root, 504, 455);
		main.setScene(scene);
		main.show();


        main.setTitle("  Hendrix College: Course Selector");
        main.getIcons().add(new Image(APPLICATION_ICON));

        main.setScene(scene);
        main.show();
    }

    private void constructDB() throws ClassNotFoundException, SQLException, IOException, ParseException{
    	ConstructJson file = new ConstructJson();
		file.loadJson("http://hoike.hendrix.edu/api/CourseModel?$filter=YearCode%20eq%20" + Integer.toString(newdata.year()) +"%20&$orderby=CourseId%20asc", Integer.toString(newdata.year()));
		ConstructDatabase db = new ConstructDatabase();
		db.Construct(Integer.toString(newdata.year()));
//		db.ConstructUserInfo();
//		db.addCourseInfo();
    }

    private void showSplash(
            final Stage initStage,
            Task<?> task,
            InitCompletionHandler initCompletionHandler
    ) {
        progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();

                initCompletionHandler.complete();
            } // todo add code to gracefully handle other task states.
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();
    }

    public interface InitCompletionHandler {
        void complete();
    }
}