package visual.threads;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import estructura.ArbolHuffman;
import estructura.Codificador;
import estructura.LectorArchivo;
import estructura.Lista;
import estructura.TablaHuffman;
import visual.VentanaComprimir;

public class ThreadComprimir extends Thread {
	private String ruta;
	private VentanaComprimir venCom;
	public ThreadComprimir(String ruta, VentanaComprimir venCom) {
		this.ruta = ruta;
		this.venCom = venCom;
	}
	public void run(){
		LectorArchivo lector = new LectorArchivo(ruta,venCom);
		lector.leerArchivo();
		Lista lis = lector.getLista();
		ArbolHuffman ah = new ArbolHuffman(lis);
		TablaHuffman th = new TablaHuffman(ah);
		try {
			Codificador cod = new Codificador(new RandomAccessFile(ruta, "rw"), ruta, th);
			cod.comprimir();
		} catch (FileNotFoundException e) {
			System.out.println("SHE LOCO");
			e.printStackTrace();
		}
	}
}
