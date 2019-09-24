package IkNafa.Desencriptador.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ContadorTexto {
	public static String contarLetrasFichero(String pFile) {
		ListaLetras lista = new ListaLetras();
		try {
			Scanner entrada = new Scanner(new File(pFile));
			while(entrada.hasNext()) {
				String palabra = entrada.next().toLowerCase();
				for(int i = 0; i<palabra.length();i++) {
					lista.contar(palabra.charAt(i));
				}
			}
			
			entrada.close();
			
			String text = new String();
			ListIterator<Letra> itr = lista.getListaOrdenado().listIterator();
			while(itr.hasNext()) {
				Letra l = itr.next();
				text = text + Character.toUpperCase(l.getLetra()) + " = " + l.getContador() + "\n";
			}
			return text;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String contarLetrasTexto(String pTexto) {
		Scanner entrada = new Scanner(pTexto);
		ListaLetras lista = new ListaLetras();
		
		while(entrada.hasNext()) {
			String palabra = entrada.next().toLowerCase();
			for(int i = 0; i<palabra.length();i++) {
				lista.contar(palabra.charAt(i));
			}
		}
		
		entrada.close();
		
		String text = new String();
		ListIterator<Letra> itr = lista.getListaOrdenado().listIterator();
		while(itr.hasNext()) {
			Letra l = itr.next();
			text = text + Character.toUpperCase(l.getLetra()) + " = " + l.getContador() + "\n";
		}
		return text;
	}
	
	public static String traducirFichero(String pFile, Configuracion pConfig) {
		try {
			String textoTraduccido = new String();
			Scanner entrada = new Scanner(new File(pFile));
			
			while(entrada.hasNextLine()) {
				String linea = entrada.nextLine();
				StringTokenizer token = new StringTokenizer(linea);
				
				while(token.hasMoreTokens()) {
					String palabra = token.nextToken();
					textoTraduccido = textoTraduccido + pConfig.getTraduccion(palabra) + " ";
				}
				
				textoTraduccido = textoTraduccido + "\n";
			}
			
			entrada.close();
			return textoTraduccido;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String traducirTexto(String pTexto, Configuracion pConfig) {
		String textoTraduccido = new String();
		Scanner entrada = new Scanner(pTexto);
		
		while(entrada.hasNextLine()) {
			String linea = entrada.nextLine();
			StringTokenizer token = new StringTokenizer(linea);
			
			while(token.hasMoreTokens()) {
				String palabra = token.nextToken();
				textoTraduccido = textoTraduccido + pConfig.getTraduccion(palabra) + " ";
			}
			
			textoTraduccido = textoTraduccido + "\n";
		}
		
		entrada.close();
		return textoTraduccido;
	}
}
