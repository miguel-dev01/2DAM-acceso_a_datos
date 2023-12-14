package User;

import java.util.Scanner;

public class Snippet {
	public static Scanner keyboard = new Scanner(System.in);
	
	public static int getInt() {
		int number = -1;
		String value = keyboard.nextLine();
		try {
			number = Integer.parseInt(value);
		} catch (Exception e) {
			System.out.println("\nEl valor introducido no es un numero. Intentalo de nuevo\n");
		}
		return number;
	}
	
	// Metodo para pedir numero por teclado
	public static int getInt(String text) {
		System.out.print(text);
		return getInt();
	}
	
	// Con enunciado. Este metodo realiza la funcion de pedir por teclado.
	public static String getString(String text) {
		System.out.print(text);
		return keyboard.nextLine().trim();
	}
}

