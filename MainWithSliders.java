package application;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class MainWithSliders extends Application {

	private DoubleProperty eckigkeit = new SimpleDoubleProperty();			// je größer, desto eckiger
    public final double getEckigkeit(){return eckigkeit.get();}
    public final void setEckigkeit(double value){eckigkeit.set(value);}
    public DoubleProperty EckigkeitProperty() {return eckigkeit;}

    private DoubleProperty feinheit = new SimpleDoubleProperty();			// je kleiner, desto feiner
    public final double getFeinheit(){return feinheit.get();}
    public final void setFeinheit(double value){feinheit.set(value);}
    public DoubleProperty FeinheitProperty() {return feinheit;}

    private DoubleProperty stärke = new SimpleDoubleProperty();			 // Stärke des Striches
    public final double getStärke(){return stärke.get();}
    public final void setStärke(double value){stärke.set(value);}
    public DoubleProperty StärkeProperty() {return stärke;}

	int limit = 1000;
	double startX = 0;
	double startY = 0;
	double endX = 450;
	double endY = 450;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,900,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			Stage newWindow = new Stage();
			VBox vbox = new VBox(5);
			HBox hbox1 = new HBox(2);
			HBox hbox2 = new HBox(2);
			HBox hbox3 = new HBox(2);
			Button btn = new Button();
			btn.setText("                                               Rendern!                                                       ");
			vbox.getChildren().addAll(hbox1,hbox2,hbox3,btn);
			Scene scene2 = new Scene(vbox,410,155);
			newWindow.setScene(scene2);
			newWindow.setX(primaryStage.getX() + 700);
            newWindow.setY(primaryStage.getY() + 100);

            setEckigkeit(0.1);
            setFeinheit(0.5);
            setStärke(5);

            Label lb1 = new Label();
            lb1.setText("Eckigkeit");
            Label lb2 = new Label();
            lb2.setText("Zoom    ");
            Label lb3 = new Label();
            lb3.setText("Stärke   ");

            Slider sl1 = new Slider(0.01, 2, 0.1);
            sl1.setShowTickMarks(true);
    		sl1.setShowTickLabels(true);
    		sl1.setMajorTickUnit(0.2f);
    		sl1.setBlockIncrement(0.005f);
    		sl1.setPrefWidth(350);

    		Slider sl2 = new Slider(0.01, 2, 0.5);
            sl2.setShowTickMarks(true);
    		sl2.setShowTickLabels(true);
    		sl2.setMajorTickUnit(0.2f);
    		sl2.setBlockIncrement(0.005f);
    		sl2.setPrefWidth(350);
    		Slider sl3 = new Slider(0.01, 30, 5);
            sl3.setShowTickMarks(true);
    		sl3.setShowTickLabels(true);
    		sl3.setMajorTickUnit(5f);
    		sl3.setBlockIncrement(1f);
    		sl3.setPrefWidth(350);

    		hbox1.getChildren().addAll(lb1,sl1);
    		hbox2.getChildren().addAll(lb2,sl2);
    		hbox3.getChildren().addAll(lb3,sl3);

    		eckigkeit.bind(sl1.valueProperty());
    		feinheit.bind(sl2.valueProperty());
    		stärke.bind(sl3.valueProperty());

            newWindow.show();
            spiraleBerechnen(root);
            primaryStage.show();

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    root.getChildren().clear();
                    spiraleBerechnen(root);
                }
            });

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void spiraleBerechnen(BorderPane root) {
		for (double i = 1; i < limit-1; i+=eckigkeit.get()) {
			startX = endX;
			startY = endY;
			endX = startX + (Math.sin(i)*(i)*feinheit.get());
			endY = startY + (Math.cos(i)*(i)*feinheit.get());

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
