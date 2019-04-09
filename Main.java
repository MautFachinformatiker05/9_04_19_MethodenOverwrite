package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);

			double eckigkeit = 0.1; // je größer, desto eckiger
			double feinheit = 0.1; // je kleiner, desto feiner
			final int limit = 10000;
			double startX = 0;
			double startY = 0;
			double endX = 450;
			double endY = 450;

			for (double i = 1; i < limit-1; i+=eckigkeit) {
				startX = endX;
				startY = endY;
				endX = startX + (Math.sin(i)*(i)*feinheit);
				endY = startY + (Math.cos(i)*(i)*feinheit);

				Line linie = new Line();

				linie.setStrokeWidth(feinheit*20);
				linie.setStartX(startX);
				linie.setStartY(startY);
				linie.setEndX(endX);
				linie.setEndY(endY);
				root.getChildren().add(linie);
			}
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
