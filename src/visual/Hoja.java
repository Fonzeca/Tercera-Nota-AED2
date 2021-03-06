package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import visual.threads.ThreadComprimir;

public class Hoja extends JPanel {
	private JButton comprimir, descomprimir, examinar;
	private JTextField campo;
	private VentanaComprimir ventanaComprimir;
	private VentanaDescomprimir ventanaDescomprimir;
	private JFileChooser jfc;
	public Hoja() {
		setLayout(null);
		jfc = new JFileChooser();
		jfc.removeChoosableFileFilter(jfc.getFileFilter());
		jfc.addChoosableFileFilter(new FileFilter() {
			public String getDescription() {
				return "Comprimibles";
			}
			public boolean accept(File f) {
				String s = f.getName();
				if(f.isDirectory()){
					return true;
				}
				if(s.length() > 4){
					return s.subSequence(s.length()-4, s.length()).charAt(0)== '.';
				}else{
					return false;
				}
			}
		});
		jfc.addChoosableFileFilter(new FileNameExtensionFilter("Archivos comprimidos .c21", ".c21"));
		comprimir = new JButton("Comprimir");
		comprimir.setBounds(20,Ventana.ALTO-70,130,20);
		ventanaComprimir= new VentanaComprimir((Ventana)this.getParent());
		comprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ThreadComprimir(campo.getText(),ventanaComprimir).start();
				ventanaComprimir.setVisible(true);
			}
		});
		comprimir.setEnabled(false);
		
		descomprimir = new JButton("Descomprimir");
		descomprimir.setBounds(Ventana.ANCHO-130-20,Ventana.ALTO-70 , 130, 20);
		ventanaDescomprimir=new VentanaDescomprimir((Ventana)this.getParent());
		descomprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventanaDescomprimir.setVisible(true);
			}
		});
		descomprimir.setEnabled(false);
		
		examinar= new JButton("Examinar");
		examinar.setBounds((Ventana.ANCHO-100-20),Ventana.ALTO/2-50,100,20);
		examinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(jfc.showOpenDialog(((JButton)e.getSource()).getParent().getParent()) == JFileChooser.APPROVE_OPTION){
					File f = jfc.getSelectedFile();
					campo.setText(f.getAbsolutePath());
					comprimir.setEnabled(true);
				}else{
					
				}
			}
		});
		
		campo= new JTextField();
		campo.setSize(180, 20);
		campo.setLocation(20, Ventana.ALTO/2-50);
		campo.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String s = campo.getText();
				if(s.length() > 4){
					if(s.subSequence(s.length()-4, s.length()).charAt(0)== '.'){
						File f = new File(campo.getText());
						if(f.exists()){
							comprimir.setEnabled(true);
						}else{
							comprimir.setEnabled(false);
						}
					}else{
						comprimir.setEnabled(false);
					}
				}else{
					comprimir.setEnabled(false);
				}
			}
		});
		
		
		
		add(comprimir);
		add(descomprimir);
		add(examinar);
		add(campo);
		
	}
}
