package igu.acciones;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import igu.VentanaAccesosAlumno;
import igu.VentanaPrincipal;
import igu.model.AccesoClase;
import igu.model.HoraClase;

public class AccionBotonAccederAVentanaAlumno implements ActionListener{
	
	private VentanaPrincipal ventanaPrincipal;
	
	public AccionBotonAccederAVentanaAlumno(VentanaPrincipal ventanaPrincipal){
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String alumno = this.ventanaPrincipal.getCampoAlumno().getText();
		String semanaActual = this.ventanaPrincipal.getSemanaActual();
		List<HoraClase> horasClase = this.ventanaPrincipal.getHorasClase();
		List<AccesoClase> accesosClase = this.ventanaPrincipal.getAccesosClase();
		new VentanaAccesosAlumno(ventanaPrincipal,alumno, semanaActual, horasClase, accesosClase).setVisible(true);
	}

}
