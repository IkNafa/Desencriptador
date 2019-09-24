package IkNafa.Desencriptador.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class ListaLetras {
	private final char[] letras = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private List<Letra> lista;
	
	public ListaLetras() {
		lista = new LinkedList<Letra>();
		
		for(int i = 0; i<letras.length; i++) {
			lista.add(new Letra(letras[i]));
		}
	}
	
	public void contar(char pLetra) {
		ListIterator<Letra> itr = lista.listIterator();
		while(itr.hasNext()) {
			Letra l = itr.next();
			if(l.getLetra() == pLetra) {
				l.sumar();
				return;
			}
		}
	}
	
	public List<Letra> getListaOrdenado(){
		return lista.stream().sorted(Comparator.comparing(Letra::getContador).reversed()).collect(Collectors.toList());
	}
}
