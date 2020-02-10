package igu.persons;

import dpigUser.DPIGTemplateUser;
import dpigUser.rules.Alert;
import dpigUser.rules.Event;
import igu.VentanaPrincipal;
import igu.model.AccesoClase;

public class PersonsActions extends DPIGTemplateUser{
	
	private VentanaPrincipal ventanaPrincipal;
	
	public PersonsActions(VentanaPrincipal ventanaPrincipal){
		this.ventanaPrincipal = ventanaPrincipal;
	}

	@Override
	public void alertMax(Alert alert) {
		System.out.println("ALERT max: "+alert.getDateAlert().toString()+", "+alert.getEvent().getAction()+", "+alert.getEvent().getHall()+", "
				+alert.getEvent().getPerson()+", "+alert.getEvent().getAccomplishedDate().toString());
		this.ventanaPrincipal.getAlerts().add(alert);
	}

	@Override
	public void alertMin(Alert arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enter(Event event) {
		System.out.println("ENTER: personName: "+event.getPerson()+", date: "+event.getAccomplishedDate()+", hall: "+event.getHall());
		/*Calendar diaActual= Calendar.getInstance();
		diaActual.set(Calendar.HOUR_OF_DAY, 18);
		diaActual.set(Calendar.MINUTE, 5);
		date = diaActual.getTime();*/
		this.ventanaPrincipal.getAccesosClase().add(new AccesoClase(event.getPerson(), event.getAccomplishedDate(), event.getHall()));
		
		if(!this.ventanaPrincipal.getTimes().containsKey(event.getPerson())){
			this.ventanaPrincipal.getTimes().put(event.getPerson(), System.currentTimeMillis());
		}
	}

	@Override
	public void in(Event event) {
		System.out.println("IN: personName: "+event.getPerson()+", date: "+event.getAccomplishedDate()+", hall: "+event.getHall());
	}

	@Override
	public void out(Event event) {
		System.out.println("OUT: personName: "+event.getPerson()+", date: "+event.getAccomplishedDate()+", hall: "+event.getHall());
		if(this.ventanaPrincipal.getTimes().containsKey(event.getPerson())){
			this.ventanaPrincipal.getSalidasClase().add((new AccesoClase(event.getPerson(), event.getAccomplishedDate(), event.getHall())));
			this.ventanaPrincipal.getTimes().replace(event.getPerson(), System.currentTimeMillis()-this.ventanaPrincipal.getTimes().get(event.getPerson()));
		}
		
	}

}
