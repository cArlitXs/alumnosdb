package es.eoi.jdbc;

import java.sql.SQLException;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoServiceImp;

public class GestionInstituto {
	
	private static AlumnoServiceImp alumnoServiceImp;
	
	public static void main(String[] args) throws SQLException {
		alumnoServiceImp = new AlumnoServiceImp();
		
//		Alumno a1 = new Alumno("456123789", "Asd", "Fgh", 30);
//		Alumno a2 = new Alumno("897564231", "Qwe", "Rty", 20);
//		
//		crearAlumno(a1);
//		crearAlumno(a2);
		
		leerTodos();
	}

	private static void leerTodos() throws SQLException{
		try {
			for (Alumno a : alumnoServiceImp.findAll()) {
				System.out.println(a);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void crearAlumno(Alumno alumno) throws SQLException {
		try {
			if(alumnoServiceImp.create(alumno))
				System.out.println("Creado con exito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
