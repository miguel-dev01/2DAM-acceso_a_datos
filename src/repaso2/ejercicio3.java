package repaso2;

import java.util.Scanner;

public class ejercicio3 {
	public static void main(String[] args) {
//		Pide un número y muestra si es positivo o negativo y si es par o impar
		System.out.print("Introduce numero: ");
		Scanner keyboard = new Scanner(System.in);
        int numero = keyboard.nextInt();
		if(numero >= 1) {
			System.out.println("Es positivo");
		} else if(numero <= -1) {
			System.out.println("Es negativo");
		} else {
			System.out.println("Neutro");
		}
		if(numero % 2 == 0) {
			System.out.println("Es par");
		} else {
			System.out.println("Es impar");
		}
		keyboard.close();
	}
}
