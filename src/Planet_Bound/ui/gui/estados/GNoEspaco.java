package Planet_Bound.ui.gui.estados;

import Planet_Bound.logica.ShipObservavel;
import Planet_Bound.logica.estados.EstadoJogo;
import Planet_Bound.ui.gui.Helper;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class GNoEspaco extends VBox {

    final ShipObservavel modelo;
    Circle planeta;

    Button next;
    StackPane spaceStation;
    Button conversor;

    public GNoEspaco(ShipObservavel model) {
        modelo = model;
        organiza();
        modelo.addPropertyChangeListener(e -> atualizaVista());
    }

    private void organiza() {
        setUpNext();
        setUpSpaceStation();
        setUpPlaneta();
        setUpConversor();

        var mid = new HBox();
        mid.getChildren().addAll(planeta, next);
        mid.setSpacing(60);
        mid.setAlignment(Pos.CENTER);
        getChildren().addAll(conversor, mid, spaceStation);
        atualizaVista();
    }

    private void setUpConversor() {
        conversor = new Button("Conversor\nde\nRecursos");
        conversor.setOnAction(e -> modelo.convertResources());
    }

    private void setUpPlaneta() {
        planeta = new Circle();
        planeta.setRadius(50);
        planeta.setCenterX(0);
        planeta.setCenterY(0);
        //planeta = new Button();
        planeta.setOnMouseClicked(e -> modelo.landOnThePlanet());
    }

    private void setUpNext() {
        next = new Button("->");
        next.setOnAction(e -> modelo.proximoPlaneta());
        // spaceStation.setVisible(!spaceStation.isVisible()));
        modelo.proximoPlaneta();
    }

    private void setUpSpaceStation() {
        Text spaceStationText = new Text("Estação\nEspacial");
        spaceStationText.setFont(Font.font(10));
        var spaceStationForm = new Ellipse();
        spaceStationForm.setRadiusX(50);
        spaceStationForm.setRadiusY(10);
        spaceStationForm.setFill(Color.GRAY);
        spaceStation = new StackPane();
        spaceStation.setVisible(true);
        spaceStation.setOnMouseClicked(e -> modelo.landOnSpaceStation());
        setAlignment(Pos.CENTER);
        setSpacing(10);
        spaceStation.getChildren().addAll(spaceStationForm, spaceStationText);
    }

    private void atualizaVista() {
        if (modelo.getEstadoJogo() == EstadoJogo.NoEspaco) {
            planeta.setFill(Helper.Type2Color(modelo.getPlanetType()));
            spaceStation.setVisible(modelo.hasSpaceStation());
            setVisible(true);
        } else
            setVisible(false);
    }
}
