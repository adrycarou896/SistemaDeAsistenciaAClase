package igu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dpigUser.rules.Alert;
import igu.acciones.AccionBotonAccederAVentanaAlumno;
import igu.acciones.AccionBotonAddHorario;
import igu.model.AccesoClase;
import igu.model.HoraClase;
import igu.persons.PersonsActions;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JPanel panel_1;
	private JLabel lblClaseA;
	private JPanel panel_2;
	private JPanel panel_3;
	private JLabel lblDaDeLa;
	private JComboBox comboDiaSemana;
	private JLabel lblHoraDesde;
	private JComboBox comboHoraDesde;
	private JLabel lblHoraHasta;
	private JComboBox comboHoraHasta;
	private JLabel label;
	private JPanel panel_4;
	private JButton btnAadir;
	private JTable tableHorarios;
	private JPanel panel_5;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;
	
	private String[] daysNames = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes","Sabado","Domingo"};
	
	private List<HoraClase> horasClase = new ArrayList<HoraClase>();
	private List<AccesoClase> accesosClase = new ArrayList<AccesoClase>();
	private List<AccesoClase> salidasClase = new ArrayList<AccesoClase>();
	private List<Alert> alerts = new ArrayList<Alert>();
	private JPanel panel_9;
	private JPanel panel_11;
	private JLabel lblAlumno;
	private JTextField campoAlumno;
	private JButton btVerAccesos;
	private JPanel panel_10;
	private JPanel panel_12;
	
	private Map<String, Long> times = new HashMap<String, Long>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		PersonsActions personsActions = new PersonsActions(this);
		personsActions.startConnectionToDPIGServer(4999);
		
		setTitle("Sistema de asistencia a clase");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 854, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JPanel panel = getPanel();

	}
	
	private JPanel getPanel(){
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		{
			panel_1 = new JPanel();
			panel.add(panel_1, BorderLayout.NORTH);
			panel_1.setLayout(new GridLayout(1, 0, 0, 0));
			{
				lblClaseA = new JLabel("Clase A1");
				lblClaseA.setFont(new Font("Tahoma", Font.BOLD, 21));
				panel_1.add(lblClaseA);
			}
		}
		{
			panel_2 = new JPanel();
			panel.add(panel_2, BorderLayout.CENTER);
			panel_2.setLayout(new BorderLayout(0, 0));
			{
				panel_3 = new JPanel();
				panel_2.add(panel_3, BorderLayout.NORTH);
				panel_3.setLayout(new GridLayout(1, 0, 0, 0));
				{
					lblDaDeLa = new JLabel("D\u00EDa de la semana ");
					lblDaDeLa.setHorizontalAlignment(SwingConstants.RIGHT);
					panel_3.add(lblDaDeLa);
				}
				{
					comboDiaSemana = new JComboBox();
					for (String day : daysNames) {
						comboDiaSemana.addItem(day);
					}
					panel_3.add(comboDiaSemana);
				}
				{
					lblHoraDesde = new JLabel("Hora desde ");
					lblHoraDesde.setHorizontalAlignment(SwingConstants.RIGHT);
					panel_3.add(lblHoraDesde);
				}
				{
					comboHoraDesde = new JComboBox();
					for (int i = 9; i <= 23; i++) {
						comboHoraDesde.addItem(i+":00");
					}
					panel_3.add(comboHoraDesde);
				}
				{
					lblHoraHasta = new JLabel("Hora hasta ");
					lblHoraHasta.setHorizontalAlignment(SwingConstants.RIGHT);
					panel_3.add(lblHoraHasta);
				}
				{
					comboHoraHasta = new JComboBox();
					for (int i = 9; i <= 23; i++) {
						comboHoraHasta.addItem(i+":00");
					}
					panel_3.add(comboHoraHasta);
				}
				{
					label = new JLabel("");
					panel_3.add(label);
				}
				{
					btnAadir = new JButton("A\u00F1adir +");
					btnAadir.addActionListener(new AccionBotonAddHorario(this));
					panel_3.add(btnAadir);
				}
			}
			{
				panel_4 = new JPanel();
				panel_2.add(panel_4, BorderLayout.CENTER);
				panel_4.setLayout(new BorderLayout(0, 0));
				{
					String[] columnNames = getColumnsTable();
					Object[][] data = getDataTable();
					
					tableHorarios = new JTable(data,columnNames);
					JScrollPane scrollPane = new JScrollPane(tableHorarios);
					tableHorarios.setFillsViewportHeight(true);
					panel_4.add(scrollPane, BorderLayout.CENTER);
				}
				{
					panel_5 = new JPanel();
					panel_4.add(panel_5, BorderLayout.NORTH);
				}
				{
					panel_6 = new JPanel();
					panel_4.add(panel_6, BorderLayout.SOUTH);
				}
				{
					panel_7 = new JPanel();
					panel_4.add(panel_7, BorderLayout.EAST);
				}
				{
					panel_8 = new JPanel();
					panel_4.add(panel_8, BorderLayout.WEST);
				}
			}
		}
		{
			panel_9 = new JPanel();
			panel.add(panel_9, BorderLayout.SOUTH);
			panel_9.setLayout(new GridLayout(1, 0, 0, 0));
			{
				panel_11 = new JPanel();
				panel_9.add(panel_11);
				panel_11.setLayout(new GridLayout(1, 0, 0, 0));
				{
					panel_12 = new JPanel();
					panel_11.add(panel_12);
				}
				{
					panel_10 = new JPanel();
					panel_11.add(panel_10);
				}
				{
					lblAlumno = new JLabel("Alumno: ");
					lblAlumno.setHorizontalAlignment(SwingConstants.RIGHT);
					panel_11.add(lblAlumno);
				}
				{
					campoAlumno = new JTextField();
					panel_11.add(campoAlumno);
					campoAlumno.setColumns(10);
				}
				{
					btVerAccesos = new JButton("Ver accesos");
					btVerAccesos.addActionListener(new AccionBotonAccederAVentanaAlumno(this));
					panel_11.add(btVerAccesos);
				}
			}
		}
		return panel;
	}
	
	public String[] getColumnsTable(){
		String[] columnNames = new String[this.daysNames.length+1];
		columnNames[0] = "Hora";
		for (int i = 0; i < this.daysNames.length; i++) {
			columnNames[i+1] = this.daysNames[i];
		}
		return columnNames;
	}
	
	public Object[][] getDataTable(){
		Object[][] data = new Object[23-9][this.daysNames.length+1];
		for (int i = 9; i < 23; i++) {
			String rangoHorario = i+":00-" + (i+1)+":00";
			for (int j = 0; j < (this.daysNames.length+1); j++) {
				if(j==0){
					data[i-9][j] =  rangoHorario;
				}
				else{
					data[i-9][j] = "";
				}
			}
		}
		return data;
	}
	
	public List<HoraClase> getHorasClase(){
		return this.horasClase;
	}
	
	public JComboBox getComboDiaSemana(){
		return this.comboDiaSemana;
	}
	
	public JComboBox getComboHoraDesde(){
		return this.comboHoraDesde;
	}
	
	public JComboBox getComboHoraHasta(){
		return this.comboHoraHasta;
	}
	
	public JTextField getCampoAlumno(){
		return this.campoAlumno;
	}
	
	public JTable getTableHorarios(){
		return this.tableHorarios;
	}
	
	public String[] getDaysNames(){
		return this.daysNames;
	}
	
	public String getSemanaActual(){
		Calendar diaActual= Calendar.getInstance();
		int diaSemana = diaActual.get(Calendar.DAY_OF_WEEK);
		if(diaSemana==1){
			diaSemana = 8;
		}
		
		LocalDateTime actualDate = LocalDateTime.now();
		
		LocalDateTime lunesDate = actualDate.minusDays(diaSemana-2);
		LocalDateTime viernesDate = actualDate.plusDays(8-diaSemana);
		
		DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String lunesActual = formmat1.format(lunesDate);
		String viernesActual = formmat1.format(viernesDate);
		String semanaActual = lunesActual +"-" + viernesActual;
		
		return semanaActual;
		/*
		int lunesDinamico = (diaSemana-2) + 7;
		int domingoDinamico = (diaSemana-2) + 1;
		for (int i = 0; i < 5; i++) {
			LocalDateTime lunesAnteriorDate = actualDate.minusDays(lunesDinamico);
			LocalDateTime domingoAnteriorDate = actualDate.minusDays(domingoDinamico);
			String lunesAnterior = formmat1.format(lunesAnteriorDate);
			String domingoAnterior = formmat1.format(domingoAnteriorDate);
			String semanaAnterior = lunesAnterior +"-" + domingoAnterior;
			semanas.add(semanaAnterior);
			
			domingoDinamico = lunesDinamico + 1;
			lunesDinamico = lunesDinamico + 7;
			
		}
		*/
	}
	
	public List<AccesoClase> getAccesosClase(){
		return this.accesosClase;
	}
	
	public List<AccesoClase> getSalidasClase(){
		return this.salidasClase;
	}
	
	public Map<String, Long> getTimes(){
		return this.times;
	}
	
	public List<Alert> getAlerts(){
		return this.alerts;
	}

}
