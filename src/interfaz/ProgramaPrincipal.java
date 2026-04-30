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

		control.crearPublicacion(1,
				"Volgin y los commits eléctricos",
				"El coronel Volgin recomienda no hacer push directo a main mientras hay tormenta.",
				"Coronel Volgin");

		control.crearPublicacion(1,
				"Ocelot practica manejo de excepciones",
				"Revolver Ocelot lanzó tres Exceptions al aire y atrapó dos con try/catch.",
				"Revolver Ocelot");

		control.crearPublicacion(2,
				"Borsch para misiones largas",
				"Receta aprobada para estudiantes que depuran hasta medianoche.",
				"Cocinero de Mother Base");

		control.crearPublicacion(3,
				"Sokolov y el código que no debía existir",
				"Sokolov insiste en que el prototipo funcionaba antes de agregarle más if.",
				"Nikolai Sokolov");
	}

	private static void menuPrincipal() {
		int opcion = -1;

		while (opcion != 0) {
			System.out.println("\n===== SISTEMA DE BLOGS =====");
			System.out.println("1. Ver blogs");
			System.out.println("2. Crear blog");
			System.out.println("3. Borrar blog");
			System.out.println("4. Entrar a un blog");
			System.out.println("0. Salir");

			opcion = leerEntero("Opción: ");

			try {
				switch (opcion) {
				case 1:
					mostrarBlogs();
					break;
				case 2:
					crearBlog();
					break;
				case 3:
					borrarBlog();
					break;
				case 4:
					entrarBlog();
					break;
				case 0:
					System.out.println("Fin del programa.");
					break;
				default:
					System.out.println("Opción inválida.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	private static void mostrarBlogs() {
		Map<Integer, String> blogs = control.obtenerBlogs();

		System.out.println("\n--- Blogs registrados ---");
		imprimirMapa(blogs);
	}
	
	private static void crearBlog() {
		System.out.println("\n--- Crear blog ---");

		System.out.print("Nombre: ");
		String nombre = entrada.nextLine();

		System.out.print("Descripción: ");
		String descripcion = entrada.nextLine();

		control.crearBlog(nombre, descripcion);

		System.out.println("Blog creado correctamente.");
	}

	private static void borrarBlog() throws Exception {
		System.out.println("\n--- Borrar blog ---");
		mostrarBlogs();

		int codigoBlog = leerEntero("Código del blog a borrar: ");

		control.borrarBlog(codigoBlog);

		System.out.println("Blog borrado correctamente.");
	}
	
	private static void entrarBlog() throws Exception {
		System.out.println("\n--- Entrar a un blog ---");
		mostrarBlogs();

		int codigoBlog = leerEntero("Código del blog: ");

		control.obtenerPublicaciones(codigoBlog);

		menuBlog(codigoBlog);
	}

	private static void menuBlog(int codigoBlog) {
		int opcion = -1;

		while (opcion != 0) {
			System.out.println("\n===== MENÚ DEL BLOG " + codigoBlog + " =====");
			System.out.println("1. Ver publicaciones");
			System.out.println("2. Crear publicación");
			System.out.println("0. Regresar");

			opcion = leerEntero("Opción: ");

			try {
				switch (opcion) {
				case 1:
					mostrarPublicaciones(codigoBlog);
					break;
				case 2:
					crearPublicacion(codigoBlog);
					break;
				case 0:
					System.out.println("Regresando al menú principal.");
					break;
				default:
					System.out.println("Opción inválida.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	private static void mostrarPublicaciones(int codigoBlog) throws Exception {
		Map<Integer, String> publicaciones = control.obtenerPublicaciones(codigoBlog);

		System.out.println("\n--- Publicaciones ---");
		imprimirMapa(publicaciones);
	}

	private static void crearPublicacion(int codigoBlog) throws Exception {
		System.out.println("\n--- Crear publicación ---");

		System.out.print("Título: ");
		String titulo = entrada.nextLine();

		System.out.print("Texto: ");
		String texto = entrada.nextLine();

		System.out.print("Nombre del creador: ");
		String nombreCreador = entrada.nextLine();

		control.crearPublicacion(codigoBlog, titulo, texto, nombreCreador);

		System.out.println("Publicación creada correctamente.");
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