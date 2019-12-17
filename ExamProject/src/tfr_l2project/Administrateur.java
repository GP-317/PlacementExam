package tfr_l2project;

import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe recense les méthodes utilisées par les fonctions appelées lorsqu'un
 * utilisateur qui est administrateur emploie l'application.
 * @author Geoffrey
 *
 */
public class Administrateur extends Utilisateur{

	private String USER_ID;
	private String USER_MDP;
	private int PROFILE;
	
	
	/**
	 * Constructeur de la classe.
	 * @param userID Identifiant de l'utilisateur.
	 * @param userMDP Mot de passe de l'utilisateur.
	 * @param profile Profil attribué lors de la création du compte utilisateur.
	 */
	public Administrateur(String userID, String userMDP, int profile) {
		super(userID, userMDP, profile);
		
		this.USER_ID = userID;
		this.USER_MDP = userMDP;
		this.PROFILE = profile;
	}
	
	
	/**
	 * Getter de l'ID utilisateur
	 * @return l'identifiant utilisateur
	 */
	public String getUSER_ID() {
		return USER_ID;
	}


	/**
	 * Setter de l'ID utilisateur
	 * @param uSER_ID l'identifiant utilisateur désiré.
	 */
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}



	/**
	 * Getter du mot de passe utilisateur
	 * @return le mot de passe de l'utilisateur concerné.
	 */
	public String getUSER_MDP() {
		return USER_MDP;
	}


	/**
	 * Setter du mdp utilisateur
	 * @param uSER_MDP mot de passe désiré pour l'utilisateur sélectionné.
	 */
	public void setUSER_MDP(String uSER_MDP) {
		USER_MDP = uSER_MDP;
	}
	
	/**
	 * Méthode créant un mot de passe généré aléatoirement. La longueur, présélectionnée, est de huit caractères.
	 * Ce mot de passe doit pouvoir être renvoyé à l'identifiant utilisateur (s'agissant d'un email) afin qu'il
	 * puisse s'authentifier.
	 * @return le mot de passe généré aléatoirement.
	 */
	public String aleaMDP() {
		Random rand = new Random();
		String userMDP = "abcd1234";
		int longueur = 8;
		String newMDP = "";
		
		for(int i = 0; i < 8; i++) {
			int k = rand.nextInt(longueur);
			newMDP += userMDP.charAt(k);
		}
		return newMDP;
	}


	/**
	 * Méthode de création d'un nouvel utilisateur. L'administrateur doit pouvoir connaître l'adresse email
	 * qui servira d'identifiant au futur utilisateur de l'application.
	 * @return un objet de type Utilisateur.
	 */
	public Utilisateur createUser() {
		
		
		Scanner sc = new Scanner(System.in);
		String ID = sc.nextLine();
		String MDP = aleaMDP().toString();
		int profile = sc.nextInt();
		
		Utilisateur newUser = new Utilisateur();
		
		newUser.setUserID(ID);
		newUser.setUserMDP(MDP);
		newUser.setPROFILE(profile);
		
		return newUser;
	}
	
	
	
	
	
}
