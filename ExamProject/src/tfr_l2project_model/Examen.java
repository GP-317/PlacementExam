package tfr_l2project_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Examen {
	
	public String TITLE;
	public String NAME_TEACHER;
	public String MATIERE;
	public int DURATION;
	public int[] START_TIME;
	
	
	
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
	 * @return the matiere le nom de la mati�re de l'examen
	 */
	public String getMatiere() {
		return MATIERE;
	}



	/**
	 * @return the duration la dur�e de l'examen
	 */
	public int getDuration() {
		return DURATION;
	}



	/**
	 * @return the dateBegin l'heure de d�but
	 */
	public int[] getStartTime() {
		return START_TIME;
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
	 * @param matiere attribue le nom de la mati�re
	 */
	public void setMatiere(String matiere) {
		this.MATIERE = matiere;
	}



	/**
	 * @param duration attribue la dur�e
	 */
	public void setDuration(int duration) {
		this.DURATION = duration;
	}



	/**
	 * @param startTime attribue l'heure de d�but d'�preuve
	 */
	public void setStartTime(int[] startTime) {
		this.START_TIME = startTime;
	}



	/**
	 * Cr�e une nouvelle �preuve d'un examen
	 * @param title Intitul� de l'examen
	 * @param nameTeacher Nom du professeur surveillant
	 * @param matiere Nom de la mati�re concern�
	 * @param duration Dur�e de l'examen
	 * @param startTime Jour et heure du d�but d'�preuve
	 */
	public void newExam(String title, String nameTeacher, String matiere, int duration, int[] startTime) {
		this.TITLE = title;
		this.NAME_TEACHER = nameTeacher;
		this.MATIERE = matiere;
		this.DURATION = duration;
		this.START_TIME = startTime;
	}
	
	
}
