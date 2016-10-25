package visual;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDescomprimir extends JDialog{
	private final String esperando = "Esperando";
	private final String procesando = "Procesando";
	private final String finalizado = "Finalizado";
	
	private final String p1 = "Leyendo cabecera: ";
	private final String p2 = "Armando tabla: ";
	private final String p3 = "Decodificando: ";
	
	private JPanel resultado;
	private JLabel pro1,pro2,pro3;
	private JLabel res1, res2, res3;
	
	private JLabel tipoObtenido;
	private String stringTipoObtenido  ="asd";
	private int intPorcentaje;
	public VentanaDescomprimir(JFrame ventana) {
		super(ventana);
		this.setSize(300,250);
		this.setResizable(false);
		this.setTitle("Descompresión");
		this.setLocationRelativeTo(ventana);
		this.setModal(true);
		this.setLayout(null);
		
		resultado= new JPanel();
		resultado.setLayout(new BoxLayout(resultado, BoxLayout.Y_AXIS));
		resultado.setSize(250,100);
		resultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
		resultado.setLocation(25, 100);
		
		pro1 = new JLabel(p1);
		pro2 = new JLabel(p2);
		pro3 = new JLabel(p3);
		pro1.setBounds(25,10,120,20);
		pro2.setBounds(25,30,90,20);
		pro3.setBounds(25,50,90,20);
		
		res1 = new JLabel(esperando);
		res2 = new JLabel(esperando);
		res3 = new JLabel(esperando);
		
		res1.setBounds(pro1.getBounds().x+pro1.getBounds().width + 5, 10,150,20);
		res2.setBounds(pro2.getBounds().x+pro2.getBounds().width + 5, 30,150,20);
		res3.setBounds(pro3.getBounds().x+pro3.getBounds().width + 5, 50,150,20);
		
		
		//evento finalizado
		
		tipoObtenido= new JLabel("Tipo de archivo de salida: ");
		
		this.add(pro1);
		this.add(pro2);
		this.add(pro3);
		this.add(res1);
		this.add(res2);
		this.add(res3);
		
		this.add(resultado);
		resultado.add(tipoObtenido);
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
		default:
			break;
		}
	}
	public void reiniciar(){
		tipoObtenido.setText("Tipo de archivo de salida: ");
		res1.setText(esperando);
		res1.setForeground(Color.black);
		res2.setText(esperando);
		res2.setForeground(Color.black);
		res3.setText(esperando);
		res3.setForeground(Color.black);
	}
	
	public void actualizarExt(String ext){
		tipoObtenido.setText("Tipo de archivo de salida: " + ext);
	}
}
