package tests;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import tfr_l2project.Administrateur;
import tfr_l2project.Utilisateur;

public class TestsGeneraux {

	
	@Test
	public void LancerTestsGeneraux() {
		
		assertTrue(testUserCreation());
		
	}
	
	
	private boolean testUserCreation() {
		
		Administrateur admin = new Administrateur(null, null, 0);
		
		String ID = "albert.einstein@gmail.com";
		String MDP = null;
		int PROFILE = 2;
		
		Utilisateur user = admin.createUser();
		System.out.println(user.getUserID());
		System.out.println(user.getUserMDP());
		System.out.println(user.getPROFILE());
		
		MDP = user.getUserMDP();
		
		if( ID.equals(user.getUserID()) 
				&& MDP.equals(user.getUserMDP())
				&& PROFILE == user.getPROFILE() ) {
			return true;
		}
		else return false;
		
	}

}

