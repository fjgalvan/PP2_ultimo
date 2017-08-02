package app.modelo;

import java.awt.Point;

public abstract class ObjetoGrafico {
	protected Point coordinate;
	protected Point size;
	protected String imagen;
	
	public ObjetoGrafico (Point coordinate, Point size, String imagen){
		this.coordinate = coordinate;
		this.size = size;
		this.imagen = imagen;
	}
	
	public ObjetoGrafico(Point coordinate, Point size){
		this.coordinate = coordinate;
		this.size = size;
	}
	
	public ObjetoGrafico(String imagen){
		this.imagen = imagen;
	}

	public String getImagen() {
		return this.imagen;
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

	public Point getSize() {
		return size;
	}

	public void setSize(Point size) {
		this.size = size;
	}
	
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}