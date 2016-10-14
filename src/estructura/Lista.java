package estructura;

public class Lista implements ILista{
	Nodo primero;

	public void insertar(byte info){
		if(primero== null){
			primero = new Nodo(info, null);
		}else{
			Nodo aux = primero;
			while(aux!= null){
				if(aux.getInfo().getDato()== info){
					aux.getInfo().setOcurrencia(aux.getInfo().getOcurrencia()+1);
					break;
				}else if (aux.getRef()== null){
					aux.setRef(new Nodo(info, null));
					break;
				}
				aux = aux.getRef();
			}
		}	
	}
	public void reordenar(){
		Lista listaAux = new Lista();
		Nodo aux = primero;

		while(aux != null){
			listaAux.insertarReordenar(aux.getInfo());
			aux = aux.getRef();

		}

		primero = listaAux.getPrimero();

	}
	public Nodo getPrimero() {
		return this.primero;
	}

	public void setPrimero(Nodo primero) {
		this.primero = primero;
	}

	public void insertarReordenar(NodoHuffman nodo){
		if(primero== null){
			primero = new Nodo(nodo, null);
		}else if(nodo.getOcurrencia() < primero.getInfo().getOcurrencia()){
				Nodo aux = primero;
				primero = new Nodo(nodo, aux);
		}else if(primero.getRef() == null && nodo.getOcurrencia() > primero.getInfo().getOcurrencia()){
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
	public void mostrar(){
		Nodo aux = primero;
		while (aux!=null){
			System.out.println("dato: "+ aux.getInfo().getDato()+", ocurrencia: "+aux.getInfo().getOcurrencia());
			aux = aux.getRef();
		}
	}

	public NodoHuffman extraerPrimero() {
		NodoHuffman aux = primero.getInfo();
		primero = primero.getRef();
		return aux;
	}

}
