package estructura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

public class Principal {

	public static void main(String[] args) {
		String aux = new File("src/recursos/archivo.ext").getAbsolutePath();
		LectorArchivo lector = new LectorArchivo(aux);
		lector.leerArchivo();
		Lista lis = lector.getLista();
		
		Lista original =new Lista();
		original.setTamaño(lis.getTamaño());
		for(int i = 0; i < lis.getTamaño(); i++){
			original.insertarReordenar(lis.get(i));
		}
		
		
		ArbolHuffman ah = new ArbolHuffman(lis);
		TablaHuffman th = new TablaHuffman(ah);
		Codificador cod = null;
		try {
			cod = new Codificador(new RandomAccessFile(aux, "rw"), aux, th, original);
			cod.codificar();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		aux = new File("src/recursos/archivo.ext").getAbsolutePath();
		try {

			Decodificador deco  = new Decodificador(new RandomAccessFile(aux, "rw"), aux);
			deco.creardescomprimido();
			deco.armarTabla();
		} catch (FileNotFoundException e) {
			System.out.println(e);

		}


	}

}
