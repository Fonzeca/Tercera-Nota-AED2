package estructura;
/**
 *  Componente utilizado en la estructura Lista. Compuesto por un info (NodoHuffman) y una referencia a otro Nodo.
 * */
public class Nodo {

	private NodoHuffman info;
	private Nodo ref;

	public Nodo(byte info, Nodo ref) {
		this.info = new  NodoHuffman(info);
		this.ref = ref;
	}
	public Nodo(NodoHuffman info,Nodo ref){
		this.info = info;
		this.ref = ref;
	}

	public NodoHuffman getInfo() {
		return info;
	}

	public void setInfo(NodoHuffman info) {
		this.info = info;
	}

	public Nodo getRef() {
		return ref;
	}

	public void setRef(Nodo ref) {
		this.ref = ref;
	}

}