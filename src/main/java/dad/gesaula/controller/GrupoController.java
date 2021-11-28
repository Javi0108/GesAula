package dad.gesaula.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.gesaula.model.Grupo;
import javafx.fxml.Initializable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GrupoController implements Initializable {

	// Model
	private StringProperty denominacion = new SimpleStringProperty();
	private ObjectProperty<LocalDate> inicioCurso = new SimpleObjectProperty<>();
	private ObjectProperty<LocalDate> finCurso = new SimpleObjectProperty<>();
	private DoubleProperty examenes = new SimpleDoubleProperty();
	private DoubleProperty practicas = new SimpleDoubleProperty();
	private DoubleProperty actitud = new SimpleDoubleProperty();

	private ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

	@FXML
	private GridPane view;

	@FXML
	private TextField denominacionText;

	@FXML
	private DatePicker finDate;

	@FXML
	private DatePicker inicioDate;

	@FXML
	private Label examenesLabel;

	@FXML
	private Slider examenesSlider;

	@FXML
	private Label practicasLabel;

	@FXML
	private Slider practicasSlider;

	@FXML
	private Label actitudLabel;

	@FXML
	private Slider actitudSlider;

	public GrupoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GrupoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public void initialize(URL location, ResourceBundle resources) {
		denominacionText.textProperty().bindBidirectional(denominacion);
		inicioDate.valueProperty().bindBidirectional(inicioCurso);
		finDate.valueProperty().bindBidirectional(finCurso);
		examenesSlider.valueProperty().bindBidirectional(examenes);
		practicasSlider.valueProperty().bindBidirectional(practicas);
		actitudSlider.valueProperty().bindBidirectional(actitud);
		examenesLabel.textProperty().bind(examenesSlider.valueProperty().asString("%.0f").concat("%"));
		practicasLabel.textProperty().bind(practicasSlider.valueProperty().asString("%.0f").concat("%"));
		actitudLabel.textProperty().bind(actitudSlider.valueProperty().asString("%.0f").concat("%"));

		grupo.addListener((o, ov, nv) -> onGrupoChanged(o, ov, nv));
	}

	private void onGrupoChanged(ObservableValue<? extends Grupo> o, Grupo ov, Grupo nv) {
		if (ov != null) {
			denominacion.unbindBidirectional(ov.denominacionProperty());
			inicioCurso.unbindBidirectional(ov.iniCursoProperty());
			finCurso.unbindBidirectional(ov.finCursoProperty());
			examenes.unbindBidirectional(ov.getPesos().examenesProperty());
			practicas.unbindBidirectional(ov.getPesos().practicasProperty());
			actitud.unbindBidirectional(ov.getPesos().actitudProperty());
		}

		if (nv != null) {
			denominacion.bindBidirectional(nv.denominacionProperty());
			inicioCurso.bindBidirectional(nv.iniCursoProperty());
			finCurso.bindBidirectional(nv.finCursoProperty());
			examenes.bindBidirectional(nv.getPesos().examenesProperty());
			practicas.bindBidirectional(nv.getPesos().practicasProperty());
			actitud.bindBidirectional(nv.getPesos().actitudProperty());
		}
	}

	public ObjectProperty<Grupo> grupoProperty() {
		return this.grupo;
	}

	public ObjectProperty<Grupo> getGrupo() {
		return grupo;
	}

	public void setGrupo(ObjectProperty<Grupo> grupo) {
		this.grupo = grupo;
	}

	public GridPane getView() {
		return view;
	}
}
