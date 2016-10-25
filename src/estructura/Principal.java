package estructura;

import visual.Ventana;

public class Principal {

	public static void main(String[] args) {
//		String ruta = new File("src/recursos/cod.txt").getAbsolutePath();
//		try {
//			Codificador codificador = new Codificador(new RandomAccessFile(ruta, "rw"), ruta);
//			codificador.comprimir();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		ruta = new File("src/recursos/cod.c21").getAbsolutePath();
//		try {
//			Decodificador decodificador  = new Decodificador(new RandomAccessFile(ruta, "rw"), ruta);
//			decodificador.descomprimir();
//		} catch (FileNotFoundException e) {
//			System.out.println(e);
//		}
		new Ventana();

	}

}
