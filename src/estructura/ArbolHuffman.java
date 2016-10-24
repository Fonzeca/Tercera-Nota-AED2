package estructura;
/**
 *  Estructura tipo árbol compuesta por nodos (NodoHuffman).
 * */
public class ArbolHuffman  {
	private Lista lista;
	private NodoHuffman raiz;
	
	public ArbolHuffman(Lista lista){
		this.lista=lista;
		while (this.lista.getPrimero().getRef() != null) {
			generadorNodo();
		}
		raiz=this.lista.getPrimero().getInfo();
	}
	
	public NodoHuffman getRaiz() {
		return raiz;
	}

	/**
	 *  Crea un nuevo NodoHuffman con dato nulo que apunta a los dos primeros nodos de la lista y cuya ocurrencia es igual a la suma de las ocurrencias
	 *  de ambos nodos. Luego, se inserta dicho nodo en el árbol.
	 * */
	private void generadorNodo(){
		NodoHuffman huff=new NodoHuffman(null);
		huff.setIzq(lista.extraerPrimero());
		huff.setDer(lista.extraerPrimero());
		huff.setOcurrencia(huff.getIzq().getOcurrencia()+huff.getDer().getOcurrencia());
		lista.insertarReordenar(huff);
	}
	
	/**
	 *  Recorre el árbol recursivamente y muestra cada nodo por entreorden/inorden
	 * */
	public void mostrar() {
		if (raiz != null) {
			mostrarRecursivo(raiz);
		}
	}

	private void mostrarRecursivo(NodoHuffman nod) {
		if (nod != null) {
			mostrarRecursivo(nod.getIzq());
			if (nod.getDato() != null) {
				System.out.println("Dato: " + nod.getDato() + ", Caracter: " + (char) nod.getDato().intValue()+ ", ocurrencia: " + nod.getOcurrencia());

			} else
				System.out.println("Dato null " + nod.getOcurrencia());
			mostrarRecursivo(nod.getDer());

		}
	}
}	
