package visual;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaComprimir extends JDialog{
	
	private final String esperando = "Esperando";
	private final String procesando = "Procesando";
	private final String finalizado = "Finalizado";
	
	private final String p1 = "Leyendo archivo: ";
	private final String p2 = "Armando arbol: ";
	private final String p3 = "Armando tabla: ";
	private final String p4 = "Codificando: ";
	
	private JPanel resumen;
	private JLabel razon,factor,tipo;
	private JLabel pro1, pro2, pro3, pro4;
	private JLabel res1, res2, res3, res4;
	private String tipoString= "";
	
	public VentanaComprimir(JFrame ventana) {
		super(ventana);
		this.setSize(300, 250);
		this.setResizable(false);
		this.setTitle("Compresión");
		this.setLocationRelativeTo(ventana);
		this.setModal(true);
		this.setLayout(null);
		
		resumen=new JPanel();
		resumen.setLayout(new BoxLayout(resumen, BoxLayout.Y_AXIS));
		resumen.setSize(250,100);
		resumen.setBorder(BorderFactory.createTitledBorder("Resumen"));
		resumen.setLocation(25, 100);
		
		//evento condificación
		pro1 = new JLabel(p1);
		pro2 = new JLabel(p2);
		pro3 = new JLabel(p3);
		pro4 = new JLabel(p4);
		
		pro1.setBounds(25,10,100,20);
		pro2.setBounds(25,30,90,20);
		pro3.setBounds(25,50,90,20);
		pro4.setBounds(25,70,73,20);
		
		res1 = new JLabel(esperando);
		res2 = new JLabel(esperando);
		res3 = new JLabel(esperando);
		res4 = new JLabel(esperando);
		
		res1.setBounds(pro1.getBounds().x+pro1.getBounds().width + 5, 10,150,20);
		res2.setBounds(pro2.getBounds().x+pro2.getBounds().width + 5, 30,150,20);
		res3.setBounds(pro3.getBounds().x+pro3.getBounds().width + 5, 50,150,20);
		res4.setBounds(pro4.getBounds().x+pro4.getBounds().width + 5, 70,150,20);
		
		
		//evento finalizado
		
		razon= new JLabel("Razon de compresión:");
		factor = new JLabel("Factor de compresion:");
		tipo= new JLabel("Tipo de archivo:");
		
		add(pro1);
		add(pro2);
		add(pro3);
		add(pro4);
		
		add(res1);
		add(res2);
		add(res3);
		add(res4);
		
		resumen.add(razon);
		resumen.add(factor);
		resumen.add(tipo);
		
		
		this.add(resumen);
	}
	public void actualizar(int numEl, boolean term){
		Color verde = new Color(96,159,32);
		switch (numEl) {
		case 1:
			if(term){
				res1.setText(finalizado);
				res1.setForeground(verde);
			}else{
				res1.setText(procesando);
			}
			break;
		case 2:
			if(term){
				res2.setText(finalizado);
				res2.setForeground(verde);
			}else{
				res2.setText(procesando);
			}
			break;
		case 3:
			if(term){
				res3.setText(finalizado);
				res3.setForeground(verde);
			}else{
				res3.setText(procesando);
			}
			break;
		case 4:
			if(term){
				res4.setText(finalizado);
				res4.setForeground(verde);
			}else{
				res4.setText(procesando);
			}
			break;
		default:
			break;
		}
	}
	public void reiniciar(){
		razon.setText("Razon de compresión:");
		factor.setText("Factor de compresion:");
		tipo.setText("Tipo de archivo:");
		
		res1.setText(esperando);
		res1.setForeground(Color.black);
		res2.setText(esperando);
		res2.setForeground(Color.black);
		res3.setText(esperando);
		res3.setForeground(Color.black);
		res4.setText(esperando);
		res4.setForeground(Color.black);
	}
	public void actualizarRatios(float razon, float factor){
		DecimalFormat df = new DecimalFormat("0.000");
		this.razon.setText("Razon de compresión: " + df.format(razon));
		this.factor.setText("Factor de compresion: " + df.format(factor));
	}
	public void actualizarExtension(String ext){
		this.tipoString = ext;
		tipo.setText("Tipo de archivo: " + tipoString);
	}
}
