package dad.gesaula.model;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@XmlType
public class Alumno {
	private StringProperty nombre;
	private StringProperty apellidos;
	private ObjectProperty<LocalDate> fechaNacimiento;
	private ObjectProperty<Sexo> sexo;
	private BooleanProperty repite;

	public Alumno() {
		nombre = new SimpleStringProperty(this, "nombre");
		apellidos = new SimpleStringProperty(this, "apellidos");
		fechaNacimiento = new SimpleObjectProperty<LocalDate>(this, "fechaNacimiento");
		sexo = new SimpleObjectProperty<Sexo>(this, "sexo");
		repite = new SimpleBooleanProperty(this, "repite", false);
	}

	public StringProperty nombreProperty() {
		return this.nombre;
	}

	@XmlElement
	public String getNombre() {
		return this.nombreProperty().get();
	}

	public void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}

	public StringProperty apellidosProperty() {
		return this.apellidos;
	}

	@XmlElement
	public String getApellidos() {
		return this.apellidosProperty().get();
	}

	public void setApellidos(final String apellidos) {
		this.apellidosProperty().set(apellidos);
	}

	public ObjectProperty<LocalDate> fechaNacimientoProperty() {
		return this.fechaNacimiento;
	}

	@XmlElement
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimientoProperty().get();
	}

	public void setFechaNacimiento(final LocalDate fechaNacimiento) {
		this.fechaNacimientoProperty().set(fechaNacimiento);
	}

	public ObjectProperty<Sexo> sexoProperty() {
		return this.sexo;
	}

	@XmlElement
	public Sexo getSexo() {
		return this.sexoProperty().get();
	}

	public void setSexo(final Sexo sexo) {
		this.sexoProperty().set(sexo);
	}

	public BooleanProperty repiteProperty() {
		return this.repite;
	}

	@XmlElement
	public boolean isRepite() {
		return this.repiteProperty().get();
	}

	public void setRepite(final boolean repite) {
		this.repiteProperty().set(repite);
	}

	@Override
	public String toString() {
		return (getNombre() + " " + getApellidos()).trim();
	}
}
