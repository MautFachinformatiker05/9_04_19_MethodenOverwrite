package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Line;

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
			Line[] punkt = new Line[1441];
			int a = 0;
			for (double i = 0; i < 4 * 2 * Math.PI; i += 2 * Math.PI / 360) {
				dX = Math.sin(i) * radius;
				dY = Math.cos(i) * radius;
				punkt[a] = new Line();
				punkt[a].setStartX(dX + xKor);
				punkt[a].setStartY(dY + yKor);
				punkt[a].setEndX(dX + xKor);
				punkt[a].setEndY(dY + yKor);
				a++;
				radius += 0.1;
			}
			root.getChildren().addAll(punkt);
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
