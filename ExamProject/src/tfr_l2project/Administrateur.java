package tfr_l2project;

public class Administrateur extends Utilisateur{

	
	private String USER_ID;
	private String USER_MDP;
	
	public Administrateur(String userID, String userMDP, int profile) {
		super(userID, userMDP, profile);
		
		this.setUSER_ID(userID);
		this.setUSER_MDP(userMDP);
		// TODO Auto-generated constructor stub
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



	public void createUser() {
		
	}
	
	
	
	
	
}
