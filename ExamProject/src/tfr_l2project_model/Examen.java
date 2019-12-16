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
	 * @return title le nom de l'examen
	 */
	public String getTitle() {
		return TITLE;
	}



	/**
	 * @return the nameTeacher le nom du professeur
	 */
	public String getNameTeacher() {
		return NAME_TEACHER;
	}



	/**
	 * @return the matiere le nom de la matière de l'examen
	 */
	public String getMatiere() {
		return MATIERE;
	}



	/**
	 * @return the duration la durée de l'examen
	 */
	public int getDuration() {
		return DURATION;
	}



	/**
	 * @return the dateBegin l'heure de début
	 */
	public int[] getDateBegin() {
		return DATE_BEGIN;
	}



	/**
	 * @param title attribue le nom de l'examen
	 */
	public void setTitle(String title) {
		this.TITLE = title;
	}



	/**
	 * @param nameTeacher attribue le nom du professeur
	 */
	public void setNameTeacher(String nameTeacher) {
		this.NAME_TEACHER = nameTeacher;
	}



	/**
	 * @param matiere attribue le nom de la matière
	 */
	public void setMatiere(String matiere) {
		this.MATIERE = matiere;
	}



	/**
	 * @param duration attribue la durée
	 */
	public void setDuration(int duration) {
		this.DURATION = duration;
	}



	/**
	 * @param dateBegin attribue l'heure de début d'épreuve
	 */
	public void setDateBegin(int[] dateBegin) {
		this.DATE_BEGIN = dateBegin;
	}



	/**
	 * Crée une nouvelle épreuve d'un examen
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
	
	
	public void modifyExam() {
		
		
		
	}
	
	
	
	
	
}
