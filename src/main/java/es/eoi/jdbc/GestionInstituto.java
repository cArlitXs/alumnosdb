package es.eoi.jdbc;

import java.sql.SQLException;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoServiceImp;

public class GestionInstituto {

	private static AlumnoServiceImp alumnoServiceImp;

	public static void main(String[] args) throws SQLException {

		Alumno a1 = new Alumno("123456789", "Carlos", "Alvarez L.", 26);
		Alumno a2 = new Alumno("897564231", "Qwe", "Rty", 20);

		System.out.println("-----------------------CREAR------------------------");
		crearAlumno(a1);
		crearAlumno(a2);
		System.out.println("----------------------------------------------------");

		
		
		System.out.println("--------------------LEER TODOS----------------------");
		leerTodos();
		System.out.println("----------------------------------------------------");

		
		
		System.out.println("---------------------ELIMINAR-----------------------");
		eliminarAlumno("897564231");
		System.out.println("----------------------------------------------------");

		
		
		System.out.println("--------------------LEER TODOS----------------------");
		leerTodos();
		System.out.println("----------------------------------------------------");

		
		
		System.out.println("--------------------ACTUALIZAR----------------------");
		actualizarAlumno("123456789", "Carlos Moises", "Alvarez Linares");
		System.out.println("----------------------------------------------------");

		
		
		System.out.println("--------------------LEER TODOS----------------------");
		leerTodos();
		System.out.println("----------------------------------------------------");

		
		
		System.out.println("--------------------ENCONTRAR-----------------------");
		encontrarDniAlumno("123456789");
		System.out.println("----------------------------------------------------");
	}

	private static void leerTodos() throws SQLException {
		try {
			alumnoServiceImp = new AlumnoServiceImp();
			for (Alumno a : alumnoServiceImp.findAll()) {
				System.out.println(a);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void crearAlumno(Alumno alumno) throws SQLException {
		try {
			alumnoServiceImp = new AlumnoServiceImp();
			if(alumnoServiceImp.create(alumno))
				System.out.println("Creado con exito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void eliminarAlumno(String dni) throws SQLException {
		try {
			if (alumnoServiceImp.delete(dni))
				System.out.println("Eliminado con exito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void actualizarAlumno(String dni, String nombre, String apellidos) throws SQLException {
		try {
			if (alumnoServiceImp.update(dni, nombre, apellidos))
				System.out.println("Actualizado con exito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void encontrarDniAlumno(String dni) throws SQLException {
		try {
			Alumno alumno = alumnoServiceImp.findByDni(dni);
			if (alumno != null)
				System.out.println(alumno.toString());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
