package estructura;

/**
 *  Componente utilizado en la estructura TablaHuffman. Compuesto por un dato (byte), una ocurrencia (int), un byte acotado y una referencia a un
 *  NodoTablaHuffman.
 * */
public class NodoTablaHuffman {
	
	private NodoTablaHuffman siguiente;
	private byte dato;
	private String byteAcotado;
	private int ocurrencia;

	public NodoTablaHuffman(byte dato, String byteAcotado, int ocurrencia, NodoTablaHuffman siguiente) {
		
		this.dato=dato;
		this.byteAcotado=byteAcotado;
		this.ocurrencia=ocurrencia;
		this.siguiente=siguiente;
	}

	public NodoTablaHuffman getSiguiente() {
		return siguiente;
	}

	public byte getDato() {
		return dato;
	}

	public String getByteAcotado() {
		return byteAcotado;
	}

	public int getOcurrencia() {
		return ocurrencia;
	}


}
