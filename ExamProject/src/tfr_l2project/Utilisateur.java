package tfr_l2project;

// import java.util.Scanner;


/**
 * Classe d�finissant identifiant et mot de passe de l'utilisateur d�sirant s'authentifier.
 * La premi�re page de l'interface graphique s'appuiera sur les valeurs d�tenues ici pour l'authentification.
 */

public class Utilisateur {
	
	// Scanner input = new Scanner(System.in);
	
	protected String USER_MDP;
	private String USER_ID;
	private int PROFILE;
	
	
	/**
	 * M�thode requ�rant le mot de passe utilisateur � des fins d'authentification.
	 * @return userMDP
	 */
	public String getUserMDP() {
		return this.USER_MDP;
	}

	/**
	 * M�thode d�finissant le mot de passe utilisateur (on utilisera dans une autre classe la fonction Scanner).
	 * @param userMDP Mot de passe entr� par l'utilisateur
	 */
	public void setUserMDP(String userMDP) {
		this.USER_MDP = userMDP;
	}
	
	/**
	 * M�thode qui param�tre l'identifiant utilisateur entr� par ce dernier pour s'authentifier.
	 * @param userID Identifiant entr� par l'utilisateur
	 */
	public void setUserID(String userID) {
		this.USER_ID = userID;
	}
	
	/**
	 * M�thode qui rappellera l'identifiant utilisateur pour effectuer la comparaison avec la base de donn�es.
	 * @return userID
	 */
	public String getUserID() {
		return this.USER_ID;
	}
	
	public int getPROFILE() {
		return PROFILE;
	}

	public void setPROFILE(int pROFILE) {
		PROFILE = pROFILE;
	}

	
	
	
	
	public Utilisateur() {
		String userID = null;
		String userMDP = null;
		int profile = 0;
	}
	
	
	
	/**
	 * M�thode "constructeur" qui param�tre identifiant et mot de passe de l'utilisateur.
	 * @param userID Identifiant de l'utilisateur
	 * @param userMDP Mot de passe de l'utilisateur
	 */
	public Utilisateur(String userID, String userMDP, int profile) {
		this.USER_ID = userID;
		this.USER_MDP = userMDP;
		this.PROFILE = profile;
	}
	
}
