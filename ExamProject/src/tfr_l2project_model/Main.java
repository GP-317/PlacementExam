package tfr_l2project_model;

import java.sql.SQLException;
import java.util.Scanner;

import tfr_l2project.Utilisateur;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Utilisateur UserLambda = new Utilisateur();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Veuillez entrer un identifiant");
		String ID = sc.nextLine();
		System.out.println("Veuillez entrer un mot de passe");
		String MDP = sc.nextLine();
		
		
		if(CallSQL.comparePW(ID, MDP) == true) {
			System.out.println("Bienvenue " + CallSQL.getNameUser(ID));
			UserLambda.setUserID(ID);
			UserLambda.setUserMDP(MDP);
			UserLambda.setPROFILE(CallSQL.getUserProfile(ID));
		}
		else System.out.println("Erreur de connexion");
	
//		CallSQL.updateExamen();
//	
	
	}

}
