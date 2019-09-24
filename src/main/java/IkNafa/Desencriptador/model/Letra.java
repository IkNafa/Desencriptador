package IkNafa.Desencriptador.model;

public class Letra {
	private char letra;
	private int contador;
	
	public Letra(char pC) {
		letra = pC;
		contador = 0;
	}
	
	public void sumar() {
		contador++;
	}
	
	public int getContador() {
		return contador;
	}
	
	public char getLetra() {
		return letra;
	}
}
