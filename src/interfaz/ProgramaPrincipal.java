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

	control.agregarComentario(1, 1,
			"ocelot@gru.ru",
			"10.0.0.7",
			"Buen post. Tiene más puntería que mis pruebas unitarias.");

	control.agregarComentario(1, 2,
			"volgin@gru.ru",
			"10.0.0.38",
			"Le falta electricidad a esa excepción, pero va bien.");

	control.agregarComentario(2, 3,
			"sokolov@lab.ru",
			"10.0.0.33",
			"El borsch no compila, pero reconforta.");

	control.agregarComentario(3, 4,
			"raikov@gru.ru",
			"10.0.0.44",
			"Aprobado, siempre que nadie toque producción.");
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
			System.out.println("3. Ver publicación y comentarios");
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
				case 3:
					entrarPublicacion(codigoBlog);
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
	
	private static void entrarPublicacion(int codigoBlog) throws Exception {
		System.out.println("\n--- Escoger publicación ---");
		mostrarPublicaciones(codigoBlog);

		int codigoPublicacion = leerEntero("Código de la publicación: ");

		control.obtenerPublicacion(codigoBlog, codigoPublicacion);

		menuPublicacion(codigoBlog, codigoPublicacion);
	}

	private static void menuPublicacion(int codigoBlog, int codigoPublicacion) {
		int opcion = -1;

		while (opcion != 0) {
			try {
				System.out.println("\n===== PUBLICACIÓN =====");
				System.out.println(control.obtenerPublicacion(codigoBlog, codigoPublicacion));

				System.out.println("\n1. Agregar comentario");
				System.out.println("2. Borrar comentario");
				System.out.println("0. Regresar");

				opcion = leerEntero("Opción: ");

				switch (opcion) {
				case 1:
					agregarComentario(codigoBlog, codigoPublicacion);
					break;
				case 2:
					borrarComentario(codigoBlog, codigoPublicacion);
					break;
				case 0:
					System.out.println("Regresando al menú del blog.");
					break;
				default:
					System.out.println("Opción inválida.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}

	private static void agregarComentario(int codigoBlog, int codigoPublicacion) throws Exception {
		System.out.println("\n--- Agregar comentario ---");

		System.out.print("Email: ");
		String email = entrada.nextLine();

		System.out.print("IP: ");
		String ip = entrada.nextLine();

		System.out.print("Texto: ");
		String texto = entrada.nextLine();

		control.agregarComentario(codigoBlog, codigoPublicacion, email, ip, texto);

		System.out.println("Comentario agregado correctamente.");
	}

	private static void borrarComentario(int codigoBlog, int codigoPublicacion) throws Exception {
		System.out.println("\n--- Borrar comentario ---");
		System.out.println("Recuerde que la primera posición es 0.");

		int posicion = leerEntero("Posición del comentario: ");

		control.borrarComentario(codigoBlog, codigoPublicacion, posicion);

		System.out.println("Comentario borrado correctamente.");
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