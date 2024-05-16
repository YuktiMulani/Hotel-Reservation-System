package application;
	
import java.io.IOException;
import java.net.Socket;

import controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Inventory Management App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	
	public static void main(String[] args) {
		
        
		launch(args);
	}
}
