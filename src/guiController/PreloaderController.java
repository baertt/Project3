package guiController;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressIndicator;

public class PreloaderController {
	WaitingExample wait = new WaitingExample();
	
	@FXML
	ProgressIndicator loader;
	
	@FXML
	public void initialize() {
		loader.setProgress(-1.0f);
		long start_time = System.nanoTime();
		wait.testConnection("www.google.com");
		long end_time = System.nanoTime();
		double difference = (end_time - start_time)/1e6;
		float dif = (float)difference;
		System.out.println(dif);
//		System.out.println(0.1/Math.round(dif) * 1.0f);
		if (wait.testConnection("www.google.com") == true) {
			for (float i = (float) 0.0; i < 10.0; i+=0.1) {
				System.out.println(i * 1.0f);
				loader.setProgress(i * 1.0f);
			}
		} else {
			loader.setProgress(-1.0f);
		}
	}

	
}
