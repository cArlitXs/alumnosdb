package es.eoi.jdbc.service;

import java.sql.SQLException;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public interface AlumnoService {

	public Alumno findByDni(String dni);
	public List<Alumno> findAll() throws SQLException;
	public boolean create(Alumno alumno) throws SQLException;
	public boolean delete(String dni);
	public boolean update(String dni, String nombre, String apellidos);
	
}
