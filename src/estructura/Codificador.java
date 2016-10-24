package estructura;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Codificador {

	private int contador=0;
	private byte byteNuevo=0;
	private String ruta;
	private RandomAccessFile comprimido, original;
	private TablaHuffman tabla;
	private ArbolHuffman arbol;
	private Lista listaOriginal, listaDuplicada;

	public Codificador(RandomAccessFile original,String ruta){
		this.original=original;
		this.ruta= ruta;
		crearArchivo();
	}

	/**
	 *	M�todo para crear el archivo comprimido
	 * */
	private void crearArchivo(){
		try{
			String rutaNueva=ruta.substring(0, ruta.length()-3)+"c21";
			comprimido= new RandomAccessFile(new File(rutaNueva), "rw");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 *	Proceso completo de compresi�n del archivo
	 * */
	public void comprimir(){
		try{
			listaOriginal = crearLista();
			listaDuplicada = crearLista();
			
			arbol = new ArbolHuffman(listaOriginal);
			tabla = new TablaHuffman(arbol);
			
			escribirCabecera();
			
			//Escritura de bytes comprimidos.
			
			int aux;
			while((aux=original.read())!=-1){
				String byteComprimido=tabla.buscar((byte)aux);
				escribirBytes(byteComprimido);
			}
			
			// Se escribe el �ltimo byte en caso de que el contador no haya llegado a 8
			if(contador > 0){
				comprimido.write(byteNuevo);
			}
			// Tama�o de este archivo
			comprimido.seek(14);
			guardarDWord(comprimido.length());
			original.close();
			comprimido.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Escritura de la cabecera completa
	 * */
	public void escribirCabecera(){
		try {
			//c21
			comprimido.write('c');
			comprimido.write('2');
			comprimido.write('1');
			//extensi�n archivo original
			String extension=ruta.substring(ruta.length()-3);
			for (int i =0;i<extension.length();i++){
				comprimido.write(extension.charAt(i));
			}
			//tama�o del archivo original
			guardarDWord(original.length());
			
			// cantidad de nodos de la tabla
			comprimido.seek(18);
			comprimido.write(listaDuplicada.getTama�o()-1);
			
			// grabado de la tabla
			for (int i=0;i<listaDuplicada.getTama�o();i++){
				NodoHuffman nodoHuffman = listaDuplicada.get(i);
				comprimido.write(nodoHuffman.getDato());
				guardarDWord(nodoHuffman.getOcurrencia());
			}
			
			// posici�n de comienzo de los datos comprimidos
			long posicionCompri = comprimido.getFilePointer();
			comprimido.seek(10);
			guardarDWord(posicionCompri);
			comprimido.seek(posicionCompri);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Toma un dato de tipo long (4 bytes) y guarda en el archivo cada uno de los 4 bytes, de m�s significativo a menos significativo
	 * */
	public void guardarDWord(long x){
		for(int i = 0; i < 4; i++){
			long y = x & 0xFF;
			try {
				comprimido.write((int)y);
				x = x >> 8;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Recorre el archivo completo y crea una lista enlazada con datos (byte) y sus ocurrencias en el mismo
	 * */
	private Lista crearLista(){
		try {
			Lista lista = new Lista();
			for (int i = 0; i < original.length(); i++) {
				lista.insertar(original.readByte());
			}
			lista.reordenar();
			original.seek(0);
			return lista;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Devuelve el tama�p del archivo comprimido
	 * */
	public long getTama�oComprimido(){
		long tama�o=0;
		try{
			tama�o= comprimido.length();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		return tama�o;
	}
	
	/**
	 * Recibe un byte acotado (menor a 8 bits) por par�metro. Se analiza cada bit de este byte acotado y, en caso de valer 1, se aplica una m�scara
	 * sobre un otro byte en la posici�n que indique un contador. Cada vez que se lee un bit del byte acotado, el contador aumenta en 1. Al llegar a
	 * 8, el byte se escribe en el archivo y el contador se resetea a 0
	 * */
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
					e.printStackTrace();
				}
				
			}
		}

	}

}
