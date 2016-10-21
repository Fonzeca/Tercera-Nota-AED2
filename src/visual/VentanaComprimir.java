package visual;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaComprimir extends JDialog{//hecha por el equipo once. #11

	private JPanel resumen;
	private JLabel ratio,tipo,estado;
	private int intRatio,intPorcentaje;
	private String tipoString= "archivo .txt";
	
	public VentanaComprimir(JFrame ventana) {
		super(ventana);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setTitle("Compresión");
		this.setLocationRelativeTo(ventana);
		this.setModal(true);
		this.setLayout(null);
		
		resumen=new JPanel();
		resumen.setLayout(new BoxLayout(resumen, BoxLayout.Y_AXIS));
		resumen.setSize(250,150);
		resumen.setBorder(BorderFactory.createTitledBorder("Resumen"));
		resumen.setLocation(25, 100);
		
		//evento condificación
		estado= new JLabel("Codificando.");
		estado.setSize(250,20);
		estado.setLocation(120,Ventana.ALTO/2-20 );
		
		//evento finalizado
		if(intPorcentaje==100){
			estado.setText("Proceso finalizado.");
			estado.setLocation(95, Ventana.ALTO/2-20);
			
		}
		
		ratio= new JLabel("Ratio de compresión: "+intRatio+"%.");
		tipo= new JLabel(tipoString);
		
		this.add(estado);
		resumen.add(ratio);
		resumen.add(tipo);
		
		
		
		this.add(resumen);
	}
	public void actualizar(){
		
		
		//fonzo puto,golozo y comilón.
	}
	public void setIntRatio(int intRatio) {
		this.intRatio = intRatio;
	}
	public void setIntPorcentaje(int intPorcentaje) {
		this.intPorcentaje = intPorcentaje;
	}
	
}
