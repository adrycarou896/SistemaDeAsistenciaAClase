package igu.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import igu.VentanaPrincipal;
import igu.model.HoraClase;
import igu.table.RowsHorariosRenderer;

public class AccionBotonAddHorario implements ActionListener{
	
	private VentanaPrincipal ventanaPrincipal;
	
	public AccionBotonAddHorario(VentanaPrincipal ventanaPrincipal){
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String diaSemana = this.ventanaPrincipal.getComboDiaSemana().getSelectedItem().toString();
		int horaDesde = Integer.parseInt(this.ventanaPrincipal.getComboHoraDesde().getSelectedItem().toString().split(":")[0]);
		int horaHasta = Integer.parseInt(this.ventanaPrincipal.getComboHoraHasta().getSelectedItem().toString().split(":")[0]);
		HoraClase horaClase = new HoraClase(diaSemana, horaDesde, horaHasta);
		this.ventanaPrincipal.getHorasClase().add(horaClase);
		
		RowsHorariosRenderer rr = new RowsHorariosRenderer(0, this.ventanaPrincipal);
		this.ventanaPrincipal.getTableHorarios().setDefaultRenderer(Object.class, rr);
		this.ventanaPrincipal.getTableHorarios().setVisible(false);
		this.ventanaPrincipal.getTableHorarios().setVisible(true);
	}

}
