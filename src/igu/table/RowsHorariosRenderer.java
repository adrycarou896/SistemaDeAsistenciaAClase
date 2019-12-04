package igu.table;

import java.awt.Color;
import java.awt.Component;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import igu.VentanaPrincipal;
import igu.model.HoraClase;

public class RowsHorariosRenderer extends DefaultTableCellRenderer {
	private int columna ;//Indica la columna a que se le aplica
	
	private VentanaPrincipal ventanaPrincipal;
	
	public RowsHorariosRenderer(int Colpatron, VentanaPrincipal ventanaPrincipal)
	{
	    this.columna = Colpatron;
	    this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
		super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		if(column!=this.columna){
			String horarioCelda = table.getValueAt(row, this.columna).toString();
			String[] horarioCeldaArray = horarioCelda.split("-");
			if(horarioCeldaArray.length>1){
				int horaInicioCelda = Integer.parseInt(horarioCeldaArray[0].split(":")[0]);
				int horaFinCelda = Integer.parseInt(horarioCeldaArray[1].split(":")[0]);
				
				List<HoraClase> horasClase = this.ventanaPrincipal.getHorasClase();
				for (HoraClase horaClase : horasClase) {
					int diaSemana = getNumberDiaSemana(horaClase.getDiaSemana());
					int horaDesde = horaClase.getHoraDesde();
					int horaHasta = horaClase.getHoraHasta();
					if(diaSemana==column){
						if(horaInicioCelda>=horaDesde && horaFinCelda<=horaHasta){
							this.setBackground(Color.LIGHT_GRAY);
							return this;
						}
					}
					
				}
			}
		}
		this.setBackground(Color.WHITE);
		return this;
	}
	
	private int getNumberDiaSemana(String diaSemana){
		String[] dayNames = this.ventanaPrincipal.getDaysNames();
		for (int i = 0; i < dayNames.length; i++) {
			String day = dayNames[i];
			if(day.equals(diaSemana)){
				return i+1;
			}
		}
		return -1;
	}

}
