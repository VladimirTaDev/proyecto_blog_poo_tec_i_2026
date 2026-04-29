package interfaz;

import java.util.Map;
import java.util.Scanner;

import control.Controladora;

public class ProgramaPrincipal {
	private static Controladora control;
	private static Scanner entrada;

	public static void main(String[] args) {
		control = new Controladora();
		entrada = new Scanner(System.in);

		try {
			cargarDatosIniciales();
			menuPrincipal();
		} catch (Exception e) {
			System.out.println("Error general: " + e.getMessage());
		}

		entrada.close();
	}

	private static void cargarDatosIniciales() throws Exception {
		control.crearBlog("Archivo secreto del GRU",
				"Reportes donde Sokolov jura que el código sí compilaba ayer.");

		control.crearBlog("Cocina táctica de Mother Base",
				"Recetas para infiltraciones largas: borsch, café frío y cero sueño.");

		control.crearBlog("Laboratorio de Sokolov",
				"Tecnología rara, robots sospechosos y commits con olor a metal.");
	}

	private static void menuPrincipal() {
		int opcion = -1;

		while (opcion != 0) {
			System.out.println("\n===== SISTEMA DE BLOGS =====");
			System.out.println("1. Ver blogs");
			System.out.println("0. Salir");

			opcion = leerEntero("Opción: ");

			switch (opcion) {
			case 1:
				mostrarBlogs();
				break;
			case 0:
				System.out.println("Fin del programa.");
				break;
			default:
				System.out.println("Opción inválida.");
			}
		}
	}

	private static void mostrarBlogs() {
		Map<Integer, String> blogs = control.obtenerBlogs();

		System.out.println("\n--- Blogs registrados ---");
		imprimirMapa(blogs);
	}

	private static void imprimirMapa(Map<Integer, String> datos) {
		if (datos.isEmpty()) {
			System.out.println("No hay datos registrados.");
		} else {
			for (Integer codigo : datos.keySet()) {
				System.out.println(codigo + " - " + datos.get(codigo));
			}
		}
	}

	private static int leerEntero(String mensaje) {
		System.out.print(mensaje);

		try {
			return Integer.parseInt(entrada.nextLine());
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}