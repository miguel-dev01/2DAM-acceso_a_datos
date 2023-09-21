package repaso2;

import java.util.Scanner;

public class ejercicio2 {
	public static void main(String[] args) {
//	Suma los números pares y los impares hasta un número pedido por teclado
		System.out.print("Introduce numero: ");
		Scanner keyboard = new Scanner(System.in);
        int numero = keyboard.nextInt();
        int sumaPar = 0;
        int sumaImpar = 0;
		for (int i = 1; i <= numero; i++) {
			if (i%2 == 0) {
				sumaPar += i;
			} else {
				sumaImpar += i;
			}
		}
		System.out.println("Numeros pares: " + sumaPar);
		System.out.println("Numeros impares: " + sumaImpar);
		keyboard.close();
	}
}
