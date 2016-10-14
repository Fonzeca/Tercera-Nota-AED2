package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Ventana extends JFrame{
	private JFileChooser jfc;
	private JButton buton1;
	private String rutaArchivo;
	
	public Ventana() {
		jfc = new JFileChooser();
		buton1 = new JButton("Seleccionar Archivo");
		buton1.setBounds(10, 150, 150, 20);
		buton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
					rutaArchivo = jfc.getSelectedFile().getAbsolutePath();
				}
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(550,200);
		setTitle("Compresor C21 por lo pibe mas borracho del tablon");
		setLocationRelativeTo(null);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		
		
		add(buton1);
	}
}
