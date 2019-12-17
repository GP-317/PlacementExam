package tfr_l2project_model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



/**
 * Classe inventoriant toutes les méthodes d'usage des requêtes SQL
 * @author Geoffrey
 *
 */
public class CallSQL {
	
	
	/**
	 * Les trois attributs codifiés private constituent le coeur de la connexion à la base de données.
	 * Si user et mdp sont voués à changer, plus tard, pour se conformer aux ID des utilisateurs et aux
	 * mots de passe de ces derniers, url ne devra, a priori, jamais changer à moins de modifier l'emplacement
	 * de la base de données.
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
	 * URL de la base de données attribuée
	 * @return the url
	 */
	public static String getURL() {
		return URL;
	}



	/**
	 * Utilisateur de la BDD
	 * @return the user
	 */
	public static String getUser() {
		return USER;
	}



	/**
	 * Mot de passe d'accès à la base de données
	 * @return the mdp
	 */
	public static String getMDP() {
		return MDP;
	}



	/**
	 * La méthode getStudentIdentity récupère le contenu de la table Eleve, transportant par une chaîne de caractères
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
				System.out.print("\t" + rs.getObject(i).toString() + "\t |");	//L'avantage de cet appel de méthode réside dans
																				//le caractère inconnu du type.
			System.out.println("\n---------------------------------");

		}

	}
	
	
	
	/**
	 * Méthode permettant la sélection des nom et prénom de tous les utilisateurs de la base de données
	 * @return str, une chaine de caractères contenant les nom et prénom des utilisateurs de la bdd
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
	 * @return str chaine de caractères composée des noms susmentionnés
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
	 * Méthode renvoyant quelles salles sont disponibles
	 * La vérification se réalise par la comparaison sur un booléen au sein de la table
	 * @return str, une chaine de caractères
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
	 * Affiche le nom des salles disponibles à un horaire prédéfini (qui pourra, plus tard, être changé au gré de l'utilisateur)
	 * @return str, une chaîne de carractères
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
	 * Affiche, plus crûment, les salles et les horaires qui leur sont attribués.
	 * @return str, chaîne de caractères
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
		
		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)				
				System.out.print("\t" + rs.getObject(i).toString() + "\t |");	
															
			System.out.println("\n----------------------------------------------------------");

		}
		
		return str;
	}
	
	
	
	
// Code inutilisé
	
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
	 * Met à jour la banque de données
	 * Méthode manuellement paramétrée mais pourra être automatique par la suite
	 * @return str renvoie une chaine de caractères
	 * @throws SQLException
	 */
	@Deprecated
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
	
	
	
	/**
	 * Méthode servant à la comparaison du mot de passe entré par l'utilisateur lors de son authentification
	 * et le mot de passe enregistré dans la base de données, lié à son identifiant utilisateur.
	 * La méthode recherche d'abord l'identifiant entré puis sélectionne le mot de passe afin de le comparer avec
	 * le mot de passe en paramètre.
	 * @param user l'identifiant de l'utilisateur désirant s'authentifier
	 * @param mdp mot de passe entré par l'utilisateur
	 * @return un booléen, qui indiquera si oui ou non l'authentification peut s'effectuer. Dépend du résultat de la
	 * comparaison.
	 * @throws SQLException
	 */
	public static boolean comparePW(String user, String mdp) throws SQLException {
		
		CN = DriverManager.getConnection(URL, USER, MDP);
		

		PreparedStatement st = CN.prepareStatement("SELECT password FROM utilisateur WHERE email = ?");
		st.setString(1, user);
		ResultSet rs = st.executeQuery();
		
		String str = "";
		
		while(rs.next()) {
			str = rs.getString("password");
		}
		
		if ( str.equals(mdp) ) {
			return true;
		}
		else return false;
	}
	
	
	
	
	
	/**
	 * Sélectionne le nom de l'utilisateur dans la base de données, depuis son identifiant.
	 * @param ID identifiant
	 * @return le prénom de l'utilisateur
	 * @throws SQLException
	 */
	public static String getNameUser(String ID) throws SQLException {
		CN = DriverManager.getConnection(URL, USER, MDP);

		PreparedStatement st = CN.prepareStatement("SELECT prenom FROM utilisateur WHERE email = ?");
		st.setString(1, ID);
		ResultSet rs = st.executeQuery();
		
		String str = "";
		
		while(rs.next()) {
			str = rs.getString("prenom");
		}
		
		return str;
	}
	
	
	
