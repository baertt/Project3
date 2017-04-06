package Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CourseInfo {
	private StringProperty code, title, prof, period, time, descrip;
	private int begIndex = 23;

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

	public String getCode() {return code.toString().substring(begIndex, code.toString().length() - 1);}
	public String getTitle() {return title.toString().substring(begIndex, title.toString().length() - 1);}
	public String getProf() {return prof.toString().substring(begIndex, prof.toString().length() - 1);}
	public String getPeriod() {return period.toString().substring(begIndex, period.toString().length() - 1);}
	public String getTime() {return time.toString().substring(begIndex, time.toString().length() - 1);}
	public String getDescrip() {return descrip.toString().substring(begIndex, descrip.toString().length() - 1);}

	@Override
	public String toString(){
		return String.format("%s\n%s\n%s\n\n%s", getCode(), getTitle(), getProf(), getDescrip());
	}
}
