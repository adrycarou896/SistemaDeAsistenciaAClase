package igu.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import igu.VentanaAccesosAlumno;

public class AccionBotonCerrarAccesosAlumno implements ActionListener{
	
	private VentanaAccesosAlumno ventanaAccesosAlumno;
	
	public AccionBotonCerrarAccesosAlumno(VentanaAccesosAlumno ventanaPrincipal){
		this.ventanaAccesosAlumno = ventanaPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.ventanaAccesosAlumno.dispose();
	}
}
