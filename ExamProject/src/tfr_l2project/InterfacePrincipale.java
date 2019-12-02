package tfr_l2project;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InterfacePrincipale extends Application {
	
	private Stage PRIMARY_STAGE;
	private BorderPane BASE;

	 @Override
	    public void start(Stage primaryStage) {
	        this.PRIMARY_STAGE = primaryStage;
	        this.PRIMARY_STAGE.setTitle("Application");

	        initBase();

	        showInterfacePrincipale();
	    }
	    
	    /**
	     * Initializes the root layout.
	     */
	    public void initBase() {
	        try {
	            // Charge la base depuis le fichier fxml.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(InterfacePrincipale.class.getResource("/tfr_l2project_view/Base.fxml"));
	            BASE = (BorderPane) loader.load();
	            
	            // Montre la scène contenant la base.
	            Scene scene = new Scene(BASE);
	            PRIMARY_STAGE.setScene(scene);
	            PRIMARY_STAGE.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Shows the person overview inside the root layout.
	     */
	    public void showInterfacePrincipale() {
	        try {
	            // Charge l'interface principale.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(InterfacePrincipale.class.getResource("/tfr_l2project_view/Authentification.fxml"));
	            AnchorPane interfacePrincipale = (AnchorPane) loader.load();
	            
	            // Place l'interface principale au coeur de la base.
	            BASE.setCenter(interfacePrincipale);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Returns the main stage.
	     * @return
	     */
	    public Stage getPrimaryStage() {
	        return PRIMARY_STAGE;
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}
