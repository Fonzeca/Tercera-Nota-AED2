package visual.threads;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import estructura.Decodificador;
import visual.VentanaDescomprimir;

public class ThreadDescomprimir extends Thread{
	private String ruta;
	private VentanaDescomprimir venDes;
	public ThreadDescomprimir(String ruta, VentanaDescomprimir venDes) {
		this.ruta = ruta;
		this.venDes = venDes;
	}
	public void run() {
		try {
			Decodificador decodificador  = new Decodificador(new RandomAccessFile(ruta, "rw"), ruta, venDes);
			decodificador.descomprimir();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
