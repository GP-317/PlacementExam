package tfr_l2project_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import tfr_l2project.CallSQL;

public class Examen {
	
	public String TITLE;
	public String NAME_TEACHER;
	public String MATIERE;
	public int DURATION;
	public int[] DATE_BEGIN;
	
	
	
	/**
	 * @return user l'utilisateur
	 */
	public String getTitle() {
		return TITLE;
	}



	/**
	 * @return the nameTeacher
	 */
	public String getNameTeacher() {
		return NAME_TEACHER;
	}



	/**
	 * @return the matiere
	 */
	public String getMatiere() {
		return MATIERE;
	}



	/**
	 * @return the duration
	 */
	public int getDuration() {
		return DURATION;
	}



	/**
	 * @return the dateBegin
	 */
	public int[] getDateBegin() {
		return DATE_BEGIN;
	}



	/**
	 * @param title the user to set
	 */
	public void setTitle(String title) {
		this.TITLE = title;
	}



	/**
	 * @param nameTeacher the nameTeacher to set
	 */
	public void setNameTeacher(String nameTeacher) {
		this.NAME_TEACHER = nameTeacher;
	}



	/**
	 * @param matiere the matiere to set
	 */
	public void setMatiere(String matiere) {
		this.MATIERE = matiere;
	}



	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.DURATION = duration;
	}



	/**
	 * @param dateBegin the dateBegin to set
	 */
	public void setDateBegin(int[] dateBegin) {
		this.DATE_BEGIN = dateBegin;
	}



	/**
	 * 
	 * @param title
	 * @param nameTeacher
	 * @param matiere
	 * @param duration
	 * @param dateBegin
	 */
	public void newExam(String title, String nameTeacher, String matiere, int duration, int[] dateBegin) {
		this.TITLE = title;
		this.NAME_TEACHER = nameTeacher;
		this.MATIERE = matiere;
		this.DURATION = duration;
		this.DATE_BEGIN = dateBegin;
	}
	
	public void transferExam() throws SQLException {
		new CallSQL();
		String url = CallSQL.getURL();
		String user = CallSQL.getUser();
		String mdp = CallSQL.getMDP();
		
		DriverManager.getConnection(url, user, mdp);
		
	}
	
	
	
	
}
