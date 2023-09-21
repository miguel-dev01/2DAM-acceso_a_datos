package repaso2;

import java.util.Scanner;

public class ejercicio1 {
	public static void main(String[] args) {
//		Calcula el factorial de un número pedido por teclado
		System.out.print("Introduce numero para factorial: ");
		Scanner keyboard = new Scanner(System.in);
        int numero = keyboard.nextInt();
        int result = 1;
		for (int i = 1; i <= numero; i++) {
			result = result * i;
			System.out.println(result);
		}
		System.out.println("Final: " + result);
		keyboard.close();
	}
}
