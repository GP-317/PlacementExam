package tests;

import tfr_l2project.Administrateur;
import tfr_l2project.Utilisateur;

public class TestsGeneraux {

	public static void main(String[] args) {
		
		Administrateur admin = new Administrateur(null, null, 0);
		
		Utilisateur user = admin.createUser();
		System.out.println(user.getUserID());
		System.out.println(user.getUserMDP());
		System.out.println(user.getPROFILE());
		
		
	}

}
