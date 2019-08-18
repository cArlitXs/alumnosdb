package es.eoi.jdbc;

import java.sql.SQLException;
import java.util.Scanner;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoServiceImp;

public class GestionInstituto {

	private static AlumnoServiceImp alumnoServiceImp;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {

//		Alumno a1 = new Alumno("123456789", "Carlos", "Alvarez L.", 26);
//		Alumno a2 = new Alumno("897564231", "Qwe", "Rty", 20);
//
//		System.out.println("-----------------------CREAR------------------------");
//		crearAlumno(a1);
//		crearAlumno(a2);
//		System.out.println("----------------------------------------------------");
//
//		
//		
//		System.out.println("--------------------LEER TODOS----------------------");
//		leerTodos();
//		System.out.println("----------------------------------------------------");
//
//		
//		
//		System.out.println("---------------------ELIMINAR-----------------------");
//		eliminarAlumno("897564231");
//		System.out.println("----------------------------------------------------");
//
//		
//		
//		System.out.println("--------------------LEER TODOS----------------------");
//		leerTodos();
//		System.out.println("----------------------------------------------------");
//
//		
//		
//		System.out.println("--------------------ACTUALIZAR----------------------");
//		actualizarAlumno("123456789", "Carlos Moises", "Alvarez Linares");
//		System.out.println("----------------------------------------------------");
//
//		
//		
//		System.out.println("--------------------LEER TODOS----------------------");
//		leerTodos();
//		System.out.println("----------------------------------------------------");
//
//		
//		
//		System.out.println("--------------------ENCONTRAR-----------------------");
//		encontrarDniAlumno("123456789");
//		System.out.println("----------------------------------------------------");

		Scanner scanner;
		String input;

		do {
			System.out.println("Alumnos:");
			System.out.println("--------");

			System.out.println("1. Crear alumno");
			System.out.println("2. Leer alumno");
			System.out.println("3. Actualizar alumno");
			System.out.println("4. Eliminar alumno");
			System.out.println("5. Leer todos los alumnos");

			System.out.println("0. Salir");

			scanner = new Scanner(System.in);
			input = scanner.nextLine();

			if (input.compareTo("1") == 0) {
				do {
					String dni = "";
					String nombre = "";
					String apellidos = "";
					Integer edad = 0;

					System.out.println("Escriba el DNI:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						dni = input;

					System.out.println("Escriba el nombre:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						nombre = input;

					System.out.println("Escriba los apellidos:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						apellidos = input;

					System.out.println("Escriba la edad:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						edad = Integer.parseInt(input);

					if (input.compareTo("") != 0) {
						Alumno alumno = new Alumno(dni, nombre, apellidos, edad);
						crearAlumno(alumno);
					}

					System.out.println("Volviendo al menú...");
					input = "";

				} while (input.compareTo("") != 0);
			}

			if (input.compareTo("2") == 0) {
				System.out.println("Escriba el DNI del alumno:");
				scanner = new Scanner(System.in);
				input = scanner.nextLine();
				
				if (input.compareTo("") != 0)
					encontrarDniAlumno(input);

				System.out.println("Volviendo al menú...");
				input = "";
			}

			if (input.compareTo("3") == 0) {
				do {
					String dni = "";
					String nombre = "";
					String apellidos = "";

					System.out.println("Escriba el DNI:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						dni = input;

					System.out.println("Escriba el nombre:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						nombre = input;

					System.out.println("Escriba los apellidos:");
					scanner = new Scanner(System.in);
					input = scanner.nextLine();
					if (input.compareTo("") != 0)
						apellidos = input;

					if (input.compareTo("") != 0)
						actualizarAlumno(dni, nombre, apellidos);

					System.out.println("Volviendo al menú...");
					input = "";

				} while (input.compareTo("") != 0);
			}

			if (input.compareTo("4") == 0) {
				System.out.println("Escriba el DNI del alumno:");
				scanner = new Scanner(System.in);
				input = scanner.nextLine();
				
				if (input.compareTo("") != 0)
					eliminarAlumno(input);

				System.out.println("Volviendo al menú...");
				input = "";
			}

			if (input.compareTo("5") == 0) {
				leerTodos();
				input = "";
			}

			if (input.compareTo("0") == 0) {
				System.out.println("¡Hasta luego!");
			}

		} while (input.compareTo("0") != 0);
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
			if (alumnoServiceImp.create(alumno))
				System.out.println("Creado con exito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void eliminarAlumno(String dni) throws SQLException {
		try {
			alumnoServiceImp = new AlumnoServiceImp();
			if (alumnoServiceImp.delete(dni))
				System.out.println("Eliminado con exito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void actualizarAlumno(String dni, String nombre, String apellidos) throws SQLException {
		try {
			alumnoServiceImp = new AlumnoServiceImp();
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
