package visual;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaDescomprimir extends JDialog{
	
	private JPanel resultado;
	private JLabel estado;
	private JLabel tipoObtenido;
	private String stringTipoObtenido  ="archivo txt POR EJEMPLO BORRAR";
	private int intPorcentaje;
public VentanaDescomprimir(JFrame ventana) {
	super(ventana);
	this.setSize(300,300);
	this.setResizable(false);
	this.setTitle("Descompresión");
	this.setLocationRelativeTo(ventana);
	this.setModal(true);
	this.setLayout(null);
	
	resultado= new JPanel();
	resultado.setSize(250,150);
	resultado.setBorder(BorderFactory.createTitledBorder("Resultado"));
	resultado.setLocation(25, 100);
	
	estado= new JLabel("Decodificando.");
	estado.setSize(250,20);
	estado.setLocation(120,Ventana.ALTO/2-20);
	
	//evento finalizado
	if(intPorcentaje==100){
		estado.setText("Proceso finalizado.");
		estado.setLocation(95, Ventana.ALTO/2-20);
		
	}
	tipoObtenido= new JLabel(stringTipoObtenido);
	
	
	this.add(estado);
	this.add(resultado);
	resultado.add(tipoObtenido);
}
}
