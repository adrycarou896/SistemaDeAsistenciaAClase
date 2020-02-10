package igu.acciones;

import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;

import dpigUser.rules.Alert;
import igu.VentanaAccesosAlumno;

public class Actualizar extends Thread{
	
	private VentanaAccesosAlumno va;
	public Actualizar(VentanaAccesosAlumno va){
		this.va = va;
	}
	
	@Override
	public void run(){
		long inicio = System.currentTimeMillis();
		while(true){
			long fin = System.currentTimeMillis();
			long seconds = fin - inicio;
			if(seconds>3){
				update();
				inicio = System.currentTimeMillis();
			}
		}
		
	}
	
	public void update(){
		va.getTableAccesos().setVisible(false);
		va.getTableAccesos().setVisible(true);
		List<Alert> alerts = va.getAlerts();
		for (Alert alert : alerts) {
			if(alert.getEvent().getPerson().equals(va.getAlumno())){
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				va.getLbSemanaActual("").setText(va.getAlumno() + " ha entrado en la clase "+alert.getEvent().getHall()
						+" a una hora superior a la permitida ("+format.format(alert.getDateAlert())+") - Hora de entrada: las "+format.format(alert.getEvent().getAccomplishedDate())+"h");
				va.getLbSemanaActual("").setForeground(Color.RED);
				va.getLbSemanaActual("").setFont(new Font("Tahoma", Font.BOLD, 11));
			}
		}
	
	}

}
