package IkNafa.Desencriptador.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Configuracion {
	
	private final char[] letras = new char[] {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	private File file;
	
	public Configuracion() {
		try {
			file = new File("./Config.txt");
			if(file.exists()) {
				file.delete();
			}
			file.createNewFile();
			
			
			PrintWriter writer = new PrintWriter(file);
			
			for (int i = 0; i < letras.length; i++) {
				writer.println(Character.toUpperCase(letras[i]) + " = " + letras[i]);
			}
			
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public char getTraduccion(char pChar) {
		try {
			char letra = Character.toLowerCase(pChar);
			Scanner entrada = new Scanner(file);
			while(entrada.hasNextLine()) {
				String linea = entrada.nextLine().toLowerCase(); //Formato: A = a
				StringTokenizer token = new StringTokenizer(linea);
				char c = token.nextToken().charAt(0);
				if(letra == c) {
					token.nextToken();
					return token.nextToken().charAt(0);
				}
			}
			entrada.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return pChar;
	}
	
	public String getTraduccion(String pPalabra) {
		String palabra = new String();
		
		for (int i = 0; i < pPalabra.length(); i++) {
			palabra = palabra + getTraduccion(pPalabra.charAt(i));
		}
		return palabra;
	}
}
