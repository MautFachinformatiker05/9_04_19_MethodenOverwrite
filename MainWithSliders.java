package application;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class MainWithSliders extends Application {

	private DoubleProperty eckigkeit = new SimpleDoubleProperty();			// je größer, desto eckiger die Spirale

	private DoubleProperty feinheit = new SimpleDoubleProperty();			// je kleiner, desto weiter entfernt

	private DoubleProperty stärke = new SimpleDoubleProperty();			 // Stärke des Striches

	private DoubleProperty rotation = new SimpleDoubleProperty();			 // Rotation der Spirale

	int limit = 1000;			// Deklarieren von Variablen
	double startX = 0;
	double startY = 0;
	double endX = 450;
	double endY = 450;
	boolean demo = false;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,900,900,false,SceneAntialiasing.BALANCED);	// erstes Fenster
			primaryStage.setScene(scene);
			Stage newWindow = new Stage();				// neues Fenster
			VBox vbox = new VBox(5);									  // deklarieren von V- und HBoxen
			HBox hbox1 = new HBox(2);
			HBox hbox2 = new HBox(2);
			HBox hbox3 = new HBox(2);
			HBox hbox4 = new HBox(2);
			vbox.setBackground(new Background(new BackgroundFill(Color.rgb(55,55,55),null,null)));

			vbox.getChildren().addAll(hbox1,hbox2,hbox3,hbox4);	// VBox enthält die HBoxen
			Scene scene2 = new Scene(vbox,460,130);
			newWindow.setScene(scene2);
			newWindow.setX(primaryStage.getX() + 700);
			newWindow.setY(primaryStage.getY() + 100);

			eckigkeit.set(0.1);		// Festlegen von Anfangswerten
			feinheit.set(0.5);
			stärke.set(5);
			rotation.set(0);

			Label lb1 = new Label();	// Erstellen der Labels
			Label lb2 = new Label();
			Label lb3 = new Label();
			Label lb4 = new Label();


			Slider sl1 = new Slider(0.01, 2, 0.1);	// Erstellen der Slider
			configure1(hbox1, lb1, sl1);	// Einstellungen für HBox und ihre Label und Slider

			Slider sl2 = new Slider(0.00, 2, 0.5);
			configure2(hbox2, lb2, sl2);

			Slider sl3 = new Slider(0.01, 30, 5);
			configure3(hbox3, lb3, sl3);

			Slider sl4 = new Slider(0.00,6.3,0);
			configure4(hbox4, lb4, sl4);

			eckigkeit.bind(sl1.valueProperty());	// zusammenkleben von Slider und Property
			feinheit.bind(sl2.valueProperty());
			stärke.bind(sl3.valueProperty());
			rotation.bind(sl4.valueProperty());

			newWindow.show();			// rendern des zweiten Fensters
			spiraleBerechnen(root);										// Berechnung der Spirale für erstes Fenster
			primaryStage.show();		// rendern des ersten Fensters

			addListenerToSlider(root, sl1);	// Wenn sich der Wert des Sliders ändert, wird die Spirale neu berechnet
			addListenerToSlider(root, sl2);
			addListenerToSlider(root, sl3);
			addListenerToSlider(root, sl4);


		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void addListenerToSlider(BorderPane root, Slider sl) {
		sl.valueProperty().addListener(new ChangeListener<Number>() {

			public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
				root.getChildren().clear();	// Löschen der vorherigen Spirale
				spiraleBerechnen(root);
			}

		});
	}

	@SuppressWarnings("static-access")
	public void configure4(HBox hbox4, Label lb4, Slider sl4) {
		commonSlider(sl4);
		sl4.setMajorTickUnit(1.575f);
		sl4.setBlockIncrement(0.1f);
		hbox4.setHgrow(sl4, Priority.ALWAYS);
		lb4.setText("Rotation ");
		commonLabel(lb4);
		hbox4.getChildren().addAll(lb4,sl4);
	}

	@SuppressWarnings("static-access")
	public void configure3(HBox hbox3, Label lb3, Slider sl3) {
		commonSlider(sl3);
		sl3.setMajorTickUnit(5f);
		sl3.setBlockIncrement(0.25f);
		hbox3.setHgrow(sl3, Priority.ALWAYS);
		lb3.setText("Stärke   ");
		commonLabel(lb3);
		hbox3.getChildren().addAll(lb3,sl3);
	}

	@SuppressWarnings("static-access")
	public void configure2(HBox hbox2, Label lb2, Slider sl2) {
		commonSlider(sl2);
		sl2.setMajorTickUnit(0.2f);
		sl2.setBlockIncrement(0.005f);
		hbox2.setHgrow(sl2, Priority.ALWAYS);
		lb2.setText("Zoom    ");
		commonLabel(lb2);
		hbox2.getChildren().addAll(lb2,sl2);
	}

	@SuppressWarnings("static-access")
	public void configure1(HBox hbox1, Label lb1, Slider sl1) {
		commonSlider(sl1);
		sl1.setMajorTickUnit(0.2f);
		sl1.setBlockIncrement(0.005f);
		hbox1.setHgrow(sl1, Priority.ALWAYS);
		lb1.setText("Eckigkeit");
		commonLabel(lb1);
		hbox1.getChildren().addAll(lb1,sl1);
	}
	public void commonLabel(Label lb1) {
		lb1.setFont(Font.font(16));
		lb1.setTextFill(Color.WHITE);
		lb1.setPadding(new Insets(0,0,0,3));
	}

	public void commonSlider(Slider sl1) {
		sl1.setShowTickMarks(true);
		sl1.setShowTickLabels(false);
		sl1.setPrefWidth(350);
		sl1.setPadding(new Insets(5,0,0,0));
	}

	public void spiraleBerechnen(BorderPane root) {
		limit = (int) (eckigkeit.get()*1500);
		for (double i = 1; i < limit-1; i+=eckigkeit.get()) {
			startX = endX;
			startY = endY;
			endX = startX + (Math.sin(i+rotation.get())*(i)*feinheit.get());
			endY = startY + (Math.cos(i+rotation.get())*(i)*feinheit.get());

			Line linie = new Line();

			linie.setStrokeWidth(feinheit.get()*stärke.get());
			linie.setStartX(startX);
			linie.setStartY(startY);
			linie.setEndX(endX);
			linie.setEndY(endY);
			root.getChildren().add(linie);
		}
		startX = 0;
		startY = 0;
		endX = 450;
		endY = 450;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
