package igu.model;

public class HoraClase {
	
	private String diaSemana;
	private int horaDesde, horaHasta;
	
	public HoraClase(String diaSemana, int horaDesde, int horaHasta){
		this.diaSemana = diaSemana;
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public int getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(int horaDesde) {
		this.horaDesde = horaDesde;
	}

	public int getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(int horaHasta) {
		this.horaHasta = horaHasta;
	}
	
}
