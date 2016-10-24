package estructura;

/**
 *  Estructura compuesta por nodos que contienen un info (NodoHuffman) y una ocurrencia (int).
 * */
public class Lista implements ILista{
	private Nodo primero;
	private int tamaño = 0;

	public int getTamaño() {
		return tamaño;
	}
	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}
	public Nodo getPrimero() {
		return this.primero;
	}
	
	public void setPrimero(Nodo primero) {
		this.primero = primero;
	}

	/**
	 *  Recibe un byte por parámetro y lo inserta en la lista, En caso de ya existir este dato en la misma, se incrementa su ocurrencia en 1.
	 * */
	public void insertar(byte info){
		if(primero== null){
			primero = new Nodo(info, null);
			tamaño++;
		}else{
			Nodo aux = primero;
			while(aux!= null){
				if(aux.getInfo().getDato()== info){
					aux.getInfo().setOcurrencia(aux.getInfo().getOcurrencia()+1);
					break;
				}else if (aux.getRef()== null){
					aux.setRef(new Nodo(info, null));
					tamaño++;
					break;
				}
				aux = aux.getRef();
			}
		}
		
	}
	
	/**
	 *  Ordena la lista por ocurrencia y luego por aparición.
	 * */
	public void reordenar(){
		Lista listaAux = new Lista();
		Nodo aux = primero;

		while(aux != null){
			listaAux.insertarReordenar(aux.getInfo());
			aux = aux.getRef();
		}
		primero = listaAux.getPrimero();
	}
	
	/**
	 *  Inserta un NodoHuffman en la
	 * */
	public void insertarReordenar(NodoHuffman nodo){
		if(primero== null){
			primero = new Nodo(nodo, null);
		}else if(nodo.getOcurrencia() < primero.getInfo().getOcurrencia()){
				Nodo aux = primero;
				primero = new Nodo(nodo, aux);
		}else if(primero.getRef() == null && nodo.getOcurrencia() >= primero.getInfo().getOcurrencia()){
				primero.setRef(new Nodo(nodo, null));
		}else{
			Nodo auxAnterior = primero, aux = primero.getRef();
			while(aux!= null){

				if(nodo.getOcurrencia() < aux.getInfo().getOcurrencia()){
					auxAnterior.setRef(new Nodo(nodo, aux));
					break;
				}else if(aux.getRef() == null){
					aux.setRef(new Nodo(nodo, null));
					break;
				}
				auxAnterior = aux;
				aux = aux.getRef();
			}
		}
		
	}
	
	/**
	 *  Devuelve el info del nodo en la posición recibida por parámetro.
	 * */
	public NodoHuffman get(int index){
		int contador= 0;
		Nodo aux = primero;
		while(aux != null){
			if(index == contador){
				return aux.getInfo();
			}
			aux = aux.getRef();
			contador++;
		}
		return aux.getInfo();
	}
	
	/**
	 *  Recorre cada nodo de la lista y muestra el par dato, ocurrencia.
	 * */
	public void mostrar(){
		Nodo aux = primero;
		while (aux!=null){
			System.out.println("dato: " + aux.getInfo().getDato() +", ocurrencia: "+aux.getInfo().getOcurrencia());
			aux = aux.getRef();
		}
	}

	/**
	 *  Devuelve el info del primer nodo y además lo elimina de la listas.
	 * */
	public NodoHuffman extraerPrimero() {
		NodoHuffman aux = primero.getInfo();
		primero = primero.getRef();
		return aux;
	}

}
