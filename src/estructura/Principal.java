package estructura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Principal {

	public static void main(String[] args) {
		String aux = new File("src/recursos/cod.txt").getAbsolutePath();
		
		LectorArchivo lector = new LectorArchivo(aux);
		lector.leerArchivo();
		Lista lis = lector.getLista();
		ArbolHuffman ah = new ArbolHuffman(lis);
		TablaHuffman th = new TablaHuffman(ah);
		Codificador cod = null;
		try {
			 cod = new Codificador(new RandomAccessFile(aux, "rw"), aux, th);
			cod.codificar();
		} catch (FileNotFoundException e) {
			System.out.println("SHE LOCO");
			e.printStackTrace();
		}
		aux = new File("src/recursos/cod.c21").getAbsolutePath();
		try {
			Decodificador deco  = new Decodificador(new RandomAccessFile(aux, "rw"), aux);
			deco.creardescomprimido();
			System.out.println(deco.leerDWord(10));
			deco.armarTabla();
		} catch (FileNotFoundException e) {
			System.out.println(e);
			
		}
		
		
	}

}
