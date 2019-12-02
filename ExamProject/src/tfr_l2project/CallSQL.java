package tfr_l2project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Classe inventoriant toutes les m�thodes d'usage des requ�tes SQL
 * @author Geoffrey
 *
 */
public class CallSQL {
	
	
	/**
	 * Les trois attributs codifi�s private constituent le coeur de la connexion � la base de donn�es.
	 * Si user et mdp sont vou�s � changer, plus tard, pour se conformer aux ID des utilisateurs et aux
	 * mots de passe de ces derniers, url ne devra, a priori, jamais changer � moins de modifier l'emplacement
	 * de la base de donn�es.
	 */
	
	private static	String URL = 
			"jdbc:mysql://localhost:3307/dbproject"					
				+"?useSSL=false&useUnicode=true"
					+"&useJDBCCompliantTimezoneShift=true"
						+"&useLegacyDatetimeCode=false"
							+"&serverTimezone=UTC"
								+"&allowPublicKeyRetrieval=true";
	
	private static String USER = "root";
	private static String MDP = "usbw";
	static Connection CN = null;
	
	
	
	
	/**
	 * @return the url
	 */
	public static String getURL() {
		return URL;
	}



	/**
	 * @return the user
	 */
	public static String getUser() {
		return USER;
	}



	/**
	 * @return the mdp
	 */
	public static String getMDP() {
		return MDP;
	}



	/**
	 * @return the cn
	 */
	public static Connection getCN() {
		return CN;
	}



	/**
	 * @param url the url to set
	 */
	public static void setURL(String url) {
		CallSQL.URL = url;
	}



	/**
	 * @param user the user to set
	 */
	public static void setUser(String user) {
		CallSQL.USER = user;
	}



	/**
	 * @param mdp the mdp to set
	 */
	public static void setMDP(String mdp) {
		CallSQL.MDP = mdp;
	}



	/**
	 * @param cn the cn to set
	 */
	public static void setCN(Connection cn) {
		CallSQL.CN = cn;
	}



