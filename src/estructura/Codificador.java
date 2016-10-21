package estructura;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Codificador {

	private int contador=0;
	private byte byteNuevo=0;
	private String ruta;
	private RandomAccessFile comprimido,original;
	private ITablaHuffman th=null;
	private Lista lista;

	public Codificador(RandomAccessFile original,String ruta,ITablaHuffman th, Lista lista){

		this.original=original;
		this.ruta= ruta;
		crearArchivo();
		this.th=th;
		this.lista = lista;
	}

	public void codificar( ){
		

		try{
			escribirCabecera();
			//Escritura de bytes comprimidos.
			
			int aux;
			while((aux=original.read())!=-1){
				String byteComprimido=th.buscar((byte)aux);
				escribirBytes(byteComprimido);
			}
			
			comprimido.write(byteNuevo);
			
			//Tamaño de este archivo
			comprimido.seek(14);
			guardarDWord(comprimido.length());
			original.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void crearArchivo(){
		try{
			String rutaNueva=ruta.substring(0, ruta.length()-4)+".c21";
			comprimido= new RandomAccessFile(new File(rutaNueva), "rw");
			
		}catch(Exception e){
			System.err.println(e);
		}
	}
	public void escribirCabecera(){
		try {
			//c21.
			comprimido.write('c');
			comprimido.write('2');
			comprimido.write('1');
			//extensión archivo original.
			String extension=ruta.substring(ruta.length()-3);
			for (int i =0;i<extension.length();i++){
				comprimido.write(extension.charAt(i));
			}
			//tamaño del archivo original.
			guardarDWord(original.length());
			//posición comienzo de los datos comprimidos

			comprimido.seek(18);
			comprimido.write(lista.getTamaño()-1);
			for (int i=0;i<lista.getTamaño();i++){
				NodoHuffman nodoHuffman = lista.get(i);
				comprimido.write(nodoHuffman.getDato());
				guardarDWord(nodoHuffman.getOcurrencia());
			}
			long posicionCompri = comprimido.getFilePointer();
			comprimido.seek(10);
			guardarDWord(posicionCompri);
			comprimido.seek(posicionCompri);
			
		} catch (IOException e) {
			System.err.println(e);
		}
		
	}
	public void guardarDWord(long x){
		for(int i = 0; i < 4; i++){
			long y = x & 0xFF;
			try {
				comprimido.write((int)y);
				x = x >> 8;
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}


	public long getTamañoComprimido(){
		long tamaño=0;
		try{
			tamaño= comprimido.length();
			
		}catch(IOException ex){
			System.err.println(ex);
		}
		return tamaño;
	}
	
	public void escribirBytes(String byteComprimido){
		
		for (int i=0; i<byteComprimido.length();i++){
			
			if(byteComprimido.charAt(i)=='1'){
				
				switch(contador){
				case 0:
					byteNuevo=(byte) (byteNuevo | 0x80);
					break;
				case 1:
					byteNuevo=(byte) (byteNuevo | 0x40);
					break;
				case 2:
					byteNuevo=(byte) (byteNuevo | 0x20);
					break;
				case 3:
					byteNuevo=(byte) (byteNuevo | 0x10);
					break;
				case 4:
					byteNuevo=(byte) (byteNuevo | 0x8);
					break;
				case 5:
					byteNuevo=(byte) (byteNuevo | 0x4);
					break;
				case 6:
					byteNuevo=(byte) (byteNuevo | 0x2);
					break;
				case 7:
					byteNuevo=(byte) (byteNuevo | 0x1);
					break;
				}
			}
			contador++;
			if (contador>7){
				try{
					comprimido.write(byteNuevo);
					contador=0;
					byteNuevo=0;
				}catch(Exception e){
					System.err.println(e);
				}
				
			}
		}

	}

}
