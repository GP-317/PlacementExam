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
	 * URL de la base de donn�es attribu�e
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
	 * Mot de passe d'acc�s � la base de donn�es
	 * @return the mdp
	 */
	public static String getMDP() {
		return MDP;
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
		
		while(rs.next()) {
			for(int i = 1; i <= resultMeta.getColumnCount(); i++)				
				System.out.print("\t" + rs.getObject(i).toString() + "\t |");	
															
			System.out.println("\n----------------------------------------------------------");

		}
		
		return str;
	}
	
	
	
	
// Code inutilis�
	
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
	 * M�thode servant � la comparaison du mot de passe entr� par l'utilisateur lors de son authentification
	 * et le mot de passe enregistr� dans la base de donn�es, li� � son identifiant utilisateur.
	 * La m�thode recherche d'abord l'identifiant entr� puis s�lectionne le mot de passe afin de le comparer avec
	 * le mot de passe en param�tre.
	 * @param user l'identifiant de l'utilisateur d�sirant s'authentifier
	 * @param mdp mot de passe entr� par l'utilisateur
	 * @return un bool�en, qui indiquera si oui ou non l'authentification peut s'effectuer. D�pend du r�sultat de la
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
	 * S�lectionne le nom de l'utilisateur dans la base de donn�es, depuis son identifiant.
	 * @param ID identifiant
	 * @return le pr�nom de l'utilisateur
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
	 * M�thode de s�lection du profil d'un utilisateur
	 * @param ID utilisateur dont on recherche le profil
	 * @return l'entier d�signant le profil attribu�
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
	 * M�thode cr�ant un nouvel examen selon les param�tres requis par l'application � l'utilisateur.
	 * Ne renvoie rien, met simplement � jour la base de donn�es.
	 * @throws SQLException
	 */
	public static void newExamen() throws SQLException{
		
		CN = DriverManager.getConnection(URL, USER, MDP);
		PreparedStatement  st = CN.prepareStatement("INSERT�INTO�examen�(idExamen,�titre,�duree,�"
				+ "profSurveillant,�matiere,tempsDebut)"
				+"� VALUES (NULL, ?, ?, ?, ?, ?)");
		System.out.println("Veuillez saisir un titre d'examen : ");
		Scanner t = new Scanner(System.in);
		String x= t.nextLine();
		st.setString(1,x);

		System.out.println("Veuillez saisir une dur�e d'examen ex 02:00:00 : ");
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
	 * M�thode qui met � jour un examen s�lectionn� selon les param�tres d�sir�s.
	 * Il ne faudra rien rentrer pour ne pas modifier l'intitul�.
	 * @throws SQLException
	 */	
	public static void updateExamen() throws SQLException{
		
		System.out.println("Veuillez saisir le titre de l'examen � mettre � jour (ex: 'Math�matiques L2 Info') : ");
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
	 * Met � jour le titre de l'examen
	 * @param titreInit Titre initial de recherche
	 * @param titreModif Titre modifi� � l'issue de la requ�te
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
	 * Met � jour la dur�e d'un examen
	 * @param titre Titre de l'examen recherch�
	 * @param duree Dur�e mise � jour pour la requ�te
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
	 * Met � jour le nom du prof surveillant
	 * @param titre Titre de l'examen recherch�
	 * @param prof Nom du professeur nouvellement assign�
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
	 * Met � jour la mati�re concern� lors de l'examen
	 * @param titre Titre de l'examen recherch�
	 * @param matiere Nom de la mati�re modifi�
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
	 * Met � jour la date et l'heure du d�but d'�preuve
	 * @param titre Titre de l'examen concern�
	 * @param tpsDeb Chaine de caract�res exprimant l'heure et la date de d�but d'�preuve selon la syntaxe de la base de donn�es.
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