	/**
	 * Méthode de sélection du profil d'un utilisateur
	 * @param ID utilisateur dont on recherche le profil
	 * @return l'entier désignant le profil attribué
	 * @throws SQLException
	 */
	public static int getUserProfile(String ID) throws SQLException {
		CN = DriverManager.getConnection(URL, USER, MDP);

		PreparedStatement st = CN.prepareStatement("SELECT idProfil FROM utilisateur WHERE email = ?");
		st.setString(1, ID);
		ResultSet rs = st.executeQuery();
		
		int profil = 0;
		
		while(rs.next()) {
			profil = rs.getInt("idProfil");
		}
		
		return profil;
	}
	
	
	/**
	 * Méthode créant un nouvel examen selon les paramètres requis par l'application à l'utilisateur.
	 * Ne renvoie rien, met simplement à jour la base de données.
	 * @throws SQLException
	 */
	public static void newExamen() throws SQLException{
		
		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement  st = CN.prepareStatement("INSERT INTO examen (idExamen, titre, duree, "
				+ "profSurveillant, matiere,tempsDebut)"
				+"  VALUES (NULL, ?, ?, ?, ?, ?)");
		System.out.println("Veuillez saisir un titre d'examen : ");
		Scanner t = new Scanner(System.in);
		String x= t.nextLine();
		st.setString(1,x);

		System.out.println("Veuillez saisir une durée d'examen ex 02:00:00 : ");
		Scanner d= new Scanner(System.in);
		String y= d.nextLine();
		st.setString(2,y);

		System.out.println("Veuillez saisir le ou les noms/prenoms des profs surveillant de l'examen : ");
		Scanner t1= new Scanner(System.in);
		String b= t1.nextLine();
		st.setString(3,b);

		System.out.println("Veuillez saisir la matiere de l'examen : ");
		Scanner m= new Scanner(System.in);
		String z = m.nextLine();
		st.setString(4,z);

		System.out.println("Veuillez saisir la date est l'heure de l'examen ex: 2018-12-31 10:30:00  : ");
		Scanner s= new Scanner(System.in);
		String p= s.nextLine();
		st.setString(5,p);
		st.execute();
	}
	
	
	
	/**
	 * Méthode qui met à jour un examen sélectionné selon les paramètres désirés.
	 * Il ne faudra rien rentrer pour ne pas modifier l'intitulé.
	 * @throws SQLException
	 */	
	public static void updateExamen() throws SQLException{
		
		System.out.println("Veuillez saisir le titre de l'examen à mettre à jour (ex: 'Mathématiques L2 Info') : ");
		Scanner i = new Scanner(System.in);
		String f = i.nextLine();
		
		
		System.out.println("Veuillez saisir ici la nouveau titre de l'examen  :");		
		Scanner t = new Scanner(System.in);
		String x = t.nextLine();
		if ( x.length() != 0 ) {
			updateExamenTitre(f, x);
		}

		System.out.println("Veuillez saisir la nouvelle duree : ");
		Scanner d = new Scanner(System.in);
		String y = d.nextLine();
		if ( y.length() != 0 ) {
			updateExamenDuree(f, y);
		}

		System.out.println("Veuillez saisir le profSurveillant : ");
		Scanner t1 = new Scanner(System.in);
		String b = t1.nextLine();
		if ( b.length() != 0 ) {
			updateExamenProfSurv(f, b);
		}

		System.out.println("Veuillez saisir la nouvelle matiere  : ");
		Scanner m = new Scanner(System.in);
		String z = m.nextLine();
		if ( z.length() != 0 ) {
			updateExamenMatiere(f, z);
		}

		System.out.println("Veuillez saisir la nouvelle date et l'heure de l'examen ex: 2018-12-31 10:30:00  : ");
		Scanner s = new Scanner(System.in);
		String p = s.nextLine();
		if ( p.length() != 0 ) {
			updateExamenTempsDebut(f, p);
		}
		
	}
	
	/**
	 * Met à jour le titre de l'examen
	 * @param titreInit Titre initial de recherche
	 * @param titreModif Titre modifié à l'issue de la requête
	 * @throws SQLException
	 */
	public static void updateExamenTitre(String titreInit, String titreModif) throws SQLException{

		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement st = CN.prepareStatement("UPDATE examen SET titre =? WHERE titre =?");

		st.setString(2, titreInit);
		st.setString(1, titreModif);

		st.executeUpdate();
	}

	/**
	 * Met à jour la durée d'un examen
	 * @param titre Titre de l'examen recherché
	 * @param duree Durée mise à jour pour la requête
	 * @throws SQLException
	 */
	public static void updateExamenDuree(String titre, String duree) throws SQLException{

		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement  st = CN.prepareStatement("UPDATE examen SET duree=? WHERE titre =?");

		st.setString(1, duree);
		st.setString(2, titre);

		st.executeUpdate();
	}
	
	/**
	 * Met à jour le nom du prof surveillant
	 * @param titre Titre de l'examen recherché
	 * @param prof Nom du professeur nouvellement assigné
	 * @throws SQLException
	 */
	public static void updateExamenProfSurv(String titre, String prof) throws SQLException{
		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement  st = CN.prepareStatement("UPDATE examen SET profSurveillant=? WHERE titre =?");
		
		st.setString(1, prof);
		st.setString(2, titre);
		
		st.executeUpdate();
	}

	/**
	 * Met à jour la matière concerné lors de l'examen
	 * @param titre Titre de l'examen recherché
	 * @param matiere Nom de la matière modifié
	 * @throws SQLException
	 */
	public static void updateExamenMatiere(String titre, String matiere) throws SQLException{
		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement  st = CN.prepareStatement("UPDATE examen SET matiere=? WHERE titre =?");
		
		st.setString(1, matiere);
		st.setString(2, titre);
		
		st.executeUpdate();
	}

	/**
	 * Met à jour la date et l'heure du début d'épreuve
	 * @param titre Titre de l'examen concerné
	 * @param tpsDeb Chaine de caractères exprimant l'heure et la date de début d'épreuve selon la syntaxe de la base de données.
	 * @throws SQLException
	 */
	public static void updateExamenTempsDebut(String titre, String tpsDeb) throws SQLException{
		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement  st = CN.prepareStatement("UPDATE examen SET tempsDebut =? WHERE titre =?");
		
		st.setString(1, tpsDeb);
		st.setString(2, titre);
		
		st.executeUpdate();
	}
	
	
}
