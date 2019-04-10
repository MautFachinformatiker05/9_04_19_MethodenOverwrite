package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 600, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			double xKor = 300;
			double yKor = 300;
			double radius = 40;

			double startX = 0;
			double startY = 0;

			double mitteX = 0;
			double mitteY = 0;

			double endX = 0;
			double endY = 0;

			double inkrement = 0.05;
			double kreisbogen = 2 * Math.PI;
			double radiusInkProUmlauf = 20;
			double schritteProUmlauf = kreisbogen / (3 * inkrement);
			double radiusink = radiusInkProUmlauf / schritteProUmlauf; // 0.65

			startX = Math.sin(0) * radius + xKor;
			startY = Math.cos(0) * radius + yKor;
			int runden = 15;

			for (double i = 0; i < runden * kreisbogen; i += inkrement) {

				QuadCurve q = new QuadCurve();

				q.setStroke(Color.BLACK);
				q.setStartX(startX);
				q.setStartY(startY);

				i += inkrement;

				mitteX = Math.sin(i) * radius + xKor;
				mitteY = Math.cos(i) * radius + yKor;

				q.setControlX(mitteX);
				q.setControlY(mitteY);

				i += inkrement;
				endX = Math.sin(i) * radius + xKor;
				endY = Math.cos(i) * radius + yKor;

				q.setEndX(endX);
				q.setEndY(endY);

				radius += radiusink;

				startX = endX;
				startY = endY;

				root.getChildren().add(q);

			}

			//

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
