package estructura;

public class ArbolHuffman  {
	private Lista x;
	private NodoHuffman raiz;
	
	public ArbolHuffman(Lista x){
		this.x=x;
		int i=-1;
		while (x.getPrimero().getRef() != null) {
			generadorNodo();
		}
		raiz=x.getPrimero().getInfo();
		
		
	}
	private void generadorNodo(){
		NodoHuffman huff=new NodoHuffman(null);
		huff.setIzq(x.extraerPrimero());
		huff.setDer(x.extraerPrimero());
		huff.setOcurrencia(huff.getIzq().getOcurrencia()+huff.getDer().getOcurrencia());
		x.insertarReordenar(huff);
	}
	private int esRaiz(){
		if(x.getPrimero()!=null && x.getPrimero().getRef()==null){
			return 1;
		}
		return -1;
	}
	public NodoHuffman getRaiz() {
		return raiz;
	}
	
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
