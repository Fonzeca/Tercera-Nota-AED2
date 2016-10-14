package estructura;

public class TablaHuffman implements ITablaHuffman{

	//	Variables
	
	private NodoTablaHuffman pri; 
	private int con=0;

	//	Constructor
	public TablaHuffman(ArbolHuffman arb) {
		recorrerArbolRecursivo(arb.getRaiz(),"");
	}
	
	//	Métodos 
	private void recorrerArbolRecursivo(NodoHuffman p,String ruta){
		if(p.izq==null&&p.der==null){
			insertar(p.dato,ruta,p.ocurrencia);
		}
		if(p.izq!=null){
			recorrerArbolRecursivo(p.izq, ruta+"0");
		}
		if(p.der!=null){
		
			recorrerArbolRecursivo(p.der,ruta+"1");
		}
		
	}
	 
	public void insertar(byte dato, String ruta,int ocurrencia){
		pri=new NodoTablaHuffman(dato,ruta, ocurrencia,pri);
		con++;
	}

	public int getTamaño() {		
		return con;
	}

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
	//devuelve el nodo en la posicion "index".
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

}
