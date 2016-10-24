package estructura;

public class TablaHuffman implements ITablaHuffman{
	
	private NodoTablaHuffman pri; 
	private int tamaño=0;

	public TablaHuffman(ArbolHuffman arb) {
		recorrerArbolRecursivo(arb.getRaiz(),"");
	}

	public int getTamaño() {		
		return tamaño;
	}

	/**
	 *  Recorre el árbol recursivamente formando una ruta dependiendo si accede a la referencia izquierda (se añade 0 a la ruta) o a la referencia
	 *  derecha (se añade 1 a la ruta). Al encontrarse con una hoja, inserta en la tabla su dato, ocurrencia, y la ruta actual hasta dicha hoja.
	 * */
	private void recorrerArbolRecursivo(NodoHuffman p,String ruta){
		if(p.getIzq()==null&&p.getDer()==null){
			insertar(p.getDato(),ruta,p.getOcurrencia());
		}
		if(p.getIzq()!=null){
			recorrerArbolRecursivo(p.getIzq(), ruta+"0");
		}
		if(p.getDer()!=null){
			recorrerArbolRecursivo(p.getDer(),ruta+"1");
		}
		
	}
	
	/**
	 *  Inserta un nuevo nodo en la tabla con los datos pasados por parámetro.
	 * */
	public void insertar(byte dato, String ruta,int ocurrencia){
		pri=new NodoTablaHuffman(dato,ruta, ocurrencia,pri);
		tamaño++;
	}

	/**
	 *  Devuelve el byte acotado correspondiente al byte pasado por parámetro.
	 * */
	public String buscar(byte x) {
		NodoTablaHuffman aux = pri;
		while(aux!= null){
			if(aux.getDato() == x){
				return aux.getByteAcotado();
			}
			aux = aux.getSiguiente();
		}
		
		return null;
	}
	
	/**
	 *  Comprueba si existe un byte que tenga asociado el byte acotado pasado por parámetro.
	 * */
	public boolean existe(String byteAcotado) {
		NodoTablaHuffman aux = pri;
		while(aux!= null){
			if(aux.getByteAcotado().equals(byteAcotado)){
				return true;
			}
			aux = aux.getSiguiente();
		}
		return false;
	}
	
	/**
	 *  Devuelve el byte que tiene asociado a él el byte acotado pasado por parámetro.
	 * */
	public byte buscarAcotado(String byteAcotado) {
		NodoTablaHuffman aux = pri;
		while(aux!= null){
			if(aux.getByteAcotado().equals(byteAcotado)){
				return aux.getDato();
			}
			aux = aux.getSiguiente();
		}
		return (Byte) null;
	}
	
	/**
	 *  Devuelve el nodo en la posición pasada por parámetro.
	 * */
	public NodoTablaHuffman get(int index){
		int contador= 0;
		NodoTablaHuffman aux = pri;
		while(aux != null){
			if(index == contador){
				return aux;
			}
			aux = aux.getSiguiente();
			contador++;
		}
		return aux;
	}
	
	/**
	 *  Recorre cada nodo de la tabla y muestra el dato (byte), el byte acotado y su ocurrencia.
	 * */
	public void mostrar(){
		NodoTablaHuffman aux = pri;
		while(aux != null){
			System.out.println("Dato: " + aux.getDato() + "\tByte acotado: " + aux.getByteAcotado()+"\tOcurrencia: " + aux.getOcurrencia());
			aux = aux.getSiguiente();
		}
	}

}
