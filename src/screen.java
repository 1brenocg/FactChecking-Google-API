import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class screen extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		 AnchorPane root = FXMLLoader.load(this.getClass().getResource("mainScreen.fxml"));
		 
		 Scene scene = new Scene(root);
		 
		 primaryStage.setTitle("Fact Checking");
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
}
