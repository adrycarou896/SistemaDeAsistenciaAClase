package igu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import dpigUser.rules.Alert;
import igu.acciones.AccionBotonActualizarAccesos;
import igu.acciones.AccionBotonCerrarAccesosAlumno;
import igu.acciones.Actualizar;
import igu.model.AccesoClase;
import igu.model.HoraClase;
import igu.table.RowsAccesosRendered;

public class VentanaAccesosAlumno extends JFrame {

	private JPanel contentPane;
	private String alumno;
	private Date fechaLunes;
	private Date fechaViernes;
	private List<HoraClase> horasClase;
	private List<AccesoClase> accesosClase;
	private List<AccesoClase> salidasClase;
	private List<Alert> alerts;
	
	private JPanel panel;
	private JLabel label;
	private JButton btCerrar;
	private JLabel label_1;
	private JLabel label_2;
	
	private VentanaPrincipal ventanaPrincipal;
	private JPanel panel_1;
	private JLabel lbUsuario;
	private JLabel semanaActualLabel;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	
	private JTable tableAccesos;
	private JPanel panel_6;
	private JPanel panel_7;
	private JPanel panel_8;

	/**
	 * Create the frame.
	 */
	public VentanaAccesosAlumno(VentanaPrincipal ventanaPrincipal, String alumno, String semanaActual, List<HoraClase> horasClase, 
	    List<AccesoClase> accesosClase, List<AccesoClase> salidasClase,List<Alert> alerts) {
		setTitle("Sistema de asistencia a clase");
		this.alumno = alumno;
		this.fechaLunes=getFechaLunes(semanaActual);
		this.fechaViernes=getFechaViernes(semanaActual);
		this.horasClase = horasClase;
		this.accesosClase = accesosClase;
		this.salidasClase = salidasClase;
		this.alerts = alerts;
		
		this.ventanaPrincipal = ventanaPrincipal;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 663, 362);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
		contentPane.add(getPanel_1(semanaActual), BorderLayout.NORTH);
		contentPane.add(getPanel_2(), BorderLayout.CENTER);
		setLocationRelativeTo(null);
		
		RowsAccesosRendered rra = new RowsAccesosRendered(this, 0);
		this.tableAccesos.setDefaultRenderer(Object.class, rra);
		this.tableAccesos.setVisible(false);
		this.tableAccesos.setVisible(true);
		
		new Actualizar(this).start();
	}
	
	private Date getFechaLunes(String semanaActual){
		String fechaLunes = semanaActual.split("-")[0];
		return getDate(fechaLunes);
	}
	
	private Date getFechaViernes(String semanaActual){
		String fechaViernes = semanaActual.split("-")[1];
		return getDate(fechaViernes);
	}
	
	private Date getDate(String fecha){
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return format.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			panel.add(getLabel());
			panel.add(getLabel_1());
			panel.add(getLabel_2());
			panel.add(getBtCerrar());
		}
		return panel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
		}
		return label;
	}
	private JButton getBtCerrar() {
		if (btCerrar == null) {
			btCerrar = new JButton("Cerrar");
			btCerrar.addActionListener(new AccionBotonCerrarAccesosAlumno(this));
		}
		return btCerrar;
	}
	private JLabel getLabel_1() {
		if (label_1 == null) {
			label_1 = new JLabel("");
		}
		return label_1;
	}
	private JLabel getLabel_2() {
		if (label_2 == null) {
			label_2 = new JLabel("");
		}
		return label_2;
	}
	
	public VentanaPrincipal getVentanaPrincipal(){
		return this.ventanaPrincipal;
	}
	private JPanel getPanel_1(String semanaActual) {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setLayout(new BorderLayout(0, 0));
			panel_1.add(getLbUsuario(), BorderLayout.WEST);
			panel_1.add(getLbSemanaActual(semanaActual), BorderLayout.SOUTH);
		}
		return panel_1;
	}
	private JLabel getLbUsuario() {
		if (lbUsuario == null) {
			lbUsuario = new JLabel(this.alumno);
			lbUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		}
		return lbUsuario;
	}
	
	public JLabel getLbSemanaActual(String semanaActual) {
		if (semanaActualLabel == null) {
			semanaActualLabel = new JLabel("Semana del "+semanaActual.split("-")[0]+" al "+semanaActual.split("-")[1]);
			semanaActualLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		}
		return semanaActualLabel;
	}
	
	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setLayout(new BorderLayout(0, 0));
			panel_2.add(getPanel_3(), BorderLayout.NORTH);
			panel_2.add(getPanel_4(), BorderLayout.CENTER);
			panel_2.add(getPanel_5(), BorderLayout.SOUTH);
		}
		return panel_2;
	}
	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setLayout(new GridLayout(1, 0, 0, 0));
			panel_3.add(getPanel_8());
			panel_3.add(getPanel_7());
			panel_3.add(getPanel_6());
		}
		return panel_3;
	}
	private JPanel getPanel_4() {
		if (panel_4 == null) {
			String[] columnNames = this.ventanaPrincipal.getColumnsTable();
			Object[][] data = this.ventanaPrincipal.getDataTable();
			tableAccesos = new JTable(data,columnNames);
			JScrollPane scrollPane = new JScrollPane(tableAccesos);
			tableAccesos.setFillsViewportHeight(true);
			panel_4 = new JPanel();
			panel_4.setLayout(new BorderLayout(0, 0));
			panel_4.add(scrollPane);
		}
		return panel_4;
	}
	private JPanel getPanel_5() {
		if (panel_5 == null) {
			panel_5 = new JPanel();
		}
		return panel_5;
	}
	
	public String getAlumno(){
		return this.alumno;
	}
	
	public Date getFechaLunes(){
		return this.fechaLunes;
	}
	
	public Date getFechaViernes(){
		return this.fechaViernes;
	}
	
	public List<AccesoClase> getAccesosClase(){
		return this.accesosClase;
	}
	public List<AccesoClase> getSalidasClase(){
		return this.salidasClase;
	}
	public List<Alert> getAlerts(){
		return this.alerts;
	}
	
	private JPanel getPanel_6() {
		if (panel_6 == null) {
			panel_6 = new JPanel();
		}
		return panel_6;
	}
	private JPanel getPanel_7() {
		if (panel_7 == null) {
			panel_7 = new JPanel();
		}
		return panel_7;
	}
	private JPanel getPanel_8() {
		if (panel_8 == null) {
			panel_8 = new JPanel();
		}
		return panel_8;
	}
	
	public JTable getTableAccesos(){
		return this.tableAccesos;
	}

}
