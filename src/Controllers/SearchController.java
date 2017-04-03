// Citation #1: http://stackoverflow.com/questions/10444722/opening-a-full-web-page-by-using-java

package Controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JEditorPane;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SearchController {

	@FXML
	Button search;

	@FXML
	Button whatIsThis;

	@FXML
	CheckBox courseTime;

	@FXML
	CheckBox subject;

	@FXML
	CheckBox professor;

	@FXML
	CheckBox fastSearch;

	@FXML
	CheckBox courseTitle;

	@FXML
	TextField professorText;

	@FXML
	TextField fastSearchNumberText;

	@FXML
	TextField courseTitleText;

	@FXML
	ChoiceBox<String> courseTimeSelector;

	@FXML
	ChoiceBox<String> subjectSelector;

	 List<String> subjects = new ArrayList<>(Arrays.asList("Select a subject", "AFRI: Africana Studies", "AMST: American Studies",
				"ANTH: Anthropology", "ARTS: Art", "ARTH: Art History", "ASIA: Asian Studies",
				"BCMB: Biochemistry/Molecular Biology", "BIOL: Biology",
				"BUSI: Business", "CHEM: Chemistry", "CHIN: Chinese", "CLAS: Classics", "CSCI: Computer Science",
				"DANC: Dance", "ECON: Economics","EDUC: Education", "ENGC: English-Creative Writing", "ENGF: English-Film Studies",
				"ENGL: English-Literary Studies", "ESOL: Eng-Speakers of Other Lang", "EVST: Environmental Studies",
				"FILM: Film", "FREN: French", "GEND: Gender Studies", "GERM: German", "GREE: Greek", "HESC: Health Sciences", "HEBR: Hebrew",
				"HIST: History", "JAPN: Japanese", "LATI: Latin", "MATH: Mathematics","LBST: Liberal Studies",
				"LITR: Literature In Translation", "MSCI: Military Science", "MUSI: Music", "MUSA: Music Activity",
				"NEUR: Neuroscience", "PHIL: Philosophy", "PACT: Physical Activity", "PHYS: Physics",
				"POLI: Politics", "PSYC: Psychology", "RELI: Religious Studies", "SOCI: Sociology",
				"SPAN: Spanish", "DANA: Theater Arts & Dance Activity", "TART: Theatre Arts & Dance", "TARA: Theatre Practicum Activity"));

	 List<String> courseTimes = new ArrayList<>(Arrays.asList("Times", "A-1", "A-2", "A-3", "A-4", "A-5", "A-6", "A-7","A-8",
				"B-1", "B-2", "B-3", "B-4", "B-5",
				"C-1", "C-2", "C-3", "C-4", "C-5", "C-6", "C-7", "C-8",
				"D-1", "D-2", "D-3", "D-4", "D-5", "D-6", "D-7", "D-8", "D-9", "D-10",
				"S-1", "S-2", "S-3",
				"L-1", "L-2", "L-3", "L-4", "L-5", "L-6"));



	@FXML
	public void initialize(){
		for(String subject: subjects){
			subjectSelector.getItems().add(subject);
		}
		subjectSelector.getSelectionModel().select(0);

		for(String time: courseTimes){
			courseTimeSelector.getItems().add(time);
		}
		courseTimeSelector.getSelectionModel().select(0);
	}

	public boolean isValidFastSearchNum(){
		fastSearchNumberText.setStyle("-fx-border-color: white;");
		try{
			int num = Integer.parseInt(fastSearchNumberText.getText());
			if(num >= 1000 && num < 100000){
				return true;
			}
		} catch(Exception a){
			return false;
		}
		return true;
	}


	// Citation #1
	@FXML
	public void openCourseTimeDescription(){
		String downloadURL="https://www.hendrix.edu/academics/academics.aspx?id=122";
		java.awt.Desktop myNewBrowserDesktop = java.awt.Desktop.getDesktop();
		try{
	       java.net.URI myNewLocation = new java.net.URI(downloadURL);
	      myNewBrowserDesktop.browse(myNewLocation);
	      } catch(Exception e){

	      }
	}


	@FXML
	public void searchFunction(ActionEvent event){
		if(courseTime.isSelected()){
			System.out.println("CourseTime");
		}
		
		if(subject.isSelected()){
			System.out.println("Subject");
		}
		
		if(professor.isSelected()){
			System.out.println("Professor");
		}
		
		if(fastSearch.isSelected()){
			System.out.println("FastSearch");
		}
		
		if(courseTitle.isSelected()){
			System.out.println("CourseTitle");
		}

		
		Stage stage = (Stage) search.getScene().getWindow();
		stage.close();



	}


}
