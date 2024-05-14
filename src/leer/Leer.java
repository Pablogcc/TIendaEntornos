package leer;
/**
 * 
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * @author Pablo
 *
 */
public class Leer {

	public static String dato() {
		String sdato = " ";
		try {
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader flujoE = new BufferedReader(isr);
			sdato = flujoE.readLine();
		} catch (IOException e) {
			System.out.println("Error " + e.getMessage());
		}
		return sdato;

	}
	//Ingresa un número entero y hace que lo saques por pantalla
	public static int datoInt() {

		return Integer.parseInt(dato());
	}
	//Ingresa un número decimal y hace que lo saques por pantalla
	public static float datoFloat() {
		return Float.parseFloat(dato());
	}

	// Leer un char por teclado

	public static char datoChar() {
		char c = ' ';
		try {
			java.io.BufferedInputStream b = new BufferedInputStream(System.in);
			c = (char) b.read();
		} catch (IOException e) {
			System.out.println("Error al leer");
			e.printStackTrace();
		}
		return c;
	}
	//Ingresa un número largo y hace que lo saques por pantalla
	public static long datoLong() {
		return Long.parseLong(dato());
	}

}
