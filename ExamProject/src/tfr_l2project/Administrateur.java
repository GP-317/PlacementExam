package tfr_l2project;

import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe recense les m�thodes utilis�es par les fonctions appel�es lorsqu'un
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
	 * @param profile Profil attribu� lors de la cr�ation du compte utilisateur.
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
	 * @param uSER_ID l'identifiant utilisateur d�sir�.
	 */
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}



	/**
	 * Getter du mot de passe utilisateur
	 * @return le mot de passe de l'utilisateur concern�.
	 */
	public String getUSER_MDP() {
		return USER_MDP;
	}


	/**
	 * Setter du mdp utilisateur
	 * @param uSER_MDP mot de passe d�sir� pour l'utilisateur s�lectionn�.
	 */
	public void setUSER_MDP(String uSER_MDP) {
		USER_MDP = uSER_MDP;
	}
	
	/**
	 * M�thode cr�ant un mot de passe g�n�r� al�atoirement. La longueur, pr�s�lectionn�e, est de huit caract�res.
	 * Ce mot de passe doit pouvoir �tre renvoy� � l'identifiant utilisateur (s'agissant d'un email) afin qu'il
	 * puisse s'authentifier.
	 * @return le mot de passe g�n�r� al�atoirement.
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
	 * M�thode de cr�ation d'un nouvel utilisateur. L'administrateur doit pouvoir conna�tre l'adresse email
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
