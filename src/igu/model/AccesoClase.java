package igu.model;

import java.util.Date;

public class AccesoClase {
	
	private String nameReconocido;
	private Date dateReconocido;
	private int hallReconocido;
	
	public AccesoClase(String nameReconocido, Date dateReconocido, int hallReconocido){
		this.nameReconocido = nameReconocido;
		this.dateReconocido = dateReconocido;
		this.hallReconocido = hallReconocido;
	}

	public String getNameReconocido() {
		return nameReconocido;
	}

	public void setNameReconocido(String nameReconocido) {
		this.nameReconocido = nameReconocido;
	}

	public Date getDateReconocido() {
		return dateReconocido;
	}

	public void setDateReconocido(Date dateReconocido) {
		this.dateReconocido = dateReconocido;
	}

	public int getHallReconocido() {
		return hallReconocido;
	}

	public void setHallReconocido(int hallReconocido) {
		this.hallReconocido = hallReconocido;
	}
	
	
}
