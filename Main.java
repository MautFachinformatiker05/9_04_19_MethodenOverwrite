package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			double dX = 0;
			double dY = 0;
			double xKor = 200;
			double yKor = 200;
			double radius = 30;
			Path pfad = new Path();
			pfad.getElements().add(new MoveTo(xKor + Math.sin(0) * radius, yKor + Math.cos(0) * radius));
			for (double i = 0; i < 4 * 2 * Math.PI; i += 2 * Math.PI / 360) {
				dX = Math.sin(i) * radius;
				dY = Math.cos(i) * radius;
				pfad.getElements().add(new LineTo(dX + xKor, dY + yKor));
				radius += 0.1;
			}
			root.getChildren().add(pfad);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
