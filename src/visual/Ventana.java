package visual;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Ventana extends JFrame{
	private Hoja hoja;
	public static final int ANCHO = 350,ALTO = 150;
	
	public Ventana() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO,ALTO);
		setTitle("Codificador C21");
		setLocationRelativeTo(null);
		setResizable(false);
		hoja = new Hoja();
		
		getContentPane().add(hoja,BorderLayout.CENTER);
		
		
		setVisible(true);
	}
}
