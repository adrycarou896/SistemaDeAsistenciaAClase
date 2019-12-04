package igu.persons;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import igu.VentanaPrincipal;
import igu.model.AccesoClase;
import tfg.SocketsClient.Client;

public class PersonsActions extends Client{
	
	private VentanaPrincipal ventanaPrincipal;
	
	public PersonsActions(VentanaPrincipal ventanaPrincipal){
		this.ventanaPrincipal = ventanaPrincipal;
	}
	
	@Override
	public void enter(String personName, Date date, int hall) {
		System.out.println("ENTER: personName: "+personName+", date: "+date.toString()+", hall: "+hall);
		Calendar diaActual= Calendar.getInstance();
		diaActual.set(Calendar.HOUR_OF_DAY, 18);
		diaActual.set(Calendar.MINUTE, 5);
		date = diaActual.getTime();
		this.ventanaPrincipal.getAccesosClase().add(new AccesoClase(personName, date, hall));
		
		if(!this.ventanaPrincipal.getTimes().containsKey(personName)){
			this.ventanaPrincipal.getTimes().put(personName, System.currentTimeMillis());
		}
		
	}

	@Override
	public void out(String personName, Date date, int hall) {
		System.out.println("OUT: personName: "+personName+", date: "+date.toString()+", hall: "+hall);
		if(this.ventanaPrincipal.getTimes().containsKey(personName)){
			this.ventanaPrincipal.getTimes().replace(personName, System.currentTimeMillis()-this.ventanaPrincipal.getTimes().get(personName));
		}
	}

}
