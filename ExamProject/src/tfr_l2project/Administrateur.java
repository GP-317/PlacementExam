package tfr_l2project;

import java.util.Random;
import java.util.Scanner;

public class Administrateur extends Utilisateur{

	
	private String USER_ID;
	private String USER_MDP;
	private int PROFILE;
	
	public Administrateur(String userID, String userMDP, int profile) {
		super(userID, userMDP, profile);
		
		this.USER_ID = userID;
		this.USER_MDP = userMDP;
		this.PROFILE = profile;
	}
	
	
	
	public String getUSER_ID() {
		return USER_ID;
	}



	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}



	public String getUSER_MDP() {
		return USER_MDP;
	}



	public void setUSER_MDP(String uSER_MDP) {
		USER_MDP = uSER_MDP;
	}
	
	
	public String newMDP() {
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



	public Utilisateur createUser() {
		
		Scanner sc = new Scanner(System.in);
		String ID = sc.nextLine();
		String MDP = newMDP().toString();
		int profile = sc.nextInt();
		
		
		Utilisateur newUser = new Utilisateur(ID, MDP, profile);
		
		return newUser;
		
	}
	
	
	
	
	
}
