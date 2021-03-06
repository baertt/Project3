// Citation #1: http://stackoverflow.com/questions/10444722/opening-a-full-web-page-by-using-java

package Controllers;
import dataBaseConstructor.ExecuteInfo;
import dataBaseConstructor.NewDataConstructer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	NewDataConstructer newdata = new NewDataConstructer();
	ExecuteInfo exe = new ExecuteInfo();
	String ctime = new String();
    String sub = new String();
    String prof = new String();
    String ctitle = new String();
    String cnum = new String();

	@FXML
	Button search;

	@FXML
	Button whatTimes;

	@FXML
	Button whatDomains;

	@FXML
	ChoiceBox<String> learningDomains;


	@FXML
	TextField professorText;

	@FXML
	TextField courseNumberText;

	@FXML
	TextField courseTitleText;

	@FXML
	ChoiceBox<String> courseTimeSelector;

	@FXML
	ChoiceBox<String> subjectSelector;

	CourseListController courseList;

	String semester;

//	String trans;

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

	 List<String> courseTimes = new ArrayList<>(Arrays.asList("Times", "A1", "A2", "A3", "A4", "A5", "A6", "A7","A8",
				"B1", "B2", "B3", "B4", "B5",
				"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8",
				"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10",
				"S1", "S2", "S3",
				"L1", "L2", "L3", "L4", "L5", "L6", "NA"));

	 List<String> domains = new ArrayList<>(Arrays.asList("Domains", "EA: Expressive Arts", "HP: Historical Perspectives",
			 "LS: Literary Studies", "NS: Natural Science Inquiry", "NS-L: Natural Science Inquiry with Lab",
			 "SB: Social and Behavioral Analysis","VA: Values, Beliefs and Ethics"));



	@FXML
	void initialize(){
		for(String subject: subjects){
			subjectSelector.getItems().add(subject);
		}
		subjectSelector.getSelectionModel().selectFirst();

		for(String time: courseTimes){
			courseTimeSelector.getItems().add(time);
		}

		courseTimeSelector.getSelectionModel().select(0);
		for(String domain: domains){
			learningDomains.getItems().add(domain);
		}
		learningDomains.getSelectionModel().select(0);

	}

	public boolean isValidFastSearchNum(){
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
	    	  // TODO: Come back and write a real error
	      }
	}

	@FXML
	public void openLearningDomainDescription(){
		String downloadURL="https://www.hendrix.edu/academics/academics.aspx?id=1072";
		java.awt.Desktop myNewBrowserDesktop = java.awt.Desktop.getDesktop();
		try{
	       java.net.URI myNewLocation = new java.net.URI(downloadURL);
	      myNewBrowserDesktop.browse(myNewLocation);
	      } catch(Exception e){

	      }
	}


	@FXML
	public void searchFunction() throws ClassNotFoundException, SQLException, IOException{
		//System.out.println("OK");
		//System.out.println(sub);
		if (courseTimeSelector.getSelectionModel().isSelected(0)) {
			ctime = "";
		} else {
			ctime = courseTimeSelector.getSelectionModel().getSelectedItem();
		}

		if (subjectSelector.getSelectionModel().isSelected(0)) {
			sub = "";
		} else {
			sub = subjectSelector.getSelectionModel().getSelectedItem();
		}

		prof = professorText.getText();
		ctitle = courseTitleText.getText();
		cnum = courseNumberText.getText();

		//System.out.println(sub);

		//System.out.println(sub);

		populate(ctime, sub, prof, ctitle, cnum, semester);
		//System.out.println(semester);


		Stage stage = (Stage) search.getScene().getWindow();
		stage.close();



	}

	void populate(String ctime, String sub, String prof, String ctitle, String cnum, String semester) throws ClassNotFoundException, SQLException {
		courseList.courses.getItems().clear();
		Class.forName("org.sqlite.JDBC");
        Connection con = DriverManager.getConnection("jdbc:sqlite:" + Integer.toString(newdata.year()) + ".db");
        Statement stat = con.createStatement();
        //System.out.println(exe.executeinfo(ctime, sub, prof, ctitle, cnum, semester));
        if (stat.execute(exe.executeinfo(ctime, sub, prof, ctitle, cnum, semester))) {
			ResultSet results = stat.getResultSet();
	        while (results.next()) {
	        	courseList.courses.getItems().add(new CourseInfo(results.getString(3), results.getString(4),
		        		results.getString(7), results.getString(5), results.getString(6), results.getString(8)));
	        }
		}
        //System.out.println("OK");
	}

	public void importVariables(CourseListController courseListController) {
		this.courseList = courseListController;
		this.semester = courseListController.semester;
		//System.out.println("Importing variables");
	}
}
