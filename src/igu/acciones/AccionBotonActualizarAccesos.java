package igu.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import igu.VentanaAccesosAlumno;

public class AccionBotonActualizarAccesos implements ActionListener{
	
	private VentanaAccesosAlumno ventanaAccesosAlumno;
	
	public AccionBotonActualizarAccesos(VentanaAccesosAlumno ventanaPrincipal){
		this.ventanaAccesosAlumno = ventanaPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.ventanaAccesosAlumno.getTableAccesos().setVisible(false);
		this.ventanaAccesosAlumno.getTableAccesos().setVisible(true);
	}
}

