package tfr_l2project;

// import java.util.Scanner;


/**
 * Classe définissant identifiant et mot de passe de l'utilisateur désirant s'authentifier.
 * La première page de l'interface graphique s'appuiera sur les valeurs détenues ici pour l'authentification.
 */

public class Utilisateur {
	
	// Scanner input = new Scanner(System.in);
	
	protected String USER_MDP;
	private String USER_ID;
	private int PROFILE;
	
	
	/**
	 * Méthode requérant le mot de passe utilisateur à des fins d'authentification.
	 * @return userMDP
	 */
	public String getUserMDP() {
		return this.USER_MDP;
	}

	/**
	 * Méthode définissant le mot de passe utilisateur (on utilisera dans une autre classe la fonction Scanner).
	 * @param userMDP Mot de passe entré par l'utilisateur
	 */
	public void setUserMDP(String userMDP) {
		this.USER_MDP = userMDP;
	}
	
	/**
	 * Méthode qui paramètre l'identifiant utilisateur entré par ce dernier pour s'authentifier.
	 * @param userID Identifiant entré par l'utilisateur
	 */
	public void setUserID(String userID) {
		this.USER_ID = userID;
	}
	
	/**
	 * Méthode qui rappellera l'identifiant utilisateur pour effectuer la comparaison avec la base de données.
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
	 * Méthode "constructeur" qui paramètre identifiant et mot de passe de l'utilisateur.
	 * @param userID Identifiant de l'utilisateur
	 * @param userMDP Mot de passe de l'utilisateur
	 */
	public Utilisateur(String userID, String userMDP, int profile) {
		this.USER_ID = userID;
		this.USER_MDP = userMDP;
		this.PROFILE = profile;
	}
	
}
