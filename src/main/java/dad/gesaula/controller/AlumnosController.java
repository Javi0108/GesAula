package dad.gesaula.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.GesAulaApp;
import dad.gesaula.model.Alumno;
import javafx.fxml.Initializable;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.util.converter.LocalDateStringConverter;

public class AlumnosController implements Initializable {

	// Controller
	private ModificarAlumnoController modificarAlumnoController = new ModificarAlumnoController();

	// Model
	private ListProperty<Alumno> alumnos = new SimpleListProperty<Alumno>(FXCollections.observableArrayList());
	private ObjectProperty<Alumno> seleccionado = new SimpleObjectProperty<Alumno>();

	@FXML
	private SplitPane view;

	@FXML
	private TableView<Alumno> alumnoTable;

	@FXML
	private TableColumn<Alumno, String> apellidosColumn;

	@FXML
	private Button eliminarButton;

	@FXML
	private TableColumn<Alumno, LocalDate> fechaNacimientoColumn;

	@FXML
	private TableColumn<Alumno, String> nombreColumn;

	@FXML
	private Button nuevoButton;

	@FXML
	private VBox noAlumnoPane;

	@FXML
	private BorderPane rightPane;

	public AlumnosController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnoView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@FXML
	void onEliminarAction(ActionEvent event) {
		Alumno seleccionado = alumnoTable.getSelectionModel().getSelectedItem();
		if (seleccionado == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(GesAulaApp.primaryStage);
			alert.setTitle("Eliminar alumno");
			alert.setHeaderText("Error al intentar eliminar un alumno.");
			alert.setContentText("No se ha seleccionado ningún alumno.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.initOwner(GesAulaApp.primaryStage);
			alert.setTitle("Eliminar alumno");
			alert.setHeaderText("Se va a eliminar al alumno '" + seleccionado + "'.");
			alert.setContentText("¿Está seguro?");
			Optional<ButtonType> opcion = alert.showAndWait();
			if (opcion.get().equals(ButtonType.OK)) {
				this.alumnos.remove(seleccionado);
			}
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		Alumno nuevo = new Alumno();
		nuevo.setNombre("Sin nombre");
		nuevo.setApellidos("Sin apellidos");
		this.alumnos.add(nuevo);
	}

	public void initialize(URL location, ResourceBundle resources) {
		// Bindeos
		alumnoTable.itemsProperty().bind(alumnos);
		seleccionado.bind(alumnoTable.getSelectionModel().selectedItemProperty());

		modificarAlumnoController.alumnoProperty().bind(seleccionado);
		// Cell factories
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		apellidosColumn.setCellValueFactory(v -> v.getValue().apellidosProperty());
		fechaNacimientoColumn.setCellValueFactory(v -> v.getValue().fechaNacimientoProperty());

		// Para convertir la fecha en un string
		fechaNacimientoColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));

		// Listener
		seleccionado.addListener((o, ov, nv) -> onSelecionadoChanged(o, ov, nv));
	}

	private void onSelecionadoChanged(ObservableValue<? extends Alumno> o, Alumno ov, Alumno nv) {
		if (nv != null) {
			rightPane.setCenter(modificarAlumnoController.getView());
		} else {
			rightPane.setCenter(noAlumnoPane);
		}
	}

	public ListProperty<Alumno> alumnosProperty() {
		return this.alumnos;
	}

	public ObservableList<Alumno> getAlumnos() {
		return this.alumnosProperty().get();
	}

	public void setAlumnos(final ObservableList<Alumno> alumnos) {
		this.alumnosProperty().set(alumnos);
	}

	public SplitPane getView() {
		return view;
	}

}
