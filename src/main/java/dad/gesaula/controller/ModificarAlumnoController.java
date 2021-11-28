package dad.gesaula.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.gesaula.model.Alumno;
import dad.gesaula.model.Sexo;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ModificarAlumnoController implements Initializable {

	private StringProperty nombre = new SimpleStringProperty();
	private StringProperty apellidos = new SimpleStringProperty();
	private ObjectProperty<LocalDate> fechaNacimiento = new SimpleObjectProperty<LocalDate>();
	private ObjectProperty<Sexo> sexo = new SimpleObjectProperty<Sexo>();
	private BooleanProperty repite = new SimpleBooleanProperty();

	private ObjectProperty<Alumno> alumno = new SimpleObjectProperty<Alumno>();

	@FXML
	private GridPane view;

	@FXML
	private TextField nombreText;

	@FXML
	private TextField apellidosText;

	@FXML
	private DatePicker fechaNacimientoDate;

	@FXML
	private ComboBox<Sexo> sexoCombo;

	@FXML
	private CheckBox repiteCheck;

	public ModificarAlumnoController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ModificarAlumnoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Bindeos
		nombreText.textProperty().bindBidirectional(nombre);
		apellidosText.textProperty().bindBidirectional(apellidos);
		fechaNacimientoDate.valueProperty().bindBidirectional(fechaNacimiento);
		sexoCombo.valueProperty().bindBidirectional(sexo);
		repiteCheck.selectedProperty().bindBidirectional(repite);

		// Añadir los items al ComboBox
		sexoCombo.getItems().addAll(Sexo.values());

		// Listener
		alumno.addListener((o, ov, nv) -> onAlumnoChanged(o, ov, nv));
	}

	private void onAlumnoChanged(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
		if (ov != null) {
			nombre.unbindBidirectional(ov.nombreProperty());
			apellidos.unbindBidirectional(ov.apellidosProperty());
			fechaNacimiento.unbindBidirectional(ov.fechaNacimientoProperty());
			sexo.unbindBidirectional(ov.sexoProperty());
			repite.unbindBidirectional(ov.repiteProperty());
		}
		if (nv != null) {
			nombre.bindBidirectional(nv.nombreProperty());
			apellidos.bindBidirectional(nv.apellidosProperty());
			fechaNacimiento.bindBidirectional(nv.fechaNacimientoProperty());
			sexo.bindBidirectional(nv.sexoProperty());
			repite.bindBidirectional(nv.repiteProperty());
		}
	}

	public ObjectProperty<Alumno> alumnoProperty() {
		return this.alumno;
	}

	public ObjectProperty<Alumno> getAlumno() {
		return alumno;
	}

	public void setAlumno(ObjectProperty<Alumno> alumno) {
		this.alumno = alumno;
	}

	public GridPane getView() {
		return view;
	}
}
