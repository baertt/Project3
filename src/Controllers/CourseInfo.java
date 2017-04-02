package Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseInfo {
	private StringProperty code, title, prof, period, time;

	public CourseInfo(String code, String title, String prof, String period, String time){
		this.code = new SimpleStringProperty(code);
		this.title = new SimpleStringProperty(title);
		this.prof = new SimpleStringProperty(prof);
		this.period = new SimpleStringProperty(period);
		this.time = new SimpleStringProperty(time);
	}

	public StringProperty codeProperty() {return code;}
	public StringProperty titleProperty() {return title;}
	public StringProperty profProperty() {return prof;}
	public StringProperty periodProperty() {return period;}
	public StringProperty timeProperty() {return time;}
}
