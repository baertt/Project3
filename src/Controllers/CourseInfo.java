package Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseInfo {
	private StringProperty code, title, prof, period, time, descrip;

	public CourseInfo(String code, String title, String prof, String period, String time, String descrip){
		this.code = new SimpleStringProperty(code);
		this.title = new SimpleStringProperty(title);
		this.prof = new SimpleStringProperty(prof);
		this.period = new SimpleStringProperty(period);
		this.time = new SimpleStringProperty(time);
		this.descrip = new SimpleStringProperty(descrip);
	}

	public StringProperty codeProperty() {return code;}
	public StringProperty titleProperty() {return title;}
	public StringProperty profProperty() {return prof;}
	public StringProperty periodProperty() {return period;}
	public StringProperty timeProperty() {return time;}
	public StringProperty descripProperty() {return descrip;}

	@Override
	public String toString(){
		int begIndex = 23;
		//int endIndex = descripProperty().toString().length() - 1;
		return String.format("%s\n%s\n%s\n\n%s", code.toString().substring(begIndex, code.toString().length() - 1) ,
							title.toString().substring(begIndex, title.toString().length() - 1) ,
							prof.toString().substring(begIndex, prof.toString().length() - 1),
							descrip.toString().substring(begIndex, descrip.toString().length() - 1));
	}
}