	/**
	 * La m�thode getStudentIdentity r�cup�re le contenu de la table Eleve, transportant par une cha�ne de caract�res
	 * @throws SQLException
	 */
	public static void getStudentIdentity() throws SQLException {

		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Eleve");
		ResultSetMetaData resultMeta = rs.getMetaData();


		System.out.println("\n**********************************");
		//On affiche le nom des colonnes
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************");

		while(rs.next()){         
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)				//Permet la recherche du type des colonnes
				System.out.print("\t" + rs.getObject(i).toString() + "\t |");	//L'avantage de cet appel de m�thode r�side dans
																				//le caract�re inconnu du type.
			System.out.println("\n---------------------------------");

		}

	}
	
	
	
	/**
	 * M�thode permettant la s�lection des nom et pr�nom de tous les utilisateurs de la base de donn�es
	 * @return str, une chaine de caract�res contenant les nom et pr�nom des utilisateurs de la bdd
	 * @throws SQLException
	 */
	public static String selectAllUserID() throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		ResultSet rs = st.executeQuery("SELECT nom, prenom FROM Utilisateur");
		ResultSetMetaData resultMeta = rs.getMetaData();
		String str = "";
		
			while(rs.next()) {
				for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
				str += rs.getObject(i).toString() + " ";
				}
			}
			
			return str;
		
	}
	
	
	/**
	 * Affiche le nom des eleves qui sont dans la filliere L1 Info
	 * @return str chaine de caract�res compos�e des noms susmentionn�s
	 * @throws SQLException
	 */
	public static String selectNameStudL1Info() throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		ResultSet rs = st.executeQuery(	
				"SELECT  El.nom FROM\r\n" + 										//SELECT  El.nom FROM
					"Examen E INNER JOIN Filiere F \r\n" + 							//Examen E INNER JOIN Filiere F
						"ON E.idExamen = F.Examen_idExamen\r\n" + 					//ON E.idExamen = F.Examen_idExamen
							"INNER JOIN Eleve El\r\n" + 							//INNER JOIN Eleve El
								"ON El.Filiere_idFiliere=F.idFiliere\r\n" + 		//ON El.Filiere_idFiliere=F.idFiliere
									"WHERE nomFiliere='L1 Info'");					//WHERE nomFiliere='L1 Info'
		ResultSetMetaData resultMeta = rs.getMetaData();
		String str = "";
		
		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
			str += rs.getObject(i).toString() + " ";
			}
		}
		
		return str;
		
	}
	
	
	
	/**
	 * M�thode renvoyant quelles salles sont disponibles
	 * La v�rification se r�alise par la comparaison sur un bool�en au sein de la table
	 * @return str, une chaine de caract�res
	 * @throws SQLException
	 */
	public static String roomDispo() throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		ResultSet rs = st.executeQuery("SELECT S.nom FROM Salle S \r\n" + 
				"WHERE dispo =1");
		ResultSetMetaData resultMeta = rs.getMetaData();
		String str = "";
		
		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
			str += rs.getObject(i).toString() + " ";
			}
		}
		
		return str;
		
	}
	
	
	/**
	 * Affiche le nom des salles disponibles � un horaire pr�d�fini (qui pourra, plus tard, �tre chang� au gr� de l'utilisateur)
	 * @return str, une cha�ne de carract�res
	 * @throws SQLException
	 */
	public static String roomsDispoFixedSchedule() throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		ResultSet rs = st.executeQuery("SELECT S.nom FROM Salle S INNER JOIN Salle_has_PlageHoraire SP\r\n" + 
				"ON S.idSalle=SP.Salle_idSalle\r\n" + 
				"INNER JOIN PlageHoraire P\r\n" + 
				"ON P.idPlageHoraire=SP.PlageHoraire_idPlageHoraire\r\n" + 
				"WHERE S.dispo=1 AND P.date= '2019-11-21' AND P.intervallePlaHor= '1012'");
		ResultSetMetaData resultMeta = rs.getMetaData();
		String str = "";
		
		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
			str += rs.getObject(i).toString() + " ";
			}
		}
		
		return str;
		
	}
	
	
	/**
	 * Affiche, plus cr�ment, les salles et les horaires qui leur sont attribu�s.
	 * @return str, cha�ne de caract�res
	 * @throws SQLException
	 */
	public static String roomsAndSchedule() throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		ResultSet rs = st.executeQuery("SELECT S.nom, PH.date, PH.intervallePlaHor \r\n" + 
				"FROM Salle S INNER JOIN Salle_has_PlageHoraire SP\r\n" + 
				"ON SP.Salle_idSalle = S.idSalle INNER JOIN PlageHoraire PH \r\n" + 
				"ON PH.idPlageHoraire = SP.PlageHoraire_idPlageHoraire");
		ResultSetMetaData resultMeta = rs.getMetaData();
		String str = "";
		
		
		
		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");

		System.out.println("\n**********************************************************");
		
		while(rs.next()){         
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)				
				System.out.print("\t" + rs.getObject(i).toString() + "\t |");	
															
			System.out.println("\n----------------------------------------------------------");

		}
		
		return str;
	}
	
	
	
	
	/**
	 * WIP
	 */
	
//	public static void setOneUserID(String login) throws SQLException {
//		
//		cn = DriverManager.getConnection(url, user, mdp);
//
//		Statement st = cn.createStatement();
//		ResultSet rs = st.executeQuery("SELECT nom FROM Utilisateur"
//				+ "WHERE email =" + login);
//		ResultSetMetaData resultMeta = rs.getMetaData();
//		String str = "";
//		
//		while(rs.next()) {
//			for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
//				str += rs.getObject(i).toString();
//			}
//		}
//		System.out.println(str);
//		
//		utilisateur setting = new utilisateur(str, mdp);
//		String alfa = setting.getUserID();
//		
//		System.out.println(alfa);
//		
//	}
		
	
	/**
	 * Met � jour la banque de donn�es
	 * M�thode manuellement param�tr�e mais pourra �tre automatique par la suite
	 * @return str renvoie une chaine de caract�res
	 * @throws SQLException
	 */
	public static String update() throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);

		Statement st = CN.createStatement();
		String rs = "UPDATE Profil"
				+ "\n SET nomProfil = 'secretariat'"
				+ "\n WHERE idProfil = 1";
		
		st.executeUpdate(rs);
		String str ="";
		
		ResultSet result = st.executeQuery("SELECT nomProfil from Profil");
		ResultSetMetaData resultMeta = result.getMetaData();
		
		while(result.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)
				str += result.getObject(i).toString();
		}
		return str;
	}
	
//	public static void createUser() throws SQLException {
//		CN = DriverManager.getConnection(URL, USER, MDP);
//
//		Statement st = CN.createStatement();
//		String rs = 
//	}
	
	
	
}
