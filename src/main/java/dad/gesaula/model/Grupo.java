package dad.gesaula.model;

import java.io.File;
import java.time.LocalDate;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlType
@XmlRootElement
public class Grupo {
	private StringProperty denominacion;
	private ObjectProperty<LocalDate> iniCurso;
	private ObjectProperty<LocalDate> finCurso;
	private ObjectProperty<Pesos> pesos;
	private ListProperty<Alumno> alumnos;

	public Grupo() {
		denominacion = new SimpleStringProperty(this, "denominacion");
		iniCurso = new SimpleObjectProperty<>(this, "iniCurso");
		finCurso = new SimpleObjectProperty<>(this, "finCurso");
		pesos = new SimpleObjectProperty<>(this, "pesos", new Pesos());
		alumnos = new SimpleListProperty<>(this, "alumnos", FXCollections.observableArrayList());
	}
	
	public void save(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Grupo.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(this, file);
	}

	public static Grupo read(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Grupo.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return (Grupo) unmarshaller.unmarshal(file);
	}

	public StringProperty denominacionProperty() {
		return this.denominacion;
	}

	@XmlAttribute
	public String getDenominacion() {
		return this.denominacionProperty().get();
	}

	public void setDenominacion(final String denominacion) {
		this.denominacionProperty().set(denominacion);
	}

	public ObjectProperty<LocalDate> iniCursoProperty() {
		return this.iniCurso;
	}

	@XmlElement
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getIniCurso() {
		return this.iniCursoProperty().get();
	}

	public void setIniCurso(final LocalDate iniCurso) {
		this.iniCursoProperty().set(iniCurso);
	}

	public ObjectProperty<LocalDate> finCursoProperty() {
		return this.finCurso;
	}

	@XmlElement
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	public LocalDate getFinCurso() {
		return this.finCursoProperty().get();
	}

	public void setFinCurso(final LocalDate finCurso) {
		this.finCursoProperty().set(finCurso);
	}

	public ObjectProperty<Pesos> pesosProperty() {
		return this.pesos;
	}

	@XmlElement
	public Pesos getPesos() {
		return this.pesosProperty().get();
	}

	public void setPesos(final Pesos pesos) {
		this.pesosProperty().set(pesos);
	}

	public ListProperty<Alumno> alumnosProperty() {
		return this.alumnos;
	}

	@XmlElement
	public ObservableList<Alumno> getAlumnos() {
		return this.alumnosProperty().get();
	}

	public void setAlumnos(final ObservableList<Alumno> alumnos) {
		this.alumnosProperty().set(alumnos);
	}
}
