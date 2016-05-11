package edu.coursera;

import java.util.ArrayList;

public class CourseDetail 
{
	private String courseName;
	private String courseId;
	private ArrayList<String> instructorsName;
	private ArrayList<String> instructorsId;
	private ArrayList<String> partnersName;
	private ArrayList<String> partnersId;
	private ArrayList<String> partnersImageUrl;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public ArrayList<String> getInstructorsName() {
		return instructorsName;
	}
	public void setInstructorsName(ArrayList<String> instructorsName) {
		this.instructorsName = instructorsName;
	}
	public ArrayList<String> getInstructorsId() {
		return instructorsId;
	}
	public void setInstructorsId(ArrayList<String> instructorsId) {
		this.instructorsId = instructorsId;
	}
	public ArrayList<String> getPartnersName() {
		return partnersName;
	}
	public void setPartnersName(ArrayList<String> partnersName) {
		this.partnersName = partnersName;
	}
	public ArrayList<String> getPartnersId() {
		return partnersId;
	}
	public void setPartnersId(ArrayList<String> partnersId) {
		this.partnersId = partnersId;
	}
	public ArrayList<String> getPartnersImageUrl() {
		return partnersImageUrl;
	}
	public void setPartnersImageUrl(ArrayList<String> partnersImageUrl) {
		this.partnersImageUrl = partnersImageUrl;
	}
}
