package estructura;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Decodificador {

	private String ruta;
	private RandomAccessFile descomprimido,comprimido;
	private TablaHuffman th = null;
	private long tamañoOriginal, tamañoDescomprimido = 0;

	public Decodificador(RandomAccessFile comprimido,String ruta) {
		this.comprimido= comprimido;
		this.ruta = ruta;
	}

	public void creardescomprimido(){
		try{
			
			char c1,c2,c3;
			comprimido.seek(3);
			c1 = (char)comprimido.read();
			c2 = (char)comprimido.read();
			c3 = (char)comprimido.read();

			String rutaNva = ruta.substring(0,ruta.length()-4)+"Descomprimido."+c1+c2+c3;
			descomprimido = new RandomAccessFile(rutaNva, "rw");
			tamañoOriginal = leerDWord(6);
		}catch(Exception e){
			System.out.println(e);

		}
	}

	// este metodo lee un dobleword de corrido.
	public long leerDWord(int seek){
		long aux= 0; 
		try {
			comprimido.seek(seek);
			aux = aux | comprimido.read();
			aux = aux | (long)comprimido.read()<<8;
			aux = aux | (long)comprimido.read()<<16;
			aux = aux | (long)comprimido.read()<<24;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return aux;
	}

	public void decodificar(int posicion){

	}
	public void armarTabla(){
		try {

			comprimido.seek(18);
			Lista lista = new Lista();
			int c = comprimido.read();//cantidad de nodos e nla cabezera del archivo.
			for (int i = 0; i <= c ; i++) {
				byte b = (byte) comprimido.read();
				Long filePointer = comprimido.getFilePointer();
				Long o  = leerDWord(filePointer.intValue());	 
				NodoHuffman nH = new NodoHuffman(b);
				nH.setOcurrencia(o.intValue());
				lista.insertarReordenar(nH);

			}
			ArbolHuffman hf = new ArbolHuffman(lista);
			th = new TablaHuffman(hf);
			leerByteComp((byte) comprimido.read());
			descomprimido.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void leerByteComp(byte byteActual){
		String respuesta = "";
		int bitActual = 1;
		Byte siguienteBit;
		try {
			while(tamañoDescomprimido != tamañoOriginal){
				siguienteBit = (byte) (byteActual & 0x80);
				if(siguienteBit==-128){
					respuesta = respuesta + "1";
				}else{
					respuesta = respuesta + "0";
				}
				if(th.existe(respuesta)){
					try {
						descomprimido.write(th.buscarAcotado(respuesta));
						tamañoDescomprimido++;
						respuesta = "";
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				byteActual = (byte) (byteActual << 1);
				

				bitActual++;
				if(bitActual>8){
					try {
						byteActual=(byte) comprimido.read();
						bitActual=1;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

