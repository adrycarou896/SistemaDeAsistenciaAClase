package igu.table;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import igu.VentanaAccesosAlumno;
import igu.model.AccesoClase;
import igu.model.HoraClase;

public class RowsAccesosRendered extends DefaultTableCellRenderer{
	
	private VentanaAccesosAlumno ventanaAccesosAlumno;
	
	private int columna;
	
	public RowsAccesosRendered(VentanaAccesosAlumno ventanaAccesosAlumno, int columna){
		this.ventanaAccesosAlumno = ventanaAccesosAlumno;
		this.columna = columna;
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
				
				String alumnoName = this.ventanaAccesosAlumno.getAlumno();
				
				List<AccesoClase> accesosClase = this.ventanaAccesosAlumno.getAccesosClase();
				List<HoraClase> horasClase = this.ventanaAccesosAlumno.getVentanaPrincipal().getHorasClase();
				
						
				for (HoraClase horaClase : horasClase) {
					int dia = getNumDiaSemana(horaClase.getDiaSemana());
					int horaDesde = horaClase.getHoraDesde();
					int horaHasta = horaClase.getHoraHasta();
					
					if(column==dia){
						if(horaInicioCelda>=horaDesde && horaFinCelda<=horaHasta){
							for (AccesoClase accesoClase : accesosClase) {
								if(accesoClase.getNameReconocido().equals(alumnoName)){
									Date fechaReconocido = accesoClase.getDateReconocido();
									DateFormat hourFormat = new SimpleDateFormat("HH");
									int horaReconocido = Integer.parseInt(hourFormat.format(fechaReconocido));		
									int diaSemanaReconocido = getDiaSemana(convertToLocalDateViaInstant(fechaReconocido));	
									
									if(dia==diaSemanaReconocido && horaReconocido>=horaDesde && horaReconocido<=horaHasta){
										this.setBackground(Color.GREEN);
										this.setToolTipText("into class: "+this.ventanaAccesosAlumno.getVentanaPrincipal().getTimes().get(alumnoName));
										return this;
									}	
								}
							}
							this.setBackground(Color.RED);
							return this;
						}
					}
				}
			}
		}
		
		this.setBackground(Color.WHITE);
		return this;
	}
	
	private int getNumDiaSemana(String dia){
		String[] days = this.ventanaAccesosAlumno.getVentanaPrincipal().getDaysNames();
		for (int i = 0; i < days.length; i++) {
			if(days[i].toLowerCase().equals(dia.toLowerCase())){
				return i+1;
			}
		}
		return -1;
	}
	
	private int getDiaSemana(LocalDate fechaReconocido){
		LocalDate fechaLunes = convertToLocalDateViaInstant(this.ventanaAccesosAlumno.getFechaLunes());
		LocalDate fechaViernes =convertToLocalDateViaInstant(this.ventanaAccesosAlumno.getFechaViernes());
		
		if((fechaReconocido.isAfter(fechaLunes) || fechaReconocido.compareTo(fechaLunes)==0) && (fechaReconocido.isBefore(fechaViernes) || fechaReconocido.compareTo(fechaViernes)==0)){
			int diaSemana = 1;
			LocalDate fechaEscogida = fechaLunes;
			while(fechaReconocido.compareTo(fechaEscogida)!=0 && fechaEscogida.compareTo(fechaViernes)!=0){
				fechaEscogida = fechaEscogida.plusDays(1);
				diaSemana++;
			}
			
			if(fechaReconocido.compareTo(fechaEscogida)==0){
				return diaSemana;
			}
		}
		return -1;
	}
	
	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
