/**
 * 
 */
package menu;

import leer.Leer;

/**
 * 
 */
public class Menus {
	public static void Mensaje_Inicial() {

		System.out.println("Bienvenido a la tienda virtual\n\n"
				+ "El programa simula una tienda que vende juegos, música y cine\n"
				+ "Solamente se pueden vender productos si aparecen disponibles en la tienda\n"
		);
	}

	
	public static void Opciones_Menu() {

		System.out.println("\nSeleccione que desea realizar:\n\n" + "\t1. Mostrar productos. (Listar productos)\n"
				+ "\t2. Comprar productos\n" + "\t3. Mostrar caja. (Importe total de la compra actual)\n"
				+ "\tSALIR --> Pulse cualquier otro número\n");
	}

	public static void Mensaje_Fin() {

		System.out.println("---- Gracias por usar este software. ----");
	}

	
 
	public static void main(String[] args) {
		// Aquí inicia el mensaje inicial de bienvenida
		Mensaje_Inicial();

		boolean continuar = true;

		do {
			//Aquí te sale las opciones del menú
			Opciones_Menu();

			switch (Leer.datoInt()) {
			case 1:

				break;
			case 2:
				

				break;
			case 3:

				break;
			default:
				// Se sale del programa
				continuar = false;
			}

		} while (continuar);
		//Aqu
		Mensaje_Fin();

	}

}
