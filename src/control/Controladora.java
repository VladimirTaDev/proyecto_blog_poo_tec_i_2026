package control;

import java.util.Map;
import java.util.TreeMap;

import logica.Blog;

public class Controladora {
	private Map<Integer, Blog> blogs;

	public Controladora() {
		blogs = new TreeMap<Integer, Blog>();
	}

	public void crearBlog(String nombre, String descripcion) {
		Blog b = new Blog(nombre, descripcion);
		blogs.put(b.getCodigo(), b);
	}

	private void revisarBlogExistente(int codigoBlog) throws Exception {
		if (!blogs.containsKey(codigoBlog)) {
			throw new Exception("Código de blog no encontrado.");
		}
	}

	public void borrarBlog(int codigoBlog) throws Exception {
		revisarBlogExistente(codigoBlog);
		blogs.remove(codigoBlog);
	}

	public Map<Integer, String> obtenerBlogs() {
		Map<Integer, String> resultado = new TreeMap<Integer, String>();

		for (Blog b : blogs.values()) {
			resultado.put(b.getCodigo(), b.getNombre());
		}

		return resultado;
	}

	public void crearPublicacion(int codigoBlog, String titulo, String texto, String nombreCreador)
			throws Exception {
		revisarBlogExistente(codigoBlog);

		Blog b = blogs.get(codigoBlog);
		b.crearPublicacion(titulo, texto, nombreCreador);
	}

	public Map<Integer, String> obtenerPublicaciones(int codigoBlog) throws Exception {
		revisarBlogExistente(codigoBlog);

		Blog b = blogs.get(codigoBlog);
		return b.getTitulosPublicaciones();
	}

	public String obtenerPublicacion(int codigoBlog, int codigoPublicacion) throws Exception {
		revisarBlogExistente(codigoBlog);

		Blog b = blogs.get(codigoBlog);
		return b.getRepresentacionPublicacion(codigoPublicacion);
	}

	public void agregarComentario(int codigoBlog, int codigoPublicacion, String email, String ip, String texto)
			throws Exception {
		revisarBlogExistente(codigoBlog);

		Blog b = blogs.get(codigoBlog);
		b.addComentario(codigoPublicacion, email, ip, texto);
	}

	public void borrarComentario(int codigoBlog, int codigoPublicacion, int posicion) throws Exception {
		revisarBlogExistente(codigoBlog);

		Blog b = blogs.get(codigoBlog);
		b.borrarComentario(codigoPublicacion, posicion);
	}
}