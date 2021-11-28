package dad.gesaula.model;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

@XmlType
public class Pesos {

	private DoubleProperty actitud;
	private DoubleProperty examenes;
	private DoubleProperty practicas;

	public Pesos() {
		actitud = new SimpleDoubleProperty(this, "actitud", 0.0);
		examenes = new SimpleDoubleProperty(this, "examenes", 0.0);
		practicas = new SimpleDoubleProperty(this, "practicas", 0.0);
	}

	public DoubleProperty actitudProperty() {
		return this.actitud;
	}

	@XmlAttribute
	public double getActitud() {
		return this.actitudProperty().get();
	}

	public void setActitud(final double actitud) {
		this.actitudProperty().set(actitud);
	}

	public DoubleProperty examenesProperty() {
		return this.examenes;
	}

	@XmlAttribute
	public double getExamenes() {
		return this.examenesProperty().get();
	}

	public void setExamenes(final double examenes) {
		this.examenesProperty().set(examenes);
	}

	public DoubleProperty practicasProperty() {
		return this.practicas;
	}

	@XmlAttribute
	public double getPracticas() {
		return this.practicasProperty().get();
	}

	public void setPracticas(final double practicas) {
		this.practicasProperty().set(practicas);
	}

}
