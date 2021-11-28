package dad.gesaula;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.gesaula.controller.AlumnosController;
import dad.gesaula.controller.GrupoController;
import dad.gesaula.model.Grupo;
import javafx.fxml.Initializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {

	// Controllers
	private GrupoController grupoController = new GrupoController();
	private AlumnosController alumnosController = new AlumnosController();

	// Model
	private StringProperty nombreFichero = new SimpleStringProperty();
	private ObjectProperty<Grupo> grupo = new SimpleObjectProperty<>();

	@FXML
	private BorderPane view;

	@FXML
	private Tab alumnosTab;

	@FXML
	private Tab grupoTab;

	@FXML
	private Button guardarButton;

	@FXML
	private TextField nombreFicherotext;

	@FXML
	private Button nuevoButton;

	public MainController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@FXML
	void onGuardarButton(ActionEvent event) {
		String ruta = nombreFichero.get();
		if (ruta != null && !ruta.isEmpty()) {
			try {
				getGrupo().save(new File(ruta));

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(GesAulaApp.primaryStage);
				alert.setTitle("Guardar grupo");
				alert.setHeaderText("Se ha guardado el grupo correctamente.");
				alert.setContentText(
						"El grupo " + getGrupo().getDenominacion() + " se ha guardado en el fichero '" + ruta + "'.");
				alert.showAndWait();

			} catch (Exception ex) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(GesAulaApp.primaryStage);
				alert.setTitle("Guardar grupo");
				alert.setHeaderText("Error al guardar el grupo.");
				alert.setContentText(ex.getMessage());
				alert.showAndWait();
				ex.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(GesAulaApp.primaryStage);
			alert.setTitle("Guardar grupo");
			alert.setHeaderText("Error al guardar el grupo.");
			alert.setContentText("Debe especificar la ruta del fichero donde se guardar� el grupo.");
			alert.showAndWait();
		}
	}

	@FXML
	void onNuevoAction(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.initOwner(GesAulaApp.primaryStage);
		alert.setTitle("Nuevo grupo");
		alert.setHeaderText("Va a crear un grupo nuevo.");
		alert.setContentText("¿Está seguro?");
		Optional<ButtonType> opcion = alert.showAndWait();
		if (opcion.get().equals(ButtonType.OK)) {
			grupo.set(new Grupo());
			nombreFicherotext.setText("");
		}
	}

	public void initialize(URL location, ResourceBundle resources) {
		grupoTab.setContent(grupoController.getView());
		alumnosTab.setContent(alumnosController.getView());
		nombreFichero.bind(nombreFicherotext.textProperty());

		grupoController.grupoProperty().bind(grupo);

		grupo.addListener((o, ov, nv) -> onGrupoChanged(o, ov, nv));

		grupo.set(new Grupo());

	}

	private void onGrupoChanged(ObservableValue<? extends Grupo> o, Grupo ov, Grupo nv) {
		if (ov != null) {
			alumnosController.alumnosProperty().unbind();
		}
		if (nv != null) {
			alumnosController.alumnosProperty().bind(nv.alumnosProperty());
		}
	}

	public ObjectProperty<Grupo> grupoProperty() {
		return this.grupo;
	}

	public Grupo getGrupo() {
		return this.grupoProperty().get();
	}

	public void setGrupo(final Grupo grupo) {
		this.grupoProperty().set(grupo);
	}

	public BorderPane getView() {
		return view;
	}
}
